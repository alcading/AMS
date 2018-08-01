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

import resources.east.data.pub.AmsDszhMessageInfo;

public class DszhMessageInfoGetter extends BaseGetter {
	@Override
	public Result call() throws AppException {
		// TODO Auto-generated method stub
		try {
			PageQueryResult pageResult = getData();
			
			List list = pageResult.getQueryResult();
			
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
		
		String messagename = (String)para.get("messagename");
		String datadate = (String)para.get("datadate");
		String borndate = (String)para.get("borndate");
//		String messagestatus = (String)para.get("messagestatus");
		
		StringBuffer hql = new StringBuffer();
		
		int pageSize = this.getResult().getPage().getEveryPage();
		int pageIndex = this.getResult().getPage().getCurrentPage();
		
		hql.append("from AmsDszhMessageInfo A where 1 = 1 ");
		
		
		if(StringUtils.isNotBlank(datadate)){
			hql.append(" and A.datadate = '"+datadate.trim()+"' ");
		}
		if(StringUtils.isNotBlank(borndate)){
			hql.append(" and A.borndate like '%"+borndate.trim()+"%' ");
	    }
//		if(StringUtils.isNotBlank(messagestatus)){
//			hql.append(" and A.messagestatus = '"+messagestatus.trim()+"' ");
//		}
		if(StringUtils.isNotBlank(messagename)){
			hql.append(" and A.messagename like '%"+messagename.trim()+"%' ");
		}
		
		hql.append(" order by A.borndate");
		
		return DszhQueryService.getInstance().pageQueryByHql(pageIndex, pageSize, hql.toString());
		
	}
}
