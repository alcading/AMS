package com.huateng.report.imports.updater;

import java.io.IOException;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resource.dao.base.HQLDAO;
import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.AmsDszhMessageInfo;
import resources.east.data.pub.KXXB;
import resources.east.data.pub.LMCKXXB;

import org.hibernate.Session;
import com.huateng.ebank.business.management.common.DAOUtils;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.ReportUtils;


/**
 * Servlet implementation class DszhQueryOutput
 */
public class DszhQueryOutput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Session session = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DszhQueryOutput() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jlrq=request.getParameter("jlrq");
		
		HQLDAO dao = DAOUtils.getHQLDAO();
		session = dao.getHibernateTemplate().getSessionFactory().openSession();
		
		//先判断是否有可导出数据
		List report_status_count = session.createSQLQuery("select count(*) from ams_dszh where REPORT_STATUS='0' or REPORT_STATUS='3' ").list();
		if(report_status_count.get(0).toString().equals("0")){
			System.out.println("没有可导出数据");
			session.close();
			try {
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("没有可导出的数据!");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		
		/*
		 * 导出文件名中的金融机构编码为总行的金融机构编码
		 */
		List Bctl_list = null;
		String headOfficeJrjgbm = null;
		try {
			Bctl_list = rootDAO.queryByQL2List("select A.brno, A.jrjgbm from BrnoJbcdLink A, Bctl B where A.brno = B.brno and B.brclass='1'");
			for(int i = 0; i < Bctl_list.size(); i ++) {
				 Object[] object = (Object[])Bctl_list.get(i);
				 headOfficeJrjgbm = (String)object[1];
			}
		} catch (CommonException e2) {
			e2.printStackTrace();
		}
		
		BufferedWriter bw=null;
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date(currentTime);
		String workDate = formatter.format(date);
		String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0105, "");
		filePath = filePath + File.separator + jlrq + File.separator;
		String filename = null;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		
		
		
		List<AmsDszh> list = session.createQuery(" from AmsDszh model where model.report_status='0' or model.report_status='3' ").list();
		
		StringBuilder bf=new StringBuilder();
		bf.append(
				"存款人姓名(存款人子信息)|存款人身份证件种类(存款人子信息)|存款人身份证件号码(存款人子信息)|身份证件到期日(存款人子信息)|发证机关所在地的地区代码(存款人子信息)|存款人类别(存款人子信息)|存款人国籍(存款人子信息)|存款人性别(存款人子信息)|存款人邮编|存款人地址|存款人电话|代理人名称|代理人身份证件种类|代理人身份证件号码|代理人国籍|代理人电话|开户银行金融机构编码（不可变更）|账号（不可变更）|账户种类|介质号（介质子信息）|介质到期日（介质子信息）|账户介质（介质子信息）|介质注销日期(介质子信息)|介质状态（介质子信息）|账户类型|II、III类户绑定账户账号(绑定账户子信息)|II、III类户绑定账户开户银行金融机构编码(绑定账户子信息)|开户日期|销户日期(不可变)|账户状态|币种|是否为军人保障卡|是否为社会保障卡|核实结果|无法核实原因|处置方法|信息类型|开户渠道|备注|开通的非柜面交易渠道|是否为\"联名账户\"|开户地地区代码|预留字段4|预留字段5");
		bf.append("\r\n");
		
		/*
		 *定义KXXB中和LMCKXXB中字段对应的StringBuilder，如果有多条记录就以分号分割然后连接起来，再与dszh中的单条记录进行拼接 
		 */
		//KXXB
		StringBuilder kxxb_kh = new StringBuilder();
		StringBuilder kxxb_kdqr = new StringBuilder();
		StringBuilder kxxb_zhjz = new StringBuilder();
		StringBuilder kxxb_xkrq = new StringBuilder();
		StringBuilder kxxb_kzt = new StringBuilder();
		
		//LMCKXXB
		StringBuilder lmckxxb_ckrxm = new StringBuilder();
		StringBuilder lmckxxb_ckrsfzjzl = new StringBuilder();
		StringBuilder lmckxxb_ckrsfzjhm = new StringBuilder();
		StringBuilder lmckxxb_sfzjdqr = new StringBuilder();
		StringBuilder lmckxxb_dqdm = new StringBuilder();
		StringBuilder lmckxxb_ckrlb = new StringBuilder();
		StringBuilder lmckxxb_ckrgjdq = new StringBuilder();
		StringBuilder lmckxxb_ckrxb = new StringBuilder();
		
		KXXB kxxb = null;
		LMCKXXB lmckxxb = null;
		Iterator iterator = null;
		
		
		int min = 10000;
		int max = 99999;
		filename = "[" + headOfficeJrjgbm + "]" + "[cams00100101]" + "[" + workDate
				+ (new Random().nextInt(max) % (max - min + 1) + min) + "]";
		filePath = filePath + filename;
		File txtFile = new File(filePath + ".txt");
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "GB18030"));
		
		
		for(AmsDszh amsDszh:list) {
			
			/*
			 * 将对象中的null全部转换为""
			 */
			try {
				nulltoNothing(amsDszh);
				for(KXXB kk:amsDszh.getKxxb()) {
					nulltoNothingkk(kk);
				}
				
				for(LMCKXXB lmck:amsDszh.getLmckxxb()) {
					nulltoNothinglmck(lmck);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			jlrq = amsDszh.getJlrq();
//			String report_status = amsDszh.getReport_status();
//			if(!report_status.equals("0")&&(!report_status.equals("3"))){
//				continue;
//			}
			try {
				iterator = amsDszh.getKxxb().iterator();
				while(iterator.hasNext()) {
					
					kxxb = (KXXB) iterator.next();
					kxxb_kh.append(kxxb.getKh() + ";");
					kxxb_kdqr.append(kxxb.getKdqr() + ";");
					kxxb_zhjz.append(kxxb.getZhjz() + ";");
					kxxb_xkrq.append(kxxb.getXkrq() + ";");
					kxxb_kzt.append(kxxb.getKzt() + ";");
				}
				//移除掉最后一个分号
				if(kxxb_kh.length() != 0) {
					kxxb_kh.deleteCharAt(kxxb_kh.length() - 1);
				}
				if(kxxb_kdqr.length() != 0) {
					kxxb_kdqr.deleteCharAt(kxxb_kdqr.length() - 1);
				}
				if(kxxb_zhjz.length() != 0) {
					kxxb_zhjz.deleteCharAt(kxxb_zhjz.length() - 1);
				}
				if(kxxb_xkrq.length() != 0) {
					kxxb_xkrq.deleteCharAt(kxxb_xkrq.length() - 1);
				}
				if(kxxb_kzt.length() != 0) {
					kxxb_kzt.deleteCharAt(kxxb_kzt.length() - 1);
				}
				
				iterator = amsDszh.getLmckxxb().iterator();
				while(iterator.hasNext()) {
					lmckxxb = (LMCKXXB) iterator.next();
					lmckxxb_ckrxm.append(";" + lmckxxb.getCkrxm());
					lmckxxb_ckrsfzjzl.append(";" + lmckxxb.getCkrsfzjzl());
					lmckxxb_ckrsfzjhm.append(";" + lmckxxb.getCkrsfzjhm());
					lmckxxb_sfzjdqr.append(";" + lmckxxb.getSfzjdqr());
					lmckxxb_dqdm.append(";" + lmckxxb.getDqdm());
					lmckxxb_ckrlb.append(";" + lmckxxb.getCkrlb());
					lmckxxb_ckrgjdq.append(";" + lmckxxb.getCkrgjdq());
					lmckxxb_ckrxb.append(";" + lmckxxb.getCkrxb());
				}
				
				
				bf.append(amsDszh.getCkrxm() + lmckxxb_ckrxm.toString() +"|" + amsDszh.getCkrsfzjzl() + lmckxxb_ckrsfzjzl.toString() +"|" + amsDszh.getCkrsfzjhm() + lmckxxb_ckrsfzjhm.toString() + "|"
						+ amsDszh.getSfzjdqr() + lmckxxb_sfzjdqr.toString() +"|" + amsDszh.getDqdm() + lmckxxb_dqdm.toString() + "|" + amsDszh.getCkrlb() + lmckxxb_ckrlb.toString() +"|"
						+ amsDszh.getCkrgjdq() + lmckxxb_ckrgjdq.toString() + "|" + amsDszh.getCkrxb() + lmckxxb_ckrxb.toString() +"|" + amsDszh.getCkryb() + "|"
						+ amsDszh.getCkrdz() + "|" + amsDszh.getCkrdh() + "|" + amsDszh.getDlrmc() + "|"
						+ amsDszh.getDlrsfzjzl() + "|" + amsDszh.getDlrsfzjhm() + "|" + amsDszh.getDlrgjdq() + "|"
						+ amsDszh.getDlrdh() + "|" + amsDszh.getJrjgbm() + "|" + amsDszh.getZh() + "|" + amsDszh.getZhzl()
						+ "|" + kxxb_kh.toString() + "|" + kxxb_kdqr.toString() + "|" + kxxb_zhjz.toString() + "|" + kxxb_xkrq.toString() + "|"
						+ kxxb_kzt.toString() + "|" + amsDszh.getZhlx() + "|" + amsDszh.getYlzhzh() + "|" + amsDszh.getYlzhjgbm()
						+ "|" + amsDszh.getKhrq() + "|" + amsDszh.getXhrq() + "|" + amsDszh.getZhzt() + "|"
						+ amsDszh.getBz() + "|" + amsDszh.getSfjrbzk() + "|" + amsDszh.getSfshbzk() + "|"
						+ amsDszh.getHsjg() + "|" + amsDszh.getWfhsyy() + "|" + amsDszh.getCzff().replaceAll(",", ";") + "|" + amsDszh.getXxlx()
						+ "|" + amsDszh.getKhqd() + "|" + amsDszh.getRemarks() + "|" +amsDszh.getFgmjyqd().replaceAll(",", ";") + "|" + amsDszh.getSflmzh() + "|" + amsDszh.getKhdqdm() + "|" +  amsDszh.getReserve4() + "|"
						+ amsDszh.getReserve5());
				bf.append("\r\n");
				
				bw.write(bf.toString());
				bf.setLength(0);
				
				kxxb_kh.delete(0, kxxb_kh.length());
				kxxb_kdqr.delete(0, kxxb_kdqr.length());
				kxxb_zhjz.delete(0, kxxb_zhjz.length());
				kxxb_xkrq.delete(0, kxxb_xkrq.length());
				kxxb_kzt.delete(0, kxxb_kzt.length());
				lmckxxb_ckrxm.delete(0, lmckxxb_ckrxm.length());
				lmckxxb_ckrsfzjzl.delete(0, lmckxxb_ckrsfzjzl.length());
				lmckxxb_ckrsfzjhm.delete(0, lmckxxb_ckrsfzjhm.length());
				lmckxxb_sfzjdqr.delete(0, lmckxxb_sfzjdqr.length());
				lmckxxb_dqdm.delete(0, lmckxxb_dqdm.length());
				lmckxxb_ckrlb.delete(0, lmckxxb_ckrlb.length());
				lmckxxb_ckrgjdq.delete(0, lmckxxb_ckrgjdq.length());
				lmckxxb_ckrxb.delete(0, lmckxxb_ckrxb.length());
				
				
//				outPutFile(jlrq,sflmzh, zh,bf,lmckxxb_list, kxxb_list, list);
				} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		
		session.close();
		/*
		 * 更新report_status
		 */
		rootDAO.executeSql("UPDATE AMS_DSZH SET REPORT_STATUS = '1' WHERE REPORT_STATUS='0' OR REPORT_STATUS='3'");
	
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		//保存报文相关信息
//		try {
//			saveMessageInfo(jlrq, workDate, filename);
//		} catch (CommonException e) {
//			e.printStackTrace();
//		}
		
		try {
			saveMessageInfo(jlrq, workDate, filename);
		} catch (CommonException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("导出成功!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	/**
	 * 将对象中的属性值null替换为""
	 * @param amsDszh
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void nulltoNothing(AmsDszh amsDszh) throws IllegalAccessException, InvocationTargetException {
		Field[] f=AmsDszh.class.getSuperclass().getDeclaredFields();
//		System.out.println("Test类里面的所有字段属性的个数为："+f.length+"个，分别为：");
		Object result = null;
		for(int i=0;i<f.length;i++){
		    String attributeName=f[i].getName();
//		    System.out.println(attributeName);
		    
		        //将属性名的首字母变为大写，为执行set/get方法做准备
		        String methodName=attributeName.substring(0,1).toUpperCase()+attributeName.substring(1);
		        
		        try{
		        	
		        	Method getMethod=AmsDszh.class.getSuperclass().getDeclaredMethod("get"+methodName);
		            
		            result=getMethod.invoke(amsDszh);
		        	if(result == null) {
		        		Method setMethod=AmsDszh.class.getSuperclass().getMethod("set"+methodName, String.class);
		                setMethod.invoke(amsDszh,"");
		        	}
		            
		        }catch (NoSuchMethodException e) {
		            try {
		                Method setMethod=AmsDszh.class.getMethod("set"+methodName,int.class);
		                setMethod.invoke(amsDszh,"");
		            } catch (Exception e2) {
		                f[i].set(amsDszh,"");
		            }

		        }
		}
	}    
	
	private static void nulltoNothingkk(KXXB kk) throws IllegalAccessException, InvocationTargetException {
		Field[] f=KXXB.class.getSuperclass().getDeclaredFields();
//		System.out.println("Test类里面的所有字段属性的个数为："+f.length+"个，分别为：");
		Object result = null;
		for(int i=0;i<f.length;i++){
		    String attributeName=f[i].getName();
//		    System.out.println(attributeName);
		    
		        //将属性名的首字母变为大写，为执行set/get方法做准备
		        String methodName=attributeName.substring(0,1).toUpperCase()+attributeName.substring(1);
		        
		        try{
		        	
		        	Method getMethod=KXXB.class.getSuperclass().getDeclaredMethod("get"+methodName);
		            
		            result=getMethod.invoke(kk);
		        	if(result == null) {
		        		Method setMethod=KXXB.class.getSuperclass().getMethod("set"+methodName, String.class);
		                setMethod.invoke(kk,"");
		        	}
		            
		        }catch (NoSuchMethodException e) {
		            try {
		                Method setMethod=KXXB.class.getMethod("set"+methodName,int.class);
		                setMethod.invoke(kk,"");
		            } catch (Exception e2) {
		                f[i].set(kk,"");
		            }

		        }
		}
	}    
	
	private static void nulltoNothinglmck(LMCKXXB kk) throws IllegalAccessException, InvocationTargetException {
		Field[] f=LMCKXXB.class.getSuperclass().getDeclaredFields();
		Object result = null;
		for(int i=0;i<f.length;i++){
		    String attributeName=f[i].getName();
//		    System.out.println(attributeName);
		    
		        //将属性名的首字母变为大写，为执行set/get方法做准备
		        String methodName=attributeName.substring(0,1).toUpperCase()+attributeName.substring(1);
		        
		        try{
		        	
		        	Method getMethod=LMCKXXB.class.getSuperclass().getDeclaredMethod("get"+methodName);
		            
		            result=getMethod.invoke(kk);
		        	if(result == null) {
		        		Method setMethod=LMCKXXB.class.getSuperclass().getMethod("set"+methodName, String.class);
		                setMethod.invoke(kk,"");
		        	}
		            
		        }catch (NoSuchMethodException e) {
		            try {
		                Method setMethod=LMCKXXB.class.getMethod("set"+methodName,int.class);
		                setMethod.invoke(kk,"");
		            } catch (Exception e2) {
		                f[i].set(kk,"");
		            }

		        }
		}
	}    
	
	/**
	 * 保存报文信息
	 * @param jlrq
	 * @param workDate
	 * @param filename
	 * @throws CommonException
	 */
	private void saveMessageInfo(String jlrq, String workDate, String filename) throws CommonException {
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		
		AmsDszhMessageInfo amsDszhMessageInfo = new AmsDszhMessageInfo();
		amsDszhMessageInfo.setMessagetype("集中账户报文");
		amsDszhMessageInfo.setDatadate(jlrq);
		amsDszhMessageInfo.setBorndate(workDate);
		amsDszhMessageInfo.setMessagestatus("-");
		amsDszhMessageInfo.setMessagename(filename + ".txt");
		rootDAO.save(amsDszhMessageInfo);
	}

}
