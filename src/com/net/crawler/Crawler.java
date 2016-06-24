package com.net.crawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.Header;
/**
 * 爬虫接口
 * @author e7691
 *
 */
public interface Crawler {
  List<String> searchObject(String shopName);//搜索物品 返回 该类物品集合信息
  String searchUrl(String url);//根据url 获取内容
  List<String> searchComment(String shopName);//评论集合
  String searchComment(String user_ID,String page);//page 获取评论页码
  List<String> searchComment(String user_ID,String seller_ID,String page);
}
