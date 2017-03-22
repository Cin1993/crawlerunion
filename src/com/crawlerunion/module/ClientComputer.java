package com.crawlerunion.module;

public class ClientComputer {
	private float memory;
	private float enable_memory;
	private String osname;
	private String os_version;
	private String ipaddress;
	
	public float getMemory() {
		return memory;
	}
	public void setMemory(float memory) {
		this.memory = memory;
	}
	public float getEnable_memory() {
		return enable_memory;
	}
	public void setEnable_memory(float enable_memory) {
		this.enable_memory = enable_memory;
	}
	public String getOsname() {
		return osname;
	}
	public void setOs_name(String osname) {
		this.osname = osname;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public String getIpaddress(){
		return ipaddress;
	}
	public void setIpaddress(String ipaddress){
		this.ipaddress = ipaddress;
	}

}
