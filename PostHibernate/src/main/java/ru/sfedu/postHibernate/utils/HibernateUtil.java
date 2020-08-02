package ru.sfedu.postHibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.postHibernate.models.lab2.ClientLab;
import ru.sfedu.postHibernate.models.lab4.ClientInfo;
import ru.sfedu.postHibernate.models.lab4.Phone;
import ru.sfedu.postHibernate.models.lab5.*;

import java.io.File;

import static ru.sfedu.postHibernate.Constants.HB_KEY;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * Создание фабрики
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

            // util for lab2
            metadataSources.addAnnotatedClass(ClientLab.class);// Аннотированная сущность

            //utils for lab4
            metadataSources.addAnnotatedClass(ClientInfo.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(ru.sfedu.postHibernate.models.lab4.Document.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(Phone.class);// Аннотированная сущность

            //utils for lab5
            metadataSources.addAnnotatedClass(Envelope.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(Letter.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(Client.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(Document.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(Company.class);// Аннотированная сущность


            metadataSources.addResource("queries.hibernate.xml");// Именованные запросы

            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }
}
