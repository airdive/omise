package com.omise.test;

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
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class RSAtest {
	public static final String ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	public static final String PUBLICKEY = "publickey";
	public static final String PRIVATEKEY = "privatekey";

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
	public static byte[] encript(String data, Key publicKey) {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(data.getBytes());
			return doFinal;
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
	
	public static byte[] decode(byte[] encript, Key privateKey) {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PrivateKey private1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, private1);
			return cipher.doFinal(encript);
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
	
	
	public static byte[] sign(byte[] encript, Key privateKey) {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privateKey2);
			signature.update(encript);
			byte[] sign = signature.sign();
			return sign;
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
	
	public static boolean verify(byte[] encript, byte[] sign) {
		Key publicKey = getKeys().get(PUBLICKEY);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(publicKey2);
			signature.update(encript);
			return signature.verify(sign);
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

	
	@Test
	public void test() {
		String string = "qwe";
		Map<String, Key> keys = getKeys();
		byte[] encript = encript(string, keys.get(PUBLICKEY));
		byte[] decode = decode(encript, keys.get(PRIVATEKEY));
		String string2 = new String(decode);
		System.out.println(string2);
		
		byte[] sign = sign(encript, keys.get(PRIVATEKEY));
		boolean verify = verify(encript, sign);
		System.out.println(verify);
	}

}
