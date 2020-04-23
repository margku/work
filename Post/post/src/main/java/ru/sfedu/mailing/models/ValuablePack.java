package ru.sfedu.mailing.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mailing.TypeObject;

@Root(name = "ValuablePack")
public class ValuablePack extends Package {
    @CsvBindByPosition(position = 13)
    public long declaredValue;

    public ValuablePack() {

    }

    @Element(name = "declaredValue")
    public long getDeclaredValue() {
        return declaredValue;
    }

    @Element(name = "declaredValue")
    public void setDeclaredValue(long declaredValue) {
        this.declaredValue = declaredValue;
    }

    /**
     * @param id
     * @param sender
     * @param recipient
     * @param addressFrom
     * @param postOfficeFrom
     * @param addressTo
     * @param postOfficeTo
     * @param price
     * @param date
     * @param received
     * @param size
     * @param weight
     * @param fragile
     * @param declaredValue
     */
    public ValuablePack(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date, boolean received, int size, int weight, boolean fragile, long declaredValue) {
        super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, received, size, weight, fragile);
        this.declaredValue = declaredValue;
        this.setTypeObject(TypeObject.VALUABLEPACK);
    }

    /**
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
     */
    public ValuablePack(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile);
        this.declaredValue = declaredValue;
        this.setTypeObject(TypeObject.VALUABLEPACK);
    }

    @Override
    public String toString() {
        return "ValuablePack{" +
                " id=" + id +
                ",declaredValue=" + declaredValue +
                ", size=" + size +
                ", weight=" + weight +
                ", fragile=" + fragile +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", addressFrom='" + addressFrom + '\'' +
                ", postOfficeFrom=" + postOfficeFrom +
                ", addressTo='" + addressTo + '\'' +
                ", postOfficeTo=" + postOfficeTo +
                ", price=" + price +
                ", received=" + received +
                ", date='" + date + '\'' +
                '}';
    }
}
