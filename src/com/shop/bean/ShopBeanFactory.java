package com.shop.bean;

import java.util.List;

/**
 * bean ���ɹ���
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
