package com.shop.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 京东 纯粹公共 数据集
 * @author e7691
 *
 */
public class JDBean extends ShopBean{

	//正则匹配查找内容
	private String id;//商品id
	private String itemID;


	private String price;
	private String title;//标题，描述物品
	private String deteilUrl;//查看物品 链接
	private String imgUrl;//物品 图片链接
	/***
	 * 
	 */
	  //评论配查找内容
	private String goodCount;//商品id
	private String generalCount;//商品中评数目
	private String poorCount;//差评数目
	private String name;//评论内容
    private List<String> comments;//评论集合
    private String shopType;//商品分类

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
