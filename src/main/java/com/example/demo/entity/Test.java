package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="T_TEST")
@Getter
public class Test {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEST_CD")
	private Long testCd;
	
	@Column(name="VAL")
	private String val;
	
	@Column(name="TEST_ID")
	private Long testId;
	
	public String getVal() {
		return val.replaceAll(" ", "");
	}
}
