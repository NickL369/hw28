import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/sales_database";
        String user = "postgres";
        String password = "qwe88132";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

//створення і заповнення таблиці я файликом додаю
            System.out.println("All records:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM sales");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("product") + " " +
                        rs.getBigDecimal("price") + " " + rs.getInt("quantity"));
            }


            System.out.println("\nFirst 2 records:");
            rs = stmt.executeQuery("SELECT * FROM sales LIMIT 2");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("product") + " " +
                        rs.getBigDecimal("price") + " " + rs.getInt("quantity"));
            }


            System.out.println("\nTotal value:");
            rs = stmt.executeQuery("SELECT SUM(price * quantity) AS total FROM sales");
            if (rs.next()) {
                System.out.println("Total: " + rs.getBigDecimal("total"));
            }


            System.out.println("\nGrouped by product:");
            rs = stmt.executeQuery("SELECT product, SUM(quantity) AS total_quantity, AVG(price) AS avg_price FROM sales GROUP BY product");
            while (rs.next()) {
                System.out.println(rs.getString("product") + " " + rs.getInt("total_quantity") + " " + rs.getBigDecimal("avg_price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
