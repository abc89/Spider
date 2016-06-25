package com.shop.bean.factory;

import java.util.List;

import com.shop.bean.ShopBean;


/**
 * ��Ʒ bean ���ɹ��� ������
 * @author e7691
 *
 */
public abstract class ShopBeanFactory {

	/**
	 * 
	 * @param content ��������
	 * @param bean  װ�� bean
	 * @return
	 */
	public  abstract ShopBean configShopCommentBean(String content,ShopBean bean);
	public abstract ShopBean createShopImfBean(String imf);
}
