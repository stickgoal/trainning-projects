package com.woniuxy.rocket.orm.support;

import com.woniuxy.rocket.orm.meta.ColumnMapping;
import com.woniuxy.rocket.orm.meta.TableMapping;

public class SqlGenerator {

	public static String byId(TableMapping table) {
		return "select * from " + table.getTableName() + " where " + table.getPkName() + "=?";
	}

	public static String all(TableMapping table) {
		return "select * from " + table.getTableName();
	}

	public static String updateById(TableMapping mapping) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("update ").append(mapping.getTableName()).append(" set ");
		for (ColumnMapping cm : mapping.getColumnMappings()) {
			if (!cm.isPk()) {
				sqlBuilder.append(cm.getColumn());
				sqlBuilder.append("=?,");
			}
		}
		sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
		sqlBuilder.append("where ");
		sqlBuilder.append(mapping.getPkName()).append("=?");

		return sqlBuilder.toString();
	}
	
	public static String deleteById(TableMapping table) {
		return "delete from " + table.getTableName() + " where " + table.getPkName() + "=?";
	}

	public static String insert(TableMapping table) {
		boolean pkAuto = false;
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("insert into ").append(table.getTableName()).append("(");
		for (ColumnMapping c : table.getColumnMappings()) {
			if(!(c.isPk()&&c.isPkAuto())) {
				sqlBuilder.append(c.getColumn()).append(",");
			}else {
				pkAuto=true;
			}
		}
		sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
		sqlBuilder.append(") values(");
		sqlBuilder.append(StringUtil.repeat("?,", pkAuto?table.getColumnMappings().size()-1:table.getColumnMappings().size()));
		sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
		sqlBuilder.append(")");
		return sqlBuilder.toString();
	}

}
