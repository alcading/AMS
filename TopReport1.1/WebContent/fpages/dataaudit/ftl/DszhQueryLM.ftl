<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="数据分析手工执行">
<@CommonQueryMacro.CommonQuery id="DszhQueryLM" init="true" submitMode="all" navigate="false">
<table width="1349px">
	
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btMod,-"  fieldStr="select[40],zh,ckrxm,ckrsfzjzl,ckrsfzjhm,sfzjdqr,dqdm,ckrlb,ckrgjdq,ckrxb"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
	</tr>
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">
function btMod_onClickCheck(){
    var rec = DszhQueryLM_dataset.firstUnit;
	var f = false;
	var ckrsfzjhm = null;
	while(rec) {
		if (rec.getValue('select')) {
			ckrsfzjhm = rec.getValue("ckrsfzjhm");
			f = true;
			break;
		}
		rec = rec.nextUnit;
	}
	if(!f) {
		alert('请选择记录');
		return false;
	}
	showUpdate(ckrsfzjhm);
}

function showUpdate(ckrsfzjhm){

	showWin("对私账户查询修改","${contextPath}/fpages/regonization/ftl/DszhQueryLMUpdate.ftl?ckrsfzjhm="+ckrsfzjhm,null,null,window);
}
</script>
</@CommonQueryMacro.page>
