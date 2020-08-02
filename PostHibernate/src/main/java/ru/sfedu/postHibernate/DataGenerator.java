package ru.sfedu.postHibernate;

import ru.sfedu.postHibernate.provider.*;


public class DataGenerator {
    public void lab2() {
        DataProviderLab2 lab2 = new DataProviderLab2();
        for (int seq = 1; seq <= 10; seq++) {
            lab2.addClientLab("Client" + String.valueOf(seq));
        }
    }

    public void lab4() {
        DataProviderLab4 lab4 = new DataProviderLab4();
        for (int seq = 1; seq <= 10; seq++) {
            lab4.addClient("Client" + String.valueOf(seq), 1212 + seq * 100000, 888 + seq, "homeAddr" + String.valueOf(seq), "workAddr" + String.valueOf(seq), "passport" + seq, 567646 + seq);
        }
    }

    public void lab5() {
        DataProviderLab5 lab5 = new DataProviderLab5();
        for (int seq = 1; seq <= 10; seq++) {
            lab5.addClient("Client" + String.valueOf(seq), 7878 + seq, 33 + seq);
            lab5.addCompany("Company" + String.valueOf(seq));
            lab5.addLetter(seq, 23 + seq, 12 + seq, 7 + seq, 77 + seq, String.valueOf(seq) + "x" + String.valueOf(seq));
        }
    }

    public void mapped() {
        DataProviderMappedSuperclass lab3 = new DataProviderMappedSuperclass();
        for (int seq = 1; seq <= 10; seq++) {
            lab3.addMoney(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111 + seq, "d" + String.valueOf(seq), 500 + seq, "currency" + String.valueOf(seq));
            lab3.addLetter(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111 + seq, "d" + String.valueOf(seq), 77 + seq, 65+seq , seq, seq);
            lab3.addValuablePack(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111 + seq, "d" + String.valueOf(seq), 44+seq, 55+seq, true, 100+seq);
            }
    }
    public void single() {
        DataProviderSingleTable lab3 = new DataProviderSingleTable();
        for (long seq = 1; seq <= 10; seq++) {
            lab3.addMoney(10, 20, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40, 111, "d" + String.valueOf(seq), 500, "currency" + String.valueOf(seq)).getStatus();
            lab3.addLetter(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111, "d" + String.valueOf(seq), 77, 65+seq , (int) seq, seq);
            lab3.addValuablePack(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111, "d" + String.valueOf(seq), 44, 55, true, 100+seq);
        }
    }
    public void tablePerClass() {
        DataProviderTablePerClass lab3 = new DataProviderTablePerClass();
        for (long seq = 1; seq <= 10; seq++) {
            lab3.addMoney(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111, "d" + String.valueOf(seq), 500, "currency" + String.valueOf(seq));
            lab3.addLetter(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111, "d" + String.valueOf(seq), 77, 65+seq , 55, seq);
            lab3.addValuablePack(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111, "d" + String.valueOf(seq), 44, 55, true, 100+seq);
        }
    }
    public void joinedTable() {
        DataProviderJoined lab3 = new DataProviderJoined();
        for (long seq = 1; seq <= 10; seq++) {
            lab3.addMoney(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 11, "d" + String.valueOf(seq), 500, "currency" + String.valueOf(seq));
            lab3.addLetter(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111, "d" + String.valueOf(seq), 77, 65+seq , (int) seq, seq);
            lab3.addValuablePack(10 + seq, 20 + seq, "addressFrom" + String.valueOf(seq), 30 + seq, "addressTo" + String.valueOf(seq), 40 + seq, 111, "d" + String.valueOf(seq), 44, 55, true, 100+seq);
        }
    }
}
