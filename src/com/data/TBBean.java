package com.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
/**
 * 淘宝数据分类
 * @author e7691
 *
 */
public class TBBean extends ShopBean{
	private static Logger log=Logger.getLogger(TBBean.class.getName());
    private static String ITEM_ID="";
    private static String PRICE="";
    private static String COMMENTNUM="";
    private static String MERCHANT="";
    private int item_ID=0;
    private int category=1;
    private int title=3;
    private int raw_title=4;
    private int pic_url=5;
    private int detail_url=6;
    private int view_price=7;
    private int view_fee=8;
    private int item_loc=9;
    private int reserve_price=10;
    private int view_sales=11;
    private int comment_count=12;
    private int seller_ID=13;
    private int nick=14;//店铺名
    private int commentContent=15;
	//商品店铺消息集合
	private List<String> lists=new ArrayList<String>();
	private String SPLITE="splite";//分隔符号
	private String HOUZHUI="tt";//后缀字符串便于分隔
	private int index;
	public TBBean(List<String> lists){
		config(lists);
	}
    public void show(){
    	int size=lists.size();
    	for(int i=0;i<size;i++){
    		System.out.println(lists.get(i));
    	}
    }
	private  void config(List<String> listss) {
		if(listss.isEmpty()){
			throw new NullPointerException("商品消息集合为空");
		}
		int size= listss.size();
		for(int i=0;i<size-1;i++){
			String str=listss.get(i);
			System.out.println(str);
			String[] shops=str.split(",");
			if(shops!=null&&shops.length>0){
				StringBuilder newStr=new StringBuilder();
				int size2=shops.length;
				for(int i2=0;i2<size2-1;i2++){
					String[] temp=shops[i2].split(":");
					if(temp.length>=2){
					newStr.append(temp[1]+SPLITE);
					}else{
						newStr.append("null"+SPLITE);
					}
				}
				this.lists.add(newStr.toString()+HOUZHUI);
				newStr.setLength(0);
				log.debug("添加新");
			}
		}
		log.debug("结束");
	}


	public String getDeteilUrl() {
		String one=lists.get(2);
		System.out.println(one);
		String[] ones=one.split(SPLITE);
		String test=ones[detail_url];
		return test;
	}
	
	public String getItem_ID() {
		String one=lists.get(index);
		String[] ones=one.split(SPLITE);
		return ones[item_ID];
	}
	
	public List<String> getComments() {
		
		return null;
	}
	@Override
	public void configComment(List<String> list) {
		// TODO Auto-generated method stub
		
	}

}
