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
import com.huateng.report.imports.operation.DszhQueryUpdateOperation;
import com.huateng.report.imports.operation.FjmzhQueryUpdateOperation;

import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.AmsDszhId;
import resources.east.data.pub.AmsFjmzh;
import resources.east.data.pub.AmsFjmzhId;

public class FjmzhQueryUpdateAndSave extends BaseUpdate {

	private static final Logger logger = Logger.getLogger(FjmzhQueryUpdateAndSave.class);
	
	@Override
	public UpdateReturnBean saveOrUpdate(
			MultiUpdateResultBean multiUpdateResultBean,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		// TODO Auto-generated method stub

			String nextUrl = "";

			UpdateReturnBean updateReturnBean = new UpdateReturnBean();
			UpdateResultBean updateResultBean = multiUpdateResultBean
					.getUpdateResultBeanByID("FjmzhQueryUpdate");


			List<AmsFjmzh> updateList = new ArrayList<AmsFjmzh>();
			List delList = new ArrayList();
			List insertList = new ArrayList();


			while (updateResultBean.hasNext()) {
				AmsFjmzh amsFjmzh=new AmsFjmzh();
				Map map = updateResultBean.next();
				mapToObject(amsFjmzh, map);
				switch (updateResultBean.getRecodeState()) {
				case UpdateResultBean.INSERT:
					insertList.add(amsFjmzh);
					break;				
				case UpdateResultBean.DELETE:
					delList.add(amsFjmzh);
					break;
				case UpdateResultBean.MODIFY:
//					String ind_idtype = amsFjmzh.getInd_idtype();
//					String ind_idnumber = amsFjmzh.getInd_idnumber();
//					AmsFjmzhId Id = new AmsFjmzhId();
//					Id.setInd_idtype(ind_idtype);
//					Id.setInd_idnumber(ind_idnumber);
//					
//					amsFjmzh.setId(Id);
//					
//					System.out.println(Id.getInd_idtype());
//					System.out.println(Id.getInd_idnumber());
					
					updateList.add(amsFjmzh);
					
					break;
				default:
					break;
				}

			}
			OperationContext oc = new OperationContext();
			oc.setAttribute(FjmzhQueryUpdateOperation.IN_DEL, delList);
			oc.setAttribute(FjmzhQueryUpdateOperation.IN_INSERT, insertList);
			oc.setAttribute(FjmzhQueryUpdateOperation.IN_UPDATE, updateList);
			OPCaller.call("FjmzhQueryUpdateOperation", oc);
			return updateReturnBean;


	}

}
