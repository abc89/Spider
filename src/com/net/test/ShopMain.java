package com.net.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shop.bean.*;
import com.shop.bean.factory.*;
import com.data.dao.JDDao;
import com.net.crawler.GMCrawler;
import com.net.crawler.JDCrawler;
import com.net.crawler.TBCrawler;

public class ShopMain {
	 private static List<String> lists=new ArrayList<String>();
		public static void main(String[] args) {
			//TBTest();
			//JDTest();
			JDShow();
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
		/**
		 * 数据查看
		 */
		private static void JDShow() {
			new JDDao().getItemType("手机");
		}
		/**
		 * 爬取数据测试
		 */
		private static void JDTest() {
			String search="手机";
			List<String> contents=JDCrawler.creatTBCrawler().searchObject(search);
			for (String content : contents) {
			
				ShopBean bean=JDFactory.getInstance().createShopImfBean(content);
				((JDBean)bean).setShopType(search);
				String id=((JDBean)bean).getItemID();
				String det=((JDBean)bean).getDeteilUrl();
				String titlt=((JDBean)bean).getTitle();
				System.out.println(titlt);
				bean=det.compareTo("null")!=0?JDFactory.getInstance().configShopCommentBean(JDCrawler.creatTBCrawler().searchComment(id, "1"),bean):null;
				if(bean!=null){
				
				bean.getComments();
				new JDDao().add((JDBean)bean);
				}
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
