package com.shop.bean.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shop.bean.*;

/**
 * 国美 bean生成工厂
 * @author e7691
 *
 */
public class GMFactory extends ShopBeanFactoryMB{

	//正则匹配查找内容
	private final int shopID=0;//商品id
	private final int price=1;
	private final int title=2;
	private final int commentNum=3;
	private final int deteil_url=4;
	private final int img_url=5;
	private final String[] parentsT=new String[]{"pId=\".*?\"","price=\".*?\"","title=\".*?\"","track=\"产品列表评论数.*?</a>","href=\".*?\"","gome-src=\".*?\""};
	//单例
	private static GMFactory facy=new GMFactory();
	private GMFactory(){
		super.parents=parentsT;
	}
	public static GMFactory getInstance(){	    	
		return facy;
	}
	@Override
	protected ShopBean setBeanImf(HashMap<Integer, String> maps) {
		GMBean bean=new GMBean();
		Set<Integer> keys=maps.keySet();
		for (Integer index : keys) {
			switch(index){
		     case shopID:bean.setShopID(maps.get(index));break;
		     case price:bean.setPrice(maps.get(index));break;
		     case img_url:bean.setImg_url(maps.get(index));break;
		     case deteil_url:bean.setDeteil_url(maps.get(index));break;
		     case title:bean.setTitle(maps.get(index));break;
		     case commentNum:bean.setCommentNum(maps.get(index));
		}
		}
		
		return bean;
	}


}
