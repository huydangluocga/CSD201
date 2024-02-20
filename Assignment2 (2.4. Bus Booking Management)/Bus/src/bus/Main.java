/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author xeban
 */
public class Main {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        boolean exit = false;
        FileBus fb = new FileBus();
        GetData getData = new GetData();
        BSTBus bstt = new BSTBus();
        LinkedList list = new LinkedList();
        LinkedList2 list2 = new LinkedList2();
        BusList bl = new BusList();
        CustomerList cl = new CustomerList();
        BookingList bk = new BookingList();
        File f = new File("E:\\NetBeansProject\\Bus");
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            System.out.println("---- Bus Booking Management ----");
            System.out.println("1. Bus List");
            System.out.println("2. Customer List");
            System.out.println("3. Booking List");
            System.out.println("4. Exit");
            int choice = getData.getInteger("Enter your choice: ", "Choice must be integer", 1, 4);
            switch (choice) {
                case 1: {
                    boolean exitBusMenu = false;
                    while (!exitBusMenu) {
                        System.out.println("----Bus List----");
                        System.out.println("1. Load Data From File");
                        System.out.println("2. Input & Insert Data");
                        System.out.println("3. In-order traverse");
                        System.out.println("4. Breadth-first traverse");
                        System.out.println("5. In-order traverse to file");
                        System.out.println("6. Search By bcode");
                        System.out.println("7. Delete By bcode by copying");
                        System.out.println("8. Simply Balancing");
                        System.out.println("9. Count Number Of Buses");
                        System.out.println("10. Exit");
                        int busChoice = getData.getInteger("Enter your choice: ", "choice must be integer", 1, 10);
                        switch (busChoice) {
                            case 1:
                                bl.Bus1(bstt);
                                break;
                            case 2:
                                bl.Bus2(bstt);
                                break;
                            case 3:
                                bl.Bus3(bstt);
                                break;
                            case 4:
                                bl.Bus4(bstt);
                                break;
                            case 5:
                                bl.Bus5(f, bstt);
                                break;
                            case 6:
                                bl.Bus6(bstt);
                                break;
                            case 7:
                                bl.Bus7(bstt);
                                break;
                            case 8:
                                bl.Bus8(bstt);
                                break;
                            case 9:
                                bl.Bus9(bstt);
                                break;
                            case 10:
                                exitBusMenu = true;
                                break;
                        }
                    }
                    break;
                }
                case 2: {
                    boolean exitCustomerMenu = false;
                    while (!exitCustomerMenu) {
                        System.out.println("----Customer List----");
                        System.out.println("1. Load Data From File");
                        System.out.println("2. Input & Add To The End");
                        System.out.println("3. Display Data");
                        System.out.println("4. Save Customer List Data");
                        System.out.println("5. Search By Ccode");
                        System.out.println("6. Delete By Ccode");
                        System.out.println("7. Exit");
                        int cusChoice = getData.getInteger("Enter your choice: ", "Choice must be integer", 1, 7);
                        switch (cusChoice) {
                            case 1:
                                cl.Customer1(list);
                                break;
                            case 2:
                                cl.Customer2(list);
                                break;
                            case 3:
                                cl.Customer3(list);
                                break;
                            case 4:
                                cl.Customer4(list);
                                break;
                            case 5:
                                cl.Customer5(list);
                                break;
                            case 6:
                                cl.Customer6(list);
                                break;
                            case 7:
                                exitCustomerMenu = true;
                                break;
                        }
                    }
                    break;
                }
                case 3: {
                    boolean exitBookingMenu = false;
                    while (!exitBookingMenu) {
                        System.out.println("----Booking List----");
                        System.out.println("1. Input Data");
                        System.out.println("2. Display Data With Available Seat");
                        System.out.println("3. Sort By Bcode & Ccode");
                        System.out.println("4. Exit");
                        int bookChoice = getData.getInteger("Enter your choice: ", "Choice must be integer", 1, 4);
                        switch (bookChoice) {
                            case 1:
                                bk.bookingList1(bstt, list, list2);
                                break;
                            case 2:
                                bk.bookingList2(list2);
                                break;
                            case 3:
                                bk.bookingList3(list2);
                                break;
                            case 4:
                                exitBookingMenu = true;
                                break;
                        }
                    }
                    break;
                }
                case 4:
                    return;
            }
        }

    }
}
