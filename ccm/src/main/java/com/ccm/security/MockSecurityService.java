package com.ccm.security;

/**
 * Created by ShinD on 2022/05/10.
 */
public class MockSecurityService implements SecurityService{

	@Override
	public Long getMemberId() {
		return 1L;
	}
}
