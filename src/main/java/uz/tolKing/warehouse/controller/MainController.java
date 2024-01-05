package uz.tolKing.warehouse.controller;


import uz.tolKing.warehouse.dao.ConnectionDAO;
import uz.tolKing.warehouse.service.ConnectionService;

import java.sql.Connection;
import java.sql.SQLException;

public class MainController {
    private final UserInteract user;
    private ConnectionService connectionService;
    private static final int PRODUCT_MENU = 1,
            DISHES_MENU = 2,
            LINENS_MENU = 3,
            QUIT = 0;

    public MainController() {
        user = new UserInteract();
    }


    public void mainConsole() {
        System.out.println("Application Name: Warehouse Search System");
        System.out.println("Version: 1.1");
        System.out.println("Creation Date: 2024-01-04");
        System.out.println("Developer: TolKing");
        System.out.println("Name: To'lqin Oltiboyev");
        System.out.println("Email: shaxzodsdf@gmail.com");
        boolean auth = true;
        while (auth){
            String username,password;

            System.out.println("\nPlease enter your user name:");
            username = user.readLine();
            System.out.println("Please enter your password:");
            password = user.readLine();
            connectionService = new ConnectionService(username,password);
            if (connectionService.getConnection() != null){
                boolean mainFlag = true;
                while (mainFlag) {
                    String usernameMeta = connectionService.getUser();
                    int mainInput = user.readInt(
                            "\nHello, " + usernameMeta +"""   
                       
                    Available Commands:
                    1 - List of all Products
                    2 - List of Dishes
                    3 - List of Linens

                    0 - Close
                    
                    Enter integer:
                    """);
                    switch (mainInput) {

                        case QUIT -> mainFlag = false;
                        default -> user.printMsg("\n| Wrong command (%s). Try again |\n".formatted(mainInput));

                    }

                }
            }

        }

    }


}
