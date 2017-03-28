package com.crawlerunion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crawlerunion.database.DBUtil;
import com.crawlerunion.module.ClientComputer;
import com.crawlerunion.operate.ClientComputerOperate;

public class ComputerInfo {
	private ClientComputer computer ;
	private ClientComputerOperate computer_operate;
	
	public ComputerInfo() {
		// TODO Auto-generated constructor stub
		computer = new ClientComputer();
		computer_operate = new ClientComputerOperate();
	}

	public ClientComputer setComputerInfo(){
		computer.setMemory(computer_operate.getMemory());
		computer.setEnable_memory(computer_operate.getEnableMemory());
		computer.setOs_name(computer_operate.getOsName());
		computer.setOs_version(computer_operate.getOsVersion());
		computer.setMacaddress(computer_operate.getMACAddress());
		return computer;
		
	}
	
	public void getComputer(){
		System.out.println(computer.getMemory());
		System.out.println(computer.getEnable_memory());
		System.out.println(computer.getOsname());
		System.out.println(computer.getOs_version());
		System.out.println(computer.getIpaddress());
		System.out.println(computer.getMacaddress());
		
	}
			
	
	
	public void insertdata(){
		DBUtil dbu = new DBUtil();
		Connection conn = dbu.getConn("crawlerunion");
		ResultSet rs = null;
		String sql = "insert into client_computer (memory, enable_memory, os_name, os_version, macaddress) VALUES ('" +computer.getMemory() +"','"
				+ computer.getEnable_memory()+"','" + computer.getOsname() + "','" + computer.getOs_version() + "','" + computer.getMacaddress() + "')";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(rs, pstmt, conn);
		}
	}
	
	
}
