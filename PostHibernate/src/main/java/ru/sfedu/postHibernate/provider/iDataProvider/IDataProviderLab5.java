package ru.sfedu.postHibernate.provider.iDataProvider;

import ru.sfedu.postHibernate.Result;
import ru.sfedu.postHibernate.models.lab4.ClientInfo;
import ru.sfedu.postHibernate.models.lab5.Client;
import ru.sfedu.postHibernate.models.lab5.Company;
import ru.sfedu.postHibernate.models.lab5.Envelope;
import ru.sfedu.postHibernate.models.lab5.Letter;

import java.util.List;

public interface IDataProviderLab5 {

    public Result addLetter(long clientId, int weight, int markCount, long mark, int price, String size);

    public Result addClient(String name, long docnum, long company);

    public Client getClient(long id);

    public Envelope getEnvelope(long id);

    public Result addCompany(String name);

    public Company getCompany(long id);

    public Result delClient(long id);

    public Result delEnvelope(long id);

    public Result delCompany(long id);

    public Letter getLetter(long id);

    public List<?> getListOfCreatedTables();

    public List<Client> getClientByName(String name);
    public List<Company> getCompanyByName(String name);
}
