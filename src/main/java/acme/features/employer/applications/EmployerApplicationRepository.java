
package acme.features.employer.applications;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select aj from Application aj where aj.id = ?1")
	Application findOneById(int id);

	@Query("select jo from Job jo join jo.job aj where aj.id= ?1")
	Job findId(int id);

	@Query("select a from Application a where a.job.id in (select j.id from Job j where j.employer.id = ?1)")
	Collection<Application> findManyByEmployerId(int EmployerId);

}
