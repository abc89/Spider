package com.myweb.exception;

public class GetHtmlResultException extends Exception {
	public void printStackTrace() {
		System.out.println("GetHtmlResultException：发送请求 响应为空 异常");
		super.printStackTrace();
	}
}
