<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="介质信息">
<@CommonQueryMacro.CommonQuery id="DszhQueryKA" init="true" submitMode="all" navigate="false">
<table width="1349px">
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btNew"  fieldStr="select[40],zh,kh,kdqr,zhjz,xkrq,kzt,operation"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.FloatWindow id="signWindow" label="" width="" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="介质信息新增"
        			  fieldStr="zh,kh,kdqr,zhjz,xkrq,kzt" colNm=4/>
        			 </br>
      				<@CommonQueryMacro.Button id= "btSave"/>
      			</div>
     		 </@CommonQueryMacro.FloatWindow>
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.FloatWindow id="signWindowEdit" label="" width="" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="介质信息修改"
        			  fieldStr="zh,kh,kdqr,zhjz,xkrq,kzt" colNm=4/>
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
	function locate(kh) {
		var record = DszhQueryKA_dataset.find(["kh"],[kh]);
		if(record) {
			DszhQueryKA_dataset.setRecord(record);
		}
	}
//系统刷新单元格
	function datatable1_operation_onRefresh(cell,value,record) {
		if(record) {
			var kh = record.getValue("kh");
			cell.innerHTML="<center><a href=\"JavaScript:showDetailKA('"+kh+"')\">修改</a></center>";
		}else {//当不存在记录时
		 cell.innerHTML="&nbsp;";
		}	
	}


var name = null;
window.onload=function(){
	var sname = GetQueryString("zh");
	 
	if(sname!=null) 
	{ 
		name = decodeURIComponent(sname); 
	}
}

function GetQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return (r[2]); 
	return null; 
}

function btNew_onClick(button){
	DszhQueryKA_dataset.setValue("zh",name);
	DszhQueryKA_dataset.setFieldReadOnly("kh",false);
	subwindow_signWindow.show();  
}

//修改
function showDetailKA(kh){
	locate(kh);
	DszhQueryKA_dataset.setFieldReadOnly("kh",true);
	subwindow_signWindowEdit.show();  
}


function btSave_postSubmit(button)
  {
	button.url="#";
	//alert("保存成功");
	subwindow_signWindow.hide();
	DszhQueryKA_dataset.flushData(DszhQueryKA_dataset.pageIndex);
  }

function signWindow_floatWindow_beforeClose(subwindow){
	
	 DszhQueryKA_dataset.cancelRecord();
	 return true;
}
	
function signWindow_floatWindow_beforeHide(subwindow){
	return signWindow_floatWindow_beforeClose(subwindow);
}


//保存后刷新当前页
	function btSaveEdit_postSubmit(button) {
		button.url="#";
		subwindow_signWindowEdit.close();
		flushCurrentPage();
	}
</script>
</@CommonQueryMacro.page>
