package me.wheezygold.skLib.common;

import java.security.GeneralSecurityException;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;

public class TwoFactor {
	
	private static String code;

	public static String getCode(String secret) {
		try {
			code = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(secret);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return code;
	}

}
