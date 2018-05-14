<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="个人结算账户修改">
<@CommonQueryMacro.CommonQuery id="DszhQueryUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top">
			<td valign="top">
				<@CommonQueryMacro.GroupBox id="guoup1" label="个人结算账户修改" expand="true">
						<table frame=void class="grouptable" width="100%">
							<tr>
								<td align="center" nowrap class="labeltd">报送状态</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="report_status" /></td>
								<td align="center" nowrap class="labeltd">数据日期</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="jlrq" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">存款人姓名 </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ckrxm"/></td>
								<td align="center" nowrap class="labeltd">存款人身份证件种类</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ckrsfzjzl" /></td>
								<td align="center" nowrap class="labeltd">存款人身份证件号码</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ckrsfzjhm" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">身份证件到期日 </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="sfzjdqr"/></td>
								<td align="center" nowrap class="labeltd">发证机关所在地的地区代码</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="dqdm" /></td>
								<td align="center" nowrap class="labeltd">存款人类别</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ckrlb" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">存款人国籍/地区 </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ckrgjdq"/></td>
								<td align="center" nowrap class="labeltd">存款人性别</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ckrxb" /></td>
								<td align="center" nowrap class="labeltd">存款人邮编</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ckryb" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">存款人地址 </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ckrdz"/></td>
								<td align="center" nowrap class="labeltd">存款人电话</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ckrdh" /></td>
								<td align="center" nowrap class="labeltd">代理人名称</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="dlrmc" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">代理人身份证件种类 </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="dlrsfzjzl"/></td>
								<td align="center" nowrap class="labeltd">代理人身份证件号码</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="dlrsfzjhm" /></td>
								<td align="center" nowrap class="labeltd">代理人国籍/地区</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="dlrgjdq" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">代理人电话 </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="dlrdh"/></td>
								<td align="center" nowrap class="labeltd">开户银行金融机构编码</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="jrjgbm" /></td>
								<td align="center" nowrap class="labeltd">账号</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="zh" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">账户种类</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="zhzl"/></td>
								<td align="center" nowrap class="labeltd">账户类型</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="zhlx" /></td>
								<td align="center" nowrap class="labeltd">绑定I类账户账号</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ylzhzh" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">绑定I类账户开户银行金融机构编码</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ylzhjgbm"/></td>
								<td align="center" nowrap class="labeltd">开户日期</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="khrq" /></td>
								<td align="center" nowrap class="labeltd">销户日期</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="xhrq" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">账户状态</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="zhzt"/></td>
								<td align="center" nowrap class="labeltd">币种</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="bz" /></td>
								<td align="center" nowrap class="labeltd">是否为军人保障卡</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="sfjrbzk" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">是否为社会保障卡</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="sfshbzk"/></td>
								<td align="center" nowrap class="labeltd">核实结果</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="hsjg" /></td>
								<td align="center" nowrap class="labeltd">无法核实原因</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="wfhsyy" /></td>
							</tr>
							<tr>
								<td align="center" nowrap class="labeltd">信息类型</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="xxlx" /></td>
								<td align="center" nowrap class="labeltd">开户渠道</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="khqd" /></td>
								<td align="center" nowrap class="labeltd">是否为联名账户</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="sflmzh" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">备注</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="remarks"/></td>
								<td align="center" nowrap class="labeltd">开通的非柜面交易渠道</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="fgmjyqd" /></td>
								<td align="center" class="labeltd">开户地地区代码</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="khdqdm"/></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">是否通过验证</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ismodify"/></td>
								
							</tr>
							<tr>
								<td align="center" class="labeltd">处置方法</td>
								<td class="datatd" colspan="5"> <@CommonQueryMacro.SingleField fId="czff"/></td>
							</tr>
						</table>
				</@CommonQueryMacro.GroupBox>
			</td>
		</tr>
		<tr>
  		   	<td valign="CENTER">
				<left><@CommonQueryMacro.Button id= "btBack"/></left>&nbsp;
				<left><@CommonQueryMacro.Button id= "btSave"/></left>&nbsp;
				<left><@CommonQueryMacro.Button id= "btMod"/></left>&nbsp;
				<left><@CommonQueryMacro.Button id= "btModLM"/></left>&nbsp;
				
  			</td>
		</tr>
		<tr>
		    <td>
		        <@CommonQueryMacro.GroupBox id="guoup1" label="校验信息" expand="true">
		        	<table frame=void class="grouptable" width="100%">
						<tr>
							<td align="center" nowrap class="labeltd">校验信息</td>
							<td  class="datatd"> <@CommonQueryMacro.SingleField fId="checkResult" /></td>
						</tr>
					</table>
		        </@CommonQueryMacro.GroupBox>
			</td>
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
function btMod_onClick(){
	var zh = DszhQueryUpdate_dataset.getValue("zh");
	showUpdate(zh);
}

function showUpdate(zh){
	showWin("介质信息","${contextPath}/fpages/dataaudit/ftl/DszhQueryKA.ftl?zh="+zh,null,null,window);
	//window.location.href = "${contextPath}/fpages/regonization/ftl/DszhQueryLM.ftl?zh="+zh;
}

function btModLM_onClick(){
	var zh = DszhQueryUpdate_dataset.getValue("zh");
	showUpdateLM(zh);
}

function showUpdateLM(zh){
	showWin("联名账户信息","${contextPath}/fpages/dataaudit/ftl/DszhQueryLM.ftl?zh="+zh,null,null,window);
}

//非空校验
function btSave_onClickCheck(button){
	var ckrxm = DszhQueryUpdate_dataset.getValue("ckrxm");
	if(ckrxm == null || ckrxm == ""){
		return false;
	}
	
	var zhzl = DszhQueryUpdate_dataset.getValue("zhzl");
	if(zhzl == null ||zhzl == ""){
		return false;
	}
	
	var zhzt = DszhQueryUpdate_dataset.getValue("zhzt");
	if(zhzt == null || zhzt == ""){
		return false;
	}
	
	var sfshbzk = DszhQueryUpdate_dataset.getValue("sfshbzk");
	if(sfshbzk == null || sfshbzk == ""){
		return false;
	}
	
	var ckrxb = DszhQueryUpdate_dataset.getValue("ckrxb");
	if(ckrxb == null || ckrxb == ""){
		return false;
	}
	
	var jrjgbm = DszhQueryUpdate_dataset.getValue("jrjgbm");
	if(jrjgbm == null || jrjgbm == ""){
		return false;
	}
	
	var khrq = DszhQueryUpdate_dataset.getValue("khrq");
	if(khrq == null || khrq == ""){
		return false;
	}
	
	var bz = DszhQueryUpdate_dataset.getValue("bz");
	if(bz == null || bz == ""){
		return false;
	}
	
	var hsjg = DszhQueryUpdate_dataset.getValue("hsjg");
	if(hsjg == null || hsjg == ""){
		return false;
	}
	
	var xxlx = DszhQueryUpdate_dataset.getValue("xxlx");
	if(xxlx == null || xxlx == ""){
		return false;
	}
	
	var zh = DszhQueryUpdate_dataset.getValue("zh");
	if(zh == null || zh == ""){
		return false;
	}
	
	var sfjrbzk = DszhQueryUpdate_dataset.getValue("sfjrbzk");
	if(sfjrbzk == null || sfjrbzk == ""){
		return false;
	}
	
	var khqd = DszhQueryUpdate_dataset.getValue("khqd");
	if(khqd == null || khqd == ""){
		return false;
	}
	
	closeWin();
}
</script>
</@CommonQueryMacro.page>
