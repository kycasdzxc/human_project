package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Chat;
import utils.DBConn;

public class ChatDao {
	private static ChatDao chatDao = new ChatDao();
	
	public static ChatDao getInstacne() {
		return chatDao;
	}
	
	private ChatDao() {}
	

	public List<Chat> list(String sender, String receiver) {
		List<Chat> list = new ArrayList<Chat>();
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT /*+ INDEX_DESC(TBL_CHAT PK_CHAT) */ * FROM TBL_CHAT\r\n"
					   + "WHERE SENDER = ? AND RECEIVER = ? OR SENDER = ? AND RECEIVER = ?";
			
			if(sender.equals(receiver)) {
				sql += " OR (SENDER = ? OR RECEIVER = ?)";
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++,  sender);
			pstmt.setString(idx++,  receiver);
			pstmt.setString(idx++,  receiver);
			pstmt.setString(idx++,  sender);
			if(sender.equals(receiver)) {
			pstmt.setString(idx++,  sender);
			pstmt.setString(idx++,  receiver);
			}
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idx = 1;
				Chat chat = new Chat(rs.getLong(idx++), rs.getString(idx++), rs.getString(idx++),
						rs.getString(idx++), rs.getString(idx++), rs.getString(idx++));
				list.add(chat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void register(Chat chat) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "INSERT INTO TBL_CHAT (CNO, SENDER, RECEIVER, CONTENT)"
					+ " VALUES (SEQ_CHAT.NEXTVAL, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chat.getSender());
			pstmt.setString(2, chat.getReceiver());
			pstmt.setString(3, chat.getContent());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Chat get(Long cno) {
		Chat chat = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT CNO, SENDER, RECEIVER, CONTENT,\r\n"
					   + "    CASE\r\n"
					   + "        WHEN SYSDATE - S_DATE > 1 THEN TO_CHAR(S_DATE, 'YY/MM/DD')\r\n"
					   + "        ELSE TO_CHAR(S_DATE, 'HH24:MI:SS')\r\n"
					   + "    END S_DATE,\r\n"
					   + "    CASE\r\n"
					   + "        WHEN SYSDATE - R_DATE > 1 THEN TO_CHAR(R_DATE, 'YY/MM/DD')\r\n"
					   + "        ELSE TO_CHAR(R_DATE, 'HH24:MI:SS')\r\n"
					   + "    END R_DATE\r\n"
					   + "FROM TBL_CHAT WHERE CNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,  cno);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx = 1;
				chat = new Chat(rs.getLong(idx++), rs.getString(idx++), rs.getString(idx++),
						rs.getString(idx++), rs.getString(idx++), rs.getString(idx++));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chat;
	}
	
	public void checkChat(Long cno) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE TBL_CHAT SET\r\n" + 
					"R_DATE = SYSDATE \r\n" + 
					"WHERE CNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,  cno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}