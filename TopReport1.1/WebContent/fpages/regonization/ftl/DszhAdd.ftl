<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="��˽�˻���ѯ����">
<@CommonQueryMacro.CommonQuery id="DszhAdd" init="false" submitMode="current" navigate="false">
	<table align="left">
		<tr valign="top">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="��˽�˻�����" fieldStr="jrjgbm,zh,zhzl,zhlx,khrq,xhrq,zhzt,bz,sfjrbzk,sfshbzk,hsjg,wfhsyy,czff,xxlx,khqd,remarks,fgmjyqd,sflmzh,khdqdm" colNm=6/>
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
	 	//closeWin();
		
	 }
	 
	 function btBack_onClick()
	 {
	 	//closeWin();
		window.location.href = "${contextPath}/fpages/dataaudit/ftl/DszhQuery.ftl";
	 }


</script>
</@CommonQueryMacro.page>
