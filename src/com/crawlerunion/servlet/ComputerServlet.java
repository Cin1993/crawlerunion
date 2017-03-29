package com.crawlerunion.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crawlerunion.dao.ComputerInfo;
import com.crawlerunion.database.DBUtil;
import com.crawlerunion.module.ClientComputer;
import com.crawlerunion.operate.DownLoad;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/ComputerServlet")
public class ComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("type").equals("computerinfo")) {
			PrintWriter out = response.getWriter();
//			out.println("<HTML>");
//			out.println("<BODY>");
//			out.print("电脑配置：");
//			out.println("</br>");
//
//			ComputerInfo computerinfo = new ComputerInfo();
//			ClientComputer computer = computerinfo.setComputerInfo();
//			computer.setIpaddress(getIpAddr(request));
//			
//			
//			try {
//				computerinfo.insertdata();
//				DBUtil dbu = new DBUtil();
//				Connection conn = dbu.getConn("crawlerunion");
//				String sql = "update client_computer set ipaddress = '" + computer.getIpaddress() + "' where macaddress = " + "'" + computer.getMacaddress() + "'";
//				System.out.println(sql);
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.execute();
//				
//				
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			out.println("Your computer memory is : "+computer.getMemory());
//			out.println("</br>");
//			out.println("Your computer enable memory is: "+computer.getEnable_memory());
//			out.println("</br>");
//			out.println("Your operating system is: "+computer.getOsname());
//			out.println("</br>");
//			out.println("Your operating system version is: "+computer.getOs_version());
//			out.println("</br>");
//			out.println("Your IP address is: "+computer.getIpaddress());
//			out.println("</br>");
//			out.println("Your MACaddress is: "+computer.getMacaddress());
			
			//check North American IPAddress
			//if (enableIpaddress(computer.getIpaddress())){
				DownLoad download = new DownLoad();
				download.download("/tmp/download.exe/", response);
			//}
						
			out.println("*****************************************");

			out.println("</BODY>");
			out.println("<HTML>");
			out.close();
		}
	}

	// 获取ipaddress
	public String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	// 判断ip地址是否是北美ip
	public boolean enableIpaddress(String ipaddress) {
		int ip_first_bit = Integer.parseInt(ipaddress.split("\\.")[0]);
		int i = 0, j = 0;
		final int[] iplist = {23, 24, 50, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 96, 97, 98, 99, 100,
				104, 107, 108, 173, 174, 184, 199, 204, 205, 206, 207, 208, 209, 216};
		for( i = 0; i < iplist.length; i++){
			if (ip_first_bit != iplist[i])
				j++;
		}
		if (j != iplist.length)
			return true;
		else
			return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
