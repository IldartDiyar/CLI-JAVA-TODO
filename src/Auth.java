import java.sql.SQLException;
import java.util.Scanner;

public class Auth {
    public String log() throws SQLException {
        DB database = new DB();
        Scanner cin = new Scanner(System.in);
        String username;
        System.out.println("Enter your username ");
        username = cin.next();
        
        System.out.println("Enter your password ");
        String password;
        password = cin.next();
        if (database.login(username, password)) {
            return username;
        } else {
            System.out.println("Your password is wrong");
            return null;
        }
        
    }
}