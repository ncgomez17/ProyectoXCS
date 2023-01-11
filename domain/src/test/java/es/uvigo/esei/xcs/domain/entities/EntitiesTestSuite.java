package es.uvigo.esei.xcs.domain.entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	OwnerTest.class,
	AdministratorTest.class,
	PetTest.class
})
public class EntitiesTestSuite {}
