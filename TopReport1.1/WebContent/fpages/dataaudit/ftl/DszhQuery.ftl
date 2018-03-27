<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="数据分析手工执行">
<@CommonQueryMacro.CommonQuery id="DszhQuery" init="true" submitMode="all" navigate="false">
<table width="1349px">
	<tr>
		<td>
			<@CommonQueryMacro.Interface id="interface" label="对私账户查询" btnNm="查询" colNm=8/>
		</td>
	</tr>
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btMod,-"  fieldStr="select[40],xxlx,ckrxm,ckrsfzjzl,ckrsfzjhm,jrjgbm,zh,zhzl,bz,zhzt,jlrq,ismodify"  width="100%" hasFrame="true" height="300" readonly="true"/>
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
function btMod_onClick(){
	var ckrsfzjzl = DszhQuery_dataset.getValue("ckrsfzjzl");
	var ckrsfzjhm = DszhQuery_dataset.getValue("ckrsfzjhm");
	
	var zh = DszhQuery_dataset.getValue("zh");
	showUpdate(zh);
}

function showUpdate(zh){

	showWin("对私账户查询修改","${contextPath}/fpages/regonization/ftl/DszhQueryUpdate.ftl?zh="+zh,null,null,window);
}
</script>
</@CommonQueryMacro.page>
