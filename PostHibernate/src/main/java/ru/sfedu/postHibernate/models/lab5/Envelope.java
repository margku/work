package ru.sfedu.postHibernate.models.lab5;

import javax.persistence.*;


@Entity (name = "Envelope")
@Table(name = "Envelope", schema = "lab5")
public class Envelope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "size", nullable = false)
    private String size;
    @Column(name = "price", nullable = false)
    private int price;
    @OneToOne(mappedBy = "envelope",  fetch = FetchType.EAGER)
    private Letter letter;
    public Envelope() {
    }
    /**
     *
     * @param size
     * @param price
     */
    public Envelope(String size, int price) {
        this.price = price;
        this.size = size;
    }


    public long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
