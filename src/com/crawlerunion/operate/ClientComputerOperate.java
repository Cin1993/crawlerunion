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
	
	// �ܵ������ڴ�
	public float getMemory(){
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();		
		long totalvirtualMemory = osmxb.getTotalPhysicalMemorySize();
		return totalvirtualMemory / KB / KB / KB;
	}
	
	// ���õ��ڴ�
	public float getEnableMemory(){
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
		return freePhysicalMemorySize / KB / KB / KB;
	}
	
	//  os����
	public String getOsName(){
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		return osmxb.getName();
	}
	
	//  os�汾
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
            // CMD��ѯmac��ַ
            process = Runtime.getRuntime().exec("ipconfig /all");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            int index_chi = -1;
            int index_eng = -1;
            while ((line = bufferedReader.readLine()) != null) {
                // Ѱ�ұ�ʾ�ַ���physical
            	index_eng = line.toLowerCase().indexOf("physical address");
            	// Ѱ�ұ�ʾ�ַ���physical
            	index_chi = line.toLowerCase().indexOf("�����ַ");
                if (index_eng >= 0) {// �ҵ���
                    index = line.indexOf(":");// Ѱ��":"��λ��
                    if (index >= 0) {
                        // ȡ��mac��ַ��ȥ��2�߿ո�
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
            System.out.println("δ��ȡ��������ַ");
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
