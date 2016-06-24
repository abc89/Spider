package com.myweb.exception;
/***
 * 获取action 对应 相应 路径  异常类
 * @author e7691
 *
 */
public class ActionConfigException extends Exception {
	public static final int CONFIGERROR_OR_NOACTION = 1;
	public static final int NOACTIONAME = 0;
    private String errorMsg=null;
    public ActionConfigException(int errorType,String msg){
    	switch(errorType){
    	case CONFIGERROR_OR_NOACTION:errorMsg="配置文件错误 :配置方式错误或action与action-url未配对"+msg;break;
    	case  NOACTIONAME: errorMsg="配置文件 不存在该action/action-url节点名"+msg;break;
    	}
    }
	public void printStackTrace() {
		System.out.println(errorMsg);
		super.printStackTrace();
	}
    
}
