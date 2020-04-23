package ru.sfedu.mailing.models;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mailing.models.Converter.ClientConverter;
import ru.sfedu.mailing.models.Converter.PostOfficeConverter;
import ru.sfedu.mailing.TypeObject;

@Root(name = "PostObject")
public class PostObject {

    @CsvBindByPosition(position = 0)
    public long id;
    @CsvCustomBindByPosition(position = 1, converter = ClientConverter.class)
    public Client sender;
    @CsvCustomBindByPosition(position = 2, converter = ClientConverter.class)
    public Client recipient;
    @CsvBindByPosition(position = 3)
    public String addressFrom;
    @CsvCustomBindByPosition(position = 4, converter = PostOfficeConverter.class)
    public PostOffice postOfficeFrom;
    @CsvBindByPosition(position = 5)
    public String addressTo;
    @CsvCustomBindByPosition(position = 6, converter = PostOfficeConverter.class)
    public PostOffice postOfficeTo;
    @CsvBindByPosition(position = 7)
    public int price;
    @CsvBindByPosition(position = 8)
    public String date;
    @CsvBindByPosition(position = 9)
    public boolean received = false;


    public TypeObject typeObject;

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
     */
    public PostObject(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date, boolean received) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.addressFrom = addressFrom;
        this.postOfficeFrom = postOfficeFrom;
        this.addressTo = addressTo;
        this.postOfficeTo = postOfficeTo;
        this.price = price;
        this.date = date;
        this.received = received;
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
     */
    public PostObject(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.addressFrom = addressFrom;
        this.postOfficeFrom = postOfficeFrom;
        this.addressTo = addressTo;
        this.postOfficeTo = postOfficeTo;
        this.price = price;
        this.date = date;
    }

    //
    // Constructors
    //
    public PostObject() {
    }

    ;

    @Element(name = "id")
    public long getId() {
        return id;
    }

    @Element(name = "id")
    public void setId(long id) {
        this.id = id;
    }

    @Element(name = "sender")
    public Client getSender() {
        return sender;
    }

    @Element(name = "sender")
    public void setSender(Client sender) {
        this.sender = sender;
    }

    @Element(name = "recipient")
    public Client getRecipient() {
        return recipient;
    }

    @Element(name = "recipient")
    public void setRecipient(Client recipient) {
        this.recipient = recipient;
    }

    @Element(name = "addressFrom")
    public String getAddressFrom() {
        return addressFrom;
    }

    @Element(name = "addressFrom")
    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    @Element(name = "addressTo")
    public String getAddressTo() {
        return addressTo;
    }

    @Element(name = "addressTo")
    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    @Element(name = "price")
    public int getPrice() {
        return price;
    }

    @Element(name = "price")
    public void setPrice(int price) {
        this.price = price;
    }

    @Element(name = "date")
    public String getDate() {
        return date;
    }

    @Element(name = "date")
    public void setDate(String date) {
        this.date = date;
    }

    @Element(name = "postOfficeFrom")
    public PostOffice getPostOfficeFrom() {
        return postOfficeFrom;
    }

    @Element(name = "postOfficeFrom")
    public void setPostOfficeFrom(PostOffice postOfficeFrom) {
        this.postOfficeFrom = postOfficeFrom;
    }

    @Element(name = "postOfficeTo")
    public PostOffice getPostOfficeTo() {
        return postOfficeTo;
    }

    @Element(name = "postOfficeTo")
    public void setPostOfficeTo(PostOffice postOfficeTo) {
        this.postOfficeTo = postOfficeTo;
    }

    @Element(name = "received")
    public boolean isReceived() {
        return received;
    }

    @Element(name = "received")
    public void setReceived(boolean received) {
        this.received = received;
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(TypeObject typeObject) {
        this.typeObject = typeObject;
    }
    
    @Override
    public String toString() {
        return "PostObject{" +
                "id=" + id +
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
