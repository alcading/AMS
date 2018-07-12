package com.huateng.report.tool;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.huateng.ebank.business.common.SystemConstant;
import com.huateng.ebank.framework.exceptions.CommonException;

import resource.report.dao.ROOTDAO;
import resource.report.dao.ROOTDAOUtils;
import resources.east.data.pub.AmsFjmzhDZ;
import resources.east.data.pub.AmsFjmzhMessageInfo;

public class FjmzhUtil {
	
    public static final String AC_0 = "CRS100";
    public static final String AC_1 = "CRS101";
    public static final String AC_2 = "CRS102";
    public static final String AC_3 = "CRS103";
    public static final String MESSAGETYPEINDIC_1 = "CRS701";
    public static final String MESSAGETYPEINDIC_2 = "CRS702";
    public static final String MESSAGETYPEINDIC_3 = "CRS703";
    public static final String MESSAGEHEADER = "CRS";
    public static final String PAYMENTTYPE = "CRS502";
    public static final String CAMS = "cams01300101";
    public static final String INTYPE = "TIN";
    public static final String MESSAGETYPE = "非居民涉税账户报文";
    public static final String MESSAGEINFO="  报文名称:";
	
    public static void saveMessageInfo(String MessageName) throws CommonException{
		ROOTDAO rootDAO = ROOTDAOUtils.getROOTDAO();
		AmsFjmzhMessageInfo messageinfo = new AmsFjmzhMessageInfo();
		messageinfo.setMessageName(MessageName);
		messageinfo.setMessageType(MESSAGETYPE);
		messageinfo.setImportDate(getyymmdd(new Date()));
		rootDAO.save(messageinfo);
	}
    
    /**
     * 返回yyyy-MM-dd格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(String date) {
    	String year = date.substring(0, 4);
    	String month = date.substring(4, 6);
    	String day = date.substring(6);
    	date = year+"-"+month+"-"+day;
        return date;
    }
    
    /**
     * 返回yyyyMMdd格式的字符串
     *
     * @param date
     * @return
     */
    public static String getyymmdd(Date date) {
    	SimpleDateFormat nyr = new SimpleDateFormat("yyyyMMdd");
 	    String date1 = nyr.format(date);
        return date1;
    }
    
    /**
     * 判断字符串是否为空，为空返回false
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
    	boolean a = false;
    	if(str!=null&&str.length()>0){
    		a=true;
    	}
    	return a;
    }
    
    /**
     * 判断中文地址信息是否全部为空，全部为空返回false
     * @param fjmdz
     * @return
     */
    public static boolean confirmationAddressCN(AmsFjmzhDZ fjmdz){
    	boolean a = false;
    	int i = 0;
    	if(fjmdz.getsAddressCN_sAddressFreeCN()==null||fjmdz.getsAddressCN_sAddressFreeCN().length()<1){i++;}
    	if(fjmdz.getsAddressFixCN_CityCN()==null||fjmdz.getsAddressFixCN_CityCN().length()<1){i++;}
    	if(fjmdz.getsAddressFixCN_Province()==null||fjmdz.getsAddressFixCN_Province().length()<1){i++;}
    	if(fjmdz.getsAddressFixCN_PostCode()==null||fjmdz.getsAddressFixCN_PostCode().length()<1){i++;}
    	if(fjmdz.getsAddressFixCN_DistrictName()==null||fjmdz.getsAddressFixCN_DistrictName().length()<1){i++;}
    	if(i<5){a=true;}
    	return a;
    }
    
    /**
     * 判断中文地址信息详情是否全部为空，全部为空返回false
     * @param fjmdz
     * @return
     */
    public static boolean confirmationAddressFixCN(AmsFjmzhDZ fjmdz){
    	boolean a = false;
    	int i = 0;
    	if(fjmdz.getsAddressFixCN_CityCN()==null||fjmdz.getsAddressFixCN_CityCN().length()<1){i++;}
    	if(fjmdz.getsAddressFixCN_Province()==null||fjmdz.getsAddressFixCN_Province().length()<1){i++;}
    	if(fjmdz.getsAddressFixCN_PostCode()==null||fjmdz.getsAddressFixCN_PostCode().length()<1){i++;}
    	if(fjmdz.getsAddressFixCN_DistrictName()==null||fjmdz.getsAddressFixCN_DistrictName().length()<1){i++;}
    	if(i<4){a=true;}
    	return a;
    }
    
    /**
     * 拼装DocRefId字段
     * @param str
     * @author caozhi
     * @return
     */
    public static String getDocRefId(String str){
		String year = getYearLast();
		year=year.substring(0, 4);
        String randomNumber = getRandomNumberToString(9);
    	str = "CN"+year+str+randomNumber;
    	return str;
    }
    
    /**
     * 生成一个随机数字符串
     * @param y
     * @author caozhi
     * @return
     */
    public static String getRandomNumberToString(int y){
		Random random = new Random();
		String result="";
		for (int i=0;i<y;i++)
		{
			result+=random.nextInt(10);
		}
    	return result;
    }
    
    /**
     * 将BigDecimal类型的数据转换成保留两位小数的字符串
     * @param money
     * @author caozhi
     * @return
     */
    public static String getBigDecimalToString(BigDecimal money){
		//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入) 
    	money=money.setScale(2, BigDecimal.ROUND_HALF_UP); 
		//转化为字符串输出 
		String OutString=money.toString(); 
    	return OutString;
    }
    
	/**
	 * 拼装报文头中的messageRefId字段
	 * @param numStr
	 * @param jrjgbm
	 * @param datatype
	 * @author caozhi
	 * @return
	 */
	public static String getMessageRefId(String numStr,String jrjgbm,String datatype){
		String messageRefId = "";
		Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(new Date());
        int t = calendar.get(Calendar.YEAR);
        String year = String.valueOf(t);
		messageRefId = "RE"+jrjgbm+year+datatype+numStr;
		return messageRefId;
	}
	
	/** 
     * 获取前一年最后一天日期 
     * @param year 年份 
     * @author caozhi
     * @return Date 
     */ 

    public static String getYearLast(){ 
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(new Date());
        //int year = calendar.get(Calendar.YEAR);
        calendar.add(calendar.YEAR, -1);
        int year = calendar.get(Calendar.YEAR);
        calendar.clear(); 
        calendar.set(Calendar.YEAR, year); 
        calendar.roll(Calendar.DAY_OF_YEAR, -1); 
        Date currYearLast = calendar.getTime();
        String currYearLastStr = new java.sql.Date(currYearLast.getTime()).toString();
        return currYearLastStr; 

    }
    
    /**
     * 获取当前时间且格式符合(yyyy-MM-ddTHH:mm:ss)
     * @return
     * @author caozhi
     */
    public static String getTimeShort() {
       Date date = new Date();
	   SimpleDateFormat nyr = new SimpleDateFormat("yyyy-MM-dd");
	   String date1 = nyr.format(date);
	   SimpleDateFormat sfm = new SimpleDateFormat("HH:mm:ss");
	   String time = sfm.format(date);
	   String dateString = date1+"T"+time;
	   return dateString;
    }
    
    /**
     * 字符串格式化长度不足8位左补0
     * @param str
     * @param strLength
     * @author caozhi
     * @return
     */
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }
    
	/**
	 * 将报送批次序列累加1
	 * @param fjmrb
	 * @author caozhi
	 * @return
	 */
    public static String updaterb(String fjmrb){
        int d = Integer.parseInt(fjmrb);
        fjmrb = ""+(d+1);
    	return fjmrb;
    }


}

