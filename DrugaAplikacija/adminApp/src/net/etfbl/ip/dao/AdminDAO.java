package net.etfbl.ip.dao;

import java.sql.*;

import net.etfbl.ip.dto.Admin;
import net.etfbl.utils.DBUtil;

public class AdminDAO {
	
	private static final String SELECT_ADMIN_BY_USERNAME_AND_PASSWORD = "SELECT * FROM admin WHERE username = ? AND password = ?";
	
	public static Admin getAdminByUsernameAndPassword(String username, String password) {
		Admin retValue = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {username, password};
			ps = DBUtil.prepareStatement(c, SELECT_ADMIN_BY_USERNAME_AND_PASSWORD, true, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				retValue = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		return retValue;
	}

}
