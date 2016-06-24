package com.myweb.db;

import java.io.File;
import java.sql.Connection;

import com.databases.DBConnectionManager;

/**
 * 数据库操作
 * @author e7691
 *
 */
public class DataBaseOperate {
	//数据库连接池 管理类
   private static DBConnectionManager  dbConnectionManager=DBConnectionManager.getInstance();
   private static boolean initial=false;
   private static String configPath="./source/db/ds_Config.xml";//默认初始化 配置文件路径
   /***
    * 
    * @param path 数据库配置文件路径及文件
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
    * @return 数据库链接实例
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
    * 回收 链接 实例 
    * @param con 要回收的实例
    */
   public static synchronized void realse(Connection con){
	   
	   if(initial){
	   dbConnectionManager.freeConnection(con);
	   }
   }
   /***
    * 关闭数据库
    * 即释放 所有连接池 链接实例
    */
   public static synchronized void closeDataBase(){
	   if(initial){
	   dbConnectionManager.release();
	   initial=false;
	   }
   }
}
