package ru.sfedu.postHibernate;

public class Constants {

        public static final String HB_KEY = "hb";
//        ConfigurationUtils

        public static final String IDP_READ_ERROR = "Record not found";
        public static final String IDP_UPD_COMPLETE = "Record updated";
        public static final String IDP_COMPLETE = "Operation completed";
        public static final String IDP_FAIL = "Operation failed";
        public static final String DEL_COMPLETE = "Record deleted";


        public static final String ADD = "ADD";
        public static final String TBL = "TBL";
        public static final String GET = "GET";
        public static final String UPD = "UPD";
        public static final String DEL = "DEL";
        public static final String BY_NAME = "BY_NAME";
        public static final String LETTER = "LETTER";
        public static final String MONEY = "MONEY";
        public static final String VALPACK = "VALPACK";

        public static final String LAB2 = "lab2";
        public static final String LAB3 = "lab3";
        public static final String LAB4 = "lab4";
        public static final String LAB5 = "lab5";


        public static final String HSQLDB_CATALOG_LAB2 = "HSQLDB_CATALOG_LAB2";
        public static final String HSQLDB_CATALOG_LAB4 = "HSQLDB_CATALOG_LAB4";
        public static final String HSQLDB_CATALOG_LAB5 = "HSQLDB_CATALOG_LAB5";
        public static final String HSQLDB_CATALOG_MAPPED = "HSQLDB_CATALOG_MAPPED";
        public static final String HSQLDB_CATALOG_TABLEPERCLASS = "HSQLDB_CATALOG_TABLEPERCLASS";
        public static final String HSQLDB_CATALOG_JOINED = "HSQLDB_CATALOG_MAPPEDSUPERCLASS";
        public static final String HSQLDB_CATALOG_SINGLETABLE= "HSQLDB_CATALOG_SINGLETABLE";

        public static final String MAPPED= "MAPPED";
        public static final String JOINED= "JOINED";
        public static final String SINGLE= "SINGLE";
        public static final String TPC= "TPC";
        public static final String NAME="name";
        public static final String ID="id ";
        public static final String QUERY="select u from company where u.id=:id";
        public static final String HQL="from ru.sfedu.postHibernate.models.lab5.Company where id=:id";
        public static final String CLIENT_LAB="from ru.sfedu.postHibernate.models.lab2.ClientLab where name=:name";
        public static final String CLIENT4="from ru.sfedu.postHibernate.models.lab4.ClientInfo where name=:name";
        public static final String CLIENT5="from ru.sfedu.postHibernate.models.lab5.Client where name=:name";
        public static final String COMPANY="from ru.sfedu.postHibernate.models.lab5.Company where name=:name";
}



