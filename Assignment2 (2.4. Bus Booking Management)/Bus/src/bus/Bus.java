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
public class Bus {

    protected String code;
    protected String name;
    protected int seat;
    protected int booked;
    protected double depart_time;
    protected double arrival_time;

    public Bus() {
    }

    public Bus(String code, String name, int seat, int booked, double depart_time, double arrival_time) {
        this.code = code;
        this.name = name;
        this.seat = seat;
        this.booked = booked;
        this.depart_time = depart_time;
        this.arrival_time = arrival_time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public double getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(double depart_time) {
        this.depart_time = depart_time;
    }

    public double getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(double arrival_time) {
        this.arrival_time = arrival_time;
    }

    void display() {
        System.out.printf("| %-15s | %-15s | %-15d | %-15d | %-16.1f | %-17.1f | \n", code, name, seat, booked, depart_time, arrival_time);
    }

}
