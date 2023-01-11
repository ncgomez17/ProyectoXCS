package es.uvigo.esei.xcs.domain.entities;

public class AdministratorTest extends UserTest {
	@Override
	protected User newUser(String login, String password) {
		return new Administrator(login, password);
	}
}
