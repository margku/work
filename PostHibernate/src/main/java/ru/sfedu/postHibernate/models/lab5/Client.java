package ru.sfedu.postHibernate.models.lab5;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(schema = "lab5",name = "сlient")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    protected Set<Letter> letters = new HashSet<>();

    @OneToOne (optional=false, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn (name="doc", unique = true)
    protected Document doc;
    public Client() {
    }

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Client_company",
    joinColumns = @JoinColumn (name = "client_id"),
    inverseJoinColumns = @JoinColumn (name = "company_id"))

    protected Set<Company> companies = new HashSet<Company>();
    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * @param name
     */
    public Client(String name) {
        this.name = name;
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

    public Set<Letter> getLetters() {
        return letters;
    }

    public void setLetters(Set<Letter> letters) {
        this.letters = letters;
    }

    public Document getDocument() {
        return doc;
    }

    public void setDocument(Document document) {
        this.doc = document;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", doc=" + doc +
                '}';
    }
}

