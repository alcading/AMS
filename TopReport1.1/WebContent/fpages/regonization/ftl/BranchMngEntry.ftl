<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<@CommonQueryMacro.page title="机构信息维护">
<table width="800px" align="left">
<tr>
<td>
<@CommonQueryMacro.CommonQuery id="Management_branchManage" init="true" submitMode="current">
	<table width="100%">
		<tr>
   			<td valign="top" colspan="2">
   				<@CommonQueryMacro.Interface id="intface" label="机构查询" colNm=4 showButton="true" />
        	</td>
        </tr>
		<tr>
   			<td>
   				<@CommonQueryMacro.PagePilot id="PagePilot"/>
   			</td>
  			
  		</tr>
  		<tr>
      		<td colspan="2">
          		<@CommonQueryMacro.DataTable id ="datatable1" paginationbar="btAdd,-,btStatus" fieldStr="brno,brname,brclass,status,st,opr" width="100%"  readonly="true"/></br>
        	</td>
        </tr>
        <tr>
      		<td colspan="2">
      		<@CommonQueryMacro.FloatWindow id="signWindow" label="" width="" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="机构信息维护"
        			  fieldStr="brno,brname,address,postno,teleno,brclass,blnUpBrcode,blnManageBrcode,brattr,otherAreaFlag,jrjgbm" colNm=4/>
        			 </br>
      				<@CommonQueryMacro.Button id= "btSave"/>
      			</div>
     		 </@CommonQueryMacro.FloatWindow>
        			
  			</td>
  		</tr>
  		<tr>
      		<td colspan="2">
      		<@CommonQueryMacro.FloatWindow id="signWindowDet" label="" width="" resize="true" defaultZoom="normal" minimize="false" maximize="false" closure="true" float="true" exclusive="true" position="center" show="false" >
      			<div align="center">
      				<@CommonQueryMacro.Group id="group1" label="机构信息维护"
        			  fieldStr="brno,brname,address,postno,teleno,brclass,blnUpBrcodeName,blnManageBrcodeName,brattr,otherAreaFlag" colNm=4/>
      			</div>
     		 </@CommonQueryMacro.FloatWindow>
        			
  			</td>
  		</tr>
  		
   </table>
