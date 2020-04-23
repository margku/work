package ru.sfedu.mailing.models;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mailing.models.Converter.EnvelopeConverter;
import ru.sfedu.mailing.models.Converter.MarkConverter;
import ru.sfedu.mailing.TypeObject;

@Root(name = "Letter")
public class Letter extends PostObject {
    @CsvBindByPosition(position = 10)
    private int weight;
    @CsvCustomBindByPosition(position = 11, converter = MarkConverter.class)
    private Mark mark;
    @CsvBindByPosition(position = 12)
    private int markCount;
    @CsvCustomBindByPosition(position = 13, converter = EnvelopeConverter.class)
    private Envelope envelope;

    //
    // Constructors
    //
    public Letter() {
    }

    /**
     *
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
     * @param weight
     * @param mark
     * @param markCount
     * @param envelope
     */
    public Letter(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date, boolean received, int weight, Mark mark, int markCount, Envelope envelope) {
        super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, received);
        this.weight = weight;
        this.mark = mark;
        this.markCount = markCount;
        this.envelope = envelope;
        this.setTypeObject(TypeObject.LETTER);
    }

    /**
     *
     * @param id
     * @param sender
     * @param recipient
     * @param addressFrom
     * @param postOfficeFrom
     * @param addressTo
     * @param postOfficeTo
     * @param price
     * @param date
     * @param weight
     * @param mark
     * @param markCount
     * @param envelope
     */
    public Letter(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date, int weight, Mark mark, int markCount, Envelope envelope) {
        super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
        this.weight = weight;
        this.mark = mark;
        this.markCount = markCount;
        this.envelope = envelope;
        this.setTypeObject(TypeObject.LETTER);

    }


    @Element(name = "weight")

    public int getWeight() {
        return weight;
    }

    @Element(name = "weight")
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Element(name = "mark")
    public Mark getMark() {
        return mark;
    }

    @Element(name = "mark")
    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Element(name = "markCount")
    public int getMarkCount() {
        return markCount;
    }

    @Element(name = "markCount")
    public void setMarkCount(int markCount) {
        this.markCount = markCount;
    }

    @Element(name = "envelope")
    public Envelope getEnvelope() {
        return envelope;
    }

    @Element(name = "envelope")
    public void setEnvelope(Envelope envelope) {
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


