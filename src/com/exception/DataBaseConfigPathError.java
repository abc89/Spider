package com.exception;
/**
 * 
 * @author e7691
 *
 */
public class DataBaseConfigPathError extends Exception {

	public static final int PATH_NOT_EXISTS = 1;
	public static final int PATH_IS_NULL = 0;
    private String errorMsg=null;
    public DataBaseConfigPathError(int errorType,String msg){
    	switch(errorType){
    	case PATH_NOT_EXISTS:errorMsg="�ļ����ļ�Ŀ¼������"+msg;break;
    	case PATH_IS_NULL: errorMsg="�����ļ� ·�� �ַ���ʵ�� Ϊnull"+msg;break;
    	}
    }
	public void printStackTrace() {
		System.out.println(errorMsg);
		super.printStackTrace();
	}
}
