package ru.sfedu.postHibernate.models.lab2;

import javax.persistence.*;


@Entity
@Table(name = "ClientLab", schema = "lab2")
public class ClientLab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name = "diameter", column = @Column(name = "FIRST_DIAMETER")),
             @AttributeOverride(name = "omega", column = @Column(name = "FIRST_OMEGA")),
             @AttributeOverride(name = "producer", column = @Column(name = "FIRST_PRODUCER")),
             @AttributeOverride(name = "type", column = @Column(name = "FIRST_TYPE"))
            })
    private Washinmashine myFirstWashinmashine;
    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name = "diameter", column = @Column(name = "SECOND_DIAMETER")),
            @AttributeOverride(name = "omega", column = @Column(name = "SECOND_OMEGA")),
            @AttributeOverride(name = "producer", column = @Column(name = "SECOND_PRODUCER")),
            @AttributeOverride(name = "type", column = @Column(name = "SECOND_TYPE"))
            })
    private Washinmashine mySecondWashinmashine;

    public ClientLab() {
    }

    /**
     * @param name
     */
    public ClientLab( String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Washinmashine getMyFirstWashinmashine() {
        return myFirstWashinmashine;
    }

    public void setMyFirstWashinmashine(Washinmashine myFirstWashinmashine) {
        this.myFirstWashinmashine = myFirstWashinmashine;
    }

    public Washinmashine getMySecondWashinmashine() {
        return mySecondWashinmashine;
    }

    public void setMySecondWashinmashine(Washinmashine mySecondWashinmashine) {
        this.mySecondWashinmashine = mySecondWashinmashine;
    }

    @Override
    public String toString() {
        return "ClientLab{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", myFirstWashinmashine=" + myFirstWashinmashine +
                ", mySecondWashinmashine=" + mySecondWashinmashine +
                '}';
    }
}

