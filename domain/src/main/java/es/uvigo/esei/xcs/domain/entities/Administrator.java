package es.uvigo.esei.xcs.domain.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An administrator of the application.
 * 
 * @author Miguel Reboiro Jato
 */
@Entity
@DiscriminatorValue("ADMIN")
@XmlRootElement(name = "admin", namespace = "http://entities.domain.xcs.esei.uvigo.es")
public class Administrator extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	// Required for JPA
	Administrator() {}
	
	/**
	 * Creates a new instance of {@code Administrator}.
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
	public Administrator(String login, String password) {
		super(login, password);
	}
}
