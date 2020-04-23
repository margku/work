package ru.sfedu.mailing.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Element;
import ru.sfedu.mailing.TypeObject;

@Root(name = "Money")
public class Money extends PostObject {
  @CsvBindByPosition(position = 10)
  private int sum;
  @CsvBindByPosition(position = 11)
  private String currency;

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
   * @param sum
   * @param currency
   */
  public Money(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date, boolean received, int sum, String currency) {
    super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, received);
    this.sum = sum;
    this.currency = currency;
    this.setTypeObject(TypeObject.MONEY);
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
   * @param sum
   * @param currency
   */
  public Money(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date, int sum, String currency) {
    super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
    this.sum = sum;
    this.currency = currency;
    this.setTypeObject(TypeObject.MONEY);
  }

  public Money () { };
  @Element (name = "sum")
  public int getSum() {
    return sum;
  }
  @Element (name = "sum")
  public void setSum(int sum) {
    this.sum = sum;
  }
  @Element (name = "currency")
  public String getCurrency() {
    return currency;
  }
  @Element (name = "currency")
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public String toString() {
    return "Money{" +
            "id=" + id +
            ", sum=" + sum +
            ", currency=" + currency +
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
