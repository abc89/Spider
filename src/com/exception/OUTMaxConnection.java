package com.exception;

import java.io.PrintWriter;

/**
 * 
 * @author e7691
 *
 */
public class OUTMaxConnection extends Exception {
   public String toString(){
	   return "��ǰ���ӳ��Ѿ��ﵽ���������";
   }

public void printStackTrace() {
	System.out.println("��ǰ���ӳ��Ѿ��ﵽ���������");
	super.printStackTrace();
}


}
