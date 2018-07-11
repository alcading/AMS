package com.huateng.report.imports.updater;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.ReportUtils;
public class FjmzhQueryLoad extends HttpServlet {
	private String headerValue = "";
	private InputStream inStream = null;
	private HttpServletResponse response = null;
	private HttpServletRequest request = null;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request=request;
		this.response=response;	
		String sAccountType = request.getParameter("sAccountType");
		if("".equals(sAccountType) || sAccountType==null){
			sAccountType = "N";
		}
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date dates = new Date(currentTime);
		String workDate = formatter.format(dates);
		String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0107, "");
		filePath = filePath + File.separator + workDate + File.separator;
		//File file = new File(filePath);
		String sourceFilePath= filePath;
		String zipFilePath = filePath;
		String fileName = workDate;
		FileToZip.fileToZip(sourceFilePath, zipFilePath, fileName); 
		inStream = new FileInputStream(zipFilePath+fileName + ".zip");
		fileName=fileName+".zip";
		setResponse(fileName);
		download();
		
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
