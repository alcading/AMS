<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="个人信息">
<@CommonQueryMacro.CommonQuery id="FjmzhQueryGR" init="true" submitMode="all" navigate="false">
<table width="100%">
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btModXM,-,btModDZ,-,btBack"  fieldStr="select[40],griAccID,grGender,grIDType,grIDNumber,grBirthDate,grFormerCountryName,operation"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.FloatWindow id="signWindow" label="" width="" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="个人信息新增"
        			  fieldStr="griAccID,grGender,grPhoneNo,grIDType,grIDNumber,grBirthDate,grBirthCity,grBirthCountryCode,grFormerCountryName,grIssuedBy,grsinType,grTIN,grExplanation,grNationality" colNm=4/>
        			 </br>
      				<@CommonQueryMacro.Button id= "btSave"/>
      			</div>
     		 </@CommonQueryMacro.FloatWindow>
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.FloatWindow id="signWindowEdit" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="个人信息修改"
        			  fieldStr="griAccID,grGender,grPhoneNo,grIDType,grIDNumber,grBirthDate,grBirthCity,grBirthCountryCode,grFormerCountryName,grIssuedBy,grTIN,grExplanation,grNationality" colNm=4/>
        			 </br>
      				<@CommonQueryMacro.Button id= "btSaveEdit"/>
      			</div>
     		 </@CommonQueryMacro.FloatWindow>
		</td>
	</tr>
	
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">

	var style = "01";
	//查看修改地址信息
	function btModDZ_onClick(){
		var griAccID = FjmzhQueryGR_dataset.getString("griAccID");
		showWin("地址信息","${contextPath}/fpages/dataaudit/ftl/FjmzhQueryDZ.ftl?diAccID="+griAccID+"&dStyle="+style,null,null,window);
	}
	
	//查看修改姓名信息
	function btModXM_onClick(){
		var griAccID = FjmzhQueryGR_dataset.getString("griAccID");
		showWin("姓名信息","${contextPath}/fpages/dataaudit/ftl/FjmzhQueryXM.ftl?xiAccID="+griAccID+"&xStyle="+style,null,null,window);
	}

	//定位一行记录
	function locate(griAccID) {
		var record = FjmzhQueryGR_dataset.find(["griAccID"],[griAccID]);
		if(record) {
			FjmzhQueryGR_dataset.setRecord(record);
		}
	}
	//系统刷新单元格
	function datatable1_operation_onRefresh(cell,value,record) {
		if(record) {
			var griAccID = record.getValue("griAccID");
			cell.innerHTML="<center><a href=\"JavaScript:showDetailGR('"+griAccID+"')\">修改</a></center>";
		}else {//当不存在记录时
		 cell.innerHTML="&nbsp;";
		}
			
	}

	
	var name = null;
	window.onload=function(){
		var griAccID = GetQueryString("griAccID");
		if(griAccID!=null) 
		{ 
			name = decodeURIComponent(griAccID);
		}
	}

	function GetQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r!=null) return (r[2]); 
		return null; 
	}
	
	function btNew_onClick(button){
		FjmzhQueryGR_dataset.setValue("griAccID",name);
		FjmzhQueryGR_dataset.setFieldReadOnly("griAccID",true);
		subwindow_signWindow.show();  
	}
	
	//修改
	function showDetailGR(griAccID){
		locate(griAccID);
		FjmzhQueryGR_dataset.setFieldReadOnly("griAccID",true);
		subwindow_signWindowEdit.show();  
	}
	
	function btSaveEdit_onClickCheck(button){
		var grGender = FjmzhQueryGR_dataset.getValue("grGender");
	 	if(grGender == null || grGender == ""){
	    	alert("性别 不能为空！");
			return false;
	    }
	    var grBirthDate = FjmzhQueryGR_dataset.getValue("grBirthDate");
	 	if(grBirthDate == null || grBirthDate == ""){
	    	alert("出生日期不能为空！");
			return false;
	    }
	    var grBirthCountryCode = FjmzhQueryGR_dataset.getValue("grBirthCountryCode");
	    var grFormerCountryName = FjmzhQueryGR_dataset.getValue("grFormerCountryName");
	 	if(grBirthCountryCode == null || grBirthCountryCode == ""){
	 		if(grFormerCountryName == null || grFormerCountryName == ""){
		    	alert("出生国代码和出生国名称(英文)必须填写一项，不能同时为空！");
				return false;
			}	
	    }
	    var grTIN = FjmzhQueryGR_dataset.getValue("grTIN");
	    var grIssuedBy = FjmzhQueryGR_dataset.getValue("grIssuedBy");
	    var grExplanation = FjmzhQueryGR_dataset.getValue("grExplanation");
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
	    var grNationality = FjmzhQueryGR_dataset.getValue("grNationality");
	 	if(grNationality == null || grNationality == ""){
	    	alert("国籍 不能为空！");
			return false;
	    }
	}
	
	function btSave_postSubmit(button)
	  {
		button.url="#";
		//alert("保存成功");
		subwindow_signWindow.hide();
		FjmzhQueryGR_dataset.flushData(FjmzhQueryGR_dataset.pageIndex);
	  }
	
	function signWindow_floatWindow_beforeClose(subwindow){
		
		 FjmzhQueryGR_dataset.cancelRecord();
		 return true;
	}
		
	function signWindow_floatWindow_beforeHide(subwindow){
		return signWindow_floatWindow_beforeClose(subwindow);
	}
	
	function btBack_onClickCheck()
	{
		closeWin();
	}
	
	//保存后刷新当前页
	function btSaveEdit_postSubmit(button) {
		button.url="#";
		subwindow_signWindowEdit.close();
		flushCurrentPage();
	}
</script>
</@CommonQueryMacro.page>
