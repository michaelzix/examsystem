package exam.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.*;

public abstract class EncryptUtils {
	

	public static String MD5Encode(String unencrypted){
		return new DigestUtils(MD5).digestAsHex(StringUtils.defaultString(unencrypted));
	}
	
}
