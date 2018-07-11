<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="姓名信息">
<@CommonQueryMacro.CommonQuery id="FjmzhQueryXM" init="true" submitMode="all" navigate="false">
<table width="100%">
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btBack"  fieldStr="select[40],xiAccID,xNameType,xLastName,xMiddleName,xTitle,xStyle,operation"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.FloatWindow id="signWindow" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="姓名信息新增"
        			  fieldStr="xiAccID,xNameType,xLastName,xMiddleName,xFirstName,xNameCN,xPrecedingTitle,xTitle,xNamePrefix,xGenerationIdentifier,xSuffix,xGeneralSuffix,xStyle" colNm=4/>
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
      				<@CommonQueryMacro.Group id="group1" label="姓名信息修改"
        			  fieldStr="xiAccID,xNameType,xLastName,xMiddleName,xFirstName,xNameCN,xPrecedingTitle,xTitle,xNamePrefix,xGenerationIdentifier,xSuffix,xGeneralSuffix,xStyle" colNm=4/>
        			 </br>
      				<@CommonQueryMacro.Button id= "btSaveEdit"/>
      			</div>
     		 </@CommonQueryMacro.FloatWindow>
		</td>
	</tr>
	
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">


//定位一行记录
	function locate(xiAccID) {
		var record = FjmzhQueryXM_dataset.find(["xiAccID"],[xiAccID]);
		if(record) {
			FjmzhQueryXM_dataset.setRecord(record);
		}
	}
	//系统刷新单元格
	function datatable1_operation_onRefresh(cell,value,record) {
		if(record) {
			var xiAccID = record.getValue("xiAccID");
			cell.innerHTML="<center><a href=\"JavaScript:showDetailGR('"+xiAccID+"')\">修改</a></center>";
		}else {//当不存在记录时
		 cell.innerHTML="&nbsp;";
		}
			
	}


var name = null;
window.onload=function(){
	var xiAccID = GetQueryString("xiAccID");
	if(griAccID!=null) 
	{ 
		name = decodeURIComponent(xiAccID);
	}
}

function GetQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return (r[2]); 
	return null; 
}

function btNew_onClick(button){
	FjmzhQueryXM_dataset.setValue("xiAccID",name);
	FjmzhQueryXM_dataset.setFieldReadOnly("xiAccID",true);
	subwindow_signWindow.show();  
}

//修改
function showDetailGR(xiAccID){
	locate(xiAccID);
	FjmzhQueryXM_dataset.setFieldReadOnly("xiAccID",true);
	subwindow_signWindowEdit.show();  
}


function btSave_postSubmit(button)
  {
	button.url="#";
	//alert("保存成功");
	subwindow_signWindow.hide();
	FjmzhQueryXM_dataset.flushData(FjmzhQueryXM_dataset.pageIndex);
  }

function signWindow_floatWindow_beforeClose(subwindow){
	
	 FjmzhQueryXM_dataset.cancelRecord();
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
	var xLastName = FjmzhQueryXM_dataset.getValue("xLastName");
 	if(xLastName == null || xLastName == ""){
    	alert("法定英文（拼音）姓  不能为空！");
		return false;
    }
    var xFirstName = FjmzhQueryXM_dataset.getValue("xFirstName");
 	if(xFirstName == null || xFirstName == ""){
    	alert("法定英文（拼音）名  不能为空！");
		return false;
    }
}

//保存后刷新当前页
function btSaveEdit_postSubmit(button) {
	button.url="#";
	subwindow_signWindowEdit.close();
	flushCurrentPage();
}

//刷新当前页
function flushCurrentPage() {
	DszhQuery_dataset.flushData(DszhQuery_dataset.pageIndex);
}

</script>
</@CommonQueryMacro.page>
