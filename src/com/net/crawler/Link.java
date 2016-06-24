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
 * http �ͻ���������
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
			//���µ�½����sidhttp://skyhero15.yytou.com:8080/gCmd.do?cmd=1eec&sid=e29aql2zs434beao2ll
			        HttpGet getWordMean = new HttpGet(cmds);
			        CloseableHttpResponse response = httpClient.execute(getWordMean);//ȡ�÷��ص���ҳԴ��
			      
			        content= EntityUtils.toByteArray(response.getEntity());			       
			        response.close();
			        //ע��(?s)����˼����'.'ƥ�任�з���Ĭ������²�ƥ��
			      
		          }catch(Exception e){
		             	e.printStackTrace();
		           }
			return content;
		}
	
		public org.apache.http.Header[] doGetHeads(String cmds) {
			org.apache.http.Header[] haHeaders =null;
			try{
			//���µ�½����sidhttp://skyhero15.yytou.com:8080/gCmd.do?cmd=1eec&sid=e29aql2zs434beao2ll
			        HttpGet getWordMean = new HttpGet(cmds);
			        CloseableHttpResponse response = httpClient.execute(getWordMean);//ȡ�÷��ص���ҳԴ��
			       haHeaders=response.getAllHeaders();
			 //       System.out.println("���뷽ʽ"+entityUtils.getContentEncoding()+response.getHeaders(""));
			     //   content=EntityUtils.toString(entityUtils);			       
			        response.close();
			        //ע��(?s)����˼����'.'ƥ�任�з���Ĭ������²�ƥ��
			      
		          }catch(Exception e){
		             	e.printStackTrace();
		           }
			return haHeaders;
		}

		public String doGetString(String cmds) {
			String content =null;
			try{
			//���µ�½����sidhttp://skyhero15.yytou.com:8080/gCmd.do?cmd=1eec&sid=e29aql2zs434beao2ll
			        HttpGet getWordMean = new HttpGet(cmds);
			        CloseableHttpResponse response = httpClient.execute(getWordMean);//ȡ�÷��ص���ҳԴ��
			      
			        content= EntityUtils.toString(response.getEntity());			       
			        response.close();
			        //ע��(?s)����˼����'.'ƥ�任�з���Ĭ������²�ƥ��
			      
		          }catch(Exception e){
		             	e.printStackTrace();
		           }
			return content;
		}
	
}
