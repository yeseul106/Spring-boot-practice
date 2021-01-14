package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.UserProfile;

@Mapper  //Mapper로 인식
public interface UserProfileMapper {

	@Select("SELECT * FROM UserProfile WHERE id=#{id}")  //id값을 파라미터로 받아서 조건문에 넣음. 그 아이디를 가진 데이터만 가져오기
	UserProfile getUserProfile(@Param("id") String id);
	
	@Select("SELECT * FROM UserProfile")
	List<UserProfile> getUserProfileList();
	
	@Insert("INSERT INTO UserProfile VALUES(#{id}, #{name}, #{phone}, #{address})")
	int insertUserProfile(@Param("id") String id,@Param("name") String name,@Param("phone") String phone, @Param("address") String address);
	//int형 결과 반환 => 이 sql문에 의해서 적용되거나 영향을 받은 sql record의 갯수
	
	@Update("UPDATE UserProfile SET name=#{name}, phone=#{phone}, address=#{address} WHERE id=#{id}")
	int updateUserProfile(@Param("id") String id,@Param("name") String name,@Param("phone") String phone, @Param("address") String address);
	//int형 결과 반환 => 이 sql문에 의해서 적용되거나 영향을 받은 sql record의 갯수
	
	@Delete("DELETE FROM UserProfile WHERE id=#{id}")
	int deleteUserProfile(@Param("id") String id);
	//int형 결과 반환 => 이 sql문에 의해서 적용되거나 영향을 받은 sql record의 갯수
}
