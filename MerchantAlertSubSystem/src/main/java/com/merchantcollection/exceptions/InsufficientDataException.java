package com.merchantcollection.exceptions;
/**
 * User defined Exception to catch if any exception occurs due to insufficient data for further processing.
 */
@SuppressWarnings("serial")
public class InsufficientDataException extends Exception{
	public String toString(){
		return "InsufficientDataException : Data provided for the process is insufficient or in invalid format";
	}
}