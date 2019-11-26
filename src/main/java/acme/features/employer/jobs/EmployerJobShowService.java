
package acme.features.employer.jobs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerJobShowService implements AbstractShowService<Employer, Job> {

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "salary", "deadline", "reference", "status", "descriptor", "descriptor.description", "descriptor.duties");

		switch (entity.getStatus()) {
		case DRAFT:
			if (request.getLocale().getDisplayLanguage() == "English") {
				model.setAttribute("status", "Draft");
			} else {
				model.setAttribute("status", "Borrador");
			}
			break;
		default:
			if (request.getLocale().getDisplayLanguage() == "English") {
				model.setAttribute("status", "Published");
			} else {
				model.setAttribute("status", "Publicado");
			}
			break;
		}

		List<Duty> duties;
		duties = new ArrayList<>(entity.getDescriptor().getDuties());
		for (int i = 0; i < duties.size(); i++) {
			model.setAttribute("duty" + i + ".title", duties.get(i).getTitle());
			model.setAttribute("duty" + i + ".description", duties.get(i).getDescription());
			model.setAttribute("duty" + i + ".percentage", duties.get(i).getPercentage());
		}

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneById(id);

		res.getDescriptor().getDuties().size(); //desenrrollar

		//res.getDescriptor().setDuties(this.repository.findDuties(id));

		return res;
	}
}
