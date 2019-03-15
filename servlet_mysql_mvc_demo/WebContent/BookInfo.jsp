<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书信息管理</title>
<script language="javascript">
function look(){
	document.forms[0].formAction.value="look";
	document.forms[0].submit();
}
function del(){
	if(document.forms[0].bookid.value==""&&document.forms[0].bookname.value=="")
	{
		alert("请输入删除信息");
		return false;
	}
	document.forms[0].formAction.value="del";
	document.forms[0].submit();
}
function update(){
	if(document.forms[0].bookid.value=="")
	{
		alert("请输入bookid");
		return false;
	}
	if(document.forms[0].bookname.value=="")
	{
		alert("请输入bookname");
		return false;
	}
	if(document.forms[0].publisher.value=="")
	{
		alert("请输入publisher");
		return false;
	}
	if(document.forms[0].price.value=="")
	{
		alert("请输入price");
		return false;
	}
	document.forms[0].formAction.value="update";
	document.forms[0].submit();
}
function add(){
	if(document.forms[0].bookname.value=="")
	{
		alert("请输入bookname");
		return false;
	}
	if(document.forms[0].publisher.value=="")
	{
		alert("请输入publihser");
		return false;
	}
	if(document.forms[0].price.value=="")
	{
		alert("请输入price");
		return false;
	}
	document.forms[0].formAction.value="add";
	document.forms[0].submit();
}
</script>
</head>
<body>
<%
List<BookInfo> list=(List)request.getAttribute("booklist");
if(null==list) list=new ArrayList<BookInfo>();
BookInfo entity=(BookInfo)request.getAttribute("book");
if(null==entity) entity=new BookInfo();
%>
<div align="center"> 
  <h2>图书操作</h2>
 <form action="<%=request.getContextPath() %>/BookInfoServlet" method="post">
  <input type="hidden" name="formAction" value="">
 图书ID：<input type="text" name="bookid" value="<%=entity.getBookid()>0?entity.getBookid():""%>">
 图书名称：<input type="text" name="bookname"value="<%=entity.getBookname()==null?"":entity.getBookname()%>"><br>
 出版社：<input type="text" name="publisher" value="<%=entity.getPublisher()==null?"":entity.getPublisher()%>">
 图书价格：<input type="text" name="price" value="<%=entity.getPrice()>0?entity.getPrice():""%>"><br>
 <input type="button" value="查询" onclick="return look();"/>
  <input type="button" value="删除" onclick="return del();"/>
   <input type="button" value="修改" onclick="return update();"/>
    <input type="button" value="添加" onclick="return add();"/>
</form>
<h3>数据记录显示</h3>
<table border=1 width='80%'>
<tr><th>ID</th><th>图书名称</th><th>出版社</th><th>图书价格</th></tr>
<% for(int i=0;i<list.size();i++) {
	BookInfo book=list.get(i);%>
<tr>
<td><%=book.getBookid() %></td>
<td><%=book.getBookname() %></td>
<td><%=book.getPublisher()%></td>
<td><%=book.getPrice() %></td>
</tr>
<%} %>
</table>
</div>
</body>
</html>