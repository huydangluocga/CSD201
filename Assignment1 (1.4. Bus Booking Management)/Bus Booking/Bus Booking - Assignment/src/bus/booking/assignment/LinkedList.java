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

import java.io.*;
import java.util.Scanner;
import bus.booking.assignment.Interface.Inter;
import bus.booking.assignment.Bus;

public class LinkedList<T extends Inter<T>> {

    Node head, tail;

    public class Node<T> {

        T info;
        Node<T> next;

        public Node(T info) {
            this.info = info;
        }
    }

    public LinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addToTail(Node p) {
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    
    public void addBefore(int x, String type) {
        Node<T> node1 = pos(x);
        Node<T> node2 = null;
        if (node1 == null) {
            System.out.println("Node(s) not found. Cannot perform the operation.");
            return;
        }
        if(type.equals("Bus")){Bus t = new Bus(); t.add((LinkedList<Bus>) this); node2 = new Node(t);}
        if(type.equals("Customer")){Customer t = new Customer(); t.add((LinkedList<Customer>) this); node2 = new Node(t);}
        if (head == node1) {
            node1.next = head;
            head = node1;
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null && current != node1) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Target node not found in the list.");
            return;
        }

        node2.next = current;
        previous.next = node2;
    }
    
    public void addBefore(LinkedList<Bus> bus, LinkedList<Customer> cus, int x) {
        Node<T> node1 = pos(x);
        Node<T> node2 = null;
        if (node1 == null) {
            System.out.println("Node(s) not found. Cannot perform the operation.");
            return;
        }
        Booking t = new Booking();
        if (t.add(bus, cus)) {
            node2 = new Node(t);
        
        if (head == node2) {
            node1.next = head;
            head = node1;
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null && current != node2) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Target node not found in the list.");
            return;
        }

        node1.next = current;
        previous.next = node1;}
    }
    

    public static String c(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void display() {
        System.out.println("ccode|cus_name|phone_number\n---------------------------");
        Node p = head;
        while (p != null) {
            System.out.println(p.info);
            p = p.next;
        }
        System.out.println("");
    }
    public void display(LinkedList<Bus> temp, String type) {
        if (type.equals("Bus")) {
            System.out.println("bcode|Bus_name|Seat|booked|depart_time|arrival_time|travel_time   \n" + "-------------------------------------------------------------------");}
        if (type.equals("Booking")) {
            System.out.println("bcode|ccode|seat|travel_time\n----------------------------");}
        Node p = head;
        while (p != null) {
            while (p != null) {
                String t = p.info.toString().substring(0, 4).trim();
                Bus n = (Bus) temp.search(new Bus(t));
                System.out.println(p.info + "|" + n.getTtime());
                p = p.next;
            }
        }
        System.out.println("");
    }

    
    public Object search(T in) {
        try{
        Node<T> p = head;
        Node<T> q = new Node<T>(in);
        while (p != null && (p.info.compareTo(q.info) != 0)) {
            p = p.next;
        }
        return p.info;
        
    }catch(NullPointerException e){System.out.println("Node not found"); return null;}
    }
//10
    public boolean contain(T in){
        Node<T> q = new Node<T>(in);
        Node<T> temp = head;
        while(temp != null){
        if(temp.info.compareTo(q.info) == 0){return true;}
        temp = temp.next;
        }
        return false;
    }



    
    public void sort() {
        int n = count();
        if(n == 0){System.out.println("List is empty");return;}
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Node<T> t1 = pos(i);
                Node<T> t2 = pos(j);
                if ((t1.info.compareTo(t2.info)) > 0) {
                    Node<T> t = new Node<>(t1.info);
                    t1.info = t2.info;
                    t2.info = t.info;
                }
            }
        }
        System.out.println("Sort successful");
    }
