package ru.sfedu.mailing.models;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mailing.TypeObject;

@Root(name = "PostOffice")
public class PostOffice {
    @CsvBindByPosition(position = 0)
    private long officeId;
    @CsvBindByPosition(position = 1)
    private String country;
    @CsvBindByPosition(position = 2)
    private String city;
    @CsvBindByPosition(position = 3)
    private String address;

    private TypeObject typeObject = TypeObject.POSTOFFICE;

    public PostOffice() {
    }

    /**
     *
     * @param officeId
     * @param country
     * @param city
     * @param address
     */
    public PostOffice(long officeId, String country, String city, String address) {
        this.officeId = officeId;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    @Element(name = "OfficeId")
    public long getOfficeId() {
        return officeId;
    }

    @Element(name = "OfficeId")
    public void setOfficeId(long officeId) {
        this.officeId = officeId;
    }

    @Element(name = "country")
    public String getCountry() {
        return country;
    }

    @Element(name = "country")
    public void setCountry(String country) {
        this.country = country;
    }

    @Element(name = "city")
    public String getCity() {
        return city;
    }

    @Element(name = "city")
    public void setCity(String city) {
        this.city = city;
    }

    @Element(name = "address")
    public String getAddress() {
        return address;
    }

    @Element(name = "address")
    public void setAddress(String address) {
        this.address = address;
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(TypeObject typeObject) {
        this.typeObject = typeObject;
    }

    @Override
    public String toString() {
        return "PostOffice{" +
                "officeId=" + officeId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}


