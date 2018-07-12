package com.huateng.report.imports.operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.report.imports.service.DszhFeedbackService;

import resources.east.data.pub.AmsDszhFeedback;

public class DszhFeedbackOperation {
	/**
	 * 
	 * @param fileName 上传文件名
	 * @throws ParseException 
	 * @throws CommonException 
	 */
	public void saveFeedbackFile_log(String path, String fileName) throws Exception {
		Date date = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String inputDate = df.format(date);
//		date = df.parse(inputDate);
		java.sql.Timestamp date_time = new java.sql.Timestamp(date.getTime());
		
		
		String feedback_type = fileName.substring((fileName.length() - 6), (fileName.length() - 4));
		String feedback_type_r = null;
		if(feedback_type.equals("rf")) {
			feedback_type_r = "0";
		}else if(feedback_type.equals("rl")) {
			feedback_type_r = "1";
		}
		
		/*
		 * 读取上传文件获得失败成功条数
		 */
		DszhFeedbackService dszhFeedbackService = DszhFeedbackService.getInstance();
		String readStr = dszhFeedbackService.readFeedback(path, fileName);
		String [] Temp = readStr.split("\\|");
		int success_num = Integer.valueOf(Temp[0]);
		int fail_num = Integer.valueOf(Temp[1]);
		
		AmsDszhFeedback amsDszhFeedback = new AmsDszhFeedback();
		amsDszhFeedback.setDate(date_time); //或者将date_time换成new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())
		amsDszhFeedback.setFile_name(fileName);
		amsDszhFeedback.setFeedback_type(feedback_type_r);
		amsDszhFeedback.setSuccess_num(success_num);
		amsDszhFeedback.setFail_num(fail_num);
		
		/*
		 * 读取账号以及错误校验代码并保存
		 */
		dszhFeedbackService.readFeedback_data(path, fileName);
		
		
		dszhFeedbackService.saveFeedback(amsDszhFeedback);
		
	}

}
