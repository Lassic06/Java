package com.yedam.member;

import java.util.List;
import java.util.Scanner;
import com.yedam.member.MemberDAO;

public class MemberService {
	public static Member memberInfo = null;
	Scanner sc = new Scanner(System.in);
	
	
	//1.로그인
	public void login() {
		Member member = null;
		System.out.println("ID>");
		String id = sc.nextLine();
		
		System.out.println("PW>");
		String pw = sc.nextLine();
		
		member = MemberDAO.getInstance().login(id);
		
		if(member != null) {
			if(member.getMemberPw().equals(pw)) {
				System.out.println("정상 로그인되었습니다.");
				System.out.println(member.getMemberId() + "님 환영합니다.");
				memberInfo = member;
			}else {
				System.out.println("비밀번호가 틀립니다.");
			}
		}else {
			System.out.println("아이디가 존재하지 않습니다.");
		}

	}
	
	//2.회원가입
	public void insertMember() {
		int result = 0;
		System.out.println("========회 원 가 입=======");
		String id = "";
		while(true) {
			System.out.println("사용할 아이디를 입력해 주세요>");
			id = sc.nextLine();
			Member member = MemberDAO.getInstance().login(id);
			if(member != null) {
				System.out.println("존재하는 ID 입니다.");
			}else if(member == null) {
				System.out.println("사용가능한 ID 입니다.");
				break;
			}
			
		}
		
		
		
		System.out.println("사용할 비밀번호를 입력해 주세요>");
		String pw = sc.nextLine();
		
		System.out.println("이름을 입력해 주세요>");
		String name = sc.nextLine();
		
		System.out.println("이메일을 입력해 주세요>");
		String email = sc.nextLine();
		
		System.out.println("전화번호를 입력해 주세요>");
		String phone = sc.nextLine();
		
		System.out.println("주소를 입력해 주세요>");
		String Addr = sc.nextLine();
		
		
		Member mem = new Member();
		mem.setMemberId(id);
		mem.setMemberPw(pw);
		mem.setMemberName(name);
		mem.setMemberEmail(email);
		mem.setMemberPhone(phone);
		mem.setMemberAddr(Addr);
		
		MemberDAO.getInstance().squenceNum(id);
		
		result = MemberDAO.getInstance().insertMember(mem);
		
		if(result > 0) {
			System.out.println("##### 회원 가입을 축하합니다 #####");
		}else {
			System.out.println("회원 가입에 실패하셨습니다.");
		}	
	}
	
	//로그아웃
	public void logout() {
		memberInfo = null;
		System.out.println("로그아웃되었습니다.");
	}
	
	//(관리자)전체 조회
	public void getMemberList() {
		List<Member> list = MemberDAO.getInstance().getMemberList();
		System.out.println("================전체 회원 조회================");
		for(int i = 0; i<list.size(); i++) {
			System.out.println("ID : " + list.get(i).getMemberId());
			System.out.println("PW : " + list.get(i).getMemberPw());
			System.out.println("이름 : " + list.get(i).getMemberName());
			System.out.println("전화번호 : " + list.get(i).getMemberPhone());
			System.out.println("주소 : " + list.get(i).getMemberAddr());
			System.out.println("권한 : " + (list.get(i).getMemberGrade().equals("N") ? "일반사용자" : "관리자"));
			System.out.println("========================================");
		}
	}
	
	//(관리자)회원정보 삭제
	public void deleteMember() {
		System.out.println("# 회원 정보 삭제");
		System.out.println("ID>");
		String id = sc.nextLine();
		
		int result = MemberDAO.getInstance().deleteMember(id);
	
		if(result > 0) {
			System.out.println("회원 정보 삭제 완료");
		}else {
			System.out.println("회원 정보 삭제 실패");
		}
	}
	//3. 회원수정
	
	//3.1 비밀번호 변경
	public void updatePW() {
		System.out.println("###비밀번호 변경###");
		System.out.println("기존 비밀번호>");
		String pw = sc.nextLine();
		Member member = new Member();
		member.setMemberId(memberInfo.getMemberId());
		
		
		if(memberInfo.getMemberPw().equals(pw)) {
			System.out.println("새로운 비밀번호>");
			String pw1 = sc.nextLine();
			member.setMemberPw(pw1);
			if(pw1.length() < 12) {
				System.out.println("새로운 비밀번호 확인 완료");
				int result = MemberDAO.getInstance().updatePW(member);
				if(result > 0) {	
					System.out.println("비밀번호 변경 완료");
					result = MemberDAO.getInstance().updatePW(member);
					memberInfo.setMemberPw(pw1);
				}else {
					System.out.println("비밀번호 변경 실패");
				}
			}else {
				System.out.println("자리수 초과 => 12자리수 미만으로 입력하세요");
			}
		}else {
			System.out.println("비밀번호가 틀렸습니다.");
		}
		
	}
	//3.2 이메일 변경
	public void updateEmali() {
		System.out.println("####이메일 변경####");
		
		Member member = new Member();
		
		System.out.println("변경할 이메일 입력>");
		String emali = sc.nextLine();
		
		member.setMemberId(memberInfo.getMemberId());
		member.setMemberAddr(emali);
		int result = MemberDAO.getInstance().updateEamil(member);
		
		if(result > 0) {
			System.out.println("이메일 변경 완료");
		}else {
			System.out.println("이메일 변경 실패");
		}
	}
	//전화번호 변경
	public void updatePhone() {
		System.out.println("###전화번호 변경###");
		
		Member member = new Member();
		
		System.out.println("변경할 전화번호 입력>");
		String phone = sc.nextLine();
		if(phone.length() > 13) {
			System.out.println("자리수 초과 => 13자리수 미만으로 입력하세요");
		}else {
			System.out.println("변경할 전화번호 확인 완료");
		}
		
		member.setMemberId(memberInfo.getMemberId());
		member.setMemberPhone(phone);
		int result = MemberDAO.getInstance().updatePhone(member);
		
		if(result > 0) {
			System.out.println("전화번호 변경 완료");
		}else {
			System.out.println("전화번호 변경 실패");
		}
	}
	//3.3 주소 변경
	public void updateAddr() {
		System.out.println("####주소 변경####");
		
		Member member = new Member();
		
		System.out.println("변경할 주소 입력>");
		String Addr = sc.nextLine();
		
		member.setMemberId(memberInfo.getMemberId());
		member.setMemberAddr(Addr);
		int result = MemberDAO.getInstance().updateAddr(member);
		
		if(result > 0) {
			System.out.println("주소 변경 완료");
		}else {
			System.out.println("주소 변경 실패");
		}
	}
	//3.4 회원탈퇴 기능
	public void deleteUser() {
		
		System.out.println("### 회원 탈퇴 ###");
		System.out.println("PW>");
		String pw = sc.nextLine();
	
			if(memberInfo.getMemberPw().equals(pw)) {
				System.out.println("회원 탈퇴를 정말 진행하시겠습니까?");
				System.out.println("1. 네 | 2. 아니오");
				int ye = Integer.parseInt(sc.nextLine());
				
				if(ye == 1) {
					MemberDAO.getInstance().deleteSeqence();
					 MemberDAO.getInstance().deleteUser(MemberService.memberInfo.getMemberId(),pw);
					 System.out.println("회원 탈퇴가 완료되었습니다.");
					 memberInfo = null;
				}else {
					 System.out.println("회원 탈퇴가 취소되었습니다.");
				}
			}else {
				System.out.println("비밀번호가 틀립니다.");
			}
	}
	
	
	
	
}
