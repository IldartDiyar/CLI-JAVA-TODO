import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner cin = new Scanner(System.in);
        DB database = new DB();
        int x;
        database.connection();
        // database.CreateTable();
        // database.CreateTableList();
        while (true) {
            System.out.println("1. Log in");
            System.out.println("2. Sign in");
            System.out.println("3. Exit");
            x = cin.nextInt();

            switch (x) {
                case 1:
                    Auth log = new Auth();
                    String name = log.log();
                    if (name != null) {
                        Todo tod = new Todo();
                        tod.list(name);
                    } else {
                        System.exit(0);
                    }
                case 2:
                    Register reg = new Register();
                    reg.register();
                case 3:
                    System.exit(0);
            }
        }
    }
}
