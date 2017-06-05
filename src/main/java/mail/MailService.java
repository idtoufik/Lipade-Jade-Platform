package mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import config.Config;

public class MailService {
	
	private Properties mailServerProperties;
	Session session;
	public enum User{
		Nao, turtlebot
	}

	public MailService() {
		Config config = new Config();
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", config.getProperty("mail.smtp.port"));
		mailServerProperties.put("mail.smtp.auth", config.getProperty("mail.smtp.auth"));
		mailServerProperties.put("mail.smtp.starttls.enable", config.getProperty("mail.smtp.starttls.enable"));
		System.out.println("Mail Server Properties have been setup successfully..");
		
		session = Session.getDefaultInstance(mailServerProperties, null);

		
	}

	public boolean sendMail(MimeMessage message){
 
		Config config = new Config();
		
		Transport transport;
		try {
			transport = session.getTransport("smtp");
			transport.connect(config.getProperty("mail.smtp.host"), config.getProperty("mail.smtp.user"), config.getProperty("mail.smtp.password"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Session getSession() {
		return session;
	}

	public static void main(String args[]) throws AddressException, MessagingException {
		MailService mailService = new MailService();
		
		MimeMessage message = new MimeMessage(mailService.getSession());
		message.addRecipient(Message.RecipientType.TO, new InternetAddress("id.toufik1@gmail.com"));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress("id.toufik@hotmail.com"));
		message.setSubject("test de toufik");
		String emailBody = "mail test message" + "<br><br> Regards, <br>ider toufik";
		message.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
		
		mailService.sendMail(message);
		System.out.println("Java Program has just sent an Email successfully. Check your email..");
	}
	
	
	
	
	
	
	
	
	
	
	
}
