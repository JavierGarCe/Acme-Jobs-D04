
package acme.features.authenticated.threads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;
		int idThread = request.getModel().getInteger("id");
		Thread thread = this.repository.findOneById(idThread);
		List<Authenticated> authenticateds = (List<Authenticated>) thread.getAuthenticateds();
		Principal principal = request.getPrincipal();
		boolean result = authenticateds.stream().filter(x -> x.getUserAccount().getId() == principal.getAccountId()).count() > 0;
		return result;
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
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
	public Thread findOne(final Request<Thread> request) {

		assert request != null;

		Thread result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		result.getMessages().size();
		return result;
	}

}
