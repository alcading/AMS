<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="���˽����˻��޸�">
<@CommonQueryMacro.CommonQuery id="DszhQueryUpdate" init="true" submitMode="all" navigate="false">
	<table align="left">
		<tr valign="top">
			<td valign="top">
				<@CommonQueryMacro.GroupBox id="guoup1" label="���˽����˻��޸�" expand="true">
						<table frame=void class="grouptable" width="100%">
							<tr>
								<td align="center" nowrap class="labeltd">����״̬</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="report_status" /></td>
								<td align="center" nowrap class="labeltd">��������</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="jlrq" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">��������� </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ckrxm"/></td>
								<td align="center" nowrap class="labeltd">��������֤������</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ckrsfzjzl" /></td>
								<td align="center" nowrap class="labeltd">��������֤������</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ckrsfzjhm" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">���֤�������� </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="sfzjdqr"/></td>
								<td align="center" nowrap class="labeltd">��֤�������ڵصĵ�������</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="dqdm" /></td>
								<td align="center" nowrap class="labeltd">��������</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ckrlb" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">����˹���/���� </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ckrgjdq"/></td>
								<td align="center" nowrap class="labeltd">������Ա�</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="ckrxb" /></td>
								<td align="center" nowrap class="labeltd">������ʱ�</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="ckryb" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">����˵�ַ </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ckrdz"/></td>
								<td align="center" nowrap class="labeltd">����˵绰</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="ckrdh" /></td>
								<td align="center" nowrap class="labeltd">����������</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="dlrmc" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">���������֤������ </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="dlrsfzjzl"/></td>
								<td align="center" nowrap class="labeltd">���������֤������</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="dlrsfzjhm" /></td>
								<td align="center" nowrap class="labeltd">�����˹���/����</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="dlrgjdq" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">�����˵绰 </td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="dlrdh"/></td>
								<td align="center" nowrap class="labeltd">�������н��ڻ�������</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="jrjgbm" /></td>
								<td align="center" nowrap class="labeltd">�˺�</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="zh" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">�˻�����</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="zhzl"/></td>
								<td align="center" nowrap class="labeltd">�˻�����</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="zhlx" /></td>
								<td align="center" nowrap class="labeltd">��I���˻��˺�</td>
								<td  class="datatd"> <@CommonQueryMacro.SingleField fId="ylzhzh" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">��I���˻��������н��ڻ�������</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ylzhjgbm"/></td>
								<td align="center" nowrap class="labeltd">��������</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="khrq" /></td>
								<td align="center" nowrap class="labeltd">��������</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="xhrq" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">�˻�״̬</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="zhzt"/></td>
								<td align="center" nowrap class="labeltd">����</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="bz" /></td>
								<td align="center" nowrap class="labeltd">�Ƿ�Ϊ���˱��Ͽ�</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="sfjrbzk" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">�Ƿ�Ϊ��ᱣ�Ͽ�</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="sfshbzk"/></td>
								<td align="center" nowrap class="labeltd">��ʵ���</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="hsjg" /></td>
								<td align="center" nowrap class="labeltd">�޷���ʵԭ��</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="wfhsyy" /></td>
							</tr>
							<tr>
								<td align="center" nowrap class="labeltd">��Ϣ����</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="xxlx" /></td>
								<td align="center" nowrap class="labeltd">��������</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="khqd" /></td>
								<td align="center" nowrap class="labeltd">�Ƿ�Ϊ�����˻�</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="sflmzh" /></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">��ע</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="remarks"/></td>
								<td align="center" nowrap class="labeltd">��ͨ�ķǹ��潻������</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="fgmjyqd" /></td>
								<td align="center" class="labeltd">�����ص�������</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="khdqdm"/></td>
							</tr>
							<tr>
								<td align="center" class="labeltd">���÷���</td>
								<td class="datatd"> <@CommonQueryMacro.SingleField fId="czff"/></td>
								<td align="center" class="labeltd">�Ƿ�ͨ����֤</td>
								<td class="datatd" > <@CommonQueryMacro.SingleField fId="ismodify"/></td>
								
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
		        <@CommonQueryMacro.GroupBox id="guoup1" label="У����Ϣ" expand="true">
		        	<table frame=void class="grouptable" width="100%">
						<tr>
							<td align="center" nowrap class="labeltd">У����Ϣ</td>
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
	showWin("������Ϣ","${contextPath}/fpages/dataaudit/ftl/DszhQueryKA.ftl?zh="+zh,null,null,window);
	//window.location.href = "${contextPath}/fpages/regonization/ftl/DszhQueryLM.ftl?zh="+zh;
}

function btModLM_onClick(){
	var zh = DszhQueryUpdate_dataset.getValue("zh");
	showUpdateLM(zh);
}

function showUpdateLM(zh){
	showWin("�����˻���Ϣ","${contextPath}/fpages/dataaudit/ftl/DszhQueryLM.ftl?zh="+zh,null,null,window);
}

//�ǿ�У��
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
