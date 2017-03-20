package com.crawlerunion.operate;

import java.sql.SQLException;

import com.crawlerunion.dao.ComputerInfo;

public class Test {

	public static void main(String[] args) {
		ComputerInfo cm = new ComputerInfo();
		cm.setComputerInfo();
		try {
			cm.insertdata();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
