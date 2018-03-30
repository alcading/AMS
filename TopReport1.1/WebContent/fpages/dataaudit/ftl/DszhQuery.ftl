<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<#import "/fpages/regonization/ftl/DszhQueryUpdate.ftl" as DszhQueryUpdate>
<@CommonQueryMacro.page title="数据分析手工执行">

<@CommonQueryMacro.CommonQuery id="DszhQuery" init="true" submitMode="all" navigate="false">
<table width="1349px">
	<tr>
		<td>
			<@CommonQueryMacro.Interface id="interface" label="个人结算账户" btnNm="查询" colNm=8/>
		</td>
	</tr>
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btMod,-,btDel"  fieldStr="select[40],xxlx,ckrxm,ckrsfzjzl,ckrsfzjhm,jrjgbm,zh,zhzl,bz,zhzt,jlrq,ismodify"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
	</tr>
</table>
<#--<span id="button-tools" style="padding-left:10px"><@CommonQueryMacro.Button id= "btSave"/>&nbsp;<span id="message" >请先按数据日期查询,再导出</span></span>-->
</@CommonQueryMacro.CommonQuery>

<script language="javascript">

$('#DszhQuery_interface_dataset_btnSubmit').after($('#button-tools'));
 function btSave_onClickCheck(button) {
	  	alert("导出成功");
	  } 


function btDel_onClickCheck(button) {
	var rec = DszhQuery_dataset.firstUnit;
	var f = false;
	while(rec) {
		if (rec.getValue('select')) {
			f = true;
			//break;
		}
		rec = rec.nextUnit;
	}
	if(!f) {
		alert('请选择记录');
		return false;
	}
}


function btMod_onClickCheck(button) {

	var rec = DszhQuery_dataset.firstUnit;
	
	var f = false;
	var zh = null;
	while(rec) {
		if (rec.getValue('select')) {
			zh = rec.getValue("zh");
			f = true;
			break;
		}
		rec = rec.nextUnit;
	}
	if(!f) {
		alert('请选择记录');
		return false;
	}
	showUpdate(zh);
	flushCurrentPage();
}

function btSave_onClickCheck(button) {
	  
	  	flushCurrentPage();
	  	
}

//function btMod_onClick(){
//	var ckrsfzjzl = DszhQuery_dataset.getValue("ckrsfzjzl");
//	var ckrsfzjhm = DszhQuery_dataset.getValue("ckrsfzjhm");
//	var zh = DszhQuery_dataset.getValue("zh");
//	showUpdate(zh);
	
//}

function showUpdate(zh){
	showWin("个人结算账户修改","${contextPath}/fpages/regonization/ftl/DszhQueryUpdate.ftl?zh="+zh,null,null,window);
	
}

function btDel_onClickCheck(button) {
		return confirm("确认删除记录？");
}
	
function btDel_postSubmit(button) {
		
		button.url="#";
		//刷新当前页
		flushCurrentPage();
}
//刷新当前页
function flushCurrentPage() {
	DszhQuery_dataset.flushData(DszhQuery_dataset.pageIndex);
}

</script>
</@CommonQueryMacro.page>
