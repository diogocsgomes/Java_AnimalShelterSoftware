import java.sql.*;

public class Main {

    public static void listUsers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM utilizadores2 ";

        DatabaseManager db = new DatabaseManager();
        Connection conn = db.getConn();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("nome") + "\t" + rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean InsertUser(String nome,String password) throws SQLException, ClassNotFoundException {

        DatabaseManager db = new DatabaseManager();
        String sql = "INSERT INTO utilizadores2(nome,password) VALUES(?,?)";
        int afected = 0;

        try (Connection connection = db.getConn();
             PreparedStatement pstmt = connection.prepareStatement(sql))
        {
            pstmt.setString(1,nome);
            pstmt.setString(2,password);
             afected = pstmt.executeUpdate();

        }
        catch (Exception e)
        {
            return false;
        }

    if(afected == 0)
        return false;
    return true;

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Hello world!");

        System.out.println(InsertUser("Danilo","Pereira"));
        listUsers();

    }
}