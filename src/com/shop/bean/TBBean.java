package com.shop.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 淘宝数据分类
 * @author e7691
 *
 */
public class TBBean extends ShopBean{
    private static String ITEM_ID="";
    private static String PRICE="";
    private static String COMMENTNUM="";
    private static String MERCHANT="";
    private String shop_ID;
    private String category;
    private String title;
    private String raw_Title;
    private String img_Url;
    private String detail_Url;
    private String price;
    private String view_Fee;
    private String item_Loc;
    private String reserve_Price;
    private String view_Sales;
	private String comment_Count;
    private String seller_ID;
    private String nick;//店铺名
    private String commentContent;
    private String shopType;
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	//商品店铺消息集合
	private List<String> comments=new ArrayList<String>();
	public String getShop_ID() {
		return shop_ID;
	}
	public void setShop_ID(String shop_ID) {
		this.shop_ID = shop_ID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRaw_Title() {
		return raw_Title;
	}
	public void setRaw_Title(String raw_Title) {
		this.raw_Title = raw_Title;
	}
	public String getImg_Url() {
		return img_Url;
	}
	public void setImg_Url(String img_Url) {
		this.img_Url = img_Url;
	}
	public String getDetail_Url() {
		return detail_Url;
	}
	public void setDetail_Url(String detail_Url) {
		this.detail_Url = detail_Url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getView_Fee() {
		return view_Fee;
	}
	public void setView_Fee(String view_Fee) {
		this.view_Fee = view_Fee;
	}
	public String getItem_Loc() {
		return item_Loc;
	}
	public void setItem_Loc(String item_Loc) {
		this.item_Loc = item_Loc;
	}
	public String getReserve_Price() {
		return reserve_Price;
	}
	public void setReserve_Price(String reserve_Price) {
		this.reserve_Price = reserve_Price;
	}
	public String getView_Sales() {
		return view_Sales;
	}
	public void setView_Sales(String view_Sales) {
		this.view_Sales = view_Sales;
	}
	public String getSeller_ID() {
		return seller_ID;
	}
	public void setSeller_ID(String seller_ID) {
		this.seller_ID = seller_ID;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
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
