import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dwuziu on 02/05/17.
 */
public class Hmac {

    public static final String HMAC_ALGORITHM = "HmacSHA256";

    public static String calculateHmac(String message, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac = Mac.getInstance(HMAC_ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(secret.getBytes(), HMAC_ALGORITHM);
        hmac.init(key);

        return Base64.encodeBase64String(hmac.doFinal(message.getBytes()));
    }


    public static boolean checkHmac(String message, String hmac, String secret) throws InvalidKeyException, NoSuchAlgorithmException {
        return hmac == calculateHmac(message, secret);
    }

}
