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
public class FileBus {

    static Scanner sc = new Scanner(System.in);
    GetData getData = new GetData();

    void save(NodeBus p) {

    }

    public static void outputFile(BSTBus bstt, FileWriter out, NodeBus p) throws IOException {

        if (p != null) {
            outputFile(bstt, out, p.left);

            String bcode = p.b.getCode().toString();
            out.write(bcode + "|");
            String busName = p.b.getName().toString();
            out.write(busName + "|");
            String seat = String.valueOf(p.b.getSeat());
            out.write(seat + "|");
            String booked = String.valueOf(p.b.getBooked());
            out.write(booked + "|");
            String departTime = String.valueOf(p.b.getDepart_time());
            out.write(departTime + "|");
            String arriveTime = String.valueOf(p.b.getArrival_time());
            out.write(arriveTime + "|" + "\n");

            outputFile(bstt, out, p.right);

        }

    }

    public static void saveData(BSTBus bstt) throws IOException {
        boolean asked = false;
        while (true) {
            if (!asked) {
                boolean confirm = getYN("Do you want to create a new save file? (Y/N): ");
                if (!confirm) {
                    System.out.println(Main.ANSI_RED + "File not saved" + Main.ANSI_RESET);
                    return;
                }
                asked = true;
            }
            String nameFile = GetData.getString("Enter the file name to save data: ", "File name must not be empty");
            File file = new File(nameFile + ".txt");
            if (file.exists()) {
                boolean override = getYN("File already exists. Do you want to override it? (Y/N): ");
                if (!override) {
                    System.out.println(Main.ANSI_RED + "File is not overriden" + Main.ANSI_RESET);
                    return;
                }
            }

            if (file.createNewFile() || file.exists()) {
                System.out.println(Main.ANSI_GREEN + "File is created!!" + Main.ANSI_RESET);
                FileWriter out = new FileWriter(file);
                outputFile(bstt, out, bstt.root);
                out.flush();
                out.close();
                System.out.println(Main.ANSI_GREEN + "Saved Successfully!" + Main.ANSI_RESET);
                break;
            } else {
                System.out.println(Main.ANSI_RED + "File already exists!" + Main.ANSI_RESET);
            }
        }
    }
}
