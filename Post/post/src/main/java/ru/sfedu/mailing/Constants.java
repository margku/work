package ru.sfedu.mailing;

public class Constants {

//        ConfigurationUtils
        public static final String CU_KEY = "config";
        public static final String FULL_PATH = "src/main/resources/config.properties";
        public static final String DEFAULT_CONFIG_PATH = "/config.properties";
        public static final String CSV = ".csv";
        public static final String XML = ".xml";
        public static final String CLIENT = "client";
        public static final String POST_OFFICE = "PostOffice";
        public static final String HISTORY = "history";
        public static final String ENVELOPE = "envelope";
//        public static final String DEFAULT_CONFIG_PATH = "C:/Users/Rita/IdeaProjects/mailing2/target/config.properties";

        public static final String CSV_PATH = "csvPath";
        public static final String XML_PATH = "xmlPath";

        public static final String JDBC_DRIVER = "ru.sfedu.mailing.jdbcDriver";
        public static final String DB_CONNECT = "ru.sfedu.mailing.dbConn";
        public static final String DB_USER = "ru.sfedu.mailing.dbUsr";
        public static final String DB_PASS = "ru.sfedu.mailing.dbPass";

        public static final String HIST_PACK = "SELECT * FROM Package WHERE Sender=%d or Recipient=%d";
        public static final String HIST_V_PACK = "SELECT * FROM valuablePack WHERE Sender=%d or Recipient=%d";
        public static final String HIST_MONEY = "SELECT * FROM Money WHERE Sender=%d or Recipient=%d";
        public static final String HIST_LETTER = "SELECT * FROM Letter WHERE Sender=%d or Recipient=%d";


        public static final String CLIENT_ID = "Id";
        public static final String CLIENT_NAME = "Name";

        public static final String ID_POST_OBJ = "Id";
        public static final String SENDER= "Sender";
        public static final String RECIPIENT = "Recipient";
        public static final String ADDRESS_FROM = "AddressFrom";
        public static final String POST_OFFICE_FROM = "PostOfficeFrom";
        public static final String POST_OFFICE_TO = "PostOfficeTo";
        public static final String ADDRESS_TO = "AddressTo";
        public static final String PRICE = "Price";
        public static final String DATE = "Date";
        public static final String RECEIVED = "Received";

        public static final String SIZE = "Size";
        public static final String WEIGHT = "Weight";
        public static final String FRAGILE = "Fragile";

        public static final String DECLARED_VALUE = "DeclaredValue";

        public static final String MARK = "Mark";
        public static final String MARK_COUNT = "MarkCount";
        public static final String ID_ENVELOPE = "Envelope";

        public static final String SUM = "Sum";
        public static final String CURRENCY = "Currency";

        public static final String ENVELOPE_ID = "Id";
        public static final String EVELOPE_PRICE = "Price";

        public static final String MARK_ID = "Id";
        public static final String MARK_NAME = "Name";
        public static final String MARK_PRICE = "Price";

        public static final String OFFICE_ID = "officeId";
        public static final String OFFICE_COUNTRY = "Country";
        public static final String OFFICE_CITY = "City";
        public static final String OFFICE_ADDRESS = "Address";
        public static final String S = "s";


        public static final String INSERT_CLIENT = "INSERT INTO Client VALUES (%d, '%s');";
        public static final String INSERT_LETTER = "INSERT INTO Letter (id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, weight, mark, markCount, envelope) VALUES (%d, %d, %d, '%s', %d, '%s', %d, %d, '%s', %d, %d, %d, %d);";
        public static final String INSERT_ENVELOPE= "INSERT INTO Envelope VALUES (%d, '%s', %d)";
        public static final String INSERT_POST_OFFICE= "INSERT INTO PostOffice VALUES (%d, '%s', '%s', '%s');";
        public static final String INSERT_MARK= "INSERT INTO Mark VALUES (%d, '%s', %d);";
        public static final String INSERT_PACKAGE= "INSERT INTO Package (id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile) VALUES (%d, %d, %d, '%s', %d, '%s', %d, %d, '%s', %d, %d, %b);";
        public static final String INSERT_VALUABLE_PACK= "INSERT INTO ValuablePack (id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile, declaredValue)  VALUES  (%d, %d, %d, '%s', %d, '%s', %d, %d, '%s', %d,  %d, %b, %d);";
        public static final String INSERT_MONEY= "INSERT INTO Money (id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, sum, currency) VALUES (%d, %d, %d, '%s', %d, '%s', %d, %d, '%s', %d, '%s');";

        public static final String GET_MONEY= "SELECT * FROM Money WHERE id=%d";
        public static final String GET_LETTER= "SELECT * FROM Letter WHERE id=%d";
        public static final String GET_PACKAGE= "SELECT * FROM Package WHERE id=%d";
        public static final String GET_CLIENT= "SELECT * FROM Client WHERE id=%d";
        public static final String GET_VALUABLE_PACK= "SELECT * FROM ValuablePack WHERE id=%d";
        public static final String GET_ENVELOPE= "SELECT * FROM Envelope WHERE id=%d";
        public static final String GET_MARK= "SELECT * FROM Mark WHERE id=%d";
        public static final String GET_POST_OFFICE= "SELECT * FROM PostOffice WHERE officeId=%d";


