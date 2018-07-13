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
import resources.east.data.pub.AmsFjmzhKZR;

@SuppressWarnings("unchecked")
public class FjmzhQueryKZRGetter extends BaseGetter{
	@Override
	public Result call() throws AppException {
		try {
			Map para=this.getCommQueryServletRequest().getParameterMap();
			String kzriAccID = (String)para.get("kzriAccID");
			if(kzriAccID!=null && !"".equals(kzriAccID)){
				kzriAccID = kzriAccID.toUpperCase();
			}
			List<AmsFjmzhKZR> list = BaseDao.queryFjmzhKZR(kzriAccID);
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
