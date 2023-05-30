package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DAO {
	//DAO -> Data Access Object
	//JDBC를 통해서 JAVA<->DB가 연결이 된다.
	
	//java -> DB 연결할때 쓰는 객체
	protected Connection conn = null;
	
	//Query문(SQL, 질의)을 가지고 실행하는 객체
	protected PreparedStatement pstmt = null;
	
	//Qurey문(SQL, 질의)을 가지고 실행하는 객체
	protected Statement stmt = null;
	
	
	//SELECT(조회) 결과 값을 반환받는 객체
	protected ResultSet rs = null;
	
	//DB 접속 정보 -> 구글 검색 DB연결
	//driver가 jdbc
	//oracle 폴더 들어가서 sqldeveloper > jdbc > lib > ojdbc8.jar > 바탕화면 옮긴뒤
	// CompanyExample(프로젝트파일) 오른쪽클릭 bulid pach이용
	String driver = "oracle.jdbc.driver.OracleDriver";
	//ip호수
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "HR";
	String pw = "1234";
	
	//DB 연결 메소드
	public void conn() {
		try {
			//1. 드라이버 로딩
			Class.forName(driver);
			//2. DB연결
			conn = DriverManager.getConnection(url,id,pw);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//DB 연결 해제
	//연결후 해제는 역순으로
	//DB에 접속자는 한정되어있는데 끊지않으면 문제가 발생함 -> SQL 조회,삭제,추가,수정을 사용하고나면 접속을 끊어주는 역활
	public void disconn() {
		try {
			//종료시 if / else if를 쓰면안됨 : 모두 종료해야하는데 if를 만족하면 뒤에 실행이 안되기 때문에
			// != null의 이유 -> 실행했으면 null이 아니게 되기때문에 null값이라면 사용을 하지 못한 상황이기에 종료의 의미가없음
 			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
		
}
