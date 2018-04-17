package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DBObject {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="type")
	private String type;
	@Column(name="path", length=1000)
	private String path;
	@Column(name="columnName")
	private String columnName;
	@Column(name="ts",insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date timestamp;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
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

	public DBObject(String name, String path, String columnName, String type) {
		super();
		this.name = name;
		this.path = path;
		this.columnName = columnName;
		this.type = type;
	}
	
	public DBObject(){}
	
}