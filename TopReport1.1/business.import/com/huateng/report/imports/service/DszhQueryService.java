package com.huateng.report.imports.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.huateng.ebank.business.common.PageQueryCondition;
import com.huateng.ebank.business.common.PageQueryResult;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.ApplicationContextUtils;

import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsDszh;

public class DszhQueryService {
	private static final Logger logger = Logger.getLogger(ImportConfigService.class);

	/**
	 * Default constructor
	 */
	protected DszhQueryService() {
	}

	/**
	 * get instance.
	 *
	 * @return
	 */
	public synchronized static DszhQueryService getInstance() {
		return (DszhQueryService)ApplicationContextUtils.getBean(DszhQueryService.class.getName());
	}

	public void saveDelUpdata(List delList, List insertList,List updateList) throws CommonException{
		ROOTDAO  rootDAO = ROOTDAOUtils.getROOTDAO();

		//新增
		
		for(Iterator it = insertList.iterator();it.hasNext();)
		{
			AmsDszh newwrd = (AmsDszh)it.next();
			
			rootDAO.save(newwrd);
		}
		
		//修改
		AmsDszh newwrd = null;
		for(Iterator it = updateList.iterator();it.hasNext();)
		{
			newwrd = (AmsDszh) it.next();
//			AmsDszh list = rootDAO.query(newwrd.getClass(), newwrd.getId());
//			System.out.println(newwrd.getClass());
//			if(list!=null && !"".equals(list)) {
				rootDAO.update(newwrd);
////			}
//			
		}
		//删除
		
		for(Iterator it = delList.iterator();it.hasNext();)
		{
			newwrd = (AmsDszh) it.next();
			
			rootDAO.delete(newwrd);
		}
		
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
