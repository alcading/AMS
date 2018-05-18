<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<#import "/fpages/regonization/ftl/DszhQueryUpdate.ftl" as DszhQueryUpdate>

<@CommonQueryMacro.page title="���˽����˻�">

<@CommonQueryMacro.CommonQuery id="DszhQuery" init="true" submitMode="all" navigate="false">
<table width="1349px">
	<tr>
		
		<td>
			<@CommonQueryMacro.GroupBox id="guoup1" label="" expand="true">
			<table frame=void class="grouptable" width="30%">
				<tr>
					<td align="center" class="labeltd">��������</td>
					<td class="datatd"> <@CommonQueryMacro.SingleField fId="lbhdate"/></td>
				</tr>
			</table>
			</@CommonQueryMacro.GroupBox>
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.Interface id="interface" label="���˽����˻�" btnNm="��ѯ" colNm=8/>
		</td>
	</tr>
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btAdd,-,btMod,-,btDel,-,btFeedback,-,btLoad"  fieldStr="select[40],xxlx,ckrxm,ckrsfzjzl,ckrsfzjhm,jrjgbm,zh,zhzl,zhzt,jlrq,ismodify,report_status"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
	</tr>
</table>
<iframe id="filedownloadfrm"  style="display: none;"></iframe>
<span id="button-tools" style="padding-left:10px"><@CommonQueryMacro.Button id= "btSave"/>&nbsp;<span id="message" >���Ȱ��������ڲ�ѯ,�ٵ�������</span></span>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">
/*
window.onload=function(){
	DszhQuery_interface_dataset.setValue("jlrq", "2017-09-28");
}
*/

window.onload=function(){
	currentDate="2017-09-28";
    DszhQuery_interface_dataset.setValue("jlrq", currentDate);
}

$('#DszhQuery_interface_dataset_btnSubmit').after($('#button-tools'));
 function btSave_postSubmit(button) {
	  	alert("�����ɹ�");
	  	
	  } 

function btLoad_onClickCheck(button){
        var jlrq = DszhQuery_dataset.getValue("jlrq");
		document.getElementById("filedownloadfrm").src ="${contextPath}/btLoad?jlrq="+jlrq;
		return false;
  	}


function btDel_onClickCheck(button) {
	var rec = DszhQuery_dataset.firstUnit;
	var f = false;
	while(rec) {
		if (rec.getValue('select')) {
			f = true;
			//break;
		}
		rec = rec.nextUnit;
	}
	if(!f) {
		alert('��ѡ���¼');
		return false;
	}
}


function btMod_onClickCheck(button) {

	var rec = DszhQuery_dataset.firstUnit;
	
	var f = false;
	var zh = null;
	while(rec) {
		if (rec.getValue('select')) {
			zh = rec.getValue("zh");
			f = true;
			break;
		}
		rec = rec.nextUnit;
	}
	if(!f) {
		alert('��ѡ���¼');
		return false;
	}
	showUpdate(zh);
	flushCurrentPage();
	
}



//function btMod_onClick(){
//	var ckrsfzjzl = DszhQuery_dataset.getValue("ckrsfzjzl");
//	var ckrsfzjhm = DszhQuery_dataset.getValue("ckrsfzjhm");
//	var zh = DszhQuery_dataset.getValue("zh");
//	showUpdate(zh);
	
//}

function btAdd_onClick(){
	//DszhQuery_dataset.insertRecord();
	
	//var paramMap = new Map();
	//loadPageWindows("DszhAdd","���˽�������","/fpages/regonization/ftl/DszhAdd.ftl",paramMap,"winZone");
}

function showUpdate(zh){
	showWin("���˽����˻��޸�","${contextPath}/fpages/regonization/ftl/DszhQueryUpdate.ftl?zh="+zh,null,flushCurrentPage(),window);
	
}

function btFeedback_onClick(){
	showFeedback();
}

//�����ļ�����ҳ��
function showFeedback(){
	showWin("�����ļ�����","${contextPath}/fpages/dataaudit/ftl/DszhFeedback.ftl");
	//popWin.showWin("1000","800","����","${contextPath}/fpages/dataaudit/ftl/DszhFeedback.ftl");
}

function btDel_onClickCheck(button) {
		return confirm("ȷ��ɾ����¼��");
}
	
function btDel_postSubmit(button) {
		
		button.url="#";
		//ˢ�µ�ǰҳ
		flushCurrentPage();
}
//ˢ�µ�ǰҳ
function flushCurrentPage() {
	DszhQuery_dataset.flushData(DszhQuery_dataset.pageIndex);
}

</script>
</@CommonQueryMacro.page>
