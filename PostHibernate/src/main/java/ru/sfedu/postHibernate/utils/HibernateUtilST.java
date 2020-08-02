package ru.sfedu.postHibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

import static ru.sfedu.postHibernate.Constants.HB_KEY;

public class HibernateUtilST {
    private static SessionFactory sessionFactory;
    /**
     * Создание фабрики
     *
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration;
            if (System.getProperty(HB_KEY) != null) {
                configuration = new Configuration().configure(new File(System.getProperty(HB_KEY)));
            } else {
                configuration = new Configuration().configure();
            }
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();


            MetadataSources metadataSources =
                    new MetadataSources(serviceRegistry);


            metadataSources.addAnnotatedClass(ru.sfedu.postHibernate.models.singleTable.Letter.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(ru.sfedu.postHibernate.models.singleTable.Package.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(ru.sfedu.postHibernate.models.singleTable.Money.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(ru.sfedu.postHibernate.models.singleTable.ValuablePack.class);// Аннотированная сущность

            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }
}
