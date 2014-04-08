/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.EJB;

import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author User
 */
@Stateless(name = "ejbs/EmailServiceEJB")
public class MailEJB {

    private final String email = "xxxxxxx@gmail.com";
    private final String password = "xxxxxxx";

    public void sendAccountActivationLinkToBuyer(String destinationEmail,
            String name) {
        // OUR EMAIL SETTINGS
        String host = "smtp.gmail.com";// Gmail
        int port = 465;
        String serviceUsername = "xxxxxxx@gmail.com";
        String servicePassword = "xxxxxxx";// Our Gmail password

        Properties props = System.getProperties();
        props.put("mail.smtp.user", serviceUsername);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        // Destination of the email
        String to = destinationEmail;
        String from = "xxxxxxx@gmail.com";

        // Creating a javax.Session with the our properties
        Session session;
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            // From: is our service
            message.setFrom(new InternetAddress(from));
            // To: destination given
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Comfirm your account");
            // Instead of simple text, a .html template should be added here!
            message.setText("Welcome....... ");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, port, serviceUsername, servicePassword);
            Transport.send(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
