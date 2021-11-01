package com.douzone.mysite.exception;

public class BoardRepositoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BoardRepositoryException() {
		super("BoardRepositoryException error");
	}

	public BoardRepositoryException(String message) {
		super("message");
	}

}
