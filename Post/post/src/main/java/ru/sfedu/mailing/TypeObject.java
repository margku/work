package ru.sfedu.mailing;

public enum TypeObject {

    HISTORY,
    POSTOFFICE,
    CLIENT,
    MARK,
    ENVELOPE,
    LETTER,
    PACKAGE,
    MONEY,
    VALUABLEPACK;

    public static String getCsvKey() {
        return "csvPath";
    }


    public String getXmlKey() {
        return "xmlPath";
    }
}
