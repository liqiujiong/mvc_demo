package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.BookInfoDao;
import com.model.BookInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
        String publisher=request.getParameter("publisher");
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
			System.out.println("执行添加");
			BookInfoDao.getInstance().add(entity);
			
		}
		if(action.equals("update")){
			System.out.println("执行修改");
			BookInfoDao.getInstance().update(entity);
		}
		//根据id进行删除
		if(action.equals("del")){
			System.out.println("执行删除");
			if(entity.getBookid()>0)BookInfoDao.getInstance().del(entity.getBookid());
			if(null!=entity.getBookname()&&entity.getBookname().length()>0)
				BookInfoDao.getInstance().del(entity.getBookname());
			entity=null;
		}
		list=BookInfoDao.getInstance().Select(entity);
		
		JSONArray ja = new JSONArray();
		for(int i = 0; i < list.size();i++) {
			JSONObject jb = new JSONObject();
			jb.put("id", list.get(i).getBookid());
			jb.put("bookname", list.get(i).getBookname());
			jb.put("Price", list.get(i).getPrice());
			jb.put("Publisher", list.get(i).getPublisher());
			ja.add(jb);
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(ja.toString());
		out.flush();
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
