package com.net.crawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;

public class TBCrawler implements Crawler{

	     private static TBCrawler crawler=new TBCrawler();
	     private static StringBuilder builder=new StringBuilder();
	     private static String fileName="./data/tb/";
	     private static Link link=new Link();
	     private static byte[] data=new byte[500000];
	     public static TBCrawler creatTBCrawler(){
	    	 return crawler;
	     }
	  private static String searchAllShopData(String shopName){
	    	 String name=fileName+shopName+"/1.txt";
	    	 try {
	    		 File file=new File(name);
	        	 if(!file.getParentFile().exists()){
	        		 file.getParentFile().mkdirs();
	        		 file.createNewFile();
	        		 updateShopData(file,shopName);
	        	 }else if(!file.exists()){		
	    				updateShopData(file,shopName);
	     		}
	 		InputStream input=new FileInputStream(file);
	 		input.read(data);
	 	    
	 		shopName=new String(data, "utf-8");
	    	 } catch (IOException e) {
	    		 
	    		 e.printStackTrace();
	    	 }
			return shopName; 	 
	     }
	  private static String searchOneShopData(String shopName){
	    	
	    	 try {
	    		 String  fileName=configFile(shopName);
	    		 if(fileName==null){
	    			 throw new NullPointerException("配置文件异常");
	    		 }
	    		 File file=new File(fileName);
	              if(!file.exists()){		 
	            	    file.createNewFile();
	    				updateShopData(file,shopName);
	     		}
	 		InputStream input=new FileInputStream(file);
	 		input.read(data);
	 	    
	 		shopName=new String(data, "utf-8");
	    	 } catch (IOException e) {
	    		 
	    		 e.printStackTrace();
	    	 }
			return shopName; 	 
	     }
	  /**
	   * 获取文件存储路径
	   * @param shopName 商品名称
	   * @return
	   */
	     private static String configFile(String shopName) {
	    	 String name=fileName+shopName+"/config.txt";
	    	 String reFileName=fileName+shopName+"/";
	    	 FileOutputStream out=null;
	    	 //只用out时将使其他未被修改的键值对被覆盖，删除
	    	 FileInputStream in=null;
	    	 try {
	    		 File file=new File(name);
	     	 if(!file.getParentFile().exists()){
        		   file.getParentFile().mkdirs();
        		   file.createNewFile();
         			in=new FileInputStream(file);
         			Properties pro=new Properties();
         			pro.load(in);	
         			 out=new FileOutputStream(file);
         			  Map toSaveMap = new HashMap();
         			   toSaveMap.put("num", "1");
         			  pro.putAll(toSaveMap);
         			pro.store(out, "create config");
         			in.close();
         			out.close();
         			return reFileName+"1.txt";
        	 }else if(!file.exists()){		 
        		   file.createNewFile();
        			in=new FileInputStream(file);
        			Properties pro=new Properties();
        			pro.load(in);	
        			 out=new FileOutputStream(file);
        			  Map toSaveMap = new HashMap();
        			   toSaveMap.put("num", "1");
        			  pro.putAll(toSaveMap);
        			pro.store(out, "create config");
        			in.close();
        			out.close();
        			return reFileName+"1.txt";
        	 }else{
        		 in=new FileInputStream(file);
     			Properties pro=new Properties();
     			pro.load(in);
     			String getValue=pro.getProperty("num");
     			in.close();
     			int size=Integer.parseInt(getValue);
     			size++;
     			return reFileName+Integer.toString(size)+".txt";
        	 }
	    	 }catch(Exception e){}
		return null;
	}
		private static void updateShopData(File file, String shopName){
	    	 byte[] data=link.doGetBytes("https://s.taobao.com/search?q="+shopName);
			try {
					file.createNewFile();
					OutputStream outputStream=new FileOutputStream(file);
					outputStream.write(data);
					outputStream.flush();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
	     }
		//https://aldcdn.tmall.com/recommend.htm?appId=03067&itemId=38698117173&vid=0&curPage=1&step=100&categoryId=50006888&sellerId=2003774307&shopId=109028094&brandId=30812&refer=&callback=jsonpAldTabWaterfall
		//https://rate.tmall.com/list_detail_rate.htm?itemId=38698117173&spuId=272092043&sellerId=2003774307&order=3&currentPage=2
		//https://rate.tmall.com/list_detail_rate.htm?itemId=38698117173&sellerId=2003774307&order=3&currentPage=1
		private static void updataComment(String shopID,File file, String page){
			 System.out.println("更新数据");
	    	 byte[] data=link.doGetBytes("https://rate.tmall.com/list_detail_rate.htm?itemId=38698117173&spuId=272092043&sellerId=2003774307&order=3&currentPage=1");

	        // String sdata=data.substring(2100, data.length());
	         //byte[] s = null;
			try {
					file.createNewFile();
					OutputStream outputStream=new FileOutputStream(file);
					outputStream.write(data);
					outputStream.flush();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		private  static Header[] searchShopData2(String url) {
			 Header[] data=link.doGetHeads(url);
			return data;
		}
		private  static String getRecomment(String item_ID, String seller_ID,
				String string) {
      	 String data=link.doGetString("https://rate.tmall.com/list_detail_rate.htm?itemId="+item_ID+"&sellerId="+seller_ID+"&order=3&currentPage=2&append=0&content=1");
	    	// System.out.println(data.length);
			return data;
		}
		public  List<String> searchObject(String shopName) {
			List<String> lists=new ArrayList<String>();
			String result=  TBCrawler.searchOneShopData(shopName);
			Pattern searchMeanPattern1 = Pattern.compile("\"nid\":.*?\"shopcard\"");
			Matcher m1 = searchMeanPattern1.matcher(result); //m1是获取包含翻译的整个<div>的
			StringBuilder builder=new StringBuilder();
			while(m1.find()){
				String means=m1.group();
				lists.add(means);
			}
			return lists;
		}
		@Override
		public List<String> searchComment(String detail_url) {
		List<String> comments=new ArrayList<String>();
 			String	comment="https:"+detail_url;
 			comment=comment.replaceAll("\\\\u003d", "=");
 			comment=comment.replaceAll("\\\\u0026", "&");
 				System.out.println(comment);
			//字符串显示\u003d,故原本为\\003d,故要匹配掉\\  则表达式为“\\\\”
			
		
			Header[] content1=TBCrawler.searchShopData2(comment);
			int size1=content1.length;
			String item_ID = null;
			String seller_ID=null;
			if(content1!=null&&size1>0){
				for(int i=0;i<size1;i++){
					String key=content1[i].getName();
					
					if(key.compareTo("at_itemId")==0){
						item_ID=content1[i].getValue();
					}else if(key.compareTo("at_prid")==0){
						seller_ID=content1[i].getValue();
					}
				}
			}
			if(seller_ID!=null){
				//追加评论
				String remomment=TBCrawler.getRecomment(item_ID,seller_ID,"1");
				Pattern searchMeanPattern2 = Pattern.compile("\"content\":.*?,");
				Matcher m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
	 			while(m2.find()){
					String tp=m2.group();
					comments.add(tp);
				}
	 			//初次评论
				 searchMeanPattern2 = Pattern.compile("\"rateContent\":.*?,");
			      m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
	 			while(m2.find()){
					String tp=m2.group();
					comments.add(tp);
				}
	 			return comments;
			}
 			//}
			return null;
		}
		@Override
		public String searchComment(String id, String page) {
			
			return null;
		}
		@Override
		public String searchUrl(String url) {
			
			return null;
		}
		@Override
		public List<String> searchComment(String user_ID, String seller_ID,
				String page) {
			List<String> comments=new ArrayList<String>();
			String remomment=TBCrawler.getRecomment(user_ID,seller_ID,"1");
			//总评论
			Pattern searchMeanPattern2 = Pattern.compile("\"total\":.*?,");
			Matcher m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
			if(m2.find()){
				String tp=m2.group();
				System.out.println(tp);
				comments.add(tp);
			}
			//图片评论
			searchMeanPattern2 = Pattern.compile("\"picNum\":.*?,");
			 m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
			if(m2.find()){
				String tp=m2.group();
				System.out.println(tp);
				comments.add(tp);
			}
			//追加评论
			searchMeanPattern2 = Pattern.compile("\"used\":.*?,");
			m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
			if(m2.find()){
				String tp=m2.group();
				System.out.println(tp);
				comments.add(tp);
			}
			searchMeanPattern2 = Pattern.compile("\"content\":.*?,");
			 m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
 			while(m2.find()){
				String tp=m2.group();
				comments.add(tp);
			}
 			//初次评论
			 searchMeanPattern2 = Pattern.compile("\"rateContent\":.*?,");
		      m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
 			while(m2.find()){
				String tp=m2.group();
				comments.add(tp);

			}
 			return comments;
		}
}
