package com.shop.bean;

import java.util.List;

/**
 * ��Ʒ���ݷ���洢
 * @author e7691
 *
 */
public abstract  class ShopBean implements Comment {

  public abstract String getShopType();
  public abstract List<String> getComments();
  public abstract void configComment(List<String> list);
}
