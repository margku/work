package ru.sfedu.postHibernate.models.lab4;



import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "ClientInfo", schema = "lab4")
public class ClientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection (fetch = FetchType.EAGER)
    @CollectionTable(name = "phone", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "number")
    @AttributeOverride(name = "number", column = @Column (name = "phoneNumber", nullable = false))
    private Set<Phone> number = new HashSet<Phone>();


    @ElementCollection (fetch = FetchType.EAGER)
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "id"))
    @OrderColumn
    @Column
    private List<String> address = new ArrayList<>();

    @ElementCollection (fetch = FetchType.EAGER)
    @CollectionTable(name = "document")
    @MapKeyColumn(name = "type")
    private Map<String, Document> document = new HashMap<String, Document>();


    public ClientInfo() {
    }

    /**
     * @param name
     */
    public ClientInfo(String name) {
        this.name = name;
    }
    public ClientInfo(long id, String name) {

        this.name = name;
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Phone> getNumber() {
        return number;
    }

    public void setNumber(Set<Phone> number) {
        this.number = number;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public Map<String, Document> getDocument() {
        return document;
    }

    public void setDocument(Map<String, Document> document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", address=" + address +
                ", document=" + document +
                '}';
    }
}

