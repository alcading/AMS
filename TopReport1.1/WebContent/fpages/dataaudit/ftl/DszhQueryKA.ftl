<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="数据分析手工执行">
<@CommonQueryMacro.CommonQuery id="DszhQueryKA" init="true" submitMode="all" navigate="false">
<table width="1349px">
	
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btMod,-"  fieldStr="select[40],zh,kh,kdqr,zhjz,xkrq,kzt"  width="100%" hasFrame="true" height="300" readonly="true"/>
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
	var zh = DszhQueryKA_dataset.getValue("zh");
	showUpdate(zh);
}

function showUpdate(zh){

	showWin("对私账户查询修改","${contextPath}/fpages/regonization/ftl/DszhQueryKAUpdate.ftl?zh="+zh,null,null,window);
}
</script>
</@CommonQueryMacro.page>
