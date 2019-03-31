<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书信息管理</title>
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script language="javascript">
//将form中的值转换为键值对(2)。{"变量名1":"值1","变量名2":"值2","变量名3":"值3"}
$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
}; 
//ajax传递数据 
function BookAjax(){
	 var targetUrl = $("#bookform").attr("action"); 	//获取表单的action属性、即servlet地址   
	  var data = $("#bookform").serializeObject();     
	   $.ajax({ 
	    type:'post',  
	    url:targetUrl, 
	    cache: false,
	    data:data, 
	   dataType:'json', 
	   contentType:'application/x-www-form-urlencoded;charset=utf-8',
	    success:function(data){
	    	//var bookinfo=eval("("+data+")");//eval()转换为json格式 没有设置dataType:'json'
	    	var bookinfo=data;// 设置dataType:'json',
	    	//alert(JSON.stringify(data)); 
	    	 //console.log(data);
	    	var htm="<table border=1 width='80%'><tr><th>ID</th><th>图书名称</th><th>出版社</th><th>图书价格</th></tr>";	    	 
	    	  for(var i = 0; i < bookinfo.length;i++){
	    		  htm+='<tr>\r\n'+
	    		    '<td>'+bookinfo[i].id+'</td>\r\n'+
	    		    '<td>'+bookinfo[i].bookname+'</td>\r\n'+
	    		    '<td>'+bookinfo[i].Publisher+'</td>\r\n'+
	    		    '<td>'+bookinfo[i].Price+'</td>\r\n'+
	    			  '</tr>\r\n';  
	    	  }
	    	  htm+="</table>"; 
	    	$("#tab").html(htm);
	    	
	    },
	    error:function(){ 
	     alert("请求失败")
	    }
	   })
	}
function look(){
	$("#formAction").val("look");
	BookAjax();
}
function del(){
	//if(document.forms[0].bookid.value==""&&document.forms[0].bookname.value=="")
	if($("#bookid").val()==""&&$("#bookname").val()=="")	//判断进不去->表单有name而没有id
	{
		alert("请输入删除信息");
		return false;
	}
	$("#formAction").val("del");
	BookAjax();
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
	BookAjax();
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
	BookAjax();
}
</script>
</head>
<body>

<div align="center"> 
  <h2>图书操作</h2>
 <form id="bookform" action="<%=request.getContextPath() %>/BookInfoServlet" method="post">
  <input type="hidden" name="formAction" id="formAction" value="">
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
<div id="tab"></div>
</table>
</div>
</body>
</html>