package com.shop.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * ���� ���⹫�� ���ݼ�
 * @author e7691
 *
 */
public class JDBean extends ShopBean{

	//����ƥ���������
	private String id;//��Ʒid
	private String itemID;


	private String price;
	private String title;//���⣬������Ʒ
	private String deteilUrl;//�鿴��Ʒ ����
	private String imgUrl;//��Ʒ ͼƬ����
	/***
	 * 
	 */
	  //�������������
	private String goodCount;//��Ʒid
	private String generalCount;//��Ʒ������Ŀ
	private String poorCount;//������Ŀ
	private String name;//��������
    private List<String> comments;//���ۼ���
    private String shopType;//��Ʒ����

	public String getId() {
		return id;
	}
	public String getItemID() {
		return itemID;
	}
	
	
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getDeteilUrl() {
		return deteilUrl;
	}


	public void setDeteilUrl(String deteil_url) {
		this.deteilUrl = deteil_url;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String img_url) {
		this.imgUrl = img_url;
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
		return comments;
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
