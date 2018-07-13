<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<#assign bean=JspTaglibs["/WEB-INF/struts-bean.tld"] />
<@CommonQueryMacro.page title="个人结算账户">

<@CommonQueryMacro.CommonQuery id="DszhQuery" init="true" submitMode="all" navigate="false">
<table width="1349px">

	<tr>
		<td>
			<@CommonQueryMacro.Interface id="interface" label="个人结算账户" btnNm="查询" colNm=8/>
		</td>
	</tr>
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btAdd,-,btMod,-,btDel,-,btFeedback,-,btMessageInfo"  fieldStr="select[40],xxlx,ckrxm,ckrsfzjzl,ckrsfzjhm,jrjgbm,zh,zhzl,zhzt,jlrq,ismodify,report_status"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
	</tr>
</table>
<iframe id="filedownloadfrm"  style="display: none;"></iframe>
<span id="button-tools" style="padding-left:10px"><@CommonQueryMacro.Button id= "btSave"/>&nbsp;<span id="message" ><@bean.message key="DszhQuery.message" /></span></span>
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

//定位一行记录
	function locate(zh) {
	
		var record = DszhQuery_dataset.find(["zh"],[zh]);
		if(record) {
			DszhQuery_dataset.setRecord(record);
		}
	}


$('#DszhQuery_interface_dataset_btnSubmit').after($('#button-tools'));

function btSave_onClickCheck(button){
		var jlrq = DszhQuery_dataset.getValue("jlrq");
		var date = Todate(jlrq);
		document.getElementById("filedownloadfrm").src ="${contextPath}/DszhQueryOutput?jlrq="+date;
		return false;
}
 function btSave_postSubmit(button) {
	  	alert("导出成功");
	  	
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
		alert('请选择记录');
		return false;
	}
	
	return confirm("确认删除记录？");
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
		alert('请选择记录');
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
	//loadPageWindows("DszhAdd","个人结算新增","/fpages/regonization/ftl/DszhAdd.ftl",paramMap,"winZone");
}

function showUpdate(zh){
	
	showWin("个人结算账户修改","/fpages/regonization/ftl/DszhQueryUpdate.ftl?zh="+zh);
	
}

function btFeedback_onClick(){
	showFeedback();
}

//反馈文件处理页面
function showFeedback(){
	showWin("反馈文件处理","${contextPath}/fpages/dataaudit/ftl/DszhFeedback.ftl");
	//popWin.showWin("1000","800","标题","${contextPath}/fpages/dataaudit/ftl/DszhFeedback.ftl");
}
	
function btDel_postSubmit(button) {
		
		button.url="#";
		//刷新当前页
		flushCurrentPage();
}
//刷新当前页
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
        week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四"; week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";
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
