package ru.sfedu.postHibernate.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sfedu.postHibernate.Msg;
import ru.sfedu.postHibernate.Result;
import ru.sfedu.postHibernate.models.lab4.ClientInfo;
import ru.sfedu.postHibernate.models.lab5.*;
import ru.sfedu.postHibernate.provider.iDataProvider.IDataProviderLab5;
import ru.sfedu.postHibernate.utils.HibernateUtil;

import javax.persistence.Column;
import javax.persistence.Query;

import java.util.Collections;
import java.util.List;

import static ru.sfedu.postHibernate.Constants.*;
import static ru.sfedu.postHibernate.Msg.COMPLETE;
import static ru.sfedu.postHibernate.Msg.NO;

public class DataProviderLab5 implements IDataProviderLab5 {
    private static final Logger log = LogManager.getLogger(DataProviderLab5.class);

    public Result addLetter(long clientId, int weight,  int markCount, long mark, int price, String size) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Client client = getClient(clientId);
            log.debug(client.toString());
            Letter letter = new Letter(weight, mark, markCount, client, new Envelope(size, price));
            client.getLetters().add(letter);
            session.beginTransaction();
            long id = (Long) session.save(letter);
            session.update(client);
            session.getTransaction().commit();
            session.close();
            return new Result(Msg.COMPLETE, id);
        } catch (Exception exception) {
            log.error(exception);
            session.close();
            return new Result(Msg.NO);
        }
    }

    public Result addClient(String name, long docnum, long company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Client client = new Client(name);
            client.setDocument(new Document(docnum));
            client.getCompanies().add(getCompany(company));
            Transaction tx1 = session.beginTransaction();
            session.save(client);
            tx1.commit();
            session.close();
            return new Result(COMPLETE, client.getId());
        } catch (Exception e) {
            log.error(e);
            session.close();
            return new Result(Msg.NO);
        }
    }
    public Client getClient(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Client client = session.find(Client.class, id);
            session.getTransaction().commit();
            session.close();
            return client;
        } catch (Exception exception) {
            log.error(exception);
            session.close();
            return null;
        }
    }
    public Envelope getEnvelope(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Envelope envelope = session.find(Envelope.class, id);
            session.getTransaction().commit();
            session.close();
            return envelope;
        } catch (Exception exception) {
            log.error(exception);
            session.close();
            return null;
        }
    }

    public Result addCompany(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Company company = new Company(name);
            Transaction tx1 = session.beginTransaction();
            session.save(company);
            tx1.commit();
            session.close();
            return new Result(COMPLETE, company.getId());
        } catch (Exception e) {
            log.error(e);
            session.close();
            return new Result(Msg.NO);
        }
    }

    public Company getCompany(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Company company = session.find(Company.class, id);
            session.getTransaction().commit();
            session.close();
            return company;
        } catch (Exception exception) {
            log.error(exception);
            session.close();
            return null;
        }
    }

    public Result delClient(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            Client client = getClient(id);
            if (client != null) {
                session.delete(client);
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
    public Result delEnvelope(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            Envelope envelope = getEnvelope(id);
            if (envelope != null) {
                session.delete(envelope);
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
    public Result delCompany(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            Company company = getCompany(id);
            if (company != null) {
                session.delete(company);
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
    public Letter getLetter(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Letter letter = session.find(Letter.class, id);
            session.close();
            return letter;
        } catch (Exception exception) {
            log.error(exception);
            session.close();
            return null;
        }
    }
    @Override
    public List<Client> getClientByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.query.Query query = session.createQuery(CLIENT5).setParameter(NAME, name);
            List<Client> clients = query.getResultList();
            session.close();
            return clients;
        } catch (Exception exception) {
            log.error(exception);
            session.close();
            return Collections.emptyList();
        }
    }
@Override
    public List<Company> getCompanyByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.query.Query query = session.createQuery(COMPANY).setParameter(NAME, name);
            List<Company> companies = query.getResultList();
            session.close();
            return companies;
        } catch (Exception exception) {
            log.error(exception);
            session.close();
            return Collections.emptyList();
        }
    }

    public List<?> getListOfCreatedTables() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.getNamedNativeQuery(HSQLDB_CATALOG_LAB5);
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
