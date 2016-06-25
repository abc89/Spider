package com.shop.bean;

import java.util.List;

/**
 * 商品数据分类存储
 * @author e7691
 *
 */
public abstract  class ShopBean implements Comment {

  public abstract String getShopType();
  public abstract List<String> getComments();
  public abstract void configComment(List<String> list);
}
