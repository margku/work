package ru.sfedu.postHibernate.provider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.postHibernate.DataGenerator;
import ru.sfedu.postHibernate.Msg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class dataProviderLab2Test {
    DataProviderLab2 lab = new DataProviderLab2();


    @Before
    public void addClientLab() {
        DataGenerator data = new DataGenerator();
        data.lab2();
    }

    @Test
    public void ClientLab() {
        System.out.println(lab.getListOfCreatedTables());
        for (long seq = 1; seq <= 10; seq++) {
           assertNotNull(lab.getClientByName("Client" + String.valueOf(seq)).toString());
            assertNotNull(lab.getClientLab(seq).toString());
            assertNotNull(lab.getClientLab(seq).getMyFirstWashinmashine().toString());
            assertNotNull(lab.getClientLab(seq).getMySecondWashinmashine().toString());
        }
        for (long seq=1; seq<= 10; seq++) {
            assertEquals(lab.updClientLab(seq, "new", "new").getStatus(), Msg.COMPLETE);
        }
    }
    @After
    public void delClientLab() {
        for (long seq = 1; seq <= 10; seq++) {
            assertEquals(lab.delClientLab(seq).getStatus(), Msg.COMPLETE);
        }
    }

}