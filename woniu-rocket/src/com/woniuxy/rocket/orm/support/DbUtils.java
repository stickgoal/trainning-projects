package com.woniuxy.rocket.orm.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.pool.DruidDataSource;
import com.woniuxy.rocket.orm.exceptions.PersistException;

public class DbUtils {

	private static DruidDataSource dds = null;

	public static void init(String url, String username, String password, String driverClassName) {

		dds = new DruidDataSource();
		dds.setDriverClassName(driverClassName);
		dds.setUrl(url);
		dds.setUsername(username);
		dds.setPassword(password);
	}

	/*
	 * 获取连接
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = dds.getConnection();
		} catch (SQLException e) {
			System.err.println("连接获取失败");
			e.printStackTrace();
		}
		return con;
	}

	/*
	 * 归还连接
	 */

	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("连接归还失败");
			e.printStackTrace();
		}
	}

	public int update(String sql, Object... params) {
		Connection conn = DbUtils.getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (int i = 1; i <= params.length; i++) {
				pstmt.setObject(i, params[i - 1]);
			}
			System.out.println("执行更新语句："+pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistException("sql异常", e);
		} finally {
			DbUtils.close(conn);
		}
	}

	public <T> List<T> selectList(String sql, Adapter<T> adapter, Object... params) {
		Connection conn = DbUtils.getConnection();
		List<T> resultList = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (int i = 1; i <= params.length; i++) {
				pstmt.setObject(i, params[i - 1]);
			}
			System.out.println("执行查询语句："+pstmt);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				resultList.add(adapter.adapt(rs));
			}
			return resultList;
		} catch (SQLException e) {
			throw new PersistException("sql异常", e);
		} finally {
			DbUtils.close(conn);
		}
	}

	public <T> T selectOne(String sql, Adapter<T> adapter, Object... params) {
		List<T> resultList = selectList(sql, adapter, params);
		if (resultList.size() == 0) {
			return null;
		}
		if (resultList.size() != 1) {
			throw new PersistException("结果个数不是1，共" + resultList.size() + "个");
		}
		return resultList.get(0);
	}

	public static interface Adapter<T> {

		T adapt(ResultSet rs);
	}

}
