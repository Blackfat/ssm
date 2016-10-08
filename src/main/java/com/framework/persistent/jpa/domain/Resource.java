package com.framework.persistent.jpa.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SYS_RESOURCE")
public class Resource extends TreeNodeEntity{
	
	private String code;
	
	private String description;
	
	private Set<RoleResource> roleResources;
	
    @OneToMany(mappedBy="resource",cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
	public Set<RoleResource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(Set<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
