/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author xeban
 */
public class CustomerList {

    Scanner sc = new Scanner(System.in);
    GetData getData = new GetData();

    private static BufferedReader openFile(String nameFile) throws IOException {
        try {
            return new BufferedReader(new FileReader(nameFile + ".txt"));
        } catch (FileNotFoundException e) {
            throw new IOException(Main.ANSI_RED + "File [" + nameFile + "] not found." + Main.ANSI_RESET, e);
        }
    }

    private static String readFile(BufferedReader reader) throws IOException {
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private static void readObject(String fileContent, LinkedList l) throws IOException {
        boolean hasErrorOccurred = false;
        StringTokenizer st = new StringTokenizer(fileContent, "|");
        while (true) {
            try {
                String ccode = st.nextToken().trim();
                String name = st.nextToken().trim();
                String phone = st.nextToken().trim();
                if (ccode.length() < 1 || (ccode.charAt(0) != 'c' && ccode.charAt(0) != 'C')) {
                    hasErrorOccurred = true;
                    continue;
                }
                Customer tempCustomer = new Customer();
                try {
                    tempCustomer.setCode(ccode);
                    tempCustomer.setName(name);
                    tempCustomer.setPhone(phone);
                    if (tempCustomer.getCode() != null && tempCustomer.getName() != null && tempCustomer.getPhone() != null) {
                        l.addToTail(ccode, name, phone);
                    }

                } catch (Exception e) {
                    hasErrorOccurred = true;
                }
            } catch (NoSuchElementException e) {

                break;
            }
        }
        if (hasErrorOccurred) {
            System.out.println(Main.ANSI_RED + "Fail to readed because there are some errors in the data." + Main.ANSI_RESET);
        } else {
            System.out.println(Main.ANSI_GREEN + "Added successfully!!!" + Main.ANSI_RESET);
        }
    }

    void Customer1(LinkedList list) {
        System.out.println("---- Load data from file ----");
        if (!list.isEmpty()) {
            System.out.println(Main.ANSI_RED + "Data has already been loaded from a file or already exists" + Main.ANSI_RESET);
            System.out.println("1. Override the current data");
            System.out.println("2. Append new data");
            System.out.println("3. Return without making any changes");
            int choice = getData.getInteger("Enter your choice: ", "Choice must be an integer.", 1, 3);
            switch (choice) {
                case 1:
                    list.clear();
                    break;
                case 2:
                    break;
                case 3:
                    return;
            }
        }
        while (true) {
            String nameFile = GetData.getString("Enter the file name to load data : ", "File name must not be empty");
            try (BufferedReader reader = openFile(nameFile)) {
                String fileContents = readFile(reader);
                try {
                    readObject(fileContents, list);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } catch (FileNotFoundException e) {
                System.out.println(Main.ANSI_RED + "Error: File [" + nameFile + "] not found." + Main.ANSI_RESET);
                return;
            } catch (IOException e) {
                System.out.println(Main.ANSI_RED + "Error reading file: " + e.getMessage() + Main.ANSI_RESET);
                return;
            }
            break;
        }
    }

    void Customer2(LinkedList list) {
        System.out.println("---- Input Information ----");
        boolean continueAdding = false;
        do {
            String ccode;
            while (true) {
                ccode = GetData.getString2("Enter customer code: ", "code must not be empty", "^[a-zA-Z0-9 ]{3,10}$", "Code length must from 3-10 and contain digit and char only");
                if (ccode.length() < 1 || (ccode.charAt(0) != 'c' && ccode.charAt(0) != 'C')) {
                    System.err.println(Main.ANSI_RED + "Customer code must start with 'c' or 'C'. Please enter a valid code." + Main.ANSI_RESET);
                } else if (list.NodeSearch(ccode, list) != null) {
                    NodeCus duplicateNode = list.NodeSearch(ccode, list);
                    System.out.println("\t---- Duplicate Information ----");
                    System.out.println("-----------------------------------------------");
                    System.out.printf("| %-10s | %-15s | %-12s | \n", "ccode", "Customer_Name", "Phone");
                    System.out.printf("| %-10s | %-15s | %-12s | \n", duplicateNode.code, duplicateNode.name, duplicateNode.phone);
                    System.out.println("-----------------------------------------------");
                    System.err.println(Main.ANSI_RED + "Customer code already exists. Please enter a different code." + Main.ANSI_RESET);
                } else {
                    if (ccode.charAt(0) == 'c') {
                        ccode = "C" + ccode.substring(1);
                    }
                    break;
                }
            }

//            String cusName;
//            NodeCus nodeWithCusName = null;
//            do {
//                cusName = GetData.getString2("Enter customer name: ", "name must not be empty", "^[a-zA-Z ]{1,10}$", "Name max length is 10 and contain char only");
//                if (cusName != null && !cusName.isEmpty()) {
//                    cusName = cusName.substring(0, 1).toUpperCase() + cusName.substring(1).toLowerCase();
//                    nodeWithCusName = list.NodeSearchName(cusName);
//                    if (nodeWithCusName != null) {                                     
//                        System.err.println(Main.ANSI_RED + "Customer name already exists. Please enter a different customer name." + Main.ANSI_RESET);
//                    }
//                } else {
//                    System.out.println(Main.ANSI_RED + "Name must not be empty." + Main.ANSI_RESET);
//                }
//            } while (nodeWithCusName != null);
            String cusName;
            do {
                cusName = GetData.getString2("Enter customer name: ", "name must not be empty", "^[a-zA-Z ]{1,10}$", "Name max length is 10 and contain char only");
                if (cusName != null && !cusName.isEmpty()) {
                    cusName = cusName.substring(0, 1).toUpperCase() + cusName.substring(1).toLowerCase();
                } else {
                    System.out.println(Main.ANSI_RED + "Name must not be empty." + Main.ANSI_RESET);
                }
            } while (cusName == null || cusName.isEmpty());

            String phone;
            while (true) {
                phone = GetData.getString("Enter customer phone: ", "phone must not be empty");
                if (!phone.matches("0\\d{9}")) {
                    System.err.println(Main.ANSI_RED + "Phone number must follow format 0xxxxxxxxx (x is digit)" + Main.ANSI_RESET);
                    continue;
                }
                NodeCus nodeWithPhone = list.findNodeByPhone(phone);
                if (nodeWithPhone != null) {
                    System.err.println(Main.ANSI_RED + "Phone number already exists. Please enter a different phone number." + Main.ANSI_RESET);
                    continue;
                }
                break;
            }
            list.addToTail(ccode, cusName, phone);
            System.out.println(Main.ANSI_GREEN + "Added successfully!" + Main.ANSI_RESET);
            continueAdding = GetData.getYN("Do you want to continue? (Y/N): ");
            if (!continueAdding) {
                System.out.println();
                return;
            }
        } while (continueAdding);
    }

    void Customer3(LinkedList list) {
        if (list.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to display" + Main.ANSI_RESET);
            return;
        }
        System.out.println("\t    ---- Display Data ----");
        System.out.println();
        list.printList(list);
    }

    void Customer4(LinkedList list) throws IOException {
        if (list.isEmpty()) {
            System.out.println(Main.ANSI_RED + "You have to enter information before add to file" + Main.ANSI_RESET);
            return;
        }
        System.out.println("---- Save customer list to file ----");
        FileCustomer.saveData(list);
    }

    void Customer5(LinkedList list) {
        if (list.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to search" + Main.ANSI_RESET);
            return;
        }
        System.out.println("---- Search by ccode ----");
        String nameSearch = GetData.getString("Enter the ccode you want to search: ", Main.ANSI_RED + "name search must not empty" + Main.ANSI_RESET);
        NodeCus current = list.head;
        boolean found = false;

        System.out.println("-----------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-12s |\n", "ccode", "Customer_Name", "Phone");

        while (current != null) {
            if (current.code != null && current.code.toLowerCase().contains(nameSearch.toLowerCase())) {
                System.out.printf("| %-10s | %-15s | %-12s |\n", current.code, current.name, current.phone);
                found = true;
            }
            current = current.next;
        }
        System.out.println("-----------------------------------------------");
        System.out.println();
        if (!found) {
            System.out.println(Main.ANSI_RED + "Data not found!" + Main.ANSI_RESET);
        }
    }

    void Customer6(LinkedList list) {
        NodeCus curr = list.head;
        NodeCus prev = null;

        if (list.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to delete" + Main.ANSI_RESET);
            return;
        }
        System.out.println("---- Delete by ccode ----");
        String nameDelete = GetData.getString("Enter ccode you want to delete: ", "ccode must not empty");

        if (curr != null && (curr.code == null ? nameDelete == null : curr.code.equalsIgnoreCase(nameDelete))) {
            list.head = curr.next;
            System.err.println(Main.ANSI_GREEN + "Deleted successful!" + Main.ANSI_RESET);
        } else {

            while (curr != null && (curr.code == null ? nameDelete == null : !curr.code.equalsIgnoreCase(nameDelete))) {
                prev = curr;
                curr = curr.next;
            }

            if (curr != null) {
                prev.next = curr.next;
                System.out.println(Main.ANSI_GREEN + "Deleted successful!" + Main.ANSI_RESET);
            }

            if (curr == null) {
                System.out.println(Main.ANSI_RED + "ccode not found!" + Main.ANSI_RESET);
            }
        }
    }
}
