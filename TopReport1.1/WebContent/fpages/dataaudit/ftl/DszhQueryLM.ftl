<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="联名账户">
<@CommonQueryMacro.CommonQuery id="DszhQueryLM" init="true" submitMode="all" navigate="false">
<table width="1349px">
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btMod,-,btNew"  fieldStr="select[40],zh,ckrxm,ckrsfzjzl,ckrsfzjhm,sfzjdqr,dqdm,ckrlb,ckrgjdq,ckrxb"  width="100%" hasFrame="false" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.FloatWindow id="signWindow" label="" width="800" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="联名账户新增"
        			  fieldStr="zh,ckrxm,ckrsfzjzl,ckrsfzjhm,sfzjdqr,dqdm,ckrlb,ckrgjdq,ckrxb" colNm=4/>
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

function btMod_onClick(){
	var zh = DszhQueryLM_dataset.getValue("zh");
	showUpdate(zh);
}

function showUpdate(zh){

	showWin("对私账户查询修改","${contextPath}/fpages/regonization/ftl/DszhQueryLMUpdate.ftl?zh="+zh,null,null,window);
}

function btNew_onClick(button){
	DszhQueryLM_dataset.setValue("zh",name);
	subwindow_signWindow.show();
	  
}

function btSave_postSubmit(button)
  {
	button.url="#";
	//alert("保存成功");
	subwindow_signWindow.hide();
	DszhQueryLM_dataset.flushData(DszhQueryLM_dataset.pageIndex);
  }

function signWindow_floatWindow_beforeClose(subwindow){
	
	 DszhQueryLM_dataset.cancelRecord();
	 return true;
}
	
function signWindow_floatWindow_beforeHide(subwindow){
	return signWindow_floatWindow_beforeClose(subwindow);
}
</script>
</@CommonQueryMacro.page>
