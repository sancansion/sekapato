package com.sekapato.logic;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sekapato.config.PropertyConfig;
import com.sekapato.entity.MailForm;

public class MailSender {

    private final static Logger logger = LoggerFactory.getLogger(MailSender.class);

    private static String USER_NAME;
    private static String PASSWORD;
    private static String ADDRESS;
    private static String SUBJECT;
    private static String line = System.getProperty("line.separator");

    PropertyConfig config = PropertyConfig.getInstance();

    public MailSender() throws ConfigurationException {
        USER_NAME = config.getString("mail.user.name");
        PASSWORD = config.getString("mail.password");
        ADDRESS = config.getString("mail.address");
        SUBJECT = config.getString("mail.subject");
    }

    public void send(MailForm form) {
        logger.info("MailForm : " + form.toString());
        sendToGMail(USER_NAME, PASSWORD, new String[] { ADDRESS }, SUBJECT, makeMessage(form));
    }

    // TODO: set in constructor
    protected String makeMessage(MailForm form) {
        StringBuilder sb = new StringBuilder();
        sb.append("【お名前】").append(line).append(form.name).append("【メールアドレス】").append(form.mailAddress).append("【メールアドレス(確認用)】").append(form.mailAddressConfirm).append("【お問い合わせ内容】").append(form.message);
        logger.info("make massage : " + sb.toString());

        return sb.toString();
    }

    private static void sendToGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        logger.info("from" + from);
        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ADDRESS, PASSWORD);
            }
        });
        session.setDebug( true );  // 
        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject, "UTF-8");
            message.setText(body, "UTF-8");

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException ae) {
            logger.error("AddressException" + ae);
            ae.printStackTrace();
        } catch (MessagingException me) {
            logger.error("MessagingException" + me);
            me.printStackTrace();
        }
    }
}