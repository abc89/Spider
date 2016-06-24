package com.data.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.data.JDBean;
import com.databases.DBConnectionManager;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;

public class RecDao {
	     private String TABLENAME="lovetable";
	     private String userID="userID";
	     private String love="love";
	    String splite="splite";
	
	 
	 	// 删除
	 	public void Delete(String where,String value) {
	 		String sql = "delete "+TABLENAME+" where ";
	 		sql +=where+"="+value;
	 		Statement stat = null;
	 		ResultSet rs = null;
	 		Connection conn = DBConnectionManager.getInstance().getConnection();
	 		try {
	 			stat = conn.createStatement();
	 			stat.executeUpdate(sql);
	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 		} finally {
	 			try {
	 				if (conn != null)
	 					DBConnectionManager.getInstance().freeConnection(conn);
	 				if (stat != null)
	 					stat.close();
	 				if (rs != null)
	 					rs.close();
	 			} catch (SQLException e) {
	 				e.printStackTrace();
	 			}
	 		}
	 	}


		

		public List<JDBean> getRecBeans(String id) {
			List<JDBean> beans=new ArrayList<JDBean>();
			String sql = "select * from "+TABLENAME+" where "+userID+"='" + id + "'";
			System.out.println("查询推荐"+sql);
			Statement stat = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				conn=DataBaseOperate.getConnection();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			};
			try {
				stat = conn.createStatement();
				rs = stat.executeQuery(sql);
				if (rs.next()) {
					beans=getLoveBeans(rs.getString(love));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
	                   conn.close();
					if (stat != null)
						stat.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return beans;
		}



		private List<JDBean> getLoveBeans(String loves) {
			List<JDBean> beans=new ArrayList<JDBean>();
			if(loves!=null&&loves.indexOf(splite)!=-1){
				String lv=loves.substring(0,loves.length()-splite.length());
				System.out.println(lv);
				if(lv.indexOf(splite)!=-1){
					String[] ls=lv.split(splite);
					for (String string : ls) {
					  List<JDBean> bs=new JDDao().getItemType(string);
					  if(bs.size()>2){
						  beans.add(bs.get(0));
						  beans.add(bs.get(1));
					  }else{
					  beans.addAll(bs);
					  }
					}
				}else{
					 List<JDBean> bs=new JDDao().getItemType(lv);
					  if(bs.size()>2){
						  beans.add(bs.get(0));
						  beans.add(bs.get(1));
					  }else{
					  beans.addAll(bs);
					  }
				}
			}
			System.out.println("获得推荐数目吗"+beans.size());
			return beans;
		}




		public void setLove(String id, String search) {
			String nlove=getLove(id);
			String newLove=null;
			if(nlove==null){
				newLove=search+splite;
			}else{
				newLove=nlove+search+splite;
			}
			String sql = "update "+TABLENAME+" set ";
			sql += love+"='" + newLove + "'";
			//sql += PASSWORD+"='" + cnbean.getPassword() + "',";
			sql += " where "+userID+"='" +id + "'";
			System.out.println(sql);
			Statement stat = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				Context initContext = new InitialContext();
				   DataSource ds  = (DataSource)initContext.lookup("java:/comp/env/jdbc/MySQLDS");
				//ds = (DataSource)envContext.lookup("jdbc/TestDB");
			conn = ds.getConnection();
				stat = conn.createStatement();
				stat.executeUpdate(sql);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
					//	DBConnectionManager.getInstance().freeConnection(conn);
					if (stat != null)
						stat.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}




		private String getLove(String id) {
			String nlove=null;
			String sql = "select * from "+TABLENAME+" where "+userID+"='" + id + "'";
			System.out.println("查询推荐"+sql);
			Statement stat = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				Context initContext = new InitialContext();
				   DataSource ds  = (DataSource)initContext.lookup("java:/comp/env/jdbc/MySQLDS");
				//ds = (DataSource)envContext.lookup("jdbc/TestDB");
			conn = ds.getConnection();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			};
			try {
				stat = conn.createStatement();
				rs = stat.executeQuery(sql);
				if (rs.next()) {
					System.out.println(rs.getString(this.love));
					nlove=rs.getString(love);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
	                   conn.close();
					if (stat != null)
						stat.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return nlove;
		}



}
