import java.sql.SQLException;
import java.util.Scanner;

public class Register {

    public void register() throws SQLException {
        DB database = new DB();
        Scanner cin = new Scanner(System.in);
        String username;
        System.out.println("Enter new username ");

        username = cin.next();
        if (database.check(username)) {
            System.out.println("Create a new username");
        } else {

            System.out.println("Enter new password ");
            String password;
            password = cin.next();
            database.CreateAccount(username, password);
            System.out.println("ur account created");
        }
    }
}
