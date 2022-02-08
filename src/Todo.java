import java.util.Scanner;

public class Todo {
    public void list(String name) {
        Scanner cin = new Scanner(System.in);
        DB database = new DB();
        while (true) {
            System.out.println("0. Show todos");
            System.out.println("1. Change status");
            System.out.println("2. Enter");
            System.out.println("3. Delete");
            System.out.println("4. exit");
            int i = cin.nextInt();
            if (i == 0) {
                System.out.println("--------------------------------------------");
                if (database.isHave(name)) {
                    database.show(name);
                } else {

                    System.out.println("u don`t have any todos");
                }
                System.out.println("--------------------------------------------");
            }
            if (i == 1) {
                System.out.println("--------------------------------------------");
                if (database.isHave(name)) {
                    database.show(name);
                    System.out.println("To change status enter Todos");
                    String x = cin.next();
                    if (database.Change(x, name)) {
                        System.out.println("Succesfully changed");
                    } else {
                        System.out.println("We have some troubles");
                    }
                    database.show(name);

                } else {
                    System.out.println("U don`t have any todos to change status");
                }
                System.out.println("--------------------------------------------");
            }
            if (i == 2) {
                System.out.println("--------------------------------------------");
                System.out.println("Enter to do");
                cin.nextLine();
                String gg = cin.nextLine();
                database.Enter(name, gg);
                System.out.println("--------------------------------------------");
            }
            if (i == 3) {
                System.out.println("--------------------------------------------");
                System.out.println("1. to delete all todos where status done ");
                System.out.println("2. to delete by a todos ");
                int j = cin.nextInt();
                if (j == 1) {
                    database.deleteDone(name);
                } else if (j == 2) {
                    System.out.println("Enter to do");
                    String gg = cin.next();
                    database.delete(name, gg);
                }
                System.out.println("--------------------------------------------");
            }
            if (i == 4) {
                System.exit(0);
            }
        }

    }
}
