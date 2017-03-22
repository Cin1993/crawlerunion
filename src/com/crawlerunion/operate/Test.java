package com.crawlerunion.operate;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import com.crawlerunion.dao.ComputerInfo;

public class Test {

	public static void main(String[] args) {
		System.out.println(enableIpaddress("192.168.1.1"));
		
		
	}
	public static boolean enableIpaddress(String ipaddress) {
		int ip_first_bit = Integer.parseInt(ipaddress.split("\\.")[0]);
		int i = 0, j = 0;
		System.out.println(ip_first_bit);
		final int[] iplist = {23, 24, 50, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 96, 97, 98, 99, 100,
				104, 107, 108, 173, 174, 184, 199, 204, 205, 206, 207, 208, 209, 216};
		for( i = 0; i < iplist.length; i++){
			if (ip_first_bit != iplist[i])
				j++;
			
		}
		System.out.println("j: " + j);
		System.out.println("list: " + iplist.length);
		if (j != iplist.length)
			return true;
		else
			return false;
	}

}
