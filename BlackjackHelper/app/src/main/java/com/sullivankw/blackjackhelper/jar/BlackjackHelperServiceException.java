package com.sullivankw.blackjackhelper.jar;

/**
 * Wrapper for any future errors to be added than can be thrown by api
 * Currently only the illegal args from the client is supported
 */
public class BlackjackHelperServiceException extends Exception {


	private static final long serialVersionUID = 7497670875827031296L;

	public BlackjackHelperServiceException() {
		super();
	}

	public BlackjackHelperServiceException(String message) {
		super(message);
	}

}
