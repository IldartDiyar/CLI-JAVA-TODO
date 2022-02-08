import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/Todo";
    static final String USER = "postgres";
    static final String PASS = "123asd123";

    public void connection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        if (conn != null) {
            System.out.println("You successfully connected to database now");

        } else {
            System.out.println("Failed to make connection to database");

        }
    }

    public boolean login(String username, String pass) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmnt = null;
            stmnt = conn.createStatement();
            String sql = "SELECT id FROM USERS WHERE name= '" + username + "' AND password='" + pass + "' ";
            ResultSet rs = stmnt.executeQuery(sql);
            rs.next();
            if (rs.getRow() > 0) {
                // System.out.println("YES");
                return true;
            } else {
                // System.out.println("NO");
                return false;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isHave(String name) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmnt = null;
            stmnt = conn.createStatement();
            String sql = "SELECT id FROM list WHERE name= '" + name + "' ";
            ResultSet rs = stmnt.executeQuery(sql);
            rs.next();
            if (rs.getRow() > 0) {
                // System.out.println("YES");
                return true;
            } else {
                // System.out.println("NO");
                return false;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void show(String name) {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmnt = null;
            stmnt = conn.createStatement();
            String sql = "SELECT * FROM list WHERE name= '" + name + "' ";
            ResultSet rs = stmnt.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                System.out.println("--------------------------------------------");
                System.out.println(
                        i + " " + rs.getString("goal") + " " + (rs.getBoolean("status") ? "Done" : "Not Done"));
                i++;
            }
            System.out.println("--------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void Enter(String name, String goal) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO list (name, goal, status) Values (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, goal);
            preparedStatement.setBoolean(3, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Inserted");
    }

    public boolean Change(String todos, String name) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmnt = null;
            stmnt = conn.createStatement();
            String sql = "Select * FROM list WHERE goal like '" + todos + "' ";
            ResultSet rs = stmnt.executeQuery(sql);
            rs.next();
            boolean state = rs.getBoolean("status");
            String sqli = "UPDATE list SET status = '" + !state + "' WHERE goal ='" + todos + "' ";
            stmnt.executeUpdate(sqli);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void deleteDone(String name) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmnt = null;
            stmnt = conn.createStatement();
            String sql = "DELETE FROM list WHERE status = true  AND name = '" + name + "' ";
            stmnt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void delete(String name, String todos) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmnt = null;
            stmnt = conn.createStatement();
            String sql = "DELETE FROM list WHERE goal = '" + todos + "'AND name = '" + name + "' ";
            stmnt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void CreateAccount(String username, String pass) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO USERS (name, password) Values (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Inserted");
    }

    public boolean check(String name) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmnt = null;
            stmnt = conn.createStatement();
            String sql = "SELECT id FROM USERS WHERE name= '" + name + "' ";
            ResultSet rs = stmnt.executeQuery(sql);
            rs.next();
            if (rs.getRow() > 0) {
                // System.out.println("YES");
                return true;
            } else {
                // System.out.println("NO");
                return false;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void CreateTable() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmnt = null;
        stmnt = conn.createStatement();
        String sql = "CREATE TABLE USERS " +
                "(id SERIAL PRIMARY KEY, " +
                " name VARCHAR(32), " +
                " password VARCHAR(32))";
        stmnt.executeQuery(sql);
    }

    public void CreateTableList() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmnt = null;
        stmnt = conn.createStatement();
        String sql = "CREATE TABLE LIST " +
                "(id SERIAL PRIMARY KEY, " +
                " name VARCHAR(32), " +
                " goal VARCHAR(150))";
        stmnt.executeQuery(sql);

    }

}

// public void CreateTable() throws SQLException {
// try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
// Statement stmt = conn.createStatement();) {
// String sql = "CREATE TABLE LIST " +
// "(id SERIAL PRIMARY KEY, " +
// " name VARCHAR(32), " +
// " goal VARCHAR(150))";

//

// stmt.executeUpdate(sql);

// } catch (SQLException e) {
// System.out.println(e.getMessage());
// }
// System.out.println("Table created");
// }

// public void select() {
// try {
// Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
// Statement stmnt = null;
// stmnt = conn.createStatement();
// String sql = "SELECT * FROM USERS ";
// ResultSet rs = stmnt.executeQuery(sql);
// while (rs.next()) {
// System.out.println(rs.getString("id") + " " + rs.getString("name") + " " +
// rs.getString("password"));
// }
// } catch (SQLException e) {
// System.out.println(e.getMessage());
// }
