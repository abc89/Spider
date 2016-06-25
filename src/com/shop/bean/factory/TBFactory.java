package com.shop.bean.factory;

import java.util.HashMap;
import java.util.Set;

import com.shop.bean.*;

/**
 * 淘宝 factory 
 * @author e7691
 *
 */
public class TBFactory extends ShopBeanFactoryMB{
    private final int shop_ID=0;//物品id
    private final int category=1;
    private final int title=2;
    private final int raw_Title=3;
    private final int img_Url=4;
    private final int detail_Url=5;//物品url链接地址
    private final int price=6;
    private final int item_loc=7;//物品出货地址
    private final int view_Sales=8;
    private final int seller_ID=9;//买家id
    private final int nick=10;//店铺名
    private String[] parentsT=new String[]{"\"nid\":.*?,","\"category\":.*?,","\"title\":.*?,","\"raw_title\":.*?,","\"pic_url\":.*?,","\"detail_url\":.*?,","\"view_price\":.*?,","\"item_loc\":.*?,","\"view_sales\":.*?,","\"user_id\":.*?,","\"nick\":.*?,"};
   
    private static TBFactory facy=new TBFactory();
    private TBFactory(){
    	super.parents=parentsT;
    }
    public static TBFactory getInstance(){
    	return facy;

    }
    protected ShopBean setBeanImf(HashMap<Integer, String> maps) {
    	TBBean bean=new TBBean();
		Set<Integer> keys=maps.keySet();
		for (Integer index : keys) {
			switch(index){
		     case shop_ID:bean.setShop_ID(maps.get(index));break;
		     case price:bean.setPrice(maps.get(index));break;
		     case img_Url:bean.setImg_Url(maps.get(index));break;
		     case detail_Url:bean.setDetail_Url(maps.get(index));break;
		     case title:bean.setTitle(maps.get(index));break;
		     case raw_Title:bean.setRaw_Title(maps.get(index));break;
		     case view_Sales:bean.setView_Sales(maps.get(index));break;
		     case seller_ID:bean.setSeller_ID(maps.get(index));break;
		}
		}
		
		return bean;
	}

}
