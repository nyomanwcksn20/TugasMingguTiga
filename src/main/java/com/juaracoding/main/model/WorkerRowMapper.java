package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WorkerRowMapper implements RowMapper<Worker> {

	@Override
	public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Worker worker = new Worker();
		worker.setWorkerId(rs.getString("worker_id"));
		worker.setfName(rs.getString("first_name"));
		worker.setlName(rs.getString("last_name"));
		worker.setSalary(rs.getDouble("salary"));
		worker.setJoinDate(rs.getString("joining_date"));
		worker.setDepartment(rs.getString("department"));
		
		return worker;
	}

}
