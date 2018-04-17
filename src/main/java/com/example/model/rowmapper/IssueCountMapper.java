package com.example.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IssueCountMapper implements RowMapper<IssueCountMapper> {
	
	private String filePath;
	private String branch;
	private Long NullSGUID;
	private Long Duplicated;
	private String email;
	private String product;
	public String getEmail() {
		return email;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getNullSGUID() {
		return NullSGUID;
	}

	public void setNullSGUID(Long nullSGUID) {
		NullSGUID = nullSGUID;
	}

	public Long getDuplicated() {
		return Duplicated;
	}

	public void setDuplicated(Long duplicated) {
		Duplicated = duplicated;
	}

	public Long getBULKSEEDMissing() {
		return BULKSEEDMissing;
	}

	public void setBULKSEEDMissing(Long bULKSEEDMissing) {
		BULKSEEDMissing = bULKSEEDMissing;
	}

	public Long getERROR_DUPSGUID() {
		return ERROR_DUPSGUID;
	}

	public void setERROR_DUPSGUID(Long eRROR_DUPSGUID) {
		ERROR_DUPSGUID = eRROR_DUPSGUID;
	}

	public Long getERROR_SGUIDDIFF() {
		return ERROR_SGUIDDIFF;
	}

	public void setERROR_SGUIDDIFF(Long eRROR_SGUIDDIFF) {
		ERROR_SGUIDDIFF = eRROR_SGUIDDIFF;
	}


	private Long BULKSEEDMissing;
	private Long ERROR_DUPSGUID;
	private Long ERROR_SGUIDDIFF;
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	
	@Override
	public IssueCountMapper mapRow(ResultSet rs, int index) throws SQLException {
		// TODO Auto-generated method stub
		IssueCountMapper bcm = new IssueCountMapper();
		bcm.setBranch(rs.getString(1));
		bcm.setFilePath(rs.getString(2));
		bcm.setEmail(rs.getString(3));
		bcm.setProduct(rs.getString(4));
		bcm.setNullSGUID(rs.getLong(5));
		bcm.setDuplicated(rs.getLong(6));
		bcm.setBULKSEEDMissing(rs.getLong(7));
		bcm.setERROR_DUPSGUID(rs.getLong(8));
		bcm.setERROR_SGUIDDIFF(rs.getLong(9));		
		
		return bcm;
	}

}
