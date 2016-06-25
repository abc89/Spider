package com.net.crawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 国美 商品抓取
 * @author e7691
 *
 */
public class GMCrawler implements Crawler{
    private static GMCrawler crawler=new GMCrawler();
    private static StringBuilder builder=new StringBuilder();
    private static String fileName="./data/gm/";
    private static Link link=new Link();
    private static byte[] data=new byte[500000];
    public static GMCrawler creatTBCrawler(){
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
   	 byte[] data=link.doGetBytes("http://search.gome.com.cn/search?question="+shopName);
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
   	 byte[] data=link.doGetBytes("http://ss.gome.com.cn/item/v1/prdevajsonp/appraiseModuleAjax/"+shopID+"/"+page+"/all/flag/appraise/all");

		try {
				file.createNewFile();
				OutputStream outputStream=new FileOutputStream(file);
				outputStream.write(data);
				outputStream.flush();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}
	public List<String> searchObject(String shopName) {
		List<String> lists=new ArrayList<String>();
		String content=searchOneShopData(shopName);
		Pattern searchMeanPattern2 = Pattern.compile("<li class=\"product-item\".*?</li>", Pattern.DOTALL);
		Matcher m2 = searchMeanPattern2.matcher(content); //m1是获取包含翻译的整个<div>的
		while(m2.find()){
			String means2=m2.group();
			lists.add(means2);
		}
		int size=lists.size();
		return lists;
	}

	@Override
	public List<String> searchComment(String shopName) {
		
		return null;
	}
	@Override
	public String searchComment(String id, String page) {
		String remomment=getRecomment(id, page);
		List<String> comments=new ArrayList<String>();
		if(remomment!=null){
			
    		Pattern searchMeanPattern2 = Pattern.compile("\"good\":.*?,");
			Matcher m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
			if(m2.find()){
				String tp=m2.group();
				System.out.println(tp);
				comments.add(tp);
			}
			searchMeanPattern2 = Pattern.compile("\"mid\":.*?,");
			 m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
			if(m2.find()){
				String tp=m2.group();
				System.out.println(tp);
				comments.add(tp);
			}
			searchMeanPattern2 = Pattern.compile("\"bad\":.*?,");
			m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
			if(m2.find()){
				String tp=m2.group();
				System.out.println(tp);
				comments.add(tp);
			}
			 searchMeanPattern2 = Pattern.compile("\"appraiseElSum\":.*?,");
		      m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
 			while(m2.find()){
				String tp=m2.group();
				System.out.println(tp);
				comments.add(tp);
			}
// 			//初次评论
//			 searchMeanPattern2 = Pattern.compile("\"rateContent\":.*?,");
//		      m2 = searchMeanPattern2.matcher(remomment); //m1是获取包含翻译的整个<div>的
// 			while(m2.find()){
//				String tp=m2.group();
//				comments.add(tp);
//			}
 			return null;
		}
		return null;
	}
	private  static String getRecomment(String shopID, String page) {
	 String data=link.doGetString("http://ss.gome.com.cn/item/v1/prdevajsonp/appraiseModuleAjax/"+shopID+"/"+page+"/all/flag/appraise/all");
    	// System.out.println(data.length);
		return data;
	}
	@Override
	public String searchUrl(String url) {
		 String data=link.doGetString(url);
	    	// System.out.println(data.length);
			return data;
	}
	@Override
	public List<String> searchComment(String user_ID, String seller_ID,
			String page) {
		
		return null;
	}
}
