package com.huateng.report.imports.batch;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;

import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.DataFormat;
import com.huateng.ebank.framework.util.DateUtil;

import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;

public class StartCamsBatch {
	private static final Logger logger = Logger.getLogger(StartCamsBatch.class);

	/**
	 * 启动集中账户每日批量程序
	 * @throws CommonException
	 * @throws SQLException 
	 */
	public void doBiz() throws CommonException, SQLException {
		//获取跑批日期
		String workdate = DataFormat.dateToNumber(DateUtil.getTbsDay());
		
		System.out.println("集中账户报送数据抽取开始,数据日期"+workdate);
		ROOTDAO rootdao = ROOTDAOUtils.getROOTDAO();
		SessionFactory sf = rootdao.getSessionFactory();
		ConnectionProvider cp = ((SessionFactoryImplementor) sf).getConnectionProvider();
		Connection conn = null;
		CallableStatement callst = null;
		String proc = "{ call P01_AMS_DSZH("+ workdate +") }";
		try {
			conn = cp.getConnection();
			callst = conn.prepareCall(proc);
			callst.execute();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (callst!=null) {
				callst.close();
			}
			if (conn!=null) {
				if(cp!=null){
					cp.closeConnection(conn);
				}
			}
		}
		
	}
}
