package net.etfbl.ip.service;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import net.etfbl.ip.beans.*;
import net.etfbl.ip.utils.DBUtil;

public class MessageManager implements Serializable {

	private static final long serialVersionUID = -8793493569387436077L;
	
	private final String SELECT_ALL_MESSAGES = "SELECT * FROM message WHERE status = 0";
	private final String SELECT_MESSAGE_BY_ID = "SELECT * FROM message WHERE id = ?";
	private final String UPDATE_MESSAGE_STATUS_BY_ID = "UPDATE message SET status = 1 WHERE id = ?";
	private final String SELECT_MESSAGE_BY_TEXT = "SELECT * FROM message WHERE status = 0 AND LOWER(message.text) LIKE LOWER(concat('%', ?,'%'))";
	public MessageManager() {}
	
	public List<MessageBean> getMessageSearch(String search) {
		List<MessageBean> msg = new ArrayList<>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {search};
			ps = DBUtil.prepareStatement(c, SELECT_MESSAGE_BY_TEXT, false, values);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				msg.add(new MessageBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getByte(4)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		return msg;
	}
	
	public int updateStatusById(Integer id) {
		int retValue = 0;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {id};
			ps = DBUtil.prepareStatement(c, UPDATE_MESSAGE_STATUS_BY_ID, false, values);
			retValue = ps.executeUpdate();
			if (retValue != 0)
				retValue = 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return retValue;
	}
	
	public MessageBean getMessageById(Integer id) {
		MessageBean retValue = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {id};
			ps = DBUtil.prepareStatement(c, SELECT_MESSAGE_BY_ID, false, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				retValue = new MessageBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getByte(4));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		
		return retValue;
	}
	
	public List<MessageBean> getAllMessages() {
		List<MessageBean> messages = new ArrayList<>();
		
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SELECT_ALL_MESSAGES);
			
			while(rs.next()) {
				messages.add(new MessageBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getByte(4)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}
		
		return messages;
	}
}
