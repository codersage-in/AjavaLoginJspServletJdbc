package in.codersage;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	public static void main(String[] args) {

		send("parthshah.ce@gmail.com", "parthshah.ce@gmail.com", null, null);
	}

	public static boolean send(String email, String token, String contextPath, String path) {
		String host = "smtp.gmail.com";
		final String user = "codersage.in@gmail.com";// change accordingly
		final String password = "Parth@4380";// change accordingly

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.protocol", "smtps");
		props.put("mail.smtp.ssl.enable", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Activate your account");
			message.setText("Click on the link to activate your account : http://localhost:8080/" + contextPath + "/"
					+ path + "?email=" + email + "&token=" + token);

			// send the message
			Transport.send(message);

			System.out.println("message sent successfully...");
			return true;

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
