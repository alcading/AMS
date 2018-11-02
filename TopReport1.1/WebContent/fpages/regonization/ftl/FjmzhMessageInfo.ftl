<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>	
<#assign bean=JspTaglibs["/WEB-INF/struts-bean.tld"] />	
<@CommonQueryMacro.page title="非居民涉税账户报文信息">	
   	<script language="javascript" src="${contextPath}/templets/easyui/jquery-1.7.2.min.js"></script>    	
    <script type="text/javascript" src="${contextPath}/templets/updata/jquery.uploadify-3.1.min.js"></script>  	
<@CommonQueryMacro.CommonQuery id="FjmzhMessageInfo" init="true" submitMode="all" navigate="false">	
<table width="1349px">	
	
	<tr>	
		<td>	
			<@CommonQueryMacro.Interface id="interface" label="非居民涉税账户报文信息" btnNm="查询" colNm=8/>	
		</td>	
	</tr>	
		
	<tr>	
		<td>	
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar="btBack"  fieldStr="messageType,messageName,importDate,operation"  width="100%" hasFrame="true" height="300" readonly="true"/>	
		</td>	
	</tr>	
</table>
<iframe id="filedownloadfjmzh"  style="display: none;"></iframe>	
</@CommonQueryMacro.CommonQuery>	
	
<script language="javascript">	
function datatable1_operation_onRefresh(cell,value,record) {	
	if(record) {	
		var messageName=record.getValue("messageName");	
		var importDate=record.getValue("importDate");
		cell.innerHTML="<center><a href=\"JavaScript:messageLoad('"+messageName+"')\">报文下载</a></center>";	
	}else {//当不存在记录时	
	 cell.innerHTML="&nbsp;";	
	}		
}

window.onload=function(){
	
	var date = new Date();
    date.setTime(date.getTime());
    if(date.getDate() < 10) {
    	today_date = "0" + date.getDate();
    }else {
    	today_date = date.getDate();
    }
    var currentDate = date.getFullYear()+"-" + (date.getMonth()+1) + "-" + today_date;
    FjmzhMessageInfo_interface_dataset.setValue("importDate", currentDate);
}

function messageLoad(messageName){
	document.getElementById("filedownloadfjmzh").src ="${contextPath}/FjmLoad?messageName="+messageName;	
}	
	
function btBack_onClick()
{
	window.location.href = "${contextPath}/fpages/dataaudit/ftl/FjmzhQuery.ftl";
}

</script>	
</@CommonQueryMacro.page>