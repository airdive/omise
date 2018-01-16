package com.linktai.utils.rsa;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
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

public class RsaUtils {
	public static final String ALGORITHM = "RSA";
	public static final String PUBLICKEY = "publickey";
	public static final String PRIVATEKEY = "privatekey";
	
	public static Map<String, Key> getKeys(){
		HashMap<String,Key> hashMap = new HashMap<String, Key>();
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
	//π´‘øº”√‹
	public byte[] encript(String data,Key publicKey) {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(data.getBytes());
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
	//ÀΩ‘øΩ‚√‹
	public byte[] decode(byte[] encript,Key privateKey) {
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
	
	@Test
	public void test1() {
		String aString = "123";
		Map<String, Key> keys = getKeys();
		byte[] encript = encript(aString, keys.get(PUBLICKEY));
		byte[] decode = decode(encript, keys.get(PRIVATEKEY));
		String string = new String(decode);
		System.out.println(string);
	}
}
