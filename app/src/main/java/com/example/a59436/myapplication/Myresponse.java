package com.example.a59436.myapplication;

public class Myresponse {
	
	private String errormsg;
	private int code;
	
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Myresponse [errormsg=" + errormsg + ", code=" + code + "]";
	}


	



}
