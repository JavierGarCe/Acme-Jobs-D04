
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
