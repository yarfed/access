package dao;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;

import java.io.File;

/**
 * Created by user on 23.12.2014.
 */
public class DatabaseActions {
    public static boolean open (String dbName) {
        try {
            Database db = DatabaseBuilder.open(new File(dbName));
            for (String str: db.getTableNames()){
                System.out.println(str);
            }
           // Table table = db.getTable("Контакты");
           // String name = "петя";
           // String email = "petya@go.com";
           // Integer codeOfType = 1;
           // table.addRow(Column.AUTO_NUMBER, name, email, codeOfType);

           db.close();
            return true;
        } catch (Exception ex) {
            System.out.println("no such file exist");
            return false;
        }
    }
}
