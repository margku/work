package ru.sfedu.postHibernate.provider.iDataProvider;
import ru.sfedu.postHibernate.Result;

import java.util.List;

public interface IDataProviderHibernate {


    //------------------------------GET METHODS-----------------------

    public Object getMoney(long id);
    public Object getLetter(long id);
    public Object getValPack(long id);


    //------------------------------DELETE METHODS-----------------------

    /**
     *
     * @param id
     * @return
     */
    public Result delMoney(long id);


    /**
     *
     * @param id
     * @return
     */
    public Result delLetter(long id);


    /**
     *
     * @param id
     * @return
     */
    public Result delValPack(long id);



    //------------------------------ADD METHODS-----------------------


    /**
     *
     * @param sender
     * @param recipient
     * @param addressFrom
     * @param postOfficeFrom
     * @param addressTo
     * @param postOfficeTo
     * @param price
     * @param date
     * @param sum
     * @param currency
     * @return
     */
    public Result addMoney(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency);

    /**
     *
     * @param sender
     * @param recipient
     * @param addressFrom
     * @param postOfficeFrom
     * @param addressTo
     * @param postOfficeTo
     * @param price
     * @param date
     * @param weight
     * @param mark
     * @param markCount
     * @param envelope
     * @return
     */
    public Result addLetter(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope);


    /**
     *
     * @param sender
     * @param recipient
     * @param addressFrom
     * @param postOfficeFrom
     * @param addressTo
     * @param postOfficeTo
     * @param price
     * @param date
     * @param size
     * @param weight
     * @param fragile
     * @param declaredValue
     * @return
     */
    public Result addValuablePack(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue);

    //------------------------------UPD METHODS-----------------------


    /**
     *
     * @param id
     * @param sender
     * @param recipient
     * @param addressFrom
     * @param postOfficeFrom
     * @param addressTo
     * @param postOfficeTo
     * @param price
     * @param date
     * @param size
     * @param weight
     * @param fragile
     * @param declaredValue
     * @return
     */
    public Result updValuablePack(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue);

    public Result updMoney(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency);

    public Result updLetter(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope);

    public List<?> getListOfCreatedTables();





}
