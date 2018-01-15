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

public class DszhQueryUpdate extends BaseGetter {

	@Override
	public Result call() throws AppException {
		try {
			Map para=this.getCommQueryServletRequest().getParameterMap();
			
			String ckrsfzjzl = (String)para.get("ckrsfzjzl");
			String ckrsfzjhm = (String)para.get("ckrsfzjhm");
			
			List<AmsDszh> list = BaseDao.queryDszhUpdate(ckrsfzjzl, ckrsfzjhm);
			
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