        public static final String DEL_CLIENT= "DELETE FROM Client WHERE id=%d;";
        public static final String DEL_CLIENT_CHECK= "select sum(c) as s from (select count(*) as c from Letter where recipient= '%d' or sender='%d' union select count(*) as c from Package where recipient=%d or sender=%d union select count(*) as c from ValuablePack where recipient=%d or sender=%d union select count(*) as c from Money where recipient=%d or sender=%d) res";
        public static final String DEL_OFFICE_CHECK= "select sum(c) as s from (select count(*) as c from Letter where postofficefrom= '%d' or postofficeto='%d' union select count(*) as c from Package where postofficefrom=%d or postofficeto=%d union select count(*) as c from ValuablePack where postofficefrom=%d or postofficeto=%d union select count(*) as c from Money where postofficeto=%d or postofficefrom=%d) res";
        public static final String DEL_ENVELOPE_CHECK= "select count(*) as s from Letter where envelope= %d";
        public static final String DEL_MARK_CHECK= "select count(*) as s from Letter where mark= %d";

        public static final String DEL_LETTER= "DELETE FROM Letter WHERE id=%d and received=true;";
        public static final String DEL_MONEY= "DELETE FROM Money WHERE id=%d and received=true;";
        public static final String DEL_PACKAGE= "DELETE FROM Package WHERE id=%d and received=true;";
        public static final String DEL_VALUABLE_PACK= "DELETE FROM ValuablePack WHERE id=%d and received=true;";
        public static final String DEL_ENVELOPE= "DELETE FROM Envelope WHERE id=%d;";
        public static final String DEL_MARK= "DELETE FROM Mark WHERE id=%d;";
        public static final String DEL_POST_OFFICE= "DELETE FROM PostOffice WHERE officeId=%d;";


        public static final String UPD_ENVELOPE= "UPDATE Envelope SET size='%s', price=%d WHERE id=%d";
        public static final String UPD_MARK= "UPDATE Mark SET name='%s', price=%d WHERE id=%d";
        public static final String UPD_CLIENT= "UPDATE Client SET name='%s' WHERE id=%d";
        public static final String UPD_POST_OFFICE= "UPDATE postOffice SET country='%s' , city='%s', address='%s' WHERE officeid=%d";
        public static final String UPD_PACKAGE= "UPDATE Package SET sender=%d , recipient=%d, addressFrom='%s', postOfficeFrom=%d, addressTo='%s', postOfficeTo=%d, price=%d, date='%s', size = %d, weight = %d, fragile = %b WHERE id=%d";
        public static final String UPD_VALUABLE_PACK= "UPDATE ValuablePack SET sender=%d , recipient=%d, addressFrom='%s', postOfficeFrom=%d, addressTo='%s', postOfficeTo=%d, price=%d, date='%s', size = %d, weight = %d, fragile = %b, declaredValue = %d WHERE id=%d";
        public static final String UPD_LETTER= "UPDATE Letter SET sender=%d , recipient=%d, addressFrom='%s', postOfficeFrom=%d, addressTo='%s', postOfficeTo=%d, price=%d, date='%s',  weight = %d, mark = %d, markCount = %d, envelope = %d WHERE id=%d";
        public static final String UPD_MONEY= "UPDATE Money SET sender=%d , recipient=%d, addressFrom='%s', postOfficeFrom=%d, addressTo='%s', postOfficeTo=%d, price=%d, date='%s', sum = %d, currency = '%s' WHERE id=%d";

        public static final String GIVE_LETTER= "UPDATE Letter SET  received = true WHERE id=%d";
        public static final String GIVE_MONEY= "UPDATE Money SET  received = true WHERE id=%d";
        public static final String GIVE_PACKAGE= "UPDATE Package SET  received = true WHERE id=%d";
        public static final String GIVE_VALUABLE_PACK= "UPDATE ValuablePack SET  received = true WHERE id=%d";

        public static final String COUNT_ENV= "SELECT COUNT(*) as s FROM envelope";
        public static final String COUNT_MARK= "SELECT COUNT(*) as s FROM mark";
        public static final String COUNT_POST_OF= "SELECT COUNT(*) as s FROM postOffice";
        public static final String COUNT_CLIENT= "SELECT COUNT(*) as s FROM client";



        public static final String SPLIT = "\\W+";
        public static final String STR = "\n";
        public static final String CLI_ERROR = "Does not exists";
        public static final String CLI_PARSER_ERROR = "Invalid number of arguments";

        public static final String IDP_ADD_FAIL = "Write error";
        public static final String IDP_READ_ERROR = "Record not found";
        public static final String IDP_UPD_COMPLETE = "Record updated";
        public static final String IDP_COMPLETE = "Operation completed";
        public static final String IDP_FAIL = "Operation failed";
        public static final String DEL_FAIL = "Сannot be deleted, record is used";
        public static final String DEL_FAIL_NOT_RECEIVED = "Сannot be deleted, record is not found or not received";
        public static final String DEL_COMPLETE = "Record deleted";
        public static final String GIVE_P_O = "PostObject is given";
        public static final String CLIENT_HISTORY = "no client history";


        public static final String DEF_NAME = "Name";
        public static final long DEF_ID = 1;
        public static final String DEF_SIZE = "0x0";
        public static final int DEF_PRICE = 1111;
        public static final String DEF_COUNTRY = "country";
        public static final String DEF_CITY = "city";
        public static final String DEF_ADDRESS = "address";

        public static final String ADD = "ADD";
        public static final String GET = "GET";
        public static final String UPD = "UPD";
        public static final String GIVE = "GIVE";
        public static final String DEL = "DEL";
        public static final String CLIENTHIST = "HISTORY";









}



