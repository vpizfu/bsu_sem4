package web.sem2.lab1.models.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "ingredients")
@NamedQueries({
        @NamedQuery(name = "Ingredient.getAll",
                query = "SELECT I FROM Ingredient I"),
        @NamedQuery(name = "Ingredient.getByPK",
                query = "SELECT I FROM Ingredient I WHERE I.id = :id")
})
@XmlRootElement
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "units")
    private double units;

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

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", units=" + units +
                '}';
    }
}
