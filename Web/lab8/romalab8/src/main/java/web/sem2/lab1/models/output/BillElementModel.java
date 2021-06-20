package web.sem2.lab1.models.output;

import web.sem2.lab1.models.entities.BillElement;
import web.sem2.lab1.models.entities.Drink;

public class BillElementModel {
    private BillElement billElement;
    private Drink drink;

    public BillElement getBillElement() {
        return billElement;
    }

    public void setBillElement(BillElement billElement) {
        this.billElement = billElement;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "BillElementModel{" +
                "billElement=" + billElement +
                ", drink=" + drink +
                '}';
    }
}
