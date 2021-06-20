package web.sem2.lab1.models.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "bills_drinks")
@NamedQueries ({
        @NamedQuery(name = "BillElement.getAll",
                query = "SELECT B FROM BillElement B"),
        @NamedQuery(name = "BillElement.getByPK",
                query = "SELECT B FROM BillElement B WHERE B.billId = :id")
})
@XmlRootElement
public class BillElement implements Serializable {
    @Id
    @Column(name = "bill_id")
    private int billId;
    @Id
    @Column(name = "drink_id")
    private int drinkId;
    @Column(name = "drink_amount")
    private int drinkAmount;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getDrinkAmount() {
        return drinkAmount;
    }

    public void setDrinkAmount(int drinkAmount) {
        this.drinkAmount = drinkAmount;
    }

    @Override
    public String toString() {
        return "BillElement{" +
                "billId=" + billId +
                ", drinkId=" + drinkId +
                ", drinkAmount=" + drinkAmount +
                '}';
    }
}
