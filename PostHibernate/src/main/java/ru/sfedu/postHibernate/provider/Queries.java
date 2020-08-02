package ru.sfedu.postHibernate.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ru.sfedu.postHibernate.Constants;
import ru.sfedu.postHibernate.Main;
import ru.sfedu.postHibernate.models.lab5.Client;
import ru.sfedu.postHibernate.models.lab5.Company;
import ru.sfedu.postHibernate.provider.iDataProvider.IQueries;
import ru.sfedu.postHibernate.utils.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

import static ru.sfedu.postHibernate.Constants.*;

public class Queries implements IQueries {
    private static Logger log = LogManager.getLogger(Queries.class);
    @Override
    public Set<Client> getClientByCompanyQuery(long id)  {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(QUERY, Company.class).setParameter(ID, id);
            Company company = (Company) query.getSingleResult();
            session.close();
           return company.getClients();
        } catch (Exception e) {
            log.error(e);
        }
        return Collections.emptySet();
    }


    @Override
    public Set<Client> getClientByCompanyCriteria(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
            Root<Company> root = criteriaQuery.from(Company.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(ID), id));
            Company company = session.createQuery(criteriaQuery).getSingleResult();
            session.close();
            return company.getClients();
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public Set<Client> getClientByCompanyHQL(long id)  {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(HQL).setParameter(ID, id);
            Company company = (Company) query.getSingleResult();
            session.close();
            return company.getClients();
        } catch (Exception e) {
            log.error(e);
        }
        return Collections.emptySet();
    }
}
