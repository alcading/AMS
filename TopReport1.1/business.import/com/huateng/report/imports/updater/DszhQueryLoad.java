package com.huateng.report.imports.updater;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.ReportUtils;

import east.utils.tools.DBUtil;

public class DszhQueryLoad extends HttpServlet {
	private static final Logger logger = Logger.getLogger(DszhQueryLoad.class);
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
	        
	        //对集中账户数据进行备份
	        dataToBackup();
	        	
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	response.getOutputStream().flush();
	    	response.getOutputStream().close();
	        fis.close();
	    }
	}
	
	public void dataToBackup(){
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			//创建执行存储过程的语句对象
	        String sql = "{call dszh_data_Backup()}";
	        CallableStatement callableStatement = conn.prepareCall(sql);
	        //调用存储过程
	        callableStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}