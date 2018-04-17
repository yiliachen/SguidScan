package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AM {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="name", length=200)
	private String name;
	@Column(name="path", length=1000)
	private String path;
	@Column(name="voUsageName")
	private String voUsageName;
	@Column(name="voUsageObjectDef")
	private String voUsageObjectDef;
	@Column(name="Def", length=1000)
	private String Def;
	
	public String getDef() {
		return Def;
	}

	public void setDef(String def) {
		Def = def;
	}

	@Column(name="ts",insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date timestamp;
	
	
	public Date getTimestamp() {
		return timestamp;
	}

	public AM(String name, String path, String voUsageName, String voUsageObjectDef) {
		super();
		this.name = name;
		this.path = path;
		this.voUsageName = voUsageName;
		this.voUsageObjectDef = voUsageObjectDef;
		this.Def = path.substring(path.indexOf("oracle")).replace("/", ".").replaceAll(".xml", "");
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


	public String getVoUsageName() {
		return voUsageName;
	}


	public void setVoUsageName(String voUsageName) {
		this.voUsageName = voUsageName;
	}


	public String getVoUsageObjectDef() {
		return voUsageObjectDef;
	}


	public void setVoUsageObjectDef(String voUsageObjectDef) {
		this.voUsageObjectDef = voUsageObjectDef;
	}
	
	public AM(){}
}