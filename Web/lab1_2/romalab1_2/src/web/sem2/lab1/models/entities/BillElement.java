package web.sem2.lab1.models.entities;

public class BillElement {
    private int billId;
    private int drinkId;
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
