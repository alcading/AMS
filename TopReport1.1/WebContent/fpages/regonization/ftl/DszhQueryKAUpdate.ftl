<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="卡信息查询修改">
<@CommonQueryMacro.CommonQuery id="DszhQueryKAUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="卡信息查询修改" fieldStr="zh,kh,kdqr,zhjz,xkrq,kzt" colNm=6/>
			</td>
		</tr>
		<tr>
		     <td>
		         <left><@CommonQueryMacro.Button id= "btMod"/></left>
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
	     DszhQueryKAUpdate_dataset.flushData(DszhQueryKAUpdate_dataset.pageIndex);
	     
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
	var zh = DszhQueryKAUpdate_dataset.getValue("zh");
	showUpdate(zh);
}

function showUpdate(zh){
	showWin("联名账户信息","${contextPath}/fpages/dataaudit/ftl/DszhQueryLM.ftl?zh="+zh,null,null,window);
	//window.location.href = "${contextPath}/fpages/regonization/ftl/DszhQueryLM.ftl?zh="+zh;
}
</script>
</@CommonQueryMacro.page>
