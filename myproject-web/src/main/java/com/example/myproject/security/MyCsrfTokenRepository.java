package com.example.myproject.security;

import com.example.myproject.redis.RedisClient;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author kyu.yzf
 * @date 2023/5/23 21:26
 */
@Component
public class MyCsrfTokenRepository implements CsrfTokenRepository {

    private final String HEADER_NAME = "X-CSRF-TOKEN";

    private final String PARAMETER_NAME = "_csrf";

    private final String TOKEN_KEY = "tokenKey";

    @Resource
    private RedisClient redisClient;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String csrfToken = UUID.randomUUID().toString();
        System.out.println("csrfToken = " + csrfToken);
        return new DefaultCsrfToken(HEADER_NAME, PARAMETER_NAME, csrfToken);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("token = " + token.getToken());
        redisClient.addCsrfToken(TOKEN_KEY,token.getToken());
        response.setHeader("csrf-token",token.getToken());
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String csrfToken = redisClient.getCsrfToken(TOKEN_KEY);
        return StringUtils.isEmpty(csrfToken) ? null : new DefaultCsrfToken(HEADER_NAME, PARAMETER_NAME, csrfToken);
    }
}
