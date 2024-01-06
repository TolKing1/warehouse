package uz.tolKing.warehouse.controller.impl;

import uz.tolKing.warehouse.controller.Controller;
import uz.tolKing.warehouse.controller.util.UserInteract;
import uz.tolKing.warehouse.controller.util.DBTablePrinter;
import uz.tolKing.warehouse.service.ProductService;

import java.sql.Connection;
import java.util.Scanner;

public class ProductControllerImpl implements Controller {
    protected final UserInteract user = new UserInteract();
    protected final Scanner scan = new Scanner(System.in);
    private final Connection connection;
    private final String tableName;
    String enterPrompt = """
                                
            Enter your prompt to filter data:
            add <id> <name>... - to add item (make sure order and type are correct)
            delete <id> - to delete item with given id
                        
            search <attribute> <value> - to search ("name bowl")
            sort <attribute> <order> - to sort ("name asc" or "price desc")
            range <attribute> <min> <max> - to search by range ("price 2.99 12.5")
                        
                 
            0 - to back main menu""";

    public ProductControllerImpl(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }


    @Override
    public void table() {
        DBTablePrinter.printTable(this.connection, this.tableName);
    }

    @Override
    public void console() {
        while (true) {
            table();
            //print instructions
            user.printMsg(enterPrompt);

            //split input
            String[] next = scan.nextLine().trim().split(" ");

            //exit
            if (next.length == 1 && next[0].equals("0")) {
                break;
            }
            //delete
            else if (next[0].toLowerCase().startsWith("del") && next.length == 2) {
                ProductService.delete(tableName, next[1]);
            }
            //add
            else if (next[0].toLowerCase().startsWith("add")) {
                ProductService.add(tableName, next);
            }
        }
    }
}
