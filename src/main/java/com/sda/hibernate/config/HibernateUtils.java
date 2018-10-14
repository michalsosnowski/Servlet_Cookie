package com.sda.hibernate.config;

import com.sda.hibernate.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sf =
            new Configuration()
            .configure()
            .buildSessionFactory();

    private static Session session;

    public synchronized static Session getSession(){
        if(session == null){
            System.out.println("Otwieram sesje");
            session = sf.openSession();
        }else{
            System.out.println("Poloczenie powinno byc");
        }
        return session;
    }

    public static SessionFactory getSessionFactory(){
        if(sf == null){
            sf = (SessionFactory) new HibernateUtils();
        }
        return sf;
    }

    public static void closeConnection(){
        System.out.println("Zamykam polaczenie");
        session.close();
        sf.close();
        System.out.println(sf);
        session = null;
        sf = null;
    }

}
