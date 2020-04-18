package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.IOUtils;

public class Email_Sending {

    // sending palne text which will show in email view
    static void sendHTIMLPlaneTextEmial(Session session) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajibhossan.ibcs@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rajibhossan@yahoo.com"));
            message.setSubject("Testing Plane Text Sending");

            message.setText("Dear Mail Crawler,\n\n No spam to my email, please!");
            Transport transport = session.getTransport("smtp");
            transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //one time send same message to multiple user
    static void sendMultipleUserPlaneTextEmial(Session session) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajibhossan.ibcs@gmail.com"));

            InternetAddress[] recipientAddress = {
                new InternetAddress("rajibhossan@hotmail.com"),
                new InternetAddress("rajibhossan@yahoo.com"),
                new InternetAddress("rajibhossan@gmail.com")
            };

            message.setRecipients(Message.RecipientType.TO, recipientAddress);
            message.setSubject("Testing Plane Text Sending");

            message.setText("Dear Rajib ,\n\n No spam to my email, please!");
            Transport transport = session.getTransport("smtp");
            transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //send html mail to user
    static void sendHTIMLTextEmial(Session session) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajibhossan.ibcs@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rajibhossan@yahoo.com"));
            message.setSubject("Testing HTML text Sending");
            message.setContent("<h1>This is actual message embedded in HTML tags</h1>", "text/html");

            Transport transport = session.getTransport("smtp");
            transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // send html page email to user
    static void sendHTIMLFileEmial(Session session) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajibhossan.ibcs@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rajibhossan@yahoo.com"));
            message.setSubject("Testing HTML file Sendings");
            StringWriter writer = new StringWriter();
            IOUtils.copy(new FileInputStream(new File("home.html")), writer);

            message.setContent(writer.toString(), "text/html; charset=utf-8");
            Transport transport = session.getTransport("smtp");
            transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // send attachment file email
    static void sendAttatchFileEmail(Session session) {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajibhossan.ibcs@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("rajibhossan@yahoo.com"));
            message.setSubject("Testing Attatchment Emails Sending");

            MimeMultipart multipart = new MimeMultipart("related");

            //Sending HTML Text
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<font color='red'><h1>Hi is actual message embedded in HTML tags</color></h1></font>", "text/html");
            multipart.addBodyPart(messageBodyPart);

            //Sending File
            String filename = "sun-cert-scdjws.pdf";
            DataSource source = new FileDataSource(filename);

            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            System.out.println(mex.getMessage());
        }
    }

    //send image attatchment file 
    static void sendAttatchFileWithImageEmail(Session session) {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajibhossan.ibcs@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("rajibhossan@yahoo.com"));
            message.setSubject("Testing Attatchment Emails Sending");

            MimeMultipart multipart = new MimeMultipart("related");

            //Sending HTML Text
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<font color='red'><h1>Hi is actual message embedded in HTML tags</color></h1></font>", "text/html");
            multipart.addBodyPart(messageBodyPart);

            //Sending Image 
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<img src=\"cid:image\">", "text/html");
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource("images.png");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(messageBodyPart);

            //Sending File
            String filename = "sun-cert-scdjws.pdf";
            DataSource source = new FileDataSource(filename);

            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            System.out.println(mex.getMessage());
        }
    }

    // send image file
    static void sendImageFileEmail(Session session) {
        try {

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajibhossan.ibcs@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("rajibhossan@yahoo.com"));
            message.setSubject("Testing Image sending ");

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>I am sending Image file</H1><img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource("images.png");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        Session session = PropertyValues.session;
//        sendMultipleUserPlaneTextEmial(session);
//        sendImageFileEmail(session);
//        sendHTIMLFileEmial(session);
//        sendAttatchFileEmail(session);
//        sendHTIMLPlaneTextEmial(session);
//        sendHTIMLTextEmial(session);

    }

}
