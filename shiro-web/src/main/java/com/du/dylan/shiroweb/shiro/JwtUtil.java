package com.du.dylan.shiroweb.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {

    private static final Long EXPIRE_TIME = 5 * 60 * 1000L;

    private static final String SECRET = "Dylan";

    private static final String PK_ID = "phone";

    /**
     * 生成 token 时，指定 token 过期时间 EXPIRE_TIME 和签名密钥 SECRET，
     * 然后将 expireDate 和 username 写入 token 中，并使用带有密钥的 HS256 签名算法进行签名
     * @param username
     * @return
     */
    public static String sign(String phone) {
        String token = null;
        // 过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        token = JWT.create()
                .withClaim(PK_ID, phone)
                .withExpiresAt(expireDate)
                .sign(algorithm);
        return token;
    }

    /**
     * 验证token，如果验证失败，便会抛出异常
     * @param token
     * @param username
     * @return
     */
    public static boolean verify(String token, String phone) {
        boolean isSuccess = false;
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim(PK_ID, phone)
                .build();
        // 验证token
        verifier.verify(token);
        isSuccess = true;
        return isSuccess;
    }

    /**
     *
     * @param token
     * @return
     */
    public static String getPkIdFromToken(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getClaim(PK_ID).asString();
        } catch (JWTDecodeException e) {
            log.error("Failed to Decode jwt. {}", e.getMessage());
            return null;
        }
    }
}
