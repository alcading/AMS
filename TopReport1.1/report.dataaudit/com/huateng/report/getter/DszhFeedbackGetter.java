package com.huateng.report.getter;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.huateng.common.err.Module;
import com.huateng.common.err.Rescode;
import com.huateng.commquery.result.Result;
import com.huateng.commquery.result.ResultMng;
import com.huateng.ebank.business.common.PageQueryResult;
import com.huateng.ebank.framework.web.commQuery.BaseGetter;
import com.huateng.exception.AppException;
import com.huateng.report.imports.service.DszhFeedbackService;

@SuppressWarnings("unchecked")
public class DszhFeedbackGetter extends BaseGetter {

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
		Map paramsMap = this.getCommQueryServletRequest().getParameterMap();
		String input_date = (String)paramsMap.get("input_date");
		StringBuffer hql = new StringBuffer();
		int pageSize = this.getResult().getPage().getEveryPage();
		int pageIndex = this.getResult().getPage().getCurrentPage();
		
		hql.append("from AmsDszhFeedback A where 1 = 1 ");
		
		if(StringUtils.isNotBlank(input_date)){
			hql.append(" and A.date between to_date ('"+ input_date +" 00:00:00','yyyy/mm/dd hh24:mi:ss') "
					+ "and to_date ('"+ input_date +" 23:59:59','yyyy/mm/dd hh24:mi:ss')");
		}
		
		hql.append(" order by date desc ");
		return DszhFeedbackService.getInstance().pageQueryByHql(pageIndex, pageSize, hql.toString());
		
	}

}
