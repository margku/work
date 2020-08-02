package ru.sfedu.postHibernate.provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sfedu.postHibernate.Result;
import ru.sfedu.postHibernate.models.mappedSuperclass.*;
import ru.sfedu.postHibernate.provider.iDataProvider.IDataProviderHibernate;
import ru.sfedu.postHibernate.utils.HibernateUtilMS;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

import static ru.sfedu.postHibernate.Constants.*;
import static ru.sfedu.postHibernate.Msg.*;

public class DataProviderMappedSuperclass implements IDataProviderHibernate {
    private static Logger log = LogManager.getLogger(DataProviderMappedSuperclass.class);

    @Override
    public Result addMoney(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency) {
        try {
            Money money = new Money(sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, sum, currency);
            Session session = HibernateUtilMS.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(money);
            tx1.commit();
            session.close();
            return new Result(COMPLETE, money.getId());
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    @Override
    public Result addLetter(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope) {
        try {
            Letter letter = new Letter(sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, weight, mark, markCount, envelope);
            Session session = HibernateUtilMS.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(letter);
            tx1.commit();
            session.close();
            return new Result(COMPLETE, letter.getId());
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    @Override
    public Result addValuablePack(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        try {
            ValuablePack aPackage = new ValuablePack(sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile, declaredValue);
            Session session = HibernateUtilMS.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(aPackage);
            tx1.commit();
            session.close();
            return new Result(COMPLETE, aPackage.getId());
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    @Override
    public Money getMoney(long id) {
        return HibernateUtilMS.getSessionFactory().openSession().get(Money.class, id);

    }

    @Override
    public ValuablePack getValPack(long id) {
        return HibernateUtilMS.getSessionFactory().openSession().get(ValuablePack.class, id);
    }

    @Override
    public Letter getLetter(long id) {
        return HibernateUtilMS.getSessionFactory().openSession().get(Letter.class, id);
    }

    @Override
    public Result delMoney(long id) {
        try {
            Session session = HibernateUtilMS.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            Money money = getMoney(id);
            if (money != null) {
                session.delete(money);
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
    public Result delLetter(long id) {
        try {
            Session session = HibernateUtilMS.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            Letter letter = getLetter(id);
            if (letter != null) {
                session.delete(letter);
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
    public Result delValPack(long id) {
        try {
            Session session = HibernateUtilMS.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            ValuablePack valuablePack = getValPack(id);
            if (valuablePack != null) {
                session.delete(valuablePack);
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
    public Result updMoney(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency) {
        try {
            Money money = new Money(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, sum, currency);
            Session session = HibernateUtilMS.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            if (getMoney(id) != null) {
                session.update(money);
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
    public Result updValuablePack(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        try {
            ValuablePack valuablePack = new ValuablePack(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile, declaredValue);
            Session session = HibernateUtilMS.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            if (getValPack(id) != null) {
                session.update(valuablePack);
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
    public Result updLetter(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope) {
        try {
            Letter letter = new Letter(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, weight, mark, markCount, envelope);
            Session session = HibernateUtilMS.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            if (getLetter(id) != null) {
                session.update(letter);
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
    public List<?> getListOfCreatedTables() {
        Session session = HibernateUtilMS.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.getNamedNativeQuery(HSQLDB_CATALOG_MAPPED);
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