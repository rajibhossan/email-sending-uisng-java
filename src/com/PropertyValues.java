package com;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
public class PropertyValues {
    
    static public Properties getProperty() {
        Properties props = new Properties();
        props.put("mail.smtp.user", "username");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.EnableSSL.enable", "true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        
        return props;
    }    
    public static Session session = Session.getInstance(getProperty(), new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(getUserName(), getPassword());
        }
    });    
    
	// password
    static public String getPassword() {
        return "";
    }
	//email address
    static public String getUserName() {
        return "";

    }
}
