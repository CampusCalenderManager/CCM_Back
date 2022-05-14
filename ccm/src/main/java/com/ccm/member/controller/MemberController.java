package com.ccm.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccm.member.controller.dto.SignUpRequest;
import com.ccm.member.service.MemberService;

import lombok.RequiredArgsConstructor;

/**
 * Created by ShinD on 2022/05/14.
 */
@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("signUp")
	public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
		memberService.create(signUpRequest.toServiceDto());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}


}
