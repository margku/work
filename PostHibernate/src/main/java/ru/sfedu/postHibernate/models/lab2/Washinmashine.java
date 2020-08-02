package ru.sfedu.postHibernate.models.lab2;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Washinmashine {
    @Column(name = "DIAMETER", nullable = false)
   private int diameter;
    @Column(name = "OMEGA", nullable = false)
   private int omega;
    @Column(name = "PRODUSER", nullable = false)
   private String producer;
    @Column(name = "TYPE", nullable = false)
    private String type;
public Washinmashine () {}
    public Washinmashine(int diameter, int omega, String producer, String type) {
        this.diameter = diameter;
        this.omega = omega;
        this.producer = producer;
        this.type = type;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getOmega() {
        return omega;
    }

    public void setOmega(int omega) {
        this.omega = omega;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Washinmashine{" +
                "diameter=" + diameter +
                ", omega='" + omega + '\'' +
                ", producer='" + producer + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
