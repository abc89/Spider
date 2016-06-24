package com.data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class JDFacy extends ShopBeanFactory{

	private static Logger log=Logger.getLogger(JDBean.class.getName());
	/***
	 * 商品信息分析属性
	 */
	private String SPLITE="splite";//分隔符号
	private String HOUZHUI="tt";//后缀字符串便于分隔
	private int index;
	//正则匹配查找内容
	private  final int shopID=0;//商品id
	private final int price=1;
	private final int title=2;
	private final int commentNum=3;
	private final int deteil_url=4;
	private final int img_url=5;
	private String[] parents=new String[]{"data-sku=\".*?\"","data-price=\".*?\"","_blank\" title=\".*?\"","flagsClk.*?</a>人评价","href=\".*?\"","data-lazy-img=\".*?\""};
	/***
	 * 商品评论分析
	 */
	//配查找内容
	private final int goodCount=0;//商品id
	private final int generalCount=1;
	private final int poorCount=2;
	private int name=3;//评论内容
	//匹配规则 数组
	private String[] comments=new String[]{"\"goodCount\":.*?,","\"generalCount\":.*?,","\"poorCount\":.*?,","\"content\":.*?,","\"commentId\":.*?,","\"score\":.*?,","\"uid\":.*?,"};
    private static JDFacy facy=new JDFacy();
    private JDFacy(){}
    public static JDFacy getInstance(){
    	return facy;
    	
    }
	@Override
	public  ShopBean configShopCommentBean(String content,ShopBean bean) {
		List<String> list=config(content);
		bean.configComment(list);
		return bean;
	}

	@Override
	public ShopBean createShopImfBean(String imf) {
		
		JDBean bean=configImf(imf);;
		
		return bean;
	}
		
	   
			private  JDBean configImf(String content) {
				List<String> rLists=new ArrayList<String>();
				JDBean bean=new JDBean();
				StringBuilder builder=new StringBuilder();
					
					     int len=parents.length;
					     for(int j=0;j<len;j++){
					    	 Pattern searchMeanPattern2 = Pattern.compile(parents[j]);
								Matcher m2 = searchMeanPattern2.matcher(content); //m1是获取包含翻译的整个<div>的
								String means="null";
								if(m2.find()){
									 means=m2.group()+"a";
									int begin=means.indexOf("=\"");
									if(begin!=-1){
										int end=means.indexOf("\"a");
										means=means.substring(begin+2,end);
									}else{
										begin=means.indexOf("\"");
										int end=means.indexOf("<");
										means=means.substring(begin+2,end);
									}
								}
								setBeanImf(bean,means,j);
					     }
				
				return bean;
			}
			 private void setBeanImf(JDBean bean, String means, int index) {
				switch(index){
				     case shopID:bean.setShopID(means);break;
				     case price:bean.setPrice(means);break;
				     case img_url:bean.setImg_url(means);break;
				     case deteil_url:bean.setDeteil_url(means);break;
				     case title:bean.setTitle(means);break;
				     case commentNum:bean.setCommentNum(means);
				}
			}
			/**
		     * 
		     * @param content html文本
		     * @return 匹配内容集合
		     */
			private  List<String> config(String content) {
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
}
