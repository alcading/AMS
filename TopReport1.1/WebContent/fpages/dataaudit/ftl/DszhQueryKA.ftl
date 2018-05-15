<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="介质信息">
<@CommonQueryMacro.CommonQuery id="DszhQueryKA" init="true" submitMode="all" navigate="false">
<table width="1349px">
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btMod,-,btNew"  fieldStr="select[40],zh,kh,kdqr,zhjz,xkrq,kzt"  width="100%" hasFrame="true" height="300" readonly="true"/>
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
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">
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

function btMod_onClickCheck(button){
    var rec = DszhQueryKA_dataset.firstUnit;
	
	var f = false;
	var kh = null;
	while(rec) {
		if (rec.getValue('select')) {
			kh = rec.getValue("kh");
			f = true;
			break;
		}
		rec = rec.nextUnit;
	}
	if(!f) {
		alert('请选择记录');
		return false;
	}
	showUpdate(kh);
}

function showUpdate(kh){

	showWin("介质信息修改","${contextPath}/fpages/regonization/ftl/DszhQueryKAUpdate.ftl?kh="+kh,null,null,window);
}

function btNew_onClick(button){
	DszhQueryKA_dataset.setValue("zh",name);
	subwindow_signWindow.show();
	  
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
</script>
</@CommonQueryMacro.page>
