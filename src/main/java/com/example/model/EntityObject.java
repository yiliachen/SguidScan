package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EntityObject {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="path",length=1000)
	private String path;
	@Column(name="DBObjectType")
	private String DBObjectType;
	@Column(name="DBObjectName")
	private String DBObjectName;
	@Column(name="EffectiveDateType")
	private String EffectiveDateType;
	@Column(name="timestamp",insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date timestamp;
	@Column(name="Def", length=1000)
	private String Def;
	
	public String getDef() {
		return Def;
	}

	public void setDef(String def) {
		Def = def;
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

	public String getDBObjectType() {
		return DBObjectType;
	}

	public void setDBObjectType(String dBObjectType) {
		DBObjectType = dBObjectType;
	}

	public String getDBObjectName() {
		return DBObjectName;
	}

	public void setDBObjectName(String dBObjectName) {
		DBObjectName = dBObjectName;
	}

	public String getEffectiveDateType() {
		return EffectiveDateType;
	}

	public void setEffectiveDateType(String effectiveDateType) {
		EffectiveDateType = effectiveDateType;
	}

	public EntityObject(String name, String path, String dBObjectType, String dBObjectName, String effectiveDateType) {
		this.name = name;
		this.path = path;
		DBObjectType = dBObjectType;
		DBObjectName = dBObjectName;
		EffectiveDateType = effectiveDateType;
		this.Def = path.substring(path.indexOf("oracle")).replace("/", ".").replaceAll(".xml", "");
	}
	
	public EntityObject(){}
}