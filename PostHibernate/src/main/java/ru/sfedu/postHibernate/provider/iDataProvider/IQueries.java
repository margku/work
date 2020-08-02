package ru.sfedu.postHibernate.provider.iDataProvider;

import ru.sfedu.postHibernate.models.lab5.Client;

import java.util.List;
import java.util.Set;

public interface IQueries {
    public Set<Client> getClientByCompanyCriteria(long id);
    public Set<Client> getClientByCompanyQuery(long id);
    public Set<Client> getClientByCompanyHQL(long id);
}
