package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
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
        //if(null!=bookname) bookname=new String(bookname.getBytes("iso-8859-1"),"utf-8");
         String publisher=request.getParameter("publisher");
         System.out.println(publisher+bookname);
        //if(null!=publisher)  publisher=new String(publisher.getBytes("iso-8859-1"),"utf-8");
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
		//根据id进行删除
		if(action.equals("del")){
			if(entity.getBookid()>0)BookInfoDao.getInstance().del(entity.getBookid());
			if(null!=entity.getBookname()&&entity.getBookname().length()>0)
				BookInfoDao.getInstance().del(entity.getBookname());
			entity=null;
		}
	
		//System.out.println(action+":"+entity.getBookid());
		list=BookInfoDao.getInstance().Select(entity);
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<table border=1 width='80%'>\r\n" + "<tr><th>ID</th><th>图书名称</th><th>出版社</th><th>图书价格</th></tr>");
		for(int i = 0; i < list.size();i++) {
			sb.append("<tr>\r\n" +
						"<td>"+list.get(i).getBookid()+ "</td>\r\n" +
						"<td>"+list.get(i).getBookname()+ "</td>\r\n" +
						"<td>"+list.get(i).getPublisher()+ "</td>\r\n" +
						"<td>"+list.get(i).getPrice()+ "</td>\r\n" +
						"</tr>\r\n");
		}
		sb.append("</table>");
		out.write(sb.toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
