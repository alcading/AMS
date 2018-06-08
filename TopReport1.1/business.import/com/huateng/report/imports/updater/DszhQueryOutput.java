package com.huateng.report.imports.updater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.huateng.commquery.result.MultiUpdateResultBean;
import com.huateng.commquery.result.UpdateResultBean;
import com.huateng.commquery.result.UpdateReturnBean;
import com.huateng.ebank.business.management.common.DAOUtils;
import com.huateng.ebank.framework.web.commQuery.BaseUpdate;
import com.huateng.exception.AppException;
import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.ReportUtils;

import resource.bean.pub.BrnoJbcdLink;
import resource.dao.base.HQLDAO;
import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.KXXB;
import resources.east.data.pub.LMCKXXB;

public class DszhQueryOutput extends BaseUpdate {
	private static final Logger logger = Logger.getLogger(DszhQueryOutput.class);
	public static Session session = null;
	@Override
	public UpdateReturnBean saveOrUpdate(MultiUpdateResultBean multiUpdateResultBean, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		UpdateReturnBean updateReturnBean = new UpdateReturnBean();
		UpdateResultBean updateResultBean = multiUpdateResultBean.getUpdateResultBeanByID("DszhQuery");
		String jlrq=updateResultBean.getParameter("jlrq");
		
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		@SuppressWarnings("unchecked")
		List<AmsDszh> list = rootDAO.queryByQL2List(" from AmsDszh model where model.jlrq='" + jlrq + "'");
		
		@SuppressWarnings("unchecked")
		List Bctl_list = rootDAO.queryByQL2List("select A.brno, A.jrjgbm from BrnoJbcdLink A, Bctl B where A.brno = B.brno and B.brclass='1'");
		String headOfficeJrjgbm = null;
		
		for(int i = 0; i < Bctl_list.size(); i ++) {
			 Object[] object = (Object[])Bctl_list.get(i);
			 headOfficeJrjgbm = (String)object[1];
		}
		BufferedWriter bw=null;
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date(currentTime);
		String workDate = formatter.format(date);
		String filePath = ReportUtils.getSysParamsValue(Constants.PARAM_DIR, Constants.PARAM_DIR_0105, "");
		filePath = filePath + File.separator + jlrq + File.separator;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		
		
		HQLDAO dao = DAOUtils.getHQLDAO();
		session = dao.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		
		System.out.println(session.hashCode());
		
		StringBuilder bf=new StringBuilder();
		bf.append(
				"存款人姓名(存款人子信息)|存款人身份证件种类(存款人子信息)|存款人身份证件号码(存款人子信息)|身份证件到期日(存款人子信息)|发证机关所在地的地区代码(存款人子信息)|存款人类别(存款人子信息)|存款人国籍(存款人子信息)|存款人性别(存款人子信息)|存款人邮编|存款人地址|存款人电话|代理人名称|代理人身份证件种类|代理人身份证件号码|代理人国籍|代理人电话|开户银行金融机构编码（不可变更）|账号（不可变更）|账户种类|介质号（介质子信息）|介质到期日（介质子信息）|账户介质（介质子信息）|介质注销日期(介质子信息)|介质状态（介质子信息）|账户类型|II、III类户绑定账户账号(绑定账户子信息)|II、III类户绑定账户开户银行金融机构编码(绑定账户子信息)|开户日期|销户日期(不可变)|账户状态|币种|是否为军人保障卡|是否为社会保障卡|核实结果|无法核实原因|处置方法|信息类型|开户渠道|备注|开通的非柜面交易渠道|是否为\"联名账户\"|开户地地区代码|预留字段4|预留字段5");
		bf.append("\r\n");
		Iterator it = list.iterator();
		for(AmsDszh amsDszh:list) {
			String sflmzh = amsDszh.getSflmzh();
			jlrq = amsDszh.getJlrq();
			String report_status = amsDszh.getReport_status();
			if(!report_status.equals("0")&&(!report_status.equals("3"))){
				continue;
			}
			String zh = amsDszh.getZh();
			
				 
				try {
					outPutFile(jlrq,sflmzh, zh,bf);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
		}
		try {
			int min = 10000;
			int max = 99999;
			filePath = filePath + "[" + headOfficeJrjgbm + "]" + "[cams00100101]" + "[" + workDate
					+ (new Random().nextInt(max) % (max + min + 1) + min) + "]";
			File txtFile = new File(filePath + ".txt");
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "UTF-8"));
			bw.write(bf.toString());	
	    bw.flush();
	    bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return updateReturnBean;
	}
	

