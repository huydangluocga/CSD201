/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

/**
 *
 * @author xeban
 */
public class Customer {

    protected String code;
    protected String name;
    protected String phone;

    public Customer() {
    }

    public Customer(String code, String name, String phone) {
        this.code = code;
        this.name = name;
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code != null && (code.toLowerCase().startsWith("c"))) {
            this.code = code.substring(0, 1).toUpperCase() + code.substring(1);
        } else {
            System.out.println("Customer code must start with 'c' or 'C'. Please enter a valid code.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        } else {
            System.out.println("Name must not be empty.");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    void display() {
        System.out.printf("| %-10s | %-11s | %-12s | \n", code, name, phone);
    }

}
