package xyz.thiennam.employeems.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ems_NotificationEmail")
@Table(name = "app_notification_email")
public class NotificationEmail extends StandardEntity {
    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "body")
    private String body;
}