package com.net.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.data.ShopBean;
import com.data.Comment;
import com.data.GMBean;
import com.data.JDBean;
import com.data.JDFacy;
import com.data.TBBean;
import com.data.dao.JDDao;
import com.net.crawler.GMCrawler;
import com.net.crawler.JDCrawler;
import com.net.crawler.TBCrawler;

public class ShopMain {
	 private static List<String> lists=new ArrayList<String>();
		public static void main(String[] args) {
			//TBTest();
			JDTest();
			//GMTest();
		}
		private static void GMTest() {
			List<String> content=GMCrawler.creatTBCrawler().searchObject("华硕笔记本");
			if(!content.isEmpty()){
				GMBean bean=new GMBean(content);
			    String id=bean.getItem_ID();
			    System.out.println();
			    String content1=GMCrawler.creatTBCrawler().searchUrl(bean.getDeteilUrl());
			    System.out.println(content1);
			    GMCrawler.creatTBCrawler().searchComment(id, "1");
			}
		}
		private static void JDTest() {
			String search="手机";
			List<String> contents=JDCrawler.creatTBCrawler().searchObject(search);
			JDDao dao=new JDDao();
			for (String content : contents) {	
				
				JDBean bean=(JDBean) JDFacy.getInstance().createShopImfBean(content);
				System.out.println("图片统一资源定位:"+bean.getDeteil_url());
				System.out.println("物品id"+bean.getShopID());
				System.out.println("物品价格"+bean.getPrice());
				System.out.println("描述："+bean.getTitle());
				String id=bean.getShopID();
				bean=(JDBean) (bean.getDeteil_url().compareTo("null")!=0?JDFacy.getInstance().configShopCommentBean(JDCrawler.creatTBCrawler().searchComment(id, "1"),bean):null);
				if(bean!=null){
				((JDBean)bean).setShopType(search);
				System.out.println("好评数"+bean.getGoodCount());
				dao.add((JDBean)bean);
				}
				
			}
			
		}
		private static void TBTest() {
//			TBCrawler crawler=TBCrawler.creatTBCrawler();
//		    List<String> content= crawler.searchObject("篮球");
//		    if(!content.isEmpty()){
//		    	TBBean bean=new TBBean(content);
//				 String id=bean.getItem_ID();
//				 //String sellerID=bean.getSeller_ID();
//				    System.out.println();
//				    System.out.println(bean.getDeteilUrl());
//				    crawler.searchComment(id,sellerID, "1");
		//	}
		  
		}

}
