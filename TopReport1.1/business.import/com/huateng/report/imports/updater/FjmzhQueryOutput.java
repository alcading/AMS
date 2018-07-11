package com.huateng.report.imports.updater;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;

import com.huateng.report.imports.common.Constants;
import com.huateng.report.tool.FjmzhUtil;
import com.huateng.report.utils.ReportUtils;

import resource.bean.pub.Bctl;
import resource.bean.pub.BrnoJbcdLink;
import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsFjmzh;
import resources.east.data.pub.AmsFjmzhDZ;
import resources.east.data.pub.AmsFjmzhGR;
import resources.east.data.pub.AmsFjmzhJG;
import resources.east.data.pub.AmsFjmzhKZR;
import resources.east.data.pub.AmsFjmzhRB;
import resources.east.data.pub.AmsFjmzhXM;

public class FjmzhQueryOutput extends HttpServlet{

	/**
	 * 非居民有数据报送
	 */
	private static final Logger logger = Logger.getLogger(DszhQueryOutput.class);

	private HttpServletResponse response = null;
	private HttpServletRequest request = null;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request=request;
		this.response=response;	
		String sAccountType = request.getParameter("sAccountType");
		if(!FjmzhUtil.isEmpty(sAccountType)){
			sAccountType = "N";
		}
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		//rootDAO.queryByHqlMax(hql)
		try {
			List<AmsFjmzh> fjmzh = rootDAO.queryByQL2List(" from AmsFjmzh where report_status in ('0','3')");
			List<AmsFjmzhRB> fjmzhRb = rootDAO.queryByQL2List(" from AmsFjmzhRB");
			List<Bctl> bctl =  rootDAO.queryByQL2List(" from Bctl where brclass = '1'");
			String brno = bctl.get(0).getBrno();
			List<BrnoJbcdLink> brnoJL = rootDAO.queryByQL2List(" from BrnoJbcdLink where brno = '"+brno+"'");
			String jrjgbm = brnoJL.get(0).getJrjgbm();
			String rb = "";
			AmsFjmzhRB fjmrb = fjmzhRb.get(0);
			String newRb = "";
			if(sAccountType.equals("N")){
				//非居民增量数据报送批次
				rb = fjmrb.getFjmzhRbN();
				newRb = FjmzhUtil.updaterb(rb);
				fjmrb.setFjmzhRbN(newRb);
			}else if(sAccountType.equals("P")){
				//非居民存量数据报送批次
				rb = fjmrb.getFjmzhRbP();
				newRb = FjmzhUtil.updaterb(rb);
				fjmrb.setFjmzhRbP(newRb);
			}
			String numStr = FjmzhUtil.addZeroForNum(rb,8);
			String messageRefId = FjmzhUtil.getMessageRefId(numStr,jrjgbm,sAccountType);		
		    writeXML(jrjgbm,messageRefId,fjmzh);
		    //更新报送批次序列
		    rootDAO.update(fjmrb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 生成非居民无数据报送xml文件
	 * @param 
	 */
	private void writeXML(String jrjgbm,String messageRefId,List<AmsFjmzh> fjmzh) {
		try{
			//创建新文件
			Document doc = DocumentHelper.createDocument();
			
			Namespace namespace = new Namespace("cncrs","http://aeoi.chinatax.gov.cn/crs/cncrs/v1");
			Namespace namespaceS = new Namespace("stc","http://aeoi.chinatax.gov.cn/crs/stctypes/v1");
			
			Element root = doc.addElement(new QName("CNCRS",namespace));
			root.addNamespace("stc", "http://aeoi.chinatax.gov.cn/crs/stctypes/v1");
			//生成对应的报文头
			//root.addNamespace("stc", "http://aeoi.chinatax.gov.cn/crs/stctypes/v1");
			root.addAttribute("version", "1.0");
			Element MessageHeader = root.addElement(new QName("MessageHeader",namespace));
			Element ReportingID = MessageHeader.addElement(new QName("ReportingID",namespace));
			ReportingID.addText(jrjgbm);
			Element FIID = MessageHeader.addElement(new QName("FIID",namespace));
			FIID.addText(jrjgbm);
			Element ReportingType = MessageHeader.addElement(new QName("ReportingType",namespace));
			ReportingType.addText(FjmzhUtil.MESSAGEHEADER);
			Element MessageRefId = MessageHeader.addElement(new QName("MessageRefId",namespace));
			MessageRefId.addText(messageRefId);
			Element ReportingPeriod = MessageHeader.addElement(new QName("ReportingPeriod",namespace));
			ReportingPeriod.addText(FjmzhUtil.getYearLast());
			Element MessageTypeIndic = MessageHeader.addElement(new QName("MessageTypeIndic",namespace));
			//暂且固定为701
			MessageTypeIndic.addText(FjmzhUtil.MESSAGETYPEINDIC_1);
			Element Tmstp = MessageHeader.addElement(new QName("Tmstp",namespace));
			Tmstp.addText(FjmzhUtil.getTimeShort());
			//定义对应从表
			AmsFjmzhGR fjmgr = null;
			AmsFjmzhJG fjmjg = null;
			AmsFjmzhKZR fjmkzr = null;
			AmsFjmzhDZ fjmdz = null;
			AmsFjmzhXM fjmxm = null;
			ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
			
			//判断是否有数据进而生成报文体
			if(fjmzh.size()>0){
				//账户具体信息父标签
				Element ReportingGroup = root.addElement(new QName("ReportingGroup",namespace));
				for (AmsFjmzh fjmobj : fjmzh) {
					//账号基本信息
					Element AccountReport = ReportingGroup.addElement(new QName("AccountReport",namespace));
						Element DocSpec = AccountReport.addElement(new QName("DocSpec",namespace));
							Element DocRefId = DocSpec.addElement(new QName("DocRefId",namespaceS));
							DocRefId.addText(FjmzhUtil.getDocRefId(jrjgbm));
							Element DocTypeIndic = DocSpec.addElement(new QName("DocTypeIndic",namespaceS));
							//取值逻辑未确定，暂且默认为R1-新账户记录
							DocTypeIndic.addText("R1");
						Element AccountNumber = AccountReport.addElement(new QName("AccountNumber",namespace));
						AccountNumber.addText(fjmobj.getAccountNumber());
						Element ClosedAccount = AccountReport.addElement(new QName("ClosedAccount",namespace));
						ClosedAccount.addText(fjmobj.getsClosedAccount());
						Element DueDiligenceInd = AccountReport.addElement(new QName("DueDiligenceInd",namespace));
						DueDiligenceInd.addText(fjmobj.getsDueDiligenceInd());
						Element SelfCertification = AccountReport.addElement(new QName("SelfCertification",namespace));
						SelfCertification.addText(fjmobj.getsSelfSertification());
						Element AccountBalance = AccountReport.addElement(new QName("AccountBalance",namespace));
						//给AccountBalance标签添加currCode标签属性
						AccountBalance.addAttribute("currCode", fjmobj.getsACC_currCode());
						AccountBalance.addText(FjmzhUtil.getBigDecimalToString(fjmobj.getiAccountBalance()));
						Element AccountHolderType = AccountReport.addElement(new QName("AccountHolderType",namespace));
						AccountHolderType.addText(fjmobj.getsAccountHolderType());
						Element OpeningFIName = AccountReport.addElement(new QName("OpeningFIName",namespace));
						OpeningFIName.addText(fjmobj.getsOpeningFIName());
						//账户收入不为null以及不等于0时才添加Payment标签
						if(fjmobj.getiPaymentAmnt()!=null && fjmobj.getiPaymentAmnt().compareTo(BigDecimal.ZERO)!=0){
							Element Payment = AccountReport.addElement(new QName("Payment",namespace));
								Element PaymentType = Payment.addElement(new QName("PaymentType",namespaceS));
								//收入类型默认为-利息
								PaymentType.addText(FjmzhUtil.PAYMENTTYPE);
								Element PaymentAmnt = Payment.addElement(new QName("PaymentAmnt",namespaceS));
								PaymentAmnt.addAttribute("currCode", fjmobj.getsPaymentAmntCurr());
								PaymentAmnt.addText(FjmzhUtil.getBigDecimalToString(fjmobj.getiPaymentAmnt()));
						}
					//非居民个人
					if(FjmzhUtil.AC_0.equals(fjmobj.getsAccountHolderType())){
						//通过账号查询到个人信息以及地址和姓名信息
						String acid = fjmobj.getAccountNumber();
						List<AmsFjmzhGR> grList = rootDAO.queryByQL2List(" from AmsFjmzhGR where griAccID = '"+acid+"'");
						fjmgr = grList.get(0);
						List<AmsFjmzhXM> xmList = rootDAO.queryByQL2List(" from AmsFjmzhXM where xiAccID = '"+acid+"' and xStyle='01'");
						fjmxm = xmList.get(0);
						List<AmsFjmzhDZ> dzList = rootDAO.queryByQL2List(" from AmsFjmzhDZ where diAccID = '"+acid+"' and dStyle='01'");
						fjmdz = dzList.get(0);
						
							//账号个人信息
							Element AccountHolder = AccountReport.addElement(new QName("AccountHolder",namespace));
							Element Individual = AccountHolder.addElement(new QName("Individual",namespace));
								//个人姓名详情信息
								Element Name = Individual.addElement(new QName("Name",namespaceS));
								//在名称类型不为空的情况下添加nameType属性
								if(FjmzhUtil.isEmpty(fjmxm.getxNameType())){
									Name.addAttribute("nameType", fjmxm.getxNameType());
								}
									Element LastName = Name.addElement(new QName("LastName",namespaceS));
									LastName.addText(fjmxm.getxFirstName());
									if(FjmzhUtil.isEmpty(fjmxm.getxMiddleName())){
										Element MiddleName = Name.addElement(new QName("MiddleName",namespaceS));
										MiddleName.addText(fjmxm.getxMiddleName());
									}
									Element FirstName = Name.addElement(new QName("FirstName",namespaceS));
									FirstName.addText(fjmxm.getxFirstName());
									if(FjmzhUtil.isEmpty(fjmxm.getxNameCN())){
										Element NameCN = Name.addElement(new QName("NameCN",namespaceS));
										NameCN.addText(fjmxm.getxNameCN());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxPrecedingTitle())){
										Element PrecedingTitle = Name.addElement(new QName("PrecedingTitle",namespaceS));
										PrecedingTitle.addText(fjmxm.getxPrecedingTitle());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxTitle())){
										Element Title = Name.addElement(new QName("Title",namespaceS));
										Title.addText(fjmxm.getxTitle());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxNamePrefix())){
										Element NamePrefix = Name.addElement(new QName("NamePrefix",namespaceS));
										NamePrefix.addText(fjmxm.getxNamePrefix());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxGenerationIdentifier())){
										Element GenerationIdentifier = Name.addElement(new QName("GenerationIdentifier",namespaceS));
										GenerationIdentifier.addText(fjmxm.getxGenerationIdentifier());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxSuffix())){
										Element Suffix = Name.addElement(new QName("Suffix",namespaceS));
										Suffix.addText(fjmxm.getxSuffix());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxGeneralSuffix())){
										Element GeneralSuffix = Name.addElement(new QName("GeneralSuffix",namespaceS));
										GeneralSuffix.addText(fjmxm.getxGeneralSuffix());
									}
								Element Gender = Individual.addElement(new QName("Gender",namespaceS));
								Gender.addText(fjmgr.getGrGender());
								//个人地址详情信息
								Element Address = Individual.addElement(new QName("Address",namespaceS));
								Address.addAttribute("legalAddressType", fjmdz.getSlegalAddressType());
									Element CountryCode = Address.addElement(new QName("CountryCode",namespaceS));
									CountryCode.addText(fjmdz.getsCountryCode());
									//个人英文地址详情
									Element AddressEN = Address.addElement(new QName("AddressEN",namespaceS));
										Element AddressFreeEN = AddressEN.addElement(new QName("AddressFreeEN",namespaceS));
										AddressFreeEN.addText(fjmdz.getsAddressEN_AddressFreeEN());
										Element AddressFixEN = AddressEN.addElement(new QName("AddressFixEN",namespaceS));
											Element CityEN = AddressFixEN.addElement(new QName("CityEN",namespaceS));
											CityEN.addText(fjmdz.getsAddressFixEN_CityEN());
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_Street())){
												Element Street = AddressFixEN.addElement(new QName("Street",namespaceS));
												Street.addText(fjmdz.getsAddressFixEN_Street());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_BuildingIden())){
												Element BuildingIdentifier = AddressFixEN.addElement(new QName("BuildingIdentifier",namespaceS));
												BuildingIdentifier.addText(fjmdz.getsAddressFixEN_BuildingIden());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_SuiteIdentifier())){
												Element SuiteIdentifier = AddressFixEN.addElement(new QName("SuiteIdentifier",namespaceS));
												SuiteIdentifier.addText(fjmdz.getsAddressFixEN_SuiteIdentifier());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_FloorIdentifier())){
												Element FloorIdentifier = AddressFixEN.addElement(new QName("FloorIdentifier",namespaceS));
												FloorIdentifier.addText(fjmdz.getsAddressFixEN_FloorIdentifier());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_DistrictName())){
												Element DistrictName = AddressFixEN.addElement(new QName("DistrictName",namespaceS));
												DistrictName.addText(fjmdz.getsAddressFixEN_DistrictName());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_POB())){
												Element POB = AddressFixEN.addElement(new QName("POB",namespaceS));
												POB.addText(fjmdz.getsAddressFixEN_POB());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_PostCode())){
												Element PostCode = AddressFixEN.addElement(new QName("PostCode",namespaceS));
												PostCode.addText(fjmdz.getsAddressFixEN_PostCode());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_CountrySubentity())){
												Element CountrySubentity = AddressFixEN.addElement(new QName("CountrySubentity",namespaceS));
												CountrySubentity.addText(fjmdz.getsAddressFixEN_CountrySubentity());
											}
									//个人中文地址详情
									if(FjmzhUtil.confirmationAddressCN(fjmdz)){
										Element AddressCN = Address.addElement(new QName("AddressCN",namespaceS));
										if(FjmzhUtil.isEmpty(fjmdz.getsAddressCN_sAddressFreeCN())){
											Element AddressFreeCN = AddressCN.addElement(new QName("AddressFreeCN",namespaceS));
											AddressFreeCN.addText(fjmdz.getsAddressCN_sAddressFreeCN());
										}
										if(FjmzhUtil.confirmationAddressFixCN(fjmdz)){
											Element AddressFixCN = AddressCN.addElement(new QName("AddressFixCN",namespaceS));
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_Province())){
												Element Province = AddressFixCN.addElement(new QName("Province",namespaceS));
												Province.addText(fjmdz.getsAddressFixCN_Province());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_CityCN())){
												Element CityCN = AddressFixCN.addElement(new QName("CityCN",namespaceS));
												CityCN.addText(fjmdz.getsAddressFixCN_CityCN());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_DistrictName())){
												Element CDistrictName = AddressFixCN.addElement(new QName("DistrictName",namespaceS));
												CDistrictName.addText(fjmdz.getsAddressFixCN_DistrictName());
											}
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_PostCode())){
												Element CPostCode = AddressFixCN.addElement(new QName("PostCode",namespaceS));
												CPostCode.addText(fjmdz.getsAddressFixCN_PostCode());
											}
										}
									}
								if(FjmzhUtil.isEmpty(fjmgr.getGrPhoneNo())){
									Element PhoneNo = Individual.addElement(new QName("PhoneNo",namespaceS));
									PhoneNo.addText(fjmgr.getGrPhoneNo());
								}
								if(FjmzhUtil.isEmpty(fjmgr.getGrPhoneNo())){
									Element IDType = Individual.addElement(new QName("IDType",namespaceS));
									IDType.addText(fjmgr.getGrIDType());
								}
								if(FjmzhUtil.isEmpty(fjmgr.getGrIDNumber())){
									Element IDNumber = Individual.addElement(new QName("IDNumber",namespaceS));
									IDNumber.addText(fjmgr.getGrIDNumber());
								}								
								//税收居民国（地区）代码
								Element ResCountryCode = Individual.addElement(new QName("ResCountryCode",namespaceS));
								ResCountryCode.addText(fjmobj.getsResCountryCode());
								if(FjmzhUtil.isEmpty(fjmgr.getGrTIN())){
									//纳税人识别号
									Element TIN = Individual.addElement(new QName("TIN",namespaceS));
									//inType默认类型为TIN
									TIN.addAttribute("inType",FjmzhUtil.INTYPE);
									TIN.addAttribute("issuedBy", fjmgr.getGrIssuedBy());
									TIN.addText(fjmgr.getGrTIN());
								}else{//未取得纳税人识别号原因
									Element Explanation = Individual.addElement(new QName("Explanation",namespaceS));
									Explanation.addText(fjmgr.getGrExplanation());
								}	
								Element Nationality = Individual.addElement(new QName("Nationality",namespaceS));
								Nationality.addText(fjmgr.getGrNationality());
								Element BirthInfo = Individual.addElement(new QName("BirthInfo",namespaceS));
									Element BirthDate = BirthInfo.addElement(new QName("BirthDate",namespaceS));
									BirthDate.addText(FjmzhUtil.dateToString(fjmgr.getGrBirthDate()));
									if(FjmzhUtil.isEmpty(fjmgr.getGrBirthCity())){
										Element City = BirthInfo.addElement(new QName("City",namespaceS));
										City.addText(fjmgr.getGrBirthCity());
									}
									Element CountryInfo = BirthInfo.addElement(new QName("CountryInfo",namespaceS));
										if(FjmzhUtil.isEmpty(fjmgr.getGrBirthCountryCode())){
											Element ACountryCode = CountryInfo.addElement(new QName("CountryCode",namespaceS));
											ACountryCode.addText(fjmgr.getGrBirthCountryCode());
										}else{
											Element FormerCountryName = CountryInfo.addElement(new QName("FormerCountryName",namespaceS));
											FormerCountryName.addText(fjmgr.getGrFormerCountryName());
										}
										
					}
					//有非居民控制人的消极非金融机构
					if(FjmzhUtil.AC_1.equals(fjmobj.getsAccountHolderType())){
						//通过账号查询出机构以及对应地址信息
						String acid = fjmobj.getAccountNumber();
						List<AmsFjmzhJG> jgList = rootDAO.queryByQL2List(" from AmsFjmzhJG where jgiAccID = '"+acid+"'");
						fjmjg = jgList.get(0);
						List<AmsFjmzhDZ> dzList = rootDAO.queryByQL2List(" from AmsFjmzhDZ where diAccID = '"+acid+"' and dStyle='02'");
						fjmdz = dzList.get(0);
						//通过账号查询出控制人以及对应姓名和地址信息
						List<AmsFjmzhKZR> kzrList = rootDAO.queryByQL2List(" from AmsFjmzhKZR where kzriAccID = '"+acid+"'");
						fjmkzr = kzrList.get(0);
						List<AmsFjmzhXM> xmList = rootDAO.queryByQL2List(" from AmsFjmzhXM where xiAccID = '"+acid+"' and xStyle='02'");
						fjmxm = xmList.get(0);
						List<AmsFjmzhDZ> kdzList = rootDAO.queryByQL2List(" from AmsFjmzhDZ where diAccID = '"+acid+"' and dStyle='03'");
						AmsFjmzhDZ kfjmdz = kdzList.get(0);
							Element AccountHolder = AccountReport.addElement(new QName("AccountHolder",namespace));
								//账号机构信息
								Element Organisation = AccountHolder.addElement(new QName("Organisation",namespace));
									//机构名字信息
									Element Name = Organisation.addElement(new QName("Name",namespaceS));
									if(FjmzhUtil.isEmpty(fjmjg.getJgNameType())){
										Name.addAttribute("nameType", fjmjg.getJgNameType());
									}
										Element OrganisationNameEN = Name.addElement(new QName("OrganisationNameEN",namespaceS));
										OrganisationNameEN.addText(fjmjg.getJgOrganisationNameEN());
										if(FjmzhUtil.isEmpty(fjmjg.getJgOrganisationNameCN())){
											Element OrganisationNameCN = Name.addElement(new QName("OrganisationNameCN",namespaceS));
											OrganisationNameCN.addText(fjmjg.getJgOrganisationNameCN());
										}
										
									Element Address = Organisation.addElement(new QName("Address",namespaceS));
									Address.addAttribute("legalAddressType", fjmdz.getSlegalAddressType());
										Element CountryCode = Address.addElement(new QName("CountryCode",namespaceS));
										CountryCode.addText(fjmdz.getsCountryCode());
										//机构英文地址详情
										Element AddressEN = Address.addElement(new QName("AddressEN",namespaceS));
											Element AddressFreeEN = AddressEN.addElement(new QName("AddressFreeEN",namespaceS));
											AddressFreeEN.addText(fjmdz.getsAddressEN_AddressFreeEN());
											Element AddressFixEN = AddressEN.addElement(new QName("AddressFixEN",namespaceS));
												Element CityEN = AddressFixEN.addElement(new QName("CityEN",namespaceS));
												CityEN.addText(fjmdz.getsAddressFixEN_CityEN());
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_Street())){
													Element Street = AddressFixEN.addElement(new QName("Street",namespaceS));
													Street.addText(fjmdz.getsAddressFixEN_Street());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_BuildingIden())){
													Element BuildingIdentifier = AddressFixEN.addElement(new QName("BuildingIdentifier",namespaceS));
													BuildingIdentifier.addText(fjmdz.getsAddressFixEN_BuildingIden());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_SuiteIdentifier())){
													Element SuiteIdentifier = AddressFixEN.addElement(new QName("SuiteIdentifier",namespaceS));
													SuiteIdentifier.addText(fjmdz.getsAddressFixEN_SuiteIdentifier());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_FloorIdentifier())){
													Element FloorIdentifier = AddressFixEN.addElement(new QName("FloorIdentifier",namespaceS));
													FloorIdentifier.addText(fjmdz.getsAddressFixEN_FloorIdentifier());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_DistrictName())){
													Element DistrictName = AddressFixEN.addElement(new QName("DistrictName",namespaceS));
													DistrictName.addText(fjmdz.getsAddressFixEN_DistrictName());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_POB())){
													Element POB = AddressFixEN.addElement(new QName("POB",namespaceS));
													POB.addText(fjmdz.getsAddressFixEN_POB());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_PostCode())){
													Element PostCode = AddressFixEN.addElement(new QName("PostCode",namespaceS));
													PostCode.addText(fjmdz.getsAddressFixEN_PostCode());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_CountrySubentity())){
													Element CountrySubentity = AddressFixEN.addElement(new QName("CountrySubentity",namespaceS));
													CountrySubentity.addText(fjmdz.getsAddressFixEN_CountrySubentity());
												}
										//机构中文地址详情
										if(FjmzhUtil.confirmationAddressCN(fjmdz)){
											Element AddressCN = Address.addElement(new QName("AddressCN",namespaceS));
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressCN_sAddressFreeCN())){
												Element AddressFreeCN = AddressCN.addElement(new QName("AddressFreeCN",namespaceS));
												AddressFreeCN.addText(fjmdz.getsAddressCN_sAddressFreeCN());
											}
											if(FjmzhUtil.confirmationAddressFixCN(fjmdz)){
												Element AddressFixCN = AddressCN.addElement(new QName("AddressFixCN",namespaceS));
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_Province())){
													Element Province = AddressFixCN.addElement(new QName("Province",namespaceS));
													Province.addText(fjmdz.getsAddressFixCN_Province());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_CityCN())){
													Element CityCN = AddressFixCN.addElement(new QName("CityCN",namespaceS));
													CityCN.addText(fjmdz.getsAddressFixCN_CityCN());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_DistrictName())){
													Element CDistrictName = AddressFixCN.addElement(new QName("DistrictName",namespaceS));
													CDistrictName.addText(fjmdz.getsAddressFixCN_DistrictName());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_PostCode())){
													Element CPostCode = AddressFixCN.addElement(new QName("PostCode",namespaceS));
													CPostCode.addText(fjmdz.getsAddressFixCN_PostCode());
												}
											}
										}
									if(FjmzhUtil.isEmpty(fjmjg.getJgPhoneNo())){
										Element PhoneNo = Organisation.addElement(new QName("PhoneNo",namespaceS));
										PhoneNo.addText(fjmjg.getJgPhoneNo());
									}
									Element ResCountryCode = Organisation.addElement(new QName("ResCountryCode",namespaceS));
									ResCountryCode.addText(fjmobj.getsResCountryCode());
									//纳税人识别号:纳税人识别号和未取得纳税人识别号原因必须填写一个
									if(FjmzhUtil.isEmpty(fjmjg.getJgTIN())){
										Element TIN = Organisation.addElement(new QName("TIN",namespaceS));
										//inType默认类型为TIN
										TIN.addAttribute("inType",FjmzhUtil.INTYPE);
										TIN.addAttribute("issuedBy", fjmjg.getJgIssuedBy());
										TIN.addText(fjmjg.getJgTIN());
									}else{//未取得纳税人识别号原因
										Element Explanation = Organisation.addElement(new QName("Explanation",namespaceS));
										Explanation.addText(fjmjg.getJgExplanation());
									}
							Element ControllingPerson = AccountReport.addElement(new QName("ControllingPerson",namespace));
								//控制人姓名详情信息
								Element kName = ControllingPerson.addElement(new QName("Name",namespace));
								//在名称类型不为空的情况下添加nameType属性
								if(FjmzhUtil.isEmpty(fjmxm.getxNameType())){
									kName.addAttribute("nameType", fjmxm.getxNameType());
								}
									Element LastName = kName.addElement(new QName("LastName",namespaceS));
									LastName.addText(fjmxm.getxFirstName());
									if(FjmzhUtil.isEmpty(fjmxm.getxMiddleName())){
										Element MiddleName = kName.addElement(new QName("MiddleName",namespaceS));
										MiddleName.addText(fjmxm.getxMiddleName());
									}
									Element FirstName = kName.addElement(new QName("FirstName",namespaceS));
									FirstName.addText(fjmxm.getxFirstName());
									if(FjmzhUtil.isEmpty(fjmxm.getxNameCN())){
										Element NameCN = kName.addElement(new QName("NameCN",namespaceS));
										NameCN.addText(fjmxm.getxNameCN());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxPrecedingTitle())){
										Element PrecedingTitle = kName.addElement(new QName("PrecedingTitle",namespaceS));
										PrecedingTitle.addText(fjmxm.getxPrecedingTitle());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxTitle())){
										Element Title = kName.addElement(new QName("Title",namespaceS));
										Title.addText(fjmxm.getxTitle());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxNamePrefix())){
										Element NamePrefix = kName.addElement(new QName("NamePrefix",namespaceS));
										NamePrefix.addText(fjmxm.getxNamePrefix());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxGenerationIdentifier())){
										Element GenerationIdentifier = kName.addElement(new QName("GenerationIdentifier",namespaceS));
										GenerationIdentifier.addText(fjmxm.getxGenerationIdentifier());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxSuffix())){
										Element Suffix = kName.addElement(new QName("Suffix",namespaceS));
										Suffix.addText(fjmxm.getxSuffix());
									}
									if(FjmzhUtil.isEmpty(fjmxm.getxGeneralSuffix())){
										Element GeneralSuffix = kName.addElement(new QName("GeneralSuffix",namespaceS));
										GeneralSuffix.addText(fjmxm.getxGeneralSuffix());
									}
								//控制人类型
								Element CtrlgPersonType = ControllingPerson.addElement(new QName("CtrlgPersonType",namespace));
								CtrlgPersonType.addText(fjmkzr.getKzrCtrlgPersonType());
								if(FjmzhUtil.isEmpty(fjmkzr.getKzrNationality())){
									Element kNationality = ControllingPerson.addElement(new QName("Nationality",namespace));
									kNationality.addText(fjmkzr.getKzrNationality());
								}
								//控制人地址信息
								Element kAddress = ControllingPerson.addElement(new QName("Address",namespace));
								kAddress.addAttribute("legalAddressType", kfjmdz.getSlegalAddressType());
								Element kCountryCode = kAddress.addElement(new QName("CountryCode",namespaceS));
								kCountryCode.addText(kfjmdz.getsCountryCode());
								//控制人英文地址详情
								Element kAddressEN = kAddress.addElement(new QName("AddressEN",namespaceS));
									Element kAddressFreeEN = kAddressEN.addElement(new QName("AddressFreeEN",namespaceS));
									kAddressFreeEN.addText(kfjmdz.getsAddressEN_AddressFreeEN());
									Element kAddressFixEN = kAddressEN.addElement(new QName("AddressFixEN",namespaceS));
										Element kCityEN = kAddressFixEN.addElement(new QName("CityEN",namespaceS));
										kCityEN.addText(kfjmdz.getsAddressFixEN_CityEN());
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixEN_Street())){
											Element kStreet = kAddressFixEN.addElement(new QName("Street",namespaceS));
											kStreet.addText(kfjmdz.getsAddressFixEN_Street());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixEN_BuildingIden())){
											Element kBuildingIdentifier = kAddressFixEN.addElement(new QName("BuildingIdentifier",namespaceS));
											kBuildingIdentifier.addText(kfjmdz.getsAddressFixEN_BuildingIden());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixEN_SuiteIdentifier())){
											Element kSuiteIdentifier = kAddressFixEN.addElement(new QName("SuiteIdentifier",namespaceS));
											kSuiteIdentifier.addText(kfjmdz.getsAddressFixEN_SuiteIdentifier());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixEN_FloorIdentifier())){
											Element kFloorIdentifier = kAddressFixEN.addElement(new QName("FloorIdentifier",namespaceS));
											kFloorIdentifier.addText(kfjmdz.getsAddressFixEN_FloorIdentifier());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixEN_DistrictName())){
											Element kDistrictName = kAddressFixEN.addElement(new QName("DistrictName",namespaceS));
											kDistrictName.addText(kfjmdz.getsAddressFixEN_DistrictName());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixEN_POB())){
											Element kPOB = kAddressFixEN.addElement(new QName("POB",namespaceS));
											kPOB.addText(kfjmdz.getsAddressFixEN_POB());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixEN_PostCode())){
											Element kPostCode = kAddressFixEN.addElement(new QName("PostCode",namespaceS));
											kPostCode.addText(kfjmdz.getsAddressFixEN_PostCode());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixEN_CountrySubentity())){
											Element kCountrySubentity = kAddressFixEN.addElement(new QName("CountrySubentity",namespaceS));
											kCountrySubentity.addText(kfjmdz.getsAddressFixEN_CountrySubentity());
										}
								//控制人中文地址详情
								if(FjmzhUtil.confirmationAddressCN(kfjmdz)){
									Element kAddressCN = kAddress.addElement(new QName("AddressCN",namespaceS));
									if(FjmzhUtil.isEmpty(kfjmdz.getsAddressCN_sAddressFreeCN())){
										Element kAddressFreeCN = kAddressCN.addElement(new QName("AddressFreeCN",namespaceS));
										kAddressFreeCN.addText(kfjmdz.getsAddressCN_sAddressFreeCN());
									}
									if(FjmzhUtil.confirmationAddressFixCN(kfjmdz)){
										Element kAddressFixCN = kAddressCN.addElement(new QName("AddressFixCN",namespaceS));
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixCN_Province())){
											Element kProvince = kAddressFixCN.addElement(new QName("Province",namespaceS));
											kProvince.addText(kfjmdz.getsAddressFixCN_Province());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixCN_CityCN())){
											Element kCityCN = kAddressFixCN.addElement(new QName("CityCN",namespaceS));
											kCityCN.addText(kfjmdz.getsAddressFixCN_CityCN());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixCN_DistrictName())){
											Element kCDistrictName = kAddressFixCN.addElement(new QName("DistrictName",namespaceS));
											kCDistrictName.addText(kfjmdz.getsAddressFixCN_DistrictName());
										}
										if(FjmzhUtil.isEmpty(kfjmdz.getsAddressFixCN_PostCode())){
											Element kCPostCode = kAddressFixCN.addElement(new QName("PostCode",namespaceS));
											kCPostCode.addText(kfjmdz.getsAddressFixCN_PostCode());
										}
									}
								}
							Element kResCountryCode = ControllingPerson.addElement(new QName("ResCountryCode",namespace));
							kResCountryCode.addText(fjmkzr.getKzrResCountryCode());
							if(FjmzhUtil.isEmpty(fjmkzr.getKzrTIN())){
								//纳税人识别号
								Element kTIN = ControllingPerson.addElement(new QName("TIN",namespace));
								//inType默认类型为TIN
								kTIN.addAttribute("inType",FjmzhUtil.INTYPE);
								kTIN.addAttribute("issuedBy",fjmkzr.getKzrIssuedBy());
								kTIN.addText(fjmkzr.getKzrTIN());
							}else{//未取得纳税人识别号原因
								Element kExplanation = ControllingPerson.addElement(new QName("Explanation",namespace));
								kExplanation.addText(fjmkzr.getKzrExplanation());
							}	
							Element kBirthInfo = ControllingPerson.addElement(new QName("BirthInfo",namespace));
								Element kBirthDate = kBirthInfo.addElement(new QName("BirthDate",namespaceS));
								kBirthDate.addText(FjmzhUtil.dateToString(fjmkzr.getKzrdBirthDate()));
								if(FjmzhUtil.isEmpty(fjmkzr.getKzrBirthCity())){
									Element kCity = kBirthInfo.addElement(new QName("City",namespaceS));
									kCity.addText(fjmkzr.getKzrBirthCity());
								}
								Element kCountryInfo = kBirthInfo.addElement(new QName("CountryInfo",namespaceS));
									if(FjmzhUtil.isEmpty(fjmkzr.getKzrBirthCountryCode())){
										Element ACountryCode = kCountryInfo.addElement(new QName("CountryCode",namespaceS));
										ACountryCode.addText(fjmkzr.getKzrBirthCountryCode());
									}else{
										Element FormerCountryName = kCountryInfo.addElement(new QName("FormerCountryName",namespaceS));
										FormerCountryName.addText(fjmkzr.getKzrFormerCountryName());
									}
									
					}
					//非居民机构，不包括消极非金融机构/非居民消极非金融机构，但没有非居民控制人
					if(FjmzhUtil.AC_2.equals(fjmobj.getsAccountHolderType()) || FjmzhUtil.AC_3.equals(fjmobj.getsAccountHolderType())){
						//通过账号查询出机构以及对应地址信息
						String acid = fjmobj.getAccountNumber();
						List<AmsFjmzhJG> jgList = rootDAO.queryByQL2List(" from AmsFjmzhJG where jgiAccID = '"+acid+"'");
						fjmjg = jgList.get(0);
						List<AmsFjmzhDZ> dzList = rootDAO.queryByQL2List(" from AmsFjmzhDZ where diAccID = '"+acid+"' and dStyle='02'");
						fjmdz = dzList.get(0);
							Element AccountHolder = AccountReport.addElement(new QName("AccountHolder",namespace));
								//账号机构信息
								Element Organisation = AccountHolder.addElement(new QName("Organisation",namespace));
								
									Element Name = Organisation.addElement(new QName("Name",namespaceS));
									if(FjmzhUtil.isEmpty(fjmjg.getJgNameType())){
										Name.addAttribute("nameType", fjmjg.getJgNameType());
									}
										Element OrganisationNameEN = Name.addElement(new QName("OrganisationNameEN",namespaceS));
										OrganisationNameEN.addText(fjmjg.getJgOrganisationNameEN());
										if(FjmzhUtil.isEmpty(fjmjg.getJgOrganisationNameCN())){
											Element OrganisationNameCN = Name.addElement(new QName("OrganisationNameCN",namespaceS));
											OrganisationNameCN.addText(fjmjg.getJgOrganisationNameCN());
										}
										
									Element Address = Organisation.addElement(new QName("Address",namespaceS));
									Address.addAttribute("legalAddressType", fjmdz.getSlegalAddressType());
										Element CountryCode = Address.addElement(new QName("CountryCode",namespaceS));
										CountryCode.addText(fjmdz.getsCountryCode());
										//机构英文地址详情
										Element AddressEN = Address.addElement(new QName("AddressEN",namespaceS));
											Element AddressFreeEN = AddressEN.addElement(new QName("AddressFreeEN",namespaceS));
											AddressFreeEN.addText(fjmdz.getsAddressEN_AddressFreeEN());
											Element AddressFixEN = AddressEN.addElement(new QName("AddressFixEN",namespaceS));
												Element CityEN = AddressFixEN.addElement(new QName("CityEN",namespaceS));
												CityEN.addText(fjmdz.getsAddressFixEN_CityEN());
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_Street())){
													Element Street = AddressFixEN.addElement(new QName("Street",namespaceS));
													Street.addText(fjmdz.getsAddressFixEN_Street());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_BuildingIden())){
													Element BuildingIdentifier = AddressFixEN.addElement(new QName("BuildingIdentifier",namespaceS));
													BuildingIdentifier.addText(fjmdz.getsAddressFixEN_BuildingIden());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_SuiteIdentifier())){
													Element SuiteIdentifier = AddressFixEN.addElement(new QName("SuiteIdentifier",namespaceS));
													SuiteIdentifier.addText(fjmdz.getsAddressFixEN_SuiteIdentifier());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_FloorIdentifier())){
													Element FloorIdentifier = AddressFixEN.addElement(new QName("FloorIdentifier",namespaceS));
													FloorIdentifier.addText(fjmdz.getsAddressFixEN_FloorIdentifier());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_DistrictName())){
													Element DistrictName = AddressFixEN.addElement(new QName("DistrictName",namespaceS));
													DistrictName.addText(fjmdz.getsAddressFixEN_DistrictName());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_POB())){
													Element POB = AddressFixEN.addElement(new QName("POB",namespaceS));
													POB.addText(fjmdz.getsAddressFixEN_POB());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_PostCode())){
													Element PostCode = AddressFixEN.addElement(new QName("PostCode",namespaceS));
													PostCode.addText(fjmdz.getsAddressFixEN_PostCode());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixEN_CountrySubentity())){
													Element CountrySubentity = AddressFixEN.addElement(new QName("CountrySubentity",namespaceS));
													CountrySubentity.addText(fjmdz.getsAddressFixEN_CountrySubentity());
												}
										//机构中文地址详情
										if(FjmzhUtil.confirmationAddressCN(fjmdz)){
											Element AddressCN = Address.addElement(new QName("AddressCN",namespaceS));
											if(FjmzhUtil.isEmpty(fjmdz.getsAddressCN_sAddressFreeCN())){
												Element AddressFreeCN = AddressCN.addElement(new QName("AddressFreeCN",namespaceS));
												AddressFreeCN.addText(fjmdz.getsAddressCN_sAddressFreeCN());
											}
											if(FjmzhUtil.confirmationAddressFixCN(fjmdz)){
												Element AddressFixCN = AddressCN.addElement(new QName("AddressFixCN",namespaceS));
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_Province())){
													Element Province = AddressFixCN.addElement(new QName("Province",namespaceS));
													Province.addText(fjmdz.getsAddressFixCN_Province());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_CityCN())){
													Element CityCN = AddressFixCN.addElement(new QName("CityCN",namespaceS));
													CityCN.addText(fjmdz.getsAddressFixCN_CityCN());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_DistrictName())){
													Element CDistrictName = AddressFixCN.addElement(new QName("DistrictName",namespaceS));
													CDistrictName.addText(fjmdz.getsAddressFixCN_DistrictName());
												}
												if(FjmzhUtil.isEmpty(fjmdz.getsAddressFixCN_PostCode())){
													Element CPostCode = AddressFixCN.addElement(new QName("PostCode",namespaceS));
													CPostCode.addText(fjmdz.getsAddressFixCN_PostCode());
												}
											}
										}
									if(FjmzhUtil.isEmpty(fjmjg.getJgPhoneNo())){
										Element PhoneNo = Organisation.addElement(new QName("PhoneNo",namespaceS));
										PhoneNo.addText(fjmjg.getJgPhoneNo());
									}
									Element ResCountryCode = Organisation.addElement(new QName("ResCountryCode",namespaceS));
									ResCountryCode.addText(fjmobj.getsResCountryCode());
									//纳税人识别号:纳税人识别号和未取得纳税人识别号原因必须填写一个
									if(FjmzhUtil.isEmpty(fjmjg.getJgTIN())){
										Element TIN = Organisation.addElement(new QName("TIN",namespaceS));
										//inType默认类型为TIN
										TIN.addAttribute("inType",FjmzhUtil.INTYPE);
										TIN.addAttribute("issuedBy", fjmjg.getJgIssuedBy());
										TIN.addText(fjmjg.getJgTIN());
									}else{//未取得纳税人识别号原因
										Element Explanation = Organisation.addElement(new QName("Explanation",namespaceS));
										Explanation.addText(fjmjg.getJgExplanation());
									}
					}
				}
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			format.setNewlines(true);
			format.setIndent(true);
			format.setSuppressDeclaration(false);
			format.setNewLineAfterDeclaration(false);
			
			//白茶清欢无别事，我在等风也等你~~~
			
			// 创建文件
			
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date(currentTime);
			String workDate = formatter.format(date);
			String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0107, "");
			filePath = filePath + File.separator + workDate + File.separator;
			String fileName = FjmzhUtil.CAMS+messageRefId;
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
			String path = file + File.separator + fileName + ".XML";
			StandaloneWriter xmlWriter = new StandaloneWriter(new PrintStream(path), format);

			xmlWriter.write(doc);
			xmlWriter.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
}
