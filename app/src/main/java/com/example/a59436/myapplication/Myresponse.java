package com.example.a59436.myapplication;

/**
 * @author xuhj
 * @version 创建时间：2016-5-11 上午11:22:46 
 * 
 */
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
