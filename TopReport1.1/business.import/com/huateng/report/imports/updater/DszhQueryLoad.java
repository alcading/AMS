package com.huateng.report.imports.updater;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.ReportUtils;

public class DszhQueryLoad extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String fileName = request.getParameter("messagename");
	    String jlrq = request.getParameter("datadate");
	    String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0105, "");
		//filePath=filePath+File.separator+jlrq.substring(0,6)+File.separator+jlrq+File.separator;
		filePath = filePath + File.separator + jlrq + File.separator;

		
	    File file = new File(filePath + fileName);
	    
	    response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition","attachment;filename=" + fileName);
	    response.setContentLength((int) file.length());
	    
	    FileInputStream fis = null;
	    try {
	        fis = new FileInputStream(file);
	        byte[] buffer = new byte[1024];
	        int count = 0;
	        while ((count = fis.read(buffer)) > 0) {
	        	response.getOutputStream().write(buffer, 0, count);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	response.getOutputStream().flush();
	    	response.getOutputStream().close();
	        fis.close();
	    }
	}
}