import java.sql.*;

public class UserDAO {
    public String getUserName(int userId) {
        // 伪代码，演示目的
        try {
            String userName = "";
            // 使用 H2 数据库的 JDBC URL，此处以内存模式运行
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM users WHERE id = " + userId);
            if (rs.next()) {
                userName = rs.getString("name");
            }
            rs.close();
            stmt.close();
            connection.close();
            return userName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}