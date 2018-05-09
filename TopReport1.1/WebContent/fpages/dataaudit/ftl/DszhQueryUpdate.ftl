<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="对私账户查询修改">
<@CommonQueryMacro.CommonQuery id="DszhQueryUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top">
			<td valign="top">
				<@CommonQueryMacro.Group id ="group1" label="对私账户查询修改" fieldStr="ckrxm,ckrsfzjzl,ckrsfzjhm,sfzjdqr,dqdm,ckrlb,ckrgjdq,ckrxb,ckryb,ckrdz,ckrdh,dlrmc,dlrsfzjzl,dlrsfzjhm,dlrgjdq,dlrdh,jrjgbm,zh,zhzl,zhlx,ylzhzh,ylzhjgbm,khrq,xhrq,zhzt,bz,sfjrbzk,sfshbzk,hsjg,wfhsyy,czff,xxlx,khqd,remarks,jlzt,jlrq,ismodify" colNm=6/>
			</td>
				</tr>
		<tr>
		   <td>
				<left><@CommonQueryMacro.Button id= "btMod"/></left>
  		   </td>
  		   <td>
  		       <input type=button value='ka'>
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
	     DszhQueryUpdate_dataset.flushData(DszhQueryUpdate_dataset.pageIndex);
	     
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
	var zh = DszhQueryUpdate_dataset.getValue("zh");
	showUpdate(zh);
}

function showUpdate(zh){

	showWin("联名账户信息","${contextPath}/fpages/regonization/ftl/DszhQueryLM.ftl?zh="+zh,null,null,window);
}
function btAdd_onClick(){
	var zh = DszhQueryUpdate_dataset.getValue("zh");
	showUpdate(zh);
}

function showUpdate(zh){
	showWin("查看卡信息","${contextPath}/fpages/regonization/ftl/DszhQueryLM.ftl?zh="+zh,null,null,window);
}
</script>
</@CommonQueryMacro.page>
