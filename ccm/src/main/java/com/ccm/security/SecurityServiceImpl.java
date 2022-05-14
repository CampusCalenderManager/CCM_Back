package com.ccm.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * Created by ShinD on 2022/05/14.
 */
@Service
public class SecurityServiceImpl implements SecurityService{
	@Override
	public Long getMemberId() {
		return Long.parseLong(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
	}
}
