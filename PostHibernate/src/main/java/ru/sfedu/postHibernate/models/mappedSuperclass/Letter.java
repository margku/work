package ru.sfedu.postHibernate.models.mappedSuperclass;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "LetterMS")
@Table(schema = "Mapped" )
public class Letter extends PostObject {
    @Column(name = "weight", nullable = false)
    private int weight;
    @Column(name = "mark", nullable = false)
    private long mark;
    @Column(name = "mark_count", nullable = false)
    private int markCount;
    @Column(name = "envelope", nullable = false)
    private long envelope;

    //
    // Constructors
    //
    public Letter() {
    }

    public Letter(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope) {
        super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
        this.weight = weight;
        this.mark = mark;
        this.markCount = markCount;
        this.envelope = envelope;

    }
    public Letter(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int weight, long mark, int markCount, long envelope) {
        super(sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
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


    public int getMarkCount() {
        return markCount;
    }


    public void setMarkCount(int markCount) {
        this.markCount = markCount;
    }

    public long getMark() {
        return mark;
    }

    public void setMark(long mark) {
        this.mark = mark;
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


