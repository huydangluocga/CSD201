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
import java.io.Console;
public class Assignment {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in;
        Validator v = new Validator();
        LinkedList<Bus> bus = new LinkedList<>();
        LinkedList<Customer> cus = new LinkedList<>();
        LinkedList<Booking> book = new LinkedList<>();
        while (true) {          
            System.out.println("Bus list:\n"
                    + "1.1.      Load data from file\n"
                    + "1.2.      Input & add to the end\n"
                    + "1.3.      Display data\n"
                    + "1.4.      Save bus list to file\n"
                    + "1.5.      Search by bcode\n"
                    + "1.6.      Delete by bcode\n"
                    + "1.7.      Sort by bcode\n"
                    + "1.8.      Add before position  k\n"
                    + "1.9.      Delete the node before the node having bcode = xCode\n"
                    + "\n"
                    + " \n"
                    + "\n"
                    + "Customer list:\n"
                    + "2.1.      Load data from file\n"
                    + "2.2.      Input & add to the end\n"
                    + "2.3.      Display data\n"
                    + "2.4.      Save customer list to file\n"
                    + "2.5.      Search by ccode\n"
                    + "2.6.      Delete by ccode\n"
                    + "\n"
                    + " \n"
                    + "\n"
                    + "Booking list:\n"
                    + "3.1.      Input data\n"
                    + "3.2.      Display data width travel time\n"
                    + "3.3.      Sort by bcode + ccode\n\n3.4. Exit Program\n");
            System.out.print("Enter your choice: ");
            in = sc.nextLine();
            while (!v.Valc(in)) {
                System.out.print("Enter your choice: ");
                in = sc.nextLine();
            }
            switch(in){
                case"1.1":{bus.Read("Bus"); break;}
                case"1.2":{bus.inputt("Bus");break;}
                case"1.3":{bus.display(bus, "Bus");break;}
                case"1.4":{bus.Write("Bus");break;}
                case"1.5":{
                    System.out.print("Enter target bus code: ");
                    in = sc.nextLine();
                    Bus t = new Bus(in);
                    System.out.println(bus.search(t));
                    break;}
                case"1.6":{
                    System.out.print("Enter target bus code: ");
                    in = sc.nextLine();
                    Bus t = new Bus(in);
                    bus.dele(t);
                    break;}
                case"1.7":{
                    bus.sort();
                    bus.display(bus, "Bus");
                    break;}
                case"1.8":{
                    System.out.print("Enter target position: ");
                    in = sc.nextLine();
                    while(!v.Vali2(in)){
                    System.out.print("Enter target position: ");
                    in = sc.nextLine();
                    }
                    int input = Integer.parseInt(in);
                    bus.addBefore(input, "Bus");
                    break;}
                case"1.9":{
                     System.out.print("Enter target bus code: ");
                    in = sc.nextLine();
                    Bus t = new Bus(in);
                    bus.deleteBefore(t);
                    break;}               
                
                case"2.1":{cus.Read("Customer");break;}
                case"2.2":{cus.inputt("Customer");break;}
                case"2.3":{cus.display();break;}
                case"2.4":{cus.Write("Customer");break;}
                case"2.5":{System.out.print("Enter target customer code: ");in = sc.nextLine();Customer t = new Customer(in);System.out.println(cus.search(t));;break;}
                case"2.6":{System.out.print("Enter target customer code: ");in = sc.nextLine();Customer t = new Customer(in);cus.dele(t);break;}
                case"3.1":{book.inputt(bus, cus);break;}
                case"3.2":{book.display(bus, "Booking");break;}
                case"3.3":{book.sort();book.display(bus, "Booking");break;}
                case"3.4":{System.out.println("Program exitted");return;
                    }
            }
        }
    }

}
