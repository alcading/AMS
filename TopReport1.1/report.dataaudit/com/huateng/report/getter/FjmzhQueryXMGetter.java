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
import resources.east.data.pub.AmsFjmzhXM;

@SuppressWarnings("unchecked")
public class FjmzhQueryXMGetter extends BaseGetter{
	@Override
	public Result call() throws AppException {
		try {
			Map para=this.getCommQueryServletRequest().getParameterMap();
			String xiAccID = (String)para.get("xiAccID");
			String xStyle = (String)para.get("xStyle");
			if(xiAccID!=null && !"".equals(xiAccID)){
				xiAccID = xiAccID.toUpperCase();
			}
			if(xStyle!=null && !"".equals(xStyle)){
				xStyle = xStyle.toUpperCase();
			}
			List<AmsFjmzhXM> list = BaseDao.queryFjmzhXM(xiAccID,xStyle);
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
