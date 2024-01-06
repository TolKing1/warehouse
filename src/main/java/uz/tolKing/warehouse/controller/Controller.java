package uz.tolKing.warehouse.controller;


import uz.tolKing.warehouse.controller.util.UserInteract;

import java.util.Scanner;

public interface Controller {
    UserInteract user = new UserInteract();
    Scanner scan = new Scanner(System.in);
    String extraData = "\n| Encountered extra data. Try again |\n";
    void table();
    void console();
}
