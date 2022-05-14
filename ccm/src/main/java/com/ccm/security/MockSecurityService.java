package com.ccm.security;

import org.springframework.stereotype.Service;

/**
 * Created by ShinD on 2022/05/10.
 */
@Service
public class MockSecurityService implements SecurityService{

	@Override
	public Long getMemberId() {
		return 1L;
	}
}
