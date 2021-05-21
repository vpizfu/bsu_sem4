package company.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "User")
public class User implements Serializable {

    public User() {

    }

    public enum Privilege {
        guest,
        user,
        admin
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "privilege")
    private int privilege;

    public User(int id, String login, String password, int privilege) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.privilege = privilege;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getPrivilege() {
        return privilege;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", privilege=" + privilege +
                '}';
    }
}
