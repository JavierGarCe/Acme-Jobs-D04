
package acme.features.authenticated.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedJobShowService implements AbstractShowService<Authenticated, Job> {

	@Autowired
	private AuthenticatedJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
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
