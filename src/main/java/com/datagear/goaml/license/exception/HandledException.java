package com.datagear.goaml.license.exception;


public class HandledException extends RuntimeException {

	String exceptionMessage;
	
  public HandledException(String exceptionMessage) {
    super(exceptionMessage);
    this.exceptionMessage=exceptionMessage;
  }
  public void printMessage(String exceptionMessage)
  {
	  System.out.println(exceptionMessage);
  }
  
}