package ru.sfedu.mailing.provider;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mailing.Constants;
import ru.sfedu.mailing.Msg;
import ru.sfedu.mailing.Result;
import ru.sfedu.mailing.TypeObject;
import ru.sfedu.mailing.models.Package;
import ru.sfedu.mailing.utils.*;
import ru.sfedu.mailing.models.*;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Collections;
import java.util.List;

import static ru.sfedu.mailing.Constants.*;
import static ru.sfedu.mailing.Msg.COMPLETE;
import static ru.sfedu.mailing.Msg.NO;


public class DataProviderCsv implements IDataProvider {
    public static Logger log = LogManager.getLogger(DataProviderCsv.class);

    /**
     * @return
     */
    private Result checkClient() {
        try {
            File file = new File(ConfigurationUtil.getConfigurationEntry(CSV_PATH) + CLIENT + CSV);
            if (file.length() == 0) {
                return addClient(DEF_ID, DEF_NAME);
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
        try {
            File file = new File(ConfigurationUtil.getConfigurationEntry(CSV_PATH) + MARK + CSV);
            if (file.length() == 0) {
                return addMark(DEF_ID, DEF_NAME, DEF_PRICE);
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
        try {
            File file = new File(ConfigurationUtil.getConfigurationEntry(CSV_PATH) + ENVELOPE + CSV);
            if (file.length() == 0) {
                return addEnvelope(DEF_ID, DEF_SIZE, DEF_PRICE);
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
        try {
            File file = new File(ConfigurationUtil.getConfigurationEntry(CSV_PATH) + POST_OFFICE + CSV);
            if (file.length() == 0) {
                return addPostOffice(DEF_ID, DEF_COUNTRY, DEF_CITY, DEF_ADDRESS);
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
        try {
            if (getPostOffice(officeId) == null) {
                PostOffice postOffice = new PostOffice(officeId, country, city, address);
                add(postOffice, true);
                return new Result(COMPLETE, IDP_COMPLETE);
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
        Client client = new Client(id, name);
        try {
            if (getClient(id) == null) {
                add(client, true);
                return new Result(COMPLETE, IDP_COMPLETE);
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
        Envelope envelope = new Envelope(id, size, price);
        try {
            if (getEnvelope(id) == null) {
                add(envelope, true);
                return new Result(COMPLETE, IDP_COMPLETE);
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
            if (getMark(id) == null) {
                Mark mark = new Mark(id, name, price);
                add(mark, true);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @param id
     * @param senderid
     * @param recipientid
     * @param addressFrom
     * @param postOfficeFromid
     * @param addressTo
     * @param postOfficeToid
     * @param price
     * @param date
     * @param sum
     * @param currency
     * @return
     */
    public Result addMoney(long id, long senderid, long recipientid, String addressFrom, long postOfficeFromid, String addressTo, long postOfficeToid, int price, String date, int sum, String currency) {
        checkClient();
        checkPostOffice();
        try {
            Client sender = getClient(senderid);
            Client recipient = getClient(recipientid);
            PostOffice postOfficeFrom = getPostOffice(postOfficeFromid);
            PostOffice postOfficeTo = getPostOffice(postOfficeToid);
            boolean check = ((getMoney(id) == null)
                    && (sender != null)
                    && (recipient != null)
                    && (postOfficeFrom != null)
                    && (postOfficeTo != null));
            if (check) {
                Money money = new Money(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, sum, currency);
                add(money, true);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @param id
     * @param senderid
     * @param recipientid
     * @param addressFrom
     * @param postOfficeFromid
     * @param addressTo
     * @param postOfficeToid
     * @param price
     * @param date
     * @param weight
     * @param markid
     * @param markCount
     * @param envelopeid
     * @return
     */
    public Result addLetter(long id, long senderid, long recipientid, String addressFrom, long postOfficeFromid, String addressTo, long postOfficeToid, int price, String date, int weight, long markid, int markCount, long envelopeid) {
        checkClient();
        checkEnvelope();
        checkMark();
        checkPostOffice();
        Client sender = getClient(senderid);
        Client recipient = getClient(recipientid);
        PostOffice postOfficeFrom = getPostOffice(postOfficeFromid);
        PostOffice postOfficeTo = getPostOffice(postOfficeToid);
        Envelope envelope = getEnvelope(envelopeid);
        Mark mark = getMark(markid);
        try {
            boolean check = ((getLetter(id) == null)
                    && (sender != null)
                    && (recipient != null)
                    && (mark != null)
                    && (envelope != null)
                    && (postOfficeFrom != null)
                    && (postOfficeTo != null));
            if (check) {
                Letter letter = new Letter(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, weight, mark, markCount, envelope);
                add(letter, true);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @param id
     * @param senderid
     * @param recipientid
     * @param addressFrom
     * @param postOfficeFromid
     * @param addressTo
     * @param postOfficeToid
     * @param price
     * @param date
     * @param size
     * @param weight
     * @param fragile
     * @return
     */
    public Result addPackage(long id, long senderid, long recipientid, String addressFrom, long postOfficeFromid, String addressTo, long postOfficeToid, int price, String date, int size, int weight, boolean fragile) {
        checkClient();
        checkPostOffice();
        try {
            Client sender = getClient(senderid);
            Client recipient = getClient(recipientid);
            PostOffice postOfficeFrom = getPostOffice(postOfficeFromid);
            PostOffice postOfficeTo = getPostOffice(postOfficeToid);
            boolean c = ((getPackage(id) == null)
                    && (sender != null)
                    && (recipient != null)
                    && (postOfficeFrom != null)
                    && (postOfficeTo != null));
            if (c) {
                Package pack = new Package(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile);
                add(pack, true);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     * @param id
     * @param senderid
     * @param recipientid
     * @param addressFrom
     * @param postOfficeFromid
     * @param addressTo
     * @param postOfficeToid
     * @param price
     * @param date
     * @param size
     * @param weight
     * @param fragile
     * @param declaredValue
     * @return
     */
    public Result addValuablePack(long id, long senderid, long recipientid, String addressFrom, long postOfficeFromid, String addressTo, long postOfficeToid, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        checkClient();
        checkPostOffice();
        try {
            Client sender = getClient(senderid);
            Client recipient = getClient(recipientid);
            PostOffice postOfficeFrom = getPostOffice(postOfficeFromid);
            PostOffice postOfficeTo = getPostOffice(postOfficeToid);
            boolean c = ((getValuablePack(id) == null)
                    && (sender != null)
                    && (recipient != null)
                    && (postOfficeFrom != null)
                    && (postOfficeTo != null));
            if (c) {
                ValuablePack valuablePack = new ValuablePack(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile, declaredValue);
                add(valuablePack, true);
                return new Result(COMPLETE, IDP_COMPLETE);
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
    public Envelope getEnvelope(long id) {
        try {
            List<Envelope> list = readFromCsv(Envelope.class);
            return list.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
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
            List<Mark> list = readFromCsv(Mark.class);
            return list.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
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
            List<Package> list = readFromCsv(Package.class);
            return list.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
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
            List<ValuablePack> list = readFromCsv(ValuablePack.class);
            return list.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
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
            List<Letter> list = readFromCsv(Letter.class);
            return list.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
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
            List<Money> list = readFromCsv(Money.class);
            return list.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public Client getClient(long id) {
        try {
            List<Client> list = readFromCsv(Client.class);
            return list.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
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
            List<PostOffice> list = readFromCsv(PostOffice.class);
            return list.stream().filter(b -> b.getOfficeId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */

    public Result delEnvelope(long id) {
        try {
            if (getEnvelope(id) != null) {
                List<Envelope> envelopes = readFromCsv(Envelope.class);
                List<Letter> letters = readFromCsv(Letter.class);
                boolean check = letters.stream().anyMatch(a -> a.getEnvelope().getId() == id);
                if (check) return new Result(NO, Constants.DEL_FAIL);
                envelopes.removeIf(aEnv -> aEnv.getId() == id);
                addList(envelopes, TypeObject.ENVELOPE);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delPostOffice(long id) {
        try {
            if (getPostOffice(id) != null) {
                List<PostOffice> postOffices = readFromCsv(PostOffice.class);
                List<Letter> letters = readFromCsv(Letter.class);
                List<Package> packages = readFromCsv(Package.class);
                List<Money> monies = readFromCsv(Money.class);
                List<ValuablePack> valuablePacks = readFromCsv(ValuablePack.class);
                boolean checkLet = (letters.stream().anyMatch(a -> a.getPostOfficeTo().getOfficeId() == id || a.getPostOfficeFrom().getOfficeId() == id));
                boolean checkMon = (monies.stream().anyMatch(a -> a.getPostOfficeTo().getOfficeId() == id || a.getPostOfficeFrom().getOfficeId() == id));
                boolean checkPack = (packages.stream().anyMatch(a -> a.getPostOfficeTo().getOfficeId() == id || a.getPostOfficeFrom().getOfficeId() == id));
                boolean checkVPack = (valuablePacks.stream().anyMatch(a -> a.getPostOfficeTo().getOfficeId() == id || a.getPostOfficeFrom().getOfficeId() == id));
                if (checkLet || checkMon || checkPack || checkVPack) return new Result(NO, Constants.DEL_FAIL);
                postOffices.removeIf(aEnv -> aEnv.getOfficeId() == id);
                addList(postOffices, TypeObject.POSTOFFICE);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delClient(long id) {
        try {
            if (getClient(id) != null) {
                List<Client> clients = readFromCsv(Client.class);
                List<Letter> letters = readFromCsv(Letter.class);
                List<Package> packages = readFromCsv(Package.class);
                List<Money> monies = readFromCsv(Money.class);
                List<ValuablePack> valuablePacks = readFromCsv(ValuablePack.class);
                boolean checkLet = (letters.stream().anyMatch(a -> a.getRecipient().getId() == id || a.getSender().getId() == id));
                boolean checkMon = (monies.stream().anyMatch(a -> a.getRecipient().getId() == id || a.getSender().getId() == id));
                boolean checkPack = (packages.stream().anyMatch(a -> a.getSender().getId() == id || a.getRecipient().getId() == id));
                boolean checkVPack = (valuablePacks.stream().anyMatch(a -> a.getRecipient().getId() == id || a.getSender().getId() == id));
                if (checkLet || checkMon || checkPack || checkVPack) return new Result(NO, Constants.DEL_FAIL);
                clients.removeIf(aClient -> aClient.getId() == id);
                addList(clients, getClient(id).getTypeObject());
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
    public Result delLetter(long id) {
        try {
            List<Letter> letters = readFromCsv(Letter.class);
            boolean check = letters.stream().anyMatch(a -> a.getId() == id && a.isReceived() == true);
            if (check) {
                letters.removeIf(aLet -> aLet.getId() == id);
                addList(letters, TypeObject.LETTER);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delPackage(long id) {
        try {
            List<Package> packageList = readFromCsv(Package.class);
            boolean check = packageList.stream().anyMatch(a -> a.getId() == id && a.isReceived() == true);
            if (check) {
                packageList.removeIf(aPackage -> aPackage.getId() == id);
                addList(packageList, TypeObject.PACKAGE);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delValuablePack(long id) {
        try {
            List<ValuablePack> packs = readFromCsv(ValuablePack.class);
            boolean check = packs.stream().anyMatch(a -> a.getId() == id && a.isReceived() == true);
            if (check) {
                packs.removeIf(aPackage -> aPackage.getId() == id);
                addList(packs, TypeObject.VALUABLEPACK);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, DEL_FAIL_NOT_RECEIVED);
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
            List<Money> moneyList = readFromCsv(Money.class);
            boolean check = moneyList.stream().anyMatch(a -> a.getId() == id && a.isReceived() == true);
            if (check) {
                moneyList.removeIf(aMoney -> aMoney.getId() == id);
                addList(moneyList, TypeObject.MONEY);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result delMark(long id) {
        try {
            if (getMark(id) != null) {
                List<Mark> marksList = readFromCsv(Mark.class);
                List<Letter> letters = readFromCsv(Letter.class);
                boolean check = letters.stream().anyMatch(a -> a.getMark().getId() == id);
                if (check) return new Result(NO, Constants.DEL_FAIL);
                marksList.removeIf(aMark -> aMark.getId() == id);
                addList(marksList, TypeObject.MARK);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @param size
     * @param price
     * @return
     */
    public Result updEnvelope(long id, String size, int price) {
        try {
            if (delEnvelope(id).getStatus() == COMPLETE && addEnvelope(id, size, price).getStatus() == COMPLETE)
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_FAIL);
    }

    /**
     * @param id
     * @param name
     * @return
     */
    public Result updClient(long id, String name) {
        try {
            if (delClient(id).getStatus() == COMPLETE && addClient(id, name).getStatus() == COMPLETE)
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_FAIL);
    }

    /**
     * @param id
     * @param senderid
     * @param recipientid
     * @param addressFrom
     * @param postOfficeFromid
     * @param addressTo
     * @param postOfficeToid
     * @param price
     * @param date
     * @param weight
     * @param markid
     * @param markCount
     * @param envelopeid
     * @return
     */
    public Result updLetter(long id, long senderid, long recipientid, String addressFrom, long postOfficeFromid, String addressTo, long postOfficeToid, int price, String date, int weight, long markid, int markCount, long envelopeid) {
        try {
            Client sender = getClient(senderid);
            Client recipient = getClient(recipientid);
            PostOffice postOfficeFrom = getPostOffice(postOfficeFromid);
            PostOffice postOfficeTo = getPostOffice(postOfficeToid);
            Mark mark = getMark(markid);
            Envelope envelope = getEnvelope(envelopeid);
            boolean c = ((sender != null)
                    && (recipient != null)
                    && (getLetter(id) != null)
                    && (mark != null)
                    && (envelope != null)
                    && (postOfficeFrom != null)
                    && (postOfficeTo != null));
            if (c) {
                Letter letter = new Letter(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, weight, mark, markCount, envelope);
                List<Letter> list = readFromCsv(Letter.class);
                list.removeIf(a -> a.getId() == letter.getId());
                list.add(letter);
                addList(list, TypeObject.LETTER);
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO);
    }

    /**
     * @param id
     * @param name
     * @param price
     * @return
     */
    public Result updMark(long id, String name, int price) {
        try {
            if ((getMark(id) != null)) {
                Mark mark = new Mark(id, name, price);
                List<Mark> list = readFromCsv(Mark.class);
                list.removeIf(a -> a.getId() == mark.getId());
                list.add(mark);
                addList(list, TypeObject.MARK);
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO);
    }

    /**
     * @param id
     * @param senderid
     * @param recipientid
     * @param addressFrom
     * @param postOfficeFromid
     * @param addressTo
     * @param postOfficeToid
     * @param price
     * @param date
     * @param sum
     * @param currency
     * @return
     */
    public Result updMoney(long id, long senderid, long recipientid, String addressFrom, long postOfficeFromid, String addressTo, long postOfficeToid, int price, String date, int sum, String currency) {
        try {
            Client sender = getClient(senderid);
            Client recipient = getClient(recipientid);
            PostOffice postOfficeFrom = getPostOffice(postOfficeFromid);
            PostOffice postOfficeTo = getPostOffice(postOfficeToid);
            boolean c = ((sender != null)
                    && (recipient != null)
                    && (getMoney(id) != null)
                    && (postOfficeFrom != null)
                    && (postOfficeTo != null));
            if (c) {
                Money money = new Money(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, sum, currency);
                List<Money> list = readFromCsv(Money.class);
                list.removeIf(a -> a.getId() == money.getId());
                list.add(money);
                addList(list, TypeObject.MONEY);
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO);
    }

    /**
     * @param id
     * @param senderid
     * @param recipientid
     * @param addressFrom
     * @param postOfficeFromid
     * @param addressTo
     * @param postOfficeToid
     * @param price
     * @param date
     * @param size
     * @param weight
     * @param fragile
     * @return
     */
    public Result updPackage(long id, long senderid, long recipientid, String addressFrom, long postOfficeFromid, String addressTo, long postOfficeToid, int price, String date, int size, int weight, boolean fragile) {
        try {
            Client sender = getClient(senderid);
            Client recipient = getClient(recipientid);
            PostOffice postOfficeFrom = getPostOffice(postOfficeFromid);
            PostOffice postOfficeTo = getPostOffice(postOfficeToid);
            boolean c = ((sender != null)
                    && (recipient != null)
                    && (getPackage(id) != null)
                    && (postOfficeFrom != null)
                    && (postOfficeTo != null));
            if (c) {
                Package aPackage = new Package(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile);
                List<Package> list = readFromCsv(Package.class);
                list.removeIf(a -> a.getId() == aPackage.getId());
                list.add(aPackage);
                addList(list, TypeObject.PACKAGE);
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO);
    }

    /**
     * @param officeId
     * @param country
     * @param city
     * @param address
     * @return
     */
    public Result updPostOffice(long officeId, String country, String city, String address) {
        try {
            if ((getPostOffice(officeId) != null)) {
                PostOffice postOffice = new PostOffice(officeId, country, city, address);
                List<PostOffice> list = readFromCsv(PostOffice.class);
                list.removeIf(a -> a.getOfficeId() == postOffice.getOfficeId());
                list.add(postOffice);
                addList(list, TypeObject.POSTOFFICE);
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO);
    }

    /**
     * @param id
     * @param senderid
     * @param recipientid
     * @param addressFrom
     * @param postOfficeFromid
     * @param addressTo
     * @param postOfficeToid
     * @param price
     * @param date
     * @param size
     * @param weight
     * @param fragile
     * @param declaredValue
     * @return
     */
    public Result updValuablePack(long id, long senderid, long recipientid, String addressFrom, long postOfficeFromid, String addressTo, long postOfficeToid, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        try {
            Client sender = getClient(senderid);
            Client recipient = getClient(recipientid);
            PostOffice postOfficeFrom = getPostOffice(postOfficeFromid);
            PostOffice postOfficeTo = getPostOffice(postOfficeToid);
            boolean c = ((sender != null)
                    && (recipient != null)
                    && (getValuablePack(id) != null)
                    && (postOfficeFrom != null)
                    && (postOfficeTo != null));
            if (c) {
                ValuablePack valuablePack = new ValuablePack(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile, declaredValue);
                List<ValuablePack> list = readFromCsv(ValuablePack.class);
                list.removeIf(a -> a.getId() == valuablePack.getId());
                list.add(valuablePack);
                addList(list, TypeObject.VALUABLEPACK);
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO);
    }

    //---------------------USE-CASE methods

    /**
     * @param id
     * @return
     */
    public Result getClientHistory(long id) {
        try {
            if (getEnvelope(id) != null) {
                List<Letter> letters = readFromCsv(Letter.class);
                List<Money> monies = readFromCsv(Money.class);
                List<Package> packages = readFromCsv(Package.class);
                List<ValuablePack> valuablePacks = readFromCsv(ValuablePack.class);
                letters.removeIf(letter -> letter.getRecipient().getId() != id || letter.getSender().getId() != id);
                monies.removeIf(money -> money.getRecipient().getId() != id || money.getSender().getId() != id);
                packages.removeIf(pack -> pack.getRecipient().getId() != id || pack.getSender().getId() != id);
                valuablePacks.removeIf(pack -> pack.getRecipient().getId() != id || pack.getSender().getId() != id);
                String str = monies.toString() + "\n" + packages.toString() + "\n" + letters.toString() + "\n" + valuablePacks.toString();
                if (str.length() == 11) return new Result(Msg.NO, CLIENT_HISTORY);
                return new Result(COMPLETE, str);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     * @param id
     * @return
     */
    public Result giveLetter(long id) {
        try {
            List<Letter> letters = readFromCsv(Letter.class);
            letters.stream().filter(l -> l.getId() == id).findFirst().ifPresent(l -> {
                l.setReceived(true);
            });
            addList(letters, TypeObject.LETTER);
            return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @return
     */
    public Result givePackage(long id) {
        try {
            List<Package> packages = readFromCsv(Package.class);
            packages.stream().filter(l -> l.getId() == id).findFirst().ifPresent(l -> {
                l.setReceived(true);
            });
            addList(packages, TypeObject.PACKAGE);
            return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @return
     */
    public Result giveMoney(long id) {
        try {
            List<Money> monies = readFromCsv(Money.class);
            monies.stream().filter(l -> l.getId() == id).findFirst().ifPresent(l ->
                    l.setReceived(true));
            addList(monies, TypeObject.MONEY);
            return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param id
     * @return
     */
    public Result giveValuablePack(long id) {
        try {
            List<ValuablePack> valuablePacks = readFromCsv(ValuablePack.class);
            valuablePacks.stream().filter(l -> l.getId() == id).findFirst().ifPresent(l ->
                    l.setReceived(true));
            addList(valuablePacks, TypeObject.VALUABLEPACK);
            return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     * @param object
     * @param append
     * @return
     */
    public Result add(Object object, Boolean append) {
        try {
            String type = object.getClass().getSimpleName().toUpperCase();
            FileWriter file_path = new FileWriter(ConfigurationUtil.getConfigurationEntry(CSV_PATH) + type + CSV, append);
            CSVWriter writer = new CSVWriter(file_path);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(object);
            writer.close();
            return new Result(COMPLETE);
        } catch (Exception e) {
            return new Result(NO);
        }
    }

    /**
     * @param type
     * @param <T>
     * @return
     */

    private <T> List<T> readFromCsv(Class type) {
        try {
            String name = type.getSimpleName().toUpperCase();
            FileReader fileReader = new FileReader(ConfigurationUtil.getConfigurationEntry(CSV_PATH) + name + CSV);
            CSVReader csvReader = new CSVReader(fileReader);
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(type).build();
            List<T> list = csvToBean.parse();
            return list;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * @param list
     * @param type
     * @param <T>
     * @return
     */
    public <T> Result addList(List<T> list, TypeObject type) {
        try {
            FileWriter file_path = new FileWriter(ConfigurationUtil.getConfigurationEntry(CSV_PATH) + type + CSV, false);
            CSVWriter writer = new CSVWriter(file_path);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(list);
            writer.close();
            return new Result(COMPLETE);
        } catch (Exception e) {
            return new Result(NO);
        }
    }
}





