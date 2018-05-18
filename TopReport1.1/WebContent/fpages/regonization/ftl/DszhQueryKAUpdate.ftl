<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="介质信息查询修改">
<@CommonQueryMacro.CommonQuery id="DszhQueryKAUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="介质信息查询修改" fieldStr="zh,kh,kdqr,zhjz,xkrq,kzt" colNm=6/>
			</td>
		</tr>
		<tr>
		     <td>
		         
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

</script>
</@CommonQueryMacro.page>
