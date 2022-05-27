package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Reply;
import utils.DBConn;

public class ReplyDao {
	private static ReplyDao replyDao = new ReplyDao();
	
	public static ReplyDao getInstacne() {
		return replyDao;
	}
	
	private ReplyDao() {}
	
	public List<Reply> list(Long bno) {
		List<Reply> list = new ArrayList<Reply>();
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT /*+ INDEX(TBL_REPLY IDX_REPLY_RNO_BNO) */ * FROM TBL_REPLY WHERE BNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,  bno);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx = 1;
				Reply reply = new Reply(rs.getLong(idx++), rs.getString(idx++),
						rs.getString(idx++), rs.getLong(idx++), rs.getString(idx++));
				list.add(reply);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void register(Reply reply) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "INSERT INTO TBL_REPLY (RNO, CONTENT, BNO, WRITER)"
					+ " VALUES (SEQ_REPLY.NEXTVAL, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getContent());
			pstmt.setLong(2, reply.getBno());
			pstmt.setString(3, reply.getWriter());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remove(Long rno) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "DELETE TBL_REPLY\r\n" + 
					"WHERE RNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, rno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeAll(Long bno) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "DELETE TBL_REPLY\r\n" + 
					"WHERE BNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modifyByWriter(String writer) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE TBL_REPLY SET\r\n" + 
					"WRITER = '(탈퇴회원)' \r\n" + 
					"WHERE WRITER = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}