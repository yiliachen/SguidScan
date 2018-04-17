package com.example.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PocFiles implements RowMapper<PocFiles>{
	private String _Mail;
	private String _Path;
	public String get_Mail() {
		return _Mail;
	}
	public void set_Mail(String _Mail) {
		this._Mail = _Mail;
	}
	public String get_Path() {
		return _Path;
	}
	public void set_Path(String _Path) {
		this._Path = _Path;
	}
	public PocFiles(String mail, String path){
		this.set_Mail(mail);
		this.set_Path(path);
	}
	public PocFiles(){}
	@Override
	public PocFiles mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		PocFiles bcm = new PocFiles();
		bcm.set_Mail(rs.getString(1));
		bcm.set_Path(rs.getString(2));		
		return bcm;
	}
}
