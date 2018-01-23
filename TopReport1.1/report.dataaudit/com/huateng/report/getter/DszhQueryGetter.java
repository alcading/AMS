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

@SuppressWarnings("unchecked")
public class DszhQueryGetter extends BaseGetter{
	@Override
	public Result call() throws AppException {
		try {
			Map para=this.getCommQueryServletRequest().getParameterMap();
			String ckrxm = (String)para.get("ckrxm");
			String ckrsfzjhm = (String)para.get("ckrsfzjhm");
			String zh = (String)para.get("zh");
			String xxlx = (String)para.get("xxlx");
			String jlrq = (String)para.get("jlrq");
			if(ckrxm!=null && !"".equals(ckrxm)){
				ckrxm = ckrxm.toUpperCase();
			}
			if(ckrsfzjhm!=null && !"".equals(ckrsfzjhm)){
				ckrsfzjhm = ckrsfzjhm.toUpperCase();
			}
			if(zh!=null && !"".equals(zh)){
				zh = zh.toUpperCase();
			}
			if(xxlx!=null && !"".equals(xxlx)){
				xxlx = xxlx.toUpperCase();
			}
			if(jlrq!=null && !"".equals(jlrq)){
				jlrq = jlrq.toUpperCase();
			}
			
			List<AmsDszh> list = BaseDao.queryDszh(ckrxm, ckrsfzjhm, zh, xxlx, jlrq);
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
