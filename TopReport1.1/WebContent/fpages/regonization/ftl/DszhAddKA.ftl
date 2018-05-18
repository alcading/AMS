<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<#import "/fpages/regonization/ftl/DszhQueryUpdate.ftl" as DszhQueryUpdate>
<@CommonQueryMacro.page title="对私账户介质新增">
<@CommonQueryMacro.CommonQuery id="DszhAddKA" init="false" submitMode="current" navigate="false">
	<table align="left">
		<tr valign="top">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="对私账户介质新增" fieldStr="zh,kh,kdqr,zhjz,xkrq,kzt" colNm=6/>
			</td>
		</tr>
		<tr>
		    <td>
		       
		    </td>
		</tr>	
		<tr>
  		   	<td valign="CENTER">
				<left><@CommonQueryMacro.Button id="btBack"/></left>&nbsp;
				<left><@CommonQueryMacro.Button id="btSave"/></left>
  			</td>
		</tr> 
	</table>
</@CommonQueryMacro.CommonQuery>
<script language="javascript">
	  
	 function btBack_onClickCheck()
	 {
	 	closeWin();
		//window.location.href = "${contextPath}/fpages/dataaudit/ftl/DszhQuery.ftl";
	 }


</script>
</@CommonQueryMacro.page>
