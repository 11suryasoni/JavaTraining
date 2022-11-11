import OOPS.DifferentCharts;
import org.apache.log4j.Logger;

import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
    static final Logger logg = Logger.getLogger(DifferentCharts.class);

    public static void main(String[] args) {

        logg.info("Getting the session object");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        logg.debug("\"mail.smtp.host\", \"smtp.gmail.com\"");
        props.put("mail.smtp.port", "587");
        logg.debug("\"mail.smtp.port\", \"587\"");
        props.put("mail.smtp.auth", "true");
        logg.debug("\"mail.smtp.auth\", \"true\"");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        logg.debug("\"mail.smtp.ssl.protocols\", \"TLSv1.2\"");
        props.put("mail.smtp.starttls.enable", "true");
        logg.debug("\"mail.smtp.starttls.enable\", \"true\"");

        Session session = Session.getInstance(props);
        logg.debug("Session - " + session);
        logg.info("Compose Mail");

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("suryasonirewa@gmail.com"));
            logg.debug("From: " + message);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("krupa@codeops.tech"));
            logg.debug("To: " + message);


            message.setSubject("CHART'S & CONVERTER CODE-REPORTS: System Generated Mail");
            logg.debug("Message Subject: " + message);

            logg.info("Message Body");
            String file = "/Users/azuga/Desktop/untitled folderDirectory1/my.zip";
            logg.debug("File: " + file);
            String fileName = "Charts_Converters.zip";
            logg.debug("File Name: " + fileName);


            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This Message Contains Reports Zip File for Charts & Converter Programs." + '\n' +
                    "This is a System Generated Mail. Do not reply." + '\n' + "Thanks");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            logg.info("Sending Mail");
            System.out.println("Sending");

            Transport.send(message, username, password);
            logg.info("Mail Sent");
            System.out.println("Done");

        } catch (MessagingException e) {
            logg.error("Sending Failed, Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}