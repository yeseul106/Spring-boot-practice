package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.*;


import com.example.demo.model.UserProfile;
import java.util.*;


@RestController //springFramework이 알아서 이 클래스를 controller로 인식하고 인스턴트를 생성
public class UserProfileController {
	private Map<String,UserProfile> userMap;
	
	@PostConstruct //데이터초기화
	public void init() {
		userMap=new HashMap<String,UserProfile>();
		//data default값
		userMap.put("1",new UserProfile("1","홍길동","111-1111","서울시 강남구 대치1동"));
		userMap.put("2",new UserProfile("2","홍길자","111-1112","서울시 강남구 대치2동"));
		userMap.put("3",new UserProfile("3","홍길순","111-1113","서울시 강남구 대치3동"));
	}
	/*
    @CrossOrigin(origins = “허용주소:포트”)
    모든 origin 허용은 @CrossOrigin(origins="*") 설정
	  */
    
	// http://localhost:8080 에서 들어오는 요청만 CORS 허용
//	@CrossOrigin(origins = "http://localhost:8080")
//	@PostMapping("/user/")
//	public String postSuccess() {
//		return "REST API 호출 성공~!!";
//	}

	//id 인자로 받아서 해당 data를 json 형태로 전달하는 api
	//Read
	@GetMapping("/user/{id}") //http의 프로토콜 -> Get방식 (데이터 조회) id를 얻어옴
	public UserProfile getUserProfile(@PathVariable("id") String id) { //PathVariable하면 id 얻어오기
		return userMap.get(id);
	}
	
	//Read
	@GetMapping("/user/all")
	public List<UserProfile> getUserProfileList(){
		return new ArrayList<UserProfile>(userMap.values()); //userProfile 모두를 ArrayList 형태로 반환
	}
	
	//Create
	@PostMapping("/user/{id}")
	public void postUserProfile(@PathVariable("id") String id,@RequestParam("name") String name,@RequestParam("phone") String phone, @RequestParam("address") String address) {
		UserProfile userProfile = new UserProfile(id,name,phone,address);
		userMap.put(id, userProfile);
	}
	
	//Update
	@PutMapping("/user/{id}")
	public void putUserProfile(@PathVariable("id") String id,@RequestParam("name") String name,@RequestParam("phone") String phone, @RequestParam("address") String address) {
		UserProfile userProfile = userMap.get(id);
		userProfile.setName(name);
		userProfile.setPhone(phone);
		userProfile.setAddress(address);
	}
	
	//Delete
	@DeleteMapping("/user/{id}")
	public void deleteUserProfile(@PathVariable("id") String id) {
		userMap.remove(id);
	}
}