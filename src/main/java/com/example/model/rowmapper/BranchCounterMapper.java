package com.example.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BranchCounterMapper implements RowMapper<BranchCounterMapper> {
	private String branch;
	private String product;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	private Long NullSGUID;
	private Long Duplicated;
	private Long BULKSEEDMissing;
	private Long ERROR_DUPSGUID;
	private Long ERROR_SGUIDDIFF;
	private Long ERROR_SGUIDINDEX;
	@Override
	
	public BranchCounterMapper mapRow(ResultSet rs, int rowNum) throws SQLException {
		BranchCounterMapper bcm = new BranchCounterMapper();
		bcm.setBranch(rs.getString(1));
		bcm.setProduct(rs.getString(2));
		bcm.setNullSGUID(rs.getLong(3));
		bcm.setDuplicated(rs.getLong(4));
		bcm.setBULKSEEDMissing(rs.getLong(5));
		bcm.setERROR_DUPSGUID(rs.getLong(6));
		bcm.setERROR_SGUIDDIFF(rs.getLong(7));
		bcm.setERROR_SGUIDINDEX(rs.getLong(8));
		return bcm;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
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
	public Long getERROR_SGUIDINDEX() {
		return ERROR_SGUIDINDEX;
	}
	public void setERROR_SGUIDINDEX(Long eRROR_SGUIDINDEX) {
		ERROR_SGUIDINDEX = eRROR_SGUIDINDEX;
	}
	
	

}
