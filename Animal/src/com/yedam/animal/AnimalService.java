package com.yedam.animal;


import java.util.List;
import java.util.Scanner;

import com.yedam.member.Member;
import com.yedam.member.MemberDAO;
import com.yedam.member.MemberService;
import com.yedam.animal.AnimalDAO;

public class AnimalService {

	
	
	Scanner sc = new Scanner(System.in);
	
	MemberService ms = new MemberService();
	
	
	//4. 축산물 정보 전체 조회
	String AnimalCode1 = "";

	public void getAnimalListAll() {
		String id = MemberService.memberInfo.getMemberId();
		List<Animal> list = AnimalDAO.getInstance().getAnimalListAll(id);
		int mugedate = 0;
		int mugeNum = 0;
		System.out.println("========================축산물 정보 전체 조회=========================");
		
		for(int i = 0; i<list.size(); i++) {
			System.out.println("관리번호 : " + list.get(i).getAnimalNumber());
			System.out.print("종류 : " + list.get(i).getAnimalType());
			System.out.print(" 축산물 탄생일: " + list.get(i).getAnimalBirth());
			System.out.print(" 무게 : " + list.get(i).getAnimalMuge() + "kg");
			System.out.print(" 성별 : " + list.get(i).getAnimalGender());
			System.out.println(" 판매된 가격 : " + list.get(i).getAnimalPrice() + "원");
			System.out.println(list.get(i).getAnimalDead().equals("1") ? "[DEAD]" : "");
			System.out.println("========================================================================");
		
		}
	}
	
	//4.1 축산물 검색 조회
	public void getAnimalList() {
		System.out.println("검색할 축산물 관리번호를 입력해 주세요>");
		System.out.println("(일부만 입력 가능)");
		String listId = sc.nextLine();
		Animal list = AnimalDAO.getInstance().getAnimalList(listId);
		if(list != null) {
		System.out.println("=================검색한 축산물 정보================");
		System.out.println("관리번호 : " + list.getAnimalNumber());
		System.out.print("종류 : " + list.getAnimalType());
		System.out.print(" 축산물 탄생일: " + list.getAnimalBirth());
		System.out.print(" 무게 : " + list.getAnimalMuge() + "kg");
		System.out.println(" 무게측정일 : " + "(" + list.getMugeDate() + ")");
		System.out.print(" 성별 : " + (list.getAnimalGender().equals("male") ? "♂" : "♀"));
		System.out.print(" 판매된 가격 : " + list.getAnimalPrice() + "원");
		System.out.println(list.getAnimalDead().equals("DEAD") ? "[DEAD]" : "");
		System.out.println("========================================================================");
		}else {
			System.out.println("잘못된 관리번호 입력입니다.");
		}
	}
//	//4.1.2 축산물 평균 증체율 조회
//	public void getAnimalMugeList() {
//		int mugedate = 0;
//		int mugeNum = 0;
//		System.out.println("검색할 축산물 관리번호를 입력해 주세요>");
//		System.out.println("(일부만 입력 가능)");
//		String id = sc.nextLine();
//		List<Animal> list = AnimalDAO.getInstance().getAnimalListAll(id);
//		for(int i = 0; i<list.size(); i++) {
//			if(list != null) {
//				System.out.println("관리번호 : " + list.get(i).getAnimalNumber());
//				mugedate = list.get(i).getDateNum();
//				mugeNum = mugedate / list.get(i).getAnimalMuge();
//				System.out.println("평균 증체율은 일일당 : " + mugeNum);
//			}else {
//				System.out.println("잘못된 관리번호 입력입니다.");
//			}
//		}
//	}
	
