package web.sem2.lab1.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bills")
@NamedQueries ({
        @NamedQuery(name = "Bill.getAll",
                    query = "SELECT B FROM Bill B"),
        @NamedQuery(name = "Bill.getByPK",
                    query = "SELECT B FROM Bill B WHERE B.id = :id")
})
public class Bill implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "paid")
    private boolean isPaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", userLogin='" + userLogin + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }
}
