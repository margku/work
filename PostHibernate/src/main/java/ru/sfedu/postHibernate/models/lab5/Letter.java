package ru.sfedu.postHibernate.models.lab5;


import javax.persistence.*;

@Entity
@Table(schema = "lab5", name = "letter")
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int weight;
    private long mark;
    private int markCount;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "Id", nullable = false)
    private Client client;
    @OneToOne (optional=false, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn (name="envelope_id", unique = true)
    private Envelope envelope;


    //
    // Constructors
    //
    public Letter() {
    }


    public Letter(long id, int weight, long mark, int markCount, Client client, Envelope envelope) {
        this.id = id;
        this.weight = weight;
        this.mark = mark;
        this.markCount = markCount;
        this.client = client;
        this.envelope = envelope;

    }

    public Letter(int weight, long mark, int markCount, Client client, Envelope envelope) {
        this.weight = weight;
        this.mark = mark;
        this.markCount = markCount;
        this.client = client;
        this.envelope = envelope;
    }

    public int getWeight() {
        return weight;
    }


    public void setWeight(int weight) {
        this.weight = weight;
    }


    public long getMark() {
        return mark;
    }


    public void setMark(long mark) {
        this.mark = mark;
    }


    public int getMarkCount() {
        return markCount;
    }


    public void setMarkCount(int markCount) {
        this.markCount = markCount;
    }


    public Envelope getEnvelope() {
        return envelope;
    }

    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", weight=" + weight +
                ", mark=" + mark +
                ", markCount=" + markCount +
                ", sender=" + client +
                ", envelope=" + envelope +
                '}';
    }
}


