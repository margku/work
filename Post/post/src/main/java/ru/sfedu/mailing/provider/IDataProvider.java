package ru.sfedu.mailing.provider;

import ru.sfedu.mailing.models.Package;
import ru.sfedu.mailing.Result;
import ru.sfedu.mailing.models.*;


public interface IDataProvider {
    //------------------------------GET METHODS-----------------------

    /**
     *
     * @param id
     * @return
     */
    public Money getMoney(long id);

    /**
     *
     * @param id
     * @return
     */
    public Package getPackage(long id);

    /**
     *
     * @param id
     * @return
     */
    public Letter getLetter(long id);

    /**
     *
     * @param id
     * @return
     */
    public Client getClient(long id);

    /**
     *
     * @param id
     * @return
     */
    public Mark getMark(long id);

    /**
     *
     * @param id
     * @return
     */
    public Envelope getEnvelope(long id);

    /**
     *
     * @param id
     * @return
     */
    public ValuablePack getValuablePack(long id);

    /**
     *
     * @param id
     * @return
     */
    public PostOffice getPostOffice(long id);

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
    public Result delPackage(long id);

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
    public Result delClient(long id);

    /**
     *
     * @param id
     * @return
     */
    public Result delMark(long id);

    /**
     *
     * @param id
     * @return
     */
    public Result delEnvelope(long id);

    /**
     *
     * @param id
     * @return
     */
    public Result delValuablePack(long id);

    /**
     *
     * @param id
     * @return
     */
    public Result delPostOffice(long id);

    //------------------------------ADD METHODS-----------------------

    /**
     *
     * @param id
     * @param name
     * @return
     */
    public Result addClient(long id, String name);

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
     * @param sum
     * @param currency
     * @return
     */
    public Result addMoney(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency);

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
     * @param weight
     * @param mark
     * @param markCount
     * @param envelope
     * @return
     */
    public Result addLetter(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope);

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
     * @return
     */
    public Result addPackage(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile);

    /**
     *
     * @param id
     * @param size
     * @param price
     * @return
     */
    public Result addEnvelope(long id, String size, int price);

    /**
     *
     * @param id
     * @param name
     * @param price
     * @return
     */
    public Result addMark(long id, String name, int price);

    /**
     *
     * @param officeId
     * @param country
     * @param city
     * @param address
     * @return
     */
    public Result addPostOffice(long officeId, String country, String city, String address);

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
    public Result addValuablePack(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue);

    //------------------------------UPD METHODS-----------------------

    /**
     *
     * @param id
     * @param name
     * @param price
     * @return
     */
    public Result updMark(long id, String name, int price);

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
     * @return
     */
    public Result updPackage(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile);

    /**
     *
     * @param officeId
     * @param country
     * @param city
     * @param address
     * @return
     */
    public Result updPostOffice(long officeId, String country, String city, String address);

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
     * @param sum
     * @param currency
     * @return
     */
    public Result updMoney(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency);

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
     * @param weight
     * @param mark
     * @param markCount
     * @param envelope
     * @return
     */
    public Result updLetter(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope);

    /**
     *
     * @param id
     * @param name
     * @return
     */
    public Result updClient(long id, String name);

    /**
     *
     * @param id
     * @param size
     * @param price
     * @return
     */
    public Result updEnvelope(long id, String size, int price);

    // USE CASE methods

    /**
     *
     * @param id
     * @return
     */
    public Result getClientHistory(long id);

    /**
     *
     * @param id
     * @return
     */
    public Result giveLetter(long id);

    /**
     *
     * @param id
     * @return
     */
    public Result givePackage(long id);

    /**
     *
     * @param id
     * @return
     */
    public Result giveMoney(long id);

    /**
     *
     * @param id
     * @return
     */
    public Result giveValuablePack(long id);


}
