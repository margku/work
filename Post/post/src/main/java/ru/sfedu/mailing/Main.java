package ru.sfedu.mailing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mailing.provider.DataProviderCsv;
import ru.sfedu.mailing.provider.DataProviderXML;
import ru.sfedu.mailing.provider.DataProviderJdbc;
import ru.sfedu.mailing.provider.IDataProvider;


import static ru.sfedu.mailing.Constants.*;


public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);
    public static String CONFIG_PATH = System.getProperty(CU_KEY, DEFAULT_CONFIG_PATH);

    public static void main(String[] args) {
//        csv client app 1 Boris
        if (args.length > 3) {
            IDataProvider provider = defineDataSource(args[0]);
            assert provider != null;
            logger.info(getAnswer(provider, args));
        } else {
            logger.error("");
        }
    }

    private static IDataProvider defineDataSource(String key) {
        switch (Keys.valueOf(key.toUpperCase())) {
            case CSV:
                return new DataProviderCsv();
            case XML:
                return new DataProviderXML();
            case DB:
                return new DataProviderJdbc();
            default:
                return null;
        }
    }

    private static String getAnswer(IDataProvider provider, String[] args) {
        switch (TypeObject.valueOf(args[1].toUpperCase())) {
            case POSTOFFICE:
                return postOffice(provider, args);
            case CLIENT:
                return client(provider, args);
            case MARK:
                return mark(provider, args);
            case ENVELOPE:
                return envelope(provider, args);
            case LETTER:
                return letter(provider, args);
            case PACKAGE:
                return packages(provider, args);
            case MONEY:
                return money(provider, args);
            case VALUABLEPACK:
                return valuablePack(provider, args);
            default:
                return "not beans";
        }
    }

    private static String client(IDataProvider provider, String[] args) {
        try {
            switch (args[2].toUpperCase()) {
                case ADD:
                    assert args.length != 5;
                    return provider.addClient(Long.parseLong(args[3]), args[4]).getAnswer();
                case UPD:
                    assert args.length != 5;
                    return provider.updClient(Long.parseLong(args[3]), args[4]).getAnswer();
                case GET:
                    assert args.length != 4;
                    return provider.getClient(Long.parseLong(args[3])).toString();
                case DEL:
                    assert args.length != 4;
                    return provider.delClient(Long.parseLong(args[3])).getAnswer();
                case CLIENTHIST:
                    assert args.length != 4;
                    return provider.getClientHistory(Long.parseLong(args[3])).getAnswer();
                default:
                    return "client not key";
            }
        } catch (Exception e) {
            logger.error(e);
            return "client - error";
        }
    }

    private static String postOffice(IDataProvider provider, String[] args) {
        try {
            switch (args[2].toUpperCase()) {
                case ADD:
                    assert args.length != 7;
                    return provider.addPostOffice(Long.parseLong(args[3]), args[4], args[5], args[6]).getAnswer();
                case UPD:
                    assert args.length != 7;
                    return provider.updPostOffice(Long.parseLong(args[3]), args[4], args[5], args[6]).getAnswer();
                case GET:
                    assert args.length != 4;
                    return provider.getPostOffice(Long.parseLong(args[3])).toString();
                case DEL:
                    assert args.length != 4;
                    return provider.delPostOffice(Long.parseLong(args[3])).getAnswer();
                default:
                    return "postOffice not key";
            }
        } catch (Exception e) {
            logger.error(e);
            return "postOffice - error";
        }
    }

    private static String mark(IDataProvider provider, String[] args) {
        try {
            switch (args[2].toUpperCase()) {
                case ADD:
                    assert args.length != 6;
                    return provider.addMark(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5])).getAnswer();
                case UPD:
                    assert args.length != 6;
                    return provider.updMark(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5])).getAnswer();
                case GET:
                    assert args.length != 4;
                    return provider.getMark(Long.parseLong(args[3])).toString();
                case DEL:
                    assert args.length != 4;
                    return provider.delMark(Long.parseLong(args[3])).getAnswer();
                default:
                    return "mark not key";
            }
        } catch (Exception e) {
            logger.error(e);
            return "mark - error";
        }
    }

    private static String envelope(IDataProvider provider, String[] args) {
        try {
            switch (args[2].toUpperCase()) {
                case ADD:
                    assert args.length != 6;
                    return provider.addEnvelope(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5])).getAnswer();
                case UPD:
                    assert args.length != 6;
                    return provider.updEnvelope(Long.parseLong(args[3]), args[4], Integer.parseInt(args[5])).getAnswer();
                case GET:
                    assert args.length != 4;
                    return provider.getEnvelope(Long.parseLong(args[3])).toString();
                case DEL:
                    assert args.length != 4;
                    return provider.delEnvelope(Long.parseLong(args[3])).getAnswer();
                default:
                    return "envelope not key";
            }
        } catch (Exception e) {
            logger.error(e);
            return "envelope - error";
        }
    }

    private static String money(IDataProvider provider, String[] args) {
        try {
            switch (args[2].toUpperCase()) {
                case ADD:
                    assert args.length != 14;
                    return provider.addMoney(Long.parseLong(args[3]), (Long.parseLong(args[4])), (Long.parseLong(args[5])), args[6], (Long.parseLong(args[7])), args[8], (Long.parseLong(args[9])), Integer.parseInt(args[10]), args[11], Integer.parseInt(args[12]), args[13]).getAnswer();
                case UPD:
                    assert args.length != 14;
                    return provider.updMoney(Long.parseLong(args[3]), (Long.parseLong(args[4])), (Long.parseLong(args[5])), args[6], (Long.parseLong(args[7])), args[8], (Long.parseLong(args[9])), Integer.parseInt(args[10]), args[11], Integer.parseInt(args[12]), args[13]).getAnswer();
                case GET:
                    assert args.length != 4;
                    return provider.getMoney(Long.parseLong(args[3])).toString();
                case DEL:
                    assert args.length != 4;
                    return provider.delMoney(Long.parseLong(args[3])).getAnswer();
                case GIVE:
                    assert args.length != 4;
                    return provider.giveMoney(Long.parseLong(args[3])).getAnswer();
                default:
                    return "money not key";
            }
        } catch (Exception e) {
            logger.error(e);
            return "money - error";
        }
    }

    private static String letter(IDataProvider provider, String[] args) {
        try {
            switch (args[2].toUpperCase()) {
                case ADD:
                    assert args.length != 16;
                    return provider.addLetter(Long.parseLong(args[3]), (Long.parseLong(args[4])), (Long.parseLong(args[5])), args[6], (Long.parseLong(args[7])), args[8], (Long.parseLong(args[9])), Integer.parseInt(args[10]), args[11], Integer.parseInt(args[12]), Long.parseLong(args[13]), Integer.parseInt(args[14]), Long.parseLong(args[15])).getAnswer();
                case UPD:
                    assert args.length != 16;
                    return provider.updLetter(Long.parseLong(args[3]), (Long.parseLong(args[4])), (Long.parseLong(args[5])), args[6], (Long.parseLong(args[7])), args[8], (Long.parseLong(args[9])), Integer.parseInt(args[10]), args[11], Integer.parseInt(args[12]), Long.parseLong(args[13]), Integer.parseInt(args[14]), Long.parseLong(args[15])).getAnswer();
                case GET:
                    assert args.length != 4;
                    return provider.getLetter(Long.parseLong(args[3])).toString();
                case DEL:
                    assert args.length != 4;
                    return provider.delMoney(Long.parseLong(args[3])).getAnswer();
                case GIVE:
                    assert args.length != 4;
                    return provider.giveLetter(Long.parseLong(args[3])).getAnswer();
                default:
                    return "letter not key";
            }
        } catch (Exception e) {
            logger.error(e);
            return "letter - error";
        }
    }

    private static String packages(IDataProvider provider, String[] args) {
        try {
            switch (args[2].toUpperCase()) {
                case ADD:
                    assert args.length != 15;
                    return provider.addPackage(Long.parseLong(args[3]), (Long.parseLong(args[4])), (Long.parseLong(args[5])), args[6], (Long.parseLong(args[7])), args[8], (Long.parseLong(args[9])), Integer.parseInt(args[10]), args[11], Integer.parseInt(args[12]), Integer.parseInt(args[13]), Boolean.parseBoolean(args[14])).getAnswer();
                case UPD:
                    assert args.length != 15;
                    return provider.updPackage(Long.parseLong(args[3]), (Long.parseLong(args[4])), (Long.parseLong(args[5])), args[6], (Long.parseLong(args[7])), args[8], (Long.parseLong(args[9])), Integer.parseInt(args[10]), args[11], Integer.parseInt(args[12]), Integer.parseInt(args[13]), Boolean.parseBoolean(args[14])).getAnswer();
                case GET:
                    assert args.length != 4;
                    return provider.getPackage(Long.parseLong(args[3])).toString();
                case DEL:
                    assert args.length != 4;
                    return provider.delPackage(Long.parseLong(args[3])).getAnswer();
                case GIVE:
                    assert args.length != 4;
                    return provider.givePackage(Long.parseLong(args[3])).getAnswer();
                default:
                    return "package not key";
            }
        } catch (Exception e) {
            logger.error(e);
            return "package - error";
        }
    }

    private static String valuablePack(IDataProvider provider, String[] args) {
        try {
            switch (args[2].toUpperCase()) {
                case ADD:
                    assert args.length != 16;
                    return provider.addValuablePack(Long.parseLong(args[3]), (Long.parseLong(args[4])), (Long.parseLong(args[5])), args[6], (Long.parseLong(args[7])), args[8], (Long.parseLong(args[9])), Integer.parseInt(args[10]), args[11], Integer.parseInt(args[12]), Integer.parseInt(args[13]), Boolean.parseBoolean(args[14]), Integer.parseInt(args[15])).getAnswer();
                case UPD:
                    assert args.length != 16;
                    return provider.updValuablePack(Long.parseLong(args[3]), (Long.parseLong(args[4])), (Long.parseLong(args[5])), args[6], (Long.parseLong(args[7])), args[8], (Long.parseLong(args[9])), Integer.parseInt(args[10]), args[11], Integer.parseInt(args[12]), Integer.parseInt(args[13]), Boolean.parseBoolean(args[14]), Integer.parseInt(args[15])).getAnswer();
                case GET:
                    assert args.length != 4;
                    return provider.getValuablePack(Long.parseLong(args[3])).toString();
                case DEL:
                    assert args.length != 4;
                    return provider.delValuablePack(Long.parseLong(args[3])).getAnswer();
                case GIVE:
                    assert args.length != 4;
                    return provider.giveValuablePack(Long.parseLong(args[3])).getAnswer();
                default:
                    return "valPack not key";
            }
        } catch (Exception e) {
            logger.error(e);
            return "valPack - error";
        }
    }
}
