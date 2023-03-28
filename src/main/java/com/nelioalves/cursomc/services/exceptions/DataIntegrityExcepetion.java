package com.nelioalves.cursomc.services.exceptions;

public class DataIntegrityExcepetion extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityExcepetion(String msg) {
		super(msg);
			}

	  public DataIntegrityExcepetion(String msg, Throwable cause) {
		  super(msg, cause);
	  }
}
