package com.huateng.report.imports.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.huateng.ebank.business.common.PageQueryCondition;
import com.huateng.ebank.business.common.PageQueryResult;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.ApplicationContextUtils;

import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.AmsDszhFeedback;
import resources.east.data.pub.AmsDszhFeedback_data;

public class DszhFeedbackService {
	public synchronized static DszhFeedbackService getInstance() {
		return (DszhFeedbackService)ApplicationContextUtils.getBean(DszhFeedbackService.class.getName());
	}
	
	/**
	 * 主要反馈报文的读取成功数和失败数
	 * @param path
	 * @param filename
	 * @return
	 */
	public String readFeedback(String path, String filename) {
		
        String read = null;;
        FileReader fileread = null;
        BufferedReader bufread=null;
        try {
            fileread = new FileReader(path + filename);
            bufread = new BufferedReader(fileread);
            
            read = bufread.readLine();
             
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(bufread!=null)
					bufread.close();
			    if(fileread!=null)
			    	fileread.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return read;
		
	}
	
	/**
	 * 主要读取失败数据的账号和结果代码
	 * @param path
	 * @param filename
	 * @return
	 * @throws Exception 
	 */
	public String readFeedback_data(String path, String filename) throws Exception {
		AmsDszhFeedback_data amsDszhFeedback_data = new AmsDszhFeedback_data();
		FileInputStream in = new FileInputStream(path + filename);
		InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;
		int i = 0;
		
		String[] temp = null;
		String zh = null;
		String result_code = null;
		while((line = bufReader.readLine())!= null) {
			i ++;
			if(i == 1) {
				continue;
			}
			temp = line.split("\\|");
			zh = temp[17];
			result_code = temp[43];
			
			amsDszhFeedback_data.setFilename(filename);
			amsDszhFeedback_data.setZh(zh);
			amsDszhFeedback_data.setJgdm(result_code);
			
			
			saveFeedback(amsDszhFeedback_data);
			saveFeedback(result_code, "3", zh); //3表示报送失败
			
		}
		bufReader.close();
		inReader.close();
		return null;
	}
	
	/**
	 * 主要保存反馈文件名称，读取时候的日期，反馈文件类型，以及成功失败的条数
	 * @param amsDszhFeedback
	 * @throws CommonException
	 */
	public void saveFeedback(AmsDszhFeedback amsDszhFeedback) throws CommonException {
		ROOTDAO  rootDAO = ROOTDAOUtils.getROOTDAO();
		rootDAO.save(amsDszhFeedback);
	}
	
	/**
	 * 主要保存反馈报文中记录的账号和错误代码及文件名称
	 * @param amsDszhFeedback
	 * @throws CommonException
	 */
	public void saveFeedback(AmsDszhFeedback_data amsDszhFeedback_data) throws CommonException {
		ROOTDAO  rootDAO = ROOTDAOUtils.getROOTDAO();
		rootDAO.save(amsDszhFeedback_data);
	}
	
	/**
	 * 根据账户向ams_dszh表中添加校验代码
	 * @param amsDszh
	 * @throws CommonException
	 */
	public void saveFeedback(String jgdm, String report_status, String zh) throws CommonException {
		ROOTDAO  rootDAO = ROOTDAOUtils.getROOTDAO();
		String sql = "update ams_dszh set jgdm='"+ jgdm +"', report_status='"+ report_status +"' where zh='"+ zh +"'";

		rootDAO.executeSql(sql);
	}
	
	/**
	 * 分页服务
	 * @param pageIndex
	 * @param maxRows
	 * @param hql
	 * @return
	 * @throws CommonException
	 */
	public PageQueryResult pageQueryByHql(int pageIndex,int maxRows,String hql) throws CommonException {
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		PageQueryResult pageQueryResult = null;
		PageQueryCondition queryCondition = new PageQueryCondition();
		queryCondition.setQueryString(hql);
		queryCondition.setPageIndex(pageIndex);
		queryCondition.setPageSize(maxRows);
		pageQueryResult = rootDAO.pageQueryByQL(queryCondition);
		return pageQueryResult;
	}
}
