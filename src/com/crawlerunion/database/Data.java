package com.crawlerunion.database;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Program Purpose: Get Site and Search Program
// Program input: N/A
// Program output: N/A
// Program data/time: 2015/7/1 13:32
// Create by: Jack
// Create time: 2015/7/1
// Program start
public class Data {

	// Program Purpose: Get Search Function
	// Program input: boolean, String, String,String, List<SqlBean>
	// Program output:List
	// Program data/time: 2015/7/1 13:32
	// Create by: Jack
	// Create time: 2015/7/1
	// Program start
	@SuppressWarnings({ "static-access", "unchecked" })
	public <T> List<T> data(Map<String, Object> map,Class<?> cla) throws SQLException, InstantiationException, IllegalAccessException {
		DBUtil db = new DBUtil();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSetMetaData m = null;
		conn = db.getConn("extractor");
		ArrayList<Object> ret = new ArrayList<Object>();

		String sql = "select ir_sql_clause from ir_sql_tbl "
				+ "where ir_id = '" + map.get("DataId") + "' and ir_prog_name='" + map.get("DataProg")
				+ "' and ir_func_name='" + map.get("DataFunc") + "' and ir_func_seq=1";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			sql = rs.getString("ir_sql_clause");
		}
		db.closeConn(rs, pstmt, conn);
		
		Field[] field = cla.getDeclaredFields();
		db = new DBUtil();
		conn = db.getConn(map.get("DataBase").toString());
		String regex = "\\{\\#(.*?)\\}";
		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(sql);
	    while (matcher.find()) {
	           System.out.println(matcher.group(1));
	           map.get(matcher.group(1));
	           sql = sql.replace("{#"+matcher.group(1)+"}", "'"+map.get(matcher.group(1))+"'");
	           
	    }
		System.out.println("sql:"+sql);
		pstmt = conn.prepareStatement(sql);
		if (map.get("DataSelect").equals("true")) {
			rs = pstmt.executeQuery();
			m = rs.getMetaData();
			int columns = m.getColumnCount();
			if (columns > 0) {
				while (rs.next()) {
					Object bean = cla.newInstance();
					for(int i=0;i<field.length;i++){
						field[i].setAccessible(true);
						System.out.println(field[i].getType().getSimpleName());
						switch(field[i].getType().getSimpleName()){
						case "Long":field[i].set(bean,rs.getLong(field[i].getName()));break;
						case "Timestamp":field[i].set(bean,rs.getTimestamp(field[i].getName()));break;
						case "Array":field[i].set(bean,rs.getArray(field[i].getName()));break;
						case "AsciiStream":field[i].set(bean,rs.getAsciiStream(field[i].getName()));break;
						case "BigDecimal":field[i].set(bean,rs.getBigDecimal(field[i].getName()));break;
						case "int":field[i].set(bean,rs.getInt(field[i].getName()));break;
						default:field[i].set(bean,rs.getString(field[i].getName()));
							break;
						}
					}
					ret.add(bean);
				}
			}
		} else {
			pstmt.executeUpdate(sql);
			return null;
		}
		//
		db.closeConn(rs, pstmt, conn);

		return (List<T>) ret;
	}
	// Program end

}
// Program end

