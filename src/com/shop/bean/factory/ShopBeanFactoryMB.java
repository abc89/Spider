package com.shop.bean.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.logs.ShopLogs;
import com.shop.bean.*;

/***
 * 添加模板生成
 * @author e7691
 *
 */
public abstract  class ShopBeanFactoryMB extends ShopBeanFactory{
	protected String[] parents;//物品匹配
	protected String[] comments;//评论匹配
	/**
	 * 
	 * @param content 评论内容
	 * @param bean  装饰 bean
	 * @return
	 */
	public  ShopBean configShopCommentBean(String content,ShopBean bean){
		List<String> list=configComment(content);
		bean.configComment(list);
		return bean;		
	}
	public ShopBean createShopImfBean(String imf){
		ShopBean bean=configImf(imf);;	
	    return bean;
	}
	   /***
	    * 搜索物品 列表 html 分析每个物品 信息 ：描述 ，价格。。
	    * @param content html文本
	    * @return ShopBean 
	    */
	protected  ShopBean configImf(String content){
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		StringBuilder builder=new StringBuilder();		
			     int len=parents.length;
			     for(int j=0;j<len;j++){
			    	 Pattern searchMeanPattern2 = Pattern.compile(parents[j]);
						Matcher m2 = searchMeanPattern2.matcher(content); //m1是获取包含翻译的整个<div>的
						String means="null";
						if(m2.find()){
							 means=m2.group()+"a";
							int begin=means.indexOf("=\"");
							int begin2=means.indexOf(":");
							if(begin!=-1){
								int end=means.indexOf("\"a");
								means=means.substring(begin+2,end);
							}else if(begin2!=-1){
								int end=means.indexOf(",");
								means=means.substring(begin2+2,end-1);
							}
							else{
								begin=means.indexOf("\"");
								int end=means.indexOf("<");
								means=means.substring(begin+2,end);
							}
						}else{					
							ShopLogs.debug(ShopBeanFactoryMB.class.getName(), "物品信息  不符合匹配："+parents[j]);
						}
						map.put(j,means);
			     }
        ShopBean bean=	setBeanImf(map);
		return bean;
	}
	protected   List<String> configComment(String content){
		List<String> lists=new ArrayList<String>();
	     int len=comments.length;
	     for(int j=0;j<len;j++){
	    	 Pattern searchMeanPattern2 = Pattern.compile(comments[j]);
				Matcher m2 = searchMeanPattern2.matcher(content); //m1是获取包含翻译的整个<div>的
				int count=0;
				while(m2.find()){
					count++;
					String means=m2.group()+"a";
					lists.add(means);
				}
				count=0;
	     }
		return lists;
	}
	protected abstract  ShopBean setBeanImf(HashMap<Integer, String> maps);
}
