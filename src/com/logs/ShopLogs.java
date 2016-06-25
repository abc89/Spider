package com.logs;

import org.apache.log4j.Logger;

public class ShopLogs {
  private static Logger logger;
  public static void warn(String name,String content){
	  logger=Logger.getLogger(name);
	  logger.warn(content);
  }
  public static void error(String name,String content){
	  logger=Logger.getLogger(name);
	  logger.warn(content);
  }
  public static void debug(String name,String content){
	  logger=Logger.getLogger(name);
	  logger.warn(content);
  }
}
