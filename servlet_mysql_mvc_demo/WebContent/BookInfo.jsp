<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书信息管理</title>
<script language="javascript">
function serialize(form) {
    var parts = [],
            elems = form.elements,
            i = 0,
            len = elems.length,
            filed = null;
    for (; i < len; i++) {
        filed = elems[i];
        switch (filed.type) {
            case "select-one":
            case "select-multiple":
                if (filed.name.length) {
                    var j = 0,
                            opt,
                            optLen = filed.options.length;
                    for (; j < optLen; j++) {
                        opt = filed.options[j];
                        if (opt.selected) {
                            parts.push(encodeURIComponent(filed.name) + "=" + encodeURIComponent(opt.value));
                        }
                    }
                }
                break;
            case undefined:
            case "submit":
            case "reset":
            case "file":
            case "button":
                break;
            case "radio":
            case "checkbox":
                if (!filed.checked) {
                    break;
                }
            default:
                if (filed.name.length && filed.value) {
                    parts.push(encodeURIComponent(filed.name) + "=" + encodeURIComponent(filed.value));
                }
        }
    }
    return parts.join("&");
}
function BookAjax(){
	var request = new XMLHttpRequest();
	request.open("POST","BookInfoServlet");
	var data = serialize(document.getElementById("bookform"));
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=utf-8");
	request.send(data);
	request.onreadystatechange = function(){
		if(request.readyState == 4){
			if(request.status == 200){
				document.getElementById("tab").innerHTML = request.responseText;
			}else{
				alert("发生错误"+request.status);
			}
		}
	}
}
function look(){
	document.forms[0].formAction.value="look";
	BookAjax();
}
function del(){
	if(document.forms[0].bookid.value==""&&document.forms[0].bookname.value=="")
	{
		alert("请输入删除信息");
		return false;
	}
	document.forms[0].formAction.value="del";
	BookAjax();
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
	BookAjax();
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
	BookAjax();
}
</script>
</head>
<body>

<div align="center"> 
  <h2>图书操作</h2>
 <form id="bookform">
  <input type="hidden" name="formAction" value="">
 图书ID：<input type="text" name="bookid" value="">
 图书名称：<input type="text" name="bookname"value=""><br>
 出版社：<input type="text" name="publisher" value="">
 图书价格：<input type="text" name="price" value=""><br>
 <input type="button" value="查询" onclick="return look();"/>
  <input type="button" value="删除" onclick="return del();"/>
   <input type="button" value="修改" onclick="return update();"/>
    <input type="button" value="添加" onclick="return add();"/>
</form>
<h3>数据记录显示</h3>
<div id="tab"></div>
</table>
</div>
</body>
</html>