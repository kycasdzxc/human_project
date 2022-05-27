package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Board;
import domain.Criteria;
import utils.DBConn;

public class BoardDao {
	private static BoardDao boardDao = new BoardDao();
	
	public static BoardDao getInstacne() {
		return boardDao;
	}
	
	private BoardDao() {}

	public List<Board> list(Criteria cri, String keyword) {
		List<Board> list = new ArrayList<Board>();
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT B.*, "
					+ "(SELECT COUNT(*) FROM TBL_REPLY R WHERE R.BNO = B.BNO) REPLYCNT \r\n" + 
					"FROM (\r\n" + 
					"    SELECT\r\n" + 
					"        /*+ INDEX_DESC(TBL_BOARD PK_BOARD) */\r\n" + 
					"        BNO,\r\n" + 
					"        TITLE,\r\n" + 
					"        HITCOUNT,\r\n" + 
					"        CASE\r\n" + 
					"            WHEN SYSDATE - REGDATE > 1 THEN TO_CHAR(REGDATE, 'YY/MM/DD')\r\n" + 
					"            ELSE TO_CHAR(REGDATE, 'HH24:MI:SS')\r\n" + 
					"        END REGDATE,\r\n" + 
					"        CATEGORY,\r\n" + 
					"        WRITER,\r\n" + 
					"        ROWNUM RN,\r\n" + 
					"        (SELECT COUNT(BNO) FROM TBL_BOARD) CNT\r\n" + 
					"    FROM TBL_BOARD\r\n" + 
					"    WHERE\r\n" + 
					"        CATEGORY = ?\r\n" + 
					"        AND ROWNUM <= ?\r\n";
			
			if(keyword != null && !keyword.isEmpty()) {
				sql += " AND (TITLE LIKE '%' || ? || '%'"
						+ " OR WRITER LIKE '%' || ? || '%')";
			}

			sql +=  ") B\r\n" + 
					"WHERE RN > ?";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setInt(idx++, cri.getCategory());
			pstmt.setInt(idx++, cri.getPageNum() * cri.getAmount());
			if(keyword != null && !keyword.isEmpty()) {
				pstmt.setString(idx++, keyword);		
				pstmt.setString(idx++, keyword);
			}
			pstmt.setInt(idx++, (cri.getPageNum() - 1) * cri.getAmount());		
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board(rs.getLong(1), rs.getString(2), null,
						rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6));
				board.setReplyCnt(rs.getInt("REPLYCNT"));
				list.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Board get(Long bno) {
		Board board = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE TBL_BOARD SET\r\n" + 
					"HITCOUNT = HITCOUNT + 1\r\n" + 
					"WHERE BNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			pstmt.executeUpdate();
			
			sql = "SELECT BNO, TITLE, CONTENT, HITCOUNT,\r\n" + 
					"    CASE\r\n" + 
					"        WHEN SYSDATE - REGDATE > 1 THEN TO_CHAR(REGDATE, 'YY/MM/DD')\r\n" + 
					"        ELSE TO_CHAR(REGDATE, 'HH24:MI:SS')\r\n" + 
					"    END REGDATE,\r\n" + 
					"    CATEGORY, WRITER\r\n" + 
					"FROM TBL_BOARD\r\n"
					+ "WHERE BNO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx = 1;
				board = new Board(rs.getLong(idx++), rs.getString(idx++), rs.getString(idx++),
						rs.getInt(idx++), rs.getString(idx++), rs.getInt(idx++), rs.getString(idx++));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	
	public void register(Board board) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT SEQ_BOARD.NEXTVAL FROM DUAL";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			board.setBno(rs.getLong(1));
			
			sql = "INSERT INTO TBL_BOARD(BNO, TITLE, CONTENT, WRITER, CATEGORY) "
					+ "VALUES (?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setLong(idx++, board.getBno());
			pstmt.setString(idx++, board.getTitle());
			pstmt.setString(idx++, board.getContent());
			pstmt.setString(idx++, board.getWriter());
			pstmt.setInt(idx++, board.getCategory());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modify(Board board) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE TBL_BOARD SET\r\n" + 
					"TITLE = ?,\r\n" + 
					"CONTENT = ?,\r\n" + 
					"REGDATE = SYSDATE\r\n" + 
					"WHERE BNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setLong(3, board.getBno());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remove(Long bno) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "DELETE TBL_BOARD\r\n" + 
					"WHERE BNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int count(Criteria cri, String keyword) {
		int count = 0;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT COUNT(*) FROM TBL_BOARD WHERE CATEGORY = ?";
			
			if(keyword != null && !keyword.isEmpty()) {
				sql += " AND (TITLE LIKE '%' || ? || '%'"
						+ " OR WRITER LIKE '%' || ? || '%')";
			}

			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setLong(idx++, cri.getCategory());
			if(keyword != null && !keyword.isEmpty()) {
				pstmt.setString(idx++, keyword);		
				pstmt.setString(idx++, keyword);
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void modifyNullByWriter(String writer) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE TBL_BOARD SET\r\n" + 
					"WRITER = NULL \r\n" + 
					"WHERE WRITER = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
