package cn.ITHong.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Permission implements Serializable {
	private Long pid;
	private String name;
	private String desciription;
	
	private Set<Role> rSet = new HashSet<Role>(0);

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesciription() {
		return desciription;
	}

	public void setDesciription(String desciription) {
		this.desciription = desciription;
	}

	public Set<Role> getrSet() {
		return rSet;
	}

	public void setrSet(Set<Role> rSet) {
		this.rSet = rSet;
	}

	
	
}
