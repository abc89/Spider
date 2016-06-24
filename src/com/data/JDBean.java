package com.data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
/***
 * 纯粹公共 数据集
 * @author e7691
 *
 */
public class JDBean extends ShopBean{

	///测试日志输出
    private static Logger log=Logger.getLogger(JDBean.class.getName());
	//正则匹配查找内容
	private String shopID;//商品id
	private String price;//价格
	private String title;//标题及描述
	private String deteil_url;//物品地址链接
	private String img_url;//图片地址
	/***
	 * 
	 */
	  //评论配查找内容
		private String goodCount;//好评
		private String generalCount;//中评
		private String poorCount;//差评
		private String name;//评论内容
	   private List<String> comments;//评论集合
   
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
		log.debug("评论集合大小"+comments.size());
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
