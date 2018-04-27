package com.example.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PocFiles implements RowMapper<PocFiles>{
	private String _Mail;
	public String get_Mail() {
		return _Mail;
	}
	public void set_Mail(String _Mail) {
		this._Mail = _Mail;
	}
	
	public PocFiles(String mail ){
		this.set_Mail(mail);
	}
	public PocFiles(){}
	@Override
	public PocFiles mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		PocFiles bcm = new PocFiles();
		bcm.set_Mail(rs.getString(1));
		return bcm;
	}
}
