/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus.booking.assignment;

/**
 *
 * @author Long
 */
import java.util.Scanner;
import bus.booking.assignment.Interface.Inter;
import bus.booking.assignment.LinkedList;
import bus.booking.assignment.Bus;

public class Customer implements Inter<Customer> {

    private String ccode;
    private String cusname;
    private String phone;

    public Customer(String ccode, String cusname, String phone) {
        this.ccode = ccode;
        this.cusname = cusname;
        this.phone = phone;
    }
    public Customer(String ccode){this.ccode = ccode;}
    public Customer() {
    }

    @Override
    public int compareTo(Customer o) {
        return this.ccode.compareTo(o.ccode);
    }

    public static String c(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    @Override
    public String toString() {
        return c(5, ccode) + "|" + c(8, cusname) + "|" + c(12, phone);
    }
    public void add(LinkedList<Customer> t){
        Scanner sc = new Scanner(System.in);
        String in;
        System.out.print("Adding new customer\n Enter customer code: ");
        in = sc.nextLine();
        Customer temp = new Customer(in);
        while(t.contain(temp)){
            System.out.println("Customer code must be unique");
            System.out.print(" Enter customer code: ");
            in = sc.nextLine();
            temp = new Customer(in);
        }
        this.ccode = in;
        
        System.out.print(" Enter customer name: ");
        in = sc.nextLine();
        this.cusname = in;
        
        System.out.print("Enter customer phone number: ");
        in = sc.nextLine();
        while(!in.matches("\\d+")){System.out.println("Phone number is digits only");
        System.out.print("Enter customer phone number: ");
        in = sc.nextLine();};
        this.phone = in;
    }
}
