package mk.ukim.finki.emt.emailsendermessage;

import mk.ukim.finki.emt.emailsendermessage.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EmailsendermessageApplication {

    @Autowired
    private EmailSenderService senderService;

    public static void main(String[] args) {
        SpringApplication.run(EmailsendermessageApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail()
    {
        senderService.sendEmail("klementinadimitrova239@gmail.com","Your order was successfully created.",
                "Thanks for ordering.");
    }
}
