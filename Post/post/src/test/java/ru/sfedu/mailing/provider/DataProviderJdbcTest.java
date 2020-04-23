package ru.sfedu.mailing.provider;

import org.junit.*;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;
import static ru.sfedu.mailing.Msg.COMPLETE;

public class DataProviderJdbcTest {

    IDataProvider dataProvider = new DataProviderJdbc();
    private int ID = 1;
    private int INT = 333;
    private String COUNTRY = "Россия";
    private String UPD = "new";
    private String SIZE = "68x67";
    private boolean FRAGILE = false;


    private static Long id;
    private static String address;
    private static String city;
    private static String nameClient;
    private static String currency;
    private static String date;

    private static long max = 100000;
    private static long markId;
    private static long clientId;
    private static long envelopeId;
    private static long postOfficeId;
    private static long postObjId;

    public static void setRandom() {
        String nameArr[] = {"Алексей", "Кирилл", "Анна", "Ксения", "Сергей", "Никита", "Наталья", "Мария", "Ольга", "Александра", "Дмитрий", "Иван", "Светлана", "Софья", "Михаил", "Пётр", "Юлия", "Дарья", "Павел", "Егор"};
        String currArr[] = {"Доллар США", "Евро", "Армянский драм", "Австралийский доллар", "Азербайджанский манат", "Болгарский лев", "Бразильский реал", "Белорусский рубль", "Канадский доллар", "Швейцарский франк", "Российский рубль"};
        String cityArr[] = {"Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Нижний Новгород", "Казань", "Челябинск", "Омск", "Самара", "Ростов-на-Дону", "Уфа", "Красноярск", "Воронеж", "Пермь", "Волгоград"};
        String addressArr[] = {"Центральная", "Молодёжная", "Школьная", "Советская", "Садовая", "Лесная", "Новая", "Ленина", "Мира", "Набережная"};
        Random random = new Random();

        nameClient = nameArr[random.nextInt(20)];
        city = cityArr[random.nextInt(11)];
        address = addressArr[random.nextInt(10)];
        currency = currArr[random.nextInt(10)];

        Date dt;
        long ms;
        ms = -946771200000L + (Math.abs(random.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        dt = new Date(ms);
        date = dt.toString();

        clientId = (long) (Math.random() * max);
        markId = (long) (Math.random() * max);
        envelopeId = (long) (Math.random() * max);
        postObjId = (long) (Math.random() * max);
        postOfficeId = (long) (Math.random() * max);

    }

    public static void setId() {
        Long idArr[] = {71L, 72L, 73L, 74L, 75L, 76L, 77L, 78L, 79L, 80L, 81L, 82L};
        Random random = new Random();
        int num = random.nextInt(12);
        id = idArr[num];
    }

    @BeforeClass
    public static void setUpClass() {
        setId();
        setRandom();
    }

    @Test
    public void ClientTest() {
        assertEquals(dataProvider.addClient(clientId, nameClient).getStatus(), COMPLETE);
        assertNotNull(dataProvider.getClient(clientId));
        assertEquals(dataProvider.updClient(clientId, UPD).getStatus(), COMPLETE);
        assertEquals(dataProvider.delClient(clientId).getStatus(), COMPLETE);

    }

    @Test
    public void PostOfficeTest() {
        assertEquals(dataProvider.addPostOffice(postOfficeId, COUNTRY, city, address).getStatus(), COMPLETE);
        assertNotNull(dataProvider.getPostOffice(postOfficeId));
        assertEquals(dataProvider.updPostOffice(postOfficeId, UPD, UPD, UPD).getStatus(), COMPLETE);
        assertEquals(dataProvider.delPostOffice(postOfficeId).getStatus(), COMPLETE);

    }

    @Test
    public void EnvelopeTest() {
        assertEquals(dataProvider.addEnvelope(envelopeId, SIZE, Math.toIntExact(id - 10)).getStatus(), COMPLETE);
        assertNotNull(dataProvider.getEnvelope(envelopeId));
        assertEquals(dataProvider.updEnvelope(envelopeId, UPD, INT).getStatus(), COMPLETE);
        assertEquals(dataProvider.delEnvelope(envelopeId).getStatus(), COMPLETE);
    }

    @Test
    public void MarkTest() {
        assertEquals(dataProvider.addMark(markId, "Mark" + id, Math.toIntExact(id - 10)).getStatus(), COMPLETE);
        assertNotNull(dataProvider.getMark(markId));
        assertEquals(dataProvider.updMark(markId, UPD, INT).getStatus(), COMPLETE);
        assertEquals(dataProvider.delMark(markId).getStatus(), COMPLETE);
    }


    @Test
    public void MoneyTest() {
        setUpClass();
        assertEquals(dataProvider.addMoney(postObjId, ID, ID, address, ID, address, ID, INT, date, INT, currency).getStatus(), COMPLETE);
        assertNotNull(dataProvider.getMoney(postObjId));
        assertEquals(dataProvider.updMoney(postObjId, ID, ID, UPD, ID, UPD, ID, INT, UPD, INT, UPD).getStatus(), COMPLETE);
        assertEquals(dataProvider.giveMoney(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.delMoney(postObjId).getStatus(), COMPLETE);
    }

    @Test
    public void ValPackTest() {
        assertEquals(dataProvider.addValuablePack(postObjId, ID, ID, address, ID, address, ID, INT, date, INT, INT, FRAGILE, INT).getStatus(), COMPLETE);
        assertNotNull(dataProvider.getValuablePack(postObjId));
        assertEquals(dataProvider.updValuablePack(postObjId, ID, ID, UPD, ID, UPD, ID, INT, UPD, INT, INT, FRAGILE, INT).getStatus(), COMPLETE);
        assertEquals(dataProvider.giveValuablePack(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.delValuablePack(postObjId).getStatus(), COMPLETE);
    }

    @Test
    public void PackageTest() {
        assertEquals(dataProvider.addPackage(postObjId, ID, ID, address, ID, address, ID, INT, date, INT, INT, FRAGILE).getStatus(), COMPLETE);
        assertNotNull(dataProvider.getPackage(postObjId));
        assertEquals(dataProvider.updPackage(postObjId, ID, ID, UPD, ID, UPD, ID, INT, UPD, INT, INT, FRAGILE).getStatus(), COMPLETE);
        assertEquals(dataProvider.givePackage(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.delPackage(postObjId).getStatus(), COMPLETE);
    }

    @Test
    public void LetterTest() {
        assertEquals(dataProvider.addLetter(postObjId, ID, ID, address, ID, address, ID, INT, date, INT, ID, INT, ID).getStatus(), COMPLETE);
        assertNotNull(dataProvider.getLetter(postObjId));
        assertEquals(dataProvider.updLetter(postObjId, ID, ID, UPD, ID, UPD, ID, INT, UPD, INT, ID, INT, ID).getStatus(), COMPLETE);
        assertEquals(dataProvider.giveLetter(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.delLetter(postObjId).getStatus(), COMPLETE);
    }

    @Test
    public void ClientHistoryTest() {
        assertEquals(dataProvider.addMoney(postObjId, ID, ID, address, ID, address, ID, INT, date, INT, currency).getStatus(), COMPLETE);
        assertEquals(dataProvider.addLetter(postObjId, ID, ID, address, ID, address, ID, INT, date, INT, ID, INT, ID).getStatus(), COMPLETE);
        assertEquals(dataProvider.addPackage(postObjId, ID, ID, address, ID, address, ID, INT, date, INT, INT, FRAGILE).getStatus(), COMPLETE);
        assertEquals(dataProvider.addValuablePack(postObjId, ID, ID, address, ID, address, ID, INT, date, INT, INT, FRAGILE, INT).getStatus(), COMPLETE);
        assertEquals(dataProvider.getClientHistory(ID).getStatus(), COMPLETE);
        assertEquals(dataProvider.giveMoney(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.delMoney(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.giveLetter(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.delLetter(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.givePackage(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.delPackage(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.giveValuablePack(postObjId).getStatus(), COMPLETE);
        assertEquals(dataProvider.delValuablePack(postObjId).getStatus(), COMPLETE);
    }

    @After
    public void delClass() {
        dataProvider.delClient(ID);
        dataProvider.delMark(ID);
        dataProvider.delEnvelope(ID);
        dataProvider.delPostOffice(ID);
    }
    }
