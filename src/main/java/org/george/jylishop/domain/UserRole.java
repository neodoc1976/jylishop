package org.george.jylishop.domain;



import javax.persistence.*;

/**
 * Created by Yulya on 06.11.2016.
 */
@Entity
@Table(name="user_roles")
public class UserRole {

    @Column(name = "user_role_id")
    @Id
    @GeneratedValue
    private int id;


    @Column (name = "role")
    private  String role;

    @ManyToOne
    @JoinColumn (name = "username" , referencedColumnName = "username")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
