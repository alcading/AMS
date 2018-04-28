<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="反馈报文处理">
	<link rel="stylesheet" type="text/css" href="${contextPath}/templets/updata/uploadify.css">  
   	<script language="javascript" src="${contextPath}/templets/easyui/jquery-1.7.2.min.js"></script>    
    <script type="text/javascript" src="${contextPath}/templets/updata/jquery.uploadify-3.1.min.js"></script>   
<@CommonQueryMacro.CommonQuery id="DszhFeedback" init="true" submitMode="all" navigate="false">
<table width="1349px">
	<tr>
		<td colspan="2">
			<input type="file" name="uploadify" id="uploadify" />
		</td>
	</tr>
	
	<tr>
		<td>
			
		</td>
	</tr>
	<tr>
		<td>
			<@CommonQueryMacro.DataTable id="datatable1" paginationbar=""  fieldStr="date,feedback_type,file_name,success_num,fail_num"  width="100%" hasFrame="true" height="300" readonly="true"/>
			
		</td>
	</tr>
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">

$(document).ready(function() {	
	
	       var swf='${contextPath}/templets/updata/uploadify.swf';
	       
	       var uploader='${contextPath}/scripts/FeedbackUpload?someKey="a"';	 
	       
		   initUpLoadify(getDatasetByID('DszhFeedback_dataset'),swf,uploader);
		   
     });
function initUpLoadify(dataset, s, u) {
	$("#uploadify")
			.uploadify(
					{
						'swf' : s,
						'uploader' : u,
						'method' : 'GET',
						'formData' : {
							'someKey' : 'someValue',
							'someOtherKey' : 1
						},
						'simUploadLimit' : 1,
						'sizeLimit' : 19871202,
						'buttonText' : '上传文件',
						'queueSizeLimit' : 500,
						'onSelect' : function(file) {
							var id = file.name.split('.')[0]+file.name.split('.')[1];
							
							$('#' + id + 'progress').css("display", "inline");
						},
						'onUploadComplete' : function(file) {
							var id = file.name.split('.')[0]+file.name.split('.')[1];
							// $('#'+id+'answer').html('上传成功');
							$('#' + id + 'ishave').html('已存在');
							$('#' + id + 'ishavehave').val('true');
							// $('#'+id+'upstatus').html('结束上传');
						},
						'onUploadError' : function(file, errorCode, errorMsg,errorString) {
							   var id = file.name.split('.')[0]+file.name.split('.')[1];
							   if($('#'+id+'progress').css("width")=='308px'){
								  return;
							   }
							   $('#'+id+'progress').css("display","none");
						},
						'onUploadStart' : function(file) {
							var id = file.name.split('.')[0]+file.name.split('.')[1];
							// $('#'+id+'upstatus').html('开始上传');
						},
						'onUploadProgress' : function(file, bytesUploaded,
								bytesTotal, totalBytesUploaded, totalBytesTotal) {
							var id = file.name.split('.')[0]+file.name.split('.')[1];
							var widthp = (bytesUploaded / bytesTotal) * 100;
							$('#' + id + 'progress').find(
									".uploadify-progress-bar").css("width",
									widthp + "%");
							// $('#'+id+'progress').find("span").text(widthp +
							// "%");
							$('#' + id + 'progressspan').html(
									Math.floor(widthp) + "%");

						},
						'onClearQueue' : function(queueItemCount) {

						}
					});
}
</script>
</@CommonQueryMacro.page>
