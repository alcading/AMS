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
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btMod,-"  fieldStr="select,accountnumber,closedaccount,duediligenceind,selfcertification,accountbalance,accountholdertype,openingfiname,payment"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
	</tr>
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript"> 
function btMod_onClick(){
	var accountnumber = FjmzhQuery_dataset.getValue("accountnumber");
	showUpdate(accountnumber);
}

function showUpdate(accountnumber){

	showWin("非居民账户查询修改","${contextPath}/fpages/regonization/ftl/FjmzhQueryUpdate.ftl?accountnumber="+accountnumber,null,null,window);
}
</script>
</@CommonQueryMacro.page>
