package ru.sfedu.mailing.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mailing.TypeObject;

@Root(name = "Mark")
public class Mark {
  @CsvBindByPosition(position = 0)
  private long id;
  @CsvBindByPosition(position = 1)
  private String name;
  @CsvBindByPosition(position = 2)
  private int price;


  public TypeObject getTypeObject() {
    return typeObject;
  }

  public void setTypeObject(TypeObject typeObject) {
    this.typeObject = typeObject;
  }

  private TypeObject typeObject = TypeObject.MARK;

  /**
   *
   * @param id
   * @param name
   * @param price
   */
  public Mark(long id, String name, int price) {
    this.id = id;
    this.name = name;
    this.price = price;

  }


  //
  // Constructors
  //
  public Mark () { };
  @Element(name = "id")
  public long getId() {
    return id;
  }
  @Element (name = "id")
  public void setId(long id) {
    this.id = id;
  }
  @Element (name = "name")
  public String getName() {
    return name;
  }
  @Element (name = "name")
  public void setName(String name) {
    this.name = name;
  }
  @Element (name = "price")
  public int getPrice() {
    return price;
  }
  @Element (name = "price")
  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Mark{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            '}';
  }
}
