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
import com.huateng.report.imports.operation.FjmzhQueryKZRUpdateOperation;

import resources.east.data.pub.AmsFjmzhKZR;

public class FjmzhUpdateKZR extends BaseUpdate {

	private static final Logger logger = Logger.getLogger(FjmzhUpdateKZR.class);
	
	@Override
	public UpdateReturnBean saveOrUpdate(
			MultiUpdateResultBean multiUpdateResultBean,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		// TODO Auto-generated method stub

			String nextUrl = "";

			UpdateReturnBean updateReturnBean = new UpdateReturnBean();
			UpdateResultBean updateResultBean = multiUpdateResultBean
					.getUpdateResultBeanByID("FjmzhQueryKZR");
			
			List<AmsFjmzhKZR> updateList = new ArrayList<AmsFjmzhKZR>();
			List delList = new ArrayList();
			List insertList = new ArrayList();


			while (updateResultBean.hasNext()) {
				AmsFjmzhKZR amsFjmzh=new AmsFjmzhKZR();
				Map map = updateResultBean.next();
				mapToObject(amsFjmzh, map);
				updateList.add(amsFjmzh);

			}
			OperationContext oc = new OperationContext();
			oc.setAttribute(FjmzhQueryKZRUpdateOperation.IN_DEL, delList);
			oc.setAttribute(FjmzhQueryKZRUpdateOperation.IN_INSERT, insertList);
			oc.setAttribute(FjmzhQueryKZRUpdateOperation.IN_UPDATE, updateList);
			OPCaller.call("FjmzhQueryKZRUpdateOperation", oc);
			return updateReturnBean;


	}

}
