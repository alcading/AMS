<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="非居民账户新增">
<@CommonQueryMacro.CommonQuery id="FjmzhAdd" init="false" submitMode="current" navigate="false">
	<table align="center" width="100%">
		<tr valign="top" id="basicInformation" style="text-align:center;">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="基础信息" fieldStr="accountNumber,sClosedAccount,sDueDiligenceInd,sSelfSertification,iAccountBalance,sACC_currCode,sAccountHolderType,sOpeningFIName,sAccountType,sPaymentType,iPaymentAmnt,sPaymentAmntCurr,sResCountryCode" colNm=6/>
			</td>
		</tr>
		<tr valign="top" id="personal" style="text-align:center;display:none;">
			<td valign="top" align="center">
				<@CommonQueryMacro.Button id= "btAdd"/><br/>
			</td>
		</tr>
		<tr valign="top" id="organization" style="text-align:center;display:none;">
			<td valign="top">
				<@CommonQueryMacro.Button id= "btAdd1"/><br/>
			</td>
		</tr>
		<tr valign="top" id="controlPerson" style="text-align:center;display:none;">
			<td valign="top">
				<@CommonQueryMacro.Button id= "btAdd2"/>
			</td>
		</tr>
		<tr>
			<td>
				<@CommonQueryMacro.FloatWindow id="signWindowGR" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
	      			<div align="center">
	      				<@CommonQueryMacro.Group id="group1" label="个人信息新增"
	        			  fieldStr="griAccID,grGender,grPhoneNo,grIDType,grIDNumber,grBirthDate,grBirthCity,grBirthCountryCode,grFormerCountryName,grIssuedBy,grTIN,grExplanation,grNationality" colNm=4/>
	        			 </br>
	      				<@CommonQueryMacro.Button id= "btSaveGR"/>&nbsp;&nbsp;
	      				<@CommonQueryMacro.Button id= "btAddGRXM"/>&nbsp;&nbsp;
	      				<@CommonQueryMacro.Button id= "btAddGRDZ"/>
	      			</div>
	     		 </@CommonQueryMacro.FloatWindow>
			</td>
		</tr>
		<tr>
			<td>
				<@CommonQueryMacro.FloatWindow id="signWindowJG" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
	      			<div align="center">
	      				<@CommonQueryMacro.Group id="group1" label="机构信息新增"
	        			  fieldStr="jgiAccID,jgPhoneNo,jgNameType,jgOrganisationNameEN,jgOrganisationNameCN,jgIssuedBy,jgTIN,jgExplanation" colNm=4/>
	        			 </br>
	      				<@CommonQueryMacro.Button id= "btSaveJG"/>&nbsp;&nbsp;
	      				<@CommonQueryMacro.Button id= "btAddJGDZ"/>
	      			</div>
	     		 </@CommonQueryMacro.FloatWindow>
			</td>
		</tr>
		<tr>
			<td>
				<@CommonQueryMacro.FloatWindow id="signWindowKZR" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
	      			<div align="center">
	      				<@CommonQueryMacro.Group id="group1" label="控制人信息新增"
	        			  fieldStr="kzriAccID,kzrCtrlgPersonType,kzrdBirthDate,kzrBirthCity,kzrBirthCountryCode,kzrFormerCountryName,kzrNationality,kzrResCountryCode,kzrIssuedBy,kzrTIN,kzrExplanation" colNm=4/>
	        			 </br>
	      				<@CommonQueryMacro.Button id= "btSaveKZR"/>&nbsp;&nbsp;
	      				<@CommonQueryMacro.Button id= "btAddKXM"/>&nbsp;&nbsp;
	      				<@CommonQueryMacro.Button id= "btAddKDZ"/>
	      			</div>
	     		 </@CommonQueryMacro.FloatWindow>
			</td>
		</tr>
		<tr>
			<td>
				<@CommonQueryMacro.FloatWindow id="signWindowDZ" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
	      			<div align="center">
	      				<@CommonQueryMacro.Group id="group1" label="地址信息新增"
	        			  fieldStr="diAccID,slegalAddressType,sCountryCode,sAddressEN_AddressFreeEN,sAddressFixEN_CityEN,sAddressFixEN_Street,sAddressFixEN_BuildingIden,sAddressFixEN_SuiteIdentifier,sAddressFixEN_FloorIdentifier,sAddressFixEN_DistrictName,sAddressFixEN_POB,sAddressFixEN_PostCode,sAddressFixEN_CountrySubentity,sAddressCN_sAddressFreeCN,sAddressFixCN_Province,sAddressFixCN_CityCN,sAddressFixCN_DistrictName,sAddressFixCN_PostCode,dStyle" colNm=4/>
	        			 </br>
	      				<@CommonQueryMacro.Button id= "btSaveDZ"/>
	      			</div>
	     		 </@CommonQueryMacro.FloatWindow>
			</td>
		</tr>
		<tr>
			<td>
				<@CommonQueryMacro.FloatWindow id="signWindowXM" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
	      			<div align="center">
	      				<@CommonQueryMacro.Group id="group1" label="姓名信息新增"
	        			  fieldStr="xiAccID,xNameType,xLastName,xMiddleName,xFirstName,xNameCN,xPrecedingTitle,xTitle,xNamePrefix,xGenerationIdentifier,xSuffix,xGeneralSuffix,xStyle" colNm=4/>
	        			 </br>
	      				<@CommonQueryMacro.Button id= "btSaveXM"/>
	      			</div>
	     		 </@CommonQueryMacro.FloatWindow>
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
	  var gflag = false;
	  var jflag = false;
	  var kflag = false;
	  var gxflag = false;
	  var gdflag = false;
	  var jdflag = false;
	  var kxflag = false;
	  var kdflag = false;
   
   	  //根据不同的账户持有人类别显示不同的必填模块
	  function sAccountHolderType_DropDown_onSelect(dropDown,record, editor){
	  	var accountholdertype = record[0];
	 
	 	showOrHide(accountholdertype);
	  	
	  	return true;
	  }
   
   	  //显示或者隐藏
	  function showOrHide(accountholdertype){
		if(accountholdertype == "CRS100"){
	    	$("tr[id=personal]").show();
	    	$("tr[id=controlPerson]").hide();
	    	$("tr[id=organization]").hide();
	    }
	    
	    if(accountholdertype == "CRS101"){
	        $("tr[id=personal]").hide();
	    	$("tr[id=organization]").show();
	    	$("tr[id=controlPerson]").show();
	    }
	    
	    if(accountholdertype == "CRS103" || accountholdertype == "CRS102"){
	    	$("tr[id=personal]").hide();
	    	$("tr[id=organization]").show();
	    	$("tr[id=controlPerson]").hide();
	    }
	  }  
	  
	 function btBack_onClick()
	 {
		window.location.href = "${contextPath}/fpages/dataaudit/ftl/FjmzhQuery.ftl";
	 }
	 
	 //打开个人信息添加弹窗
	 function btAdd_onClick()
	 {
	 	var accountNumber = FjmzhAdd_dataset.getValue("accountNumber");
	    if(accountNumber == null || accountNumber == "" || accountNumber.length > 32){
	    	alert("账号不能为空且最大长度为32.");
			return false;
	    }
	    var grGender = "M";
	    FjmzhAdd_dataset.setValue("grGender", grGender);
	    FjmzhAdd_dataset.setValue("griAccID", accountNumber);
	 	subwindow_signWindowGR.show();
	 }
	 
	 //打开个人姓名信息添加弹窗
	 function btAddGRXM_onClick()
	 {
	 	var accountNumber = FjmzhAdd_dataset.getValue("accountNumber");
	    FjmzhAdd_dataset.setValue("xStyle", "01");
	    FjmzhAdd_dataset.setValue("xiAccID", accountNumber);
	    FjmzhAdd_dataset.setFieldReadOnly("xStyle",true);
	    //打开时初始化界面
	    clear_subwindow_signWindowXM();
	 	subwindow_signWindowXM.show();
	 }
	 
	 //打开个人地址信息添加弹窗
	 function btAddGRDZ_onClick()
	 {
	 	var accountNumber = FjmzhAdd_dataset.getValue("accountNumber");
	    var dStyle = "01";
	    FjmzhAdd_dataset.setValue("dStyle", dStyle);
	    FjmzhAdd_dataset.setValue("diAccID", accountNumber);
	    clear_subwindow_signWindowDZ();
	 	subwindow_signWindowDZ.show();
	 }
	 
	 //打开机构信息添加弹窗
	 function btAdd1_onClick()
	 {
	 	var accountNumber = FjmzhAdd_dataset.getValue("accountNumber");
	 	if(accountNumber == null || accountNumber == "" || accountNumber.length > 32){
	    	alert("账号不能为空且最大长度为32.");
			return false;
	    }
	    FjmzhAdd_dataset.setValue("jgiAccID", accountNumber);
	 	subwindow_signWindowJG.show();
	 }
	 
	 //打开机构地址信息添加弹窗
	 function btAddJGDZ_onClick()
	 {
	 	var accountNumber = FjmzhAdd_dataset.getValue("accountNumber");
	    var dStyle = "02";
	    FjmzhAdd_dataset.setValue("dStyle", dStyle);
	    FjmzhAdd_dataset.setValue("diAccID", accountNumber);
	    clear_subwindow_signWindowDZ();
	 	subwindow_signWindowDZ.show();
	 }
	 
	 //打开控制人信息添加弹窗
	 function btAdd2_onClick()
	 {
	 	var accountNumber = FjmzhAdd_dataset.getValue("accountNumber");
	 	if(accountNumber == null || accountNumber == "" || accountNumber.length > 32){
	    	alert("账号不能为空且最大长度为32.");
			return false;
	    }
	    
	    FjmzhAdd_dataset.setValue("kzriAccID", accountNumber);
	 	subwindow_signWindowKZR.show();
	 }
	 
	 //打开控制人姓名信息添加弹窗
	 function btAddKXM_onClick()
	 {
	 	var accountNumber = FjmzhAdd_dataset.getValue("accountNumber");
	    var xStyle = "02";
	    FjmzhAdd_dataset.setValue("xStyle", xStyle);
	    FjmzhAdd_dataset.setValue("xiAccID", accountNumber);
	    FjmzhAdd_dataset.setFieldReadOnly("xStyle",true);
	    clear_subwindow_signWindowXM();
	 	subwindow_signWindowXM.show();
	 }
	 
	 //打开控制人地址信息添加弹窗
	 function btAddKDZ_onClick()
	 {
	 	var accountNumber = FjmzhAdd_dataset.getValue("accountNumber");
	    var dStyle = "03";
	    FjmzhAdd_dataset.setValue("dStyle", dStyle);
	    FjmzhAdd_dataset.setValue("diAccID", accountNumber);
	    clear_subwindow_signWindowDZ();
	 	subwindow_signWindowDZ.show();
	 }
	 
	 //姓名弹窗初始化
	 function clear_subwindow_signWindowXM(){
	    FjmzhAdd_dataset.setValue("xNameType","");
	    FjmzhAdd_dataset.setValue("xLastName","");
	    FjmzhAdd_dataset.setValue("xMiddleName","");
	    FjmzhAdd_dataset.setValue("xFirstName","");
	    FjmzhAdd_dataset.setValue("xNameCN","");
	    FjmzhAdd_dataset.setValue("xPrecedingTitle","");
	    FjmzhAdd_dataset.setValue("xTitle","");
	    FjmzhAdd_dataset.setValue("xNamePrefix","");
	    FjmzhAdd_dataset.setValue("xGenerationIdentifier","");
	    FjmzhAdd_dataset.setValue("xSuffix","");
	    FjmzhAdd_dataset.setValue("xGeneralSuffix","");
	 }
	 
	 //地址弹窗初始化
	 function clear_subwindow_signWindowDZ(){
	    FjmzhAdd_dataset.setValue("slegalAddressType","OECD301");
	    FjmzhAdd_dataset.setValue("sCountryCode","");
	    FjmzhAdd_dataset.setValue("sAddressEN_AddressFreeEN","");
	    FjmzhAdd_dataset.setValue("sAddressFixEN_CityEN","");
	    FjmzhAdd_dataset.setValue("sAddressFixEN_Street","");
	    FjmzhAdd_dataset.setValue("sAddressFixEN_BuildingIden","");
	    FjmzhAdd_dataset.setValue("sAddressFixEN_SuiteIdentifier","");
	    FjmzhAdd_dataset.setValue("sAddressFixEN_FloorIdentifier","");
	    FjmzhAdd_dataset.setValue("sAddressFixEN_DistrictName","");
	    FjmzhAdd_dataset.setValue("sAddressFixEN_POB","");
	    FjmzhAdd_dataset.setValue("sAddressFixEN_PostCode","");
	   	FjmzhAdd_dataset.setValue("sAddressFixEN_CountrySubentity","");
	    FjmzhAdd_dataset.setValue("sAddressCN_sAddressFreeCN","");
	    FjmzhAdd_dataset.setValue("sAddressFixCN_Province","");
	    FjmzhAdd_dataset.setValue("sAddressFixCN_CityCN","");
	    FjmzhAdd_dataset.setValue("sAddressFixCN_DistrictName","");
	    FjmzhAdd_dataset.setValue("sAddressFixCN_PostCode","");
	 }
	 
	 //基础信息数据验证及控制
	 function btSave_onClickCheck(button){
	 	var accountNumber = FjmzhAdd_dataset.getValue("accountNumber");
	 	if(accountNumber == null || accountNumber == ""){
	    	alert("账号为必填项且最大长度为32！");
			return false;
	    }
	    var sClosedAccount = FjmzhAdd_dataset.getValue("sClosedAccount");
	 	if(sClosedAccount == null || sClosedAccount == ""){
	    	alert("账户状态为必选项！");
			return false;
	    }
	    var sDueDiligenceInd = FjmzhAdd_dataset.getValue("sDueDiligenceInd");
	 	if(sDueDiligenceInd == null || sDueDiligenceInd == ""){
	    	alert("是否新开客户为必选项！");
			return false;
	    }
	    var sSelfSertification = FjmzhAdd_dataset.getValue("sSelfSertification");
	 	if(sSelfSertification == null || sSelfSertification == ""){
	    	alert("是否取得自证声明为必选项！");
			return false;
	    }
	    var iAccountBalance = FjmzhAdd_dataset.getValue("iAccountBalance");
	 	if(iAccountBalance == null || iAccountBalance == ""){
	    	alert("账户余额为必填项！");
			return false;
	    }
	    var sACC_currCode = FjmzhAdd_dataset.getValue("sACC_currCode");
	 	if(sACC_currCode == null || sACC_currCode == ""){
	    	alert("指定货币代码为必选项！");
			return false;
	    }
	    var AHType = FjmzhAdd_dataset.getValue("sAccountHolderType");
	 	if(AHType == null || AHType == ""){
	    	alert("账户持有人类别为必选项！");
			return false;
	    }
	    var sOpeningFIName = FjmzhAdd_dataset.getValue("sOpeningFIName");
	 	if(sOpeningFIName == null || sOpeningFIName == ""){
	    	alert("开户行金融机构编码为必填项！");
			return false;
	    }
	 	var sAccountType = FjmzhAdd_dataset.getValue("sAccountType");
	 	if(sAccountType == null || sAccountType == ""){
	    	alert("账户数据类型为必选项！");
			return false;
	    }
	 	var sResCountryCode = FjmzhAdd_dataset.getValue("sResCountryCode");
	 	if(sResCountryCode == null || sResCountryCode == ""){
	    	alert("税收居民国（地区）代码为必填项！");
			return false;
	    }
	    
	    var iPaymentAmnt = FjmzhAdd_dataset.getValue("iPaymentAmnt");
	    var sPaymentAmntCurr = FjmzhAdd_dataset.getValue("sPaymentAmntCurr");
	    if(iPaymentAmnt != null && iPaymentAmnt != ""){
	    	if(sPaymentAmntCurr == null || sPaymentAmntCurr == ""){
	    		alert("账户收入金额 不为空时 账户收入币种 必须填写！");
				return false;
	    	}
	    }
	    
	    if(AHType == "CRS100"){
	    	if(!gflag){
		 		alert("请先填写个人信息！");
		 		return false;
	 		}
	    }
	    
	    if(AHType == "CRS101"){
	        if(!jflag){
		 		alert("请先填写机构信息！");
		 		return false;
		 	}
		 	if(!kflag){
		 		alert("请先填写控制人信息！");
		 		return false;
		 	}
	    }
	    
	    if(AHType == "CRS103" || AHType == "CRS102"){
	    	if(!jflag){
		 		alert("请先填写机构信息！");
		 		return false;
		 	}
	    }
	 }
	 //个人信息验证及控制
	 function btSaveGR_onClickCheck(button){
	 	if(!gxflag){
	 		alert("请先填写个人姓名信息！");
	 		return false;
	 	}
	 	if(!gdflag){
	 		alert("请先填写个人地址信息！");
	 		return false;
	 	}
	 	var grGender = FjmzhAdd_dataset.getValue("grGender");
	 	if(grGender == null || grGender == ""){
	    	alert("性别 不能为空！");
			return false;
	    }
	    var grBirthDate = FjmzhAdd_dataset.getValue("grBirthDate");
	 	if(grBirthDate == null || grBirthDate == ""){
	    	alert("出生日期不能为空！");
			return false;
	    }
	    var grBirthCountryCode = FjmzhAdd_dataset.getValue("grBirthCountryCode");
	    var grFormerCountryName = FjmzhAdd_dataset.getValue("grFormerCountryName");
	 	if(grBirthCountryCode == null || grBirthCountryCode == ""){
	 		if(grFormerCountryName == null || grFormerCountryName == ""){
		    	alert("出生国代码和出生国名称(英文)必须填写一项，不能同时为空！");
				return false;
			}	
	    }
	    var grTIN = FjmzhAdd_dataset.getValue("grTIN");
	    var grIssuedBy = FjmzhAdd_dataset.getValue("grIssuedBy");
	    var grExplanation = FjmzhAdd_dataset.getValue("grExplanation");
	 	if(grTIN == null || grTIN == ""){
	 		if(grExplanation == null || grExplanation == ""){
		    	alert("纳税人识别号和未取得纳税人识别号说明必须填写一项，不能同时为空！");
				return false;
			}	
	    }
	    if(grTIN != null && grTIN != ""){
	    	if(grIssuedBy == null || grIssuedBy == ""){
	    		alert("纳税人识别号 不为空时 发放识别号的国家（地区）代码 必须填写！");
				return false;
	    	}
	    }
	    var grNationality = FjmzhAdd_dataset.getValue("grNationality");
	 	if(grNationality == null || grNationality == ""){
	    	alert("国籍 不能为空！");
			return false;
	    }
	 }
	 //机构信息验证及控制
	 function btSaveJG_onClickCheck(button){
	 	if(!jdflag){
	 		alert("请先填写机构地址信息！");
	 		return false;
	 	}
	 	var jgOrganisationNameEN = FjmzhAdd_dataset.getValue("jgOrganisationNameEN");
	 	if(jgOrganisationNameEN == null || jgOrganisationNameEN == ""){
	    	alert("英文名称 不能为空！");
			return false;
	    }
	    var jgTIN = FjmzhAdd_dataset.getValue("jgTIN");
	    var jgIssuedBy = FjmzhAdd_dataset.getValue("jgIssuedBy");
	    var jgExplanation = FjmzhAdd_dataset.getValue("jgExplanation");
	 	if(jgTIN == null || jgTIN == ""){
	 		if(jgExplanation == null || jgExplanation == ""){
		    	alert("纳税人识别号和未取得纳税人识别号说明必须填写一项，不能同时为空！");
				return false;
			}	
	    }
	    if(jgTIN != null && jgTIN != ""){
	    	if(jgIssuedBy == null || jgIssuedBy == ""){
	    		alert("纳税人识别号 不为空时 发放识别号的国家（地区）代码 必须填写！");
				return false;
	    	}
	    }
	 }
	 //控制人信息验证及控制
	 function btSaveKZR_onClickCheck(button){
	 	if(!kxflag){
	 		alert("请先填写控制人姓名信息！");
	 		return false;
	 	}
	 	if(!kdflag){
	 		alert("请先填写控制人地址信息！");
	 		return false;
	 	}
	 	var kzrCtrlgPersonType = FjmzhAdd_dataset.getValue("kzrCtrlgPersonType");
	 	if(kzrCtrlgPersonType == null || kzrCtrlgPersonType == ""){
	    	alert("控制人类型 不能为空！");
			return false;
	    }
	    var kzrdBirthDate = FjmzhAdd_dataset.getValue("kzrdBirthDate");
	 	if(kzrdBirthDate == null || kzrdBirthDate == ""){
	    	alert("出生日期 不能为空！");
			return false;
	    }
	    var kzrResCountryCode = FjmzhAdd_dataset.getValue("kzrResCountryCode");
	 	if(kzrResCountryCode == null || kzrResCountryCode == ""){
	    	alert("税收居民国（地区）代码 不能为空！");
			return false;
	    }
	    var kzrBirthCountryCode = FjmzhAdd_dataset.getValue("kzrBirthCountryCode");
	    var kzrFormerCountryName = FjmzhAdd_dataset.getValue("kzrFormerCountryName");
	 	if(kzrBirthCountryCode == null || kzrBirthCountryCode == ""){
	 		if(kzrFormerCountryName == null || kzrFormerCountryName == ""){
		    	alert("出生国代码 和 出生国名称(英文) 必须填写一项，不能同时为空！");
				return false;
			}	
	    }
	    var kzrTIN = FjmzhAdd_dataset.getValue("kzrTIN");
	    var kzrIssuedBy = FjmzhAdd_dataset.getValue("kzrIssuedBy");
	    var kzrExplanation = FjmzhAdd_dataset.getValue("kzrExplanation");
	 	if(kzrTIN == null || kzrTIN == ""){
	 		if(kzrExplanation == null || kzrExplanation == ""){
		    	alert("纳税人识别号 和 未取得纳税人识别号说明 必须填写一项，不能同时为空！");
				return false;
			}	
	    }
	    if(kzrTIN != null && kzrTIN != ""){
	    	if(kzrIssuedBy == null || kzrIssuedBy == ""){
	    		alert("纳税人识别号 不为空时 发放识别号的国家（地区）代码 必须填写！");
				return false;
	    	}
	    }
	 }
	 //姓名信息验证
	 function btSaveXM_onClickCheck(button){
	 	var xLastName = FjmzhAdd_dataset.getValue("xLastName");
	 	if(xLastName == null || xLastName == ""){
	    	alert("法定英文（拼音）姓  不能为空！");
			return false;
	    }
	    var xFirstName = FjmzhAdd_dataset.getValue("xFirstName");
	 	if(xFirstName == null || xFirstName == ""){
	    	alert("法定英文（拼音）名  不能为空！");
			return false;
	    }
	 }
	 
	 //地址信息验证
	 function btSaveDZ_onClickCheck(button){
	 	var slegalAddressType = FjmzhAdd_dataset.getValue("slegalAddressType");
	 	if(slegalAddressType == null || slegalAddressType == ""){
	    	alert("地址类型 不能为空！");
			return false;
	    }
	 	var sCountryCode = FjmzhAdd_dataset.getValue("sCountryCode");
	 	if(sCountryCode == null || sCountryCode == ""){
	    	alert("国家代码 不能为空！");
			return false;
	    }
	    var sAddressEN_AddressFreeEN = FjmzhAdd_dataset.getValue("sAddressEN_AddressFreeEN");
	 	if(sAddressEN_AddressFreeEN == null || sAddressEN_AddressFreeEN == ""){
	    	alert("英文详细地址  不能为空！");
			return false;
	    }
	    var sAddressFixEN_CityEN = FjmzhAdd_dataset.getValue("sAddressFixEN_CityEN");
	 	if(sAddressFixEN_CityEN == null || sAddressFixEN_CityEN == ""){
	    	alert("所在城市  不能为空！");
			return false;
	    }
	    var sAddressFixCN_Province = FjmzhAdd_dataset.getValue("sAddressFixCN_Province");
	    var sAddressFixCN_CityCN = FjmzhAdd_dataset.getValue("sAddressFixCN_CityCN");
	 	if(sAddressFixCN_Province != null && sAddressFixCN_Province != ""){
		 	if(sAddressFixCN_CityCN == null || sAddressFixCN_CityCN == ""){
		    	alert("省级行政区划代码 不为空时 地级市行政区划代码 必须填写！");
				return false;
		    }
		}
	 }
	 
	 function btSaveGR_postSubmit(button) {
		button.url="#";
		subwindow_signWindowGR.close();
		gflag = true;
		$("#btAdd").hide();
		//flushCurrentPage();
		alert("保存成功！");
	 }
	 
	 function btSaveJG_postSubmit(button) {
		button.url="#";
		subwindow_signWindowJG.close();
		jflag = true;
		$("#btAdd1").hide();
		//flushCurrentPage();
		alert("保存成功！");
	 }
	 
	 function btSaveKZR_postSubmit(button) {
		button.url="#";
		subwindow_signWindowKZR.close();
		kflag = true;
		$("#btAdd2").hide();
		//flushCurrentPage();
		alert("保存成功！");
	 }
	 
	 function btSaveXM_postSubmit(button) {
	 	var xStyle = FjmzhAdd_dataset.getValue("xStyle");
		button.url="#";
		subwindow_signWindowXM.close();
		//保存对应姓名成功后将标识改为true
		if(xStyle == "01"){
			gxflag = true;
			$("#btAddGRXM").hide();
		}
		if(xStyle == "02"){
			kxflag = true;
			$("#btAddKXM").hide();
		}
		//FjmzhAdd_dataset.setFieldReadOnly("xStyle",false);
		//flushCurrentPage();
		alert("保存成功！");
	 }
	 
	 function btSaveDZ_postSubmit(button) {
	 	var dStyle = FjmzhAdd_dataset.getValue("dStyle");
		button.url="#";
		subwindow_signWindowDZ.close();
		//保存对应地址成功后将标识改为true
		if(dStyle == "01"){
			gdflag = true;
			$("#btAddGRDZ").hide();
		}
		if(dStyle == "02"){
			jdflag = true;
			$("#btAddJGDZ").hide();
		}
		if(dStyle == "03"){
			kdflag = true;
			$("#btAddKDZ").hide();
		}
		
		//FjmzhAdd_dataset.setFieldReadOnly("dStyle",false);
		//flushCurrentPage();
		alert("保存成功！");
	 }
	
</script>
</@CommonQueryMacro.page>
