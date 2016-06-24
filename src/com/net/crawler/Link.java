package com.net.crawler;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * http 客户端连接类
 * @author e7691
 *
 */
public class Link {
		private CloseableHttpClient httpClient;
	    public Link(){
	        httpClient = HttpClients.createDefault();
	    }
		
		public  byte[] doGetBytes(String cmds) {
			byte[] content =null;
			try{
			//重新登陆更新sidhttp://skyhero15.yytou.com:8080/gCmd.do?cmd=1eec&sid=e29aql2zs434beao2ll
			        HttpGet getWordMean = new HttpGet(cmds);
			        CloseableHttpResponse response = httpClient.execute(getWordMean);//取得返回的网页源码
			      
			        content= EntityUtils.toByteArray(response.getEntity());			       
			        response.close();
			        //注意(?s)，意思是让'.'匹配换行符，默认情况下不匹配
			      
		          }catch(Exception e){
		             	e.printStackTrace();
		           }
			return content;
		}
	
		public org.apache.http.Header[] doGetHeads(String cmds) {
			org.apache.http.Header[] haHeaders =null;
			try{
			//重新登陆更新sidhttp://skyhero15.yytou.com:8080/gCmd.do?cmd=1eec&sid=e29aql2zs434beao2ll
			        HttpGet getWordMean = new HttpGet(cmds);
			        CloseableHttpResponse response = httpClient.execute(getWordMean);//取得返回的网页源码
			       haHeaders=response.getAllHeaders();
			 //       System.out.println("编码方式"+entityUtils.getContentEncoding()+response.getHeaders(""));
			     //   content=EntityUtils.toString(entityUtils);			       
			        response.close();
			        //注意(?s)，意思是让'.'匹配换行符，默认情况下不匹配
			      
		          }catch(Exception e){
		             	e.printStackTrace();
		           }
			return haHeaders;
		}

		public String doGetString(String cmds) {
			String content =null;
			try{
			//重新登陆更新sidhttp://skyhero15.yytou.com:8080/gCmd.do?cmd=1eec&sid=e29aql2zs434beao2ll
			        HttpGet getWordMean = new HttpGet(cmds);
			        CloseableHttpResponse response = httpClient.execute(getWordMean);//取得返回的网页源码
			      
			        content= EntityUtils.toString(response.getEntity());			       
			        response.close();
			        //注意(?s)，意思是让'.'匹配换行符，默认情况下不匹配
			      
		          }catch(Exception e){
		             	e.printStackTrace();
		           }
			return content;
		}
	
}
