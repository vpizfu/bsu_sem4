package web.sem2.lab1.models.output;

import web.sem2.lab1.models.entities.Bill;

import java.util.Arrays;
import java.util.List;

public class BillOutputModel {
    private Bill bill;
    private List<BillElementModel> billElements;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public List<BillElementModel> getBillElements() {
        return billElements;
    }

    public void setBillElements(List<BillElementModel> billElements) {
        this.billElements = billElements;
    }

    @Override
    public String toString() {
        return "BillOutputModel{" +
                "bill=" + bill +
                ", billElements=" + Arrays.toString(billElements.toArray()) +
                '}';
    }
}