	public static int outPutFile(String jlrq,String sflmzh, String zh,StringBuilder bf) throws IOException {
		
		
		HQLDAO dao = DAOUtils.getHQLDAO();
		Transaction tx = null;
		
		Integer lmccount=0;
		Integer counts=0;
		AmsDszh amsDszh = new AmsDszh();
		KXXB kxxb = new KXXB();
		LMCKXXB lmckxxb = new LMCKXXB();
		String sql = "FROM AmsDszh WHERE ZH="+ zh;
		
		System.out.println(session.hashCode());
		System.out.println(session.isOpen());
		
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		StringBuilder builder3 = new StringBuilder();
		StringBuilder builder4 = new StringBuilder();
		StringBuilder builder5 = new StringBuilder();
		StringBuilder builder6 = new StringBuilder();
		StringBuilder builder7 = new StringBuilder();
		StringBuilder builder8 = new StringBuilder();
		try {
			tx = session.beginTransaction();
			AmsDszh s = (AmsDszh)session.get(AmsDszh.class, zh);  
			s.setReport_status("1");
			tx.commit();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
		}
		String sql_lmckxxb = "select COUNT(*) FROM LMCKXXB where zh=" + zh;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(sql_lmckxxb);
			List lmckxxb_count = query.list();
			tx.commit();
			
			for(int i = 0; i < lmckxxb_count.size(); i ++){
				lmccount=Integer.parseInt(lmckxxb_count.get(i).toString());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
		}
		String sql_lmckxxb1 = "from LMCKXXB where zh=" + zh;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(sql_lmckxxb1);
			List<LMCKXXB> lmckxxb_temp = (List<LMCKXXB>)query.list();
			tx.commit();
			
			
			Iterator its=lmckxxb_temp.iterator();    
			  while(its.hasNext()){   
				LMCKXXB lmckxxb_list=(LMCKXXB)its.next();
				if(lmccount<1){
					String ckrxm = lmckxxb_list.getCkrxm();
					if(ckrxm!=null){
						lmckxxb.setCkrxm(ckrxm);
					}else{
						lmckxxb.setCkrxm("");
					}
				  String ckrsfzjzl = lmckxxb_list.getCkrsfzjzl();
				  if(ckrsfzjzl!=null){
					  lmckxxb.setCkrsfzjzl(ckrsfzjzl);
				  }else{
					  lmckxxb.setCkrsfzjzl("");
				  }
				  String ckrsfzjhm = lmckxxb_list.getCkrsfzjhm();
				  if(ckrsfzjhm!=null){
					  lmckxxb.setCkrsfzjhm(ckrsfzjhm);
				  }else{
					  lmckxxb.setCkrsfzjhm("");
				  }
				  String sfzjdqr = lmckxxb_list.getSfzjdqr();
				  if(sfzjdqr!=null){
					  lmckxxb.setSfzjdqr(sfzjdqr);
				  }else{
					  lmckxxb.setSfzjdqr("");
				  }
				  String dqdm = lmckxxb_list.getDqdm();
				  if(dqdm!=null){
					  lmckxxb.setDqdm(dqdm);
				  }else{
					  lmckxxb.setDqdm("");
				  }
				  String ckrlb= lmckxxb_list.getCkrlb();
				  if(ckrlb!=null){
					  lmckxxb.setCkrlb(ckrlb);
				  }else{
					  lmckxxb.setCkrlb("");
				  }
				  String ckrgjdq = lmckxxb_list.getCkrgjdq();
				  if(ckrgjdq!=null){
					  lmckxxb.setCkrgjdq(ckrgjdq);
				  }else{
					  lmckxxb.setCkrgjdq("");
				  }
				  String ckrxb = lmckxxb_list.getCkrxb();
				  if(ckrxb!=null){
					  lmckxxb.setCkrxb(ckrxb);
				  }else{
					  lmckxxb.setCkrxb(ckrxb);
				  }
				}else{
					counts++;
					String ckrxm = lmckxxb_list.getCkrxm();
                    if(counts==lmccount){
						if(ckrxm!=null){
						builder1.append(ckrxm);
						}else{
							ckrxm="";
							builder1.append(ckrxm);
						}
						
					}else{
					if(ckrxm!=null){
						ckrxm=ckrxm+";";
						builder1.append(ckrxm);
					}else{
						ckrxm=""+";";
						builder1.append(ckrxm);
					}
					}
					String ckrsfzjzl= lmckxxb_list.getCkrsfzjzl();
					if(counts==lmccount){
						if(ckrsfzjzl!=null){
							builder2.append(ckrsfzjzl);
						}else{
							ckrsfzjzl="";
							builder2.append(ckrsfzjzl);
						}
					}else{
					if(ckrsfzjzl!=null){
						ckrsfzjzl=ckrsfzjzl+";";
						builder2.append(ckrsfzjzl);
					}else{
						ckrsfzjzl=""+";";
						builder2.append(ckrsfzjzl);
					}
					}
					String ckrsfzjhm = lmckxxb_list.getCkrsfzjhm();
					if(counts==lmccount){
						if(ckrsfzjhm!=null){
							builder3.append(ckrsfzjhm);
						}else{
							ckrsfzjhm="";
							builder3.append(ckrsfzjhm);
						}
					}else{
					if(ckrsfzjhm!=null){
						ckrsfzjhm=ckrsfzjhm+";";
						builder3.append(ckrsfzjhm);
					}else{
						ckrsfzjhm=""+";";
						builder3.append(ckrsfzjhm);
					}
					}
					String sfzjdqr = lmckxxb_list.getSfzjdqr();
					if(counts==lmccount){
						if(sfzjdqr!=null){
							builder4.append(sfzjdqr);
						}else{
							sfzjdqr="";
							builder4.append(sfzjdqr);
						}
					}else{
					if(sfzjdqr!=null){
						sfzjdqr=sfzjdqr+";";
						builder4.append(sfzjdqr);
					}else{
						sfzjdqr=""+";";
						builder4.append(sfzjdqr);
					}
					}
					String dqdm = lmckxxb_list.getDqdm();
					if(counts==lmccount){
						if(dqdm!=null){
							builder5.append(dqdm);
						}else{
							dqdm="";
							builder5.append(dqdm);
						}
					}else{
					if(dqdm!=null){
						dqdm=dqdm+";";
						builder5.append(dqdm);
					}else{
						dqdm=""+";";
						builder5.append(dqdm);
					}
					}
					String ckrlb = lmckxxb_list.getCkrlb();
					if(counts==lmccount){
						if(ckrlb!=null){
							builder6.append(ckrlb);
						}else{
							ckrlb="";
						}
						
					}else{
					if(ckrlb!=null){
						ckrlb=ckrlb+";";
						builder6.append(ckrlb);
					}else{
						ckrlb=""+";";
						builder6.append(ckrlb);
					}
					}
					String ckrgjdq = lmckxxb_list.getCkrgjdq();
					if(counts==lmccount){
						if(ckrgjdq!=null){
							builder7.append(ckrgjdq);
						}else{
							ckrgjdq="";
							builder7.append(ckrgjdq);
						}
					}else{
					if(ckrgjdq!=null){
						ckrgjdq=ckrgjdq+";";
						builder7.append(ckrgjdq);
					}else{
						ckrgjdq=""+";";
						builder7.append(ckrgjdq);
					}
					}
					String ckrxb = lmckxxb_list.getCkrxb();
					if(counts==lmccount){
						if(ckrxb!=null){
							builder8.append(ckrxb);
						}else{
							ckrxb="";
						}
					}else{
					if(ckrxb!=null){
						ckrxb=ckrxb+";";
						builder8.append(ckrxb);
					}else{
						ckrxb=""+";";
						builder8.append(ckrxb);
					}
					}
				}
				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
		}
		try {
			
			
			tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			List<AmsDszh> amsdszh_list = query.list();
			tx.commit();
			
			
			Iterator its=amsdszh_list.iterator();    
			  while(its.hasNext()){    
				  AmsDszh amsDszh_temp=(AmsDszh)its.next();   
				String ckrxm = amsDszh_temp.getCkrxm();
				if (ckrxm != null) {
					amsDszh.setCkrxm(ckrxm);
				} else {
					amsDszh.setCkrxm("");
				}
				String ckrsfzjzl = amsDszh_temp.getCkrsfzjzl();
				if (ckrsfzjzl != null) {
					amsDszh.setCkrsfzjzl(ckrsfzjzl);
				} else {
					amsDszh.setCkrsfzjzl("");
				}
				String ckrsfzjhm = amsDszh_temp.getCkrsfzjhm();
				if (ckrsfzjhm != null) {
					amsDszh.setCkrsfzjhm(ckrsfzjhm);
				} else {
					amsDszh.setCkrsfzjhm("");
				}
				String sfzjdqr = amsDszh_temp.getSfzjdqr();
				if (sfzjdqr != null) {
					amsDszh.setSfzjdqr(sfzjdqr);
				} else {
					amsDszh.setSfzjdqr("");
				}
				String ckrlb = amsDszh_temp.getCkrlb();
				if (ckrlb != null) {
					amsDszh.setCkrlb(ckrlb);
				} else {
					amsDszh.setCkrlb("");
				}
				String ckrgjdq = amsDszh_temp.getCkrgjdq();
				if (ckrgjdq != null) {
					amsDszh.setCkrgjdq(ckrgjdq);
				} else {
					amsDszh.setCkrgjdq("");
				}
				String ckrxb = amsDszh_temp.getCkrxb();
				if (ckrxb != null) {
					amsDszh.setCkrxb(ckrxb);
				} else {
					amsDszh.setCkrxb("");
				}
				String ckryb = amsDszh_temp.getCkryb();
				if (ckryb != null) {
					amsDszh.setCkryb(ckryb);
				} else {
					amsDszh.setCkryb("");
				}
				String ckrdz = amsDszh_temp.getCkrdz();
				if (ckrdz != null) {
					amsDszh.setCkrdz(ckrdz);
				} else {
					amsDszh.setCkrdz("");
				}
				String ckrdh = amsDszh_temp.getCkrdh();
				if (ckrdh != null) {
					amsDszh.setCkrdh(ckrdh);
				} else {
					amsDszh.setCkrdh("");
				}
				String dlrmc = amsDszh_temp.getDlrmc();
				if (dlrmc != null) {
					amsDszh.setDlrmc(dlrmc);
				} else {
					amsDszh.setDlrmc("");
				}
				String dlrsfzjzl = amsDszh_temp.getDlrsfzjzl();
				if (dlrsfzjzl != null) {
					amsDszh.setDlrsfzjzl(dlrsfzjzl);
				} else {
					amsDszh.setDlrsfzjzl("");
				}
				String dlrsfzjhm = amsDszh_temp.getDlrsfzjhm();
				if (dlrsfzjhm != null) {
					amsDszh.setDlrsfzjhm(dlrsfzjhm);
				} else {
					amsDszh.setDlrsfzjhm("");
				}
				String dlrgjdq = amsDszh_temp.getDlrgjdq();
				if (dlrgjdq != null) {
					amsDszh.setDlrgjdq(dlrgjdq);
				} else {
					amsDszh.setDlrgjdq("");
				}
				String dlrdh = amsDszh_temp.getDlrdh();
				if (dlrdh != null) {
					amsDszh.setDlrdh(dlrdh);
				} else {
					amsDszh.setDlrdh("");
				}
				String jrjgbm = amsDszh_temp.getJrjgbm();
				if (jrjgbm != null) {
					amsDszh.setJrjgbm(jrjgbm);
				} else {
					amsDszh.setJrjgbm("");
				}
				String zh1 = amsDszh_temp.getZh();
				if (zh1 != null) {
					amsDszh.setZh(zh1);
				} else {
					amsDszh.setZh("");
				}
				String zhzl = amsDszh_temp.getZhzl();
				if (zhzl != null) {
					amsDszh.setZhzl(zhzl);
				} else {
					amsDszh.setZhzl("");
				}
				String zhlx = amsDszh_temp.getZhlx();
				if (zhlx != null) {
					amsDszh.setZhlx(zhlx);
				} else {
					amsDszh.setZhlx("");
				}
				String ylzhzh = amsDszh_temp.getYlzhzh();
				if (ylzhzh != null) {
					amsDszh.setYlzhzh(ylzhzh);
				} else {
					amsDszh.setYlzhzh("");
				}
				String ylzhjgbm = amsDszh_temp.getYlzhjgbm();
				if (ylzhjgbm != null) {
					amsDszh.setYlzhjgbm(ylzhjgbm);
				} else {
					amsDszh.setYlzhjgbm("");
				}
				String khrq = amsDszh_temp.getKhrq();
				if (khrq != null) {
					amsDszh.setKhrq(khrq);
				} else {
					amsDszh.setKhrq("");
				}
				String xhrq = amsDszh_temp.getXhrq();
				if (xhrq != null) {
					amsDszh.setXhrq(xhrq);
				} else {
					amsDszh.setXhrq("");
				}
				String zhzt = amsDszh_temp.getZhzt();
				if (zhzt != null) {
					amsDszh.setZhzt(zhzt);
				} else {
					amsDszh.setZhzt("");
				}
				String bz = amsDszh_temp.getBz();
				if (bz != null) {
					amsDszh.setBz(bz);
				} else {
					amsDszh.setBz("");
				}
				String sfjrbzk = amsDszh_temp.getSfjrbzk();
				if (sfjrbzk != null) {
					amsDszh.setSfjrbzk(sfjrbzk);
				} else {
					amsDszh.setSfjrbzk("");
				}
				String sfshbzk = amsDszh_temp.getSfshbzk();
				if (sfshbzk != null) {
					amsDszh.setSfshbzk(sfshbzk);
				} else {
					amsDszh.setSfshbzk("");
				}
				String hsjg = amsDszh_temp.getHsjg();
				if (hsjg != null) {
					amsDszh.setHsjg(hsjg);
				} else {
					amsDszh.setHsjg("");
				}
				String wfhsyy = amsDszh_temp.getWfhsyy();
				if (wfhsyy != null) {
					amsDszh.setWfhsyy(wfhsyy);
				} else {
					amsDszh.setWfhsyy("");
				}
				String czff = amsDszh_temp.getCzff();
				if (czff != null) {
					amsDszh.setCzff(czff);
				} else {
					amsDszh.setCzff("");
				}
				String xxlx = amsDszh_temp.getXxlx();
				if (xxlx != null) {
					amsDszh.setXxlx(xxlx);
				} else {
					amsDszh.setXxlx("");
				}
				String khqd = amsDszh_temp.getKhqd();
				if (khqd != null) {
					amsDszh.setKhqd(khqd);
				} else {
					amsDszh.setKhqd("");
				}
				String remarks = amsDszh_temp.getRemarks();
				if (remarks != null) {
					amsDszh.setRemarks(remarks);
				} else {
					amsDszh.setRemarks("");
				}
				String reserve4 = amsDszh_temp.getReserve4();
				if (reserve4 != null) {
					amsDszh.setReserve4(reserve4);
				} else {
					amsDszh.setReserve4("");
				}
				String reserve5 = amsDszh_temp.getReserve5();
				if (reserve5 != null) {
					amsDszh.setReserve5(reserve5);
				} else {
					amsDszh.setReserve5("");
				}
				String jlzt = amsDszh_temp.getJlzt();
				if (jlzt != null) {
					amsDszh.setJlzt(jlzt);
				} else {
					amsDszh.setJlzt("");
				}
				String jlrq1 = amsDszh_temp.getJlrq();
				if (jlrq1 != null) {
					amsDszh.setJlrq(jlrq1);
				} else {
					amsDszh.setJlrq("");
				}
				String fgmjyqd = amsDszh_temp.getFgmjyqd();
				if (fgmjyqd != null) {
					amsDszh.setFgmjyqd(fgmjyqd);
				} else {
					amsDszh.setFgmjyqd("");
				}
				String sflmzh1 = amsDszh_temp.getSflmzh();
				if (sflmzh1 != null) {
					amsDszh.setSflmzh(sflmzh1);
				} else {
					amsDszh.setSflmzh("");
				}
				String khdqdm = amsDszh_temp.getKhdqdm();
				if (khdqdm != null) {
					amsDszh.setKhdqdm(khdqdm);
				} else {
					amsDszh.setKhdqdm("");
				}
				String dqdm = amsDszh_temp.getDqdm();
				if (dqdm != null) {
					amsDszh.setDqdm(dqdm);
				} else {
					amsDszh.setDqdm("");
				}
				String sfzjdqr1 = amsDszh_temp.getSfzjdqr();
				if (sfzjdqr1 != null) {
					amsDszh.setSfzjdqr(sfzjdqr1);
				} else {
					amsDszh.setSfzjdqr("");
				}
			}
 			String sql_kxxb = "SELECT count(*) FROM KXXB WHERE ZH=" + zh;
			Integer count = 0;
			tx = session.beginTransaction();
			Query query_kxxbCount = session.createQuery(sql_kxxb);
			List kxxb_count = query_kxxbCount.list();
			tx.commit();
			
			
			for(int i = 0; i < kxxb_count.size(); i ++){
				count=Integer.parseInt(kxxb_count.get(i).toString());
			}
			String sql_kxxb0 = "from KXXB where zh=" + zh;
			tx = session.beginTransaction();
			Query query_kxxb = session.createQuery(sql_kxxb0);
			List kxxb_list = query_kxxb.list();
			tx.commit();
			
			
			StringBuilder builderkh = new StringBuilder();
			StringBuilder builderkdqr = new StringBuilder();
			StringBuilder builderzhjz = new StringBuilder();
			StringBuilder builderxkrq = new StringBuilder();
			StringBuilder builderkzt = new StringBuilder();
			Integer res=0;
			
			Iterator it=kxxb_list.iterator();    
			  while(it.hasNext()){    
				  KXXB kxxb_temp=(KXXB)it.next();  
				if (count <= 1) {
					String kh = kxxb_temp.getKh();
					if (kh != null) {
						kxxb.setKh(kh);
					} else {
						kxxb.setKh("");
					}
					String kdqr = kxxb_temp.getKdqr();
					if (kdqr != null) {
						kxxb.setKdqr(kdqr);
					} else {
						kxxb.setKdqr("");
					}
					String zhjz = kxxb_temp.getZhjz();
					if (zhjz != null) {
						kxxb.setZhjz(zhjz);
					} else {
						kxxb.setZhjz("");
					}
					String xkrq = kxxb_temp.getXkrq();
					if (xkrq != null) {
						kxxb.setXkrq(xkrq);
					} else {
						kxxb.setXkrq("");
					}
					String kzt = kxxb_temp.getKzt();
					if (kzt != null) {
						kxxb.setKzt(kzt);
					} else {
						kxxb.setKzt("");
					}
				}
				
				if (count > 1) {
					res++;
					String kh = kxxb_temp.getKh();
					if (res == count) {
						if (kh != null) {
							builderkh.append(kh);
						} else {
							kh = "";
							builderkh.append(kh);
						}
					} else {
						if (kh != null) {
							kh = kh + ";";
							builderkh.append(kh);
						} else {
							kh = ""+";";
							builderkh.append(kh);
						}
					}
					// kxxb.setKh(kh);
					String kdqr = kxxb_temp.getKdqr();
					if (res == count) {
						if (kdqr != null) {
							builderkdqr.append(kdqr);
						} else {
							kdqr = "";
							builderkdqr.append(kdqr);
						}
					} else {
						if (kdqr != null) {
							kdqr = kdqr + ";";
							builderkdqr.append(kdqr);
						} else {
							kdqr =  ""+";";
							builderkdqr.append(kdqr);
						}
					}
					// kxxb.setKdqr(kdqr);
					String zhjz = kxxb_temp.getZhjz();
					if (res == count) {
						if (zhjz != null) {
							builderzhjz.append(zhjz);
						} else {
							zhjz = "";
							builderzhjz.append(zhjz);
						}
					} else {
						if (zhjz != null) {
							zhjz = zhjz + ";";
							builderzhjz.append(zhjz);
						} else {
							zhjz =  ""+";";
							builderzhjz.append(zhjz);
						}
					}
					// kxxb.setZhjz(zhjz);
					String xkrq = kxxb_temp.getXkrq();
					if (res == count) {
						if (xkrq != null) {
							builderxkrq.append(xkrq);
						} else {
							xkrq = "";
							builderxkrq.append(xkrq);
						}
					} else {
						if (xkrq != null) {
							xkrq = xkrq + ";";
							builderxkrq.append(xkrq);
						} else {
							xkrq = ""+";";
							builderxkrq.append(xkrq);
						}
					}
					// kxxb.setXkrq(xkrq);
					String kzt = kxxb_temp.getKzt();
					if (res == count) {
						if (kzt != null) {
							builderkzt.append(kzt);
						} else {
							kzt = "";
							builderkzt.append(kzt);
						}
					} else {
						if (kzt != null) {
							kzt = kzt + ";";
							builderkzt.append(kzt);
						} else {
							kzt = ""+";";
							;
							builderkzt.append(kzt);
						}
					}
					// kxxb.setKzt(kzt);
				}
			}
			if (count > 1) {
				kxxb.setKh(builderkh.toString());
				kxxb.setKdqr(builderkdqr.toString());
				kxxb.setZhjz(builderzhjz.toString());
				kxxb.setXkrq(builderxkrq.toString());
				kxxb.setKzt(builderkzt.toString());
			}
			if(lmccount>1){
				lmckxxb.setCkrxm(builder1.toString());
				lmckxxb.setCkrsfzjzl(builder2.toString());
				lmckxxb.setCkrsfzjhm(builder3.toString());
				lmckxxb.setSfzjdqr(builder4.toString());
				lmckxxb.setDqdm(builder5.toString());
				lmckxxb.setCkrlb(builder6.toString());
				lmckxxb.setCkrgjdq(builder7.toString());
				lmckxxb.setCkrxb(builder8.toString());
				bf.append(amsDszh.getCkrxm() +";"+ lmckxxb.getCkrxm()+"|" + amsDszh.getCkrsfzjzl() +";"+lmckxxb.getCkrsfzjzl()+"|" + amsDszh.getCkrsfzjhm() +";"+lmckxxb.getCkrsfzjhm()+ "|"
						+ amsDszh.getSfzjdqr() +";"+lmckxxb.getSfzjdqr()+"|" + amsDszh.getDqdm() +";"+lmckxxb.getDqdm()+ "|" + amsDszh.getCkrlb() + ";"+lmckxxb.getCkrlb()+"|"
						+ amsDszh.getCkrgjdq() +";"+lmckxxb.getCkrgjdq()+ "|" + amsDszh.getCkrxb() + ";"+lmckxxb.getCkrxb()+"|" + amsDszh.getCkryb() + "|"
						+ amsDszh.getCkrdz() + "|" + amsDszh.getCkrdh() + "|" + amsDszh.getDlrmc() + "|"
						+ amsDszh.getDlrsfzjzl() + "|" + amsDszh.getDlrsfzjhm() + "|" + amsDszh.getDlrgjdq() + "|"
						+ amsDszh.getDlrdh() + "|" + amsDszh.getJrjgbm() + "|" + amsDszh.getZh() + "|" + amsDszh.getZhzl()
						+ "|" + kxxb.getKh() + "|" + kxxb.getKdqr() + "|" + kxxb.getZhjz() + "|" + kxxb.getXkrq() + "|"
						+ kxxb.getKzt() + "|" + amsDszh.getZhlx() + "|" + amsDszh.getYlzhzh() + "|" + amsDszh.getYlzhjgbm()
						+ "|" + amsDszh.getKhrq() + "|" + amsDszh.getXhrq() + "|" + amsDszh.getZhzt() + "|"
						+ amsDszh.getBz() + "|" + amsDszh.getSfjrbzk() + "|" + amsDszh.getSfshbzk() + "|"
						+ amsDszh.getHsjg() + "|" + amsDszh.getWfhsyy() + "|" + amsDszh.getCzff().replaceAll(",", ";") + "|" + amsDszh.getXxlx()
						+ "|" + amsDszh.getKhqd() + "|" + amsDszh.getRemarks() + "|" +amsDszh.getFgmjyqd().replaceAll(",", ";") + "|" + amsDszh.getSflmzh() + "|" + amsDszh.getKhdqdm() + "|" +  amsDszh.getReserve4() + "|"
						+ amsDszh.getReserve5());
			}else{
				bf.append(amsDszh.getCkrxm() +"|" + amsDszh.getCkrsfzjzl() +"|" + amsDszh.getCkrsfzjhm() + "|"
						+ amsDszh.getSfzjdqr()+"|" + amsDszh.getDqdm()+ "|" + amsDszh.getCkrlb() +"|"
						+ amsDszh.getCkrgjdq() + "|" + amsDszh.getCkrxb() +"|" + amsDszh.getCkryb() + "|"
						+ amsDszh.getCkrdz() + "|" + amsDszh.getCkrdh() + "|" + amsDszh.getDlrmc() + "|"
						+ amsDszh.getDlrsfzjzl() + "|" + amsDszh.getDlrsfzjhm() + "|" + amsDszh.getDlrgjdq() + "|"
						+ amsDszh.getDlrdh() + "|" + amsDszh.getJrjgbm() + "|" + amsDszh.getZh() + "|" + amsDszh.getZhzl()
						+ "|" + kxxb.getKh() + "|" + kxxb.getKdqr() + "|" + kxxb.getZhjz() + "|" + kxxb.getXkrq() + "|"
						+ kxxb.getKzt() + "|" + amsDszh.getZhlx() + "|" + amsDszh.getYlzhzh() + "|" + amsDszh.getYlzhjgbm()
						+ "|" + amsDszh.getKhrq() + "|" + amsDszh.getXhrq() + "|" + amsDszh.getZhzt() + "|"
						+ amsDszh.getBz() + "|" + amsDszh.getSfjrbzk() + "|" + amsDszh.getSfshbzk() + "|"
						+ amsDszh.getHsjg() + "|" + amsDszh.getWfhsyy() + "|" + amsDszh.getCzff().replaceAll(",", ";") + "|" + amsDszh.getXxlx()
						+ "|" + amsDszh.getKhqd() + "|" + amsDszh.getRemarks() + "|" +amsDszh.getFgmjyqd().replaceAll(",", ";") + "|" + amsDszh.getSflmzh() + "|" + amsDszh.getKhdqdm() + "|" + amsDszh.getReserve4() + "|"
						+ amsDszh.getReserve5());
			}
			bf.append("\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
		int ret = 0;
		return ret;
	}
}