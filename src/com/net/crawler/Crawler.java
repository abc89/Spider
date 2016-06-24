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
 * ����ӿ�
 * @author e7691
 *
 */
public interface Crawler {
  List<String> searchObject(String shopName);//������Ʒ ���� ������Ʒ������Ϣ
  String searchUrl(String url);//����url ��ȡ����
  List<String> searchComment(String shopName);//���ۼ���
  String searchComment(String user_ID,String page);//page ��ȡ����ҳ��
  List<String> searchComment(String user_ID,String seller_ID,String page);
}
