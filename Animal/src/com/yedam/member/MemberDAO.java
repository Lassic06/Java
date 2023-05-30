package com.yedam.member;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class MemberDAO extends DAO {
	
	private static MemberDAO mDao = null;
	private MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		if (mDao == null) {
			mDao = new MemberDAO();
		}
		return mDao;
	}
	
	
	//1. 로그인 기능
	public Member login(String id) {
		Member member = null;
		try {
			conn();
			String sql = "SELECT * FROM member WHERE member_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberEmail(rs.getString("member_email"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberAddr(rs.getString("member_addr"));
				member.setMemberGrade(rs.getString("member_grade"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return member;
	}
	//2. 회원가입
	public int insertMember(Member member) {
		int result = 0;
		try {
			conn();
			System.out.println(member.getMemberId());
			String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, 'N')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberEmail());
			pstmt.setString(5, member.getMemberPhone());
			pstmt.setString(6, member.getMemberAddr());
				
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	//시퀀스 생성(고유넘버링)
	public int squenceNum(String id) {
		int result = 0;
		try {
			conn();
				
			String sql = "CREATE SEQUENCE "+id+"_seq \r\n"
					+ "        INCREMENT BY 1\r\n"
					+ "        START WITH 1\r\n"
					+ "        MINVALUE 1\r\n"
					+ "        MAXVALUE 9999\r\n"
					+ "        CYCLE\r\n"
					+ "        NOCACHE\r\n"
					+ "        NOORDER";
			pstmt = conn.prepareStatement(sql);

			boolean a = pstmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}	
	//시퀀스 작동 (고유번호)
	public Member getSquence() {
		Member rs1 = new Member();

		try {
			conn();
			String sql = "SELECT "+ MemberService.memberInfo.getMemberId() +"_SEQ" + ".nextval\r\n"
					+ "FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rs1.setSqsTest(rs.getString("nextval"));
			}


//			String rs1 = rs.getString(sql);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return rs1;
	}
	
	// 시퀀스
	//(관리자)전체 조회
	public List<Member> getMemberList(){
		List<Member> list = new ArrayList<>();
		Member member = null;
		try {
			conn();
			String sql = "SELECT *\r\n"
					+ "FROM member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberAddr(rs.getString("member_addr"));
				member.setMemberGrade(rs.getString("member_grade"));
				list.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	//3 회원 수정
	//3.1 비밀번호 변경
	public int updatePW(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE member Set member_PW = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberId());
				
			result = pstmt.executeUpdate();
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	//전화번호 변경
	public int updatePhone(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE member Set member_Phone = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPhone());
			pstmt.setString(2, member.getMemberId());
				
			result = pstmt.executeUpdate();
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	//3.2 이메일 변경
	public int updateEamil(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE member Set member_email = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
		
	//3.3 주소 변경
	public int updateAddr(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE member Set member_Addr = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberAddr());
			pstmt.setString(2, member.getMemberId());
				
			result = pstmt.executeUpdate();
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
		
		
	//(관리자)회원 삭제
	public int deleteMember(String id) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM member WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
				
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	public int deleteSeqence() {
		int result = 0;
		try {
			conn();
			String sql = "DROP SEQUENCE " + MemberService.memberInfo.getMemberId()  + "_SEQ";
			pstmt = conn.prepareStatement(sql);
			
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	//3.4 회원 탈퇴
	public int deleteUser(String id, String pw) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM member WHERE member_id = ? AND member_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
						
			result = pstmt.executeUpdate();
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
				disconn();
		}
		return result;
	}	
	
}
