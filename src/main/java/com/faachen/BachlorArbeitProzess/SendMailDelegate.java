package com.faachen.BachlorArbeitProzess;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMailDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String fromEmail = "temp.yongli@gmail.com";
		String password = "temp123456789";
		String toEmail = (String) execution.getVariable("toMail");
		String name = (String) execution.getVariable("name");//"NoReply-BachlorarbeitProzess"
		String title = (String) execution.getVariable("title");//"BachlorArbeitProzess"
		String body = (String) execution.getVariable("body");//"Das ist ein Email von BachlorArbeitProzess"

		sendEmail(fromEmail, password, toEmail, name, title,body);
	}
	
	

	public void sendEmail(String fromEmail, String password, String toEmail, String fromEmailName, String subject,
			String body) throws Exception {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);

		MimeMessage msg = new MimeMessage(session);
		// set message headers
		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.addHeader("format", "flowed");
		msg.addHeader("Content-Transfer-Encoding", "8bit");

		msg.setFrom(new InternetAddress(fromEmail, fromEmailName));

		msg.setReplyTo(InternetAddress.parse(toEmail, false));

		msg.setSubject(subject, "UTF-8");

		msg.setText(body, "UTF-8");

		msg.setSentDate(new Date());

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		System.out.println("EMail Sent");
		Transport.send(msg);
		System.out.println("EMail Sent Successfully!!");

	}
	
	//	Nur für Test
	public static void main(String[] args) throws Exception {
		
		String fromEmail = "temp.yongli@gmail.com";
		String password = "temp123456789";
		String toEmail = "temp.yongli@gmail.com";
		String name = "NoReply-BachlorarbeitProzess";
		String title = "BachlorArbeitProzess";
		String body = "Das ist ein Email von BachlorArbeitProzess";

		SendMailDelegate d = new SendMailDelegate();
		
		d.sendEmail(fromEmail, password, toEmail, name, title,body);
		
	}

}
