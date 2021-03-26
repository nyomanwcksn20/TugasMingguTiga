package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BonusRowMapper implements RowMapper<Bonus> {

	@Override
	public Bonus mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Bonus bonus = new Bonus();
		bonus.setRefId(rs.getInt("worker_ref_id"));
		bonus.setBonusDate(rs.getString("bonus_date"));
		bonus.setBonusAmount(rs.getInt("bonus_amount"));
		return bonus;
	}

}
