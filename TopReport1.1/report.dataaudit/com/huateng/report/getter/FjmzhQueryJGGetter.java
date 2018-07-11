package com.huateng.report.getter;

import java.util.List;
import java.util.Map;

import com.huateng.common.err.Module;
import com.huateng.common.err.Rescode;
import com.huateng.commquery.result.Result;
import com.huateng.commquery.result.ResultMng;
import com.huateng.ebank.framework.web.commQuery.BaseGetter;
import com.huateng.exception.AppException;

import east.dao.BaseDao;
import resources.east.data.pub.AmsFjmzhGR;
import resources.east.data.pub.AmsFjmzhJG;

@SuppressWarnings("unchecked")
public class FjmzhQueryJGGetter extends BaseGetter{
	@Override
	public Result call() throws AppException {
		try {
			Map para=this.getCommQueryServletRequest().getParameterMap();
			String jgiAccID = (String)para.get("jgiAccID");
			if(jgiAccID!=null && !"".equals(jgiAccID)){
				jgiAccID = jgiAccID.toUpperCase();
			}
			List<AmsFjmzhJG> list = BaseDao.queryFjmzhJG(jgiAccID);
			ResultMng.fillResultByList(getCommonQueryBean(),
					getCommQueryServletRequest(), list, getResult());
			result.setContent(list);
			result.getPage().setTotalPage(1);
			result.init();
			return result;
		} catch (AppException appEx) {
			throw appEx;
		} catch (Exception ex) {
			throw new AppException(Module.SYSTEM_MODULE,
					Rescode.DEFAULT_RESCODE, ex.getMessage(), ex);
		}
	}	
	
}
