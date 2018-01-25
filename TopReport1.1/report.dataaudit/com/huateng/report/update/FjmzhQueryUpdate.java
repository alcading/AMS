package com.huateng.report.update;

import java.util.List;
import java.util.Map;

import com.huateng.common.err.Module;
import com.huateng.common.err.Rescode;
import com.huateng.commquery.result.Result;
import com.huateng.commquery.result.ResultMng;
import com.huateng.ebank.framework.web.commQuery.BaseGetter;
import com.huateng.exception.AppException;

import east.dao.BaseDao;
import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.AmsFjmzh;

public class FjmzhQueryUpdate extends BaseGetter {

	@Override
	public Result call() throws AppException {
		try {
			Map para=this.getCommQueryServletRequest().getParameterMap();
			
			String ind_idtype = (String)para.get("ind_idtype");
			String ind_idnumber = (String)para.get("ind_idnumber");
			
			List<AmsFjmzh> list = BaseDao.queryFjmzhUpdate(ind_idtype, ind_idnumber);
			
			System.out.println(list);
			
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
