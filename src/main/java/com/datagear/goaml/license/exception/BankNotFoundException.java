package com.datagear.goaml.license.exception;

public class BankNotFoundException extends RuntimeException {

	  public BankNotFoundException(Long id) {
	    super("Could not find bank " + id);
	  }
	}