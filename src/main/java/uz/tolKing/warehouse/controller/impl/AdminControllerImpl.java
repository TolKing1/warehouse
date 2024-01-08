package uz.tolKing.warehouse.controller.impl;

import uz.tolKing.warehouse.controller.Controller;
import uz.tolKing.warehouse.service.AdminService;

public class AdminControllerImpl implements Controller {
    final AdminService adminService = new AdminService();

    final String enterPrompt = """
            │ Enter your prompt:
            │ add <login> <password> - to add new admin
            │           \s
            │ 0 - to back main menu ⬅️
            """;

    @Override
    public void table() {
        String users = adminService.getUserList();
        String userList = """
                        │ User list:
                        %s
                        """.formatted(users);
        System.out.println(userList);
    }

    @Override
    public void console() {
        while (true) {
            user.printMsg("+"+"─".repeat(53));
            //print users
            table();

            //print instructions
            user.printMsg(enterPrompt);

            String[] next = scan.nextLine().trim().split(" ");
            user.printMsg("+"+"─".repeat(53));
            //exit
            if (next.length == 1 && next[0].equals("0")) {
                break;
            }
            //add
            else if (next[0].toLowerCase().startsWith("add") && next.length == 3) {
                adminService.add(next[1], next[2]);
            } else {
                user.printMsg(extraData);
            }

        }
    }
}
