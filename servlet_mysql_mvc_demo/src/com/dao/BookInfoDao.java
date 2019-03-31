package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.model.BookInfo;

public class BookInfoDao  extends BaseDAO{
	private static BookInfoDao _instance = null;
    private BookInfoDao(){}
    public static BookInfoDao getInstance()
    {
        if (null==_instance)
        {
            _instance = new BookInfoDao();
        }
        return _instance;
    }
    public BookInfo row2Data(ResultSet rs) throws SQLException
    {
    	BookInfo entity = new BookInfo();
    	entity.setBookid(rs.getInt("bookid"));
    	entity.setBookname(rs.getString("bookname"));
    	entity.setPublisher(rs.getString("publisher"));
    	entity.setPrice(rs.getFloat("price"));
        return entity;
    }
    /*Ìí¼Ó*/
    public void add(BookInfo entity){
    	Connection conn = super.getConn();
    	PreparedStatement pstmt = null;
    	String sql = "insert into bookinfo(Bookname,Publisher,Price) values(?,?,?);";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, entity.getBookname());
			pstmt.setString(2, entity.getPublisher());
			pstmt.setFloat(3, entity.getPrice());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			super.closeConn(conn, pstmt, null);
		}
		//System.out.println(sql);
    }
    /*Ìí¼Ó*/
    public void addId(BookInfo entity){
    	Connection conn = super.getConn();
    	PreparedStatement pstmt = null;
    	String sql = "insert into bookinfo(bookid,Bookname,Publisher,Price) values(?,?,?,?);";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, entity.getBookid());
			pstmt.setString(2, entity.getBookname());
			pstmt.setString(3, entity.getPublisher());
			pstmt.setFloat(4, entity.getPrice());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			super.closeConn(conn, pstmt, null);
		}
    //	System.out.println(sql);
    }
   /*É¾³ý*/
    public void del(int id){
    	Connection conn = super.getConn();
    	PreparedStatement pstmt = null;
    	String sql = "delete from bookinfo where bookid=?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			super.closeConn(conn, pstmt, null);
		}
    //	System.out.println(sql);
    } 
    /*É¾³ý*/
    public void del(String name){
    	Connection conn = super.getConn();
    	PreparedStatement pstmt = null;
    	String sql = "delete from bookinfo where Bookname=?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			super.closeConn(conn, pstmt, null);
		}
    //	System.out.println(sql);
    }
    /*ÐÞ¸Ä*/
    public void update(BookInfo entity){
    	del(entity.getBookid());
    	addId(entity);
    }
    /*²éÑ¯*/
    public List<BookInfo> Select(BookInfo entity){
    	if(null==entity)entity=new BookInfo();
    	Connection conn = super.getConn();
    	Statement pstmt = null;
    	ResultSet rs = null;
    	BookInfo book=null;
    	List<BookInfo> booklist=new ArrayList<BookInfo>();
    	String sql = "select * from bookinfo where 1=1 order by BookId ";
    	if(entity.getBookid()>0)
    		sql+="and bookid="+entity.getBookid();
    	if(null!=entity.getBookname()&&entity.getBookname().length()>0)
    		sql+=" and bookname like '%"+entity.getBookname()+"%'";
    	if(null!=entity.getPublisher()&&entity.getPublisher().length()>0)
    		sql+=" and Publisher like '%"+entity.getPublisher()+"%'";
    	try {
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery(sql);
			while(rs.next()){
				book=row2Data(rs);
				booklist.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			super.closeConn(conn, pstmt, rs);
		}
    	//System.out.println(sql);
    	return booklist;
    }
}
