/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import java.util.Scanner;

/**
 *
 * @author xeban
 */
public class BookingList {

    Scanner sc = new Scanner(System.in);
    GetData getData = new GetData();

    void bookingList1(BSTBus bstt, LinkedList l, LinkedList2 l2) {
        NodeBus n;
        NodeCus n2;
        if (bstt.isEmpty() && l.isEmpty()) {
            System.out.println(Main.ANSI_RED + "Please load data or input information of Bus and Customer" + Main.ANSI_RESET);
            return;
        }
        if (bstt.isEmpty()) {
            System.out.println(Main.ANSI_RED + "Please load data or input information of Bus first." + Main.ANSI_RESET);
            return;
        }
        if (l.isEmpty()) {
            System.out.println(Main.ANSI_RED + "Please load data or input information of Customer first." + Main.ANSI_RESET);
            return;
        }
        System.out.println("---- Input data ----");
        boolean continueBooking = false;
        do {
            String bcode;
            String ccode;
            while (true) {
                bcode = GetData.getString("Enter bus code: ", "Bus code must not be empty").toUpperCase();
                if (!bcode.matches("B\\d+")) {
                    System.out.println(Main.ANSI_RED + "Invalid bus code. Please try again." + Main.ANSI_RESET);
                } else {
                    n = bstt.searchRec2(bstt.root, bcode);
                    if (n != null) {
                        if (n.b.getBooked() < n.b.getSeat()) {
                            break;
                        } else {
                            System.out.println(Main.ANSI_RED + "The bus is fully booked. Please try again with a different bus." + Main.ANSI_RESET);
                            return;
                        }
                    } else {
                        System.out.println(Main.ANSI_RED + "Bus code Not found." + Main.ANSI_RESET);
                        return;
                    }
                }
            }

            ccode = GetData.getString("Enter customer code: ", "Customer code must not be empty").toUpperCase();
            if (!ccode.matches("C\\d+")) {
                System.out.println(Main.ANSI_RED + "Invalid customer code. Please try again." + Main.ANSI_RESET);
            } else {
                n2 = l.NodeSearch(ccode, l);
                if (n2 == null) {
                    System.out.println(Main.ANSI_RED + "Customer code not found" + Main.ANSI_RESET);
                    return;
                } else if (l2.searchByCcode(ccode)) {
                    boolean ask = GetData.getYN("The customer has already made a booking. Do you want to proceed? (Y/N): ");
                    if (!ask) {
                        return;
                    }
                }
            }

            int availableSeats = n.b.getSeat() - n.b.getBooked();
            if (availableSeats == 0) {
                System.out.println(Main.ANSI_RED + "The bus is fully booked. Please try with a different bus." + Main.ANSI_RESET);
                return;
            }

            int bookedSeats = getData.getSeat("Enter the number of seats to be booked: ", "The number of seats booked must be an integer", availableSeats);

            if (bookedSeats > 0) {
                if (n.b.getBooked() + bookedSeats <= n.b.getSeat()) {
                    l2.addToTail(bcode, ccode, bookedSeats);
                    n.b.setBooked(n.b.getBooked() + bookedSeats);
                    System.out.println(Main.ANSI_GREEN + "Added successfully!" + Main.ANSI_RESET);

                    continueBooking = GetData.getYN("Do you want to continue booking? (Y/N): ");
                    if (!continueBooking) {
                        System.out.println();
                        return;
                    }
                } else {
                    System.out.println(Main.ANSI_RED + "The number of seats requested exceeds the available seats on the bus. Please try booking a lower number of seats." + Main.ANSI_RESET);
                }
            } else {

            }

        } while (continueBooking);
    }

    void bookingList2(LinkedList2 l) {
        if (l.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to display" + Main.ANSI_RESET);
            return;
        }
        System.out.println("\t---- Display Data ----");
        System.out.println();
        System.out.println("-----------------------------------------");
        l.printList(l);
        System.out.println("-----------------------------------------");
        System.out.println();
    }

    void bookingList3(LinkedList2 l) {
        if (l.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to sort" + Main.ANSI_RESET);
            return;
        }
        System.out.println("---- Sort by bcode and ccode ----");
        int sortOption = getData.getInteger("1. Sort by bcode\n2. Sort by ccode\nEnter your choice: ", "Choice must be integer", 1, 2);
        String option = sortOption == 1 ? "bcode" : "ccode";
        l.head = l.mergeSort(l.head, option);
        System.out.println("-----------------------------------------");
        l.printList(l);
        System.out.println("-----------------------------------------");
        System.out.println(Main.ANSI_GREEN + "Sort Completed!!" + Main.ANSI_RESET);
    }
}
