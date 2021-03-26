package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TitleRowMapper implements RowMapper<Title> {

	@Override
	public Title mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Title title = new Title();
		title.setRefId(rs.getInt("worker_ref_id"));
		title.setWorkerTitle(rs.getString("worker_title"));
		title.setAffectedFrom(rs.getString("affected_from"));
		return title;
	}

}
