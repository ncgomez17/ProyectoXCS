package es.uvigo.esei.xcs.domain.entities;

import static java.util.Arrays.copyOfRange;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsEmptyIterable.emptyIterable;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class OwnerTest extends UserTest {
	private Pet[] pets;
	
	private Pet petOwned;
	private Pet petNotOwned;
	private Pet[] petsWithoutOwned;

	@Before
	public void setUpOwner() throws Exception {
		this.pets = new Pet[] {
			new Pet("Lassie", AnimalType.DOG, new Date()),
			new Pet("Pajaroto", AnimalType.BIRD, new Date())
		};
		
		this.petNotOwned = new Pet("Doraemon", AnimalType.CAT, new Date());
		this.petOwned = this.pets[1];
		this.petsWithoutOwned = copyOfRange(this.pets, 0, 1);
	}
	
	@Override
	protected User newUser(String login, String password) {
		return new Owner(login, password);
	}

	@Test
	public void testOwnerStringStringCollection() {
		final String[] logins = { login, "A", repeat('A', 100) };
		
		for (String login : logins) {
			final Owner owner = new Owner(login, password, pets);
			
			assertThat(owner.getLogin(), is(equalTo(login)));
			assertThat(owner.getPassword(), is(equalTo(md5Pass)));
			assertThat(owner.getPets(), containsInAnyOrder(pets));
		}
	}
	
	@Test
	public void testOwnerStringStringCollectionEmptyPets() {
		final Owner owner = new Owner(login, password, new Pet[0]);
		
		assertThat(owner.getLogin(), is(equalTo(login)));
		assertThat(owner.getPassword(), is(equalTo(md5Pass)));
		assertThat(owner.getPets(), is(emptyIterable()));
	}

	@Test(expected = NullPointerException.class)
	public void testOwnerStringStringCollectionNullLogin() {
		new Owner(null, password, pets);
	}
	
	@Test(expected = NullPointerException.class)
	public void testOwnerStringStringCollectionNullPassword() {
		new Owner(login, null, pets);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOwnerStringStringCollectionLoginTooShort() {
		new Owner(shortLogin, password, pets);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOwnerStringStringCollectionLoginTooLong() {
		new Owner(longLogin, password, pets);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOwnerStringStringCollectionPasswordTooShort() {
		new Owner(login, shortPassword, pets);
	}
	
	@Test(expected = NullPointerException.class)
	public void testOwnerStringStringCollectionNullPets() {
		new Owner(login, password, (Pet[]) null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testOwnerStringStringCollectionPasswordPetsWithNull() {
		new Owner(login, password, new Pet[] { petNotOwned, null });
	}

	@Test
	public void testAddPet() {
		final Owner owner = new Owner(login, password);
		
		owner.addPet(petNotOwned);
		
		assertThat(owner.getPets(), contains(petNotOwned));
		assertThat(petNotOwned.getOwner(), is(owner));
	}

	@Test
	public void testAddPetAlreadyOwned() {
		final Owner owner = new Owner(login, password, pets);
		
		owner.addPet(petOwned);
		
		assertThat(owner.getPets(), containsInAnyOrder(pets));
	}

	@Test(expected = NullPointerException.class)
	public void testAddPetNull() {
		final Owner owner = new Owner(login, password);
		
		owner.addPet(null);
	}

	@Test
	public void testRemovePet() {
		final Owner owner = new Owner(login, password, pets);
		
		owner.removePet(petOwned);
		assertThat(owner.getPets(), contains(petsWithoutOwned));
		assertThat(petOwned.getOwner(), is(nullValue()));
	}

	@Test(expected = NullPointerException.class)
	public void testRemovePetNull() {
		final Owner owner = new Owner(login, password);
		
		owner.removePet(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemovePetNotOwned() {
		final Owner owner = new Owner(login, password);
		
		owner.removePet(petNotOwned);
	}

	@Test
	public void testOwnsPet() {
		final Owner owner = new Owner(login, password, pets);

		for (Pet pet : pets) {
			assertThat(owner.ownsPet(pet), is(true));
		}
		assertThat(owner.ownsPet(petNotOwned), is(false));
	}

	@Test
	public void testOwnsPetNotOwned() {
		final Owner owner = new Owner(login, password, pets);

		assertThat(owner.ownsPet(petNotOwned), is(false));
	}

	@Test
	public void testOwnsPetNull() {
		final Owner owner = new Owner(login, password, pets);

		assertThat(owner.ownsPet(null), is(false));
	}

	@Test
	public void testInternalAddPet() {
		final Owner owner = new Owner(login, password);
		
		owner.internalAddPet(petNotOwned);
		
		assertThat(owner.getPets(), contains(petNotOwned));
	}

	@Test
	public void testInternalAddPetAlreadyOwned() {
		final Owner owner = new Owner(login, password, pets);
		
		owner.internalAddPet(petOwned);
		
		assertThat(owner.getPets(), containsInAnyOrder(pets));
	}

	@Test(expected = NullPointerException.class)
	public void testInternalAddPetNull() {
		final Owner owner = new Owner(login, password);
		
		owner.internalAddPet(null);
	}

	@Test
	public void testInternalRemovePet() {
		final Owner owner = new Owner(login, password, pets);
		
		owner.internalRemovePet(petOwned);
		assertThat(owner.getPets(), contains(petsWithoutOwned));
	}

	@Test(expected = NullPointerException.class)
	public void testSetLoginNullValue() {
		final Owner owner = new Owner(login, password);
		
		owner.setLogin(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testInternalRemovePetNull() {
		final Owner owner = new Owner(login, password, pets);
		
		owner.internalRemovePet(null);
	}
}
