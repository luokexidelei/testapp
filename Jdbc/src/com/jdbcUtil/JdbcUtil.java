package com.jdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
	// 配置文件的默认配置！要求你必须给出c3p0-config.xml！！！   -----在src 下面
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	/**
	 * 返回连接池对象
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}
	/**
	 * 返回链接
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
