package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.UserProfile;
import java.util.*;


@RestController //springFramework이 알아서 이 클래스를 controller로 인식하고 인스턴트를 생성
public class UserProfileController {
	
	private UserProfileMapper mapper;
	
	public UserProfileController(UserProfileMapper mapper) { //생성자를 통해서 받겠다!! 스프링 부트가 알아서 객체 생성해서 생성자로 전달
		this.mapper = mapper;
	}
	
	//id 인자로 받아서 해당 data를 json 형태로 전달하는 api
	//Read
	@GetMapping("/user/{id}") //http의 프로토콜 -> Get방식 (데이터 조회) id를 얻어옴
	public UserProfile getUserProfile(@PathVariable("id") String id) { //PathVariable하면 id 얻어오기
		return mapper.getUserProfile(id); //우리가 Mapping해놓은 sql문이 실행되어서 결과가 자바 객체로 반환 => json형태로 전달됨 
	}
	
	//Read
	@GetMapping("/user/all")
	public List<UserProfile> getUserProfileList(){
		return mapper.getUserProfileList(); //userProfile 모두를 ArrayList 형태로 반환
	}
	
	//Create
	@PostMapping("/user/{id}")
	public void postUserProfile(@PathVariable("id") String id,@RequestParam("name") String name,@RequestParam("phone") String phone, @RequestParam("address") String address) {
		mapper.insertUserProfile(id, name, phone, address);
	}
	
	//Update
	@PutMapping("/user/{id}")
	public void putUserProfile(@PathVariable("id") String id,@RequestParam("name") String name,@RequestParam("phone") String phone, @RequestParam("address") String address) {
		mapper.updateUserProfile(id, name, phone, address);
	}
	
	//Delete
	@DeleteMapping("/user/{id}")
	public void deleteUserProfile(@PathVariable("id") String id) {
		mapper.deleteUserProfile(id);
	}
}
