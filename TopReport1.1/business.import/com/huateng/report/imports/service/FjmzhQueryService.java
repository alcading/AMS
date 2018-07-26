package com.huateng.report.imports.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.huateng.ebank.business.common.PageQueryCondition;
import com.huateng.ebank.business.common.PageQueryResult;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.ApplicationContextUtils;
import com.huateng.report.tool.FjmzhUtil;

import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsFjmzh;

public class FjmzhQueryService {
	private static final Logger logger = Logger.getLogger(ImportConfigService.class);

	/**
	 * Default constructor
	 */
	protected FjmzhQueryService() {
	}

	/**
	 * get instance.
	 *
	 * @return
	 */
	public synchronized static FjmzhQueryService getInstance() {
		return (FjmzhQueryService)ApplicationContextUtils.getBean(FjmzhQueryService.class.getName());
	}

	public void saveDelUpdata(List delList,List insertList,List updateList) throws CommonException{
		ROOTDAO  rootDAO = ROOTDAOUtils.getROOTDAO();
		AmsFjmzh newwrd = null;

		//新增
		
		for(Iterator it = insertList.iterator();it.hasNext();)
		{
			newwrd = (AmsFjmzh) it.next();
			rootDAO.save(newwrd);
		}
		
		//修改
		
		for(Iterator it = updateList.iterator();it.hasNext();)
		{
			newwrd = (AmsFjmzh) it.next();
			newwrd.setReport_status(FjmzhUtil.NOTSUBMITTED);
			rootDAO.update(newwrd);
			
		}
		//删除
		
		for(Iterator it = delList.iterator();it.hasNext();)
		{
			newwrd = (AmsFjmzh) it.next();
			String accountNumber = newwrd.getAccountNumber();
			String cyrlb = newwrd.getsAccountHolderType();
			String sqldz = this.sqldz(accountNumber);
			String sqlxm = this.sqlxm(accountNumber);
			String sqlgr = this.sqlgr(accountNumber);
			String sqljg = this.sqljg(accountNumber);
			String sqlkzr = this.sqlkzr(accountNumber);
			if("CRS100".equals(cyrlb)){
				rootDAO.delete(sqldz);
				rootDAO.delete(sqlxm);
				rootDAO.delete(sqlgr);
			}else if("CRS101".equals(cyrlb)){
				rootDAO.delete(sqldz);
				rootDAO.delete(sqlxm);
				rootDAO.delete(sqljg);
				rootDAO.delete(sqlkzr);
			}else if("CRS102".equals(cyrlb) || "CRS103".equals(cyrlb)){
				rootDAO.delete(sqldz);
				rootDAO.delete(sqljg);
			}
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
	
	public String sqldz(String accountNumber){
		String sql = " from AmsFjmzhDZ where 1=1";
		if (accountNumber != null && !"".equals(accountNumber)) {
			sql += " and diAccID = '" + accountNumber + "'";
		}
		return sql;
	}
	
	public String sqlxm(String accountNumber){
		String sql = " from AmsFjmzhXM where 1=1";
		if (accountNumber != null && !"".equals(accountNumber)) {
			sql += " and xiAccID = '" + accountNumber + "'";
		}
		return sql;
	}
	
	public String sqlgr(String accountNumber){
		String sql = " from AmsFjmzhGR where 1=1";
		if (accountNumber != null && !"".equals(accountNumber)) {
			sql += " and griAccID = '" + accountNumber + "'";
		}
		return sql;
	}
	
	public String sqljg(String accountNumber){
		String sql = " from AmsFjmzhJG where 1=1";
		if (accountNumber != null && !"".equals(accountNumber)) {
			sql += " and jgiAccID = '" + accountNumber + "'";
		}
		return sql;
	}
	
	public String sqlkzr(String accountNumber){
		String sql = " from AmsFjmzhKZR where 1=1";
		if (accountNumber != null && !"".equals(accountNumber)) {
			sql += " and kzriAccID = '" + accountNumber + "'";
		}
		return sql;
	}
}
