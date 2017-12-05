package com.faachen.BachlorArbeitProzess;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ThemaApprovedDelegate implements JavaDelegate {

	Client client = ClientBuilder.newClient();

	public static String path = "http://localhost:9080/gpmserver2/api/thesisService/thesis";

	@Override
	public void execute(DelegateExecution execution) throws Exception {

	
		String studentId = execution.getVariable("studentId").toString();
		
		String approved = execution.getVariable("approved").toString();
		
		updateThesis(studentId, approved);
	}

	public void updateThesis(String studentId, String approved) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(path);

		Form form = new Form();
		form.param("studentId", studentId);
		form.param("approved", approved);

		webTarget.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Thesis.class);

		System.out.println("------------------------updateThesis---------------------------");
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
	
	public static void main(String[] args) {
		
		ThemaApprovedDelegate d = new ThemaApprovedDelegate();
		
		String studentId = "1";
		
		String approved = "1";
		
		d.updateThesis(studentId, approved);
		
		
	}

}
