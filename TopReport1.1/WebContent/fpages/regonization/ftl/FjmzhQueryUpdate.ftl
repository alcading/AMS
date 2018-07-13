<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="非居民账户查询修改">
<@CommonQueryMacro.CommonQuery id="FjmzhQueryUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top" id="basicInformation" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="基础信息" fieldStr="accountNumber,sClosedAccount,sDueDiligenceInd,sSelfSertification,iAccountBalance,sACC_currCode,sAccountHolderType,sOpeningFIName,sAccountType,sPaymentType,iPaymentAmnt,sPaymentAmntCurr,sResCountryCode" colNm=6/>
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
      	var accountNumber = FjmzhQueryUpdate_dataset.getValue("accountNumber");
	 	if(accountNumber == null || accountNumber == ""){
	    	alert("账号为必填项且最大长度为32！");
			return false;
	    }
	    var sClosedAccount = FjmzhQueryUpdate_dataset.getValue("sClosedAccount");
	 	if(sClosedAccount == null || sClosedAccount == ""){
	    	alert("账户状态为必选项！");
			return false;
	    }
	    var sDueDiligenceInd = FjmzhQueryUpdate_dataset.getValue("sDueDiligenceInd");
	 	if(sDueDiligenceInd == null || sDueDiligenceInd == ""){
	    	alert("是否新开客户为必选项！");
			return false;
	    }
	    var sSelfSertification = FjmzhQueryUpdate_dataset.getValue("sSelfSertification");
	 	if(sSelfSertification == null || sSelfSertification == ""){
	    	alert("是否取得自证声明为必选项！");
			return false;
	    }
	    var iAccountBalance = FjmzhQueryUpdate_dataset.getValue("iAccountBalance");
	 	if(iAccountBalance == null || iAccountBalance == ""){
	    	alert("账户余额为必填项！");
			return false;
	    }
	    var sACC_currCode = FjmzhQueryUpdate_dataset.getValue("sACC_currCode");
	 	if(sACC_currCode == null || sACC_currCode == ""){
	    	alert("指定货币代码为必选项！");
			return false;
	    }
	    var AHType = FjmzhQueryUpdate_dataset.getValue("sAccountHolderType");
	 	if(AHType == null || AHType == ""){
	    	alert("账户持有人类别为必选项！");
			return false;
	    }
	    var sOpeningFIName = FjmzhQueryUpdate_dataset.getValue("sOpeningFIName");
	 	if(sOpeningFIName == null || sOpeningFIName == ""){
	    	alert("开户行金融机构编码为必填项！");
			return false;
	    }
	 	var sAccountType = FjmzhQueryUpdate_dataset.getValue("sAccountType");
	 	if(sAccountType == null || sAccountType == ""){
	    	alert("账户数据类型为必选项！");
			return false;
	    }
	 	var sResCountryCode = FjmzhQueryUpdate_dataset.getValue("sResCountryCode");
	 	if(sResCountryCode == null || sResCountryCode == ""){
	    	alert("税收居民国（地区）代码为必填项！");
			return false;
	    }
	    
	    var iPaymentAmnt = FjmzhQueryUpdate_dataset.getValue("iPaymentAmnt");
	    var sPaymentAmntCurr = FjmzhQueryUpdate_dataset.getValue("sPaymentAmntCurr");
	    if(iPaymentAmnt != null && iPaymentAmnt != ""){
	    	if(sPaymentAmntCurr == null || sPaymentAmntCurr == ""){
	    		alert("账户收入金额 不为空时 账户收入币种 必须填写！");
				return false;
	    	}
	    }
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
