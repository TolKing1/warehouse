package uz.tolKing.warehouse.controller.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInteract {
    private final Scanner scanner = new Scanner(System.in);

    public String readLineAndPrint(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                int res = scanner.nextInt();
                scanner.nextLine();
                return res;
            } catch (InputMismatchException e) {
                System.out.println("""
                        ******************************************
                        * Invalid data. Please enter an integer. *
                        ******************************************
                        """);
                scanner.nextLine();
            }
        }

    }
    public double checkDouble(String string){
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            System.out.println("""
                    ******************************************
                    *   Value cannot be String. Try again    *
                    ******************************************
                    """);
        }
        return -1;
    }
    public void printMsg(String message) {
        System.out.println(message);
    }
}
