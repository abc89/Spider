package com.shop.bean.factory;

import java.util.List;

import com.shop.bean.ShopBean;


/**
 * 商品 bean 生成工厂 抽象父类
 * @author e7691
 *
 */
public abstract class ShopBeanFactory {

	/**
	 * 
	 * @param content 评论内容
	 * @param bean  装饰 bean
	 * @return
	 */
	public  abstract ShopBean configShopCommentBean(String content,ShopBean bean);
	public abstract ShopBean createShopImfBean(String imf);
}
