package ru.sfedu.postHibernate.models.singleTable;


import javax.persistence.*;
@Table(schema = "single" , name = "singleTable")
@Entity(name = "PostObject")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.STRING)
public class PostObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public long sender;

    public long recipient;

    public String addressFrom;

    public long postOfficeFrom;

    public String addressTo;

    public long postOfficeTo;

    public int price;

    public String date;

    public boolean received = false;


    public PostObject(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date) {
        this.sender = sender;
        this.recipient = recipient;
        this.addressFrom = addressFrom;
        this.postOfficeFrom = postOfficeFrom;
        this.addressTo = addressTo;
        this.postOfficeTo = postOfficeTo;
        this.price = price;
        this.date = date;
        this.id = id;
    }

    public PostObject(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date) {
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


    public long getId() {
        return id;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public long getRecipient() {
        return recipient;
    }

    public void setRecipient(long recipient) {
        this.recipient = recipient;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getPostOfficeFrom() {
        return postOfficeFrom;
    }

    public void setPostOfficeFrom(long postOfficeFrom) {
        this.postOfficeFrom = postOfficeFrom;
    }

    public long getPostOfficeTo() {
        return postOfficeTo;
    }

    public void setPostOfficeTo(long postOfficeTo) {
        this.postOfficeTo = postOfficeTo;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
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
