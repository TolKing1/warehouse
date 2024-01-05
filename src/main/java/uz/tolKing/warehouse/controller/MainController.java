package uz.tolKing.warehouse.controller;


import uz.tolKing.warehouse.controller.util.TableUtil;
import uz.tolKing.warehouse.service.ConnectionService;

import java.util.List;

public class MainController {
    private final UserInteract user;
    private ConnectionService connectionService;
    private static final int
            QUIT = 0,
            ADD_ADMIN = -1,
            ADD_CATEGORY = -2;

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
        while (true){
            String username,password;
            System.out.println();
            username = user.readLineAndPrint("\nPlease enter your user name:");
            password = user.readLineAndPrint("Please enter your password:");
            connectionService = new ConnectionService(username,password);
            if (connectionService.getConnection() != null){
                user.printMsg("\nHello, " + connectionService.getUser());
                boolean mainFlag = true;
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
                    switch (mainInput) {
                        case ADD_ADMIN -> System.out.println("admin");
                        case ADD_CATEGORY -> System.out.println("category");
                        case QUIT -> mainFlag = false;
                        default -> user.printMsg("\n| Wrong command (%s). Try again |\n".formatted(mainInput));

                    }

                }
            }
        }

    }


}
