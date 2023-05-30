package com.yedam.animal;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Animal {
//	CREATE TABLE animal(
//			ANIMAL_NUMBER VARCHAR(100) primary key NOT NULL,
//			ANIMAL_TYPE VARCHAR(20),
//			ANIMAL_BIRTH DATE,
//			ANIMAL_MUGE INT,
//			 MUGE_DATE DATE,
//			ANIMAL_GENDER VARCHAR(10),
//			ANIMAL_PRICE INT,
//			ANIMAL_DEAD CHAR(1),
//			MEMBER_ID VARCHAR(12),
//		    DateNum INT
//		);

	
	private String animalNumber;
	private String animalType;
	private Date animalBirth;
	private int animalMuge;
	private Date mugeDate;
	private String animalGender;
	private int animalPrice;
	private String animalDead;
	private String memberId;
	private int DateNum;

}
