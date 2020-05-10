package com.infy.shopping;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.shopping.entity.UserEntity;
import com.infy.shopping.exception.SAppsException;
import com.infy.shopping.model.user.AccountType;
import com.infy.shopping.model.user.Login;
import com.infy.shopping.repository.UserRepository;
import com.infy.shopping.security.jwt.JwtUtil;
import com.infy.shopping.service.LoginService;
import com.infy.shopping.service.LoginServiceImpl;

@RunWith(SpringRunner.class)
public class LoginServiceTest {

	@MockBean
	private UserRepository userRepository;
	@MockBean
	private JwtUtil jwtUtil;
	
	private LoginService loginService;
	
	@Before
	public void initLoginServiceTest() {
		this.loginService = new LoginServiceImpl(userRepository, jwtUtil);
	}

	@Test
	public void loginTest1() {
		Login login = Login.builder()
				.userEmail("nikhil.bhargav01@infosys.com")
				.password("Abcd#123")
				.build();
		
		UserEntity userEntity = UserEntity.builder()
				.userId("d47740c7-ca62-44ff-a6cf-722c50ba6b55")
				.name("Nikhil Bhargav")
				.email("nikhil.bhargav01@infosys.com")
				.password("Abcd#123")
				.accountType(AccountType.REGESTERED.toString())
				.build();
		
		Mockito.when(userRepository.findByEmail(login.getUserEmail()))
			.thenReturn(userEntity);			
		
		assertEquals(loginService.login(login).getValue(), "d47740c7-ca62-44ff-a6cf-722c50ba6b55");
	}
	
	@Test(expected=SAppsException.class)
	public void loginTest2() {
		Login login = Login.builder()
				.userEmail("nikhil.bhargav01@infosys.com")
				.password("WrongPassword")
				.build();
		
		UserEntity userEntity = UserEntity.builder()
				.userId("d47740c7-ca62-44ff-a6cf-722c50ba6b55")
				.name("Nikhil Bhargav")
				.email("nikhil.bhargav01@infosys.com")
				.password("Abcd#123")
				.accountType(AccountType.REGESTERED.toString())
				.build();
		
		Mockito.when(userRepository.findByEmail(login.getUserEmail()))
			.thenReturn(userEntity);			
		
		assertEquals(loginService.login(login).getValue(), "d47740c7-ca62-44ff-a6cf-722c50ba6b55");
	}
	

	@Test(expected=SAppsException.class)
	public void loginTest3() {
		Login login = Login.builder()
				.userEmail("Wrong_Email@infosys.com")
				.password("Abcd#123")
				.build();
		
		UserEntity userEntity = null;
		
		Mockito.when(userRepository.findByEmail(login.getUserEmail()))
			.thenReturn(userEntity);			
		
		assertEquals(loginService.login(login).getValue(), "d47740c7-ca62-44ff-a6cf-722c50ba6b55");
	}

	@Test
	public void loginTest4() {
		Login login = Login.builder()
				.guestId("e8cebd44-47ca-4bb4-a23e-b0c816cc9d5e")
				.build();
		
		Optional<UserEntity> userEntity = Optional.of(UserEntity.builder()
				.userId("e8cebd44-47ca-4bb4-a23e-b0c816cc9d5e")
				.accountType(AccountType.GUEST.toString())
				.build());
		
		Mockito.when(userRepository.findById(login.getGuestId()))
			.thenReturn(userEntity);			
		
		assertEquals(loginService.login(login).getValue(), "e8cebd44-47ca-4bb4-a23e-b0c816cc9d5e");
	}
	
	@Test(expected=SAppsException.class)
	public void loginTest5() {
		Login login = Login.builder()
				.guestId("WrongGuestId")
				.build();
		
		Optional<UserEntity> userEntity = Optional.empty();
		
		Mockito.when(userRepository.findById(login.getGuestId()))
			.thenReturn(userEntity);			
		
		assertEquals(loginService.login(login).getValue(), "e8cebd44-47ca-4bb4-a23e-b0c816cc9d5e");
	}
	

	@Test(expected=SAppsException.class)
	public void loginTest6() {
		Login login = Login.builder()
				.guestId("e8cebd44-47ca-4bb4-a23e-b0c816cc9d5e")
				.build();
		
		Optional<UserEntity> userEntity = Optional.of(UserEntity.builder()
				.userId("e8cebd44-47ca-4bb4-a23e-b0c816cc9d5e")
				.name("Nikhil Bhargav")
				.email("nikhil.bhargav01@infosys.com")
				.password("Abcd#123")
				.accountType(AccountType.REGESTERED.toString())
				.build());
		
		Mockito.when(userRepository.findById(login.getGuestId()))
			.thenReturn(userEntity);			
		
		assertEquals(loginService.login(login).getValue(), "e8cebd44-47ca-4bb4-a23e-b0c816cc9d5e");
	}
	
	@Test
	public void loginTest7() {
		Login login = Login.builder()
				.createGuestUser(true)
				.build();
		
		UserEntity userEntity = UserEntity.builder()
				.userId(UUID.randomUUID().toString())
				.accountType(AccountType.GUEST.toString())
				.build();
		
		Mockito.when(userRepository.saveAndFlush(userEntity))
			.thenReturn(userEntity);			
		
		assertEquals(loginService.login(login).getMessage(), "Guest user created successfully");
	}
	
	@Test(expected=SAppsException.class)
	public void loginTest8() {
		Login login = Login.builder()
				.createGuestUser(false)
				.build();
		
		UserEntity userEntity = UserEntity.builder()
				.userId(UUID.randomUUID().toString())
				.accountType(AccountType.GUEST.toString())
				.build();
		
		Mockito.when(userRepository.saveAndFlush(userEntity))
			.thenReturn(userEntity);			
		
		assertEquals(loginService.login(login).getValue(), userEntity.getUserId());
	}
	
}
