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
    	case PATH_NOT_EXISTS:errorMsg="文件或文件目录不存在"+msg;break;
    	case PATH_IS_NULL: errorMsg="配置文件 路径 字符串实例 为null"+msg;break;
    	}
    }
	public void printStackTrace() {
		System.out.println(errorMsg);
		super.printStackTrace();
	}
}
