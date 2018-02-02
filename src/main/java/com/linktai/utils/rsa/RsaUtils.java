package com.linktai.utils.rsa;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class RsaUtils {
	public static final String ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	public static final String PUBLICKEY = "publickey";
	public static final String PRIVATEKEY = "privatekey";
	public static final String public_key="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfU9YbEmwClZKekbBogSlvI3+6XMlr0jvrbq9ZiU/UEtnEenTh6BP/UGnVGRcJyu/5jmjclVa0+p78wGt0AmOeCq0U9ewg72D/pP/7WTdrzD4ZBRakIOuGoMvUmwxZzM3w3yJc9h+DUgXybWUgedk16Sb3Xm9OlslKZx91fSuOQwIDAQAB";
	public static final String private_key="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ9T1hsSbAKVkp6RsGiBKW8jf7pcyWvSO+tur1mJT9QS2cR6dOHoE/9QadUZFwnK7/mOaNyVVrT6nvzAa3QCY54KrRT17CDvYP+k//tZN2vMPhkFFqQg64agy9SbDFnMzfDfIlz2H4NSBfJtZSB52TXpJvdeb06WyUpnH3V9K45DAgMBAAECgYEAjXuwNGgefAkAo0kygwq3lZbLdN2yi8msJfwrQHI77lIzCVPI+VbSczWgVXirSanpgg2hNshyr9GiX0Yr5V+9vwkdY+0l/Om93E9qDCfwo0dYcLeD7GyyiVnUarD1NnZ+CLHOKIJRfoZS7S7aj1rYDTJHP9k4J8a9eEQQBdHWovECQQD9xS06HqKTWx+I9vNy+8UtzoBsb33lfNc5LAGwFPansNA1p3bymow/Xv0h/KAh7clNJLWhsuHECipDS9HuMFf5AkEAoLo5EyauF7STW2zxoeXS6lgAF7qCSwJFVopKCqAU16FVkndG39cdSDcLdPp+chztwz1+3bSWG17cbRM7zsw/GwJAdktx98PMGVDC9H/ZoGII2n+GkgWu9EkrsrT23HQJPsZHaJt18UVl1yyaZztyEh7cVZ+lI4lVgKMUbGZtp9DVUQJADqBjyUZf2XBpaIyFgMI1a3YOrBog+Up/nNyA2G6w9t6LtwR7wqsE0ymPNVoGX8QaLwZNoUdp0ZvUW2jArJURuQJBAN0RT/CRwKETFSBhR3j1+4Ar/scQiB0zW9Hi9ldi9adG8qfmlh/616iMEzdmj+EFdL4pGbtH0mpzRNwjdZyxGps=";

	public static Map<String, Key> getKeys() {
		HashMap<String, Key> hashMap = new HashMap<String, Key>();
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
			keyPairGenerator.initialize(1024);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			hashMap.put(PUBLICKEY, publicKey);
			hashMap.put(PRIVATEKEY, privateKey);
			return hashMap;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ��Կ����
	public static String encript(String data, String publicKey) {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode(publicKey));
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(data.getBytes());
			return encoder(doFinal);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ˽Կ����
	public static byte[] decode(String encript, String privateKey) {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode(privateKey));
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PrivateKey private1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, private1);
			byte[] decode = decode(encript);
			return cipher.doFinal(decode);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param data
	 *            �Ѽ��ܵ�����
	 * @param privateKey
	 * @return
	 */
	public static String sign(String data, String privateKey) {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode(privateKey));
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privateKey2);
			signature.update(decode(data));
			byte[] sign = signature.sign();
			return encoder(sign);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	public static boolean verify(String encript, String sign,String publicKey) {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode(publicKey));
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(publicKey2);
			signature.update(decode(encript));
			return signature.verify(decode(sign));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return false;
	}

	

	public static String encoder(byte[] bs) {
		return Base64.getEncoder().encodeToString(bs);
	}

	public static byte[] decode(String string) {
		return Base64.getDecoder().decode(string);
	}

	public static String createSign(String date) {
		if (date != null) {
			String encript = encript(date, public_key);
			String sign = sign(encript, private_key);
			return sign;
		}
		return null;
	}

	@Test
	public void test1() {
		String string = "ww";
		String encript = encript(string, public_key);
		byte[] decode = decode(encript, private_key);
		System.out.println(new String(decode));
		String sign = sign(encript, private_key);
		boolean verify = verify(encript, sign, public_key);
		System.out.println(verify);

	}
	
	@Test
	public void test3() {
		Map<String, Key> keys = getKeys();
		String publicKey = encoder(keys.get(PUBLICKEY).getEncoded());
		String privatekey = encoder(keys.get(PRIVATEKEY).getEncoded());
		System.out.println(publicKey);
		System.out.println(privatekey);
	}

	

}
