package uz.tolKing.warehouse.controller.impl;

import uz.tolKing.warehouse.controller.Controller;
import uz.tolKing.warehouse.controller.util.TableUtil;
import uz.tolKing.warehouse.service.CategoryService;
import uz.tolKing.warehouse.service.ConnectionService;

import java.util.*;

public class CategoryControllerImpl implements Controller {
    final CategoryService categoryService = new CategoryService();

    @Override
    public void table() {
        List<String> tableList = ConnectionService.getTableNames();
        String tablesString = TableUtil.tableListByOrder(tableList);

        user.printMsg("│ Categories:\n%s│".formatted(tablesString));
    }

    @Override
    public void console() {
        main:
        while (true) {
            user.printMsg("+"+"─".repeat(53));
            //print users
            table();

            //table name
            user.printMsg(
                    "│ 0 or empty - to quit ❌");
            System.out.print(
                    "│ Enter name of new category:  ");
            String name = scan.nextLine().trim().toLowerCase();
            //close
            if (name.isEmpty() || name.equals("0")) {
                break;
            }
            Map<String, String> params = new LinkedHashMap<>();

            //params
            while (true) {
                user.printMsg("+"+"-".repeat(39));
                user.printMsg("〡 Your table:");
                System.out.print("〡     ");
                params.forEach((k, v) -> {
                    System.out.printf(k + " " + v + " | ");
                });
                user.printMsg("\n〡");
                String prompt = """
                        〡 Enter column: <name> <type> like 'name varchar(200)'
                        〡
                        〡 0 - quit              ❌
                        〡 empty - to confirm    ✅""";
                user.printMsg(prompt);
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
                    String error = """
                            *************************************************
                            * Incorrect data. Try again like '<name> <type> *
                            *************************************************
                            """;
                    user.printMsg(error);
                }
            }

            categoryService.add(name,params);

        }
    }
}
