package com.crawlerunion.operate;

import java.lang.management.ManagementFactory;
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
	
	
}
