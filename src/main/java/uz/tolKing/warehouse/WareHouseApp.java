package uz.tolKing.warehouse;

import uz.tolKing.warehouse.controller.MainController;
import uz.tolKing.warehouse.dao.ConnectionDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class WareHouseApp {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.mainConsole();
    }
}
