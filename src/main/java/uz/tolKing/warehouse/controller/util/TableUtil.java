package uz.tolKing.warehouse.controller.util;

import java.util.List;

public class TableUtil {
    public static String tableListByOrder(List<String> tableList){
        StringBuilder tablesString = new StringBuilder();
        for (int i = 0; i < tableList.size(); i++) {
            tablesString.append(i+1).append(" - ").append(tableList.get(i)).append("\n");
        }
        return tablesString.toString();
    }
}
