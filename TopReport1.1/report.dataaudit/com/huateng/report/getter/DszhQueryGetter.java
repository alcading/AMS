package com.huateng.report.getter;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.huateng.common.err.Module;
import com.huateng.common.err.Rescode;
import com.huateng.commquery.result.Result;
import com.huateng.commquery.result.ResultMng;
import com.huateng.ebank.business.common.PageQueryResult;
import com.huateng.ebank.framework.web.commQuery.BaseGetter;
import com.huateng.exception.AppException;
import com.huateng.report.imports.service.DszhQueryService;


@SuppressWarnings("unchecked")
public class DszhQueryGetter extends BaseGetter{
	
	@Override
	public Result call() throws AppException {
		// TODO Auto-generated method stub
		try {
			PageQueryResult pageResult = getData();
			ResultMng.fillResultByList(
				getCommonQueryBean(),
				getCommQueryServletRequest(),
				pageResult.getQueryResult(),
				getResult());
			result.setContent(pageResult.getQueryResult());
			result.getPage().setTotalPage(pageResult.getPageCount(getResult().getPage().getEveryPage()));
			
			result.init();
			
			return result;
		}catch(AppException appEx){
			throw appEx;
		}catch(Exception ex){
			throw new AppException(Module.SYSTEM_MODULE,
					Rescode.DEFAULT_RESCODE, ex.getMessage(),ex);
		}
	}
	
	
	private PageQueryResult getData() throws AppException {
		Map para = this.getCommQueryServletRequest().getParameterMap();
		
		String ckrxm = (String)para.get("ckrxm");
		String ckrsfzjhm = (String)para.get("ckrsfzjhm");
		String zh = (String)para.get("zh");
		String xxlx = (String)para.get("xxlx");
		String jlrq = (String)para.get("jlrq");
		String report_status = (String)para.get("report_status");
		
//		String jlrq = getCommQueryServletRequest().getParameter("jlrq");
		
		StringBuffer hql = new StringBuffer();
		
		int pageSize = this.getResult().getPage().getEveryPage();
		int pageIndex = this.getResult().getPage().getCurrentPage();
		
		hql.append("from AmsDszh A,Globalinfo B where 1 = 1 ");
		
		if(StringUtils.isNotBlank(ckrxm)){
			hql.append(" and A.ckrxm = '"+ckrxm.trim()+"' ");
		}
		if(StringUtils.isNotBlank(ckrsfzjhm)){
			hql.append(" and A.ckrsfzjhm = '"+ckrsfzjhm.trim()+"' ");
	    }
		if(StringUtils.isNotBlank(zh)){
			hql.append(" and A.zh = '"+zh.trim()+"' ");
		}
		if(StringUtils.isNotBlank(xxlx)){
			hql.append(" and A.xxlx = '"+xxlx.trim()+"' ");
		}
		if(StringUtils.isNotBlank(report_status)){
			hql.append(" and A.report_status = '"+report_status.trim()+"' ");
		}
		if(StringUtils.isNotBlank(jlrq)){
			hql.append(" and A.jlrq = '"+jlrq.trim().toUpperCase()+"' ");
		}else {
			hql.append(" and 1 = 0 ");
		}
		return DszhQueryService.getInstance().pageQueryByHql(pageIndex, pageSize, hql.toString());
		
	}
	
}