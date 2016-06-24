package com.data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
/***
 * ���⹫�� ���ݼ�
 * @author e7691
 *
 */
public class JDBean extends ShopBean{

	///������־���
    private static Logger log=Logger.getLogger(JDBean.class.getName());
	//����ƥ���������
	private String shopID;//��Ʒid
	private String price;//�۸�
	private String title;//���⼰����
	private String deteil_url;//��Ʒ��ַ����
	private String img_url;//ͼƬ��ַ
	/***
	 * 
	 */
	  //�������������
		private String goodCount;//����
		private String generalCount;//����
		private String poorCount;//����
		private String name;//��������
	   private List<String> comments;//���ۼ���
   
	  private String shopType;

	public String getShopID() {
		return shopID;
	}


	public void setShopID(String shopID) {
		this.shopID = shopID;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDeteil_url() {
		return deteil_url;
	}


	public void setDeteil_url(String deteil_url) {
		this.deteil_url = deteil_url;
	}


	public String getImg_url() {
		return img_url;
	}


	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}


	public String getGoodCount() {
		return goodCount;
	}


	public void setGoodCount(String goodCount) {
		this.goodCount = goodCount;
	}


	public String getGeneralCount() {
		return generalCount;
	}


	public void setGeneralCount(String generalCount) {
		this.generalCount = generalCount;
	}


	public String getPoorCount() {
		return poorCount;
	}


	public void setPoorCount(String poorCount) {
		this.poorCount = poorCount;
	}


	public String getShopType() {
		return shopType;
	}


	public void setShopType(String shopType) {
		this.shopType = shopType;
	}


	public void setComments(List<String> comments) {
		this.comments = comments;
	}


	private String commentNum;
	public String getCommentNum() {
		return commentNum;
	}


	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}



	public List<String> getComments() {
		
		for (String comment : comments) {
			log.debug(comment);	
		}
		log.debug("���ۼ��ϴ�С"+comments.size());
		return null;
	}


	public void configComment(List<String> list) {
		String gooCount=list.get(0);
		int begin=gooCount.indexOf(":");
		int end=gooCount.indexOf(",");
		if(begin!=-1){
			this.goodCount=gooCount.substring(begin+1, end);
			
		}else{
			this.goodCount=gooCount;
			
		}
		this.comments=list;
	}



}
