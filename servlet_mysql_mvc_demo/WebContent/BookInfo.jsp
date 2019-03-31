<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书信息管理</title>
<link href="css/pages.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="js/pages.js"></script>
<script language="javascript">
function look(){
	$("#formAction").val("look");
	//BookAjax();
	getPage(1,pageSize);
}
function del(){
	//if(document.forms[0].bookid.value==""&&document.forms[0].bookname.value=="")
	if($("#bookid").val()==""&&$("#bookname").val()=="")	//判断进不去->表单有name而没有id
	{
		alert("请输入删除信息");
		return false;
	}
	$("#formAction").val("del");
	getPage(1,pageSize);
}
function update(){
	//if(document.forms[0].bookid.value=="")
	if($("#bookid").val()=="")
	{
		alert("请输入bookid");
		return false;
	}
	if($("#bookname").val()=="")
	{
		alert("请输入bookname");
		return false;
	}
	if($("#publisher").val()=="")
	{
		alert("请输入publisher");
		return false;
	}
	if($("#price").val()=="")
	{
		alert("请输入price");
		return false;
	}
	$("#formAction").val("update");
	getPage(1,pageSize);
}
function add(){
	if($("#bookname").val()=="")
	{
		alert("请输入bookname");
		return false;
	}
	if($("#publisher").val()=="")
	{
		alert("请输入publihser");
		return false;
	}
	if($("#price").val()=="")
	{
		alert("请输入price");
		return false;
	}
	$("#formAction").val("add");
	getPage(1,pageSize);
}
</script>
</head>
<body>

<div align="center"> 
  <h2>图书操作</h2>
 <form id="bookform" action="<%=request.getContextPath() %>/BookInfoServlet" method="post">
  <input type="hidden" name="formAction" id="formAction" value="">
  <input type="hidden" name="curPage" id="curPage" value="">
  <input type="hidden" name="pageSize" id="pageSize" value="">
 图书ID：<input type="text" name="bookid" id="bookid" value="">
 图书名称：<input type="text" name="bookname" id="bookname" value=""><br>
 出版社：<input type="text" name="publisher" id="publisher" value="">
 图书价格：<input type="text" name="price" id="price" value=""><br>
 <input type="button" value="查询" onclick="return look();"/>
  <input type="button" value="删除" onclick="return del();"/>
   <input type="button" value="修改" onclick="return update();"/>
    <input type="button" value="添加" onclick="return add();"/>
</form>
<h3>数据记录显示</h3>
	<div id = pages></div>
	<div>
	<div class="pageList"><div class="pages" id="pageList"></div></div>
	</div>
</div>
</body>
</html>