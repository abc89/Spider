package com.shop.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
/**
 * 京东评论页面数据
 * @author e7691
 *
 */
public class JDShopComment implements Comment {
	private static Logger log=Logger.getLogger(JDShopComment.class.getName());
	private List<String> lists=new ArrayList<String>();
	private String SPLITE="splite";//分隔符号
	private String HOUZHUI="tt";//后缀字符串便于分隔
	private int index;
	//配查找内容
	private int goodCount=0;//商品id
	private int generalCount=1;
	private int poorCount=2;
	private int name=3;//评论内容
	//匹配规则 数组
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
