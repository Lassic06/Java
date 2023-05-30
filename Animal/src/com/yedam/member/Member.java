package com.yedam.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
//	CREATE TABLE member(
//	MEMBER_ID          VARCHAR(12) primary key NOT NULL,
//	MEMBER_PW          VARCHAR(12),
//	MEMBER_NAME        VARCHAR(16),
//  MEMBER_EMAIL	   VARCHAR(100),
//	MEMBER_PHONE       VARCHAR(13),
//	MEMBER_ADDR        VARCHAR(100),
//	MEMBER_GRADE       CHAR(1));	
	
//	INSERT INTO member 
//	VALUES ('admin', '1234', 'god', 'sky', 'sky', 'sky', '1'); 

	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	private String memberPhone;
	private String memberAddr;
	private String memberGrade;
	private String sqsTest;
	
}
