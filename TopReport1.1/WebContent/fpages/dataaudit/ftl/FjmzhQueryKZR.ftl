<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="控制人信息">
<@CommonQueryMacro.CommonQuery id="FjmzhQueryKZR" init="true" submitMode="all" navigate="false">
<table width="100%">
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btModXM,-,btModDZ,-,btBack"  fieldStr="select[40],kzriAccID,kzrCtrlgPersonType,kzrdBirthDate,kzrBirthCity,operation"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.FloatWindow id="signWindow" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="控制人信息新增"
        			  fieldStr="kzriAccID,kzrCtrlgPersonType,kzrdBirthDate,kzrBirthCity,kzrBirthCountryCode,kzrFormerCountryName,kzrNationality,kzrResCountryCode,kzrIssuedBy,kzrsinType,kzrTIN,kzrExplanation" colNm=4/>
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
      				<@CommonQueryMacro.Group id="group1" label="控制人信息修改"
        			  fieldStr="kzriAccID,kzrCtrlgPersonType,kzrdBirthDate,kzrBirthCity,kzrBirthCountryCode,kzrFormerCountryName,kzrNationality,kzrResCountryCode,kzrIssuedBy,kzrTIN,kzrExplanation" colNm=4/>
        			 </br>
      				<@CommonQueryMacro.Button id= "btSaveEdit"/>
      			</div>
     		 </@CommonQueryMacro.FloatWindow>
		</td>
	</tr>
	
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">

	//查看修改地址信息
	function btModDZ_onClick(){
		var diAccID = FjmzhQueryKZR_dataset.getValue("kzriAccID");
		var style = "03";
		showWin("地址信息","${contextPath}/fpages/dataaudit/ftl/FjmzhQueryDZ.ftl?diAccID="+diAccID+"&dStyle="+style,null,null,window);
	}
	
	//查看修改姓名信息
	function btModXM_onClick(){
		var xiAccID = FjmzhQueryKZR_dataset.getValue("kzriAccID");
		var xstyle = "02";
		showWin("姓名信息","${contextPath}/fpages/dataaudit/ftl/FjmzhQueryXM.ftl?xiAccID="+xiAccID+"&xStyle="+xstyle,null,null,window);
	}

	//定位一行记录
	function locate(kzriAccID) {
		var record = FjmzhQueryKZR_dataset.find(["kzriAccID"],[kzriAccID]);
		if(record) {
			FjmzhQueryKZR_dataset.setRecord(record);
		}
	}
	//系统刷新单元格
	function datatable1_operation_onRefresh(cell,value,record) {
		if(record) {
			var kzriAccID = record.getValue("kzriAccID");
			cell.innerHTML="<center><a href=\"JavaScript:showDetailKZR('"+kzriAccID+"')\">修改</a></center>";
		}else {//当不存在记录时
		 cell.innerHTML="&nbsp;";
		}
			
	}


var name = null;
window.onload=function(){
	var griAccID = GetQueryString("kzriAccID");
	if(griAccID!=null) 
	{ 
		name = decodeURIComponent(kzriAccID);
	}
}

function GetQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return (r[2]); 
	return null; 
}

function btNew_onClick(button){
	FjmzhQueryKZR_dataset.setValue("kzriAccID",name);
	FjmzhQueryKZR_dataset.setFieldReadOnly("kzriAccID",true);
	subwindow_signWindow.show();  
}

//修改
function showDetailKZR(kzriAccID){
	locate(kzriAccID);
	FjmzhQueryKZR_dataset.setFieldReadOnly("kzriAccID",true);
	subwindow_signWindowEdit.show();  
}


function btSave_postSubmit(button)
  {
	button.url="#";
	//alert("保存成功");
	subwindow_signWindow.hide();
	FjmzhQueryKZR_dataset.flushData(FjmzhQueryKZR_dataset.pageIndex);
  }

function signWindow_floatWindow_beforeClose(subwindow){
	
	 FjmzhQueryKZR_dataset.cancelRecord();
	 return true;
}
	
function signWindow_floatWindow_beforeHide(subwindow){
	return signWindow_floatWindow_beforeClose(subwindow);
}

function btBack_onClickCheck()
{
	closeWin();
}

function btSaveEdit_onClickCheck(button){
	var kzrCtrlgPersonType = FjmzhQueryKZR_dataset.getValue("kzrCtrlgPersonType");
 	if(kzrCtrlgPersonType == null || kzrCtrlgPersonType == ""){
    	alert("控制人类型 不能为空！");
		return false;
    }
    var kzrdBirthDate = FjmzhQueryKZR_dataset.getValue("kzrdBirthDate");
 	if(kzrdBirthDate == null || kzrdBirthDate == ""){
    	alert("出生日期 不能为空！");
		return false;
    }
    var kzrResCountryCode = FjmzhQueryKZR_dataset.getValue("kzrResCountryCode");
 	if(kzrResCountryCode == null || kzrResCountryCode == ""){
    	alert("税收居民国（地区）代码 不能为空！");
		return false;
    }
    var kzrBirthCountryCode = FjmzhQueryKZR_dataset.getValue("kzrBirthCountryCode");
    var kzrFormerCountryName = FjmzhQueryKZR_dataset.getValue("kzrFormerCountryName");
 	if(kzrBirthCountryCode == null || kzrBirthCountryCode == ""){
 		if(kzrFormerCountryName == null || kzrFormerCountryName == ""){
	    	alert("出生国代码 和 出生国名称(英文) 必须填写一项，不能同时为空！");
			return false;
		}	
    }
    var kzrTIN = FjmzhQueryKZR_dataset.getValue("kzrTIN");
    var kzrIssuedBy = FjmzhQueryKZR_dataset.getValue("kzrIssuedBy");
    var kzrExplanation = FjmzhQueryKZR_dataset.getValue("kzrExplanation");
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

//保存后刷新当前页
	function btSaveEdit_postSubmit(button) {
		button.url="#";
		subwindow_signWindowEdit.close();
		flushCurrentPage();
	}
</script>
</@CommonQueryMacro.page>
