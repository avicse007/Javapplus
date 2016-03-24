package com.snapdeal.ite4helthcheck.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author singh.avinash
 *
 */
public class SendEmails {

	private static final String userName = "webautomationn@gmail.com";
	private static final String password = "QA@Testing123";

	/**
	 * @param string
	 * @param failingAPIsDetails
	 * 
	 */
	public static void sendEmail(String recipient) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject("ITE4-Master HealthCheck Report | " + new Date());

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart.setText("Hi Team,"
					+ "\n\nPlease find the attached Report for current status of backend systems on ITE4-Master. \n\n");
			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = null;

			filename = System.getProperty("user.dir") + "/SystemsHealthCheckReport.html";

			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			// filename
			messageBodyPart.setFileName("SystemsHealthCheckReport.html");
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);
			String mess = "";
			try {
				mess = FileUtils.readFileToString(new File(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}

			messageBodyPart1.setContent(
					"<br><b><u><font size=\"3\">Purpose</font></u>-:</u></b><i><font size=\"2\"> Checks whether all backend system are up and responding. This job runs on ITE4-Master twice a day on weekdays only.</i></font>.</br><br><br>"
							+ mess,
					"text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart1);

			Transport.send(message);

			System.out.println("Message Sending Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

