<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<#assign bean=JspTaglibs["/WEB-INF/struts-bean.tld"] />
<@CommonQueryMacro.page title="���˽����˻�������Ϣ">
   	<script language="javascript" src="${contextPath}/templets/easyui/jquery-1.7.2.min.js"></script>    
    <script type="text/javascript" src="${contextPath}/templets/updata/jquery.uploadify-3.1.min.js"></script>  
<@CommonQueryMacro.CommonQuery id="DszhMessageInfo" init="true" submitMode="all" navigate="false">
<table width="1349px">

	<tr>
		<td>
			<@CommonQueryMacro.Interface id="interface" label="���˽����˻�������Ϣ" btnNm="��ѯ" colNm=8/>
		</td>
	</tr>
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btBack"  fieldStr="messagetype,messagename,borndate,messagestatus"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			<br/>
		</td>
	</tr>
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">
/*
function datatable1_operation_onRefresh(cell,value,record) {
		if(record) {
			cell.innerHTML="<center><a href=\"JavaScript:Send()\">����</a></center>";
			
		}else {//�������ڼ�¼ʱ
		 cell.innerHTML="&nbsp;";
		}	
	}
*/
function datatable1_messagename_onRefresh(cell,value,record) {
		if(record!=null) {
		
		var messagename=record.getValue("messagename");
		var datadate=record.getValue("datadate");
		var dateee = Todate(datadate);
			cell.innerHTML = "<a href=\"/TopReport1.1/btLoad?messagename="+messagename+"&datadate="+dateee+"\">"+messagename+"</a>";
		}else {//�������ڼ�¼ʱ
		 cell.innerHTML = "";
		}	
	}
/*
function Download(datadate,messagename){
	var dateee = Todate(datadate);
    $.ajax({
        url:"/TopReport1.1/btLoad",
        type:"post",
        async:false,   //�Ƿ�Ϊ�첽����
        cache:false,  //�Ƿ񻺴���
        data:{datadate:dateee,messagename:messagename},
        dataType:"json",
        success:function (data) {
        	alert(data);
        },
        error:function (err) {
            alert("ʧ��");
        }
    }); 
}
*/
function btBack_onClick(button) {

	var date = new Date();
    date.setTime(date.getTime()-24*60*60*1000);
    if(date.getDate() < 10) {
    	today_date = "0" + date.getDate();
    }else {
    	today_date = date.getDate();
    }
    
    if((date.getMonth() + 1) < 10) {
    	var month = "0" + (date.getMonth() + 1);
    }else {
    	var month = date.getMonth() + 1;
    }
    var currentDate = date.getFullYear() + month + today_date;
    
	window.location.href="${contextPath}/fpages/dataaudit/ftl/DszhQuery.ftl?jlrq="+currentDate;
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
