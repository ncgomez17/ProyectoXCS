package es.uvigo.esei.xcs.domain.entities;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public abstract class UserTest {
	protected String login;
	protected String password;
	protected String md5Pass;
	
	protected String newLogin;
	protected String newPassword;
	protected String newPasswordMD5;
	
	protected String shortLogin;
	protected String longLogin;
	protected String shortPassword;

	@Before
	public void setUpUser() throws Exception {
		this.login = "Pepe";
		this.password = "pepepa";
		this.md5Pass = "41B0EEB2550AE3A43BF34DC2E8408E90";
		
		this.newLogin = "José";
		this.newPassword = "josepass";
		this.newPasswordMD5 = "A3F6F4B40B24E2FD61F08923ED452F34";
		
		this.shortLogin = "";
		this.longLogin = repeat('A', 101); 
		this.shortPassword = repeat('A', 5);
	}
	
	protected abstract User newUser(String login, String password);

	@Test
	public void testUserStringString() {
		final String[] logins = { login, "A", repeat('A', 100) };
		
		for (String login : logins) {
			final User admin = newUser(login, password);
	
			assertThat(admin.getLogin(), is(equalTo(login)));
			assertThat(admin.getPassword(), is(equalTo(md5Pass)));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testUserStringStringNullLogin() {
		newUser(null, password);
	}
	
	@Test(expected = NullPointerException.class)
	public void testUserStringStringNullPassword() {
		newUser(login, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserStringStringLoginTooShort() {
		newUser(shortLogin, password);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserStringStringLoginTooLong() {
		newUser(longLogin, password);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserStringStringPasswordTooShort() {
		newUser(login, shortPassword);
	}
	
	@Test
	public void testSetLogin() {
		final String[] logins = { login, "A", repeat('A', 100) };
		
		for (String login : logins) {
			final User admin = newUser(login, password);
			
			admin.setLogin(newLogin);
	
			assertThat(admin.getLogin(), is(equalTo(newLogin)));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetLoginTooShort() {
		final User admin = newUser(login, password);
		
		admin.setLogin(shortLogin);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetLoginTooLong() {
		final User admin = newUser(login, password);
		
		admin.setLogin(longLogin);
	}

	@Test
	public void testSetPassword() {
		final User admin = newUser(login, password);
		
		admin.setPassword(newPasswordMD5);

		assertThat(admin.getPassword(), is(equalTo(newPasswordMD5)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPasswordNoMD5() {
		final User admin = newUser(login, password);
		
		admin.setPassword("No MD5 password");
	}

	@Test(expected = NullPointerException.class)
	public void testSetPasswordNullValue() {
		final User admin = newUser(login, password);
		
		admin.setPassword(null);
	}

	@Test
	public void testChangePassword() {
		final User admin = newUser(login, password);
		
		admin.changePassword(newPassword);

		assertThat(admin.getPassword(), is(equalTo(newPasswordMD5)));
	}

	@Test(expected = NullPointerException.class)
	public void testChangePasswordNull() {
		final User admin = newUser(login, password);
		
		admin.changePassword(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testChangePasswordTooShort() {
		final User admin = newUser(login, password);
		
		admin.changePassword(shortPassword);
	}
}
