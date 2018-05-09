package com.huateng.report.imports.updater;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.huateng.commquery.result.MultiUpdateResultBean;
import com.huateng.commquery.result.UpdateResultBean;
import com.huateng.commquery.result.UpdateReturnBean;
import com.huateng.ebank.business.common.GlobalInfo;
import com.huateng.ebank.framework.operation.OPCaller;
import com.huateng.ebank.framework.operation.OperationContext;
import com.huateng.ebank.framework.web.commQuery.BaseUpdate;
import com.huateng.exception.AppException;
import com.huateng.report.imports.common.Constants;
import com.huateng.report.imports.operation.DszhQueryUpdateOperation;
import com.huateng.report.utils.ReportUtils;

import east.creatfile.CreatFile;
import east.dao.BaseDao;
import east.utils.tools.ToolUtils;
import east.utils.tools.XmlUtil;
import east.vo.DefautValueVO;
import resource.bean.pub.Bctl;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.AmsDszhId;

public class DszhQueryImport extends BaseUpdate {

	private static final Logger logger = Logger.getLogger(DszhQueryImport.class);
	
	@Override
	public UpdateReturnBean saveOrUpdate(
			MultiUpdateResultBean multiUpdateResultBean,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		// TODO Auto-generated method stub
			String nextUrl = "";

			UpdateReturnBean updateReturnBean = new UpdateReturnBean();
			UpdateResultBean updateResultBean = multiUpdateResultBean
					.getUpdateResultBeanByID("DszhQuery");
			CreatFile creatFile = new CreatFile();
			while (updateResultBean.hasNext()) {
				AmsDszh amsDszh=new AmsDszh();
				Map map = updateResultBean.next();
				mapToObject(amsDszh, map);
				String jlrq = amsDszh.getJlrq();
				//String bctl=getUtil.getJrjgbm(jlrq);
				//String workdate =amsDszh.getJlrq();
				DefautValueVO defautValue = ToolUtils.defautValue();
				XmlUtil x = new XmlUtil();
				Map<String, List<String>> tableInfoMap;
				try {
					tableInfoMap = BaseDao.queryFieldInfo();
					Map<String, String> sqlMap = x.getSqlMap();
					String fileName=null;
					//金融机构代号
					Bctl bctl=ROOTDAOUtils.getBctlDAO().query("9999");
					//生成文件路径加年月路径
					String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR,
							Constants.PARAM_DIR_0103, "");
					String workdate = jlrq;
					filePath = filePath + File.separator + workdate.substring(0, 6) + File.separator;
					File path = new File(filePath);
					if(!path.exists()){
						path.mkdir();
					}
					String tableName="DSZH";
					
					try {
						creatFile.writeFile(tableName, workdate, sqlMap, tableInfoMap, defautValue, filePath,bctl);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//读取sql.xml中sql
				
			}
			return updateReturnBean;
	}
}

