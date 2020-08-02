package ru.sfedu.postHibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.postHibernate.provider.*;
import ru.sfedu.postHibernate.provider.iDataProvider.IDataProviderHibernate;
import ru.sfedu.postHibernate.provider.iDataProvider.IDataProviderLab2;
import ru.sfedu.postHibernate.provider.iDataProvider.IDataProviderLab4;
import ru.sfedu.postHibernate.provider.iDataProvider.IDataProviderLab5;

import java.util.Arrays;

import static ru.sfedu.postHibernate.Constants.*;


public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        String lab = args[0];
        switch (lab.toLowerCase()) {
//            lab2 get 1
            case LAB2:
                System.out.println(lab2(args)); break;
            case LAB3:
                System.out.println(lab3(args)); break;
            case LAB4:
                System.out.println(lab4(args)); break;
            case LAB5:
                System.out.println(lab5(args)); break;
            default:
                System.out.println("not key " + args[0]);

        }
    }

    public static String lab2(String[] args) {
        IDataProviderLab2 lab2 = new DataProviderLab2();
        DataGenerator data = new DataGenerator();
        data.lab2();
        switch (args[1].toUpperCase()) {
            case TBL:
                return lab2.getListOfCreatedTables().toString();
            case GET:
                return lab2.getClientLab(Long.parseLong(args[2])).toString() + "\n"
                        + lab2.getClientLab(Long.parseLong(args[2])).getMyFirstWashinmashine().toString() + "\n"
                        + lab2.getClientLab(Long.parseLong(args[2])).getMySecondWashinmashine().toString();
            case UPD:
                return lab2.updClientLab(Long.parseLong(args[2]), args[3], args[4]).toString();
            case DEL:
                return lab2.delClientLab(Long.parseLong(args[2])).toString();
            case BY_NAME:
                return lab2.getClientByName(args[2]).toString();
            default:
                return "not key " + args[1];
        }
    }

    public static String lab4(String[] args) {
        IDataProviderLab4 lab4 = new DataProviderLab4();
        DataGenerator data = new DataGenerator();
        data.lab4();
        switch (args[1].toUpperCase()) {
            case TBL:
                return lab4.getListOfCreatedTables().toString();
            case GET:
                return lab4.getClient(Long.parseLong(args[2])).toString() + "\n" +
                        lab4.getClient(Long.parseLong(args[2])).getAddress().toString() + "\n" +
                        lab4.getClient(Long.parseLong(args[2])).getNumber().toString() + "\n" +
                        lab4.getClient(Long.parseLong(args[2])).getDocument().toString();
            case UPD:
                return lab4.updClient(Long.parseLong(args[2]), args[3], Long.parseLong(args[4]), Long.parseLong(args[5]), args[6], args[7], args[8], Long.parseLong(args[9])).toString();
            case DEL:
                return lab4.delClient(Long.parseLong(args[2])).toString();
            case BY_NAME:
                return lab4.getClientByName(args[2]).toString();
            default:
                return "not key";
        }
    }

    public static String lab5(String[] args) {
        IDataProviderLab5 lab5 = new DataProviderLab5();
        DataGenerator data = new DataGenerator();
        data.lab5();
        switch (args[1].toUpperCase()) {
            case TBL:
                return lab5.getListOfCreatedTables().toString();
            case GET:
               return lab5.getClient(Long.parseLong(args[2])).toString()  + "\n" +
                lab5.getClient(Long.parseLong(args[2])).getDocument().toString() + "\n" +
                lab5.getCompany(Long.parseLong(args[2])).toString();
            case DEL:
                return lab5.delClient(Long.parseLong(args[2])).toString();
            case BY_NAME:
                return lab5.getClientByName(args[2]).toString();
            default:
                return "not key";
        }
    }

    public static String lab3(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        switch (args[1].toUpperCase()) {
            case MAPPED:
                DataProviderMappedSuperclass mapped = new DataProviderMappedSuperclass();
                dataGenerator.mapped();
                switch (args[2].toUpperCase()) {
                    case ADD:
                        return mapped.getListOfCreatedTables().toString();
                    case LETTER:
                        return mapped.getLetter(Long.parseLong(args[3])).toString();
                    case MONEY:
                        return mapped.getMoney(Long.parseLong(args[3])).toString();
                    case VALPACK:
                        return mapped.getValPack(Long.parseLong(args[3])).toString();
                    default:
                        return "not key " + args[3];
                }
            case JOINED:
                DataProviderJoined joined = new DataProviderJoined();
                dataGenerator.joinedTable();
                switch (args[2].toUpperCase()) {
                    case ADD:
                        return joined.getListOfCreatedTables().toString();
                    case LETTER:
                        return joined.getLetter(Long.parseLong(args[3])).toString();
                    case MONEY:
                        return joined.getMoney(Long.parseLong(args[3])).toString();
                    case VALPACK:
                        return joined.getValPack(Long.parseLong(args[3])).toString();
                    default:
                        return "not key " + args[2];
                }
            case SINGLE:
                DataProviderSingleTable singleTable = new DataProviderSingleTable();
                dataGenerator.single();
                switch (args[2].toUpperCase()) {
                    case ADD:
                        return singleTable.getListOfCreatedTables().toString();
                    case LETTER:
                        return singleTable.getLetter(Long.parseLong(args[3])).toString();
                    case MONEY:
                        return singleTable.getMoney(Long.parseLong(args[3])).toString();
                    case VALPACK:
                        return singleTable.getValPack(Long.parseLong(args[3])).toString();
                    default:
                        return "not key " + args[2];
                }
            case TPC:
                DataProviderTablePerClass tpc = new DataProviderTablePerClass();
                dataGenerator.tablePerClass();
                switch (args[2].toUpperCase()) {
                    case ADD:
                        return tpc.getListOfCreatedTables().toString();
                    case LETTER:
                        return tpc.getLetter(Long.parseLong(args[3])).toString();
                    case MONEY:
                        return tpc.getMoney(Long.parseLong(args[3])).toString();
                    case VALPACK:
                        return tpc.getValPack(Long.parseLong(args[3])).toString();
                    default:
                        return "not key " + args[2];
                }
            default:
                return "not key " + args[1];
        }
    }
}
