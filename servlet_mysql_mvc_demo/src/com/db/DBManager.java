package com.db;
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/* 数据库连接池的数据链接类，产生数据链接对象  */
public class DBManager {

	private static DataSource ds = null;// 声明数据源对象

	public static DataSource getDataSource() throws Exception// 从xml中获取数据源的的函数
	{

		if (null == ds) {
			Context initContext = new InitialContext();// 上下文初始化
			if (null == initContext) {
				throw new Exception("No Context");
			}
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("sysdemo");// 获取数据源对象
		}

		return (ds);
	}

	/* 获取数据库链接池链接对象函数 */

	public static Connection getConnection() {
		try {
			Connection conn = getDataSource().getConnection();// 从xml配置的数据库连接池获取链接
			if (null != conn) {
				return (conn);// 产生了数据库连接池的链接并返回
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
