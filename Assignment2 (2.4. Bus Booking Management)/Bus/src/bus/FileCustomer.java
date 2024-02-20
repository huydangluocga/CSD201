/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import static bus.GetData.getYN;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author xeban
 */
public class FileCustomer {

    private static Scanner sc = new Scanner(System.in);

    public static void outputFile(LinkedList list, String path) throws IOException {
        FileWriter out = new FileWriter(path);
        NodeCus p = list.head;

        while (p != null) {

            String ccode = p.code.toString();
            out.write(ccode + "|");
            String cusName = p.name.toString();
            out.write(cusName + "|");
            String phone = p.phone.toString();
            out.write(phone + "|" + "\n");

            p = p.next;
        }
        out.flush();
        out.close();
        System.out.println(Main.ANSI_GREEN + "Saved successfully!" + Main.ANSI_RESET);
    }

    public static void saveData(LinkedList l) throws IOException {
        boolean asked = false;
//        while (true) {
            if (!asked) {
                boolean confirm = getYN("Do you want to create a new save file? (Y/N): ");
                if (!confirm) {
                    System.out.println(Main.ANSI_RED + "File not saved" + Main.ANSI_RESET);
                    return;
                }
                asked = true;
            }
            String nameFile = GetData.getString("Enter the file name to save data: ", "file name must not be empty");
            if (!nameFile.toLowerCase().endsWith(".txt")) {
                nameFile += ".txt";
            }
            File file = new File(nameFile);
            if (file.exists()) {
                boolean confirm = getYN(Main.ANSI_RED + "File already exists. Do you want to overwrite it? (Y/N): " + Main.ANSI_RESET);
                if (confirm) {
                    outputFile(l, nameFile);
                } else {
                    System.out.println(Main.ANSI_RED + "File is not overriden!" + Main.ANSI_RESET);
                    return;
                }
            } else {
                System.out.println(Main.ANSI_GREEN + "<File is created>" + Main.ANSI_RESET);
                outputFile(l, nameFile);
            }
//        }
    }
}