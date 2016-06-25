package com.shop.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class GMBean extends ShopBean{
	private String shopID;//…Ã∆∑id
	private String price;
	private String title;
	private String commentNum;
	private String deteil_url;
	private String img_url;
	private String shopType;
	public GMBean() {}
	public String getShopID() {
		return shopID;
	}
	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String data_price) {
		this.price = data_price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
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
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	@Override
	public List<String> getComments() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void configComment(List<String> list) {
		// TODO Auto-generated method stub
		
	}
	 



}
