
package acme.features.authenticated.threads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.entities.threads.Threads;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadsShowService implements AbstractShowService<Authenticated, Threads> {

	@Autowired
	AuthenticatedThreadsRepository repository;


	@Override
	public boolean authorise(final Request<Threads> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Threads> request, final Threads entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "messages");

		String title, tags, body;
		Date moment;
		Integer id;
		List<Message> messages = new ArrayList<>(entity.getMessages());
		for (int i = 0; i < entity.getMessages().size(); i++) {
			title = messages.get(i).getTitle();
			tags = messages.get(i).getTags();
			body = messages.get(i).getBody();
			moment = messages.get(i).getMoment();
			id = messages.get(i).getId();
			model.setAttribute("title[" + i + "]", title);
			model.setAttribute("tags[" + i + "]", tags);
			model.setAttribute("body[" + i + "]", body);
			model.setAttribute("moment[" + i + "]", moment);
			model.setAttribute("id[" + i + "]", id);
		}

	}

	@Override
	public Threads findOne(final Request<Threads> request) {

		assert request != null;

		Threads result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		result.getMessages().size();
		return result;
	}

}
