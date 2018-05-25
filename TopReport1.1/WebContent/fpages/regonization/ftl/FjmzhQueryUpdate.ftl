<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="非居民账户查询修改">
<@CommonQueryMacro.CommonQuery id="FjmzhQueryUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="基础信息" fieldStr="accountnumber,closedaccount,duediligenceind,selfcertification,accountbalance,accountholdertype,openingfiname,payment" colNm=6/>
			</td>
		</tr>
		<tr valign="top" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="个人" fieldStr="ind_name,ind_gender,ind_address,ind_phoneno,ind_idtype,ind_idnumber,ind_rescountrycode,ind_tin,ind_explanation,ind_nationality,ind_birthinfo" colNm=6/>
			</td>
		</tr>
		<tr valign="top" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="机构" fieldStr="org_name,org_address,org_phoneno,org_rescountrycode,org_tin,org_explanation" colNm=6/>
			</td>
		</tr>
		<tr valign="top" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="控制人" fieldStr="con_name,con_ctrlgpersontype,con_nationality,con_address,con_rescountrycode,con_tin,con_explanation,con_birthinfo" colNm=6/>
			</td>
		</tr>
		<tr>
  		   	<td valign="CENTER">
				<left><@CommonQueryMacro.Button id= "btBack"/></left>&nbsp;
				<left><@CommonQueryMacro.Button id= "btSave"/></left>
  			</td>
		</tr> 
	</table>
</@CommonQueryMacro.CommonQuery>


<script language="javascript">


     function btSave_postSubmit(button){
	     fieldReadOnlyStatus(true);      
	     FjmzhQueryUpdate_dataset.flushData(FjmzhQueryUpdate_dataset.pageIndex);
	     //window.location.href = "${contextPath}/fpages/dataaudit/ftl/FjmzhQuery.ftl";
	  }
	  
	  //非空校验
	  function btSave_onClickCheck(button) {
	    var accountnumber = FjmzhQueryUpdate_dataset.getValue("accountnumber");
	    if(accountnumber == null || accountnumber == "" || accountnumber.length > 32){
	    	alert("账号不能为空且最大长度为32.");
			return false;
	    }
	    
	    var closedaccount = FjmzhQueryUpdate_dataset.getValue("closedaccount");
	    if(closedaccount == null || closedaccount == ""){
	    	alert("账号是否注销为必选项.");
			return false;
	    }
	    
	    var duediligenceind = FjmzhQueryUpdate_dataset.getValue("duediligenceind");
	    if(duediligenceind == null || duediligenceind == ""){
	    	alert("是否新开账户为必选项.");
			return false;
	    }
	        
	  	closeWin();
	  	return true;
	  }
	  
	 function btBack_onClickCheck()
	 {
		closeWin();
		window.location.href = "${contextPath}/fpages/dataaudit/ftl/FjmzhQuery.ftl";
	 }
	
</script>
</@CommonQueryMacro.page>
