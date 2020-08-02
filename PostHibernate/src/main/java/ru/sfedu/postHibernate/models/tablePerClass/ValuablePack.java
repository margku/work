package ru.sfedu.postHibernate.models.tablePerClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity (name =  "ValuablePack")
@Table(schema = "TPC")
public class ValuablePack extends Package {
    @Column(name = "declaredValue", nullable = false)
    public long declaredValue;

    public ValuablePack() {

    }
    public long getDeclaredValue() {
        return declaredValue;
    }
    public void setDeclaredValue(long declaredValue) {
        this.declaredValue = declaredValue;
    }

    public ValuablePack(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile);
        this.declaredValue = declaredValue;
    }
    public ValuablePack(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile, long declaredValue) {
        super(sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, size, weight, fragile);
        this.declaredValue = declaredValue;
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
