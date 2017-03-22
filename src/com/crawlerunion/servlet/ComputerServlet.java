package com.crawlerunion.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crawlerunion.dao.ComputerInfo;
import com.crawlerunion.module.ClientComputer;

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
			out.println("<HTML>");
			out.println("<BODY>");
			out.print("�������ã�");
			out.println("</br>");

			ComputerInfo computerinfo = new ComputerInfo();
			ClientComputer computer = computerinfo.setComputerInfo();
			computer.setIpaddress(getIpAddr(request));
			out.println(computer.getMemory());
			out.println(computer.getEnable_memory());
			out.println(computer.getOsname());
			out.println(computer.getOs_version());
			out.println(computer.getIpaddress());

			out.println("</BODY>");
			out.println("<HTML>");
			out.close();
		}
	}

	// ��ȡipaddress
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
				// ��������ȡ�������õ�IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// ����ͨ������������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	// �ж�ip��ַ�Ƿ��Ǳ���ip
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