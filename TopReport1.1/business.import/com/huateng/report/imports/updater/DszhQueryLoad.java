package com.huateng.report.imports.updater;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huateng.commquery.result.MultiUpdateResultBean;
import com.huateng.commquery.result.UpdateResultBean;
import com.huateng.commquery.result.UpdateReturnBean;
import com.huateng.ebank.framework.web.commQuery.BaseUpdate;
import com.huateng.ebank.framework.web.struts.ResponseContentType;
import com.huateng.exception.AppException;
import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.PackZipUtil;
import com.huateng.report.utils.ReportUtils;
public class DszhQueryLoad extends HttpServlet {
	private String contentType = "";
	private String headerValue = "";
	private String argFilePath = "";
	private InputStream inStream = null;
	private HttpServletResponse response = null;
	private HttpServletRequest request = null;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request=request;
		this.response=response;	
		String jlrq = request.getParameter("jlrq");
		String[]jlrqs=jlrq.split("");
		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US);
		Date date;
		try {
			date = df.parse(jlrq);
			jlrq = new SimpleDateFormat("MMdd").format(date);
			if(jlrqs.length==33){
			jlrq=jlrqs[29]+jlrqs[30]+jlrqs[31]+jlrqs[32]+jlrq;
			}else{
			jlrq=jlrqs[30]+jlrqs[31]+jlrqs[32]+jlrqs[33]+jlrq;
			}
			String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0105, "");
			//filePath=filePath+File.separator+jlrq.substring(0,6)+File.separator+jlrq+File.separator;
			filePath = filePath + File.separator + jlrq + File.separator;
			File file = new File(filePath);
			
			String sourceFilePath= filePath;
			String zipFilePath = filePath;
			String fileName = jlrq;
			FileToZip.fileToZip(sourceFilePath, zipFilePath, fileName); 
			inStream = new FileInputStream(zipFilePath+fileName + ".zip");
			fileName=fileName+".zip";
			setResponse(fileName);
			download();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//super.doGet(request, response);
	}
	private void setResponse(String fileName) {
		try {
			fileName = new String(fileName.getBytes("GBK"), "8859_1");
			//contentType = ResponseContentType.getContentType(fileName);
			headerValue = "attachment;   filename=" + fileName;
			response.setContentType("application/x-msdownload");
			//response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", headerValue);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void download() {
		try {
			PrintWriter pwriter = response.getWriter();
			int by =-1;
			while ((by=inStream.read()) != -1) {
				pwriter.write(by);
			}
			
			inStream.close();
			pwriter.flush();
			pwriter.close();
		} catch (IOException e) {}
	}
	
}
