package com.huateng.report.imports.updater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huateng.commquery.result.MultiUpdateResultBean;
import com.huateng.commquery.result.ResultMng;
import com.huateng.commquery.result.UpdateResultBean;
import com.huateng.commquery.result.UpdateReturnBean;
import com.huateng.ebank.business.common.service.DataDicService;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.web.commQuery.BaseUpdate;
import com.huateng.exception.AppException;
import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.ReportUtils;

import east.utils.tools.DBUtil;
import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.KXXB;
import resources.east.data.pub.LMCKXXB;

public class DszhQueryOutput extends BaseUpdate {
	private static final Logger logger = Logger.getLogger(DszhQueryOutput.class);
	@Override
	public UpdateReturnBean saveOrUpdate(MultiUpdateResultBean multiUpdateResultBean, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		//Connection conn = DBUtil.getConnection();
		UpdateReturnBean updateReturnBean = new UpdateReturnBean();
		UpdateResultBean updateResultBean = multiUpdateResultBean.getUpdateResultBeanByID("DszhQuery");
		String jlrq=updateResultBean.getParameter("jlrq");
		
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		List<AmsDszh> list = rootDAO.queryByQL2List(" from AmsDszh model where model.jlrq='" + jlrq + "'");
		
		BufferedWriter bw=null;
		String jrjgbm=null;
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date(currentTime);
		String workDate = formatter.format(date);
		String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0105, "");
		filePath = filePath + File.separator + jlrq + File.separator;
		//filePath=filePath+File.separator+jlrq.substring(0,6)+File.separator+jlrq+File.separator;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		StringBuilder bf=new StringBuilder();
		bf.append(
				"存款人姓名(存款人子信息)|存款人身份证件种类(存款人子信息)|存款人身份证件号码(存款人子信息)|身份证件到期日(存款人子信息)|发证机关所在地的地区代码(存款人子信息)|存款人类别(存款人子信息)|存款人国籍(存款人子信息)|存款人性别(存款人子信息)|存款人邮编|存款人地址|存款人电话|代理人名称|代理人身份证件种类|代理人身份证件号码|代理人国籍|代理人电话|开户银行金融机构编码（不可变更）|账号（不可变更）|账户种类|介质号（介质子信息）|介质到期日（介质子信息）|账户介质（介质子信息）|介质注销日期(介质子信息)|介质状态（介质子信息）|账户类型|II、III类户绑定账户账号(绑定账户子信息)|II、III类户绑定账户开户银行金融机构编码(绑定账户子信息)|开户日期|销户日期(不可变)|账户状态|币种|是否为军人保障卡|是否为社会保障卡|核实结果|无法核实原因|处置方法|信息类型|开户渠道|备注|开通的非柜面交易渠道|是否为\"联名账户\"|开户地地区代码|预留字段4|预留字段5");
		bf.append("\r\n");
		Iterator it = list.iterator();
		for(AmsDszh amsDszh:list) {
//			Map map = updateResultBean.next();
//			mapToObject(amsDszh, map);
			String sflmzh = amsDszh.getSflmzh();
			jrjgbm = amsDszh.getJrjgbm();
			jlrq = amsDszh.getJlrq();
			String report_status = amsDszh.getReport_status();
			if(!report_status.equals("0")&&(!report_status.equals("3"))){
				continue;
			}
			String zh = amsDszh.getZh();
			
				 
				try {
					outPutFile(jlrq,sflmzh, zh,bf);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				try {
//					bw.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		}
		try {
			int min = 10000;
			int max = 99999;
			filePath = filePath + "[" + jrjgbm + "]" + "[cams00100101]" + "[" + workDate
					+ (new Random().nextInt(max) % (max + min + 1) + min) + "]";
			File txtFile = new File(filePath + ".txt");
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "UTF-8"));
			bw.write(bf.toString());	
	    bw.flush();
	    bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateReturnBean;
	}
	

	public static int outPutFile(String jlrq,String sflmzh, String zh,StringBuilder bf) throws IOException {
		Integer lmccount=0;
		Integer counts=0;
		Connection conn = DBUtil.getConnection();
		AmsDszh amsDszh = new AmsDszh();
		KXXB kxxb = new KXXB();
		LMCKXXB lmckxxb = new LMCKXXB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CKRXM,CKRSFZJZL,CKRSFZJHM,SFZJDQR,DQDM,CKRLB,CKRGJDQ,CKRXB,CKRYB,CKRDZ,CKRDH,DLRMC,DLRSFZJZL,DLRSFZJHM,DLRGJDQ,DLRDH,JRJGBM,ZH,ZHZL,ZHLX,YLZHZH,YLZHJGBM,KHRQ,XHRQ,"
				+ "ZHZT,BZ,SFJRBZK,SFSHBZK,HSJG,WFHSYY,CZFF,XXLX,KHQD,REMARKS,RESERVE4,RESERVE5,JLZT,JLRQ,ISMODIFY,FGMJYQD,SFLMZH,KHDQDM FROM AMS_DSZH WHERE ZH="
				+ zh;
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		StringBuilder builder3 = new StringBuilder();
		StringBuilder builder4 = new StringBuilder();
		StringBuilder builder5 = new StringBuilder();
		StringBuilder builder6 = new StringBuilder();
		StringBuilder builder7 = new StringBuilder();
		StringBuilder builder8 = new StringBuilder();
		String sql_update = "update ams_dszh t set t.report_status='1' where zh="+zh;
		try {
			pstmt = conn.prepareStatement(sql_update);
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String sql_lmckxxb = "select COUNT(*) FROM AMS_LMCKXXB where zh=" + zh;
		try {
			pstmt = conn.prepareStatement(sql_lmckxxb);
			rs = pstmt.executeQuery();
			while(rs.next()){
				lmccount=rs.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String sql_lmckxxb1 = "select CKRXM,CKRSFZJZL,CKRSFZJHM,SFZJDQR,DQDM,CKRLB,CKRGJDQ,CKRXB from AMS_LMCKXXB where zh=" + zh;
		try {
			pstmt = conn.prepareStatement(sql_lmckxxb1);
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(lmccount<1){
					String ckrxm = rs.getString("CKRXM");
					if(ckrxm!=null){
						lmckxxb.setCkrxm(ckrxm);
					}else{
						lmckxxb.setCkrxm("");
					}
				  String ckrsfzjzl = rs.getString("CKRSFZJZL");
				  if(ckrsfzjzl!=null){
					  lmckxxb.setCkrsfzjzl(ckrsfzjzl);
				  }else{
					  lmckxxb.setCkrsfzjzl("");
				  }
				  String ckrsfzjhm = rs.getString("CKRSFZJHM");
				  if(ckrsfzjhm!=null){
					  lmckxxb.setCkrsfzjhm(ckrsfzjhm);
				  }else{
					  lmckxxb.setCkrsfzjhm("");
				  }
				  String sfzjdqr = rs.getString("SFZJDQR");
				  if(sfzjdqr!=null){
					  lmckxxb.setSfzjdqr(sfzjdqr);
				  }else{
					  lmckxxb.setSfzjdqr("");
				  }
				  String dqdm = rs.getString("DQDM");
				  if(dqdm!=null){
					  lmckxxb.setDqdm(dqdm);
				  }else{
					  lmckxxb.setDqdm("");
				  }
				  String ckrlb= rs.getString("CKRLB");
				  if(ckrlb!=null){
					  lmckxxb.setCkrlb(ckrlb);
				  }else{
					  lmckxxb.setCkrlb("");
				  }
				  String ckrgjdq = rs.getString("CKRGJDQ");
				  if(ckrgjdq!=null){
					  lmckxxb.setCkrgjdq(ckrgjdq);
				  }else{
					  lmckxxb.setCkrgjdq("");
				  }
				  String ckrxb = rs.getString("CKRXB");
				  if(ckrxb!=null){
					  lmckxxb.setCkrxb(ckrxb);
				  }else{
					  lmckxxb.setCkrxb(ckrxb);
				  }
				}else{
					counts++;
					String ckrxm = rs.getString("CKRXM");
                    if(counts==lmccount){
						if(ckrxm!=null){
						builder1.append(ckrxm);
						}else{
							ckrxm="";
							builder1.append(ckrxm);
						}
						
					}else{
					if(ckrxm!=null){
						ckrxm=ckrxm+";";
						builder1.append(ckrxm);
					}else{
						ckrxm=""+";";
						builder1.append(ckrxm);
					}
					}
					String ckrsfzjzl= rs.getString("CKRSFZJZL");
					if(counts==lmccount){
						if(ckrsfzjzl!=null){
							builder2.append(ckrsfzjzl);
						}else{
							ckrsfzjzl="";
							builder2.append(ckrsfzjzl);
						}
					}else{
					if(ckrsfzjzl!=null){
						ckrsfzjzl=ckrsfzjzl+";";
						builder2.append(ckrsfzjzl);
					}else{
						ckrsfzjzl=""+";";
						builder2.append(ckrsfzjzl);
					}
					}
					String ckrsfzjhm = rs.getString("CKRSFZJHM");
					if(counts==lmccount){
						if(ckrsfzjhm!=null){
							builder3.append(ckrsfzjhm);
						}else{
							ckrsfzjhm="";
							builder3.append(ckrsfzjhm);
						}
					}else{
					if(ckrsfzjhm!=null){
						ckrsfzjhm=ckrsfzjhm+";";
						builder3.append(ckrsfzjhm);
					}else{
						ckrsfzjhm=""+";";
						builder3.append(ckrsfzjhm);
					}
					}
					String sfzjdqr = rs.getString("SFZJDQR");
					if(counts==lmccount){
						if(sfzjdqr!=null){
							builder4.append(sfzjdqr);
						}else{
							sfzjdqr="";
							builder4.append(sfzjdqr);
						}
					}else{
					if(sfzjdqr!=null){
						sfzjdqr=sfzjdqr+";";
						builder4.append(sfzjdqr);
					}else{
						sfzjdqr=""+";";
						builder4.append(sfzjdqr);
					}
					}
					String dqdm = rs.getString("DQDM");
					if(counts==lmccount){
						if(dqdm!=null){
							builder5.append(dqdm);
						}else{
							dqdm="";
							builder5.append(dqdm);
						}
					}else{
					if(dqdm!=null){
						dqdm=dqdm+";";
						builder5.append(dqdm);
					}else{
						dqdm=""+";";
						builder5.append(dqdm);
					}
					}
					String ckrlb = rs.getString("CKRLB");
					if(counts==lmccount){
						if(ckrlb!=null){
							builder6.append(ckrlb);
						}else{
							ckrlb="";
						}
						
					}else{
					if(ckrlb!=null){
						ckrlb=ckrlb+";";
						builder6.append(ckrlb);
					}else{
						ckrlb=""+";";
						builder6.append(ckrlb);
					}
					}
					String ckrgjdq = rs.getString("CKRGJDQ");
					if(counts==lmccount){
						if(ckrgjdq!=null){
							builder7.append(ckrgjdq);
						}else{
							ckrgjdq="";
							builder7.append(ckrgjdq);
						}
					}else{
					if(ckrgjdq!=null){
						ckrgjdq=ckrgjdq+";";
						builder7.append(ckrgjdq);
					}else{
						ckrgjdq=""+";";
						builder7.append(ckrgjdq);
					}
					}
					String ckrxb = rs.getString("CKRXB");
					if(counts==lmccount){
						if(ckrxb!=null){
							builder8.append(ckrxb);
						}else{
							ckrxb="";
						}
					}else{
					if(ckrxb!=null){
						ckrxb=ckrxb+";";
						builder8.append(ckrxb);
					}else{
						ckrxb=""+";";
						builder8.append(ckrxb);
					}
					}
				}
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String ckrxm = rs.getString("CKRXM");
				if (ckrxm != null) {
					amsDszh.setCkrxm(ckrxm);
				} else {
					amsDszh.setCkrxm("");
				}
				String ckrsfzjzl = rs.getString("CKRSFZJZL");
				if (ckrsfzjzl != null) {
					amsDszh.setCkrsfzjzl(ckrsfzjzl);
				} else {
					amsDszh.setCkrsfzjzl("");
				}
				String ckrsfzjhm = rs.getString("CKRSFZJHM");
				if (ckrsfzjhm != null) {
					amsDszh.setCkrsfzjhm(ckrsfzjhm);
				} else {
					amsDszh.setCkrsfzjhm("");
				}
				String sfzjdqr = rs.getString("SFZJDQR");
				if (sfzjdqr != null) {
					amsDszh.setSfzjdqr(sfzjdqr);
				} else {
					amsDszh.setSfzjdqr("");
				}
				String ckrlb = rs.getString("CKRLB");
				if (ckrlb != null) {
					amsDszh.setCkrlb(ckrlb);
				} else {
					amsDszh.setCkrlb("");
				}
				String ckrgjdq = rs.getString("CKRGJDQ");
				if (ckrgjdq != null) {
					amsDszh.setCkrgjdq(ckrgjdq);
				} else {
					amsDszh.setCkrgjdq("");
				}
				String ckrxb = rs.getString("CKRXB");
				if (ckrxb != null) {
					amsDszh.setCkrxb(ckrxb);
				} else {
					amsDszh.setCkrxb("");
				}
				String ckryb = rs.getString("CKRYB");
				if (ckryb != null) {
					amsDszh.setCkryb(ckryb);
				} else {
					amsDszh.setCkryb("");
				}
				String ckrdz = rs.getString("CKRDZ");
				if (ckrdz != null) {
					amsDszh.setCkrdz(ckrdz);
				} else {
					amsDszh.setCkrdz("");
				}
				String ckrdh = rs.getString("CKRDH");
				if (ckrdh != null) {
					amsDszh.setCkrdh(ckrdh);
				} else {
					amsDszh.setCkrdh("");
				}
				String dlrmc = rs.getString("DLRMC");
				if (dlrmc != null) {
					amsDszh.setDlrmc(dlrmc);
				} else {
					amsDszh.setDlrmc("");
				}
				String dlrsfzjzl = rs.getString("DLRSFZJZL");
				if (dlrsfzjzl != null) {
					amsDszh.setDlrsfzjzl(dlrsfzjzl);
				} else {
					amsDszh.setDlrsfzjzl("");
				}
				String dlrsfzjhm = rs.getString("DLRSFZJHM");
				if (dlrsfzjhm != null) {
					amsDszh.setDlrsfzjhm(dlrsfzjhm);
				} else {
					amsDszh.setDlrsfzjhm("");
				}
				String dlrgjdq = rs.getString("DLRGJDQ");
				if (dlrgjdq != null) {
					amsDszh.setDlrgjdq(dlrgjdq);
				} else {
					amsDszh.setDlrgjdq("");
				}
				String dlrdh = rs.getString("DLRDH");
				if (dlrdh != null) {
					amsDszh.setDlrdh(dlrdh);
				} else {
					amsDszh.setDlrdh("");
				}
				String jrjgbm = rs.getString("JRJGBM");
				if (jrjgbm != null) {
					amsDszh.setJrjgbm(jrjgbm);
				} else {
					amsDszh.setJrjgbm("");
				}
				String zh1 = rs.getString("ZH");
				if (zh1 != null) {
					amsDszh.setZh(zh1);
				} else {
					amsDszh.setZh("");
				}
				String zhzl = rs.getString("ZHZL");
				if (zhzl != null) {
					amsDszh.setZhzl(zhzl);
				} else {
					amsDszh.setZhzl("");
				}
				String zhlx = rs.getString("ZHLX");
				if (zhlx != null) {
					amsDszh.setZhlx(zhlx);
				} else {
					amsDszh.setZhlx("");
				}
				String ylzhzh = rs.getString("YLZHZH");
				if (ylzhzh != null) {
					amsDszh.setYlzhzh(ylzhzh);
				} else {
					amsDszh.setYlzhzh("");
				}
				String ylzhjgbm = rs.getString("YLZHJGBM");
				if (ylzhjgbm != null) {
					amsDszh.setYlzhjgbm(ylzhjgbm);
				} else {
					amsDszh.setYlzhjgbm("");
				}
				String khrq = rs.getString("KHRQ");
				if (khrq != null) {
					amsDszh.setKhrq(khrq);
				} else {
					amsDszh.setKhrq("");
				}
				String xhrq = rs.getString("XHRQ");
				if (xhrq != null) {
					amsDszh.setXhrq(xhrq);
				} else {
					amsDszh.setXhrq("");
				}
				String zhzt = rs.getString("ZHZT");
				if (zhzt != null) {
					amsDszh.setZhzt(zhzt);
				} else {
					amsDszh.setZhzt("");
				}
				String bz = rs.getString("BZ");
				if (bz != null) {
					amsDszh.setBz(bz);
				} else {
					amsDszh.setBz("");
				}
				String sfjrbzk = rs.getString("SFJRBZK");
				if (sfjrbzk != null) {
					amsDszh.setSfjrbzk(sfjrbzk);
				} else {
					amsDszh.setSfjrbzk("");
				}
				String sfshbzk = rs.getString("SFSHBZK");
				if (sfshbzk != null) {
					amsDszh.setSfshbzk(sfshbzk);
				} else {
					amsDszh.setSfshbzk("");
				}
				String hsjg = rs.getString("HSJG");
				if (hsjg != null) {
					amsDszh.setHsjg(hsjg);
				} else {
					amsDszh.setHsjg("");
				}
				String wfhsyy = rs.getString("WFHSYY");
				if (wfhsyy != null) {
					amsDszh.setWfhsyy(wfhsyy);
				} else {
					amsDszh.setWfhsyy("");
				}
				String czff = rs.getString("CZFF");
				if (czff != null) {
					amsDszh.setCzff(czff);
				} else {
					amsDszh.setCzff("");
				}
				String xxlx = rs.getString("XXLX");
				if (xxlx != null) {
					amsDszh.setXxlx(xxlx);
				} else {
					amsDszh.setXxlx("");
				}
				String khqd = rs.getString("KHQD");
				if (khqd != null) {
					amsDszh.setKhqd(khqd);
				} else {
					amsDszh.setKhqd("");
				}
				String remarks = rs.getString("REMARKS");
				if (remarks != null) {
					amsDszh.setRemarks(remarks);
				} else {
					amsDszh.setRemarks("");
				}
				String reserve4 = rs.getString("RESERVE4");
				if (reserve4 != null) {
					amsDszh.setReserve4(reserve4);
				} else {
					amsDszh.setReserve4("");
				}
				String reserve5 = rs.getString("RESERVE5");
				if (reserve5 != null) {
					amsDszh.setReserve5(reserve5);
				} else {
					amsDszh.setReserve5("");
				}
				String jlzt = rs.getString("JLZT");
				if (jlzt != null) {
					amsDszh.setJlzt(jlzt);
				} else {
					amsDszh.setJlzt("");
				}
				String jlrq1 = rs.getString("JLRQ");
				if (jlrq1 != null) {
					amsDszh.setJlrq(jlrq1);
				} else {
					amsDszh.setJlrq("");
				}
				String fgmjyqd = rs.getString("FGMJYQD");
				if (fgmjyqd != null) {
					amsDszh.setFgmjyqd(fgmjyqd);
				} else {
					amsDszh.setFgmjyqd("");
				}
				String sflmzh1 = rs.getString("SFLMZH");
				if (sflmzh1 != null) {
					amsDszh.setSflmzh(sflmzh1);
				} else {
					amsDszh.setSflmzh("");
				}
				String khdqdm = rs.getString("KHDQDM");
				if (khdqdm != null) {
					amsDszh.setKhdqdm(khdqdm);
				} else {
					amsDszh.setKhdqdm("");
				}
				String dqdm = rs.getString("DQDM");
				if (dqdm != null) {
					amsDszh.setDqdm(dqdm);
				} else {
					amsDszh.setDqdm("");
				}
				String sfzjdqr1 = rs.getString("SFZJDQR");
				if (sfzjdqr1 != null) {
					amsDszh.setSfzjdqr(sfzjdqr1);
				} else {
					amsDszh.setSfzjdqr("");
				}
			}
 			String sql_kxxb = "SELECT count(*) FROM AMS_KXXB WHERE ZH=" + zh;
			Integer count = 0;
			pstmt = conn.prepareStatement(sql_kxxb);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			String sql_kxxb0 = "select KH,KDQR,ZHJZ,XKRQ,KZT from ams_kxxb where zh=" + zh;
			pstmt = conn.prepareStatement(sql_kxxb0);
			rs = pstmt.executeQuery();
			StringBuilder builderkh = new StringBuilder();
			StringBuilder builderkdqr = new StringBuilder();
			StringBuilder builderzhjz = new StringBuilder();
			StringBuilder builderxkrq = new StringBuilder();
			StringBuilder builderkzt = new StringBuilder();
			Integer res=0;
			while (rs.next()) {
				if (count <= 1) {
					String kh = rs.getString("KH");
					if (kh != null) {
						kxxb.setKh(kh);
					} else {
						kxxb.setKh("");
					}
					String kdqr = rs.getString("KDQR");
					if (kdqr != null) {
						kxxb.setKdqr(kdqr);
					} else {
						kxxb.setKdqr("");
					}
					String zhjz = rs.getString("ZHJZ");
					if (zhjz != null) {
						kxxb.setZhjz(zhjz);
					} else {
						kxxb.setZhjz("");
					}
					String xkrq = rs.getString("XKRQ");
					if (xkrq != null) {
						kxxb.setXkrq(xkrq);
					} else {
						kxxb.setXkrq("");
					}
					String kzt = rs.getString("KZT");
					if (kzt != null) {
						kxxb.setKzt(kzt);
					} else {
						kxxb.setKzt("");
					}
				}
				
				if (count > 1) {
					res++;
					String kh = rs.getString("KH");
					if (res == count) {
						if (kh != null) {
							builderkh.append(kh);
						} else {
							kh = "";
							builderkh.append(kh);
						}
					} else {
						if (kh != null) {
							kh = kh + ";";
							builderkh.append(kh);
						} else {
							kh = ""+";";
							builderkh.append(kh);
						}
					}
					// kxxb.setKh(kh);
					String kdqr = rs.getString("KDQR");
					if (res == count) {
						if (kdqr != null) {
							builderkdqr.append(kdqr);
						} else {
							kdqr = "";
							builderkdqr.append(kdqr);
						}
					} else {
						if (kdqr != null) {
							kdqr = kdqr + ";";
							builderkdqr.append(kdqr);
						} else {
							kdqr =  ""+";";
							builderkdqr.append(kdqr);
						}
					}
					// kxxb.setKdqr(kdqr);
					String zhjz = rs.getString("ZHJZ");
					if (res == count) {
						if (zhjz != null) {
							builderzhjz.append(zhjz);
						} else {
							zhjz = "";
							builderzhjz.append(zhjz);
						}
					} else {
						if (zhjz != null) {
							zhjz = zhjz + ";";
							builderzhjz.append(zhjz);
						} else {
							zhjz =  ""+";";
							builderzhjz.append(zhjz);
						}
					}
					// kxxb.setZhjz(zhjz);
					String xkrq = rs.getString("XKRQ");
					if (res == count) {
						if (xkrq != null) {
							builderxkrq.append(xkrq);
						} else {
							xkrq = "";
							builderxkrq.append(xkrq);
						}
					} else {
						if (xkrq != null) {
							xkrq = xkrq + ";";
							builderxkrq.append(xkrq);
						} else {
							xkrq = ""+";";
							builderxkrq.append(xkrq);
						}
					}
					// kxxb.setXkrq(xkrq);
					String kzt = rs.getString("KZT");
					if (res == count) {
						if (kzt != null) {
							builderkzt.append(kzt);
						} else {
							kzt = "";
							builderkzt.append(kzt);
						}
					} else {
						if (kzt != null) {
							kzt = kzt + ";";
							builderkzt.append(kzt);
						} else {
							kzt = ""+";";
							;
							builderkzt.append(kzt);
						}
					}
					// kxxb.setKzt(kzt);
				}
			}
			if (count > 1) {
				kxxb.setKh(builderkh.toString());
				kxxb.setKdqr(builderkdqr.toString());
				kxxb.setZhjz(builderzhjz.toString());
				kxxb.setXkrq(builderxkrq.toString());
				kxxb.setKzt(builderkzt.toString());
			}
			if(lmccount>1){
				lmckxxb.setCkrxm(builder1.toString());
				lmckxxb.setCkrsfzjzl(builder2.toString());
				lmckxxb.setCkrsfzjhm(builder3.toString());
				lmckxxb.setSfzjdqr(builder4.toString());
				lmckxxb.setDqdm(builder5.toString());
				lmckxxb.setCkrlb(builder6.toString());
				lmckxxb.setCkrgjdq(builder7.toString());
				lmckxxb.setCkrxb(builder8.toString());
				bf.append(amsDszh.getCkrxm() +";"+ lmckxxb.getCkrxm()+"|" + amsDszh.getCkrsfzjzl() +";"+lmckxxb.getCkrsfzjzl()+"|" + amsDszh.getCkrsfzjhm() +";"+lmckxxb.getCkrsfzjhm()+ "|"
						+ amsDszh.getSfzjdqr() +";"+lmckxxb.getSfzjdqr()+"|" + amsDszh.getDqdm() +";"+lmckxxb.getDqdm()+ "|" + amsDszh.getCkrlb() + ";"+lmckxxb.getCkrlb()+"|"
						+ amsDszh.getCkrgjdq() +";"+lmckxxb.getCkrgjdq()+ "|" + amsDszh.getCkrxb() + ";"+lmckxxb.getCkrxb()+"|" + amsDszh.getCkryb() + "|"
						+ amsDszh.getCkrdz() + "|" + amsDszh.getCkrdh() + "|" + amsDszh.getDlrmc() + "|"
						+ amsDszh.getDlrsfzjzl() + "|" + amsDszh.getDlrsfzjhm() + "|" + amsDszh.getDlrgjdq() + "|"
						+ amsDszh.getDlrdh() + "|" + amsDszh.getJrjgbm() + "|" + amsDszh.getZh() + "|" + amsDszh.getZhzl()
						+ "|" + kxxb.getKh() + "|" + kxxb.getKdqr() + "|" + kxxb.getZhjz() + "|" + kxxb.getXkrq() + "|"
						+ kxxb.getKzt() + "|" + amsDszh.getZhlx() + "|" + amsDszh.getYlzhzh() + "|" + amsDszh.getYlzhjgbm()
						+ "|" + amsDszh.getKhrq() + "|" + amsDszh.getXhrq() + "|" + amsDszh.getZhzt() + "|"
						+ amsDszh.getBz() + "|" + amsDszh.getSfjrbzk() + "|" + amsDszh.getSfshbzk() + "|"
						+ amsDszh.getHsjg() + "|" + amsDszh.getWfhsyy() + "|" + amsDszh.getCzff().replaceAll(",", ";") + "|" + amsDszh.getXxlx()
						+ "|" + amsDszh.getKhqd() + "|" + amsDszh.getRemarks() + "|" +amsDszh.getFgmjyqd().replaceAll(",", ";") + "|" + amsDszh.getSflmzh() + "|" + amsDszh.getKhdqdm()+ amsDszh.getReserve4() + "|"
						+ amsDszh.getReserve5());
			}else{
				bf.append(amsDszh.getCkrxm() +"|" + amsDszh.getCkrsfzjzl() +"|" + amsDszh.getCkrsfzjhm() + "|"
						+ amsDszh.getSfzjdqr()+"|" + amsDszh.getDqdm()+ "|" + amsDszh.getCkrlb() +"|"
						+ amsDszh.getCkrgjdq() + "|" + amsDszh.getCkrxb() +"|" + amsDszh.getCkryb() + "|"
						+ amsDszh.getCkrdz() + "|" + amsDszh.getCkrdh() + "|" + amsDszh.getDlrmc() + "|"
						+ amsDszh.getDlrsfzjzl() + "|" + amsDszh.getDlrsfzjhm() + "|" + amsDszh.getDlrgjdq() + "|"
						+ amsDszh.getDlrdh() + "|" + amsDszh.getJrjgbm() + "|" + amsDszh.getZh() + "|" + amsDszh.getZhzl()
						+ "|" + kxxb.getKh() + "|" + kxxb.getKdqr() + "|" + kxxb.getZhjz() + "|" + kxxb.getXkrq() + "|"
						+ kxxb.getKzt() + "|" + amsDszh.getZhlx() + "|" + amsDszh.getYlzhzh() + "|" + amsDszh.getYlzhjgbm()
						+ "|" + amsDszh.getKhrq() + "|" + amsDszh.getXhrq() + "|" + amsDszh.getZhzt() + "|"
						+ amsDszh.getBz() + "|" + amsDszh.getSfjrbzk() + "|" + amsDszh.getSfshbzk() + "|"
						+ amsDszh.getHsjg() + "|" + amsDszh.getWfhsyy() + "|" + amsDszh.getCzff().replaceAll(",", ";") + "|" + amsDszh.getXxlx()
						+ "|" + amsDszh.getKhqd() + "|" + amsDszh.getRemarks() + "|" +amsDszh.getFgmjyqd().replaceAll(",", ";") + "|" + amsDszh.getSflmzh() + "|" + amsDszh.getKhdqdm()+ amsDszh.getReserve4() + "|"
						+ amsDszh.getReserve5());
			}
			// String kh=map.get(kxxb).getKh();
			bf.append("\r\n");
		} catch (SQLException e) {
			// TODO Auto-generategd catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		bw.write(bf.toString());
//		bw.flush();
		int ret = 0;
		return ret;
	}
}
