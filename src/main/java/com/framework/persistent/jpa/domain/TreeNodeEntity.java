package com.framework.persistent.jpa.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;



public abstract class TreeNodeEntity <T extends TreeNodeEntity<T>> extends IdEntity implements
Comparable<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7804250152151828053L;
	
	public static final long ROOT_PARENT_ID=-1L;
	
	protected T parent;

	protected Set<T> children;

	protected Integer sortIndex = 0;

	protected String name;

    
	@Column(name="PARENT_ID")
	protected Long pid;
    
	
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public T getParent() {
		return parent;
	}


	public void setParent(T parent) {
		this.parent = parent;
	}

    @OneToMany(mappedBy="parent",cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
    @OrderBy("sortIndex ASC")
	public Set<T> getChildren() {
		return children;
	}


	public void setChildren(Set<T> children) {
		this.children = children;
	}

 
	public Integer getSortIndex() {
		return sortIndex;
	}


	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

  
	public Long getPid() {
		return pid;
	}


	public void setPid(Long pid) {
		this.pid = pid;
	}

    /**
     * 实现 Comparable 接口中的 compareTo 方法，
     *通常大于时返回一个正数，小于时返回一个负数，
     *等于时返回零，具体情况可以自行决定。由于 TreeSet
     * 会根据 compareTo 的结果来排序，因此输出结果
     */
	@Override
	public int compareTo(T obj) {
		if (obj != null && this.getSortIndex() != null) {
		    if (this.getSortIndex().compareTo(obj.getSortIndex()) == 1)
			return 1;
		    if (this.getId().equals(obj.getId()))
			return 0;
		}
		return -1;
	}
    
}
