
package acme.features.authenticated.threads;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Threads;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedThreadsListService implements AbstractListService<Authenticated, Threads> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedThreadsRepository repository;


	@Override
	public boolean authorise(final Request<Threads> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Threads> findMany(final Request<Threads> request) {
		assert request != null;

		Collection<Threads> result;
		Principal principal;
		principal = request.getPrincipal();
		result = this.repository.findManyByAuthenticatedId(principal.getActiveRoleId());
		return result;
	}

	@Override
	public void unbind(final Request<Threads> request, final Threads entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");

	}

}
