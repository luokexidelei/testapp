package com.jdbc;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import com.jdbcUtil.JdbcUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class jdbcTest {
	
	/**
	 * Statement
	 * @throws Exception
	 */
	
	@Test
	public void test1() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		String sql = "insert into mydb1 values('1','zhangSan', '123')";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		String sql1 = "select * from mydb1";
		ResultSet rs = stmt.executeQuery(sql1);
		while(rs.next()) {
			String id = rs.getString(1);
			String username = rs.getString(2);
			String password = rs.getString(3);
			System.out.println(id + "-----" +username + "-----" + password);
		}
	}
	/**
	 * PreparedStatement
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		//注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获得链接
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		String sql = "insert into mydb1 values(?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, 2);
		pstmt.setString(2, "liSi");
		pstmt.setString(3, "qwer");
		pstmt.execute();
	}
	@Test
	/**
	 * DBCP数据库连接池
	 */
	public void test3() throws Exception {
		/**
		 * 基本配置
		 */
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		/**
		 * 其他配置
		 */
		dataSource.setMaxActive(20);
		dataSource.setMaxIdle(10);
		dataSource.setMinIdle(2);
		dataSource.setInitialSize(10);
		//dataSource.wait(1000);
		
		Connection conn = dataSource.getConnection();
		System.out.println(conn.getClass().getName());
		conn.close();
		
	}
	/**
	 * C3P0
	 * @throws IOException
	 * @throws PropertyVetoException 
	 * @throws SQLException 
	 */
	@Test
	public void test4() throws IOException, PropertyVetoException, SQLException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		/**
		 * 四大参数配置
		 */
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		ds.setUser("root");
		ds.setPassword("root");
		ds.setDriverClass("com.mysql.jdbc.Driver");
		/**
		 * 其他参数配置
		 */
		ds.setAcquireIncrement(5);
		ds.setMaxPoolSize(50);
		ds.setInitialPoolSize(10);
		ds.setMinPoolSize(2);
		
		Connection conn = ds.getConnection();
		System.out.println(conn.getClass().getName());
		
		conn.close();
		
	}
	@Test
	public void test5() throws SQLException  {
		DataSource ds = JdbcUtil.getDataSource();
		String sql = "select * from mydb1";
		PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String psd = rs.getString(3);
			System.out.println(id + "---" + name + "---" + psd);
		}
		
	}
}
