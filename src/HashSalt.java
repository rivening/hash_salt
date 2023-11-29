import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashSalt {
    public static void main(String[] args) {
        String pwd = "123123abc";
        System.out.println("pwd: " + pwd);
        getEncrypt(pwd,"");
        getEncrypt(pwd,getSalt());
        getEncrypt(pwd,getSalt());
    }

    // Salt
    public static String getSalt() {
        //1. Random, salt 생성
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[20];

        //2. 난수 생성
        sr.nextBytes(salt);

        //3. byte To String (10진수 문자열로 변경)
        StringBuilder sb = new StringBuilder();
        for (byte b : salt) {
            sb.append(String.format("%02x", b));
        }

        System.out.println("salt : " + sb);

        return sb.toString();
    }

    // SHA-256 알고리즘 적용
    public static void getEncrypt(String pwd, String salt) {
        String result = "";

        try {
            //1. SHA256 알고리즘 객체 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //2. SHA512 알고리즘 객체 생성
//            MessageDigest md = MessageDigest.getInstance("SHA-512");

            md.update((pwd + salt).getBytes());
            byte[] pwd_salt = md.digest();

            //3. byte To String (10진수의 문자열로 변경)
            StringBuilder sb = new StringBuilder();
            for (byte b : pwd_salt) {
                sb.append(String.format("%02x", b));
            }

            result = sb.toString();

            System.out.println("암호화 : " + result);


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}