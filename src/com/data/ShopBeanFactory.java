package com.data;

import java.util.List;


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
