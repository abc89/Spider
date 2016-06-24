package com.exception;

import java.io.PrintWriter;

/**
 * 
 * @author e7691
 *
 */
public class OUTMaxConnection extends Exception {
   public String toString(){
	   return "当前连接池已经达到最大连接数";
   }

public void printStackTrace() {
	System.out.println("当前连接池已经达到最大连接数");
	super.printStackTrace();
}


}
