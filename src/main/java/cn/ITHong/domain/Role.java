package cn.ITHong.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {
	private Long rid;
	private String name;
	private String description;
	
	private Set<User>	uSet = new HashSet<User>(0);
	private Set<Permission>	pSet = new HashSet<Permission>(0);  
	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getuSet() {
		return uSet;
	}

	public void setuSet(Set<User> uSet) {
		this.uSet = uSet;
	}

	public Set<Permission> getpSet() {
		return pSet;
	}

	public void setpSet(Set<Permission> pSet) {
		this.pSet = pSet;
	}

	
	
}
