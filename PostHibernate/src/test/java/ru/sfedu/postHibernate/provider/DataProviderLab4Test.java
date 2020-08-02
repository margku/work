package ru.sfedu.postHibernate.provider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.postHibernate.DataGenerator;
import ru.sfedu.postHibernate.Msg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class DataProviderLab4Test {
    DataProviderLab4 lab4 = new DataProviderLab4();
    private long ID = 1;
    private int INT = 333;
    private String UPD = "new";

    @Before
    public void addClient() {
        DataGenerator data = new DataGenerator();
        data.lab4();
    }

    @Test
    public void Client() {
        System.out.println(lab4.getListOfCreatedTables());
        for (long seq = 1; seq <= 10; seq++) {
                assertNotNull(lab4.getClientByName("Client" + String.valueOf(seq)).toString());
            assertNotNull(lab4.getClient(seq).toString());
            assertNotNull(lab4.getClient(seq).getAddress().toString());
            assertNotNull(lab4.getClient(seq).getNumber().toString());
            assertNotNull(lab4.getClient(seq).getDocument().toString());
        }
        for (long seq = 1; seq <= 10; seq++) {
            assertEquals(lab4.updClient(seq, "new", INT, INT, "new", "new", "new", INT).getStatus(), Msg.COMPLETE);
        }
}

@After
public void delClient() {
    for (long seq = 1; seq <= 10; seq++) {
        assertEquals(lab4.delClient(seq).getStatus(), Msg.COMPLETE);
    }
}
}