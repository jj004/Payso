package com.merchantcollection.exceptions;
/**
 * User defined Exception to catch if any exception occurs during database transaction.
 */
@SuppressWarnings("serial")
public class DatabaseTransactionException extends Exception {
	public String toString(){
		return "DatabaseTransactionException : Unable to process database query llike Insert, Update, Delete or Select";
	} 
}