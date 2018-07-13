package east.dao;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.directwebremoting.annotations.Auth;

import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.DataFormat;
import com.huateng.ebank.framework.util.DateUtil;
import com.huateng.report.imports.common.FileImportUtil;

import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.AmsFjmzh;
import resources.east.data.pub.AmsFjmzhDZ;
import resources.east.data.pub.AmsFjmzhGR;
import resources.east.data.pub.AmsFjmzhJG;
import resources.east.data.pub.AmsFjmzhKZR;
import resources.east.data.pub.AmsFjmzhXM;
import resources.east.data.pub.KXXB;
import resources.east.data.pub.LMCKXXB;
import east.utils.tools.DBUtil;
import east.utils.tools.ToolUtils;
import east.vo.DefautValueVO;

public class BaseDao {

	/**
	 * 
	 * @param tableName
	 * @param cjrq
	 *            format:yyyyMMdd
	 * @return Map<fieldName, fieldValue>
	 */
	public static List<Map<String, String>> query(String tableName, String cjrq, Map<String, String> sqlMap)
			throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		try {
			if (sqlMap.get(tableName) == null || "".equals(sqlMap.get(tableName).trim())) {
				throw new Exception("tableName:[" + tableName + "] not exist!");
			}
			pstmt = conn.prepareStatement(sqlMap.get(tableName));
			int paramNum = pstmt.getParameterMetaData().getParameterCount();
			for (int i = 1; i < paramNum + 1; i++) {
				pstmt.setString(i, cjrq);
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {

				while (rs.next()) {
					map = new HashMap<String, String>();
					for (int i = 1; i <= count; i++) {
						map.put(rsmd.getColumnName(i), rs.getString(i));
					}
					list.add(map);
				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[" + tableName + "] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}

	public static int queryAndWriteFile(String tableName, String cjrq, Map<String, String> sqlMap, Map<String, List<String>> tableInfoMap, 
    		BufferedWriter bw, DefautValueVO defautValue) throws Exception{
    	int ret = 0;
    	int count = 0;
		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder line = null;
		String fieldName = null;
		String fieldType = null;
		String fieldLength = null;
		String specflag = null;
		String fieldLength2 = null;
		String fieldSetFlag= null;
		String fieldValue = null;
		String[] temAry = null;
		//	value	byte[] 定长,value  String非定长
		String value = null;
		try {
			if(sqlMap.get(tableName)==null || "".equals(sqlMap.get(tableName).trim())) {
				throw new Exception("tableName:["+tableName + "] not exist!");
			}
			pstmt = conn.prepareStatement(sqlMap.get(tableName));
			int paramNum = pstmt.getParameterMetaData().getParameterCount();
			for (int i = 1; i < paramNum+1; i++) {
				pstmt.setString(i, cjrq);
			}
//			String sql = "ELECT CKRXM, CKRSFZJZL,CKRSFZJHM,SFZJDQR,DQDM,CKRLB,CKRGJDQ,CKRXB,CKRYB,CKRDZ,"
//      + "CKRDH,"
//       +"DLRMC, "
//     + "DLRSFZJZL, "
//      +"DLRSFZJHM, "
//     + "DLRGJDQ, "
//     + "DLRDH, "
//     + "JRJGBM, "
//     + "ZH, "
//     + "ZHZL, "
//      +"KH, "
//     + "KDQR, "
//      +"ZHJZ, "
//     + "XKRQ, "
//     + "KZT, "
//      +"ZHLX, "
//     + "YLZHZH, "
//     + "YLZHJGBM, "
//      +"KHRQ, "
//     + "XHRQ, "
//     + "ZHZT, "
//      +"BZ, "
//      +"SFJRBZK, "
//      +"SFSHBZK, "
//      +"HSJG, "
//      +"WFHSYY, "
//      +"CZFF, "
//      +"XXLX, "
//     + "KHQD, "
//      +"REMARKS, "
//     + "RESERVE1, "
//     + "RESERVE2, "
//     + "RESERVE3, "
//     + "RESERVE4, "
//     + "RESERVE5, "
//      +"JLZT, "
//     + "JLRQ, "
//     + "ISMODIFY FROM AMS_DSZH WHERE JLRQ="+cjrq;
			rs = pstmt.executeQuery();
			//rs.getStatement();
			ResultSetMetaData rsmd = rs.getMetaData();
			count = rsmd.getColumnCount();
			List<String> fieldInfoList = tableInfoMap.get(tableName);
			if(rs!=null){
				while(rs.next()){
					ret ++;
					line = new StringBuilder();
					if(count == fieldInfoList.size())
					{
						for (int i = 0; i < count; i++) 
						{
							String fieldInfo = fieldInfoList.get(i);
							temAry = fieldInfo.split("\\|");
							//获取字段名称
							fieldName = temAry[0];
							//字段类型
							fieldType= temAry[1];
							//获取字段长度
							fieldLength = temAry[2];
							//是否特殊标志
							specflag= temAry[3];
							//特殊处理位数
							fieldLength2= temAry[4];
							//变形字段标记  '不变形-0,名称变形-1,身份证变形-2,名称暂时不取-3,身份证暂时不取-4,其他暂不取-5,其他暂时不变形-6'
							fieldSetFlag= temAry[5];
							//sql中查出对应字段值
							fieldValue = rs.getString(fieldName);
							if(fieldType.startsWith("TIMESTAMP")) {
								fieldValue = ToolUtils.formatTimestamp(fieldValue);
							}else if(fieldType.startsWith("DATE")){
								fieldValue = ToolUtils.formatDate(fieldValue);
							}
							try {
								if ((fieldName.equals("YXJGDM")||fieldName.equals("YWBLX"))&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue =defautValue.getYXJGDM();
								} else if (fieldName.equals("NBJGH")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue = defautValue.getNBJGH();
								} else if (fieldName.equals("JRXKZH")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue =defautValue.getJRXKZH();
								} else if (fieldName.equals("JBJGMC")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue = defautValue.getYXJGMC();
								}else if (fieldName.equals("YXJGMC")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue = defautValue.getYXJGMC();
								} else if (fieldName.equals("ZXJGDM")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue = defautValue.getNBJGH();
								}
								else if(fieldName.equals("FKXDM")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue = defautValue.getYXJGDM();
								}
								else if(fieldName.equals("FKXMC")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue = defautValue.getYXJGMC();
								}
								else if(fieldName.equals("JYJGMC")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue = defautValue.getYXJGMC();
								}
								else if(fieldName.equals("DJJG")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue = defautValue.getYXJGDM();
								}
								else if(fieldName.equals("CZJG")&& (null == fieldValue || "".equals(fieldValue.trim()))) {
									fieldValue = defautValue.getYXJGDM();
								}
								if(null!=fieldValue&&!"".equals(fieldValue)){
									fieldValue=fieldValue.replaceAll(",", "-");
								}
							//非定长用逗号分割
								value=ToolUtils.formatString(tableName,fieldName,fieldType, fieldValue, Integer.parseInt(fieldLength),specflag,Integer.parseInt(fieldLength2),Integer.parseInt(fieldSetFlag));
							} catch (Exception e) {
								break;
							}
							//定长 line.append(new String(value,"GBK"));
							//非定长
							line.append(value);
						}
						//非定长需要长度减1
						//line.deleteCharAt(line.length()-1);
						bw.write(line + "\n");
						bw.flush();
					}
					else
					{
						System.err.println("tableName:["+tableName + "]FIELD_INFO is wrong!");
					}
				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:["+tableName + "] query! error!" + e.getMessage(), e);
		} finally{
			DBUtil.close(conn, pstmt, rs);
		}
		return ret;
	}

	/**
	 * insert field info
	 * 
	 * @param fieldList
	 * @throws Exception
	 */
	public static void insertFieldInfo(Map<String, List<Map<String, String>>> fieldMap) throws Exception {

		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			for (String tableName : fieldMap.keySet()) {
				for (Map<String, String> fieldInfoMap : fieldMap.get(tableName)) {
					stmt.addBatch("insert into field_info values ('" + fieldInfoMap.get("TABLE_NAME") + "', "
							+ fieldInfoMap.get("SEQ") + ", '" + fieldInfoMap.get("COLUMN_NAME") + "', '"
							+ fieldInfoMap.get("COLUMN_TYPE") + "')");
				}
			}
			stmt.executeBatch();
		} catch (SQLException e) {
			throw new Exception("insert Column error!===" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, stmt, rs);
		}

	}

	public static Map<String, List<String>> queryFieldInfo() throws Exception {

		Connection conn = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> fieldList = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from field_info order by table_name, seq");
			if (rs != null) {
				while (rs.next()) {
					if (map.containsKey(rs.getString("TABLE_NAME"))) {
						fieldList = map.get(rs.getString("TABLE_NAME"));
					} else {
						fieldList = new ArrayList<String>();
					}
					fieldList.add(rs.getString("COLUMN_NAME") + "|" + rs.getString("COLUMN_TYPE") + "|"
							+ rs.getString("TOTAL") + "|" + rs.getString("SPECFLAG") + "|" + rs.getString("LENGTH2")
							+ "|" + rs.getString("SETFLAG"));
					map.put(rs.getString("TABLE_NAME"), fieldList);
				}
			}
		} catch (SQLException e) {
			throw new Exception("query Column error！===" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
		return map;

	}

	/**
	 * 根据tableName获取field_info表名
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List queryFieldInfoTable(String tableName) throws Exception {

		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		Statement stmt = null;
		ResultSet rs = null;
		List<String> tableList = new ArrayList<String>();
		String sql = "select distinct TABLE_NAME from field_info where 1=1";
		if (tableName != null && !"".equals(tableName)) {
			sql += " and TABLE_NAME like '%" + tableName + "%'";
		}
		sql += " order by TABLE_NAME";
		tableList = rootDAO.queryBySQLList(sql);
		return tableList;

	}

	public static Map<String, List<String>> queryFieldInfo2(String whereString) throws Exception {

		Connection conn = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> fieldList = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from field_info where 1=1 ";
			if (whereString != null && !"".equals(whereString)) {
				sql += " and " + whereString;
			}
			sql += "order by table_name, seq";
			rs = stmt.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					if (map.containsKey(rs.getString("TABLE_NAME"))) {
						fieldList = map.get(rs.getString("TABLE_NAME"));
					} else {
						fieldList = new ArrayList<String>();
					}
					fieldList.add(rs.getString("COLUMN_NAME") + "|" + rs.getString("COLUMN_TYPE") + "|"
							+ rs.getString("TOTAL") + "|" + rs.getString("SPECFLAG") + "|" + rs.getString("LENGTH2")
							+ "|" + rs.getString("SETFLAG"));
					map.put(rs.getString("TABLE_NAME"), fieldList);
				}
			}
		} catch (SQLException e) {
			throw new Exception("query Column error！===" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
		return map;

	}

	/**
	 * @author xuhong 20160331
	 * @Description 用于判断某报表在当前日期是否执行数据分析生成文件
	 * @param tableName
	 * @param tbsdy
	 * @return
	 * @throws CommonException
	 */
	public static Map ifCreateFile(String tableName, String tbsdy) throws CommonException {
		Map retMap = new HashMap();
		Boolean retFlag = false;
		String retType = "";
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		List<String> tableList = new ArrayList<String>();
		String sql = "select TYPE from field_table where 1=1 and TABLE_NAME='" + tableName + "' ";
		tableList = rootDAO.queryBySQLList(sql);
		// 系统日期--shaomy
		Date dt = DateUtil.stringToDate2(tbsdy);
		if (tableList == null || tableList.size() == 0) {
			System.out.println("field_table配置错误：表[" + tableName + "]未配置");
			throw new CommonException("field_table配置错误：表[" + tableName + "]未配置");
		} else {
			/*
			 * type类型 Y：按年 Q: 按季 M：按月 W：按周 D: 按日
			 */
			retType = (String) tableList.get(0);
			System.out.println("retType:" + retType);
			Calendar c = Calendar.getInstance();

			if ("Y".equals(retType)) {
				if ("1231".equals(tbsdy.substring(4))) {
					retFlag = true;
				}

			} else if ("Q".equals(retType)) {
				if ("0331".equals(tbsdy.substring(4)) || "0630".equals(tbsdy.substring(4))
						|| "0930".equals(tbsdy.substring(4)) || "1231".equals(tbsdy.substring(4))) {
					retFlag = true;
				}
			} else if ("M".equals(retType)) {
				// shaomy
				c.setTime(dt);
				c.set(Calendar.DATE, (c.get(Calendar.DATE) + 1));
				if (c.get(Calendar.DAY_OF_MONTH) == 1) {
					retFlag = true;
				}
			} else if ("W".equals(retType)) {
				// shaomy
				c.setTime(dt);
				c.set(Calendar.DATE, (c.get(Calendar.DATE)));
				if (c.get(Calendar.DAY_OF_WEEK) == 1) {
					retFlag = true;
				}
			} else if ("D".equals(retType)) {
				retFlag = true;

			} else {
				System.out.println("field_table配置错误：表[" + tableName + "]对应的类型[" + retType + "]配置错误！只能为：Y、Q、M、W、D三者之一。");
				throw new CommonException(
						"field_table配置错误：表[" + tableName + "]对应的类型[" + retType + "]配置错误！只能为：Y、Q、M、W、D三者之一。");
			}
		}
		retMap.put("retType", retType);
		retMap.put("retFlag", retFlag);
		return retMap;
	}

	/**
	 * 备份cpwj到cpwj_bak
	 */
	public static void backupCpwj() throws Exception {
		try {
			ROOTDAO rootdao = ROOTDAOUtils.getROOTDAO();
			String sql = "insert into cpwj_bak select * from cpwj where 1=1";
			rootdao.executeSql(sql);
		} catch (Exception e) {
			System.out.println("备份cpwj到cpwj_bak错误！错误信息：" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 清空cpwj或者cpwj_bak
	 */
	public static void delCpwjOrBak(String tableName) throws Exception {
		try {
			ROOTDAO rootdao = ROOTDAOUtils.getROOTDAO();
			String sql = "truncate table " + tableName + "";
			rootdao.executeSql(sql);
		} catch (Exception e) {
			System.out.println("清空表" + tableName + "错误！错误信息：" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 按起始日期、终止日期从cpwj_bak导指定时间段日期到cpwj
	 * 
	 * @param beginDate
	 * @param endDate
	 */
	public static void insertCpwj(String beginDate, String endDate) throws Exception {
		try {
			ROOTDAO rootdao = ROOTDAOUtils.getROOTDAO();
			String sql = "insert into cpwj select * from cpwj_bak where jyrq>=to_date('" + beginDate
					+ "','yyyy-mm-dd') and jyrq<=to_date('" + endDate + "','yyyy-mm-dd')";
			rootdao.executeSql(sql);
		} catch (Exception e) {
			System.out.println("还原cpwj_bak到cpwj错误！错误信息：" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 还原传票文件
	 */
	public static void revertCpwj() throws Exception {
		try {
			ROOTDAO rootdao = ROOTDAOUtils.getROOTDAO();
			String sql = "insert into cpwj select * from cpwj_bak where 1=1";
			rootdao.executeSql(sql);
		} catch (Exception e) {
			System.out.println("还原cpwj_bak到cpwj错误！错误信息：" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 查询非居民 账户基本信息 /cz update in 2018/6/7
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<AmsFjmzh> queryFjmzh(String sAccountType, String accountNumber, String sAccountHolderType, String rpStatus)
			throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmsFjmzh> list = new ArrayList<AmsFjmzh>();
		// Map<String, String> map = null;
		try {

			String sql = "select ACCOUNTNUMBER, IACCOUNTBALANCE, IPAYMENTAMNT, REPORT_STATUS, SACC_CURRCODE, SACCOUNTHOLDERTYPE"
					+ ", SACCOUNTTYPE, SCLOSEDACCOUNT, SDUEDILIGENCEIND, SINTYPE, SISSUEDBY, SOPENINGFINAME, SPAYMENTAMNTCURR"
					+ ", SPAYMENTTYPE, SRESCOUNTRYCODE, SSELFSERTIFICATION, TIN from AMS_FJMZH where 1=1";
			if (sAccountHolderType != null && !"".equals(sAccountHolderType)) {
				sql += "and SACCOUNTHOLDERTYPE = '" + sAccountHolderType + "'";
			}
			if (accountNumber != null && !"".equals(accountNumber)) {
				sql += "and ACCOUNTNUMBER = '" + accountNumber + "'";
			}
			if (rpStatus != null && !"".equals(rpStatus)) {
				sql += "and REPORT_STATUS = '" + rpStatus + "'";
			}
			if (sAccountType != null && !"".equals(sAccountType)) {
				sql += "and SACCOUNTTYPE = '" + sAccountType + "'";
			}
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {

				while (rs.next()) {
					// map = new HashMap<String, String>();
					AmsFjmzh ad = new AmsFjmzh();
					ad.setAccountNumber(rs.getString("ACCOUNTNUMBER"));
					ad.setiAccountBalance(rs.getBigDecimal("IACCOUNTBALANCE"));
					ad.setiPaymentAmnt(rs.getBigDecimal("IPAYMENTAMNT"));
					ad.setReport_status(rs.getString("REPORT_STATUS"));
					ad.setsACC_currCode(rs.getString("SACC_CURRCODE"));
					ad.setsAccountHolderType(rs.getString("SACCOUNTHOLDERTYPE"));
					ad.setsAccountType(rs.getString("SACCOUNTTYPE"));
					ad.setsClosedAccount(rs.getString("SCLOSEDACCOUNT"));
					ad.setsDueDiligenceInd(rs.getString("SDUEDILIGENCEIND"));
					ad.setSinType(rs.getString("SINTYPE"));
					ad.setsIssuedBy(rs.getString("SISSUEDBY"));
					ad.setsOpeningFIName(rs.getString("SOPENINGFINAME"));
					ad.setsPaymentAmntCurr(rs.getString("SPAYMENTAMNTCURR"));
					ad.setsPaymentType(rs.getString("SPAYMENTTYPE"));
					ad.setsResCountryCode(rs.getString("SRESCOUNTRYCODE"));
					ad.setsSelfSertification(rs.getString("SSELFSERTIFICATION"));
					ad.setTIN(rs.getString("TIN"));

					list.add(ad);

				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}
	
	/**
	 * 查询非居民 账户个人信息 /cz update in 2018/6/19
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<AmsFjmzhGR> queryFjmzhGR(String griAccID)
			throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmsFjmzhGR> list = new ArrayList<AmsFjmzhGR>();
		// Map<String, String> map = null;
		try {

			String sql = "select * from AMS_FJMZH_GR where 1=1";
			if (griAccID != null && !"".equals(griAccID)) {
				sql += "and IACCID = '" + griAccID + "'";
			}
			
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {

				while (rs.next()) {
					// map = new HashMap<String, String>();
					AmsFjmzhGR ad = new AmsFjmzhGR();
					ad.setGrBirthCity(rs.getString("SBIRTHCITY"));
					ad.setGrBirthCountryCode(rs.getString("SBIRTHCOUNTRYCODE"));
					ad.setGrBirthDate(rs.getString("DBIRTHDATE"));
					ad.setGrExplanation(rs.getString("SEXPLANATION"));
					ad.setGrFormerCountryName(rs.getString("SFORMERCOUNTRYNAME"));
					ad.setGrGender(rs.getString("SGENDER"));
					ad.setGriAccID(rs.getString("IACCID"));
					ad.setGrIDNumber(rs.getString("SIDNUMBER"));
					ad.setGrIDType(rs.getString("SIDTYPE"));
					ad.setGrIssuedBy(rs.getString("SISSUEDBY"));
					ad.setGrNationality(rs.getString("SNATIONALITY"));
					ad.setGrPhoneNo(rs.getString("SPHONENO"));
					ad.setGrsinType(rs.getString("SINTYPE"));
					ad.setGrTIN(rs.getString("TIN"));

					list.add(ad);

				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}
	
	/**
	 * 查询非居民 账户机构信息 /cz update in 2018/6/19
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<AmsFjmzhJG> queryFjmzhJG(String griAccID)
			throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmsFjmzhJG> list = new ArrayList<AmsFjmzhJG>();
		// Map<String, String> map = null;
		try {

			String sql = "select * from AMS_FJMZH_JG where 1=1";
			if (griAccID != null && !"".equals(griAccID)) {
				sql += "and IACCID = '" + griAccID + "'";
			}
			
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {

				while (rs.next()) {
					// map = new HashMap<String, String>();
					AmsFjmzhJG ad = new AmsFjmzhJG();
					ad.setJgExplanation(rs.getString("SEXPLANATION"));
					ad.setJgiAccID(rs.getString("IACCID"));
					ad.setJgIssuedBy(rs.getString("SISSUEDBY"));
					ad.setJgNameType(rs.getString("SNAMETYPE"));
					ad.setJgOrganisationNameCN(rs.getString("SORGANISATIONNAMECN"));
					ad.setJgOrganisationNameEN(rs.getString("SORGANISATIONNAMEEN"));
					ad.setJgPhoneNo(rs.getString("SPHONENO"));
					ad.setJgsinType(rs.getString("SINTYPE"));
					ad.setJgTIN(rs.getString("TIN"));

					list.add(ad);

				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}
	
	/**
	 * 查询非居民 账户控制人信息 /cz update in 2018/6/19
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<AmsFjmzhKZR> queryFjmzhKZR(String kzriAccID)
			throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmsFjmzhKZR> list = new ArrayList<AmsFjmzhKZR>();
		// Map<String, String> map = null;
		try {

			String sql = "select * from AMS_FJMZH_KZR where 1=1";
			if (kzriAccID != null && !"".equals(kzriAccID)) {
				sql += "and IACCID = '" + kzriAccID + "'";
			}
			
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {

				while (rs.next()) {
					// map = new HashMap<String, String>();
					AmsFjmzhKZR ad = new AmsFjmzhKZR();
					ad.setKzrBirthCity(rs.getString("SBIRTHCITY"));
					ad.setKzrBirthCountryCode(rs.getString("SBIRTHCOUNTRYCODE"));
					ad.setKzrCtrlgPersonType(rs.getString("SCTRLGPERSONTYPE"));
					ad.setKzrdBirthDate(rs.getString("DBIRTHDATE"));
					ad.setKzrExplanation(rs.getString("SEXPLANATION"));
					ad.setKzrFormerCountryName(rs.getString("SFORMERCOUNTRYNAME"));
					ad.setKzriAccID(rs.getString("IACCID"));
					ad.setKzrIssuedBy(rs.getString("SISSUEDBY"));
					ad.setKzrNationality(rs.getString("SNATIONALITY"));
					ad.setKzrResCountryCode(rs.getString("SRESCOUNTRYCODE"));
					ad.setKzrsinType(rs.getString("SINTYPE"));
					ad.setKzrTIN(rs.getString("TIN"));

					list.add(ad);

				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}

	/**
	 * 查询非居民 账户地址信息 /cz update in 2018/6/19
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<AmsFjmzhDZ> queryFjmzhDZ(String griAccID,String dStyle)
			throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmsFjmzhDZ> list = new ArrayList<AmsFjmzhDZ>();
		// Map<String, String> map = null;
		try {

			String sql = "select * from AMS_FJMZH_DZ where 1=1";
			if (griAccID != null && !"".equals(griAccID)) {
				sql += "and IACCID = '" + griAccID + "'";
			}
			
			if (dStyle != null && !"".equals(dStyle)) {
				sql += "and SSTYLE = '" + dStyle + "'";
			}
			
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {

				while (rs.next()) {
					// map = new HashMap<String, String>();
					AmsFjmzhDZ ad = new AmsFjmzhDZ();
					ad.setsAddressCN_sAddressFreeCN(rs.getString("ADDRESSCN_SADDRESSFREECN"));
					ad.setsAddressEN_AddressFreeEN(rs.getString("ADDRESSEN_ADDRESSFREEEN"));
					ad.setDiAccID(rs.getString("IACCID"));
					ad.setdStyle(rs.getString("SSTYLE"));
					ad.setsAddressFixCN_CityCN(rs.getString("SADDRESSFIXCN_CITYCN"));
					ad.setsAddressFixCN_DistrictName(rs.getString("SADDRESSFIXCN_DISTRICTNAME"));
					ad.setsAddressFixCN_PostCode(rs.getString("SADDRESSFIXCN_POSTCODE"));
					ad.setsAddressFixCN_Province(rs.getString("SADDRESSFIXCN_PROVINCE"));
					ad.setsAddressFixEN_BuildingIden(rs.getString("SADDRESSFIXEN_BUILDINGIDEN"));
					ad.setsAddressFixEN_CityEN(rs.getString("SADDRESSFIXEN_CITYEN"));
					ad.setsAddressFixEN_CountrySubentity(rs.getString("SADDRESSFIXEN_COUNTRYSUBENTITY"));
					ad.setsAddressFixEN_DistrictName(rs.getString("SADDRESSFIXEN_DISTRICTNAME"));
					ad.setsAddressFixEN_FloorIdentifier(rs.getString("SADDRESSFIXEN_FLOORIDENTIFIER"));
					ad.setsAddressFixEN_POB(rs.getString("SADDRESSFIXEN_POB"));
					ad.setsAddressFixEN_PostCode(rs.getString("SADDRESSFIXEN_POSTCODE"));
					ad.setsAddressFixEN_Street(rs.getString("SADDRESSFIXEN_STREET"));
					ad.setsAddressFixEN_SuiteIdentifier(rs.getString("SADDRESSFIXEN_SUITEIDENTIFIER"));
					ad.setsCountryCode(rs.getString("SCOUNTRYCODE"));
					ad.setSlegalAddressType(rs.getString("SLEGALADDRESSTYPE"));

					list.add(ad);

				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}

	/**
	 * 查询非居民 账户姓名信息 /cz update in 2018/6/19
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<AmsFjmzhXM> queryFjmzhXM(String griAccID,String xStyle)
			throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmsFjmzhXM> list = new ArrayList<AmsFjmzhXM>();
		// Map<String, String> map = null;
		try {

			String sql = "select * from AMS_FJMZH_XM where 1=1";
			if (griAccID != null && !"".equals(griAccID)) {
				sql += "and IACCID = '" + griAccID + "'";
			}
			if (xStyle != null && !"".equals(xStyle)) {
				sql += "and SSTYLE = '" + xStyle + "'";
			}
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {

				while (rs.next()) {
					// map = new HashMap<String, String>();
					AmsFjmzhXM ad = new AmsFjmzhXM();
					ad.setxFirstName(rs.getString("SFIRSTNAME"));
					ad.setxGeneralSuffix(rs.getString("SGENERALSUFFIX"));
					ad.setxGenerationIdentifier(rs.getString("SGENERATIONIDENTIFIER"));
					ad.setXiAccID(rs.getString("IACCID"));
					ad.setxLastName(rs.getString("SLASTNAME"));
					ad.setxMiddleName(rs.getString("SMIDDLENAME"));
					ad.setxNameCN(rs.getString("SNAMECN"));
					ad.setxNamePrefix(rs.getString("SNAMEPREFIX"));
					ad.setxNameType(rs.getString("SNAMETYPE"));
					ad.setxPrecedingTitle(rs.getString("SPRECEDINGTITLE"));
					ad.setxStyle(rs.getString("SSTYLE"));
					ad.setxSuffix(rs.getString("SSUFFIX"));
					ad.setxTitle(rs.getString("STITLE"));

					list.add(ad);

				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}


	/**
	 * 查询对私账户信息 /cx add in 2018/1/5
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	public static List<AmsDszh> queryDszh(String ckrxm, String ckrsfzjhm, String zh, String xxlx, String jlrq)
			throws Exception {
		
		if(jlrq == null) {
			return null;
		}
		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmsDszh> list = new ArrayList<AmsDszh>();
		// Map<String, String> map = null;
		try {
			String sql = "select XXLX, CKRXM, CKRSFZJZL, CKRSFZJHM, JRJGBM, ZH, ZHZL, BZ, ZHZT, JLRQ, ISMODIFY, REPORT_STATUS from AMS_DSZH where 1=1";
			if (ckrxm != null && !"".equals(ckrxm)) {
				sql += "and ckrxm like '%" + ckrxm + "%'";
			}
			if (ckrsfzjhm != null && !"".equals(ckrsfzjhm)) {
				sql += "and ckrsfzjhm='" + ckrsfzjhm + "'";
			}
			if (zh != null && !"".equals(zh)) {
				sql += "and zh = '" + zh + "'";
			}
			if (xxlx != null && !"".equals(xxlx)) {
				sql += "and xxlx = '" + xxlx + "'";
			}
			if (jlrq != null && !"".equals(jlrq)) {
				jlrq = FileImportUtil.getWorkDate(jlrq);
				sql += "and jlrq='" + jlrq + "'";
			}
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			
			
			
			if (rs != null) {
				
				while (rs.next()) {
					// map = new HashMap<String, String>();
					AmsDszh ad = new AmsDszh();
					ad.setXxlx(rs.getString("XXLX"));
					ad.setCkrxm(rs.getString("CKRXM"));
					ad.setCkrsfzjzl(rs.getString("CKRSFZJZL"));
					ad.setCkrsfzjhm(rs.getString("CKRSFZJHM"));
					ad.setJrjgbm(rs.getString("JRJGBM"));
					ad.setZh(rs.getString("ZH"));
					ad.setZhzl(rs.getString("ZHZL"));
					ad.setBz(rs.getString("BZ"));
					ad.setZhzt(rs.getString("ZHZT"));
					ad.setJlrq(rs.getString("JLRQ"));
					ad.setIsmodify(rs.getString("ISMODIFY"));
					ad.setReport_status(rs.getString("REPORT_STATUS"));
					list.add(ad);
				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	*/
	/**
	 * 对私账户查询修改/ cx add in 2018/1/5
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	public static List<AmsDszh> queryDszhUpdate(String zh) throws Exception {
		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmsDszh> list = new ArrayList<AmsDszh>();
		// Map<String, String> map = null;
		try {
			String sql = "select * from AMS_DSZH where 1=1"; 
			//String sql = "select * from AMS_DSZH t1 left join AMS_LMCKXXB t2  on t1.zh=t2.zh left join AMS_KXXB t3  on t1.zh=t3.zh where 1=1"; 
			if (zh != null && !"".equals(zh)) {
				sql += "and zh='" + zh + "'";
			}
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			
			if (rs != null) {
				while (rs.next()) {
					// map = new HashMap<String, String>();
					AmsDszh ad = new AmsDszh();
					ad.setCkrxm(rs.getString("CKRXM"));
					ad.setCkrsfzjzl(rs.getString("CKRSFZJZL"));
					ad.setCkrsfzjhm(rs.getString("CKRSFZJHM"));
					ad.setSfzjdqr(rs.getString("SFZJDQR"));
					ad.setDqdm(rs.getString("DQDM"));
					ad.setCkrlb(rs.getString("CKRLB"));
					ad.setCkrgjdq(rs.getString("CKRGJDQ"));
					ad.setCkrxb(rs.getString("CKRXB"));
					ad.setCkryb(rs.getString("CKRYB"));
					ad.setCkrdz(rs.getString("CKRDZ"));
					ad.setCkrdh(rs.getString("CKRDH"));
					ad.setDlrmc(rs.getString("DLRMC"));
					ad.setDlrsfzjzl(rs.getString("DLRSFZJZL"));
					ad.setDlrsfzjhm(rs.getString("DLRSFZJHM"));
					ad.setDlrgjdq(rs.getString("DLRGJDQ"));
					ad.setDlrdh(rs.getString("DLRDH"));
					ad.setJrjgbm(rs.getString("JRJGBM"));
					ad.setZh(rs.getString("ZH"));
					ad.setZhzl(rs.getString("ZHZL"));
					//ad.setKh(rs.getString("KH"));
					//ad.setKdqr(rs.getString("KDQR"));
					//ad.setZhjz(rs.getString("ZHJZ"));
					//ad.setXkrq(rs.getString("XKRQ"));
					//ad.setKzt(rs.getString("KZT"));
					ad.setZhlx(rs.getString("ZHLX"));
					ad.setYlzhzh(rs.getString("YLZHZH"));
					ad.setYlzhjgbm(rs.getString("YLZHJGBM"));
					ad.setKhrq(rs.getString("KHRQ"));
					ad.setXhrq(rs.getString("XHRQ"));
					ad.setZhzt(rs.getString("ZHZT"));
					ad.setBz(rs.getString("BZ"));
					ad.setSfjrbzk(rs.getString("SFJRBZK"));
					ad.setSfshbzk(rs.getString("SFSHBZK"));
					ad.setHsjg(rs.getString("HSJG"));
					ad.setWfhsyy(rs.getString("WFHSYY"));
					ad.setCzff(rs.getString("CZFF"));
					ad.setXxlx(rs.getString("XXLX"));
					ad.setKhqd(rs.getString("KHQD"));
					ad.setRemarks(rs.getString("REMARKS"));
					ad.setJlzt(rs.getString("JLZT"));
					ad.setJlrq(rs.getString("JLRQ"));
					ad.setIsmodify(rs.getString("ISMODIFY"));
                    ad.setSflmzh(rs.getString("SFLMZH"));
                    ad.setFgmjyqd(rs.getString("FGMJYQD"));
                    ad.setKhdqdm(rs.getString("KHDQDM"));
                    ad.setReport_status(rs.getString("REPORT_STATUS"));
                    ad.setJgdm(rs.getString("JGDM"));
					list.add(ad);
				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	*/
	/**
	 *对私联名账户信息查询
	 * modify by 2018-03-15
	 * 
	 */
	
	public static List<LMCKXXB> queryDszhLMUpdate(String zh, String ckrsfzjhm) throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LMCKXXB> list = new ArrayList<LMCKXXB>();
		// Map<String, String> map = null;
		try {
			String sql = "select * from AMS_LMCKXXB where 1=1";
			if (zh != null && !"".equals(zh)) {
				sql += "and zh='" + zh + "'";
			}
			if (ckrsfzjhm != null && !"".equals(ckrsfzjhm)) {
				sql += "and ckrsfzjhm='" + ckrsfzjhm + "'";
			}
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {

				while (rs.next()) {
					// map = new HashMap<String, String>();
					LMCKXXB ad = new LMCKXXB();
					ad.setCkrxm(rs.getString("CKRXM"));
					ad.setCkrsfzjzl(rs.getString("CKRSFZJZL"));
					ad.setCkrsfzjhm(rs.getString("CKRSFZJHM"));
					ad.setSfzjdqr(rs.getString("SFZJDQR"));
					ad.setDqdm(rs.getString("DQDM"));
					ad.setCkrlb(rs.getString("CKRLB"));
					ad.setCkrgjdq(rs.getString("CKRGJDQ"));
					ad.setCkrxb(rs.getString("CKRXB"));
     				ad.setZh(rs.getString("ZH"));
					list.add(ad);
				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}

    /**
     * 卡信息
     * */
	public static List<KXXB> queryDszhKAUpdate(String zh) throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<KXXB> list = new ArrayList<KXXB>();
		// Map<String, String> map = null;
		try {
			String sql = "select * from AMS_KXXB where 1=1";
			if (zh != null && !"".equals(zh)) {
				sql += "and zh='" + zh + "'";
			}
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {
				
				while (rs.next()) {
					// map = new HashMap<String, String>();
					KXXB ad = new KXXB();
					ad.setZh(rs.getString("ZH"));
					ad.setKh(rs.getString("KH"));
					ad.setKdqr(rs.getString("KDQR"));
					ad.setZhjz(rs.getString("ZHJZ"));
					ad.setXkrq(rs.getString("XKRQ"));
					ad.setKzt(rs.getString("KZT"));
					list.add(ad);
				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}
	/**
	 * 非居民账户查询修改/ cx add in 2018/1/25
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<AmsFjmzh> queryFjmzhUpdate(String accountnumber) throws Exception {

		Connection conn = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmsFjmzh> list = new ArrayList<AmsFjmzh>();
		// Map<String, String> map = null;
		try {
			String sql = "select * from AMS_FJMZH where 1=1";
			if (accountnumber != null && !"".equals(accountnumber)) {
				sql += "and accountnumber='" + accountnumber + "'";
			}
			pstmt = conn.prepareStatement(sql);
			int paramNum = pstmt.getParameterMetaData().getParameterCount();

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (rs != null) {

				while (rs.next()) {
					// map = new HashMap<String, String>();
					AmsFjmzh ad = new AmsFjmzh();
					ad.setAccountNumber(rs.getString("ACCOUNTNUMBER"));
					ad.setiAccountBalance(rs.getBigDecimal("IACCOUNTBALANCE"));
					ad.setiPaymentAmnt(rs.getBigDecimal("IPAYMENTAMNT"));
					ad.setReport_status(rs.getString("REPORT_STATUS"));
					ad.setsACC_currCode(rs.getString("SACC_CURRCODE"));
					ad.setsAccountHolderType(rs.getString("SACCOUNTHOLDERTYPE"));
					ad.setsAccountType(rs.getString("SACCOUNTTYPE"));
					ad.setsClosedAccount(rs.getString("SCLOSEDACCOUNT"));
					ad.setsDueDiligenceInd(rs.getString("SDUEDILIGENCEIND"));
					ad.setSinType(rs.getString("SINTYPE"));
					ad.setsIssuedBy(rs.getString("SISSUEDBY"));
					ad.setsOpeningFIName(rs.getString("SOPENINGFINAME"));
					ad.setsPaymentAmntCurr(rs.getString("SPAYMENTAMNTCURR"));
					ad.setsPaymentType(rs.getString("SPAYMENTTYPE"));
					ad.setsResCountryCode(rs.getString("SRESCOUNTRYCODE"));
					ad.setsSelfSertification(rs.getString("SSELFSERTIFICATION"));
					ad.setTIN(rs.getString("TIN"));

					list.add(ad);
				}
			}
		} catch (SQLException e) {
			throw new Exception("tableName:[] query! error!" + e.getMessage(), e);
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;

	}

}