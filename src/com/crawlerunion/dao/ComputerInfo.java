package com.crawlerunion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.crawlerunion.database.DBUtil;
import com.crawlerunion.module.ClientComputer;
import com.crawlerunion.operate.ClientComputerOperate;

public class ComputerInfo {
	private ClientComputer computer ;
	private ClientComputerOperate computer_operate;
	private DBUtil dbu;
	
	public ComputerInfo() {
		// TODO Auto-generated constructor stub
		computer = new ClientComputer();
		computer_operate = new ClientComputerOperate();
		dbu = new DBUtil();
	}

	public ClientComputer setComputerInfo(){
		computer.setMemory(computer_operate.getMemory());
		computer.setEnable_memory(computer_operate.getEnableMemory());
		computer.setOs_name(computer_operate.getOsName());
		computer.setOs_version(computer_operate.getOsVersion());
		return computer;
		
	}
	
	public void getComputer(){
		System.out.println(computer.getMemory());
		System.out.println(computer.getEnable_memory());
		System.out.println(computer.getOsname());
		System.out.println(computer.getOs_version());
		System.out.println(computer.getIpaddress());
		
	}
	
	
	
	
	
	
	
	
	
	public void insertdata() throws SQLException{
		Connection con = dbu.getConn("crawlerunion");
		String sql = "insert into client_computer (memory, enable_memory, os_name, os_version) VALUES ('" +computer.getMemory() +"','"
				+ computer.getEnable_memory()+"','" + computer.getOsname() + "','" + computer.getOs_version() + "')";
		PreparedStatement pstmt = con.prepareStatement(sql);
		System.out.println(sql);
		pstmt.execute();
		
	}
	
	
}
