package com.huateng.report.imports.updater;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.ReportUtils;

import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsFjmzh;
import resources.east.data.pub.AmsFjmzhMessageInfo;
public class FjmzhQueryLoad extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("messageName");
		String workDate = null;
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		List<AmsFjmzhMessageInfo> messageInfo;
		try {
			messageInfo = rootDAO.queryByQL2List(" from AmsFjmzhMessageInfo where messageName= '"+fileName+"'");
			workDate = messageInfo.get(0).getImportDate();
		} catch (CommonException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0107, "");
		filePath = filePath + File.separator + workDate + File.separator;
		
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
			}catch (Exception e) {	
		        e.printStackTrace();	
		    } finally {	
		    	response.getOutputStream().flush();	
		    	response.getOutputStream().close();	
		        fis.close();	
	    }
		
		
	}
	
}
