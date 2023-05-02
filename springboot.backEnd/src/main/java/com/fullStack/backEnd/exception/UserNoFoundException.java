package com.fullStack.backEnd.exception;

public class UserNoFoundException  extends RuntimeException {
	
	public UserNoFoundException(Long id) {
		
		//super("Could not find user with  id " + id);
		System.out.println("Could not find user with  id " + id);
	}
	
}
