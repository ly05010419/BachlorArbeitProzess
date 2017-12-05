package com.faachen.BachlorArbeitProzess;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ThemaEintragenDelegate implements JavaDelegate {

	Client client = ClientBuilder.newClient();

	public static String path = "http://localhost:9080/gpmserver2/api/thesisService/thesis";

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String studentId = execution.getVariable("studentId").toString();
		String supervisor = execution.getVariable("supervisor").toString();
		String title = execution.getVariable("title").toString();
		String approved = execution.getVariable("approved").toString();

		saveThesis(studentId, approved, supervisor, title);

	}

	public void saveThesis(String studentId, String approved, String supervisor, String title) {

		WebTarget webTarget = client.target(path);

		Form form = new Form();
		form.param("studentId", studentId);
		form.param("approved", approved);
		form.param("supervisor", supervisor);
		form.param("title", title);

		webTarget.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Thesis.class);

		System.out.println("------------------------saveThesis---------------------------");
	}

	public void getAllThesis() {

		List<Thesis> list = client.target(path).request(MediaType.APPLICATION_JSON).get(List.class);
		System.out.println(list);
	}

	public void getThesisById(int id) {
		Thesis thesis = client.target(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON)
				.get(Thesis.class);
		System.out.println(thesis);
	}

	// Nur für Test
	public static void main(String[] args) {

		ThemaEintragenDelegate d = new ThemaEintragenDelegate();

		String studentId = "1";
		String supervisor = "supervisor";
		String title = "title";
		String approved = "1";

		d.saveThesis(studentId, approved, supervisor, title);
	}

}
