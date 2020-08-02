package ru.sfedu.postHibernate.models.joinedTable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity(name =  "Package")
@Table(schema = "joined")
@PrimaryKeyJoinColumn(name = "postObj_id")
public class Package extends PostObject {

  public int size;

  public int weight;

  public boolean fragile;


  public Package(long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile) {
    super(sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
    this.size = size;
    this.weight = weight;
    this.fragile = fragile;
  }
  public Package(long id, long sender, long recipient, String addressFrom, long postOfficeFrom, String addressTo, long postOfficeTo, int price, String date, int size, int weight, boolean fragile) {
    super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
    this.size = size;
    this.weight = weight;
    this.fragile = fragile;
  }

  public Package() { };

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public boolean isFragile() {
    return fragile;
  }

  public void setFragile(boolean fragile) {
    this.fragile = fragile;
  }

  @Override
  public String toString() {
    return "Package{" +
            " id=" + id +
            ",size=" + size +
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
