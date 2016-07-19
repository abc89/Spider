package com.data.dao;  
  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.cfg.Configuration;  
  
/**
 * hibernate  sessionfactory工厂获取
 * @author e7691
 *
 */
@SuppressWarnings("deprecation")  
public final class Hibernateutils {  
      
    private static SessionFactory sFactory;  
      
    private Hibernateutils(){}  
      
    static{  
        Configuration cfg = new Configuration();  
        cfg.configure();  
        sFactory = cfg.buildSessionFactory();  
    }  
  
    public static SessionFactory getSessionFactory() {  
        return sFactory;  
    }  
      
    public static Session getSession(){  
        return sFactory.openSession();  
    }  
}  