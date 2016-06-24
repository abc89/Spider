package com.myweb.db;

/**
 * mysql 语句生成工具类
 * 不好用
 * @author e7691
 *
 */
public  class MySqlSentence {
	/*
	 * 注意空格!!!!!!!!!!!!!!
	 * 两两直接要加空格
	 * 此处JDBC驱动 表项 统一不可加单引号“'”？ 可加可不加分好“;”
	 * 表项 [id]加方括号错误
	 */
	/**
	 * 
	 * @param auto 键值自动 加一
	 * @param tableName 表名
	 * @param keys 键名称数组
	 * @param params 值数组
	 * @return
	 */
	public static String getInsert(boolean auto,String tableName,String[] keys,Object...params){
		String insertSentence=null;
		if(auto){
			System.out.println("键值自动 加一");
		 insertSentence="insert into "+tableName+"(";
		//当主键与其他键 值加入时，可直接赋值，不用在语句上说明键项
		for(int i=1;i<keys.length;i++){
			if(i!=(keys.length-1)){
				insertSentence=insertSentence+keys[i]+",";
			}else{
				insertSentence=insertSentence+keys[i]+")values('";
			}
		}
		for(int i=1;i<params.length;i++){
			if(i!=(params.length-1)){
			if(params[i] instanceof String||params[i] instanceof Integer){
				insertSentence=insertSentence+params[i]+"','";
			}else{
				insertSentence=insertSentence+null+"','";
			}
			}else if(params[i] instanceof String||params[i] instanceof Integer){
				insertSentence=insertSentence+params[i]+"')";
			}
		}}else{
			System.out.println("键值非自动 加一");
			insertSentence="insert into "+tableName+" values('";
			for(int i=0;i<params.length;i++){
				if(i!=(params.length-1)){
				if(params[i] instanceof String||params[i] instanceof Integer){
					insertSentence=insertSentence+params[i]+"','";
				}else{
					insertSentence=insertSentence+null+"','";
				}
				}else if(params[i] instanceof String||params[i] instanceof Integer){
					insertSentence=insertSentence+params[i]+"')";
				}
			}
		}
		
		System.out.println(insertSentence);
		return insertSentence;

	}
	/**
	 * 获取 整个表 语句
	 * @param tableName 表名
	 * @return 查询语句
	 */
	public static String getQuery(String tableName){
		if(tableName!=null){
		return "select * from "+tableName;
		}
		return tableName;
	}
	/**
	 * 
	 * @param tableName 表名
	 * @param key 键名
	 * @param value 值
	 * @return 语句
	 */
	public static String getSearch(String tableName,String key,String value){
		if(tableName!=null){
		return "select * from "+tableName+" where "+key+"='"+value+"'";
	}
	return null;
	}
	/**
	 * 删除整个表
	 * @param tableName 表名
	 * @return 语句
	 */
	public static String getClearAll(String tableName){
		return "delete from "+tableName;
		
	}
	/**
	 * create a new table
	 * @param tableName 表名
	 * @return 语句
	 */
			public static String getCreateTable(String tableName){
				return "CREATE TABLE "+tableName;
				
			}
			
			 /**
			  * 
			  * @param tableName
			  * @param key
			  * @param value
			  * @return
			  */
			public static String getDeleteTable(String tableName,String key,Object value){
				return "DELETE FROM "+tableName+" WHERE "+key+"='"+value+"'";
				
			}
/**
 * 
 * @param tableName
 * @return
 */
		public static String getDropTable(String tableName){
			return "drop table "+tableName;
			
		}
		/**
		 * 单条数据更新
		 * @param tableName
		 * @param whereKey
		 * @param keyvalue
		 * @param upKey
		 * @param upValue
		 * @return
		 */
		public static String getUpdateTable(String tableName,String whereKey,String keyvalue,String upKey,String upValue){
			return "update "+tableName+" set "+upKey+"="+upValue+" where "+whereKey+"='"+upKey+"'";
		}
		/**
		 * 多条件更新
		 * @param tableName
		 * @param keys
		 * @param params
		 * @return
		 */
		public static String getUpdateTable(String tableName,String[] keys,Object...params){
			String updateSentence=null;
			String key=keys[0];
		    String keyValue=params[0].toString();
			updateSentence="update "+tableName+" set";
				//当主键与其他键 值加入时，可直接赋值，不用在语句上说明键项
				for(int i=1;i<keys.length;i++){
					updateSentence=updateSentence+" "+keys[i]+"='";
					if(i!=(keys.length-1)){
					if(params[i] instanceof String||params[i] instanceof Integer){
						updateSentence=updateSentence+params[i]+"',";
					}else{
						updateSentence=updateSentence+null+"',";
					}
					
				}else{
					if(params[i] instanceof String||params[i] instanceof Integer){
						updateSentence=updateSentence+params[i]+"' where "+key+"='"+keyValue+"'";
					}else{
						updateSentence=updateSentence+null+"' where "+key+"='"+keyValue+"'";
					}
				}
		}
			
			return updateSentence;
		}
}
