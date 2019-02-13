package com.travelblog.service.auth;

import com.travelblog.model.redis.AuthToken;
import com.travelblog.repository.UserRepository;
import com.travelblog.repository.redis.AuthTokenRepository;
import com.travelblog.service.auth.impl.AuthServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthTestService {

    private final String email = "email";

    private final String password = "password";

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @InjectMocks
    private AuthServiceImpl sut;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthTokenRepository authTokenRepository;

    @Mock
    private JwtService jwtService;

    @Autowired
    private BCrypt bCrypt;

    @Test
    public void TestCheckEmailAndPasswordNotFound() {
        when(userRepository.findOneByEmail(email)).thenReturn(Optional.empty());
        boolean result = sut.checkEmailAndPassword(email, password);
        Mockito.verify(userRepository, Mockito.times(1)).findOneByEmail(email);
        assertThat(result, equalTo(false));
    }

    /**
     * @TODO Cannot mock bcrypt somehow
     */

    @Test
    public void TestIsAuthenticatedIfNoTokenInRedis() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        when(authTokenRepository.findById(anyString())).thenReturn(Optional.empty());
        boolean result = sut.isAuthenticated(httpServletRequest);
        assertThat(result, equalTo(false));
    }

    @Test
    public void TestIsAuthenticatedIfTokenExists() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        when(authTokenRepository.findById(anyString())).thenReturn(Optional.of(new AuthToken()));
        boolean result = sut.isAuthenticated(httpServletRequest);
        assertThat(result, equalTo(false));
    }

}
