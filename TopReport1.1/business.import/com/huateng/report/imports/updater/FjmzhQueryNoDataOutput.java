package com.huateng.report.imports.updater;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import resources.east.data.pub.AmsFjmzhRB;

public class FjmzhQueryNoDataOutput extends HttpServlet{

	/**
	 * 非居民无数据报送
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
		
		try {
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
			//更新报送批次序列
			rootDAO.update(fjmrb);
			String numStr = FjmzhUtil.addZeroForNum(rb,8);
			String messageRefId = FjmzhUtil.getMessageRefId(numStr,jrjgbm,sAccountType);		
		    writeXML(jrjgbm,messageRefId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 生成非居民无数据报送xml文件
	 * @param al
	 * @throws IOException 
	 */
	private void writeXML(String jrjgbm,String messageRefId) throws IOException {
		String returnInfo = null;
		try{
			//创建新文件
			Document doc = DocumentHelper
			.createDocument();
			Namespace namespaceS = new Namespace("stc","http://aeoi.chinatax.gov.cn/crs/stctypes/v1");
			
			Namespace namespace = new Namespace("cncrs",
					 "http://aeoi.chinatax.gov.cn/crs/cncrs/v1");
			 Element root = doc.addElement(new QName("CNCRS",namespace));
			//准备节点和属性，文本内容
			root.addNamespace("stc", "http://aeoi.chinatax.gov.cn/crs/stctypes/v1");
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
			MessageTypeIndic.addText(FjmzhUtil.MESSAGETYPEINDIC_3);
			Element Tmstp = MessageHeader.addElement(new QName("Tmstp",namespace));
			Tmstp.addText(FjmzhUtil.getTimeShort());
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			format.setNewlines(true);
			format.setIndent(true);
			format.setSuppressDeclaration(false);
			format.setNewLineAfterDeclaration(false);
			
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date(currentTime);
			String workDate = formatter.format(date);
			String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0107, "");
			filePath = filePath + File.separator + workDate + File.separator;
			String fileName = FjmzhUtil.CAMS+messageRefId;
			//filePath=filePath+File.separator+jlrq.substring(0,6)+File.separator+jlrq+File.separator;
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
			String path = file + File.separator + fileName + ".xml";
			StandaloneWriter xmlWriter = new StandaloneWriter(new PrintStream(path), format);

			xmlWriter.write(doc);
			xmlWriter.close();
			FjmzhUtil.saveMessageInfo(fileName + ".xml");
			returnInfo=FjmzhUtil.MESSAGEINFO+fileName+".xml";
		}
		catch(Exception e){
			e.printStackTrace();
		}
		response.setCharacterEncoding("GB18030");
		response.getWriter().write("<script type='text/javascript'>alert('导出成功!"+returnInfo+"');</script>");
	}
	
}
