package uz.tolKing.warehouse.controller;


import uz.tolKing.warehouse.controller.impl.ProductControllerImpl;
import uz.tolKing.warehouse.controller.util.TableUtil;
import uz.tolKing.warehouse.controller.util.UserInteract;
import uz.tolKing.warehouse.service.ConnectionService;

import java.sql.Connection;
import java.util.List;

public class MainController {
    private static final int
            QUIT = 0,
            ADD_ADMIN = -1,
            ADD_CATEGORY = -2;
    private final UserInteract user;
    private ConnectionService connectionService;

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
        while (true) {
            //ask login and password
            String username, password;
            username = user.readLineAndPrint("\nPlease enter your user name:");
            password = user.readLineAndPrint("Please enter your password:");

            //try to connect
            connectionService = new ConnectionService(username, password);
            Connection connection = ConnectionService.getConnection();

            if (ConnectionService.getConnection() != null) {
                boolean mainFlag = true;

                //print username
                user.printMsg("\nHello, " + connectionService.getUser());

                //print available commands and tables
                while (mainFlag) {
                    List<String> tableList = connectionService.getTableNames();
                    String tablesString = TableUtil.tableListByOrder(tableList);

                    user.printMsg("-".repeat(20));
                    int mainInput = user.readInt(
                            """
                                    Available tables:
                                    %s

                                    0 - Close
                                    Enter integer:"""
                                    .formatted(tablesString));
                    user.printMsg("-".repeat(20));

                    //wait for command input
                    if (mainInput > 0 && mainInput - 1 <= tableList.size()) {
                        ProductControllerImpl productController = new ProductControllerImpl(connection,tableList.get(mainInput - 1));
                        productController.console();
                    } else if (mainInput == ADD_ADMIN) {
                        System.out.println("admin");
                    } else if (mainInput == ADD_CATEGORY) {
                        System.out.println("category");
                    } else if (mainInput == QUIT) {
                        mainFlag = false;
                    } else {
                        user.printMsg("\n| Wrong command (%s). Try again |\n".formatted(mainInput));
                    }
                }
            }
        }

    }


}
