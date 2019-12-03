
package acme.features.worker.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerAuditRecordShowService implements AbstractShowService<Worker, AuditRecord> {

	@Autowired
	WorkerAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		int AuditRecordId = request.getModel().getInteger("id");
		int jobId = this.repository.findJobIdByAuditorRecordId(AuditRecordId);
		Principal principal = request.getPrincipal();

		return this.repository.findNumberApplicationsByJobId(jobId, principal.getActiveRoleId()) > 0; //el worker debe haber hecho al menos una application al trabajo en cuestion para ver sus audit records
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "status", "body");

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		int AuditRecordId;
		AuditRecord result;
		AuditRecordId = request.getModel().getInteger("id");
		result = this.repository.findOneAuditRecordById(AuditRecordId);

		return result;
	}

}
