import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class ShaSalt {

    // 사용할 해시 알고리즘
    private static final String HASH_ALGORITHM = "SHA-256";

    // 솔트 길이
    private static final int SALT_LENGTH = 16;

    // 비밀번호 해싱 함수
    public static String hashPassword(String password, String salt) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);

            // 솔트를 바이트 배열로 변환
            byte[] saltBytes = Base64.getDecoder().decode(salt);

            // 솔트를 해시 함수에 추가
            md.update(saltBytes);

            // 비밀번호를 바이트 배열로 변환하여 해시 함수에 추가
            byte[] passwordBytes = password.getBytes();
            md.update(passwordBytes);

            // 해시된 비밀번호를 얻어냄
            byte[] hashedBytes = md.digest();

            // 해시된 비밀번호를 Base64로 인코딩
            hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    // 무작위 솔트 생성 함수
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static void main(String[] args) {
        // 사용자의 비밀번호
        String userPassword = "user123";

        // 솔트 생성
        String salt = generateSalt();

        // 비밀번호 해시 생성
        String hashedPassword = hashPassword(userPassword, salt);

        // 결과 출력
        System.out.println("Original Password: " + userPassword);
        System.out.println("Salt: " + salt);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}