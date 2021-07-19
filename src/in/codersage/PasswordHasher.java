package in.codersage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

	public static void main(String[] args) throws Exception {
		System.out.println(new PasswordHasher().getHashPass("Hello"));
	}

	public static String getHashPass(String password) throws NoSuchAlgorithmException {
		String hashpass="";
		String plainText = password;
		MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
		mdAlgorithm.update(plainText.getBytes());

		byte[] digest = mdAlgorithm.digest();
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < digest.length; i++) {
			plainText = Integer.toHexString(0xFF & digest[i]);

			if (plainText.length() < 2) {
				plainText = "0" + plainText;
			}

			hexString.append(plainText);
		}
		hashpass = hexString.toString();

		return hashpass;
	}

}
