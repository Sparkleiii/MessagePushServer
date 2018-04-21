<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Admin Console</title>
	<meta name="menu" content="notification" />    
</head>

<body>

<h1>Send Notifications</h1>

<%--<div style="background:#eee; margin:20px 0px; padding:20px; width:500px; border:solid 1px #999;">--%>
<div style="margin:20px 0px;">
<form action="notification.do?action=send" method="post" style="margin: 0px;" enctype="multipart/form-data">
<table width="600" cellpadding="4" cellspacing="0" border="0">
<tr>
	<td width="20%">To:</td>
	<td width="80%">
		<input type="radio" name="broadcast" value="0" checked="checked" />  所有人
        <input type="radio" name="broadcast" value="1" /> 通过客户端编号
        <input type="radio" name="broadcast" value="2" /> 通过用户名
        <input type="radio" name="broadcast" value="3" /> 通过兴趣标签
	</td>
</tr>
<tr id="trUsername" style="display:none;">
	<td>客户端编号:</td>
	<td><input type="text" id="username" name="username" value="" style="width:380px;" /></td>
</tr>
<tr id="trAlias" style="display:none;">
	<td>用户名:</td>
	<td><input type="text" id="alias" name="alias" value="" style="width:380px;" /></td>
</tr>
<tr id="trTag" style="display:none;">
	<td>兴趣标签:</td>
	<td><input type="text" id="tag" name="tag" value="" style="width:380px;" /></td>
</tr>
<tr>
	<td>标题:</td>
	<td><input type="text" id="title" name="title" value="title test" style="width:380px;" /></td>
</tr>
<tr>
	<td>内容:</td>
	<td><textarea id="message" name="message" style="width:380px; height:80px;" >Message test</textarea></td>
</tr>
<tr>
	<td>链接地址:</td>
	<td><input type="text" id="uri" name="uri" value="" style="width:380px;" />
	    <br/><span style="font-size:0.8em">例: http://www.dokdocorea.com, geo:37.24,131.86, tel:111-222-3333</span>
	</td>
</tr>
<tr>
	<td>图片:</td>
	<td><input type="file" id="image" name="image" value="" style="width:380px;" />
	    <br/><span style="font-size:0.8em" style="color:red">只允许图片</span>
	</td>
</tr>

<tr>
	<td>&nbsp;</td>
	<td><input type="submit" value="提交" /></td>
</tr>
</table> 
</form>
</div>

<script type="text/javascript"> 
//<![CDATA[
 
$(function() {
	$('input[name=broadcast]').click(function() {
		if ($('input[name=broadcast]')[0].checked) {
			$('#trUsername').hide();
			$('#trAlias').hide();
			$('#trTag').hide();
		} else if($('input[name=broadcast]')[1].checked){
			$('#trUsername').show();
			$('#trAlias').hide();
			$('#trTag').hide();
		}else if($('input[name=broadcast]')[2].checked){
			$('#trUsername').hide();
			$('#trAlias').show();
			$('#trTag').hide();
		}else if($('input[name=broadcast]')[3].checked){
			$('#trUsername').hide();
			$('#trAlias').hide();
			$('#trTag').show();
		}
	});
	
	if ($('input[name=broadcast]')[0].checked) {
			$('#trUsername').hide();
			$('#trAlias').hide();
			$('#trTag').hide();
		} else if($('input[name=broadcast]')[1].checked){
			$('#trUsername').show();
			$('#trAlias').hide();
			$('#trTag').hide();
		}else if($('input[name=broadcast]')[2].checked){
			$('#trUsername').hide();
			$('#trAlias').show();
			$('#trTag').hide();
		}else if($('input[name=broadcast]')[3].checked){
			$('#trUsername').hide();
			$('#trAlias').hide();
			$('#trTag').show();
		}
});
//]]>
</script>

</body>
</html>
