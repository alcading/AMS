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

@SuppressWarnings("unchecked")
public class FjmzhQueryGRGetter extends BaseGetter{
	@Override
	public Result call() throws AppException {
		try {
			Map para=this.getCommQueryServletRequest().getParameterMap();
			String griAccID = (String)para.get("griAccID");
			if(griAccID!=null && !"".equals(griAccID)){
				griAccID = griAccID.toUpperCase();
			}
			List<AmsFjmzhGR> list = BaseDao.queryFjmzhGR(griAccID);
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
