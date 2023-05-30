package com.yedam.exe;

import java.util.Scanner;

import com.yedam.animal.AnimalService;
import com.yedam.member.MemberService;

public class AnimalApp {

	Scanner sc = new Scanner(System.in);
	boolean run = true;
	AnimalService as = new AnimalService();
	MemberService ms = new MemberService();
	
	
	
	public AnimalApp() {
		start();
		
	}
	
	private void start() {
		
		while(run) {
			if (MemberService.memberInfo != null) {
				loginMenu();
			} else if (MemberService.memberInfo == null) {
				logoutMenu();
			}
		}
	}
	
	private void logoutMenu() {
		System.out.println("==============메뉴============");
		System.out.println("1. 로그인 | 2. 회원가입 | 3. 종료");
		System.out.println("=============================");
		System.out.println("입력>");
		String selectNo = sc.nextLine();
		
		if (selectNo.equals("1")) {
			ms.login();
		}else if (selectNo.equals("2")) {
			ms.insertMember();
		}else if (selectNo.equals("3")) {
			run = false;
			System.out.println("프로그램이 종료 되었습니다.");
		}
	}

	
	
	
	private void loginMenu() {
		if (MemberService.memberInfo.getMemberGrade().equals("a")) {
			System.out.println("1. 모든 회원 정보 조회 | 2. 회원 삭제 | 3. 로그아웃 | 4. 종료");
			System.out.println("입력>");
			String menu = sc.nextLine();
			switch (menu) {
			case "1":	
				ms.getMemberList();
				break;
			case "2":
				ms.deleteMember();
				break;
			case "3":
				ms.logout();
				break;
			case "4":
				run = false;
				System.out.println("프로그램이 종료됩니다.");
				break;	
			}
		
		}else if (MemberService.memberInfo.getMemberGrade().equals("N")) {
			System.out.println("1. 축산물 정보 조회 | 2. 축산물 등록 및 정보입력 | 3. 회원 수정 | 4. 로그아웃 | 5. 종료");
			String menu = sc.nextLine();
			
			switch (menu) {
			case "1":
				AnimalSearch();
				break;
			case "2":
				insertAnimal();
				break;
			case "3":
				updateNum();
				break;
			case "4":
				ms.logout();
				break;
			case "5":
				run = false;
				System.out.println("프로그램이 종료됩니다.");
				break;
			}
		}
	}
	//축산물 정보 조회
	public void AnimalSearch(){
		System.out.println("1. 축산물 전체 정보 조회| 2. 축산물 검색 정보 조회 | 3. 축산물 판매 평균가격 조회 | 4. 축산물 성비 조회 | 5. 뒤로가기 ");
		String menu = sc.nextLine();
		switch (menu) {
		case "1":
			as.getAnimalListAll();
			break;
		case "2":
			as.getAnimalList();
			break;
		case "3":
			as.getAvgPrice();
			break;
		case "4":
			as.getMale();
			break;
		case "5":
			loginMenu();
			break;
//		case "6":
//			as.getAnimalMugeList();
//			break;
		}
	}
	//축산물 등록
	public void insertAnimal() {
		System.out.println("1. 축산물 등록 | 2. 축산물 정보 입력, 수정 | 3.뒤로가기");
		String menu = sc.nextLine();
		switch (menu) {
		case "1":
			as.insertAnimalAll();
			break;
		case "2":
			insertAnimalTable();
			break;
		case "3":
			loginMenu();
			break;
		}
		
	}
	//축산물 등록, 수정란
	public void insertAnimalTable(){
		System.out.println("===== 축산물 정보 등록 및 수정 ======");
		System.out.println("1. 축산물 무게 등록 | 2. 축산물 판매 가격 등록 | 3. 축산물 데이터 삭제 | 4. 뒤로가기");
		String menu = sc.nextLine();
		switch (menu) {
		case "1":
			as.insertAnimalMuge();
			break;
		case "2":
			as.insertAnimalpri();
			break;
		case "3":
			as.deleteMember();
			break;
		case "4":
			insertAnimal();
			break;
		}
	}
	
	public void updateNum() {
		System.out.println("==============회원정보변경============");
		System.out.println("1. 비밀번호 변경 | 2. 전화번호 변경 | 3. 이메일 변경 | 4. 주소 변경 | 5. 회원 탈퇴 | 6. 취소");
		System.out.println("=============================");
		System.out.println("입력>");
		String selectNo = sc.nextLine();
		
		if (selectNo.equals("1")) {
			ms.updatePW();
		}else if (selectNo.equals("2")) {
			ms.updatePhone();
		}else if (selectNo.equals("3")) {
			ms.updateEmali();
		}else if (selectNo.equals("4")) {
			ms.updateAddr();
		}else if (selectNo.equals("5")) {
			ms.deleteUser();
		}else if (selectNo.equals("6")) {
			loginMenu();
		}
	}
}









