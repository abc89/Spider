package com.shop.bean.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shop.bean.*;

/**
 * ���� bean ���ɹ���
 * @author e7691
 *
 */
public class JDFactory extends ShopBeanFactoryMB{

	/***
	 * ��Ʒ��Ϣ��������
	 */
	//����ƥ���������
	private  final int shopID=0;//��Ʒid
	private final int price=1;
	private final int title=2;
	private final int commentNum=3;
	private final int deteil_url=4;
	private final int img_url=5;
	//��Ʒ��Ϣ���� ����
	private String[] jdParents=new String[]{"data-sku=\".*?\"","data-price=\".*?\"","_blank\" title=\".*?\"","flagsClk.*?</a>������","href=\".*?\"","data-lazy-img=\".*?\""};
	/***
	 * ��Ʒ���۷���
	 */
	//���������
	private final int goodCount=0;//��Ʒid
	private final int generalCount=1;
	private final int poorCount=2;
	private int name=3;//��������
	//����ƥ����� ����
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
	 * JDBean ���
	 * @param bean
	 * @param means �������
	 * @param index ���λ������
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
