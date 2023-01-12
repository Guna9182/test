package com.cg.exception;

public class NoSuchAccountFoundException extends Exception {

	public NoSuchAccountFoundException(String msg) {
		super(msg);
	}
}