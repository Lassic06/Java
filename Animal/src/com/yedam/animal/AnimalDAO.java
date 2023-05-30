package com.yedam.animal;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.member.Member;
import com.yedam.member.MemberService;

public class AnimalDAO extends DAO {

	private static AnimalDAO aDao = null;
	private AnimalDAO() {
		
	}
	
	public static AnimalDAO getInstance() {
		if (aDao == null) {
			aDao = new AnimalDAO();
		}
		return aDao;
	}
	//축산물 전체 조회
	public List<Animal> getAnimalListAll(String id){
		List<Animal> list = new ArrayList<>();
		Animal animal = null;
		try {
			conn();
			String sql = "SELECT * FROM animal WHERE member_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				animal = new Animal();
				animal.setAnimalNumber(rs.getString("animal_number"));
				animal.setAnimalType(rs.getString("animal_type"));
				animal.setAnimalBirth(rs.getDate("animal_birth"));
				animal.setAnimalMuge(rs.getInt("animal_muge"));
				animal.setAnimalGender(rs.getString("animal_gender"));
				animal.setAnimalPrice(rs.getInt("animal_price"));
				animal.setAnimalDead(rs.getString("animal_dead"));
				list.add(animal);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	//축산물 검색 조회(한건 조회)
	public Animal getAnimalList(String listId) {
		Animal animal = null;
	try {
		conn();
		// 쿼리문자열 그냥 합쳐지기가 안됨, RDBMS 프로그램에 따라 차이가 있음 ORACLE은 Like '%' || ? || '%', iBatis는 like '%' || #?# || '%'
		String sql = "SELECT * FROM animal WHERE (animal_number LIKE ? || '%' AND animal_number LIKE '%' || ? || '%')";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, MemberService.memberInfo.getMemberId());
		pstmt.setString(2, listId);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			animal = new Animal();
			animal.setAnimalNumber(rs.getString("animal_number"));
			animal.setAnimalType(rs.getString("animal_type"));
			animal.setAnimalBirth(rs.getDate("animal_birth"));
			animal.setAnimalMuge(rs.getInt("animal_muge"));
			animal.setMugeDate(rs.getDate("muge_date"));
			animal.setAnimalGender(rs.getString("animal_gender"));
			animal.setAnimalPrice(rs.getInt("animal_price"));
			animal.setAnimalDead(rs.getString("animal_dead"));
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		disconn();
	}
	return animal;
	}
	//축산물 평균 증체율 조회
	public Animal getAnimalMugeList(String listId) {
		Animal animal = null;
		try {
			conn();
			String sql = "SELECT (muge_date-animal_birth) AS datenum\r\n"
					+ "FROM animal\r\n"
					+ "WHERE animal_number LIKE '%' || ? || '%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, listId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			animal = new Animal();
			animal.setDateNum(rs.getInt("datenum"));
			animal.setAnimalNumber(rs.getString("animal_number"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return animal;
		
	}
	//5. 축산물 등록
	public int insertAnimalAll(Animal animal) {
		int result = 0 ;
		try {
			conn();
			
			String sql = "INSERT INTO Animal VALUES(?, ?, ?, ?, ?, ?, ?,' ', ?, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, animal.getAnimalNumber());
			pstmt.setString(2, animal.getAnimalType());
			pstmt.setDate(3, animal.getAnimalBirth());
			pstmt.setInt(4, animal.getAnimalMuge());
			pstmt.setDate(5, animal.getMugeDate());
			pstmt.setString(6, animal.getAnimalGender());
			pstmt.setInt(7, animal.getAnimalPrice());
//			pstmt.setString(7, animal.getAnimalDead());
			pstmt.setString(8, animal.getMemberId());
//			pstmt.setInt(9, animal.getDateNum());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	//5.1.5 축산물 무게 등록
		public int insertAnimalMuge(Animal animal) {
			int result = 0;
			try {
				conn();
				String sql = "UPDATE animal \r\n"
						+ "Set animal_Muge = ?, muge_date = ?\r\n"
						+ "WHERE animal_number = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, animal.getAnimalMuge());
				pstmt.setDate(2, animal.getMugeDate());
				pstmt.setString(3, animal.getAnimalNumber());
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				disconn();
			}
			return result;
		}
	
	//5.1.6 축산물 판매가격 등록
		public int insertAnimalPrice(Animal animal) {
			int result = 0;
			try {
				conn();
				String sql = "UPDATE animal\r\n"
						+ "SET animal_price = ?\r\n"
						+ "WHERE animal_number = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, animal.getAnimalPrice());
				pstmt.setString(2, animal.getAnimalNumber());
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				disconn();
			}
			return result;
		}
	
	
	
	//5.1.4 축산물 정보 삭제
		public int deleteAnimal(String animalNumber) {
			int result = 0;
			try {
				conn();
				String sql = "DELETE FROM animal WHERE animal_number = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, animalNumber);
				
				result = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				disconn();
			}
			return result;
			}	
	
}
