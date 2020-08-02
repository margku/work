package ru.sfedu.postHibernate.provider.iDataProvider;
import ru.sfedu.postHibernate.Result;
import ru.sfedu.postHibernate.models.lab4.ClientInfo;

import java.util.List;

public interface IDataProviderLab4 {

    public Result addClient(String name, long number, long homenum, String homeAddr, String workAddr, String typeDoc, long docNum);

    public Result delClient(long id);

    public ClientInfo getClient(long id);

    public Result updClient(long id, String name, long number, long homenum, String homeAddr, String workAddr, String typeDoc, long docNum);

    public List<ClientInfo> getClientByName(String name);

    public List<?> getListOfCreatedTables();
}
