package com.data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class GMBean extends ShopBean{
	private static Logger log=Logger.getLogger(GMBean.class.getName());
	private List<String> lists=new ArrayList<String>();
	private String SPLITE="splite";//�ָ�����
	private String HOUZHUI="tt";//��׺�ַ������ڷָ�
	private int index;
	//����ƥ���������
	private int shopID=0;//��Ʒid
	private int data_price=1;
	private int title=2;
	private int commentNum=3;
	private int deteil_url=4;
	private int img_url=5;
	private String[] parents=new String[]{"pId=\".*?\"","price=\".*?\"","title=\".*?\"","track=\"��Ʒ�б�������.*?</a>","href=\".*?\"","gome-src=\".*?\""};
	public GMBean(List<String> content) {
		config(content);
		}
	    public void show(){
	    	int size=lists.size();
	    	for(int i=0;i<size;i++){
	    		System.out.println(lists.get(i));
	    	}
	    }
		private  void config(List<String> listss) {
			if(listss.isEmpty()){
				log.debug( "��Ʒ��Ϣ����Ϊ��");
				throw new NullPointerException("��Ʒ��Ϣ����Ϊ��");
			}
			int size= listss.size();
			StringBuilder builder=new StringBuilder();
			for(int i=0;i<size;i++){
				String content=listss.get(i);
				     int len=parents.length;
				     for(int j=0;j<len;j++){
				    	 Pattern searchMeanPattern2 = Pattern.compile(parents[j]);
							Matcher m2 = searchMeanPattern2.matcher(content); //m1�ǻ�ȡ�������������<div>��
							if(m2.find()){
								String means=m2.group()+"a";
								int begin=means.indexOf("=\"");
								if(begin!=-1){
									if(means.indexOf(">")!=-1){
											begin=means.indexOf("\">");
											int end=means.indexOf("<");
											means=means.substring(begin+2,end);
									}else{
									int end=means.indexOf("\"a");
									means=means.substring(begin+2,end);
									}
								}
								builder.append(means+SPLITE);
							}
				     }
					lists.add(builder.toString()+"tt");
					builder.setLength(0);
				
			}
		}


	public String getDeteilUrl() {
		String one=lists.get(index);
		System.out.println(one);
		String[] ones=one.split(SPLITE);
		String test=ones[deteil_url];
		return test;
	}

	public String getItem_ID() {
		String one=lists.get(index);
		String[] ones=one.split(SPLITE);
		return ones[shopID];
	}
	
	@Override
	public void configComment(List<String> list) {
	}
	@Override
	public List<String> getComments() {
		// TODO Auto-generated method stub
		return null;
	}


}
