package net.etfbl.ip.dao;

import java.sql.*;
import net.etfbl.ip.dto.*;
import net.etfbl.utils.DBUtil;

public class AvatarDAO {
	
	private static final String SQL_INSERT_FOR_USER_ID = "INSERT INTO avatar (fk_user_avatar, name) VALUES (?, ?)";
	private static final String SQL_DELETE_BY_USER_ID = "DELETE FROM avatar WHERE fk_user_id=?";
	
	public static int delete(Integer id) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {id};
			ps = DBUtil.prepareStatement(c, SQL_DELETE_BY_USER_ID, true, values);
			retValue = ps.executeUpdate();
			if (retValue != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					retValue = 1;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		return retValue;
	}
	
	public static int insert(Avatar avatar) {
		int retValue = 0;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {avatar.getFk_user_avatar(), avatar.getName()};
			ps = DBUtil.prepareStatement(c, SQL_INSERT_FOR_USER_ID, true, values);
			retValue = ps.executeUpdate();
			if (retValue != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					retValue = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return retValue;
	}

}
