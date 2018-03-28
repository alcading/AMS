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
import com.huateng.report.imports.operation.DszhQueryLMUpdateOperation;
import com.huateng.report.imports.operation.DszhQueryUpdateOperation;

import resources.east.data.pub.LMCKXXB;
import resources.east.data.pub.LMCKXXB;

public class DszhQueryLMUpdateAndSave extends BaseUpdate {

	private static final Logger logger = Logger.getLogger(DszhQueryLMUpdateAndSave.class);
	
	@Override
	public UpdateReturnBean saveOrUpdate(
			MultiUpdateResultBean multiUpdateResultBean,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		// TODO Auto-generated method stub

			String nextUrl = "";

			UpdateReturnBean updateReturnBean = new UpdateReturnBean();
			UpdateResultBean updateResultBean = multiUpdateResultBean
					.getUpdateResultBeanByID("DszhQueryLMUpdate");


			List<LMCKXXB> updateList = new ArrayList<LMCKXXB>();
			List delList = new ArrayList();
			List insertList = new ArrayList();


			while (updateResultBean.hasNext()) {
				LMCKXXB lmckxxb=new LMCKXXB();
				Map map = updateResultBean.next();
				mapToObject(lmckxxb, map);
				switch (updateResultBean.getRecodeState()) {
				case UpdateResultBean.INSERT:
					insertList.add(lmckxxb);
					break;				
				case UpdateResultBean.DELETE:
					delList.add(lmckxxb);
					break;
				case UpdateResultBean.MODIFY:
					String zh = lmckxxb.getZh();
					
					lmckxxb.setZh(zh);
					
					updateList.add(lmckxxb);
					
					break;
				default:
					break;
				}

			}
			OperationContext oc = new OperationContext();
			oc.setAttribute(DszhQueryLMUpdateOperation.IN_DEL, delList);
			oc.setAttribute(DszhQueryLMUpdateOperation.IN_INSERT, insertList);
			oc.setAttribute(DszhQueryLMUpdateOperation.IN_UPDATE, updateList);
			OPCaller.call("DszhQueryLMUpdateOperation", oc);
			return updateReturnBean;


	}

}
