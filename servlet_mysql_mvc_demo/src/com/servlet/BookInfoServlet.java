package com.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookInfoDao;
import com.model.BookInfo;

/**
 * Servlet implementation class BookInfoServlet
 */
public class BookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /*获取表单中数据*/
    protected BookInfo getData(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
    	//request.setCharacterEncoding("UTF-8");//只对表单post方法有用
    	BookInfo book=new BookInfo();
    	try {
    	int bookid=0;
    	if(null!=request.getParameter("bookid")&&!request.getParameter("bookid").equals(""))
    	bookid=Integer.parseInt(request.getParameter("bookid"));
    	String bookname=request.getParameter("bookname");
        if(null!=bookname) bookname=new String(bookname.getBytes("iso-8859-1"),"utf-8");
         String publisher=request.getParameter("publisher");
        if(null!=publisher)  publisher=new String(publisher.getBytes("iso-8859-1"),"utf-8");
         float price=0;
         if(null!=request.getParameter("price")&&!request.getParameter("price").equals(""))
         price=Float.parseFloat(request.getParameter("price"));
         book.setBookid(bookid);
         book.setBookname(bookname);
         book.setPublisher(publisher);
         book.setPrice(price);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return book;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<BookInfo> list=null;
		BookInfo entity=getData(request,  response);
		String action=request.getParameter("formAction")==null?"":request.getParameter("formAction");
		if(action.equals("add")){
			BookInfoDao.getInstance().add(entity);
			
		}
		if(action.equals("update")){
			BookInfoDao.getInstance().update(entity);
		}
		if(action.equals("del")){
			if(entity.getBookid()>0)BookInfoDao.getInstance().del(entity.getBookid());
			if(null!=entity.getBookname()&&entity.getBookname().length()>0)
				BookInfoDao.getInstance().del(entity.getBookname());
			entity=null;
		}
	
		//System.out.println(action+":"+entity.getBookid());
		list=BookInfoDao.getInstance().Select(entity);
		request.setAttribute("booklist", list);
		if(action.equals("look")){
			if(entity.getBookid()>0&&list.size()>0)
			request.setAttribute("book", list.get(0));
		}
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("BookInfo.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
