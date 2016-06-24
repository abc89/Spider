package com.data;

import java.util.List;

/**
 * 商品数据抽象bean
 * @author e7691
 *
 */
public abstract  class ShopBean implements Comment {

  /**
   * 评论
   * @return
   */
  public abstract List<String> getComments();
  public abstract void configComment(List<String> list);
}