</@CommonQueryMacro.CommonQuery>
</td>
</tr>
</table>
<script language="javascript">
	//定位一条记录
	function locate(id) {
		var record = Management_branchManage_dataset.find(["brcode"],[id]);
		if (record) {
			Management_branchManage_dataset.setRecord(record); 
		}
	}
	
	function openBranchDtl(id){
		locate(id);
		Management_branchManage_dataset.setFieldReadOnly("brno", true);
		Management_branchManage_dataset.setFieldReadOnly("brname", false);
		Management_branchManage_dataset.setFieldReadOnly("address", false);
		Management_branchManage_dataset.setFieldReadOnly("postno", false);
		Management_branchManage_dataset.setFieldReadOnly("teleno", false);
		Management_branchManage_dataset.setFieldReadOnly("brclass", false);
		Management_branchManage_dataset.setFieldReadOnly("blnUpBrcode", false);
		Management_branchManage_dataset.setFieldReadOnly("blnManageBrcode", false);
		Management_branchManage_dataset.setFieldReadOnly("brattr", false);
		Management_branchManage_dataset.setFieldReadOnly("otherAreaFlag", false);
		
		subwindow_signWindow.show();
	}
	
	function openBranchDtlDetail(id){
		locate(id);
		Management_branchManage_dataset.setFieldReadOnly("brno", true);
		Management_branchManage_dataset.setFieldReadOnly("brname", true);
		Management_branchManage_dataset.setFieldReadOnly("address", true);
		Management_branchManage_dataset.setFieldReadOnly("postno", true);
		Management_branchManage_dataset.setFieldReadOnly("teleno", true);
		Management_branchManage_dataset.setFieldReadOnly("brclass", true);
		Management_branchManage_dataset.setFieldReadOnly("blnUpBrcode", true);
		Management_branchManage_dataset.setFieldReadOnly("blnManageBrcode", true);
		Management_branchManage_dataset.setFieldReadOnly("brattr", true);
		Management_branchManage_dataset.setFieldReadOnly("otherAreaFlag", true);
		subwindow_signWindowDet.show();
	}
	
	function btAdd_onClick(){
		subwindow_signWindow.show();
	}
	
	function datatable1_opr_onRefresh(cell, value, record)
	{
	
		if (record) {//当存在记录时
			var lock = record.getValue("lock");
			var id = record.getValue("brcode");
			if(isTrue(lock)){
				cell.innerHTML = "<center><a href=\"Javascript:void(0);\" style=\"color:#666666\" title=\"记录已锁定，不能操作\">修改</a></center>";
			}else{
				cell.innerHTML="<center><a href=\"JavaScript:openBranchDtl('"+id+"')\">修改</a></center>";
			}
		} else {//当不存在记录时
		 cell.innerHTML="&nbsp;";
		}
	}

	function signWindow_floatWindow_beforeClose(subwindow){
		Management_branchManage_dataset.cancelRecord();
		return true;
	}
	function signWindow_floatWindow_beforeHide(subwindow){
		return signWindow_floatWindow_beforeClose(subwindow);
	}
	
	function Management_branchManage_dataset_afterInsert(dataset, mode){
		Management_branchManage_dataset.setValue2("status", "1");
	}
	//展示对比功能的js
	function datatable1_brno_onRefresh(cell, value, record){
	if(record!=null){
		var sta = record.getValue("st");
		var id = record.getValue("brcode");
		var brno=record.getValue("brno");


		cell.innerHTML = "<a href=\"Javascript:showDetail('"+id+"','"+sta+"')\">"+brno+"</a>";

	} else {
		cell.innerHTML = ""
	}
}


