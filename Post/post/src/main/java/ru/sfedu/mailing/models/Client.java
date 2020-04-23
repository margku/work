package ru.sfedu.mailing.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mailing.TypeObject;

@Root(name = "Client")
public class Client {
    @CsvBindByPosition(position = 0)
    private long id;
    @CsvBindByPosition(position = 1)
    private String name;

    private TypeObject typeObject = TypeObject.CLIENT;

    public Client() {
    }

    /**
     *
     * @param id
     * @param name
     */
    public Client(long id, String name) {
        this.name = name;
        this.id = id;
    }

    @Element(name = "id")
    public long getId() {
        return id;
    }

    @Element(name = "id")
    public void setId(long id) {
        this.id = id;
    }

    @Element(name = "name")
    public String getName() {
        return name;
    }

    @Element(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(TypeObject typeObject) {
        this.typeObject = typeObject;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

