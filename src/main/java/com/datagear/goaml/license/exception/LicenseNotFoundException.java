package com.datagear.goaml.license.exception;


public class LicenseNotFoundException extends RuntimeException {

  public LicenseNotFoundException(Long id) {
    super("Could not find license " + id);
  }
}