package com.douzone.mysite.exception;

public class GuestbookRepositroyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GuestbookRepositroyException() {
		super("GuestbookRepositroyException error");
	}

	public GuestbookRepositroyException(String message) {
		super("message");
	}
}
