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

import com.logs.ShopLogs;

/***
 * ������Ʒץȡ
 * @author e7691
 *
 */
public class JDCrawler implements Crawler {		     
		     private static String fileName="./data/jd/";//�洢html����Ŀ¼
		     private static Link link=new Link();//http����
		     private static byte[] data=new byte[500000];//ҳ�滺��
		     
			 private static JDCrawler crawler=new JDCrawler();
		     public static JDCrawler creatTBCrawler(){
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
		    			 ShopLogs.error(JDCrawler.class.getName(),"���� �����ļ��쳣");
		    			 throw new NullPointerException("�����ļ��쳣");
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
	 * ���� ���������ļ�
	 * @param shopName ������Ʒ����
	 * @return
	 */
     private static String configFile(String shopName) {
		    	 String name=fileName+shopName+"/config.txt";
		    	 String reFileName=fileName+shopName+"/";
		    	 FileOutputStream out=null;
		    	 //ֻ��outʱ��ʹ����δ���޸ĵļ�ֵ�Ա����ǣ�ɾ��
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
    /**
     * �洢��ȡ������   
     * @param file
     * @param shopName
     */
	private static void updateShopData(File file, String shopName){
		    	 byte[] data=link.doGetBytes("http://search.jd.com/Search?keyword="+shopName+"&enc=utf-8");
		    	 System.out.println(data.length);
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
			@Override
			public List<String> searchObject(String shopName) {
				List<String> lists=new ArrayList<String>();
				String content=searchOneShopData(shopName);
				Pattern searchMeanPattern2 = Pattern.compile("<li data-sku=.*?</a></div>.*?</li>", Pattern.DOTALL);
				Matcher m2 = searchMeanPattern2.matcher(content); //m1�ǻ�ȡ�������������<div>��
				while(m2.find()){
					String means2=m2.group();
					lists.add(means2);
				}
				int size=lists.size();
				System.out.println("��Ʒ����:"+size);
				return lists;
			}
			@Override
			public List<String> searchComment(String shopName) {
				
				return null;
			}
			@Override
			public String searchComment(String id, String page) {
				String remomment=getRecomment(id, page);
				//JDShopComment bean=new JDShopComment(remomment);
//				List<String> comments=new ArrayList<String>();
//				if(remomment!=null){
//					
//		    		Pattern searchMeanPattern2 = Pattern.compile("\"goodCount\":.*?,");
//					Matcher m2 = searchMeanPattern2.matcher(remomment); //m1�ǻ�ȡ�������������<div>��
//					if(m2.find()){
//						String tp=m2.group();
//						System.out.println(tp);
//						comments.add(tp);
//					}
//					searchMeanPattern2 = Pattern.compile("\"generalCount\":.*?,");
//					 m2 = searchMeanPattern2.matcher(remomment); //m1�ǻ�ȡ�������������<div>��
//					if(m2.find()){
//						String tp=m2.group();
//						System.out.println(tp);
//						comments.add(tp);
//					}
//					searchMeanPattern2 = Pattern.compile("\"poorCount\":.*?,");
//					m2 = searchMeanPattern2.matcher(remomment); //m1�ǻ�ȡ�������������<div>��
//					if(m2.find()){
//						String tp=m2.group();
//						System.out.println(tp);
//						comments.add(tp);
//					}
//					 searchMeanPattern2 = Pattern.compile("\"content\":.*?,");
//				      m2 = searchMeanPattern2.matcher(remomment); //m1�ǻ�ȡ�������������<div>��
//		 			while(m2.find()){
//						String tp=m2.group();
//						System.out.println(tp);
//						comments.add(tp);
//					}
////		 			//��������
////					 searchMeanPattern2 = Pattern.compile("\"rateContent\":.*?,");
////				      m2 = searchMeanPattern2.matcher(remomment); //m1�ǻ�ȡ�������������<div>��
////		 			while(m2.find()){
////						String tp=m2.group();
////						comments.add(tp);
////					}
//		 			return comments;
//				}
				return remomment;
			}
				private  static String getRecomment(String shopID, String page) {
                 String data=link.doGetString("http://club.jd.com/productpage/p-"+shopID+"-s-0-t-"+page+"-p-0.html");
			    	// System.out.println(data.length);
					return data;
				}
			@Override
			public String searchUrl(String url) {
				
				return null;
			}
			@Override
			public List<String> searchComment(String user_ID, String seller_ID,
					String page) {
				
				return null;
			}
}
