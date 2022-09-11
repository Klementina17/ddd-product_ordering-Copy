package mk.ukim.finki.emt.emailsendermessage;

import mk.ukim.finki.emt.emailsendermessage.EmailDetails;

// Interface
public interface EmailService {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}