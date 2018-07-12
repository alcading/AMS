<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="非居民账户查询修改">
<@CommonQueryMacro.CommonQuery id="FjmzhQueryUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top" id="basicInformation" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="基础信息" fieldStr="accountnumber,closedaccount,duediligenceind,selfcertification,accountbalance,accountholdertype,openingfiname,payment" colNm=6/>
			</td>
		</tr>
		<tr valign="top" id="personal" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="个人" fieldStr="ind_name,ind_gender,ind_address,ind_phoneno,ind_idtype,ind_idnumber,ind_rescountrycode,ind_tin,ind_explanation,ind_nationality,ind_birthinfo" colNm=6/>
			</td>
		</tr>
		<tr valign="top" id="organization" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="机构" fieldStr="org_name,org_address,org_phoneno,org_rescountrycode,org_tin,org_explanation" colNm=6/>
			</td>
		</tr>
		<tr valign="top" id="controlPerson" style="text-align:center;">
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


	 
	 
	 window.onload=function(){
	 	var accountholdertype = null;
		var actype = GetQueryString("accountholdertype");
		
		if(actype!=null) 
		{ 
			accountholdertype = decodeURIComponent(actype); 
			showOrHide(accountholdertype);
		}
	 }
	 
	 

	function GetQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r!=null) return (r[2]); 
		return null; 
	}

	  //显示或者隐藏
	  function showOrHide(accountholdertype){
		if(accountholdertype == "CRS100"){
	    	$("tr[id=personal]").show();
	    	$("tr[id=controlPerson]").show();
	    	$("tr[id=organization]").hide();
	    }
	    
	    if(accountholdertype == "CRS101" || accountholdertype == "CRS102"){
	        $("tr[id=personal]").hide();
	    	$("tr[id=organization]").show();
	    	$("tr[id=controlPerson]").show();
	    }
	    
	    if(accountholdertype == "CRS103"){
	    	$("tr[id=personal]").hide();
	    	$("tr[id=organization]").show();
	    	$("tr[id=controlPerson]").hide();
	    }
	  }

      function btSave_postSubmit(button){
	     fieldReadOnlyStatus(true);      
	     FjmzhQueryUpdate_dataset.flushData(FjmzhQueryUpdate_dataset.pageIndex);
	     //window.location.href = "${contextPath}/fpages/dataaudit/ftl/FjmzhQuery.ftl";
	  }
	  
	  //根据不同的账户持有人类别显示不同的必填模块
	  function accountholdertype_DropDown_onSelect(dropDown,record, editor){
	  	var accountholdertype = record[0];
	 
	 	showOrHide(accountholdertype);
	  	
	  	return true;
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
	    
	    var selfcertification = FjmzhQueryUpdate_dataset.getValue("selfcertification");
	    if(selfcertification == null || selfcertification == ""){
	    	alert("是否取得自证声明为必选项.");
			return false;
	    }
	    
	    var duediligenceind = FjmzhQueryUpdate_dataset.getValue("duediligenceind");
	    if(duediligenceind == null || duediligenceind == ""){
	    	alert("是否新开客户为必选项.");
			return false;
	    }
	    
	    var accountbalance = FjmzhQueryUpdate_dataset.getValue("accountbalance");
	    if(accountbalance == null || accountbalance == "" || accountbalance.length >22){
	    	alert("账户余额不能为空且最大长度为22.");
			return false;
	    }
	    
	    var accountholdertype = FjmzhQueryUpdate_dataset.getValue("accountholdertype");
	    if(accountholdertype == null || accountholdertype == ""){
	    	alert("账户持有人类别为必选项.");
			return false;
	    }
	    
	    var openingfiname = FjmzhQueryUpdate_dataset.getValue("openingfiname");
	    if(openingfiname == null || openingfiname == ""){
	    	alert("开户银行金融机构编码不能为空.");
			return false;
	    }
	    
	    if(accountholdertype == "CRS100"){
	    	var pers = true;
	    	var cont = true;
	    	pers = personal_Check();
	    	if(!pers) return false;
	    	cont = controlPerson_Check();
	    	if(!cont) return false;
	    }
	    
	    if(accountholdertype == "CRS101" || accountholdertype == "CRS102"){
	    	var orgn = true;
	    	var cont = true;
	    	orgn = organization_Check();
	    	if(!orgn) return false;
	    	cont = controlPerson_Check();
	    	if(!cont) return false;
	    }
	    
	    if(accountholdertype == "CRS103"){
	    	var orgn = true;
	    	orgn = organization_Check();
	    	if(!orgn) return false;
	    }
	        
	    alert("保存成功");
	  	closeWin();
	  }
	  //非居民个人信息输入数据验证
	  function personal_Check(){
	  	
	  	var ind_name = FjmzhQueryUpdate_dataset.getValue("ind_name");
	  	if(ind_name == null || ind_name == ""){
	    	alert("姓名不能为空.");
			return false;
	    }
	  	
	  	var ind_gender = FjmzhQueryUpdate_dataset.getValue("ind_gender");
	  	if(ind_gender == null || ind_gender == ""){
	  		alert("性别为必选项.");
			return false;
	  	}
	  	
	  	var ind_address = FjmzhQueryUpdate_dataset.getValue("ind_address");
	  	if(ind_address == null || ind_address == ""){
	  		alert("地址不能为空.");
			return false;
	  	}
	  	
	  	var ind_phoneno = FjmzhQueryUpdate_dataset.getValue("ind_phoneno");
	  	if(ind_phoneno == null || ind_phoneno == "" || ind_phoneno.length > 20){
	  		alert("联系电话不能为空且最大长度为20.");
			return false;
	  	}
	  	
	  	var ind_idtype = FjmzhQueryUpdate_dataset.getValue("ind_idtype");
	  	if(ind_idtype == null || ind_idtype == ""){
	  		alert("身份证件类型为必选项.");
			return false;
	  	}
	  	
	  	var ind_idnumber = FjmzhQueryUpdate_dataset.getValue("ind_idnumber");
	  	if(ind_idnumber == null || ind_idnumber == ""){
	  		alert("身份证件号码不能为空.");
			return false;
	  	}
	  	
	  	var ind_rescountrycode = FjmzhQueryUpdate_dataset.getValue("ind_rescountrycode");
	  	if(ind_rescountrycode == null || ind_rescountrycode == ""){
	  		alert("税收居民国（地区）代码不能为空.");
			return false;
	  	}
	  	
	  	var ind_tin = FjmzhQueryUpdate_dataset.getValue("ind_tin");
	  	var ind_explanation = FjmzhQueryUpdate_dataset.getValue("ind_explanation");
	  	if(ind_tin == null || ind_tin == ""){
	  		if(ind_explanation == null || ind_explanation == ""){
	  			alert("纳税人识别号未填写则原因 必填.");
				return false;
	  		}
	  	}
	  	
	  	var ind_nationality = FjmzhQueryUpdate_dataset.getValue("ind_nationality");
	  	if(ind_nationality == null || ind_nationality == ""){
	  		alert("国籍不能为空.");
			return false;
	  	}
	  	
	  	var ind_birthinfo = FjmzhQueryUpdate_dataset.getValue("ind_birthinfo");
	  	if(ind_birthinfo == null || ind_birthinfo == ""){
	  		alert("出生信息不能为空.");
			return false;
	  	}
	  	return true;
	  }
	  //非居民机构信息输入数据验证
	  function organization_Check(){
	  
	  	var org_name = FjmzhQueryUpdate_dataset.getValue("org_name");
	  	if(org_name == null || org_name == ""){
	    	alert("机构名称不能为空.");
			return false;
	    }
	  	
	  	var org_address = FjmzhQueryUpdate_dataset.getValue("org_address");
	  	if(org_address == null || org_address == ""){
	  		alert("机构地址不能为空.");
			return false;
	  	}
	  	
	  	var org_phoneno = FjmzhQueryUpdate_dataset.getValue("org_phoneno");
	  	if(org_phoneno == null || org_phoneno == "" || org_phoneno.length > 20){
	  		alert("机构电话不能为空且最大长度为20.");
			return false;
	  	}
	  	
	  	var org_rescountrycode = FjmzhQueryUpdate_dataset.getValue("org_rescountrycode");
	  	if(org_rescountrycode == null || org_rescountrycode == ""){
	  		alert("税收居民国（地区）代码不能为空.");
			return false;
	  	}
	  	
	  	var org_tin = FjmzhQueryUpdate_dataset.getValue("org_tin");
	  	var org_explanation = FjmzhQueryUpdate_dataset.getValue("org_explanation");
	  	if(org_tin == null || org_tin == ""){
	  		if(org_explanation == null || org_explanation == ""){
	  			alert("纳税人识别号未填写则原因 必填.");
				return false;
	  		}
	  	}
	  	
	  	return true;
	  }
	  //非居民控制人信息输入数据验证
	  function controlPerson_Check(){
	  
	  	var con_name = FjmzhQueryUpdate_dataset.getValue("con_name");
	  	if(con_name == null || con_name == ""){
	    	alert("控制人姓名不能为空.");
			return false;
	    }
	  	
	  	var con_ctrlgpersontype = FjmzhQueryUpdate_dataset.getValue("con_ctrlgpersontype");
	  	if(con_ctrlgpersontype == null || con_ctrlgpersontype == ""){
	  		alert("控制人类型为必选项.");
			return false;
	  	}
	  	
	  	var con_nationality = FjmzhQueryUpdate_dataset.getValue("con_nationality");
	  	if(con_nationality == null || con_nationality == ""){
	  		alert("国籍不能为空.");
			return false;
	  	}
	  	
	  	var con_address = FjmzhQueryUpdate_dataset.getValue("con_address");
	  	if(con_address == null || con_address == ""){
	  		alert("地址不能为空.");
			return false;
	  	}
	  	
	  	var con_rescountrycode = FjmzhQueryUpdate_dataset.getValue("con_rescountrycode");
	  	if(con_rescountrycode == null || con_rescountrycode == ""){
	  		alert("税收居民国（地区）代码不能为空.");
			return false;
	  	}
	  	
	  	var con_tin = FjmzhQueryUpdate_dataset.getValue("con_tin");
	  	var con_explanation = FjmzhQueryUpdate_dataset.getValue("con_explanation");
	  	if(con_tin == null || con_tin == ""){
	  		if(con_explanation == null || con_explanation == ""){
	  			alert("纳税人识别号未填写则原因 必填.");
				return false;
	  		}
	  	}
	  	
	  	var con_birthinfo = FjmzhQueryUpdate_dataset.getValue("con_birthinfo");
	  	if(con_birthinfo == null || con_birthinfo == ""){
	  		alert("出生信息不能为空.");
			return false;
	  	}
	  	
	  	return true;
	  }
	  
	 function btBack_onClickCheck()
	 {
		window.location.href = "${contextPath}/fpages/dataaudit/ftl/FjmzhQuery.ftl";
		closeWin();
	 }
	
</script>
</@CommonQueryMacro.page>
