package com.huateng.report.getter;

import java.util.ArrayList;
import java.util.HashMap;
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

@SuppressWarnings("unchecked")
public class FjmzhQueryGetter extends BaseGetter{
	@Override
	public Result call() throws AppException {
		try {
			Map para=this.getCommQueryServletRequest().getParameterMap();
			String ind_name = (String)para.get("ind_name");
			String accountnumber = (String)para.get("accountnumber");
			String ind_idtype = (String)para.get("ind_idtype");
			String ind_idnumber = (String)para.get("ind_idnumber");
			if(ind_name!=null && !"".equals(ind_name)){
				ind_name = ind_name.toUpperCase();
			}
			if(accountnumber!=null && !"".equals(accountnumber)){
				accountnumber = accountnumber.toUpperCase();
			}
			if(ind_idtype!=null && !"".equals(ind_idtype)){
				ind_idtype = ind_idtype.toUpperCase();
			}
			if(ind_idnumber!=null && !"".equals(ind_idnumber)){
				ind_idnumber = ind_idnumber.toUpperCase();
			}			
			List<AmsFjmzh> list = BaseDao.queryFjmzh(ind_name,accountnumber,ind_idtype,ind_idnumber);
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
