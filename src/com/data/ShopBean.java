package com.data;

import java.util.List;

/**
 * ��Ʒ���ݳ���bean
 * @author e7691
 *
 */
public abstract  class ShopBean implements Comment {

  /**
   * ����
   * @return
   */
  public abstract List<String> getComments();
  public abstract void configComment(List<String> list);
}
