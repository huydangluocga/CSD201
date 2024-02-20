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


public class Bus implements Inter<Bus> {

    private String bcode;
    private String busname;
    private int seatnum;
    private int booked;
    private double dptime;
    private double artime;

    public Bus(String bcode, String bus_name, int seatnum, int booked, double dptime, double artime) {
        this.bcode = bcode;
        this.busname = bus_name;
        this.seatnum = seatnum;
        this.booked = booked;
        this.dptime = dptime;
        this.artime = artime;
    }

    public Bus(String bcode) {
        this.bcode = bcode;
    }

    public Bus() {
    }

    public String getTtime() {        
        return String.format("%12.1f", artime - dptime);
    }

    @Override
    public int compareTo(Bus o) {
        return this.bcode.compareTo(o.bcode);
    }

    public static String c(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    @Override
    public String toString() {
        return c(5, bcode) + "|" + c(8, busname) + "|" + c(4, "" + seatnum) + "|" + c(6, "" + booked) + "|" + c(11, "" + dptime) + "|" + c(12, "" + artime);
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public int getSeatnum() {
        return seatnum;
    }

    public int getBooked() {
        return booked;
    }
    
    public void add(LinkedList<Bus> t) {
        Validator v = new Validator();
        Scanner sc = new Scanner(System.in);
        String in;
        System.out.print("Adding new Bus\n Enter bus code: ");
        in = sc.nextLine();
        Bus temp = new Bus(in);
        while (t.contain(temp)) {
            System.out.println("Bcode must be unique");
            System.out.print(" Enter bus code: ");
            in = sc.nextLine();
            temp.setBcode(in);
        }
        this.bcode = in;
        
        System.out.print(" Enter bus name: ");
        this.busname = sc.nextLine();
        
        System.out.print(" Enter number of seats: ");
        in = sc.nextLine();
        while(!v.Vali(in)){
        System.out.print(" Enter number of seats: ");
        in = sc.nextLine();
        }
        this.seatnum = Integer.parseInt(in);
        
        System.out.print(" Enter number of booked seats: ");
        in = sc.nextLine();
        while(!v.Vali(in, this.seatnum)){
        System.out.print(" Enter number of booked seats: ");
        in = sc.nextLine();
        }
        this.seatnum = Integer.parseInt(in);
        
        System.out.print(" Enter departure time: ");
        in = sc.nextLine();
        while(!v.Vald(in)){
        System.out.print(" Enter departure time: ");
        in = sc.nextLine();
        }
        this.dptime = Double.parseDouble(in);
        
        System.out.print(" Enter arrival time: ");
        in = sc.nextLine();
        while(!v.Vald(in, this.dptime)){
        System.out.print(" Enter arrival time: ");
        in = sc.nextLine();
        }
        this.artime = Double.parseDouble(in);
    }

}
