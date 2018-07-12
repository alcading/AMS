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

import resources.east.data.pub.KXXB;

public class DszhQueryKAAdd extends BaseUpdate {
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
						.getUpdateResultBeanByID("DszhQueryKA");
			
			

			List<KXXB> updateList = new ArrayList<KXXB>();
			List delList = new ArrayList();
			List insertList = new ArrayList();


			while (updateResultBean.hasNext()) {
				KXXB kxxb=new KXXB();
				Map map = updateResultBean.next();
				mapToObject(kxxb, map);
				switch (updateResultBean.getRecodeState()) {
				case UpdateResultBean.INSERT:
					
					insertList.add(kxxb);
					break;				
				case UpdateResultBean.DELETE:
					delList.add(kxxb);
					break;
				case UpdateResultBean.MODIFY:
//					String zh = lmckxxb.getZh();
//					
//					lmckxxb.setZh(zh);
					
					updateList.add(kxxb);
					
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
