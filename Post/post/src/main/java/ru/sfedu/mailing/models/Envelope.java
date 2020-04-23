package ru.sfedu.mailing.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Element;
import ru.sfedu.mailing.TypeObject;

@Root(name = "Envelope")
public class Envelope {
    @CsvBindByPosition(position = 0)
    private long id;
    @CsvBindByPosition(position = 1)
    private String size;
    @CsvBindByPosition(position = 2)
    private int price;
    private TypeObject typeObject = TypeObject.ENVELOPE;

    public Envelope() {
    }

    /**
     *
     * @param id
     * @param size
     * @param price
     */
    public Envelope(long id, String size, int price) {
        this.id = id;
        this.price = price;
        this.size = size;
    }

    @Element(name = "id")
    public long getId() {
        return id;
    }

    @Element(name = "id")
    public void setId(long id) {
        this.id = id;
    }

    @Element(name = "size")
    public String getSize() {
        return size;
    }

    @Element(name = "size")
    public void setSize(String size) {
        this.size = size;
    }

    @Element(name = "price")
    public int getPrice() {
        return price;
    }

    @Element(name = "price")
    public void setPrice(int price) {
        this.price = price;
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(TypeObject typeObject) {
        this.typeObject = typeObject;
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "id=" + id +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
