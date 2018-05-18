package com.huateng.report.imports.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.ApplicationContextUtils;

import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.LMCKXXB;

public class DszhQueryLMService {
	private static final Logger logger = Logger.getLogger(ImportConfigService.class);

	/**
	 * Default constructor
	 */
	protected DszhQueryLMService() {
	}

	/**
	 * get instance.
	 *
	 * @return
	 */
	public synchronized static DszhQueryLMService getInstance() {
		return (DszhQueryLMService)ApplicationContextUtils.getBean(DszhQueryLMService.class.getName());
	}

	public void saveDelUpdata(List delList,List insertList,List updateList) throws CommonException{
		ROOTDAO  rootDAO = ROOTDAOUtils.getROOTDAO();

		//新增
		
		for(Iterator it = insertList.iterator();it.hasNext();)
		{
			LMCKXXB newwrd = (LMCKXXB) it.next();
			
			rootDAO.save(newwrd);
		}
		
		//修改
		LMCKXXB newwrd = null;
		for(Iterator it = updateList.iterator();it.hasNext();)
		{
			newwrd = (LMCKXXB) it.next();
//			AmsDszh list = rootDAO.query(newwrd.getClass(), newwrd.getId());
//			System.out.println(newwrd.getClass());
//			if(list!=null && !"".equals(list)) {
				rootDAO.update(newwrd);
//			}
			
		}
		//删除
		/*
		for(Iterator it = delList.iterator();it.hasNext();)
		{
			BiImportFileConfig newwrd = (BiImportFileConfig) it.next();
			String importidFileid=newwrd.getId();
//			List delimportList=rootDAO.queryByCondition("importFileId='"+importidFileid+"'", "BiImportFieldConfig");
			List delimportList=rootDAO.queryByCondition("from BiImportFieldConfig b where b.importFileId = '"+importidFileid+"'");
			List delxmlList=rootDAO.queryByCondition("from BiImportXmlConfig b where b.guid ='"+importidFileid+"'");
			for(int i=0;i<delimportList.size();i++){
				BiImportFieldConfig delimport=(BiImportFieldConfig)delimportList.get(i);
				rootDAO.delete(delimport);
			}	
			for(int i=0;i<delxmlList.size();i++){
				BiImportXmlConfig delxml=(BiImportXmlConfig)delxmlList.get(i);
				rootDAO.delete(delxml);
			}	
			rootDAO.delete(newwrd);
		}
		*/
	}
}
