/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apui;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Moody
 */
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory;
static {
try {
Configuration config = new Configuration().configure();
ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
MetadataSources s = new MetadataSources( servReg );
s.addAnnotatedClass(User.class);
s.addAnnotatedClass(Snacks.class);
s.addAnnotatedClass(Moive.class);
s.addAnnotatedClass(seat.class);
// Add as much as the number of tables you have in your database ( we only have Student class in this tutorial)
sessionFactory = s.buildMetadata().buildSessionFactory();
} catch (Throwable ex) {
// Log the exception.
System.err.println("Initial SessionFactory creation failed." + ex);
throw new ExceptionInInitializerError(ex);
}
}
public static SessionFactory getSessionFactory() {
return sessionFactory;
}
    
}
