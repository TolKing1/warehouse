package uz.tolKing.warehouse.controller;


import uz.tolKing.warehouse.controller.util.UserInteract;

import java.sql.Connection;
import java.util.Scanner;

public interface Controller {
    final UserInteract user = new UserInteract();
    final Scanner scan = new Scanner(System.in);
    String extraData = "\n| Encountered extra data. Try again |\n";
    void table();
    void console();
}