//13

    public void dele(Node q) {
   if (head == null) {
            System.out.println("The list is empty.");
            return;
        }

        if (head == q) {
            head = q.next;
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null && current != q) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Node to be deleted not found in the list.");
            return;
        }

        previous.next = current.next;
        System.out.println("Delete succesful");
    }
    
    public void dele(T o) {
        Node<T> p = head;
        Node<T> q = new Node<T>(o);
        while (p != null && (p.info.compareTo(q.info) != 0)) {
            p = p.next;
        }
        dele(p);
    }
        public void deleteBefore(T o) {
        Node<T> q = new Node(search(o));
            
        if (head == null || head == q || head.next == null) {
            System.out.println("Cannot perform the operation.");
            return;
        }

        Node current = head;
        Node previous = null;


        while (current != null && current.info != q.info) {
            previous = current;
            current = current.next;
        }
         
        if (previous == null) {
            System.out.println("No node found before the specified node.");
            return;
        }
        dele(previous);
    }

        
    public Node pos(int x) {
        Node p = head;
        int c = 0;
        while (p != null && c < x) {
            c++;
            p = p.next;
        }
        return p;
    }

    public void Read(String type) {
            Scanner sc = new Scanner(System.in);
        String path = "";
        System.out.print("Enter file path(empty for default file location): ");
        path = sc.nextLine();
        if (path.isEmpty()){
    if (type.equals("Bus")) {path = "bus.txt";}
    if (type.equals("Customer")) {path = "customer.txt";}
    if (type.equals("Booking")) {path = "booking.txt";}}
        try {
            BufferedReader read = new BufferedReader(new FileReader(path));
            String line;
            File temp = new File(path);
            if(temp.length() != 0){System.out.print("Do you want to keep the existing data (Y/N): ");
            String choice;
            choice = sc.nextLine();
            while(!choice.toLowerCase().equals("y") && !choice.toLowerCase().equals("n")){
                System.out.println("Invalid choice!");
                System.out.print("Do you want to keep the existing data (Y/N): ");
                choice = sc.nextLine();
            }
            if(choice.equals("n")){return;}
            }
            int count = 1;
            while ((line = read.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] t = line.split("[\\s|]+");
                try{
                if (type.equals("Bus")) {this.addToTail(new Node(new Bus(t[0], t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3]), Double.parseDouble(t[4]), Double.parseDouble(t[5]))));}
                if (type.equals("Customer")) {this.addToTail(new Node(new Customer(t[0], t[1], t[2])));}
                if (type.equals("Booking")) {this.addToTail(new Node(new Booking(t[0], t[1], Integer.parseInt(t[2]))));}
                }catch (NumberFormatException e){System.out.println("Reas error at line " + count);
                }catch(ArrayIndexOutOfBoundsException e){System.out.println("Reas error at line " + count);}
                count++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error when reading file");
        }
    }
    public void inputt(String type) {
        if (type.equals("Bus")) {Bus t = new Bus(); t.add((LinkedList<Bus>) this); this.addToTail(new Node(t));}
        if (type.equals("Customer")) {Customer t = new Customer(); t.add((LinkedList<Customer>) this); this.addToTail(new Node(t));}
    }
    
    public void inputt(LinkedList<Bus> bus, LinkedList<Customer> cus){
    Booking t = new Booking();
    if(t.add(bus, cus)){
    this.addToTail(new Node(t));
    }
    }
    
    public void Write(String type){
    Scanner sc = new Scanner(System.in);
        String path = "";
        System.out.println("Enter file path(empty for default file location): ");
        path = sc.nextLine();
        if (path.isEmpty()){
    if (type.equals("Bus")) {path = "bus.txt";}
    if (type.equals("Customer")) {path = "customer.txt";}
    if (type.equals("Booking")) {path = "booking.txt";}}
    try{
    PrintWriter write = new PrintWriter(new FileWriter(path));
        Node p = head;
        while (p != null) {
            write.println(p.info);
            p = p.next;
        }
        write.close();
    }catch(FileNotFoundException e){System.out.println("File not found, new file created");
    }catch(IOException e){System.out.println("Error in reading file");}
    }
    
        public int count() {
        Node p = head;
        int c = 0;
        while (p != null) {
            c++;
            p = p.next;
        }
        return c;
    }
}
