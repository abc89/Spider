package com.net.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shop.bean.*;
import com.shop.bean.factory.*;
import com.net.crawler.GMCrawler;
import com.net.crawler.JDCrawler;
import com.net.crawler.TBCrawler;

public class ShopMain {
	 private static List<String> lists=new ArrayList<String>();
		public static void main(String[] args) {
			TBTest();
			//JDTest();
			//GMTest();
		}
		private static void GMTest() {
			List<String> contents=GMCrawler.creatTBCrawler().searchObject("华硕笔记本");
			for (String content : contents) {
				ShopBean bean=GMFactory.getInstance().createShopImfBean(content);
				String id=((GMBean)bean).getShopID();
				String det=((GMBean)bean).getDeteil_url();
				String title=((GMBean)bean).getTitle();
				System.out.println(title);
			    String content1=GMCrawler.creatTBCrawler().searchUrl(((GMBean)bean).getDeteil_url());
			  //  GMCrawler.creatTBCrawler().searchComment(id, "1");
			}
		}
		private static void JDTest() {
			List<String> contents=JDCrawler.creatTBCrawler().searchObject("手机");
			for (String content : contents) {
			
				ShopBean bean=JDFactory.getInstance().createShopImfBean(content);
				String id=((JDBean)bean).getShopID();
				String det=((JDBean)bean).getDeteil_url();
				String titlt=((JDBean)bean).getTitle();
				System.out.println(titlt);
				bean=det.compareTo("null")!=0?JDFactory.getInstance().configShopCommentBean(JDCrawler.creatTBCrawler().searchComment(id, "1"),bean):null;
				if(bean!=null)
				bean.getComments();
			}
			
		}
		private static void TBTest() {
			TBCrawler crawler=TBCrawler.creatTBCrawler();
		    List<String> contents= crawler.searchObject("衣服");
			for (String content : contents) {
				ShopBean bean=TBFactory.getInstance().createShopImfBean(content);
				 String id=((TBBean)bean).getShop_ID();
				 String sellerID=((TBBean)bean).getSeller_ID();
				 String title=((TBBean)bean).getRaw_Title();
				 System.out.println(title);
			}
		  
		}

}
