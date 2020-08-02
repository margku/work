package ru.sfedu.postHibernate.models.joinedTable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "Letter")
@Table(schema = "joined")
@PrimaryKeyJoinColumn(name = "postObj_id")


public class Letter extends PostObject {

    private int weight;

    private long mark;

    private int markCount;

    private long envelope;

    //
    // Constructors
    //
    public Letter() {
    }


    public Letter(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope) {
        super(sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
        this.weight = weight;
        this.mark = mark;
        this.markCount = markCount;
        this.envelope = envelope;

    }

    public Letter(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope) {
        super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
        this.weight = weight;
        this.mark = mark;
        this.markCount = markCount;
        this.envelope = envelope;

    }
    public int getWeight() {
        return weight;
    }


    public void setWeight(int weight) {
        this.weight = weight;
    }


    public long getMark() {
        return mark;
    }


    public void setMark(long mark) {
        this.mark = mark;
    }


    public int getMarkCount() {
        return markCount;
    }


    public void setMarkCount(int markCount) {
        this.markCount = markCount;
    }


    public long getEnvelope() {
        return envelope;
    }

    public void setEnvelope(long envelope) {
        this.envelope = envelope;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ",weight=" + weight +
                ", mark=" + mark +
                ", markCount=" + markCount +
                ", envelope=" + envelope +
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


