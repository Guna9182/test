package com.cg.entity;

import javax.persistence.*;

import lombok.ToString;

//Owner Entity

@Entity
@Table(name = "emp")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eid;
	private String ename;
	private int esal;

	// Account entity Reference
	@OneToOne(cascade = CascadeType.ALL)
	private Account eaccount;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getEsal() {
		return esal;
	}

	public void setEsal(int esal) {
		this.esal = esal;
	}

	public Account getEaccount() {
		return eaccount;
	}

	public void setEaccount(Account eaccount) {
		this.eaccount = eaccount;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", esal=" + esal + ", eaccount=" + eaccount + "]";
	}

}