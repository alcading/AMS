package com.huateng.report.imports.updater;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.huateng.commquery.result.MultiUpdateResultBean;
import com.huateng.commquery.result.UpdateResultBean;
import com.huateng.commquery.result.UpdateReturnBean;
import com.huateng.ebank.framework.web.commQuery.BaseUpdate;
import com.huateng.exception.AppException;
import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.ReportUtils;

import resource.bean.pub.Bctl;
import resource.bean.pub.BrnoJbcdLink;
import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsFjmzhRB;

public class FjmzhQueryNoDataOutput extends BaseUpdate {

	/**
	 * 非居民无数据报送
	 */
	private static final Logger logger = Logger.getLogger(DszhQueryOutput.class);

	@Override
	public UpdateReturnBean saveOrUpdate(MultiUpdateResultBean multiUpdateResultBean, HttpServletRequest request,
			HttpServletResponse response)
			throws AppException {
		UpdateReturnBean updateReturnBean = new UpdateReturnBean();
		UpdateResultBean updateResultBean = multiUpdateResultBean.getUpdateResultBeanByID("FjmzhQuery");
		String mk = updateResultBean.getParameter("sAccountType");
		String sAccountType=updateResultBean.getParameter("sAccountType");
		if("".equals(sAccountType) || sAccountType==null){
			sAccountType = "N";
		}
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		//rootDAO.queryByHqlMax(hql)
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
			newRb = updaterb(rb);
			fjmrb.setFjmzhRbN(newRb);
		}else if(sAccountType.equals("P")){
			//非居民存量数据报送批次
			rb = fjmrb.getFjmzhRbP();
			newRb = updaterb(rb);
			fjmrb.setFjmzhRbP(newRb);
		}
		//更新报送批次序列
		rootDAO.update(fjmrb);
		String numStr = addZeroForNum(rb,8);
		String messageRefId = getMessageRefId(numStr,jrjgbm,sAccountType);		
	    writeXML(jrjgbm,messageRefId);
	    
		return updateReturnBean;
	}
	/***
	 * 生成非居民无数据报送xml文件
	 * @param al
	 */
	private void writeXML(String jrjgbm,String messageRefId) {
		try{
			//创建新文件
			Document doc = DocumentHelper
			.createDocument();
			Namespace namespaceS = new Namespace("stc","http://aeoi.chinatax.gov.cn/crs/stctypes/v1");
			
			Namespace namespace = new Namespace("cncrs",
					 "http://aeoi.chinatax.gov.cn/crs/cncrs/v1");
			 Element root = doc.addElement(new QName("CNCRS",namespace));
			//准备节点和属性，文本内容
			//google div 1 
				//员工信息列表 div 2
			root.addNamespace("stc", "http://aeoi.chinatax.gov.cn/crs/stctypes/v1");
			root.addAttribute("version", "1.0");
			Element MessageHeader = root.addElement(new QName("MessageHeader",namespace));
			Element ReportingID = MessageHeader.addElement(new QName("ReportingID",namespace));
			ReportingID.addText(jrjgbm);
			Element FIID = MessageHeader.addElement(new QName("FIID",namespace));
			FIID.addText(jrjgbm);
			Element ReportingType = MessageHeader.addElement(new QName("ReportingType",namespace));
			ReportingType.addText("CRS");
			Element MessageRefId = MessageHeader.addElement(new QName("MessageRefId",namespace));
			MessageRefId.addText(messageRefId);
			Element ReportingPeriod = MessageHeader.addElement(new QName("ReportingPeriod",namespace));
			ReportingPeriod.addText(getYearLast());
			Element MessageTypeIndic = MessageHeader.addElement(new QName("MessageTypeIndic",namespace));
			MessageTypeIndic.addText("CRS703");
			Element Tmstp = MessageHeader.addElement(new QName("Tmstp",namespace));
			Tmstp.addText(getTimeShort());
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			format.setNewlines(true);
			format.setIndent(true);
			format.setSuppressDeclaration(false);
			format.setNewLineAfterDeclaration(false);
			
			
			//StandaloneWriter writer = new StandaloneWriter(System.out,format);  
			
			// 创建文件
			
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date(currentTime);
			String workDate = formatter.format(date);
			String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0107, "");
			filePath = filePath + File.separator + workDate + File.separator;
			String fileName = "cams01300101"+messageRefId;
			//filePath=filePath+File.separator+jlrq.substring(0,6)+File.separator+jlrq+File.separator;
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
			String path = file + File.separator + fileName + ".XML";
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);

			xmlWriter.write(doc);
			xmlWriter.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param numStr
	 * @param jrjgbm
	 * @param datatype
	 * @return
	 */
	public String getMessageRefId(String numStr,String jrjgbm,String datatype){
		String messageRefId = "";
		Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(new Date());
        int t = calendar.get(Calendar.YEAR);
        String year = String.valueOf(t);
		messageRefId = "RE"+jrjgbm+year+datatype+numStr;
		return messageRefId;
	}
	
	/** 
     * 获取前一年最后一天日期 
     * @param year 年份 
     * @return Date 
     * @author caozhi
     */ 

    public String getYearLast(){ 
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(new Date());
        //int year = calendar.get(Calendar.YEAR);
        calendar.add(calendar.YEAR, -1);
        int year = calendar.get(Calendar.YEAR);
        calendar.clear(); 
        calendar.set(Calendar.YEAR, year); 
        calendar.roll(Calendar.DAY_OF_YEAR, -1); 
        Date currYearLast = calendar.getTime();
        String currYearLastStr = new java.sql.Date(currYearLast.getTime()).toString();
        return currYearLastStr; 

    }
    /**
     * 获取当前时间且格式符合(yyyy-MM-ddTHH:mm:ss)
     * @return
     */
    public String getTimeShort() {
       Date date = new Date();
	   SimpleDateFormat nyr = new SimpleDateFormat("yyyy-MM-dd");
	   String date1 = nyr.format(date);
	   SimpleDateFormat sfm = new SimpleDateFormat("HH:mm:ss");
	   String time = sfm.format(date);
	   String dateString = date1+"T"+time;
	   return dateString;
    }
    /**
     * 字符串格式化长度不足8位左补0
     * @param str
     * @param strLength
     * @return
     */
    public String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }
	/**
	 * 将报送批次序列累加1
	 * @param fjmrb
	 * @return
	 */
    public String updaterb(String fjmrb){
        int d = Integer.parseInt(fjmrb);
        fjmrb = ""+(d+1);
    	return fjmrb;
    }

	

		
}
