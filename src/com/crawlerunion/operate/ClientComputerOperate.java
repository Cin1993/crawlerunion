package com.crawlerunion.operate;

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
	public static void main(String[] args) {
		ComputerInfo computerinfo = new ComputerInfo();
		ClientComputer computer = computerinfo.setComputerInfo();
		System.out.println("Your computer memory is : "+computer.getMemory()+"GB");
		System.out.println("Your computer enable memory is: "+computer.getEnable_memory()+"GB");
		System.out.println("Your operating system is: "+computer.getOsname());
		System.out.println("Your operating system version is: "+computer.getOs_version());
		
	}
	
}
