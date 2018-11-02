package com.huateng.report.getter;	
	
import java.util.Date;
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
import com.huateng.report.imports.service.FjmzhQueryService;
import com.huateng.report.tool.FjmzhUtil;

import resources.east.data.pub.AmsFjmzhMessageInfo;	
	
public class FjmzhMessageInfoGetter extends BaseGetter {	
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
		
		String messageName = (String)para.get("messageName");	
		String importDate = (String)para.get("importDate");	
			
		StringBuffer hql = new StringBuffer();	
			
		int pageSize = this.getResult().getPage().getEveryPage();	
		int pageIndex = this.getResult().getPage().getCurrentPage();	
			
		hql.append("from AmsFjmzhMessageInfo A where 1 = 1  order by miid desc");	
			
		if(FjmzhUtil.isEmpty(messageName)){	
			hql.append(" and A.messageName like '%"+messageName.trim()+"%' ");	
		}	
			
		if(FjmzhUtil.isEmpty(importDate)){	
			hql.append(" and A.importDate = '"+importDate.trim().toUpperCase()+"' ");	
		}else{
			hql.append(" and A.importDate = '"+FjmzhUtil.getyymmdd(new Date()).trim().toUpperCase()+"' ");
		}
			
		return FjmzhQueryService.getInstance().pageQueryByHql(pageIndex, pageSize, hql.toString());	
			
	}	
}