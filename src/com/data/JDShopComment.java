package com.data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
/**
 * ��������ҳ������
 * @author e7691
 *
 */
public class JDShopComment implements Comment {
	private static Logger log=Logger.getLogger(JDShopComment.class.getName());
	private List<String> lists=new ArrayList<String>();
	private String SPLITE="splite";//�ָ�����
	private String HOUZHUI="tt";//��׺�ַ������ڷָ�
	private int index;
	//���������
	private int goodCount=0;//��Ʒid
	private int generalCount=1;
	private int poorCount=2;
	private int name=3;//��������
	//ƥ����� ����
	private String[] comments=new String[]{"\"goodCount\":.*?,","\"generalCount\":.*?,","\"poorCount\":.*?,","\"content\":.*?,","\"commentId\":.*?,","\"score\":.*?,","\"uid\":.*?,"};
   
	public JDShopComment(List<String> lis) {
		this.lists=lis;
		}
	
	    public void show(){
	    	int size=lists.size();
	    	for(int i=0;i<size;i++){
	    		 log.debug(lists.get(i));
	    	}
	    }
	    

}
