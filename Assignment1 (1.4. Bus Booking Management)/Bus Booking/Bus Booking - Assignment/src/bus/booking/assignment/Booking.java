/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus.booking.assignment;

import java.util.Scanner;
import bus.booking.assignment.Interface.Inter;

/**
 *
 * @author Long
 */
public class Booking implements Inter<Booking> {

    private String ccode;
    private String bcode;
    private int seat;

    public Booking(String ccode, String bcode, int seat) {
        this.ccode = ccode;
        this.bcode = bcode;
        this.seat = seat;
    }

    public Booking() {
    }

    public static String c(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    @Override
    public int compareTo(Booking o) {
        if (this.bcode.compareTo(o.bcode) != 0) {
            return this.ccode.compareTo(o.ccode);
        } else {
            return this.ccode.compareTo(o.ccode);
        }
    }
    @Override
    public String toString(){
    return c(5, bcode) + "|" + c(5, ccode)+"|" + c(4, ""+ seat);
    }

    
    public boolean add(LinkedList<Bus> bus, LinkedList<Customer> cus){
        Scanner sc = new Scanner(System.in);
        String in;
        Validator v = new Validator();
        System.out.print("Adding new Booking\n Enter bus code: ");
        in = sc.nextLine();
        this.bcode = in;
        System.out.print(" Enter customer code: ");
        in = sc.nextLine();
        this.ccode = in;
        
        if (bus.contain(new Bus(bcode)) && cus.contain(new Customer(ccode))){
            Bus temp = (Bus) bus.search(new Bus(bcode)); 
            if (temp.getSeatnum() - temp.getBooked() ==0){System.out.println("Bus is exhausted");return false;}            
            System.out.print(" Enter number of seats to be booked: ");
            in = sc.nextLine();
            while(!v.Vali(in, (temp.getSeatnum() - temp.getBooked()))){
            System.out.print(" Enter number of seats to be booked: ");
            in = sc.nextLine();
        }
            this.seat = Integer.parseInt(in);
        }else if (bus.contain(new Bus(bcode)) ^ cus.contain(new Customer(ccode))) { System.out.println("Customer or Bus doesn't exist");
        }else {System.out.println("Customer and Bus doesn't exist");return false;}
        return true;
    }
}
