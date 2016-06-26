package org.george.jylishop.domain;

/**
 * Created by Yulya on 02.05.2016.
 */
public class Contact {
    private String name;
    private String email;
    private String telephone;
    private String address;
    private String location;
    private int id;
    public final static String Contact_Type="contact_type" ;//додаткове поле-маркер для точної ідентифікації класу даних при виклику з бази.

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
