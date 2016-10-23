package com.mycompany.spring.security.authentication;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class MyPasswordEncoder extends MessageDigestPasswordEncoder {

	public MyPasswordEncoder(String algorithm) {
		super(algorithm);
	}

	@Override
	public String encodePassword(String rawPass, Object salt) {
		// TODO Auto-generated method stub
		return super.encodePassword(rawPass, salt);
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		// TODO Auto-generated method stub
		return super.isPasswordValid(encPass, rawPass, salt);
	}

	private String saltedHash(String alg, String str, String salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance(alg);

			digest.update(str.getBytes("UTF-8"));
			if (salt == null) {
				BigInteger salti = new BigInteger("0").abs();
				salt = salti.toString(16);
			}
			byte[] hash = digest.digest(hexToBytes(salt));

			for (int iteration = 0; iteration < 1000; iteration++) {
				digest.reset();
				hash = digest.digest(hash);
			}

			return bytesToHex(hash);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private String oldHashString(String alg, String str) {
		try {
			byte[] hashedValue = MessageDigest.getInstance(alg).digest(
					str.getBytes());

			return bytesToHex(hashedValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public byte[] hexToBytes(String hexString) {
		BigInteger bi = new BigInteger(hexString, 16);
		return bi.toByteArray();
	}

	public String bytesToHex(byte[] theBytes) {
		StringBuffer sb = new StringBuffer(theBytes.length * 2);

		for (int k = 0; k < theBytes.length; k++) {
			if ((theBytes[k] >= 0) && (theBytes[k] <= 15)) {
				sb.append('0');
			}

			sb.append(Integer.toHexString(theBytes[k] & 0xFF));
		}

		return sb.toString();
	}
}
