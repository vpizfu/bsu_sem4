package company.DAO;

import javax.persistence.*;
import java.util.List;

/**
 * Represents "tour" table content.
 */
@Entity
@Table(name = "Tour")
@NamedQueries({
        @NamedQuery(name = "getAllTours", query = "SELECT t FROM Tour  t"),
        @NamedQuery(name = "getAllHotToursOfType", query = "SELECT t FROM Tour t WHERE t.isHot = true  AND type = ?"),
        @NamedQuery(name = "getAllToursForClient", query = "SELECT t FROM Tour t INNER JOIN t.clients c WHERE c.id = ?")
})

@NamedNativeQueries({
        @NamedNativeQuery(name = "bookTourRequest", query = "INSERT INTO client_orders (client_id,tour_id) VALUES (?,?)")
})
public class Tour {

    public Tour() {

    }

    public enum type {
        allInclusive,
        sport,
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_orders",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    public List<Client> clients;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private float price;

    @Column(name = "tour_date_start")
    private String startDate;

    @Column(name = "tour_date_end")
    private String endDate;

    @Column(name = "is_hot")
    private boolean isHot;

    @Column(name = "type")
    private String type;

    public Tour(int id, String location, float price, String startDate, String endDate, boolean isHot, String type) {
        this.id = id;
        this.location = location;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isHot = isHot;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }
}
