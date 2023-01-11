package es.uvigo.esei.xcs.domain.entities;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.inclusiveBetween;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * A pet.
 * 
 * @author Miguel Reboiro-Jato
 */
@Entity(name = "Pet")
@XmlRootElement(name = "pet", namespace = "http://entities.domain.xcs.esei.uvigo.es")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pet implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(nullable = false, length = 4)
	@Enumerated(EnumType.STRING)
	private AnimalType animal;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date birth;
	
	@ManyToOne
	@JoinColumn(name = "owner", referencedColumnName = "login", nullable = false)
	@XmlTransient
	private Owner owner;
	
	// Required for JPA.
	Pet() {}
	
	// For testing purposes.
	Pet(int id, String name, AnimalType animal, Date birth) {
		this(name, animal, birth, null);
		this.id = id;
	}
	
	/**
	 * Creates a new instance of {@code Pet} without owner.
	 * 
	 * @param name the name of the pet. This parameter must be a non empty and
	 * non {@code null} string with a maximum length of 100 chars.
	 * @param animal the type of animal. This parameter can not be {@code null}.
	 * @param birth the date of birth of this pet. This parameter can not be
	 * {@code null} and the date can not be after the current date.
	 * 
	 * @throws NullPointerException if a {@code null} value is passed as the
	 * value for any parameter.
	 * @throws IllegalArgumentException if value provided for any parameter is
	 * not valid according to its description.
	 */
	public Pet(String name, AnimalType animal, Date birth) {
		this(name, animal, birth, null);
	}
	
	/**
	 * Creates a new instance of {@code Pet} with owner.
	 * 
	 * @param name the name of the pet. This parameter must be a non empty and
	 * non {@code null} string with a maximum length of 100 chars.
	 * @param animal the type of animal. This parameter can not be {@code null}.
	 * @param birth the date of birth of this pet. This parameter can not be
	 * {@code null} and the date can not be after the current date.
	 * @param owner the owner of the pet. {@code null} value is valid,
	 * meaning that the pet has no owner.
	 * 
	 * @throws NullPointerException if a {@code null} value is passed as the
	 * value for any parameter except for {@code owner}.
	 * @throws IllegalArgumentException if value provided for any parameter is
	 * not valid according to its description.
	 */
	public Pet(String name, AnimalType animal, Date birth, Owner owner) {
		this.setName(name);
		this.setAnimal(animal);
		this.setBirth(birth);
		this.setOwner(owner);
	}
	
	public int getId() {
		return id;
	}

	/**
	 * Returns the name of the pet.
	 * 
	 * @return the name of the pet.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the pet.
	 * 
	 * @param name the new name of the pet. This parameter must be a non empty
	 * and non {@code null} string with a maximum length of 100 chars.
	 * 
	 * @throws NullPointerException if a {@code null} value is passed.
	 * @throws IllegalArgumentException if the length of the string passed is
	 * not valid.
	 */
	public void setName(String name) {
		requireNonNull(name, "name can't be null");
		inclusiveBetween(1, 100, name.length(), "name must have a length between 1 and 100");
		
		this.name = name;
	}

	/**
	 * Returns the type of animal of this pet.
	 * 
	 * @return the type of animal of this pet.
	 */
	public AnimalType getAnimal() {
		return animal;
	}

	/**
	 * Sets the animal type of this pet.
	 * 
	 * @param animal the new animal type for this pet. This parameter can not be
	 * {@code null}.
	 * 
	 * @throws NullPointerException if a {@code null} value is passed.
	 */
	public void setAnimal(AnimalType animal) {
		requireNonNull(animal, "animal can't be null");
		
		this.animal = animal;
	}

	/**
	 * Returns the date of birth of this pet.
	 * 
	 * @return the date of birth of this pet.
	 */
	public Date getBirth() {
		return birth;
	}

	/**
	 * Sets the date of birth of this pet.
	 * 
	 * @param birth the new date of birth for this pet. This parameter can not
	 * be {@code null} and the date can not be previous to the current date.
	 * 
	 * @throws NullPointerException if a {@code null} value is passed.
	 * @throws IllegalArgumentException if the value provided is after the
	 * current date.
	 */
	public void setBirth(Date birth) {
		requireNonNull(birth, "birth can't be null");
		inclusiveBetween(new Date(0), new Date(), birth,
			"birth must be previous to the current time"
		);
		
		this.birth = birth;
	}

	/**
	 * Returns the owner of this pet. The value returned can be {@code null},
	 * meaning that the pet has no owner.
	 * 
	 * @return the owner of this pet.
	 */
	public Owner getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of this pet. The new owner can be {@code null}, meaining
	 * that the pet has no owner.
	 * 
	 * @param owner the new owner of the pet. {@code null} value is valid,
	 * meaning that the pet has no owner.
	 */
	public void setOwner(Owner owner) {
		if (this.owner != null)
			this.owner.internalRemovePet(this);
		
		this.owner = owner;
		
		if (this.owner != null)
			this.owner.internalAddPet(this);
	}
}
