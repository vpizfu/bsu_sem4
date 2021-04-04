package company.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Client")
/**
 * Represents "client" table content.
 */
public class Client implements Serializable {

    @ManyToMany(mappedBy = "clients")
    private List<Tour> tours;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "discount")
    private int discount;

    public Client(int id, String name, int discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
    }

    public Client() {

    }

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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String stringWrap(){
        return "Client{" +
                "tours=" + tours +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                '}';
    }

    @Override
    public String toString() {
        return "Client{" +
                "tours=" + tours +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                '}';
    }
}
