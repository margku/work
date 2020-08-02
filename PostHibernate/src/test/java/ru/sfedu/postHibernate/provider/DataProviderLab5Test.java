package ru.sfedu.postHibernate.provider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.postHibernate.DataGenerator;
import static org.junit.Assert.*;
import static ru.sfedu.postHibernate.Msg.COMPLETE;

public class DataProviderLab5Test {
    DataProviderLab5 lab5 = new DataProviderLab5();
    private int INT = 333;



    @Before
    public void add() {
        DataGenerator data = new DataGenerator();
        data.lab5();
    }

    @Test
    public void get() {
        System.out.println(lab5.getListOfCreatedTables());
        for (int seq = 1; seq <= 10; seq++) {
            assertNotNull(lab5.getClientByName("Client" + String.valueOf(seq)).toString());
            assertNotNull(lab5.getCompanyByName("Company" + String.valueOf(seq)).toString());
            assertNotNull(lab5.getClient(seq).toString());
            assertNotNull(lab5.getClient(seq).getDocument().toString());
            assertNotNull(lab5.getClient(seq).getLetters().toString());
            assertNotNull(lab5.getLetter(seq).toString());
        }
    }
    @After
    public void del() {
        for (int seq = 1; seq <= 10; seq++) {
            assertEquals(lab5.delClient(seq).getStatus(), COMPLETE);
            assertEquals(lab5.delCompany(seq).getStatus(), COMPLETE);
        }
    }
}
