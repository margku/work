package ru.sfedu.mailing.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.mailing.*;
import ru.sfedu.mailing.models.Package;
import ru.sfedu.mailing.utils.*;
import ru.sfedu.mailing.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.sfedu.mailing.Constants.*;
import static ru.sfedu.mailing.Constants.DEL_COMPLETE;
import static ru.sfedu.mailing.Msg.COMPLETE;
import static ru.sfedu.mailing.Msg.NO;
import static ru.sfedu.mailing.TypeObject.*;
import static ru.sfedu.mailing.TypeObject.MARK;

public class DataProviderXML implements IDataProvider {


    public static Logger log = LogManager.getLogger(DataProviderXML.class);


    //    add methods

    /**
     *
     * @return
     */
    public Result checkClient() {
        try {
            File clientFile= new File(ConfigurationUtil.getConfigurationEntry(XML_PATH) + TypeObject.CLIENT + XML);
            if (clientFile.length() == 0 || clientFile.length() == 10) {
                return addClient(DEF_ID, DEF_NAME);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @return
     */
    public Result checkMark() {
        try {
            File markFile= new File(ConfigurationUtil.getConfigurationEntry(XML_PATH) + MARK + XML);
            if (markFile.length() == 0 || markFile.length() == 10) {
                return addMark(DEF_ID, DEF_NAME, DEF_PRICE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @return
     */
    public Result checkEnvelope() {
        try {
            File file= new File(ConfigurationUtil.getConfigurationEntry(XML_PATH) + TypeObject.ENVELOPE + XML);
            if (file.length() == 0 || file.length() == 10) {
                return addEnvelope(DEF_ID, DEF_SIZE, DEF_PRICE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @return
     */
    public Result checkPostOffice() {
        try {
            File file= new File(ConfigurationUtil.getConfigurationEntry(XML_PATH) + POSTOFFICE + XML);
            if (file.length() == 0 || file.length() == 10) {
                return addPostOffice(DEF_ID, DEF_COUNTRY, DEF_CITY, DEF_ADDRESS);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @param officeId
     * @param country
     * @param city
     * @param address
     * @return
     */
    public Result addPostOffice(long officeId, String country, String city, String address) {
        PostOffice postOffice = new PostOffice(officeId, country, city, address);
        ArrayList<PostOffice> postOffices = new ArrayList<>();
        try {
            boolean check = getPostOffice(officeId) == null;
            postOffices.addAll(read(POSTOFFICE));
            postOffices.add(postOffice);
            if (check) {
                add(postOffices, POSTOFFICE);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @param id
     * @param name
     * @return
     */
    public Result addClient(long id, String name) {
        Client client = new Client(id, name);
        List<Client> addclients = new ArrayList<>();
        addclients.add(client);
        ArrayList<Client> clients = new ArrayList<>();
        try {
            boolean check = (getClient(id) == null);
            clients.addAll(read(TypeObject.CLIENT));
            clients.addAll(addclients);
            if (check) {
                add(clients, TypeObject.CLIENT);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @param id
     * @param size
     * @param price
     * @return
     */
    public Result addEnvelope(long id, String size, int price) {
        Envelope envelope = new Envelope(id, size, price);
        List<Envelope> addenvelopes = new ArrayList<>();
        addenvelopes.add(envelope);
        ArrayList<Envelope> envelopes = new ArrayList<>();
        try {
            boolean check = (getEnvelope(id) == null);
            envelopes.addAll(read(TypeObject.ENVELOPE));
            envelopes.add(envelope);
            if (check) {
                add(envelopes, TypeObject.ENVELOPE);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

    /**
     *
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
                ArrayList<Package> packages = new ArrayList<>();
                packages.add(pack);
                packages.addAll(packages);
                add(packages, PACKAGE);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

    /**
     *
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
                ArrayList<Money> monies = new ArrayList<>();
                monies.addAll(read(MONEY));
                monies.add(money);
                add(monies, MONEY);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

    /**
     *
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
                ArrayList<ValuablePack> valuablePacks = new ArrayList<>();
                valuablePacks.addAll(read(VALUABLEPACK));
                valuablePacks.add(valuablePack);
                add(valuablePacks, VALUABLEPACK);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @param id
     * @param name
     * @param price
     * @return
     */
    public Result addMark(long id, String name, int price) {
        Mark mark = new Mark(id, name, price);
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            marks.addAll(read(MARK));
            marks.add(mark);
            if (getMark(id) == null) {
                add(marks, MARK);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

    /**
     *
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
        checkPostOffice();
        checkEnvelope();
        checkMark();
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
                ArrayList<Letter> letters = new ArrayList<>();
                letters.addAll(read(LETTER));
                letters.add(letter);
                add(letters, LETTER);
                return new Result(COMPLETE, IDP_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @param id
     * @return
     */
    public Money getMoney(long id) {
        try {
            List<Money> monies = read(MONEY);
            return monies.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Package getPackage(long id) {
        try {
            List<Package> packages = read(PACKAGE);
            return packages.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Client getClient(long id) {
        try {
            List<Client> clients = read(TypeObject.CLIENT);
            return clients.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public PostOffice getPostOffice(long id) {
        try {
            List<PostOffice> list = read(POSTOFFICE);
            return list.stream().filter(b -> b.getOfficeId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Result delEnvelope(long id) {
        try {
            List<Envelope> envelopes = read(TypeObject.ENVELOPE);
            List<Letter> letters = read(LETTER);
            boolean check = letters.stream().anyMatch(a -> a.getEnvelope().getId() == id);
            if (check) return new Result(NO, Constants.DEL_FAIL);
            envelopes.removeIf(aEnv -> aEnv.getId() == id);
            if (add(envelopes, TypeObject.ENVELOPE).getStatus() == COMPLETE) {
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_FAIL);
    }

    /**
     *
     * @param id
     * @return
     */

    public Result delPostOffice(long id) {
        try {
            List<PostOffice> postOffices = read(POSTOFFICE);
            List<Letter> letters = read(LETTER);
            List<Package> packages = read(PACKAGE);
            List<Money> monies = read(MONEY);
            List<ValuablePack> valuablePacks = read(VALUABLEPACK);
            boolean checkLet = (letters.stream().anyMatch(a -> a.getPostOfficeTo().getOfficeId() == id || a.getPostOfficeFrom().getOfficeId() == id));
            boolean checkMon = (monies.stream().anyMatch(a -> a.getPostOfficeTo().getOfficeId() == id || a.getPostOfficeFrom().getOfficeId() == id));
            boolean checkPack = (packages.stream().anyMatch(a -> a.getPostOfficeTo().getOfficeId() == id || a.getPostOfficeFrom().getOfficeId() == id));
            boolean checkVPack = (valuablePacks.stream().anyMatch(a -> a.getPostOfficeTo().getOfficeId() == id || a.getPostOfficeFrom().getOfficeId() == id));
            if (checkLet || checkMon || checkPack || checkVPack) return new Result(NO, Constants.DEL_FAIL);
            postOffices.removeIf(aEnv -> aEnv.getOfficeId() == id);
            if (add(postOffices, POSTOFFICE).getStatus() == COMPLETE) {
                return new Result(COMPLETE, DEL_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_FAIL);
    }

    /**
     *
     * @param id
     * @return
     */
    public Result delClient(long id) {
        try {
            List<Client> clients = read(TypeObject.CLIENT);
            List<Letter> letters = read(LETTER);
            List<Package> packages = read(PACKAGE);
            List<Money> monies = read(MONEY);
            List<ValuablePack> valuablePacks = read(VALUABLEPACK);
            boolean checkLet = (letters.stream().anyMatch(a -> a.getRecipient().getId() == id || a.getSender().getId() == id));
            boolean checkMon = (monies.stream().anyMatch(a -> a.getRecipient().getId() == id || a.getSender().getId() == id));
            boolean checkPack = (packages.stream().anyMatch(a -> a.getSender().getId() == id || a.getRecipient().getId() == id));
            boolean checkVPack = (valuablePacks.stream().anyMatch(a -> a.getRecipient().getId() == id || a.getSender().getId() == id));
            if (checkLet || checkMon || checkPack || checkVPack) return new Result(NO, Constants.DEL_FAIL);
            clients.removeIf(aClient -> aClient.getId() == id);
            if (add(clients, TypeObject.CLIENT).getStatus() == COMPLETE) {
                return new Result(COMPLETE, DEL_COMPLETE);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_FAIL);
    }

    /**
     *
     * @param id
     * @return
     */

    public Result delLetter(long id) {
        try {
            List<Letter> letters = read(LETTER);
            boolean check = letters.stream().anyMatch(a -> a.getId() == id && a.isReceived() == true);
            if (check) {
                letters.removeIf(a -> a.getId() == id);
                add(letters, LETTER);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     *
     * @param id
     * @return
     */
    public Result delPackage(long id) {
        try {
            List<Package> packages = read(PACKAGE);
            boolean check = packages.stream().anyMatch(a -> a.getId() == id && a.isReceived() == true);
            if (check) {
                packages.removeIf(aMoney -> aMoney.getId() == id);
                add(packages, PACKAGE);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     *
     * @param id
     * @return
     */
    public Result delValuablePack(long id) {
        try {
            List<ValuablePack> valuablePacks = read(VALUABLEPACK);
            boolean check = valuablePacks.stream().anyMatch(a -> a.getId() == id && a.isReceived() == true);
            if (check) {
                valuablePacks.removeIf(aMoney -> aMoney.getId() == id);
                add(valuablePacks, VALUABLEPACK);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     *
     * @param id
     * @return
     */
    public Result delMoney(long id) {
        try {
            List<Money> moneyList = read(TypeObject.MONEY);
            boolean check = moneyList.stream().anyMatch(a -> a.getId() == id && a.isReceived() == true);
            if (check) {
                moneyList.removeIf(aMoney -> aMoney.getId() == id);
                add(moneyList, MONEY);
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, DEL_FAIL_NOT_RECEIVED);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, IDP_FAIL);
    }

    /**
     *
     * @param id
     * @return
     */
    public Result delMark(long id) {
        try {
            List<Mark> marksList = read(MARK);
            List<Letter> letters = read(LETTER);
            boolean check = letters.stream().anyMatch(a -> a.getMark().getId() == id);
            if (check) return new Result(NO, Constants.DEL_FAIL);
            marksList.removeIf(aMark -> aMark.getId() == id);
            add(letters, LETTER);
            if (add(marksList, MARK).getStatus() == COMPLETE) {
                return new Result(COMPLETE, DEL_COMPLETE);
            } else return new Result(Msg.NO, IDP_READ_ERROR);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_FAIL);
    }

    /**
     *
     * @param id
     * @return
     */
    public ValuablePack getValuablePack(long id) {
        try {
            List<ValuablePack> valuablePacks = read(VALUABLEPACK);
            return valuablePacks.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Letter getLetter(long id) {
        try {
            List<Letter> letters = read(LETTER);
            return letters.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */

    public Envelope getEnvelope(long id) {
        try {
            List<Envelope> envelopes = read(TypeObject.ENVELOPE);
            return envelopes.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Mark getMark(long id) {
        try {
            List<Mark> markList = read(MARK);
            return markList.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     *
     * @param bean
     * @param typeObject
     * @return
     */

    public Result add(List<?> bean, TypeObject typeObject) {
        try {
            FileWriter writer = new FileWriter(ConfigurationUtil.getConfigurationEntry(XML_PATH) + typeObject + XML);
            Serializer serializer = new Persister();
            ListXML xmlWrap = new ListXML();
            xmlWrap.setList(bean);
            serializer.write(xmlWrap, writer);
            return new Result(COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO, Constants.IDP_COMPLETE);
    }

    /**
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> List read(TypeObject type) {
        try {
            FileReader fileReader = new FileReader(ConfigurationUtil.getConfigurationEntry(XML_PATH) + type + XML);
            Serializer serializer = new Persister();
            ListXML xml = serializer.read(ListXML.class, fileReader);
            if (xml.getList() == null) {
                xml.setList(Collections.emptyList());
            }
            return xml.getList();
        } catch (Exception e) {
            log.error(e);
        }
        return Collections.emptyList();
    }

    /**
     *
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
     *
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

    public Result updLetter(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope) {
        try {
            List<Letter> list = read(LETTER);
            list.removeIf(a -> a.getId() == id);
            add(list, LETTER);
           return addLetter(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, weight, mark, markCount, envelope);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @param id
     * @param name
     * @param price
     * @return
     */
    @Override
    public Result updMark(long id, String name, int price) {
        try {
            if (delMark(id).getStatus() == COMPLETE && addMark(id, name, price).getStatus() == COMPLETE)
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_FAIL);
    }

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
    public Result updMoney(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency) {
        try {
            List<Money> list = read(MONEY);
            list.removeIf(a -> a.getId() == id);
            add(list, MONEY);
        return    addMoney( id,  sender,  recipient,  addressFrom,  postOfficeFrom,  addressTo,  postOfficeTo,  price,  date,  sum,  currency);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

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
    public Result updPackage(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile) {
        try {
            List<Package> list = read(PACKAGE);
            list.removeIf(a -> a.getId() == id);
            add(list, PACKAGE);
           return addPackage( id,  sender,  recipient,  addressFrom,  postOfficeFrom,  addressTo,  postOfficeTo,  price,  date,  size,  weight,  fragile);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }

    /**
     *
     * @param id
     * @param country
     * @param city
     * @param address
     * @return
     */

    public Result updPostOffice(long id, String country, String city, String address) {
        try {
            if (delPostOffice(id).getStatus() == COMPLETE && addPostOffice(id, country, city, address).getStatus() == COMPLETE)
                return new Result(Msg.COMPLETE, IDP_UPD_COMPLETE);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_FAIL);
    }

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
    public Result updValuablePack(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        try {
            List<ValuablePack> list = read(VALUABLEPACK);
            list.removeIf(a -> a.getId() == id);
            add(list, VALUABLEPACK);
       return      addValuablePack( id,  sender,  recipient,  addressFrom,  postOfficeFrom,  addressTo,  postOfficeTo,  price,  date,  size,  weight,  fragile,  declaredValue);

        } catch (Exception e) {
            log.error(e);
        }
        return new Result(Msg.NO, IDP_ADD_FAIL);
    }


//---------------------USE-CASE methods

    /**
     *
     * @param id
     * @return
     */
    public Result getClientHistory(long id) {
        try {
            List<Letter> letters = read(LETTER);
            List<Money> monies = read(MONEY);
            List<Package> packages = read(PACKAGE);
            List<ValuablePack> valuablePacks = read(VALUABLEPACK);
            letters.removeIf(letter -> letter.getRecipient().getId() != id || letter.getSender().getId() != id);
            monies.removeIf(money -> money.getRecipient().getId() != id || money.getSender().getId() != id);
            packages.removeIf(pack -> pack.getRecipient().getId() != id || pack.getSender().getId() != id);
            valuablePacks.removeIf(pack -> pack.getRecipient().getId() != id || pack.getSender().getId() != id);
            String str = monies.toString() + "\n" + packages.toString() + "\n" + letters.toString() + "\n" + valuablePacks.toString();
            return new Result(COMPLETE, str);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     *
     * @param id
     * @return
     */
    public Result giveLetter(long id) {
        try {
            List<Letter> letters = read(LETTER);
            List<Letter> newletters = read(LETTER);
            newletters.removeIf(a -> a.getId() != id);
            newletters.stream().filter(l -> l.getId() == id).findFirst().ifPresent(l -> {
                l.setReceived(true);
            });
            letters.removeIf(a -> a.getId() == id);
            letters.addAll(newletters);
            add(letters, LETTER);
            return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     *
     * @param id
     * @return
     */
    public Result givePackage(long id) {
        try {
            List<Package> packages = read(PACKAGE);
            List<Package> newpackages = read(PACKAGE);
            newpackages.removeIf(a -> a.getId() != id);
            newpackages.stream().filter(l -> l.getId() == id).findFirst().ifPresent(l -> {
                l.setReceived(true);
            });
            packages.removeIf(a -> a.getId() == id);
            packages.addAll(newpackages);
            add(packages, PACKAGE);
            return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     *
     * @param id
     * @return
     */
    public Result giveMoney(long id) {
        try {
            List<Money> monies = read(MONEY);
            List<Money> monies1 = read(MONEY);
            monies1.removeIf(a -> a.getId() != id);
            monies1.stream().filter(l -> l.getId() == id).findFirst().ifPresent(l -> {
                l.setReceived(true);
            });
            monies.removeIf(a -> a.getId() == id);
            monies.addAll(monies1);
            add(monies, MONEY);
            return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }

    /**
     *
     * @param id
     * @return
     */
    public Result giveValuablePack(long id) {
        try {
            List<ValuablePack> valuablePacks = read(VALUABLEPACK);
            List<ValuablePack> valuablePacks1 = read(VALUABLEPACK);
            valuablePacks1.removeIf(a -> a.getId() != id);
            valuablePacks1.stream().filter(l -> l.getId() == id).findFirst().ifPresent(l -> {
                l.setReceived(true);
            });
            valuablePacks.removeIf(a -> a.getId() == id);
            valuablePacks.addAll(valuablePacks1);
            add(valuablePacks, VALUABLEPACK);
            return new Result(COMPLETE, GIVE_P_O);
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(NO);
    }
}