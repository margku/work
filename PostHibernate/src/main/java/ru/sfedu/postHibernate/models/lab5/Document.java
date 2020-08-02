package ru.sfedu.postHibernate.models.lab5;

import javax.persistence.*;

@Entity
@Table(name = "Document", schema = "lab5")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long docNum;
    @OneToOne(mappedBy = "doc")
    private Client client;
    public Document(long doc) {
        this.docNum = doc;
    }

    public Document() {
    }

    public long getDocNum() {
        return docNum;
    }

    public void setDocNum(long docNum) {
        this.docNum = docNum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docNum=" + docNum +
                '}';
    }
}
