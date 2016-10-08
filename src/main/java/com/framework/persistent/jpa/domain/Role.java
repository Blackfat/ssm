package com.framework.persistent.jpa.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SYS_ROLE")
public class Role extends IdEntity{
	
	private String rolename;
	
	private String description;
	
	private Set<RoleResource> roleResources;
	
	private Set<UserRole> userRoles;
	
	@OneToMany(mappedBy="role",cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
    public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(mappedBy="role",cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
	public Set<RoleResource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(Set<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
