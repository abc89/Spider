package com.shop.bean.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shop.bean.*;

/**
 * 京东 bean 生成工长
 * @author e7691
 *
 */
public class JDFactory extends ShopBeanFactoryMB{

	/***
	 * 商品信息分析属性
	 */
	//正则匹配查找内容
	private  final int shopID=0;//商品id
	private final int price=1;
	private final int title=2;
	private final int commentNum=3;
	private final int deteil_url=4;
	private final int img_url=5;
	//商品信息分析 正则
	private String[] jdParents=new String[]{"data-sku=\".*?\"","data-price=\".*?\"","_blank\" title=\".*?\"","flagsClk.*?</a>人评价","href=\".*?\"","data-lazy-img=\".*?\""};
	/***
	 * 商品评论分析
	 */
	//配查找内容
	private final int goodCount=0;//商品id
	private final int generalCount=1;
	private final int poorCount=2;
	private int name=3;//评论内容
	//评论匹配规则 数组
	private String[] commentsT=new String[]{"\"goodCount\":.*?,","\"generalCount\":.*?,","\"poorCount\":.*?,","\"content\":.*?,","\"commentId\":.*?,","\"score\":.*?,","\"uid\":.*?,"};
    private static JDFactory facy=new JDFactory();
    private JDFactory(){
    	super.parents=jdParents;
    	super.comments=commentsT;
    }
    public static JDFactory getInstance(){
    	return facy;

    }
	/**
	 * JDBean 填充
	 * @param bean
	 * @param means 填充内容
	 * @param index 填充位置索引
	 */
	@Override
	protected ShopBean setBeanImf(HashMap<Integer, String> maps) {
				JDBean bean=new JDBean();
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
