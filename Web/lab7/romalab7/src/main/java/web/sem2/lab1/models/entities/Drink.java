package web.sem2.lab1.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "drinks")
@NamedQueries ({
        @NamedQuery(name = "Drink.getAll",
                query = "SELECT D FROM Drink D"),
        @NamedQuery(name = "Drink.getByPK",
                query = "SELECT D FROM Drink D WHERE D.id = :id")
})
public class Drink implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private double cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
