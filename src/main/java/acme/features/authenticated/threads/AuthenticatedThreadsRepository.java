
package acme.features.authenticated.threads;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.threads.Threads;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadsRepository extends AbstractRepository {

	@Query("select t from Threads t where t.id = ?1")
	Threads findOneById(int id);

	@Query("select distinct t from Threads t join t.messages m on m.user.id = ?1")
	Collection<Threads> findManyByAuthenticatedId(int id);

}
