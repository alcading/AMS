<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<#assign bean=JspTaglibs["/WEB-INF/struts-bean.tld"] />
<@CommonQueryMacro.page title="���˽����˻�">

<@CommonQueryMacro.CommonQuery id="DszhQuery" init="true" submitMode="all" navigate="false">
<table width="1349px">

	<tr>
		<td>
			<@CommonQueryMacro.Interface id="interface" label="���˽����˻�" btnNm="��ѯ" colNm=8/>
		</td>
	</tr>
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btAdd,-,btMod,-,btDel,-,btFeedback,-,btMessageInfo"  fieldStr="select[40],xxlx,ckrxm,ckrsfzjzl,ckrsfzjhm,jrjgbm,zh,zhzl,zhzt,jlrq,report_status,jgdm"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
		</td>
	</tr>
</table>
<iframe id="filedownloadfrm"  style="display: none;"></iframe>
<span id="button-tools" style="padding-left:10px"><@CommonQueryMacro.Button id= "btSave"/>&nbsp;<span id="message" ></span></span>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">
window.onload=function(){
	
	var date = new Date();
    date.setTime(date.getTime()-24*60*60*1000);
    if(date.getDate() < 10) {
    	today_date = "0" + date.getDate();
    }else {
    	today_date = date.getDate();
    }
    var currentDate = date.getFullYear()+"-" + (date.getMonth()+1) + "-" + today_date;
    DszhQuery_interface_dataset.setValue("jlrq", currentDate);
}

//��λһ�м�¼
	function locate(zh) {
	
		var record = DszhQuery_dataset.find(["zh"],[zh]);
		if(record) {
			DszhQuery_dataset.setRecord(record);
		}
	}


$('#DszhQuery_interface_dataset_btnSubmit').after($('#button-tools'));

function btSave_onClickCheck(button){
		
		loading();
		exportTxt();
		return false;
}

function exportTxt(){

	var jlrq = DszhQuery_dataset.getValue("jlrq");
	var date = Todate(jlrq);
    $.ajax({
        url:"/TopReport1.1/DszhQueryOutput",
        type:"get",
        async:true,   //�Ƿ�Ϊ�첽����
        cache:false,  //�Ƿ񻺴���
        data:{jlrq:date},
        dataType:"text",
        success:function (data) {
        	alert(data);
        	loaded()
        },
        error:function (err) {
            alert("ʧ��");
        }
    }); 
}
//���ֲ�
function loading() {
    var mask_bg = document.createElement("div");
    mask_bg.id = "mask_bg";
    mask_bg.style.position = "absolute";
    mask_bg.style.top = "30%";
    mask_bg.style.left = "35%";
    mask_bg.style.width = "15%";
    mask_bg.style.height = "10%";
    mask_bg.style.backgroundColor = "#000";
    mask_bg.style.opacity = 0.9;
    mask_bg.style.zIndex = 10001;
    document.body.appendChild(mask_bg);
 
    var mask_msg = document.createElement("div");
    mask_msg.style.position = "absolute";
    mask_msg.style.top = "35%";
    mask_msg.style.left = "0%";
    mask_msg.style.width = "100%";
    mask_msg.style.backgroundColor = "white";
    mask_msg.style.border = "#336699 1px solid";
    mask_msg.style.textAlign = "center";
    mask_msg.style.fontSize = "1.1em";
    mask_msg.style.fontWeight = "bold";
    mask_msg.style.padding = "0.5em 0em 0.5em 0em";
    mask_msg.style.zIndex = 10002;
    mask_msg.innerText = "����ִ��,���Ժ�...";
    mask_bg.appendChild(mask_msg);
}
//�ر����ֲ�
function loaded() {
    var mask_bg = document.getElementById("mask_bg");
    if (mask_bg != null)
        mask_bg.parentNode.removeChild(mask_bg);
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
	
	return confirm("ȷ��ɾ����¼��");
}


function btMod_onClickCheck() {

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
	
	showWin("���˽����˻��޸�","/fpages/regonization/ftl/DszhQueryUpdate.ftl?zh="+zh);
	
}

function btFeedback_onClick(){
	showFeedback();
}

//�����ļ�����ҳ��
function showFeedback(){
	showWin("�����ļ�����","${contextPath}/fpages/dataaudit/ftl/DszhFeedback.ftl");
	//popWin.showWin("1000","800","����","${contextPath}/fpages/dataaudit/ftl/DszhFeedback.ftl");
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

function Todate(num) {
        num = num + "";
        var date = "";
        var month = new Array();
        month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4; month["May"] = 5; month["Jun"] = 6;
        month["Jul"] = 7; month["Aug"] = 8; month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
        var week = new Array();
        week["Mon"] = "һ"; week["Tue"] = "��"; week["Wed"] = "��"; week["Thu"] = "��"; week["Fri"] = "��"; week["Sat"] = "��"; week["Sun"] = "��";
        str = num.split(" ");
        if(str[2] < 10) {
        	str[2] = 0 + str[2];
        }
        date = str[5];
        if(month[str[1]] < 10){
        	date = date + "0" + month[str[1]] + str[2];
        }else {
        	date = date + month[str[1]] + str[2];
        }
        

        return date;
    }
</script>
</@CommonQueryMacro.page>
