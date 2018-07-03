package com.huateng.report.imports.updater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
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

import com.huateng.report.tool.FSearchTool;
import com.huateng.commquery.result.MultiUpdateResultBean;
import com.huateng.commquery.result.UpdateResultBean;
import com.huateng.commquery.result.UpdateReturnBean;
import com.huateng.ebank.business.management.common.DAOUtils;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.web.commQuery.BaseUpdate;
import com.huateng.exception.AppException;
import com.huateng.report.imports.common.Constants;
import com.huateng.report.utils.ReportUtils;

import resource.bean.pub.BrnoJbcdLink;
import resource.dao.base.HQLDAO;
import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.base.BaseAmsDszh;
import resources.east.data.base.BaseKXXB;
import resources.east.data.pub.AmsDszh;
import resources.east.data.pub.AmsDszhMessageInfo;
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
//		List<AmsDszh> list = rootDAO.queryByQL2List(" from AmsDszh model where model.jlrq='" + jlrq + "'");
		
		
		/*
		 * 导出文件名中的金融机构编码为总行的金融机构编码
		 */
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
		String filename = null;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		
		
		HQLDAO dao = DAOUtils.getHQLDAO();
		session = dao.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		
		
		List<KXXB> kxxb_list = null;
		List<LMCKXXB> lmckxxb_list = null;
		
		
		List<AmsDszh> list = session.createQuery(" from AmsDszh model where model.jlrq='" + jlrq + "'").list();
		Iterator iterator = null;
//		for(AmsDszh ams:list) {
//			System.out.println(ams.toString());
//			iterator = ams.getKxxb().iterator();
//			while(iterator.hasNext()) {
//				String mk = ((BaseKXXB) iterator.next()).getKdqr();
//				System.out.println(mk);
//			}
//		}
		
        
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
		
		for(AmsDszh amsDszh:list) {
			
			/*
			 * 将对象中的null全部转换为""
			 */
			try {
				nulltoNothing(amsDszh);
				for(KXXB kk:amsDszh.getKxxb()) {
					nulltoNothingkk(kk);
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sflmzh = amsDszh.getSflmzh();
			jlrq = amsDszh.getJlrq();
			String report_status = amsDszh.getReport_status();
			if(!report_status.equals("0")&&(!report_status.equals("3"))){
				continue;
			}
			String zh = amsDszh.getZh();
			
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
		rootDAO.executeSql("UPDATE AMS_DSZH SET REPORT_STATUS = '1' WHERE (REPORT_STATUS='0' OR REPORT_STATUS='3') AND JLRQ = '" + jlrq + "'");
	
		try {
			int min = 10000;
			int max = 99999;
			filename = "[" + headOfficeJrjgbm + "]" + "[cams00100101]" + "[" + workDate
					+ (new Random().nextInt(max) % (max - min + 1) + min) + "]";
			filePath = filePath + filename;
			File txtFile = new File(filePath + ".txt");
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "GB18030"));
			bw.write(bf.toString());	
	    bw.flush();
	    bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//保存报文相关信息
		saveMessageInfo(jlrq, workDate, filename);
		return updateReturnBean;
	}

	public static int outPutFile(String jlrq,String sflmzh, String zh,StringBuilder bf,List<LMCKXXB> lmckxxbList, List<KXXB> kxxbList, List<AmsDszh> list) throws IOException {
		
		FSearchTool tool = null;
		
		Integer lmccount=0;
		Integer counts=0;
		AmsDszh amsDszh = new AmsDszh();
		KXXB kxxb = new KXXB();
		LMCKXXB lmckxxb = new LMCKXXB();
		
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		StringBuilder builder3 = new StringBuilder();
		StringBuilder builder4 = new StringBuilder();
		StringBuilder builder5 = new StringBuilder();
		StringBuilder builder6 = new StringBuilder();
		StringBuilder builder7 = new StringBuilder();
		StringBuilder builder8 = new StringBuilder();
		
		try {
			
			tool = new FSearchTool(lmckxxbList, "zh");
			List<Object> lmckxxb_temp = tool.searchTasks(zh);
			lmccount = lmckxxb_temp.size();
			
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
			
			amsDszh = (AmsDszh)session.get(AmsDszh.class, zh);
			
			//将对象中的属性值null替换为""
			nulltoNothing(amsDszh); 
			tool = new FSearchTool(kxxbList, "zh");
			List<Object> kxxb_list = tool.searchTasks(zh);
			int count = kxxb_list.size();
			
			
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
			if(lmccount>0){
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
	
	
	private void saveMessageInfo(String jlrq, String workDate, String filename) throws CommonException {
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		
		AmsDszhMessageInfo amsDszhMessageInfo = new AmsDszhMessageInfo();
		amsDszhMessageInfo.setMessagetype("集中账户报文");
		amsDszhMessageInfo.setDatadate(jlrq);
		amsDszhMessageInfo.setBorndate(workDate);
		amsDszhMessageInfo.setMessagestatus("0");
		amsDszhMessageInfo.setMessagename(filename + ".txt");
		rootDAO.save(amsDszhMessageInfo);
	}
}