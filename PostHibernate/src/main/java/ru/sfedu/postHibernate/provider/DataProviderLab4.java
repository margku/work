package ru.sfedu.postHibernate.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sfedu.postHibernate.Result;
import ru.sfedu.postHibernate.models.lab4.ClientInfo;
import ru.sfedu.postHibernate.models.lab4.Document;
import ru.sfedu.postHibernate.models.lab4.Phone;
import ru.sfedu.postHibernate.provider.iDataProvider.IDataProviderLab4;
import ru.sfedu.postHibernate.utils.HibernateUtil;


import javax.persistence.Query;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


import static ru.sfedu.postHibernate.Constants.*;
import static ru.sfedu.postHibernate.Msg.COMPLETE;
import static ru.sfedu.postHibernate.Msg.NO;

public class DataProviderLab4 implements IDataProviderLab4 {

    private static Logger log = LogManager.getLogger(DataProviderLab4.class);
@Override
    public Result addClient(String name, long number, long homenum, String homeAddr, String workAddr, String typeDoc, long docNum) {
        try {
            Map<String, Document> map = new HashMap<String , Document>();
            map.put(typeDoc, new Document(docNum));
            ClientInfo clientInfo = new ClientInfo(name);
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            clientInfo.getNumber().add(new Phone(homenum));
            clientInfo.getNumber().add(new Phone(number));
            clientInfo.getAddress().add(homeAddr);
            clientInfo.getAddress().add(workAddr);
            clientInfo.setDocument(map);
            session.save(clientInfo);
            tx1.commit();
            session.close();
            return new Result(COMPLETE, clientInfo.getId());
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }
@Override
    public Result delClient(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            ClientInfo clientInfo = getClient(id);
            if (clientInfo != null) {
                session.delete(clientInfo);
                tx1.commit();
                session.close();
                return new Result(COMPLETE, DEL_COMPLETE);
            } else {
                session.close();
                return new Result(NO, IDP_READ_ERROR);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }
@Override
    public ClientInfo getClient(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ClientInfo clientInfo = session.find(ClientInfo.class, id);
            session.getTransaction().commit();
            session.close();
            return clientInfo;
        } catch (Exception exception) {
            log.error(exception);
            session.close();
            return null;
        }
    }
    @Override
    public Result updClient(long id, String name, long number, long homenum, String homeAddr, String workAddr, String typeDoc, long docNum) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            if (getClient(id) != null) {
                Map<String, Document> map = new HashMap<String , Document>();
                map.put(typeDoc, new Document(docNum));
                ClientInfo clientInfo = new ClientInfo(id, name);
                clientInfo.getNumber().add(new Phone(homenum));
                clientInfo.getNumber().add(new Phone(number));
                clientInfo.getAddress().add(homeAddr);
                clientInfo.getAddress().add(workAddr);
                clientInfo.setDocument(map);
                session.update(clientInfo);
                transaction.commit();
                session.close();
                return new Result(COMPLETE, IDP_UPD_COMPLETE);
            } else {
                session.close();
                return new Result(NO, IDP_READ_ERROR);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }
    @Override
    public List<?> getListOfCreatedTables() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.getNamedNativeQuery(HSQLDB_CATALOG_LAB4);
            List<?> answer = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return answer;
        } catch (Exception e) {
            log.error(e);
            session.close();
            return Collections.emptyList();
        }
    }
    @Override
    public List<ClientInfo> getClientByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.query.Query query = session.createQuery(CLIENT4).setParameter(NAME, name);
            List<ClientInfo> clients = query.getResultList();
            session.close();
            return clients;
        } catch (Exception exception) {
            log.error(exception);
            session.close();
            return Collections.emptyList();
        }
    }
}
