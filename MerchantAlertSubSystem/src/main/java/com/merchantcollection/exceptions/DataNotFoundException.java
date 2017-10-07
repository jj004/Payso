package com.merchantcollection.exceptions;
/**
 * User defined Exception to catch if any exception occurs when trying to fetch data.
 */
@SuppressWarnings("serial")
public class DataNotFoundException extends Exception{
	
	public String toString(){
		return "DataNotFoundException : Data required for furthere execution is not available or is invalid";	
	}
}