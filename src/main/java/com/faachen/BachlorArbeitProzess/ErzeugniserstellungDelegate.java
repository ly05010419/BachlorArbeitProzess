package com.faachen.BachlorArbeitProzess;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ErzeugniserstellungDelegate implements JavaDelegate {

	

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println("Erzeugnis ist erstellt");
	}

	

}
