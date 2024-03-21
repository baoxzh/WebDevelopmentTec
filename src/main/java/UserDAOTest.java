import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOTest {

    @BeforeClass
    public static void createTestDatabase() {
        JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        try {
        Connection conn = cp.getConnection();
        Statement stmt = conn.createStatement();

            stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255))");

        stmt.execute("INSERT INTO users (id, name) VALUES (1, 'John')");
        stmt.close();
        conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetUserName() {
        UserDAO dao = new UserDAO();
        String name = dao.getUserName(1);
        Assert.assertEquals("John", name);
    }
}