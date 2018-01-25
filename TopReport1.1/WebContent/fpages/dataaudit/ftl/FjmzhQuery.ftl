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
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btMod,-"  fieldStr="select[40],accountnumber,closedaccount,duediligenceind,selfcertification,accountbalance,accountholdertype,openingfiname,payment,ind_name,ind_idtype,ind_idnumber"  width="100%" hasFrame="true" height="300" readonly="true"/>
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
	var ind_idtype = FjmzhQuery_dataset.getValue("ind_idtype");
	var ind_idnumber = FjmzhQuery_dataset.getValue("ind_idnumber");
	
	showUpdate(ind_idtype,ind_idnumber);
}

function showUpdate(ind_idtype,ind_idnumber){

	showWin("对私账户查询修改","${contextPath}/fpages/regonization/ftl/FjmzhQueryUpdate.ftl?ind_idtype="+ind_idtype+"&ind_idnumber="+ind_idnumber,null,null,window);
}
</script>
</@CommonQueryMacro.page>
