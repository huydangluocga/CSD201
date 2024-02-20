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
public class GetData {

    private static final Scanner sc = new Scanner(System.in);

    public int getInteger(String msg, String err, int min, int max) {
        Scanner sc = new Scanner(System.in);
        String input;
        int result;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();
            if (input.length() == 0 || input.equals("")) {
                System.err.println(Main.ANSI_RED + "input must not empty" + Main.ANSI_RESET);
                continue;
            }
            try {
                result = Integer.parseInt(input);
                if (result < min || result > max) {
                    System.out.println(Main.ANSI_RED + "must in range from " + min + " to " + max + Main.ANSI_RESET);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println(Main.ANSI_RED + err + Main.ANSI_RESET);
            }

        }
        return result;
    }

    public int getSeat(String msg, String err, int max) {
        Scanner sc = new Scanner(System.in);
        String input;
        int result;
        boolean isValidInput = false;
        while (true) {
            System.out.print("\u001B[0m" + msg + "\u001B[32m");
            input = sc.nextLine().trim();
            if (input.length() == 0 || input.equals("")) {
                System.err.println("\u001B[0m" + "Input must not be empty");
                continue;
            }
            try {
                result = Integer.parseInt(input);
                if (result > max) {
                    System.out.println(Main.ANSI_RED + "There is only " + max + " seat left!" + Main.ANSI_RESET);
                    String ask = getString("Do you want to proceed with booking the remaining seats? (Y/N): ", "must not empty");
                    while (true) {
                        if (ask.equalsIgnoreCase("Y")) {
                            isValidInput = true;
                            break;
                        } else if (ask.equalsIgnoreCase("N")) {
                            return 0;
                        } else {
                            System.out.println(Main.ANSI_RED + "Must enter y/n" + Main.ANSI_RESET);
                            ask = getString("Do you want to proceed with booking the remaining seats? (Y/N): ", "must not empty");
                        }
                    }
                } else {
                    isValidInput = true;
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println(Main.ANSI_RED + err + Main.ANSI_RESET);
            }
        }
        if (isValidInput) {
            return result;
        } else {
            return 0;
        }
    }

    public static double getTimeInput(String msg, String timeRegex, String err) {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();
            if (input.matches(timeRegex)) {
                return convertTimeToFloat(input);
            } else {
                System.out.println(Main.ANSI_RED + err + Main.ANSI_RESET);
            }
        }
    }

    public static double convertTimeToFloat(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        return hours + (minutes / 60.0) + (seconds / 3600.0);
    }

    public static String getString(String msg, String err) {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();
            if (input.equals("") || input.length() == 0) {
                System.out.println(Main.ANSI_RED + err + Main.ANSI_RESET);
                continue;
            }
            break;
        }
        return input;
    }

    public static String getString2(String msg, String err, String regex, String err2) {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();
            if (input.equals("") || input.length() == 0) {
                System.out.println(Main.ANSI_RED + err + Main.ANSI_RESET);
                continue;
            }
            // "^[a-zA-Z0-9 ]{1,10}$"
            if (!input.matches(regex)) {
                System.out.println(Main.ANSI_RED + err2 + Main.ANSI_RESET);
                continue;
            }
            break;
        }
        return input;
    }

    public double getDouble(String msg, String err, double min, double max) {
        Scanner sc = new Scanner(System.in);
        String input;
        double result;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();
            if (input.length() == 0 || input.equals("")) {
                System.err.println(Main.ANSI_RED + "input must not empty" + Main.ANSI_RESET);
                continue;
            }
            try {
                result = Double.parseDouble(input);
                if (result < min || result > max) {
                    System.out.println(Main.ANSI_RED + "must in range from " + min + " to " + max + Main.ANSI_RESET);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println(Main.ANSI_RED + err + Main.ANSI_RESET);
            }

        }
        return result;
    }

    public static boolean getYN(String msg) {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(Main.ANSI_RED + "must not empty" + Main.ANSI_RESET);
                continue;
            }
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println(Main.ANSI_RED + "Must enter y/n" + Main.ANSI_RESET);
            }
        }
    }

    public static String getPhone(String msg, String err) {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();
            if (input.equals("") || input.length() == 0) {
                System.out.println(Main.ANSI_RED + err + Main.ANSI_RESET);
                continue;
            }
            if (!input.matches("\\d+")) {
                System.out.println(Main.ANSI_RED + "Phone number should contain digits only." + Main.ANSI_RESET);
                continue;
            }
            break;
        }
        return input;
    }
}
