package com.juaracoding.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Bonus;
import com.juaracoding.main.model.BonusRowMapper;
import com.juaracoding.main.model.Bonus;
import com.juaracoding.main.model.Bonus;
import com.juaracoding.main.model.BonusRowMapper;

@RestController
@RequestMapping("/bonus")
public class BonusController {
	@Autowired
	JdbcTemplate jdbc;
	
	public List<Bonus> getBonus(){
		String sql = "Select * from bonus";
		List<Bonus> bonus = jdbc.query(sql, new BonusRowMapper());
		return bonus;
	}
	
	public int insertBonus(Bonus bonus) {
		return jdbc.update("INSERT INTO `bonus`(`worker_ref_id`, `bonus_date`, `bonus_amount`) VALUES ("+bonus.getRefId()+",'"+bonus.getBonusDate()+"',"+bonus.getBonusAmount()+")");
	}
	
	public int updateBonus(String refId, Bonus bonus) {
		return jdbc.update("UPDATE `bonus` SET `bonus_date`='"+bonus.getBonusDate()+"',`bonus_amount`="+bonus.getBonusAmount()+" WHERE `worker_ref_id`="+bonus.getRefId()+"");
	}
	
	public int deleteBonus(String refId) {
		return jdbc.update("DELETE FROM `bonus` WHERE `worker_ref_id`="+refId+"");
	}
	
	@GetMapping("/")
	public List<Bonus> List(){
		return getBonus();	
	}
	
	@PostMapping("/insert")
    public String add(@RequestBody Bonus bonus) {
	 

		if (this.insertBonus(bonus) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
    }
	
	 @PutMapping("/{refId}")
	    public ResponseEntity<?> update(@RequestBody Bonus bonus, @PathVariable String refId) {
		 try {
	            updateBonus(refId, bonus);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	 }

	 @DeleteMapping("/{refId}")
	    public void delete(@PathVariable String refId) {
		 	deleteBonus(refId);
	 }
}
