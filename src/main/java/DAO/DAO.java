package DAO;

import java.sql.*;

public class DAO {

    public static String nome_db = "dbripetizioni";
    private static String url1 = "jdbc:mysql://localhost:3306/dbripetizioni";
    private static String user = "root";
    private static String password = "";


    public DAO(String url1, String user, String password) {
        this.url1 = url1;
        this.user = user;
        this.password = password;
    }

    public void registerDriver(){
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*METHODS FOR THE ENTITY utente*/
    public Utente getUtente(String account, String pass) throws SQLException {
        Utente n= null;
        //ArrayList<Utente> out = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url1, user,password);
            if(conn!=null)
                System.out.println("Connected to the database " + nome_db);
            String query = "SELECT * FROM utente WHERE account = ? AND password = ?;";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, account);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            rs.next();
            n = new Utente(rs.getString("account"), rs.getString("password"), rs.getString("ruolo"));
            rs.close();
            st.close();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally{
            conn.close();
        }
        System.out.println("Fine");
        return n;
    }

}
