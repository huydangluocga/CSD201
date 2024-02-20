/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author xeban
 */
public class BusList extends Bus {

    GetData getData = new GetData();
    Scanner sc = new Scanner(System.in);
    FileBus fb = new FileBus();

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

    private static void readObjects(String fileContents, BSTBus bstt) {
        StringTokenizer st = new StringTokenizer(fileContents, "|");
        while (st.hasMoreTokens()) {
            String bcode = st.nextToken().trim();
            String bus_name = st.nextToken().trim();
            int seat = Integer.parseInt(st.nextToken().trim());
            int booked = Integer.parseInt(st.nextToken().trim());
            double depart_time = Double.parseDouble(st.nextToken().trim());
            double arrival_time = Double.parseDouble(st.nextToken().trim());
            bstt.add(new Bus(bcode, bus_name, seat, booked, depart_time, arrival_time));
        }
    }

    void Bus1(BSTBus bstt) throws FileNotFoundException, IOException {
        System.out.println("---- Load data from file ----");
        if (!bstt.isEmpty()) {
            System.out.println(Main.ANSI_RED + "Data has already been loaded from a file or already exists" + Main.ANSI_RESET);
            System.out.println("1. Override the current data");
            System.out.println("2. Append new data");
            System.out.println("3. Return without making any changes");
            int choice = getData.getInteger("Enter your choice: ", "Choice must be an integer.", 1, 3);
            switch (choice) {
                case 1:
                    bstt.clear();
                    break;
                case 2:
                    break;
                case 3:
                    return;
            }
        }
        while (true) {
            String nameFile = GetData.getString("Enter the file name to load data : ", "file name must not be empty");
            try (BufferedReader reader = openFile(nameFile)) {
                String fileContents = readFile(reader);
                try {
                    readObjects(fileContents, bstt);
                    System.out.println(Main.ANSI_GREEN + "Load successful!!!" + Main.ANSI_RESET);
                } catch (NumberFormatException e) {
                    System.out.println(Main.ANSI_RED + "Error loading file: Invalid data format. Error converting string to number." + Main.ANSI_RESET);
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

    void Bus2(BSTBus bstt) {
        System.out.println("----Input & Add ----");
        boolean continueAdding;
        do {
            while (true) {
                code = GetData.getString2("Enter Bus code: ", "Bus code must not be empty", "^[a-zA-Z0-9 ]{3,10}$", "Code length must from 3-10 and contain digit and char only");
                if (!(code.startsWith("B") || code.startsWith("b"))) {
                    System.out.println(Main.ANSI_RED + "Bus code must start with the letter 'B' or 'b'. Please try again." + Main.ANSI_RESET);
                } else {
                    if (code.startsWith("b")) {
                        code = "B" + code.substring(1);
                    }
                    if (bstt.search(code)) {
                        System.out.println(Main.ANSI_RED + "Duplicate bus code. Please enter a different bus code." + Main.ANSI_RESET);
                    } else {
                        break;
                    }
                }
            }
            while (true) {
                name = GetData.getString2("Enter Bus name: ", "Bus name must not be empty", "^[a-zA-Z ]{1,15}$", "Code length must from 1-15 and contain char only");
                if (name != null && !name.isEmpty()) {
                    name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                    if (bstt.searchName(name)) {
                        System.out.println(Main.ANSI_RED + "Duplicate bus name. Please enter a different bus name." + Main.ANSI_RESET);
                        continue;
                    } else {
                        break;
                    }
                }
            }
            while (true) {
                seat = getData.getInteger("Enter seat: ", "Seat must be an integer", 1, 100);
                booked = getData.getInteger("Enter booked seat: ", "Booked seat must be an integer", 1, seat);
                if (seat < booked) {
                    System.out.println(Main.ANSI_RED + "The number of booked seats cannot exceed the total number of seats. Please try again." + Main.ANSI_RESET);
                } else {
                    break;
                }
            }
            while (true) {
                String timeOption = GetData.getString("Choose option for time input (1 for float, 2 for HH:mm:ss): ", "Option must not be empty");
                if (timeOption.equals("1")) {
                    depart_time = this.getData.getDouble("Enter depart time: ", "Depart time must be a number", 0, 24);
                    arrival_time = this.getData.getDouble("Enter arrival time: ", "Arrival time must be a number", 0, 24);
                    bstt.add(new Bus(code, name, seat, booked, depart_time, arrival_time));
                    break;
                } else if (timeOption.equals("2")) {
                    String timeRegex = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
                    String departTime = getTimeInput("Enter depart time in HH:mm:ss format: ", timeRegex, "Invalid time format. Please enter in the format HH:mm:ss");
                    String arrivalTime = getTimeInput("Enter arrival time in HH:mm:ss format: ", timeRegex, "Invalid time format. Please enter in the format HH:mm:ss");
                    depart_time = convertTimeToFloat(departTime);
                    arrival_time = convertTimeToFloat(arrivalTime);
                    bstt.add(new Bus(code, name, seat, booked, depart_time, arrival_time));
                    if (depart_time >= arrival_time) {
                        System.out.println(Main.ANSI_RED + "Departure time cannot be later than or equal to arrival time. Please try again." + Main.ANSI_RESET);
                        continue;
                    } else {
                        bstt.add(new Bus(code, name, seat, booked, depart_time, arrival_time));
                        break;
                    }
                } else {
                    System.out.println(Main.ANSI_RED + "Invalid option. Please choose 1 or 2." + Main.ANSI_RESET);
                    continue;
                }
            }
            System.out.println(Main.ANSI_GREEN + "Added Successfully!!!" + Main.ANSI_RESET);
            continueAdding = GetData.getYN("Do you want to continue adding information? (Y/N): ");
            if (!continueAdding) {
                System.out.println();
                return;
            }
        } while (continueAdding);
    }

    private String getTimeInput(String msg, String timeRegex, String err) {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();
            if (input.matches(timeRegex)) {
                return input;
            } else {
                System.out.println(Main.ANSI_RED + err + Main.ANSI_RESET);
            }
        }
    }

    private double convertTimeToFloat(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        return hours + (minutes / 60.0) + (seconds / 3600.0);
    }

    void Bus3(BSTBus bst) throws IOException {
        if (bst.isEmpty()) {
            System.out.println("No data to display");
            return;
        }
        System.out.println("\t\t\t---- In-order traverse ----");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-16s | %-17s | \n", "b_code", "bus_name", "seat", "booked", "depart_time", "arrival_time");
        bst.inOrder(bst.root);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

    }

    void Bus4(BSTBus bstt) throws IOException {
        if (bstt.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to display" + Main.ANSI_RESET);
            return;
        }
        System.out.println("\t\t\t---- Breadth-first Traverse ----");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-16s | %-17s | \n", "b_code", "bus_name", "seat", "booked", "depart_time", "arrival_time");
        bstt.printBreadthFirst();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }

    void Bus5(File f, BSTBus bstt) throws IOException {
        if (bstt.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to save to file" + Main.ANSI_RESET);
            return;
        }
        System.out.println("---- In-order traverse to file ---- ");
        FileWriter out = new FileWriter(f.getAbsolutePath() + ".txt");
        FileBus.saveData(bstt);
        out.close();
    }

    void Bus6(BSTBus bstt) {
        if (bstt.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to search" + Main.ANSI_RESET);
            return;
        }
        System.out.println("---- Search By Bcode ----");
        String pCodeSearch = GetData.getString("Enter bcode you want to search: ", "bcode must not empty");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-16s | %-17s | \n", "b_code", "bus_name", "seat", "booked", "depart_time", "arrival_time");
        NodeBus result = bstt.searchRec3(bstt.root, pCodeSearch);
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        if (result != null) {

        } else {
            System.out.println(Main.ANSI_RED + "Not found!" + Main.ANSI_RESET);
        }
    }

    void Bus7(BSTBus bstt) {
        if (bstt.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to delete" + Main.ANSI_RESET);
            return;
        }
        System.out.println("---- Delete By Bcode ----");
        String delete = GetData.getString("Enter bcode to delete by copying: ", "bcode must not empty");
        bstt.deleByCopy(delete);

    }

    void Bus8(BSTBus bstt) {
        if (bstt.isEmpty()) {
            System.out.println(Main.ANSI_RED + "No data to balancing" + Main.ANSI_RESET);
            return;
        }
        System.out.println("---- Balancing tree ----");
        BSTBus balancedBST = new BSTBus();
        storePreOrder(bstt.root, balancedBST);
        storeInOrder(bstt.root, balancedBST);
        storePostOrder(bstt.root, balancedBST);
        bstt.root = balancedBST.root;
        System.out.println(Main.ANSI_GREEN + "Balancing completed!" + Main.ANSI_RESET);

        System.out.println("\t\t\t----Pre-order traverse after balancing----");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-16s | %-17s | \n", "b_code", "bus_name", "seat", "booked", "depart_time", "arrival_time");
        bstt.preOrder(bstt.root);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        System.out.println();

        System.out.println("\t\t\t----In-order traverse after balancing----");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-16s | %-17s | \n", "b_code", "bus_name", "seat", "booked", "depart_time", "arrival_time");
        bstt.inOrder(bstt.root);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        System.out.println();

        System.out.println("\t\t\t----Post-order traverse after balancing----");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-16s | %-17s | \n", "b_code", "bus_name", "seat", "booked", "depart_time", "arrival_time");
        bstt.postOrder(bstt.root);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

    }

    void storePreOrder(NodeBus root, BSTBus bst) {
        if (root != null) {
            bst.add(root.b);
            storePreOrder(root.left, bst);
            storePreOrder(root.right, bst);
        }
    }

    void storeInOrder(NodeBus root, BSTBus bst) {
        if (root != null) {
            storeInOrder(root.left, bst);
            bst.add(root.b);
            storeInOrder(root.right, bst);
        }
    }

    void storePostOrder(NodeBus root, BSTBus bst) {
        if (root != null) {
            storePostOrder(root.left, bst);
            storePostOrder(root.right, bst);
            bst.add(root.b);
        }
    }

    void Bus9(BSTBus bstt) {
        System.out.println("---- Count Number Of Buses ----");
        System.out.println("Number of Buses: ");
        System.out.println(Main.ANSI_GREEN + bstt.count(bstt.root) + Main.ANSI_RESET);

    }
}
