package ru.sfedu.mailing.provider;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mailing.Constants;
import ru.sfedu.mailing.Msg;
import ru.sfedu.mailing.Result;
import ru.sfedu.mailing.models.*;
import ru.sfedu.mailing.models.Package;

import java.sql.Connection;
import java.sql.*;
import java.io.IOException;

import static ru.sfedu.mailing.utils.ConfigurationUtil.getConfigurationEntry;
import static ru.sfedu.mailing.Constants.*;
import static ru.sfedu.mailing.Msg.*;
import static ru.sfedu.mailing.Msg.COMPLETE;


public class DataProviderJdbc implements IDataProvider {

    private static Logger log = LogManager.getLogger(DataProviderJdbc.class);
    private Connection connection;

    /**
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */

    private Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName(getConfigurationEntry(JDBC_DRIVER));
        connection = DriverManager.getConnection(
                getConfigurationEntry(DB_CONNECT),
                getConfigurationEntry(DB_USER),
                getConfigurationEntry(DB_PASS)
        );
        return connection;
    }

    /**
     * @param sql
     * @return
     */

    public Result execute(String sql) {
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(sql);
            connection.close();
            return new Result(COMPLETE);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            log.error(e);
            return new Result(NO);
        }
    }

    /**
     * @param sql
     * @return
     */

    private ResultSet select(String sql) {
        try {
            Statement statement = getConnection().createStatement();
            ResultSet set = statement.executeQuery(sql);
            connection.close();
            return set;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @return
     */
    private Result checkClient() {
        int count = 0;
        try {
            ResultSet res = select(String.format(COUNT_CLIENT));
            if (res != null && res.next()) {
                count = res.getInt(S);
                if (count == 0) {
                    return addClient(DEF_ID, DEF_NAME);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @return
     */
    private Result checkMark() {
        int count = 0;
        try {
            ResultSet res = select(String.format(COUNT_MARK));
            if (res != null && res.next()) {
                count = res.getInt(S);
                if (count == 0) {
                    return addMark(DEF_ID, DEF_NAME, DEF_PRICE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @return
     */
    private Result checkEnvelope() {
        int count = 0;
        try {
            ResultSet res = select(String.format(COUNT_ENV));
            if (res != null && res.next()) {
                count = res.getInt(S);
                if (count == 0) {
                    return addEnvelope(DEF_ID, DEF_SIZE, DEF_PRICE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @return
     */
    private Result checkPostOffice() {
        int count = 0;
        try {
            ResultSet res = select(String.format(COUNT_POST_OF));
            if (res != null && res.next()) {
                count = res.getInt(S);
                if (count == 0) {
                    return addPostOffice(DEF_ID, DEF_COUNTRY, DEF_CITY, DEF_ADDRESS);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @param officeId
     * @param country
     * @param city
     * @param address
     * @return
     */
    public Result addPostOffice(long officeId, String country, String city, String address) {
        PostOffice postOffice = new PostOffice(officeId, country, city, address);
        try {
            boolean check = getPostOffice(postOffice.getOfficeId()) == null;
            if (check) {
                boolean d = execute(String.format(INSERT_POST_OFFICE, officeId, postOffice.getCountry(), postOffice.getCity(), postOffice.getAddress())).getStatus() == COMPLETE;
                if (d) {
                    return new Result(COMPLETE, IDP_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @param id
     * @param name
     * @return
     */
    public Result addClient(long id, String name) {
        try {
            if (getClient(id) == null) {
                boolean d = execute(String.format(INSERT_CLIENT, id, name)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(COMPLETE, IDP_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @param id
     * @param size
     * @param price
     * @return
     */
    public Result addEnvelope(long id, String size, int price) {
        try {
            boolean check = getEnvelope(id) == null;
            if (check) {
                boolean d = execute(String.format(INSERT_ENVELOPE, id, size, price)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(COMPLETE, IDP_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @param id
     * @param name
     * @param price
     * @return
     */
    public Result addMark(long id, String name, int price) {
        try {
            boolean check = getMark((id)) == null;
            if (check) {
                boolean d = execute(String.format(INSERT_MARK, id, name, price)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(COMPLETE, IDP_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
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
    public Result addPackage(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile) {
        checkClient();
        checkPostOffice();
        try {
            boolean check = ((getClient(sender) != null)
                    && (getClient(recipient) != null)
                    && (getPostOffice(postOfficeFrom) != null)
                    && (getPostOffice(postOfficeTo) != null));
            if (check) {
                boolean d = execute(String.format(INSERT_PACKAGE,
                        id, sender, recipient, addressFrom, postOfficeFrom,
                        addressTo, postOfficeTo, price, date,
                        size, weight, fragile)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
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


    public Result addValuablePack(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        checkClient();
        checkPostOffice();
        try {
            boolean check = ((getClient(sender) != null)
                    && (getClient(recipient) != null)
                    && (getPostOffice(postOfficeFrom) != null)
                    && (getPostOffice(postOfficeTo) != null));
            if (check) {
                boolean d = execute(String.format(INSERT_VALUABLE_PACK,
                        id, sender, recipient, addressFrom, postOfficeFrom,
                        addressTo, postOfficeTo, price, date,
                        size, weight, fragile, declaredValue)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
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
    public Result addMoney(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency) {
        checkClient();
        checkPostOffice();
        try {
            boolean check = ((getClient(sender) != null)
                    && (getClient(recipient) != null)
                    && (getPostOffice(postOfficeFrom) != null)
                    && (getPostOffice(postOfficeTo) != null));
            if (check) {
                boolean d = (execute(String.format(INSERT_MONEY,
                        id, sender, recipient, addressFrom, postOfficeFrom,
                        addressTo, postOfficeTo, price, date,
                        sum, currency)).getStatus() == COMPLETE);
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
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
    public Result addLetter(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope) {
        checkClient();
        checkEnvelope();
        checkMark();
        checkPostOffice();
        try {
            boolean check = ((getClient(sender) != null)
                    && (getClient(recipient) != null)
                    && (getEnvelope(envelope) != null)
                    && (getMark(mark) != null)
                    && (getPostOffice(postOfficeFrom) != null)
                    && (getPostOffice(postOfficeTo) != null));
            if (check) {
                boolean d = (execute(String.format(INSERT_LETTER,
                        id, sender, recipient, addressFrom, postOfficeFrom,
                        addressTo, postOfficeTo, price, date,
                        weight, mark, markCount, envelope)).getStatus() == COMPLETE);
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Client getClient(long id) {
        try {
            ResultSet res = select(String.format(GET_CLIENT, id));
            if (res != null && res.next()) {
                Client client = new Client(res.getInt(CLIENT_ID), res.getString(CLIENT_NAME));
                return client;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public PostOffice getPostOffice(long id) {
        try {
            ResultSet res = select(String.format(GET_POST_OFFICE, id));
            if (res != null && res.next()) {
                PostOffice postOffice = new PostOffice(res.getInt(OFFICE_ID), res.getString(OFFICE_COUNTRY), res.getString(OFFICE_CITY), res.getString(OFFICE_ADDRESS));
                return postOffice;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public Envelope getEnvelope(long id) {
        try {
            ResultSet res = select(String.format(GET_ENVELOPE, id));
            if (res != null && res.next()) {
                Envelope envelope = new Envelope(res.getLong(ENVELOPE_ID), res.getString(SIZE), res.getInt(EVELOPE_PRICE));
                return envelope;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public Mark getMark(long id) {
        try {
            ResultSet res = select(String.format(GET_MARK, id));
            if (res != null && res.next()) {
                Mark mark = new Mark(res.getLong(MARK_ID), res.getString(MARK_NAME), res.getInt(MARK_PRICE));
                return mark;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public Money getMoney(long id) {
        try {
            ResultSet res = select(String.format(GET_MONEY, id));
            if (res != null && res.next()) {
                Money money = new Money(res.getLong(ID_POST_OBJ), getClient(res.getLong(SENDER)),
                        getClient(res.getLong(RECIPIENT)), res.getString(ADDRESS_FROM),
                        getPostOffice(res.getLong(POST_OFFICE_FROM)), res.getString(ADDRESS_TO),
                        getPostOffice(res.getLong(POST_OFFICE_TO)), res.getInt(PRICE), res.getString(DATE), res.getBoolean(RECEIVED),
                        res.getInt(SUM), res.getString(CURRENCY));
                return money;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public Letter getLetter(long id) {
        try {
            ResultSet res = select(String.format(GET_LETTER, id));
            if (res != null && res.next()) {
                Letter letter = new Letter(res.getLong(ID_POST_OBJ), getClient(res.getLong(SENDER)), getClient(res.getLong(RECIPIENT)), res.getString(ADDRESS_FROM),
                        getPostOffice(res.getLong(POST_OFFICE_FROM)), res.getString(ADDRESS_TO), getPostOffice(res.getLong(POST_OFFICE_TO)), res.getInt(PRICE), res.getString(DATE), res.getBoolean(RECEIVED),
                        res.getInt(WEIGHT), getMark(res.getLong(Constants.MARK)), res.getInt(MARK_COUNT), getEnvelope(res.getLong(ID_ENVELOPE)));
                return letter;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public Package getPackage(long id) {
        try {
            ResultSet res = select(String.format(GET_PACKAGE, id));

            if (res != null && res.next()) {
                Package pack = new Package(res.getLong(ID_POST_OBJ), getClient(res.getLong(SENDER)), getClient(res.getLong(RECIPIENT)), res.getString(ADDRESS_FROM),
                        getPostOffice(res.getLong(POST_OFFICE_FROM)), res.getString(ADDRESS_TO), getPostOffice(res.getLong(POST_OFFICE_TO)), res.getInt(PRICE), res.getString(DATE), res.getBoolean(RECEIVED),
                        res.getInt(SIZE), res.getInt(WEIGHT), res.getBoolean(FRAGILE));
                return pack;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public ValuablePack getValuablePack(long id) {
        try {
            ResultSet res = select(String.format(GET_VALUABLE_PACK, id));
            if (res != null && res.next()) {
                ValuablePack pack = new ValuablePack(res.getLong(ID_POST_OBJ), getClient(res.getLong(SENDER)), getClient(res.getLong(RECIPIENT)), res.getString(ADDRESS_FROM),
                        getPostOffice(res.getLong(POST_OFFICE_FROM)), res.getString(ADDRESS_TO), getPostOffice(res.getLong(POST_OFFICE_TO)), res.getInt(PRICE), res.getString(DATE), res.getBoolean(RECEIVED),
                        res.getInt(SIZE), res.getInt(WEIGHT), res.getBoolean(FRAGILE), res.getLong(DECLARED_VALUE));
                return pack;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public Result delClient(long id) {
        int count = 0;
        try {
            if (getClient(id) != null) {
                ResultSet res = select(String.format(DEL_CLIENT_CHECK, id, id, id, id, id, id, id, id));
                if (res != null && res.next()) {
                    count = res.getInt(S);
                }
                if (count != 0) return new Result(NO, Constants.DEL_FAIL);
                boolean a = execute(String.format(DEL_CLIENT, id)).getStatus() == COMPLETE;
                if (a == true) return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @return
     */
    public Result delPostOffice(long id) {
        int count = 0;
        try {
            if (getPostOffice(id) != null) {
                ResultSet res = select(String.format(DEL_OFFICE_CHECK, id, id, id, id, id, id, id, id));
                if (res != null && res.next()) {
                    count = res.getInt(S);
                }
                if (count != 0) return new Result(NO, Constants.DEL_FAIL);
                boolean a = execute(String.format(DEL_POST_OFFICE, id)).getStatus() == COMPLETE;
                if (a == true)
                    return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @return
     */
    public Result delEnvelope(long id) {
        int count = 0;
        try {
            if (getEnvelope(id) != null) {
                ResultSet res = select(String.format(DEL_ENVELOPE_CHECK, id));
                if (res != null && res.next()) {
                    count = res.getInt(S);
                }
                if (count != 0) return new Result(NO, Constants.DEL_FAIL);
                boolean a = execute(String.format(DEL_ENVELOPE, id)).getStatus() == COMPLETE;
                if (a == true) return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @return
     */
    public Result delMark(long id) {
        int count = 0;
        try {
            if (getMark(id) != null) {
                ResultSet res = select(String.format(DEL_MARK_CHECK, id));
                if (res != null && res.next()) {
                    count = res.getInt(S);
                }
                if (count != 0) return new Result(NO, Constants.DEL_FAIL);
                boolean a = execute(String.format(DEL_MARK, id)).getStatus() == COMPLETE;
                if (a == true) return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @return
     */
    public Result delLetter(long id) {
        try {
            if ((getLetter(id).isReceived() == true) && (execute(String.format(DEL_LETTER, id)).getStatus() == COMPLETE)) {
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delMoney(long id) {
        try {
            if ((getMoney(id).isReceived() == true) && (execute(String.format(DEL_MONEY, id)).getStatus() == COMPLETE)) {
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @return
     */
    public Result delPackage(long id) {
        try {
            if ((getPackage(id).isReceived() == true) && (execute(String.format(DEL_PACKAGE, id)).getStatus() == COMPLETE)) {
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @return
     */
    public Result delValuablePack(long id) {
        try {
            if ((getValuablePack(id).isReceived() == true) && (execute(String.format(DEL_VALUABLE_PACK, id)).getStatus() == COMPLETE)) {
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @param size
     * @param price
     * @return
     */
    public Result updEnvelope(long id, String size, int price) {
        try {
            boolean check = getEnvelope(id) != null;
            if (check) {
                boolean d = execute(String.format(UPD_ENVELOPE, size, price, id)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @param name
     * @param price
     * @return
     */
    public Result updMark(long id, String name, int price) {
        try {
            boolean check = getMark(id) != null;
            if (check) {
                boolean d = execute(String.format(UPD_MARK, name, price, id)).getStatus() == COMPLETE;
                if (d) return new Result(Msg.COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param officeId
     * @param country
     * @param city
     * @param address
     * @return
     */
    public Result updPostOffice(long officeId, String country, String city, String address) {
        ;
        try {
            boolean check = getPostOffice(officeId) != null;
            if (check) {
                boolean d = execute(String.format(UPD_POST_OFFICE, country, city, address, officeId)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @param name
     * @return
     */
    public Result updClient(long id, String name) {
        try {
            boolean check = getClient(id) != null;
            if (check) {
                boolean d = execute(String.format(UPD_CLIENT, name, id)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
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
    public Result updPackage(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile) {
        try {
            boolean check = getPackage(id) != null;
            if (check) {
                boolean d = execute(String.format(UPD_PACKAGE, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile, id)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
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
    public Result updValuablePack(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        try {
            boolean check = getValuablePack(id) != null;
            if (check) {
                boolean d = execute(String.format(UPD_VALUABLE_PACK, sender,
                        recipient, addressFrom, postOfficeFrom, addressTo,
                        postOfficeTo, price, date, size, weight, fragile, declaredValue, id)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
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
    public Result updMoney(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency) {
        try {
            boolean check = getMoney(id) != null;
            if (check) {
                boolean d = execute(String.format(UPD_MONEY, sender,
                        recipient, addressFrom, postOfficeFrom, addressTo,
                        postOfficeTo, price, date, sum,
                        currency, id)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
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
    public Result updLetter(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope) {
        try {
            boolean check = getLetter(id) != null;
            if (check) {
                boolean d = execute(String.format(UPD_LETTER, sender,
                        recipient, addressFrom, postOfficeFrom, addressTo,
                        postOfficeTo, price, date, weight,
                        mark, markCount, envelope, id)).getStatus() == COMPLETE;
                if (d) {
                    return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Result getClientHistory(long id) {
        try {
            StringBuilder answer = new StringBuilder();
            ResultSet resPack = select(String.format(HIST_PACK, id, id));
            ResultSet resVPack = select(String.format(HIST_V_PACK, id, id));
            ResultSet resMon = select(String.format(HIST_MONEY, id, id));
            ResultSet resLet = select(String.format(HIST_LETTER, id, id));

            while (resPack != null && resPack.next()) {
                answer.append(getPackage(resPack.getInt(ID_POST_OBJ)) + STR);
            }

            while (resMon != null && resMon.next()) {
                answer.append(getMoney(resMon.getInt(ID_POST_OBJ)) + STR);
            }

            while (resLet != null && resLet.next()) {
                answer.append(getLetter(resLet.getInt(ID_POST_OBJ)) + STR);
            }
            while (resLet != null && resVPack.next()) {
                answer.append(getValuablePack(resVPack.getInt(ID_POST_OBJ)) + STR);
            }

            return new Result(COMPLETE, answer.toString());
        } catch (SQLException e) {
            log.error(e);
        }
        return new Result(NO, IDP_READ_ERROR);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Result giveLetter(long id) {
        try {
            if (execute(String.format(GIVE_LETTER, id)).getStatus() == COMPLETE)
                return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public Result givePackage(long id) {
        try {
            if (execute(String.format(GIVE_PACKAGE, id)).getStatus() == COMPLETE)
                return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Result giveMoney(long id) {
        try {
            if (execute(String.format(GIVE_MONEY, id)).getStatus() == COMPLETE)
                return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Result giveValuablePack(long id) {
        try {
            if (execute(String.format(GIVE_VALUABLE_PACK, id)).getStatus() == COMPLETE)
                return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }
}

