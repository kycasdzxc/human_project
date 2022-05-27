package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Criteria;
import domain.Member;
import utils.DBConn;

public class MemberDao {
	private static MemberDao memberDao = new MemberDao();
	public static MemberDao getInstance() {
		return memberDao;
	}
	private MemberDao() {}
	
	public List<Member> list(Criteria cri) {
		List<Member> list = new ArrayList<Member>();
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT B.* \r\n" + 
					"FROM (\r\n" + 
					"    SELECT /*+ INDEX(TBL_MEMBER PK_MEMBER) */ \r\n" + 
					"        ID,\r\n" + 
					"        NAME,\r\n" + 
					"        ROWNUM RN\r\n" + 
					"    FROM TBL_MEMBER\r\n" + 
					"    WHERE\r\n" + 
					"        ROWNUM <= ?\r\n" +
					") B\r\n" + 
					"WHERE RN > ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPageNum() * cri.getAmount());
			pstmt.setInt(2, (cri.getPageNum() - 1) * cri.getAmount());			
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member(rs.getString(1), null, rs.getString(2));
				list.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Member get(String id) {
		Member member = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM TBL_MEMBER\r\n" + 
					"WHERE ID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setString(idx++, id.trim().toLowerCase());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx2 = 1;
				member = new Member(
						rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
						rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
						rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
						rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
						rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
						null
				);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public void register(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "{call PROC_INSERT_MEMBER(?,?,?,?,?,?,?,?,?,?,?,?)}";

			CallableStatement cstmt = conn.prepareCall(sql);
			
			int idx = 1;
			cstmt.setString(idx++, member.getId().toLowerCase().trim());
			cstmt.setString(idx++, member.getPw().toLowerCase().trim());
			cstmt.setString(idx++, member.getName().toLowerCase().trim());
			cstmt.setString(idx++, member.getSi());
			cstmt.setString(idx++, member.getSgg());
			cstmt.setString(idx++, member.getEmd());
			cstmt.setString(idx++, member.getRoadAddr());
			cstmt.setString(idx++, member.getAddrDetail());
			cstmt.setString(idx++, member.getZipNo());
			cstmt.setString(idx++, member.getRoadFullAddr());
			cstmt.setString(idx++, member.getJibunAddr());
			cstmt.setString(idx++, member.getEmail().toLowerCase().trim());
			
			cstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modify(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE TBL_MEMBER SET \r\n");
			if(member.getPw() != null && !member.getPw().equals("")) {
				sb.append("PW = ?, \r\n");
			}
			sb.append("NAME = ?, \r\n");
			sb.append("SI = ?, \r\n");
			sb.append("SGG = ?, \r\n");
			sb.append("EMD = ?, \r\n");
			sb.append("ROADADDR = ?, \r\n");
			sb.append("ADDRDETAIL = ?, \r\n");
			sb.append("ZIPNO = ?, \r\n");
			sb.append("ROADFULLADDR = ?, \r\n");
			sb.append("JIBUNADDR = ?, \r\n");
			sb.append("SPEAK = ? \r\n");
			sb.append("WHERE ID = ?");
			
			String sql = sb.toString();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			if(member.getPw() != null && !member.getPw().equals("")) {
				pstmt.setString(idx++, member.getPw().toLowerCase().trim());
			}
			pstmt.setString(idx++, member.getName().toLowerCase().trim());
			pstmt.setString(idx++, member.getSi());
			pstmt.setString(idx++, member.getSgg());
			pstmt.setString(idx++, member.getEmd());
			pstmt.setString(idx++, member.getRoadAddr());
			pstmt.setString(idx++, member.getAddrDetail());
			pstmt.setString(idx++, member.getZipNo());
			pstmt.setString(idx++, member.getRoadFullAddr());
			pstmt.setString(idx++, member.getJibunAddr());
			pstmt.setString(idx++, member.getSpeak());
			pstmt.setString(idx++, member.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remove(String id) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "{CALL QUIT_PROC(?)}";
			PreparedStatement pstmt = conn.prepareCall(sql);
			
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public Member login(String id, String pw) {
		Member member = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM TBL_MEMBER\r\n" + 
					"WHERE ID = ? AND PW = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setString(idx++, id.toLowerCase().trim());
			pstmt.setString(idx++, pw.toLowerCase().trim());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx2 = 1;
				member = new Member(rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public void updateAuthToken(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE TBL_MEMBER SET\r\n" + 
					"AUTH_TOKEN = ?\r\n" + 
					"WHERE ID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getAuthToken());
			pstmt.setString(2, member.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateAuth(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE TBL_MEMBER SET\r\n" + 
					"AUTH = ?\r\n" + 
					"WHERE ID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getAuth());
			pstmt.setString(2, member.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member findBy(String email) {
		Member member = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM TBL_MEMBER\r\n" + 
					"WHERE EMAIL = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setString(idx++, email.toLowerCase().trim());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx2 = 1;
				member = new Member(rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public int count(Criteria cri) {
		int count = 0;
		try {
			ResultSet rs = DBConn.getConnection().prepareStatement("SELECT COUNT(*) FROM TBL_MEMBER").executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}