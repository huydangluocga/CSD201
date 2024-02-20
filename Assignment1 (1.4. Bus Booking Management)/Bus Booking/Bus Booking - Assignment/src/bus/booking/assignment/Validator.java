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
public class Validator {

    public boolean Vali(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input format");
            return false;
        }
        int in = Integer.parseInt(input);
        if (in <= 0) {
            System.out.println("Input must be larger than 0");
            return false;
        }
        return true;
    }
        public boolean Vali2(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input format");
            return false;
        }
        int in = Integer.parseInt(input);
        if (in < 0) {
            System.out.println("Input must at least 0");
            return false;
        }
        return true;
    }

    public boolean Vali(String input, int high) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input format");
            return false;
        }
        int in = Integer.parseInt(input);
        if (in <= 0) {
            System.out.println("Input must be larger than 0");
            return false;
        }
        if (in > high) {
            System.out.println("Input must not exceed number of seats on the bus <" +high +">");
            return false;
        }
        return true;
    }

    public boolean Vald(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input format");
            return false;
        }
        double in = Double.parseDouble(input);
        if (in <= 0) {
            System.out.println("Input must be at least 0");
            return false;
        }
        return true;
    }

    public boolean Vald(String input, double low) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input format");
            return false;
        }
        double in = Double.parseDouble(input);
        if (in <= low) {
            System.out.println("Input must be larger than departure time");
            return false;
        }
        return true;
    }
        public boolean Valc(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input format");
            return false;
        }
        double in = Double.parseDouble(input);
        if (in < 1.1 || in > 3.4) {
            System.out.println("Invalid choice");
            return false;
        }
        return true;
    }
}
