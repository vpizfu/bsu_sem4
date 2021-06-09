package web.sem2.lab1.models.entities;

public class Bill {
    private int id;
    private String userLogin;
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