	//5. 축산물 등록
	public void insertAnimalAll() {
		System.out.println("=======축산물 등록======");
		
		//축산물 종류 등록
		System.out.println("축산물의 종류를 입력해 주세요> ");
		String type = sc.nextLine();
		
		//축산물 생년월일 등록
		String birth1 = "";
		boolean result1 = true;
		
		while(result1) {
			System.out.println("축산물의 생년월일을 입력해 주세요>");
			String birth = sc.nextLine();
			if(birth.length() == 8) {
				String finds = birth;
				String ans = finds.substring(0,4);
				String ans1 = finds.substring(4,6);
				String ans2 = finds.substring(6,8);
				birth1 = (ans + "-" + ans1 + "-" + ans2);
				break;
			}else if(birth.length() == 10) {
				birth1 = birth;
				break;
			}else {
				System.out.println("잘못된 날짜 입력입니다. ex) 20110101, 2011-01-01");
				continue;
			}
		}	
		
		java.sql.Date birth2 = java.sql.Date.valueOf(birth1);
		
		//축산물 무게 등록
		System.out.println("축산물의 무게 입력>");
		int muge = Integer.parseInt(sc.nextLine());
		
		
		//축산물 무게측정 날짜 등록
		String mugeDate2 = "";
		
		while(result1) {
			System.out.println("무게측정 날짜 입력>");
			String mugeDate = sc.nextLine();
			if(mugeDate.length() == 8) {
				String mud = mugeDate;
				String mud1 = mud.substring(0,4);
				String mud2 = mud.substring(4,6);
				String mud3 = mud.substring(6,8);
				mugeDate2 = (mud1 + "-" + mud2 + "-" + mud3);
				break;
			}else if (mugeDate.length() == 10){
					mugeDate2 = mugeDate;
					break;
			}else {
				System.out.println("잘못된 날짜 입력입니다. ex) 20110101, 2011-01-01");
				continue;
			}
		}
		
		java.sql.Date mugeDate3 = java.sql.Date.valueOf(mugeDate2);
		
		//축산물 성별 등록
		System.out.println("축산물의 성별을 입력해 주세요");
		System.out.println("1. Male(♂) | 2. female(♀) ");
		String gender = sc.nextLine();
		int gender1 = Integer.parseInt(gender);
		if(gender1 == 1) {
			gender = "Male";
		}else if(gender1 == 2) {
			gender = "female";
		}else {
			System.out.println("잘못된 입력입니다.");
	
		}
		//축산물 관리번호 (자동)
		
		String id1 = MemberService.memberInfo.getMemberId();
		String an1 = birth1.substring(2,4);
		
		
		Member rs1 = MemberDAO.getInstance().getSquence();
		String animalNumber = (id1 + "_" + an1 + type + gender + rs1.getSqsTest());
		
		Animal ani = new Animal();
		String id = MemberService.memberInfo.getMemberId();
		ani.setAnimalNumber(animalNumber);
		ani.setMemberId(id);
		ani.setAnimalType(type);
		ani.setAnimalMuge(muge);
		ani.setMugeDate(mugeDate3);
		ani.setAnimalBirth(birth2);
		ani.setAnimalGender(gender);
		
		
		int result = AnimalDAO.getInstance().insertAnimalAll(ani);
		
		if(result > 0) {
			System.out.println("축산물 등록 완료");
		}else {
			System.out.println("축산물 등록 실패");
		}
	}
	//5.1 축산물 개별 등록
	//5.1.1 축산물 무게 등록
	public void insertAnimalMuge() {
		Animal animal = new Animal();
		String mugeDate2 = "";
		boolean result1 = true;
		
		System.out.println("===축산물 무게 등록===");
		System.out.println("축산물 관리 번호 입력>");
		String aid = sc.nextLine();
		
		System.out.println("=====축산물 무게 등록======");
		System.out.println("무게 입력>");
		int muge = Integer.parseInt(sc.nextLine());
		
		
		while(result1) {
			System.out.println("무게측정 날짜 입력>");
			String mugeDate = sc.nextLine();
			if(mugeDate.length() == 8) {
				String mud = mugeDate;
				String mud1 = mud.substring(0,4);
				String mud2 = mud.substring(4,6);
				String mud3 = mud.substring(6,8);
				mugeDate2 = (mud1 + "-" + mud2 + "-" + mud3);
				break;
			}else if (mugeDate.length() == 10){
					mugeDate2 = mugeDate;
					break;
			}else {
				System.out.println("잘못된 날짜 입력입니다. ex) 20110101, 2011-01-01");
				continue;
			}
		}
		java.sql.Date mugeDate3 = java.sql.Date.valueOf(mugeDate2);
		
		animal.setAnimalNumber(aid);
		animal.setAnimalMuge(muge);
		animal.setMugeDate(mugeDate3);
	
		AnimalDAO.getInstance().insertAnimalMuge(animal);
		
	}
	//5.1.2 축산물 판매 가격 등록
		public void insertAnimalpri() {
			Animal animal = new Animal();
			
			System.out.println("축산물 관리 번호 입력>");
			String aid = sc.nextLine();
			System.out.println("=====축산물 판매 가격 등록======");
			System.out.println("판매 가격 입력>");
			int price = Integer.parseInt(sc.nextLine());
			
			animal.setAnimalNumber(aid);
			animal.setAnimalPrice(price);
			
			
			AnimalDAO.getInstance().insertAnimalPrice(animal);
		}
	//5.1.3 축산물 폐사 등록
		public void insetAnimalDead() {
			Animal animal = new Animal();
			
			System.out.println("=======축산물 폐사 등록========");
			String dead = sc.nextLine();
			
			animal.setAnimalDead(dead);
		}
	//8.1 축산물 성비 계산
		public void getMale() {
			double total = 0;
			int male = 0;
			int female = 0;
			double percentM = 0;
			double percentF = 0;
			String id = MemberService.memberInfo.getMemberId();
			List<Animal> list = AnimalDAO.getInstance().getAnimalListAll(id);
			
			System.out.println("========================축산물 정보 전체 조회=========================");
			for(int i = 0; i<list.size(); i++) {
				if(list.get(i).getAnimalGender().equals("Male")) {
					male = male + 1;
				}else {
					female = female +1;
				}
				total ++;
				percentM = (male/total) * 100;
				percentF = (female/total) * 100;
			}
			System.out.println("수컷의 숫자 = " + male + "마리" );
			System.out.println("암컷의 숫자 = " + female + "마리");
			System.out.println("가축의 성비 수컷 = " + percentM + "%");
			System.out.println("가축의 성비 암컷 = " + percentF + "%");
//			System.out.println("가축의 성비 암컷 = " + (female/total*100) + "%");
		}
		
		
	//9.1 축산물 판매 평균가 조회
		public void getAvgPrice() {
	//		String AnimalCode1 = "";
			int sum = 0;
			int total = 0;
			int avg = 0;
				String id = MemberService.memberInfo.getMemberId();
				List<Animal> list = AnimalDAO.getInstance().getAnimalListAll(id);
				
				System.out.println("========================축산물 정보 전체 조회=========================");
				for(int i = 0; i<list.size(); i++) {
//					System.out.println(" 판매 가격 : " + list.get(i).getAnimalPrice() + "원");
//					System.out.println("========================================================================");
					if(list.get(i).getAnimalPrice() != 0) {
					sum = sum + list.get(i).getAnimalPrice();
					total ++;
					}
			}
			avg = (int)(sum/total);
			System.out.println("판매 평균 가격 : " + avg + "원");
		}
		
	//5.1.4 축산물 데이터 삭제
		public void deleteMember() {
			System.out.println("### 축산물 데이터 삭제 ###");
			System.out.println("축산물 관리번호>");
			String animalNumber = sc.nextLine();
			
			int result = AnimalDAO.getInstance().deleteAnimal(animalNumber);
			
			if(result > 0) {
				System.out.println("축산물 정보 삭제 완료");
			}else {
				System.out.println("축산물 정보 삭제 실패");
			}
			
		}
}
	
	


