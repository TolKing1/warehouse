package uz.tolKing.warehouse.controller.impl;

import uz.tolKing.warehouse.controller.Controller;
import uz.tolKing.warehouse.controller.util.TableUtil;
import uz.tolKing.warehouse.service.CategoryService;
import uz.tolKing.warehouse.service.ConnectionService;

import java.util.*;

public class CategoryController implements Controller {
    final CategoryService categoryService = new CategoryService();


    @Override
    public void table() {
        List<String> tableList = ConnectionService.getTableNames();
        String tablesString = TableUtil.tableListByOrder(tableList);

        user.printMsg("Tables:\n%s".formatted(tablesString));
    }

    @Override
    public void console() {
        main:
        while (true) {
            //print users
            table();

            //table name
            user.printMsg("Enter name of category: <name> like 'pillow'\n\nLeave 0 or empty to quit");
            String name = scan.nextLine().trim().toLowerCase();
            //close
            if (name.isEmpty() || name.equals("0")) {
                break;
            }
            Map<String, String> params = new LinkedHashMap<>();

            //params
            while (true) {
                user.printMsg("-".repeat(20));
                user.printMsg("Your table:\n");
                params.forEach((k, v) -> {
                    System.out.printf(k + " " + v + " | ");
                });
                user.printMsg("\n");
                user.printMsg("Enter data: <name> <type> like 'name text' \n\n Leave 0  to quit\n Leave empty to confirm");
                String[] next = scan.nextLine().trim().split(" ");
                //confirm
                if (next[0].isEmpty()) {
                    break;
                }
                //cancel all
                else if (next.length == 1 && Objects.equals(next[0], "0")) {
                    break main;
                }
                //add
                else if (next.length == 2) {
                    params.put(next[0], next[1]);
                } else {
                    user.printMsg("\n| Incorrect data. Try again like '<name> <type>'|\n");
                }
            }

            categoryService.add(name,params);

        }
    }
}
