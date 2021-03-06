package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ViewObject {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="path",  length=1000)
	private String path;
	@Column(name="EntityUsageName")
	private String EntityUsageName;
	@Column(name="EntityUsageObjectDef")
	private String EntityUsageObjectDef;
	@Column(name="ts",insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date timestamp;
	@Column(name="IsEffectiveDated")
	private String IsEffectiveDated;
	@Column(name="Def", length=1000)
	private String Def;
	@Column(name="Extends", length=1000)
	private String Extends;
	
	public String getExtends() {
		return Extends;
	}

	public void setExtends(String extends1) {
		Extends = extends1;
	}

	public String getDef() {
		return Def;
	}

	public void setDef(String def) {
		Def = def;
	}
	
	public String getIsEffectiveDated() {
		return IsEffectiveDated;
	}

	public void setIsEffectiveDated(String isEffectiveDated) {
		IsEffectiveDated = isEffectiveDated;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getpath() {
		return path;
	}


	public void setpath(String path) {
		this.path = path;
	}

	public String getEntityUsageName() {
		return EntityUsageName;
	}

	public void setEntityUsageName(String entityUsageName) {
		EntityUsageName = entityUsageName;
	}

	public String getEntityUsageObjectDef() {
		return EntityUsageObjectDef;
	}

	public void setEntityUsageObjectDef(String entityUsageObjectDef) {
		EntityUsageObjectDef = entityUsageObjectDef;
	}

	public ViewObject(String name, String path, String entityUsageName, String entityUsageObjectDef, String IsEffectiveDated, String Extends) {
		super();
		this.name = name;
		this.path = path;
		EntityUsageName = entityUsageName;
		EntityUsageObjectDef = entityUsageObjectDef;
		this.Extends = Extends;
		this.IsEffectiveDated = IsEffectiveDated;
		this.Def = path.substring(path.indexOf("oracle")).replace("/", ".").replaceAll(".xml", "");
	}
	
	public ViewObject() {}
}