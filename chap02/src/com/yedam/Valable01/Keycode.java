package com.yedam.Valable01;

import java.io.IOException;
import java.util.Scanner;

public class Keycode {
	public static void main(String[] args) throws IOException {
		//keyCode -> 하나의 문자만 받아 올 때
		System.out.println("입력>");
		
		int keyCode = 0;
		
		keyCode = System.in.read();
		System.out.println("KeyCode : " + keyCode);
		
		keyCode = System.in.read();
		System.out.println("KeyCode : " + keyCode);
		
		keyCode = System.in.read();
		System.out.println("KeyCode : " + keyCode);
		
		//Scanner
		Scanner scanner = new Scanner(System.in);
		System.out.println("입력>");
		
		//문자열 읽기
		//nextLine()
		//=> enter키 이전까지 데이터를 받아 옴.
		//=> enter키 기준으로 데이터를 읽어 옴.
		String inputData = scanner.nextLine();
		//정수 읽기
		//nextInt()
		//=> 데이터는 들어가고 enter키가 남음
		int data = scanner.nextInt();
		//해결방법* scanner.nextLine();을 적어주면됨 (엔터키 소멸)
		//nextInt()에서 남은 엔터만 남아서 입력됨(공백데이터 투입)
		inputData = scanner.nextLine();
		System.out.println("Scanner 활용 => "+inputData);
		
		//데이터 비교 -> 입력한 값 == 저장된 값 비교
		
		//기본 타입(정수, 실수 비교 ==)
		//문자열 -> equals
		if(inputData.equals("yedam")) {
			System.out.println("yedam과 일치합니다.");
			
		}
	}
}
