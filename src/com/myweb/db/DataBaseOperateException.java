package com.myweb.db;

public class DataBaseOperateException extends Exception {
	public static final int UNINITIAL_TO_CONFIGFILE = 0;
	private String msg=null;
    public DataBaseOperateException(int type){
    	switch(type){
    	case UNINITIAL_TO_CONFIGFILE:msg="δ�������ݿ��ļ�";break;
    	}
    }
	public void printStackTrace() {
	    System.out.println(msg);
		super.printStackTrace();
	}
     
}
