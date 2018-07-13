package com.huateng.report.imports.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.ApplicationContextUtils;

import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsFjmzh;
import resources.east.data.pub.AmsFjmzhXM;

public class FjmzhXMQueryService {
	private static final Logger logger = Logger.getLogger(ImportConfigService.class);

	/**
	 * Default constructor
	 */
	protected FjmzhXMQueryService() {
	}

	/**
	 * get instance.
	 *
	 * @return
	 */
	public synchronized static FjmzhXMQueryService getInstance() {
		return (FjmzhXMQueryService)ApplicationContextUtils.getBean(FjmzhXMQueryService.class.getName());
	}

	public void saveDelUpdata(List delList,List insertList,List updateList) throws CommonException{
		ROOTDAO  rootDAO = ROOTDAOUtils.getROOTDAO();
		AmsFjmzhXM newwrd = null;
		//新增
		
		for(Iterator it = insertList.iterator();it.hasNext();)
		{
			newwrd = (AmsFjmzhXM) it.next();
			rootDAO.save(newwrd);
		}
		
		//修改
		
		for(Iterator it = updateList.iterator();it.hasNext();)
		{
			newwrd = (AmsFjmzhXM) it.next();
			rootDAO.update(newwrd);
			
		}
		//删除
		
		for(Iterator it = delList.iterator();it.hasNext();)
		{
			newwrd = (AmsFjmzhXM) it.next();
			rootDAO.delete(newwrd);
		}
		
	}
}
