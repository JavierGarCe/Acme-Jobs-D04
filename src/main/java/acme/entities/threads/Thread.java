
package acme.entities.threads;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Thread extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long			serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String						title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date						moment;

	// Relationships -----------------------------------------------------------

	@NotEmpty
	@OneToMany
	Collection<@Valid Message>			messages;

	@NotEmpty
	@ManyToMany
	Collection<@Valid Authenticated>	authenticateds;
}
