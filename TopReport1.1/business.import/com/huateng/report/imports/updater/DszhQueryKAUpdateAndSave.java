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
import com.huateng.report.imports.operation.DszhQueryKAUpdateOperation;
import com.huateng.report.imports.operation.DszhQueryLMUpdateOperation;
import com.huateng.report.imports.operation.DszhQueryUpdateOperation;

import resources.east.data.pub.KXXB;
import resources.east.data.pub.LMCKXXB;
import resources.east.data.pub.LMCKXXB;

public class DszhQueryKAUpdateAndSave extends BaseUpdate {

	private static final Logger logger = Logger.getLogger(DszhQueryKAUpdateAndSave.class);
	
	@Override
	public UpdateReturnBean saveOrUpdate(
			MultiUpdateResultBean multiUpdateResultBean,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		// TODO Auto-generated method stub

			String nextUrl = "";

			UpdateReturnBean updateReturnBean = new UpdateReturnBean();
			UpdateResultBean updateResultBean = multiUpdateResultBean
					.getUpdateResultBeanByID("DszhQueryKAUpdate");


			List<KXXB> updateList = new ArrayList<KXXB>();
			List delList = new ArrayList();
			List insertList = new ArrayList();


			while (updateResultBean.hasNext()) {
				KXXB lmckxxb=new KXXB();
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
			oc.setAttribute(DszhQueryKAUpdateOperation.IN_DEL, delList);
			oc.setAttribute(DszhQueryKAUpdateOperation.IN_INSERT, insertList);
			oc.setAttribute(DszhQueryKAUpdateOperation.IN_UPDATE, updateList);
			OPCaller.call("DszhQueryKAUpdateOperation", oc);
			return updateReturnBean;


	}

}
