
package acme.features.worker.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerDutyShowService implements AbstractShowService<Worker, Duty> {

	@Autowired
	private WorkerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		int dutyId = request.getModel().getInteger("id");
		Integer jobId = this.repository.findJobIdByDutyId(dutyId);
		assert jobId != null;
		Principal principal;

		principal = request.getPrincipal();

		return this.repository.findNumberApplicationsByJobId(jobId, principal.getActiveRoleId()) > 0; //Miramos si el worker en cuestion ha hecho alguna aplicacion al job correspondiente
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentage");

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);

		return result;
	}

}
