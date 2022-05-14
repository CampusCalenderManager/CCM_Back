package com.ccm.auth.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by ShinD on 2022/05/14.
 */
public interface JwtService {
	String createAccessToken(Long id);

	Long extractMemberId(String jwt);

	void sendToken(HttpServletResponse response, String jwt);

	Optional<String> extractToken(HttpServletRequest request);

	boolean isValid(String jwt);
}