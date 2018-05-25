package com.huateng.report.update;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.huateng.report.imports.operation.DszhQueryUpdateOperation;

import resources.east.data.pub.KXXB;
import resources.east.data.pub.LMCKXXB;

public class DszhAddKA extends BaseUpdate {
private static final Logger logger = Logger.getLogger(DszhAddUpdate.class);
	
	@Override
	public UpdateReturnBean saveOrUpdate(
			MultiUpdateResultBean multiUpdateResultBean,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		// TODO Auto-generated method stub

			String nextUrl = "";

			UpdateReturnBean updateReturnBean = new UpdateReturnBean();
			UpdateResultBean updateResultBean = multiUpdateResultBean
					.getUpdateResultBeanByID("DszhAdd");


			List<KXXB> updateList = new ArrayList<KXXB>();
			List delList = new ArrayList();
			List insertList = new ArrayList();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String createdate = sdf.format(date);
			
			while (updateResultBean.hasNext()) {
				KXXB amsDszh=new KXXB();
				Map map = updateResultBean.next();
				mapToObject(amsDszh, map);
				switch (updateResultBean.getRecodeState()) {
				case UpdateResultBean.INSERT:
					insertList.add(amsDszh);
					break;				
				case UpdateResultBean.DELETE:
					delList.add(amsDszh);
					break;
				case UpdateResultBean.MODIFY:
//					String zh = amsDszh.getZh();
//					
//					amsDszh.setZh(zh);
					
					updateList.add(amsDszh);
					
					break;
				default:
					break;
				}

			}
			OperationContext oc = new OperationContext();
			oc.setAttribute(DszhQueryUpdateOperation.IN_DEL, delList);
			oc.setAttribute(DszhQueryUpdateOperation.IN_INSERT, updateList);
			oc.setAttribute(DszhQueryUpdateOperation.IN_UPDATE, insertList);
			OPCaller.call("DszhQueryKAUpdateOperation", oc);
			return updateReturnBean;


	}
}
