
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

		request.unbind(entity, model, "title", "salary", "deadline", "reference", "status", "descriptor.description");

		StringBuilder buffer;
		List<Duty> duties;
		duties = new ArrayList<>(entity.getDescriptor().getDuties());

		buffer = new StringBuilder();
		String lang = request.getLocale().getDisplayLanguage();

		for (Duty duty : duties) {
			if (lang.equals("English")) {
				buffer.append("Title: ");
				buffer.append(duty.getTitle());
				buffer.append(", description: ");
				buffer.append(duty.getDescription());
				buffer.append(", percentage: ");
				buffer.append(duty.getPercentage());
				buffer.append("\n");
			} else {
				buffer.append("Título: ");
				buffer.append(duty.getTitle());
				buffer.append(", descripción: ");
				buffer.append(duty.getDescription());
				buffer.append(", porcentaje: ");
				buffer.append(duty.getPercentage());
				buffer.append("\n");
			}
		}

		model.setAttribute("duties", buffer.toString());

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
