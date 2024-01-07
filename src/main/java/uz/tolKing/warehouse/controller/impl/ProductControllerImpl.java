package uz.tolKing.warehouse.controller.impl;

import uz.tolKing.warehouse.controller.Controller;
import uz.tolKing.warehouse.controller.util.DBTablePrinter;
import uz.tolKing.warehouse.service.ProductService;

import java.sql.Connection;

public class ProductControllerImpl implements Controller {
    private final Connection connection;
    private final String tableName;
    final String enterPrompt = """
            Enter your prompt:
                        
            add <id> <name>... - to add item (make sure order and type are correct)
            delete <id> - to delete item with given id
                        
            search <attribute> <value> - example:       search name Cloth
            sort <attribute> <order> - example:         sort id asc
            range <attribute> <min> <max> - example:    range price 1 15
                        
            P.s: Make sure you are using '.' instead of ',' for decimals
                        
            0 - to back main menu ️ ⬅️
            """;

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

        //print table
        table();

        //print instructions
        user.printMsg(enterPrompt);

        while (true) {
            //split input
            String[] next = scan.nextLine().trim().split(" ");

            ProductService productService = new ProductService();

            //exit
            if (next.length == 1 && next[0].equals("0")) {
                break;
            }
            user.printMsg("-".repeat(40));
            //delete
            if (next[0].toLowerCase().startsWith("del") && next.length == 2) {
                productService.delete(tableName, next[1]);
                table();
            }
            //add
            else if (next[0].toLowerCase().startsWith("add")) {
                productService.add(tableName, next);
                table();
            }
            //search
            else if (next[0].toLowerCase().startsWith("search") && next.length == 3) {
                DBTablePrinter.printResultSet(productService.search(tableName, next));
            }
            //sort
            else if (next[0].toLowerCase().startsWith("sort") && next.length == 3) {
                DBTablePrinter.printResultSet(productService.sort(tableName, next));
            }
            //rang
            else if (next[0].toLowerCase().startsWith("range") && next.length == 4) {
                DBTablePrinter.printResultSet(productService.range(tableName, next));
            } else {
                user.printMsg(extraData);
            }
        }
    }
}
