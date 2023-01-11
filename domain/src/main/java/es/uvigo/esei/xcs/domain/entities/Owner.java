package es.uvigo.esei.xcs.domain.entities;

import static java.util.Arrays.stream;
import static java.util.Collections.unmodifiableCollection;
import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A pet owner.
 * 
 * @author Miguel Reboiro-Jato
 */
@Entity
@DiscriminatorValue("OWNER")
@XmlRootElement(name = "owner", namespace = "http://entities.domain.xcs.esei.uvigo.es")
public class Owner extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(
		mappedBy = "owner",
		targetEntity = Pet.class,
		cascade = CascadeType.ALL,
		orphanRemoval = true,
		fetch = FetchType.EAGER
	)
	private Collection<Pet> pets;
	
	// Required for JPA
	Owner() {}
	
	/**
	 * Creates a new instance of {@code Owner} without pets.
	 * 
	 * @param login the login that identifies the user. This parameter must be a
	 * non empty and non {@code null} string with a maximum length of 100 chars.
	 * @param password the raw password of the user. This parameter must be a
	 * non {@code null} string with a minimum length of 6 chars.
	 * 
	 * @throws NullPointerException if a {@code null} value is passed as the
	 * value for any parameter.
	 * @throws IllegalArgumentException if value provided for any parameter is
	 * not valid according to its description.
	 */
	public Owner(String login, String password) {
		super(login, password);
		this.pets = new ArrayList<>();
	}
	
	/**
	 * Creates a new instance of {@code Owner} with pets.
	 * 
	 * @param login the login that identifies the user. This parameter must be a
	 * non empty and non {@code null} string with a maximum length of 100 chars.
	 * @param password the raw password of the user. This parameter must be a
	 * non {@code null} string with a minimum length of 4 chars.
	 * @param pets the pets that belong to this owner. The list of pets can be
	 * empty. {@code null} values are not supported.
	 * 
	 * @throws NullPointerException if a {@code null} value is passed as the
	 * value for any parameter.
	 * @throws IllegalArgumentException if value provided for any parameter is
	 * not valid according to its description.
	 */
	public Owner(String login, String password, Pet ... pets) {
		super(login, password);
		this.pets = new ArrayList<>();
		
		stream(pets).forEach(this::addPet);
	}
	
	/**
	 * Returns the pets that belongs to this owner. The collection returned is
	 * unmodifiable and no order are guaranteed.
	 * If the pet already belongs to this owner, no action will be done.
	 * 
	 * @return the pets that belongs to this owner. 
	 */
	public Collection<Pet> getPets() {
		return unmodifiableCollection(pets);
	}
	
	/**
	 * Adds a pet to this owner. The pet's owner will be set to this instance.
	 * 
	 * @param pet the pet to add to this owner. {@code null} values not
	 * supported.
	 * @throws NullPointerException if the {@code pet} is {@code null}.
	 */
	public void addPet(Pet pet) {
		requireNonNull(pet, "pet can't be null");
		
		if (!this.ownsPet(pet)) {
			pet.setOwner(this);
		}
	}
	
	/**
	 * Removes a pet from this owner. The pet's owner will be set to
	 * {@code null}.
	 * 
	 * @param pet the pet to remove from this owner. {@code null} values not
	 * supported.
	 * @throws NullPointerException if the {@code pet} is {@code null}.
	 * @throws IllegalArgumentException if the {@code pet} does not belong to
	 * this owner.
	 */
	public void removePet(Pet pet) {
		requireNonNull(pet, "pet can't be null");
		
		if (this.ownsPet(pet)) {
			pet.setOwner(null);
		} else {
			throw new IllegalArgumentException("pet doesn't belong to this owner");
		}
	}
	
	/**
	 * Checks if a pet belongs to this owner.
	 * 
	 * @param pet the pet whose property will be checked.
	 * @return {@code true} if the pet belongs to this owner. {@code false}
	 * otherwise.
	 */
	public boolean ownsPet(Pet pet) {
		return this.pets.contains(pet);
	}
	
	/**
	 * Adds a pet directly to the pets collection of this owner if the pet does
	 * not already belongs to this owner. The pet's owner will not be updated.
	 * 
	 * @param pet the pet to add to this owner. {@code null} values not
	 * supported.
	 * @throws NullPointerException if the {@code pet} is {@code null}.
	 */
	void internalAddPet(Pet pet) {
		requireNonNull(pet, "pet can't be null");
		
		if (!this.ownsPet(pet))
			this.pets.add(pet);
	}
	
	/**
	 * Removes a pet directly from the pets collection of this owner. The pet's
	 * owner will not be updated.
	 * 
	 * @param pet the pet to remove from this owner. {@code null} values not
	 * supported.
	 * @throws NullPointerException if the {@code pet} is {@code null}.
	 */
	void internalRemovePet(Pet pet) {
		requireNonNull(pet, "pet can't be null");
		
		this.pets.remove(pet);
	}
}
