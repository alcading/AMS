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
import resources.east.data.pub.AmsFjmzh;

@SuppressWarnings("unchecked")
public class FjmzhQueryGetter extends BaseGetter{
	@Override
	public Result call() throws AppException {
		try {
			Map para=this.getCommQueryServletRequest().getParameterMap();
			String sAccountType = (String)para.get("sAccountType");
			String rpStatus = (String)para.get("report_status");
			String accountNumber = (String)para.get("accountNumber");
			String sAccountHolderType = (String)para.get("sAccountHolderType");
			if(sAccountType!=null && !"".equals(sAccountType)){
				sAccountType = sAccountType.toUpperCase();
			}
			if(rpStatus!=null && !"".equals(rpStatus)){
				rpStatus = rpStatus.toUpperCase();
			}
			if(accountNumber!=null && !"".equals(accountNumber)){
				accountNumber = accountNumber.toUpperCase();
			}
			if(sAccountHolderType!=null && !"".equals(sAccountHolderType)){
				sAccountHolderType = sAccountHolderType.toUpperCase();
			}
			List<AmsFjmzh> list = BaseDao.queryFjmzh(sAccountType,accountNumber,sAccountHolderType,rpStatus);
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
