package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="SGUID_ISSUE_LIST")
@Table(name="SGUID_ISSUE_LIST", uniqueConstraints=@UniqueConstraint(columnNames={"filepath", "branch", "issue_type", "comments"}))
public class SguidIssueList {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="filepath")
	private String filepath;
	@Column(name="add_date")
	private Date add_date;
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
		String [] paths = filepath.split("\\/");
		this.setProduct(paths[2]);
	}
	public String getIssue_type() {
		return issue_type;
	}
	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}
	public String getPoc() {
		return poc;
	}
	public void setPoc(String poc) {
		this.poc = poc;
	}
	public String getBug_assocated() {
		return bug_assocated;
	}
	public void setBug_assocated(String bug_assocated) {
		this.bug_assocated = bug_assocated;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Column(name="issue_type")
	private String issue_type;
	@Column(name="poc")
	private String poc;
	@Column(name="bug_assocated")
	private String bug_assocated;
	@Column(name="status")
	private String status;
	@Column(name="comments", length=3000)
	private String comments;
	@Column(name="branch")
	private String branch;
	@Column(name="product")
	private String product;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	
}
