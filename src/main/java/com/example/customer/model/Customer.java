package com.example.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    private int cust_id;
    
    private String cust_name;
    private String cust_email;
    private int cust_ph_number;
    private String cust_gender;

    public void customer(int cust_id, String cust_name, String cust_email, int cust_ph_number, String cust_gender){
        this.cust_id= cust_id;
        this.cust_name= cust_name;
        this.cust_email= cust_email;
        this.cust_ph_number= cust_ph_number;
        this.cust_gender= cust_gender;
    }
    public int getCust_id() {
        return cust_id;
    }
    public String getCust_name() {
        return cust_name;
    }
    public String getCust_email() {
        return cust_email;
    }
    public int getCust_ph_number() {
        return cust_ph_number;
    }
    public String getCust_gender() {
        return cust_gender;
    }
    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }
    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }
    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }
    public void setCust_ph_number(int cust_ph_number) {
        this.cust_ph_number = cust_ph_number;
    }
    public void setCust_gender(String cust_gender) {
        this.cust_gender = cust_gender;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cust_id;
        result = prime * result + ((cust_name == null) ? 0 : cust_name.hashCode());
        result = prime * result + ((cust_email == null) ? 0 : cust_email.hashCode());
        result = prime * result + cust_ph_number;
        result = prime * result + ((cust_gender == null) ? 0 : cust_gender.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (cust_id != other.cust_id)
            return false;
        if (cust_name == null) {
            if (other.cust_name != null)
                return false;
        } else if (!cust_name.equals(other.cust_name))
            return false;
        if (cust_email == null) {
            if (other.cust_email != null)
                return false;
        } else if (!cust_email.equals(other.cust_email))
            return false;
        if (cust_ph_number != other.cust_ph_number)
            return false;
        if (cust_gender == null) {
            if (other.cust_gender != null)
                return false;
        } else if (!cust_gender.equals(other.cust_gender))
            return false;
        return true;
    }
    
}
