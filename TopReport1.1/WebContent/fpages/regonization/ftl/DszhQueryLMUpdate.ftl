<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="联名账户查询修改">
<@CommonQueryMacro.CommonQuery id="DszhQueryLMUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="联名账户查询修改" fieldStr="zh,ckrxm,ckrsfzjzl,ckrsfzjhm,sfzjdqr,dqdm,ckrlb,ckrgjdq,ckrxb" colNm=6/>
			</td>
				</tr>
		<tr>
  		   	<td valign="CENTER">
				<left><@CommonQueryMacro.Button id= "btBack"/></left>&nbsp;
				<left><@CommonQueryMacro.Button id= "btSave"/></left>
  			</td>
		</tr> 
	</table>
</@CommonQueryMacro.CommonQuery>


<script language="javascript">
     function btSave_postSubmit(button){
	     fieldReadOnlyStatus(true);      
	     DszhQueryLMUpdate_dataset.flushData(DszhQueryLMUpdate_dataset.pageIndex);
	     
	  }
	  function btSave_onClickCheck(button) {
	  	closeWin();
	  	return true;
	  }
	  
	 function btBack_onClickCheck()
	 {
		closeWin();
	 }
function btMod_onClick(){
	var zh = DszhQueryLMUpdate_dataset.getValue("zh");
	showUpdate(zh);
}

function showUpdate(zh){
	showWin("联名账户信息","${contextPath}/fpages/dataaudit/ftl/DszhQueryLM.ftl?zh="+zh,null,null,window);
	//window.location.href = "${contextPath}/fpages/regonization/ftl/DszhQueryLM.ftl?zh="+zh;
}
</script>
</@CommonQueryMacro.page>
