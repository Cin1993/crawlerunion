package com.crawlerunion.operate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

import com.crawlerunion.dao.ComputerInfo;
import com.crawlerunion.module.ClientComputer;
import com.sun.management.OperatingSystemMXBean;

public class ClientComputerOperate {
	private static final float KB = 1024;
	
	// 总的物理内存
	public float getMemory(){
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();		
		long totalvirtualMemory = osmxb.getTotalPhysicalMemorySize();
		return totalvirtualMemory / KB / KB / KB;
	}
	
	// 可用的内存
	public float getEnableMemory(){
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
		return freePhysicalMemorySize / KB / KB / KB;
	}
	
	//  os名字
	public String getOsName(){
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		return osmxb.getName();
	}
	
	//  os版本
	public String getOsVersion(){
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		return osmxb.getVersion();
	}
	
	
	// computer MACAddress
	public String getMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            // CMD查询mac地址
            process = Runtime.getRuntime().exec("ipconfig /all");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            int index_chi = -1;
            int index_eng = -1;
            while ((line = bufferedReader.readLine()) != null) {
                // 寻找标示字符串physical
            	index_eng = line.toLowerCase().indexOf("physical address");
            	// 寻找标示字符串physical
            	index_chi = line.toLowerCase().indexOf("物理地址");
                if (index_eng >= 0) {// 找到了
                    index = line.indexOf(":");// 寻找":"的位置
                    if (index >= 0) {
                        // 取出mac地址并去除2边空格
                        mac = line.substring(index + 1).trim();
                    }
                    break;
                }
                if (index_chi >= 0){
                	index = line.indexOf(":");
                	if(index >= 0){
                		mac = line.substring(index + 1).trim();
                	}
                	break;
                }
            }
        }
        catch (IOException e) {
            System.out.println("未获取到网卡地址");
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }
        return mac;
    }
	
	
	
	public static void main(String[] args) {
		ComputerInfo computerinfo = new ComputerInfo();
		ClientComputer computer = computerinfo.setComputerInfo();
		System.out.println("Your computer memory is : "+computer.getMemory()+"GB");
		System.out.println("Your computer enable memory is: "+computer.getEnable_memory()+"GB");
		System.out.println("Your operating system is: "+computer.getOsname());
		System.out.println("Your operating system version is: "+computer.getOs_version());
		
	}
	
}
