package ru.sfedu.postHibernate.models.lab5;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(schema = "lab5",name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "size", nullable = false)
    private String name;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Client_company",
            joinColumns = @JoinColumn (name = "company_id"),
            inverseJoinColumns = @JoinColumn (name = "client_id"))
   protected Set<Client> clients = new HashSet<Client>();


    public Company() {
    }

    public Company(String name) {
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

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;

    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
