package com.huateng.report.update;

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

import east.dao.BaseDao;
import resources.east.data.pub.AmsDszh;

public class DszhQueryUpdate extends BaseGetter {
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
		
		String zh = (String)para.get("zh");
		StringBuffer hql = new StringBuffer();
		
		int pageSize = this.getResult().getPage().getEveryPage();
		int pageIndex = this.getResult().getPage().getCurrentPage();
		
		hql.append("from AmsDszh A where 1 = 1 ");
		
		if(StringUtils.isNotBlank(zh)){
			hql.append(" and A.zh = '"+zh.trim()+"' ");
		}
		return DszhQueryService.getInstance().pageQueryByHql(pageIndex, pageSize, hql.toString());
		
	}

}
