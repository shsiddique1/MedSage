package com.medsage.wcc.exception;

import java.io.Serializable;

public class CWAException
  extends Exception
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int code;
  private String message;
  
  public CWAException()
  {
    this.code = 1001;
  }
  
  public CWAException(int code)
  {
    this.code = code;
  }
  
  public CWAException(int code, String message)
  {
    this.code = code;
    this.message = message;
  }
  
  public int getCode()
  {
    return this.code;
  }
  
  public String getMessage()
  {
    return this.message;
  }
}

