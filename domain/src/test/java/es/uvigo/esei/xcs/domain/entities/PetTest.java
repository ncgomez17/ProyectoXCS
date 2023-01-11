package es.uvigo.esei.xcs.domain.entities;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class PetTest {
	private String name;
	private AnimalType animalType;
	private Date birth;
	private Owner owner;
	
	private String newName;
	private AnimalType newAnimalType;
	private Owner newOwner;
	private Date futureDate;
	private Date newBirth;

	@Before
	public void setUp() throws Exception {
		this.name = "Rex";
		this.animalType = AnimalType.DOG;
		this.birth = new Date();
		this.owner = new Owner("pepe", "pepepass");
		
		this.newName = "Ein";
		this.newAnimalType = AnimalType.BIRD;
		this.newOwner = new Owner("José", "josepass");
		final int oneDay = 24*60*60*1000;
		this.newBirth = new Date(System.currentTimeMillis() - oneDay);
		this.futureDate = new Date(System.currentTimeMillis() + oneDay);
	}

	@Test
	public void testPetStringAnimalTypeDate() {
		final String[] names = { name, "A", StringUtils.repeat("A", 100)};
		
		for (String name : names) {
			final Pet pet = new Pet(name, animalType, birth);
			
			assertThat(pet.getName(), is(equalTo(name)));
			assertThat(pet.getAnimal(), is(equalTo(animalType)));
			assertThat(pet.getBirth(), is(equalTo(birth)));
			assertThat(pet.getOwner(), is(nullValue()));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testPetStringAnimalTypeDateNullName() {
		new Pet(null, animalType, birth);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPetStringAnimalTypeDateNameTooShort() {
		new Pet("", animalType, birth);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPetStringAnimalTypeDateNameTooLong() {
		new Pet(repeat('A', 101), animalType, birth);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPetStringAnimalTypeDateNullAnimal() {
		new Pet(name, null, birth);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPetStringAnimalTypeDateNullBirth() {
		new Pet(name, animalType, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPetStringAnimalTypeDateBirthAfterCurrent() {
		new Pet(name, animalType, futureDate);
	}

	@Test
	public void testPetStringAnimalTypeDateOwner() {
		final String[] names = { name, "A", repeat("A", 100)};
		
		for (String name : names) {
			final Pet pet = new Pet(name, animalType, birth, owner);
			
			assertThat(pet.getName(), is(equalTo(name)));
			assertThat(pet.getAnimal(), is(equalTo(animalType)));
			assertThat(pet.getBirth(), is(equalTo(birth)));
			assertThat(pet.getOwner(), is(equalTo(owner)));
			assertThat(owner.ownsPet(pet), is(true));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testPetStringAnimalTypeDateOwnerNullName() {
		new Pet(null, animalType, birth, owner);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPetStringAnimalTypeDateOwnerNameTooShort() {
		new Pet("", animalType, birth, owner);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPetStringAnimalTypeDateOwnerNameTooLong() {
		new Pet(repeat('A', 101), animalType, birth, owner);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPetStringAnimalTypeDateOwnerNullAnimal() {
		new Pet(name, null, birth, owner);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPetStringAnimalTypeDateOwnerNullBirth() {
		new Pet(name, animalType, null, owner);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPetStringAnimalTypeDateOwnerBirthAfterCurrent() {
		new Pet(name, animalType, futureDate, owner);
	}

	@Test
	public void testSetName() {
		final Pet pet = new Pet(name, animalType, birth);
		
		pet.setName(newName);
		
		assertThat(pet.getName(), is(equalTo(newName)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetNameNull() {
		final Pet pet = new Pet(name, animalType, birth);
		
		pet.setName(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameTooShort() {
		final Pet pet = new Pet(name, animalType, birth);
		
		pet.setName("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameNullLong() {
		final Pet pet = new Pet(name, animalType, birth);
		
		pet.setName(repeat('A', 101));
	}

	@Test
	public void testSetAnimal() {
		final Pet pet = new Pet(name, animalType, birth);
		
		pet.setAnimal(newAnimalType);
		
		assertThat(pet.getAnimal(), is(equalTo(newAnimalType)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetAnimalNull() {
		final Pet pet = new Pet(name, animalType, birth);
		
		pet.setAnimal(null);
	}

	@Test
	public void testSetBirth() {
		final Pet pet = new Pet(name, animalType, birth);
		
		pet.setBirth(newBirth);
		
		assertThat(pet.getBirth(), is(equalTo(newBirth)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetBirthNull() {
		final Pet pet = new Pet(name, animalType, birth);
		
		pet.setBirth(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetBirthAfterCurrent() {
		final Pet pet = new Pet(name, animalType, birth);
		
		pet.setBirth(futureDate);
	}

	@Test
	public void testSetOwner() {
		final Pet pet = new Pet(name, animalType, birth, owner);
		
		pet.setOwner(newOwner);
		
		assertThat(pet.getOwner(), is(equalTo(newOwner)));
		assertThat(newOwner.ownsPet(pet), is(true));
		assertThat(owner.ownsPet(pet), is(false));
	}

	@Test
	public void testSetOwnerNull() {
		final Pet pet = new Pet(name, animalType, birth, owner);
		
		pet.setOwner(null);
		
		assertThat(pet.getOwner(), is(nullValue()));
		assertThat(owner.ownsPet(pet), is(false));
	}
}
