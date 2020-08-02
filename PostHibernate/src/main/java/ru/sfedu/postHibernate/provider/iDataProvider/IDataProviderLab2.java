package ru.sfedu.postHibernate.provider.iDataProvider;

import ru.sfedu.postHibernate.Result;
import ru.sfedu.postHibernate.models.lab2.ClientLab;

import java.util.List;


public interface IDataProviderLab2 {

    public Result addClientLab(String name);

    public Result delClientLab(long id);

    public ClientLab getClientLab(Long id);

    public Result updClientLab(long id, String name, String producer);
    public List<ClientLab> getClientByName(String name);
    public List<?> getListOfCreatedTables();
}
