package com.jdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
	// �����ļ���Ĭ�����ã�Ҫ����������c3p0-config.xml������   -----��src ����
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	/**
	 * �������ӳض���
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}
	/**
	 * ��������
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
