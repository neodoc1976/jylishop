package org.george.jylishop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Yulya on 02.05.2016.
 */
@Entity
@Table
public class Contact {
    private String name;
    private String email;
    private String telephone;
    private String address;
    private String location;
    @Id
    @GeneratedValue
    private int id;
    public final static String CONTACT_TYPE ="contact_type" ;//додаткове поле-маркер для точної ідентифікації класу даних при виклику з бази.

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

}
