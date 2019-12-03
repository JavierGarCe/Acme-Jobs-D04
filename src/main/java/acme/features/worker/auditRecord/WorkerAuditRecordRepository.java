
package acme.features.worker.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerAuditRecordRepository extends AbstractRepository {

	@Query("select b from AuditRecord b where b.id = ?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select b from AuditRecord b where b.job.id = ?1 and b.status = 1")
	Collection<AuditRecord> findManyByJobId(int SponsorId);

	@Query("select b from AuditRecord b")
	Collection<AuditRecord> findMany();

	@Query("select a.job.id from AuditRecord a where a.id = ?1")
	int findJobIdByAuditorRecordId(int id);

	@Query("select count(a) from Application a where a.job.id = ?1 and a.worker.id=?2")
	int findNumberApplicationsByJobId(int id1, int id2);
}
