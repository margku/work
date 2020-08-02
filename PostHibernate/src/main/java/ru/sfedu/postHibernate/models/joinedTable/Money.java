package ru.sfedu.postHibernate.models.joinedTable;
import javax.persistence.*;


@Entity(name = "Money")
@Table(schema = "joined")
@PrimaryKeyJoinColumn(name = "postObj_id")
public class Money extends PostObject {
  @Column(name = "sums", nullable = false)
  private int sum;
  @Column(name = "currency", nullable = false)
  private String currency;


  public Money(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency) {
    super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
    this.sum = sum;
    this.currency = currency;
  }

  public Money(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int sum, String currency) {
    super(sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
    this.sum = sum;
    this.currency = currency;

  }

  public Money() { };

  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }

  public String getCurrency() {
    return currency;
  }

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
