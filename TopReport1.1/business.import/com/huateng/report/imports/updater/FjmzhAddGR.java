package com.huateng.report.imports.updater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huateng.commquery.result.MultiUpdateResultBean;
import com.huateng.commquery.result.UpdateResultBean;
import com.huateng.commquery.result.UpdateReturnBean;
import com.huateng.ebank.framework.operation.OPCaller;
import com.huateng.ebank.framework.operation.OperationContext;
import com.huateng.ebank.framework.web.commQuery.BaseUpdate;
import com.huateng.exception.AppException;
import com.huateng.report.imports.operation.FjmzhQueryGRUpdateOperation;
import com.huateng.report.imports.operation.FjmzhQueryUpdateOperation;

import resources.east.data.pub.AmsFjmzhGR;

public class FjmzhAddGR extends BaseUpdate {

	private static final Logger logger = Logger.getLogger(FjmzhAddGR.class);
	
	@Override
	public UpdateReturnBean saveOrUpdate(
			MultiUpdateResultBean multiUpdateResultBean,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		// TODO Auto-generated method stub

			String nextUrl = "";

			UpdateReturnBean updateReturnBean = new UpdateReturnBean();
			UpdateResultBean updateResultBean = multiUpdateResultBean
					.getUpdateResultBeanByID("FjmzhAdd");
			
			List<AmsFjmzhGR> insertList = new ArrayList<AmsFjmzhGR>();
			List delList = new ArrayList();
			List updateList = new ArrayList();


			while (updateResultBean.hasNext()) {
				AmsFjmzhGR amsFjmzh=new AmsFjmzhGR();
				Map map = updateResultBean.next();
				mapToObject(amsFjmzh, map);
				insertList.add(amsFjmzh);

			}
			OperationContext oc = new OperationContext();
			oc.setAttribute(FjmzhQueryGRUpdateOperation.IN_DEL, delList);
			oc.setAttribute(FjmzhQueryGRUpdateOperation.IN_INSERT, insertList);
			oc.setAttribute(FjmzhQueryGRUpdateOperation.IN_UPDATE, updateList);
			OPCaller.call("FjmzhQueryGRUpdateOperation", oc);
			return updateReturnBean;


	}

}
