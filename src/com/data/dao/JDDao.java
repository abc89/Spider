package com.data.dao;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.databases.DBConnectionManager;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;
import com.shop.bean.JDBean;
public class JDDao{
	  private String TABLENAME="JDBean";
	     private String ID="id";
	     private String type="type";
	     private String price="price";
	     private String title="title";
	     private String imgUrl="imgUrl";
	     private String goodCount="goodCount";
	     private String itemID="itemID";
	 	public void add(JDBean bean) {
	 		 Session s = null;  
	         Transaction ts = null;  
	   
	         try {  
	             s = Hibernateutils.getSession();  
	             ts = s.beginTransaction();  
	             s.save(bean);  
	             ts.commit();  
	         } catch (HibernateException e) {  
	             if (ts != null)  
	                 ts.rollback();  
	             throw e;  
	         } finally {  
	             if (s != null)  
	                 s.close();  
	         }  
	 	}

	 
	 	// 删除
	 	public void Delete(String where,String value) {
	 		String sql = "delete "+TABLENAME+" where ";
	 		sql +=where+"="+value;
	 		Statement stat = null;
	 		ResultSet rs = null;
	 		Connection conn = null;
	 		try {
	 			conn = DataBaseOperate.getConnection();
	 			stat = conn.createStatement();
	 			stat.executeUpdate(sql);
	 		} catch (SQLException | DataBaseOperateException e) {
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

        /**
         * hibernate 查询商品
         * @param search 商品类型
         * @return 对应商品类型 列表
         */
		public List<JDBean> getItemType(String search) {
			 List<JDBean> beans=new ArrayList<JDBean>();
			 Session s = null;  
	         try {  
			 s = Hibernateutils.getSession();  
             Query query = s.createQuery("from com.shop.bean.JDBean jd where jd.shopType='"+search+"'");
             
              beans = query.list();
             //迭代器去迭代.
             for(Iterator iter=beans.iterator();iter.hasNext();)
             {
                JDBean user =(JDBean)iter.next();
                System.out.println("id="+user.getId() + "name="+user.getTitle());
             }
             return beans;
	         } catch (HibernateException e) {  
	               
	             throw e;  
	         } finally {  
	             if (s != null)  
	                 s.close();  
	         }  
		}

		protected JDBean builderBean(ResultSet rs) {
		
			JDBean bean=new JDBean();
			try {
				bean.setItemID(rs.getString(itemID));
				bean.setImgUrl(rs.getString(imgUrl));
				bean.setGoodCount(rs.getString(goodCount));
				bean.setPrice(rs.getString(price));
				bean.setTitle(rs.getString(title));
				bean.setShopType(rs.getString(type));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bean;
		}



}
