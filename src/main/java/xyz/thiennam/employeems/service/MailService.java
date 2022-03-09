package xyz.thiennam.employeems.service;

import org.springframework.scheduling.annotation.Async;
import xyz.thiennam.employeems.entity.NotificationEmail;

public interface MailService {

    @Async
    void sendMail(NotificationEmail notificationEmail);
}
