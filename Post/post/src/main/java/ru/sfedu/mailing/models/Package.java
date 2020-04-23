package ru.sfedu.mailing.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mailing.TypeObject;

@Root(name = "Package")
public class Package extends PostObject {
  @CsvBindByPosition(position = 10)
  public int size;
  @CsvBindByPosition(position = 11)
  public int weight;
  @CsvBindByPosition(position = 12)
  public boolean fragile;

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
   * @param size
   * @param weight
   * @param fragile
   */
  public Package(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date, boolean received, int size, int weight, boolean fragile) {
    super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date, received);
    this.size = size;
    this.weight = weight;
    this.fragile = fragile;
    this.setTypeObject(TypeObject.PACKAGE);
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
   * @param size
   * @param weight
   * @param fragile
   */
  public Package(long id, Client sender, Client recipient, String addressFrom, PostOffice postOfficeFrom, String addressTo, PostOffice postOfficeTo, int price, String date, int size, int weight, boolean fragile) {
    super(id, sender, recipient, addressFrom, postOfficeFrom, addressTo, postOfficeTo, price, date);
    this.size = size;
    this.weight = weight;
    this.fragile = fragile;
    this.setTypeObject(TypeObject.PACKAGE);
  }

  public Package () { };
  @Element(name = "size")
  public int getSize() {
    return size;
  }
  @Element (name = "size")
  public void setSize(int size) {
    this.size = size;
  }
  @Element (name = "weight")
  public int getWeight() {
    return weight;
  }
  @Element (name = "weight")
  public void setWeight(int weight) {
    this.weight = weight;
  }
  @Element (name = "fragile")
  public boolean isFragile() {
    return fragile;
  }
  @Element (name = "fragile")
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
