
package acme.features.authenticated.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneById(int id);

	@Query("select j from Job j where j.deadline > CURRENT_TIMESTAMP") //Only active offers are shown
	Collection<Job> findManyActive();

	@Query("select d.duties from Job j join j.descriptor d where j.id = ?1")
	Collection<Duty> findDuties(int id);

}
