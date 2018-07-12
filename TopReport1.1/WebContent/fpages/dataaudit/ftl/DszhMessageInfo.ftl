<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<#assign bean=JspTaglibs["/WEB-INF/struts-bean.tld"] />
<@CommonQueryMacro.page title="个人结算账户报文信息">
   	<script language="javascript" src="${contextPath}/templets/easyui/jquery-1.7.2.min.js"></script>    
    <script type="text/javascript" src="${contextPath}/templets/updata/jquery.uploadify-3.1.min.js"></script>  
<@CommonQueryMacro.CommonQuery id="DszhMessageInfo" init="true" submitMode="all" navigate="false">
<table width="1349px">

	<tr>
		<td>
			<@CommonQueryMacro.Interface id="interface" label="个人结算账户报文信息" btnNm="查询" colNm=8/>
		</td>
	</tr>
	
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar=""  fieldStr="messagetype,messagename,datadate,borndate,messagestatus,operation"  width="100%" hasFrame="true" height="300" readonly="true"/>
		</td>
	</tr>
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">
function datatable1_operation_onRefresh(cell,value,record) {
		if(record) {
			cell.innerHTML="<center><a href=\"JavaScript:Delete()\">删除</a>&nbsp;<a href=\"JavaScript:Send()\">发送</a></center>";
			
		}else {//当不存在记录时
		 cell.innerHTML="&nbsp;";
		}	
	}
function datatable1_messagename_onRefresh(cell,value,record) {
		if(record!=null) {
		
		var messagename=record.getValue("messagename");
		var datadate=record.getValue("datadate");
		var dateee = Todate(datadate);
			cell.innerHTML = "<a href=\"/TopReport1.1/btLoad?messagename="+messagename+"&datadate="+dateee+"\">"+messagename+"</a>";
		}else {//当不存在记录时
		 cell.innerHTML = "";
		}	
	}
/*
function Download(datadate,messagename){
	var dateee = Todate(datadate);
    $.ajax({
        url:"/TopReport1.1/btLoad",
        type:"post",
        async:false,   //是否为异步请求
        cache:false,  //是否缓存结果
        data:{datadate:dateee,messagename:messagename},
        dataType:"json",
        success:function (data) {
        	alert(data);
        },
        error:function (err) {
            alert("失败");
        }
    }); 
}
*/
function Send(){
	alert("send");
}
function Delete(){
	alert("delete");
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
