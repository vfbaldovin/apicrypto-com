package com.web.apicrypto.config;

import com.web.apicrypto.model.MailCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender mailSender() {

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.zoho.eu");
        mailSender.setPort(587);
        mailSender.setUsername(MailCredentials.EMAIL_ADDRESS);
        mailSender.setPassword(MailCredentials.PASSWORD);

        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        return mailSender;

    }
}