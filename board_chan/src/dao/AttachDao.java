package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Attach;
import utils.DBConn;

public class AttachDao {
	private static AttachDao attachDao = new AttachDao();
	
	public static AttachDao getInstacne() {
		return attachDao;
	}
	
	private AttachDao() {}

	public List<Attach> list() {
		List<Attach> list = new ArrayList<>();
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM TBL_ATTACH";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx = 1;
				Attach attach = new Attach(
					rs.getString(idx++),
					rs.getString(idx++),
					rs.getString(idx++),
					rs.getBoolean(idx++),
					rs.getInt(idx++),
					rs.getString(idx++)
				);
				list.add(attach);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Attach get(String id) {
		Attach attach = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM TBL_ATTACH WHERE ID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx = 1;
				attach = new Attach(
					rs.getString(idx++),
					rs.getString(idx++),
					rs.getString(idx++),
					rs.getBoolean(idx++),
					rs.getInt(idx++),
					id
					);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attach;
	}
	
	public void insert(Attach attach) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "INSERT INTO TBL_ATTACH "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setString(idx++, attach.getUuid());
			pstmt.setString(idx++, attach.getOrigin());
			pstmt.setString(idx++, attach.getPath());
			pstmt.setBoolean(idx++, attach.isImage());
			pstmt.setInt(idx++, attach.getOrd());
			pstmt.setString(idx++, attach.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeNull(String id) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE (SELECT ID FROM TBL_ATTACH WHERE ID = ?)\r\n"
					+ "SET ID = NULL";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeNull() {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "DELETE TBL_ATTACH\r\n"
					+ "WHERE ID IS NULL";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
