<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="机构信息">
<@CommonQueryMacro.CommonQuery id="FjmzhQueryJG" init="true" submitMode="all" navigate="false">
<table width="100%">
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable2" paginationbar="btModDZ,-,btBack"  fieldStr="select[40],jgiAccID,jgPhoneNo,jgNameType,jgExplanation,operation"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.FloatWindow id="signWindow" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="机构信息新增"
        			  fieldStr="jgiAccID,jgPhoneNo,jgNameType,jgOrganisationNameEN,jgOrganisationNameCN,jgIssuedBy,jgsinType,jgTIN,jgExplanation" colNm=4/>
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
      				<@CommonQueryMacro.Group id="group1" label="机构信息修改"
        			  fieldStr="jgiAccID,jgPhoneNo,jgNameType,jgOrganisationNameEN,jgOrganisationNameCN,jgIssuedBy,jgsinType,jgTIN,jgExplanation" colNm=4/>
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
		var jgiAccID = FjmzhQueryJG_dataset.getValue("jgiAccID");
		var style = "02";
		showWin("地址信息","${contextPath}/fpages/dataaudit/ftl/FjmzhQueryDZ.ftl?diAccID="+jgiAccID+"&dStyle="+style,null,null,window);
	}

	//定位一行记录
	function locate(jgiAccID) {
		var record = FjmzhQueryJG_dataset.find(["jgiAccID"],[jgiAccID]);
		if(record) {
			FjmzhQueryJG_dataset.setRecord(record);
		}
	}
	//系统刷新单元格
	function datatable2_operation_onRefresh(cell,value,record) {
		if(record) {
			var jgiAccID = record.getValue("jgiAccID");
			cell.innerHTML="<center><a href=\"JavaScript:showDetailJG('"+jgiAccID+"')\">修改</a></center>";
		}else {//当不存在记录时
		 cell.innerHTML="&nbsp;";
		}
			
	}


var name = null;
window.onload=function(){
	var jgiAccID = GetQueryString("jgiAccID");
	if(griAccID!=null) 
	{ 
		name = decodeURIComponent(jgiAccID);
	}
}

function GetQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return (r[2]); 
	return null; 
}

function btNew_onClick(button){
	FjmzhQueryJG_dataset.setValue("jgiAccID",name);
	FjmzhQueryJG_dataset.setFieldReadOnly("jgiAccID",true);
	subwindow_signWindow.show();  
}

//修改
function showDetailJG(jgiAccID){
	locate(jgiAccID);
	FjmzhQueryJG_dataset.setFieldReadOnly("jgiAccID",true);
	subwindow_signWindowEdit.show();  
}


function btSave_postSubmit(button)
  {
	button.url="#";
	//alert("保存成功");
	subwindow_signWindow.hide();
	FjmzhQueryJG_dataset.flushData(FjmzhQueryGR_dataset.pageIndex);
  }

function signWindow_floatWindow_beforeClose(subwindow){
	
	 FjmzhQueryJG_dataset.cancelRecord();
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
