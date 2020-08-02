package ru.sfedu.postHibernate.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sfedu.postHibernate.Constants;
import ru.sfedu.postHibernate.Msg;
import ru.sfedu.postHibernate.Result;
import ru.sfedu.postHibernate.models.lab2.ClientLab;
import ru.sfedu.postHibernate.models.lab2.Washinmashine;
import ru.sfedu.postHibernate.models.lab5.Client;
import ru.sfedu.postHibernate.models.lab5.Company;
import ru.sfedu.postHibernate.provider.iDataProvider.IDataProviderLab2;
import ru.sfedu.postHibernate.utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.List;

import static ru.sfedu.postHibernate.Constants.*;

public class DataProviderLab2 implements IDataProviderLab2 {
    private static final Logger log = LogManager.getLogger(DataProviderLab5.class);

    public Result addClientLab(String name) {
        try {
            ClientLab client = new ClientLab(name);
            Washinmashine washinmashine1 = new Washinmashine(1, 100, "jjjj", "1type");
            Washinmashine washinmashine2 = new Washinmashine(2, 200, "uuuu", "2type");
            client.setMyFirstWashinmashine(washinmashine1);
            client.setMySecondWashinmashine(washinmashine2);
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(client);
            tx1.commit();
            session.close();
            return new Result(Msg.COMPLETE, client.getId());
        } catch (Exception e) {
            return new Result(Msg.NO);
        }
    }

    public Result delClientLab(long id) {
        try {
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx2 = session2.beginTransaction();
            session2.delete(getClientLab(id));
            tx2.commit();
            session2.close();
            return new Result(Msg.COMPLETE, IDP_COMPLETE);
        } catch (Exception e) {
            return new Result(Msg.NO);
        }
    }

    public ClientLab getClientLab(Long id) {
        return HibernateUtil.getSessionFactory().openSession().get(ClientLab.class, id);
    }
    @Override
    public List<ClientLab> getClientByName(String name) {
            Session session = HibernateUtil.getSessionFactory().openSession();try {
                org.hibernate.query.Query query = session.createQuery(CLIENT_LAB).setParameter(NAME, name);
                List<ClientLab> clients = query.getResultList();
                session.close();
                return clients;
            } catch (Exception exception) {
                log.error(exception);
                session.close();
                return Collections.emptyList();
            }
        }

    public Result updClientLab(long id, String name, String producer) {
        try {
            ClientLab clientLab = getClientLab(id);
            clientLab.setName(name);
            clientLab.getMyFirstWashinmashine().setProducer(producer);
            clientLab.getMySecondWashinmashine().setProducer(producer);
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(clientLab);
            tx1.commit();
            session.close();
            return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
        } catch (Exception e) {
            return new Result(Msg.NO);
        }
    }

    public List<?> getListOfCreatedTables() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.getNamedNativeQuery(HSQLDB_CATALOG_LAB2);
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
}
