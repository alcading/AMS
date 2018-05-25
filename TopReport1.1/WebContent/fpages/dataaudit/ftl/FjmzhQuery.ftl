<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="非居民账户查询">
<@CommonQueryMacro.CommonQuery id="FjmzhQuery" init="true" submitMode="all" navigate="false">
<table width="1349px">
	<tr>
		<td>
			<@CommonQueryMacro.Interface id="interface" label="非居民账户查询" btnNm="查询" colNm=8/>
		</td>
	</tr>
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btAdd,-,btMod,-,btDel"  fieldStr="select,accountnumber,closedaccount,duediligenceind,selfcertification,accountbalance,accountholdertype,openingfiname,payment"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
	</tr>
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript"> 


function btMod_onClickCheck(button) {
	
	var rec = FjmzhQuery_dataset.firstUnit;
	
	var f = false;
	var accountnumber = null;
	
	while(rec) {
		if (rec.getValue('select')) {
			f = true;
			accountnumber = rec.getValue('accountnumber');
			break;
		}
		rec = rec.nextUnit;
	}
	if(!f) {
		alert('请选择记录');
		return false;
	}
	showUpdate(accountnumber);
	flushCurrentPage();
	
}

function showUpdate(accountnumber){

	showWin("非居民账户查询修改","${contextPath}/fpages/regonization/ftl/FjmzhQueryUpdate.ftl?accountnumber="+accountnumber,null,flushCurrentPage(),window);
	
}



function btDel_postSubmit(button) {
		
		button.url="#";
		//刷新当前页
		flushCurrentPage();
		alert("删除成功！");	
}

function btDel_onClickCheck(button) {
	var rec = FjmzhQuery_dataset.firstUnit;
	var f = false;
	while(rec) {
		if (rec.getValue('select')) {
			f = true;
		}
		rec = rec.nextUnit;
	}
	if(!f) {
		alert('请选择记录');
		return false;
	}
	return confirm("确认删除记录？");
}

//刷新当前页
function flushCurrentPage() {
	FjmzhQuery_dataset.flushData(FjmzhQuery_dataset.pageIndex);
}



</script>
</@CommonQueryMacro.page>