function showDetail(id,sta){

	var paramMap = new Map();
	paramMap.put("id",id);
	paramMap.put("st",sta);
	paramMap.put("action","detail");
	paramMap.put("flag","0");
	loadPageWindows("partWin", "机构管理","/fpages/regonization/ftl/branchManageDetail.ftl", paramMap, "winZone");
}
	function btStatus_onClickCheck(button) {
		var status = Management_branchManage_dataset.getValue("status");
		 var lock = Management_branchManage_dataset.getValue("lock");
		if(status == '0'){
			if(confirm("确认将该机构设置为有效?")){
			    Management_branchManage_dataset.setParameter("statu", "1");
			      
				return true;
			}else{
				return false;
			}
		} else {
			if(confirm("确认将该机构设置为无效?")){
			
				Management_branchManage_dataset.setParameter("statu", "0");
				
				return true;
			}else{
				return false;
			}
		}
	}
	function btStatus_postSubmit(button) {
		alert("设置成功");
        Management_branchManage_dataset.flushData(Management_branchManage_dataset.pageIndex);
	}
    function Management_branchManage_dataset_afterScroll(dataset){
		 
		 
		 <#--
		 var  v_brcode = Management_branchManage_dataset.getValue("brcode");
		 var  v_brclass = Management_branchManage_dataset.getValue("brclass");
		  //数据库中的记录。
		  if ( v_brcode!="" ){
		    Management_branchManage_dataset.setFieldReadOnly("brno",true);
		    Management_branchManage_dataset.setFieldReadOnly("brname",false);
		  }else{
		    Management_branchManage_dataset.setFieldReadOnly("brno",false);
		    Management_branchManage_dataset.setFieldReadOnly("brname",false);
		  }
		  if ( v_brclass =="1" ){
		  	Management_branchManage_dataset.setFieldReadOnly("blnUpBrcode",true);
		  }else{
		  	Management_branchManage_dataset.setFieldReadOnly("blnUpBrcode",false);
		  }
		  return true;
		  -->
		  var lock = Management_branchManage_dataset.getValue("lock");
		  if(isTrue(lock)){
		  	btStatus.disable(true);
		  }else{
		  	btStatus.disable(false);
		  }
		  
	}
	function Management_branchManage_dataset_afterChange(dataset,field){
			if(field.name=="postno"){
			v_postno=Management_branchManage_dataset.getValue("postno");
				if(isNaN(v_postno)){
					alert("字段【邮政编码】必须为数字");
					Management_branchManage_dataset.setValue2("postno","");
					return false;
				}else if( v_postno.indexOf('-') != -1){
					alert("字段【邮政编码】必须为数字");
					Management_branchManage_dataset.setValue2("postno","");
					return false;
				}else if(v_postno.length<6&&v_postno.length!=0){
					alert("字段【邮政编码】必须为6位");
					Management_branchManage_dataset.setValue2("postno","");
					return false;
				}
				return true;
			}
			if(field.name=="teleno"){
				var v_teleno = Management_branchManage_dataset.getValue("teleno");
	    		var validChar = "0123456789-";
 				for (var i = 0; i < v_teleno.length; i++){
  				var c = v_teleno.charAt(i);
  				if ( validChar.indexOf(c) == -1){
  				alert("字段【联系电话】只能包含-和数字");
  				Management_branchManage_dataset.setValue2("teleno","");
  				return false;
  			}
 		}
			}
		}

	function btnAdd_onClick(button){
		var  v_brcode = Management_branchManage_dataset.getValue("brcode");
		//数据库中的记录。
		if (v_brcode!=""  ){
			Management_branchManage_dataset.setFieldReadOnly("brno",true);
			Management_branchManage_dataset.setFieldReadOnly("brname",false);
		}else{
			Management_branchManage_dataset.setFieldReadOnly("brno",false);
			Management_branchManage_dataset.setFieldReadOnly("brname",false);
		}
		
		Management_branchManage_dataset.setFieldReadOnly("address", false);
		Management_branchManage_dataset.setFieldReadOnly("postno", false);
		Management_branchManage_dataset.setFieldReadOnly("teleno", false);
		Management_branchManage_dataset.setFieldReadOnly("brclass", false);
		Management_branchManage_dataset.setFieldReadOnly("blnUpBrcode", false);
		Management_branchManage_dataset.setFieldReadOnly("blnManageBrcode", false);
		Management_branchManage_dataset.setFieldReadOnly("brattr", false);
		Management_branchManage_dataset.setFieldReadOnly("otherAreaFlag", false);
	}

	  //function btnAdd_onClickCheck(button)
      //{
       // return checkValue();
       //}

      function btSave_postSubmit(button)
      {
			button.url="#";
        	//alert("保存成功");
        	subwindow_signWindow.hide();
        	Management_branchManage_dataset.flushData(Management_branchManage_dataset.pageIndex);
      }

      function btSave_onClickCheck(button)
      {
		    return checkValue();
      }

      function checkValue()
		{
			if(Management_branchManage_dataset.getValue("blnUpBrcode")==""&&Management_branchManage_dataset.getValue("brclass")!="1")
			{
				alert("字段[上级机构]不应为空。");
	 	 		return false;
			}

			if(Management_branchManage_dataset.getValue("brclass")=="")
			{
				alert("字段[机构级别]不应为空。");
	 	 		return false;
			}
			return true;
		}

	function brclass_DropDown_onSelect(dropDown,record,editor)
	{
	   var brclass  = record.getValue("data").trim();
	   var length  = Management_branchManage_dataset.length;
		var flag = true;
	   if(length>1&&brclass=="1"){
	   		alert("只能有一个总行!");
	   		flag = false;
	   }
		if(!flag){
			return false;
		}

	   return true;
	}
	//去掉页面“归属分行”字段，但当选中“上级机构”字段时，自动给“归属分行”赋值
	function blnUpBrcode_DropDown_onSelect(dropDown,record,editor)
	{
	   var blnUpBrcode  = record.getValue("brcode").trim();
	   Management_branchManage_dataset.setValue2("blnBranchBrcode",blnUpBrcode);
	   return true;

	}

</script>
</@CommonQueryMacro.page>