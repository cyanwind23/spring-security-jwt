package xyz.thiennam.employeems.service.bean;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import xyz.thiennam.employeems.entity.NotificationEmail;
import xyz.thiennam.employeems.exception.EmsException;
import xyz.thiennam.employeems.service.MailService;

@Service
@AllArgsConstructor
@Slf4j
public class MailServiceBean implements MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    private String buildContent(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }

    @Override
    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("ems.thiennam23@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(this.buildContent(notificationEmail.getBody()));
        };

        try {
            mailSender.send(messagePreparator);
            log.info("Activation mail sent!");
        } catch (MailException e) {
            throw new EmsException("Exception occurred while sending email to " + notificationEmail.getRecipient());
        }

    }
}
