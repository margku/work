package ru.sfedu.postHibernate.provider;

import org.junit.Before;
import org.junit.Test;
import ru.sfedu.postHibernate.DataGenerator;

import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;
import static ru.sfedu.postHibernate.Msg.COMPLETE;

public class DataProviderJoinedTest {

    DataProviderJoined hibernate = new DataProviderJoined();
    private int INT = 333;
    private String UPD = "new";


    @Before
    public void add() {
        DataGenerator data = new DataGenerator();
        data.joinedTable();
    }

    @Test
    public void MoneyTest() {
//        System.out.println(lab3.getListOfCreatedTables());
        for (long seq=1; seq<= 10; seq = seq +3) {
            assertNotNull(hibernate.getMoney(seq).toString());
            assertEquals(hibernate.updMoney(seq, INT, INT, UPD, INT, UPD, INT, INT, UPD, INT, UPD).getStatus(), COMPLETE);
            assertEquals(hibernate.delMoney(seq).getStatus(), COMPLETE);
        }
    }

    @Test
    public void ValPackTest() {
        for (int seq=3; seq<= 10;seq = seq +3) {
            assertNotNull(hibernate.getValPack(seq).toString());
            assertEquals(hibernate.updValuablePack(seq, INT, INT, UPD, INT, UPD, INT, INT, UPD, INT, INT, false, INT).getStatus(), COMPLETE);
            assertEquals(hibernate.delValPack(seq).getStatus(), COMPLETE);
        }
    }

    @Test
    public void LetterTest() {
        for (int seq = 2; seq <= 10; seq = seq +3) {
            assertNotNull(hibernate.getLetter(seq).toString());
            assertEquals(hibernate.updLetter(seq, INT, INT, UPD, INT, UPD, INT, INT, UPD, INT, INT, INT, INT).getStatus(), COMPLETE);
            assertEquals(hibernate.delLetter(seq).getStatus(), COMPLETE);
        }
    }
}