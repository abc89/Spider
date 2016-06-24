package com.myweb.db;

import java.io.File;
import java.sql.Connection;

import com.databases.DBConnectionManager;

/**
 * ���ݿ����
 * @author e7691
 *
 */
public class DataBaseOperate {
	//���ݿ����ӳ� ������
   private static DBConnectionManager  dbConnectionManager=DBConnectionManager.getInstance();
   private static boolean initial=false;
   private static String configPath="./source/db/ds_Config.xml";//Ĭ�ϳ�ʼ�� �����ļ�·��
   /***
    * 
    * @param path ���ݿ������ļ�·�����ļ�
    */
   public static void init(String path){
	   if(!initial){
		   File file=new File("."); 
		   String curPath=file.getAbsolutePath();
		  
		   System.out.println(curPath);
		   dbConnectionManager.configFilePath(path);
		   initial=true;
	   }
   }
   /**
    * 
    * @return ���ݿ�����ʵ��
    * @throws DataBaseOperateException
    */
   public static synchronized Connection getConnection() throws DataBaseOperateException{
	   if(initial){
	   return dbConnectionManager.getConnection();
	   }
       if(configPath==null){    	   
    	   throw new DataBaseOperateException(DataBaseOperateException.UNINITIAL_TO_CONFIGFILE);
       }else{
		   init(configPath);
		   return dbConnectionManager.getConnection();
   }
   }
   /**
    * ���� ���� ʵ�� 
    * @param con Ҫ���յ�ʵ��
    */
   public static synchronized void realse(Connection con){
	   
	   if(initial){
	   dbConnectionManager.freeConnection(con);
	   }
   }
   /***
    * �ر����ݿ�
    * ���ͷ� �������ӳ� ����ʵ��
    */
   public static synchronized void closeDataBase(){
	   if(initial){
	   dbConnectionManager.release();
	   initial=false;
	   }
   }
}
