<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="非居民账户查询修改">
<@CommonQueryMacro.CommonQuery id="FjmzhQueryUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top" id="basicInformation" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="基础信息" fieldStr="accountNumber,sClosedAccount,sDueDiligenceInd,sSelfSertification,iAccountBalance,sACC_currCode,sAccountHolderType,sOpeningFIName,sAccountType,sPaymentType,iPaymentAmnt,sPaymentAmntCurr,sResCountryCode,sIssuedBy,sinType,TIN,report_status" colNm=6/>
			</td>
		</tr>
		<tr valign="top" id="personal" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Button id= "btModGR"/><br/>
			</td>
		</tr>
		<tr valign="top" id="organization" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Button id= "btModJG"/><br/>
			</td>
		</tr>
		<tr valign="top" id="controlPerson" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Button id= "btModKZR"/><br/>
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
	//查看修改个人信息
	function btModGR_onClick(){
		var accountNumber = FjmzhQueryUpdate_dataset.getString("accountNumber");
		showWin("个人信息","${contextPath}/fpages/dataaudit/ftl/FjmzhQueryGR.ftl?griAccID="+accountNumber,null,null,window);
	}
	
	//查看修改机构信息
	function btModJG_onClick(){
		var accountNumber = FjmzhQueryUpdate_dataset.getString("accountNumber");
		showWin("机构信息","${contextPath}/fpages/dataaudit/ftl/FjmzhQueryJG.ftl?jgiAccID="+accountNumber,null,null,window);
	}
	
	//查看修改控制人信息
	function btModKZR_onClick(){
		var accountNumber = FjmzhQueryUpdate_dataset.getString("accountNumber");
		showWin("控制人信息","${contextPath}/fpages/dataaudit/ftl/FjmzhQueryKZR.ftl?kzriAccID="+accountNumber,null,null,window);
	}

	 
	 window.onload=function(){
	 	var sAccountHolderType = null;
		var actype = GetQueryString("sAccountHolderType");
		if(actype!=null) 
		{ 
			sAccountHolderType = decodeURIComponent(actype); 
			showOrHide(sAccountHolderType);
		}
	 }
	 
	 

	function GetQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r!=null) return (r[2]); 
		return null; 
	}

	  //显示或者隐藏
	  function showOrHide(sAccountHolderType){
	  
		if(sAccountHolderType == "CRS100"){
	    	$("tr[id=personal]").show();
	    	$("tr[id=controlPerson]").hide();
	    	$("tr[id=organization]").hide();
	    }
	    
	    if(sAccountHolderType == "CRS101"){
	        $("tr[id=personal]").hide();
	    	$("tr[id=organization]").show();
	    	$("tr[id=controlPerson]").show();
	    }
	    
	    if(sAccountHolderType == "CRS102" || sAccountHolderType == "CRS103"){
	    	$("tr[id=personal]").hide();
	    	$("tr[id=organization]").show();
	    	$("tr[id=controlPerson]").hide();
	    }
	  }

      function btSave_onClickCheck(button){
  		alert("保存成功");
		closeWin();
      }
	  
	  //根据不同的账户持有人类别显示不同的必填模块
	  function sAccountHolderType_DropDown_onSelect(dropDown,record, editor){
	  	var sAccountHolderType = record[0];
	 
	 	showOrHide(sAccountHolderType);
	  	
	  	return true;
	  }
	  
	 function btBack_onClickCheck()
	 {
		closeWin();
	 }
	 
	
</script>
</@CommonQueryMacro.page>
