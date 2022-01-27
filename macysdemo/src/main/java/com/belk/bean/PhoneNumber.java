package com.belk.bean;

import java.io.Serializable;

public class PhoneNumber implements Serializable  {
	
	String home;

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}
	String mob;

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}
    
}
