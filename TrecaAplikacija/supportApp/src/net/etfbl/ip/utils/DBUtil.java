package net.etfbl.ip.utils;

import java.sql.*;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class DBUtil {
    private static final String jdbcURL;
    private static final String username;
    private static final String password;

    static {
            ResourceBundle bundle = PropertyResourceBundle.getBundle("net.etfbl.ip.utils.db");
            jdbcURL = bundle.getString("jdbcURL");
            username = bundle.getString("username");
            password = bundle.getString("password");
    }

    public static PreparedStatement prepareStatement(Connection c, String sql, boolean retGenKeys, Object... values) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql, retGenKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);

        for(int i = 0; i< values.length; i++) {
            ps.setObject(i+1, values[i]);
        }
        return ps;
    }

    public static Connection getConnection() throws SQLException {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return DriverManager.getConnection(jdbcURL, username, password);
    }

    public static void close(Connection c) {
        if(c != null)
            try {
                c.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
    }

    public static void close(Statement s) {
        if(s != null)
            try{
                s.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
    }
    public static void close(ResultSet rs) {
        if(rs != null)
            try {
                rs.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void close(ResultSet rs, Statement s, Connection c) {
        close(rs);
        close(s);
        close(c);
    }
    public static void close(Statement s, Connection c) {
        close(s);
        close(c);
    }
}
