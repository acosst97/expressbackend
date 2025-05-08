package com.express.imp;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
@Service
public class EnviarEmailImp {
    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(String from, String to, String subject, String body) {
        SimpleMailMessage mimensaje = new SimpleMailMessage();
        mimensaje.setFrom(from);
        mimensaje.setTo(to);
        mimensaje.setSubject(subject);
        mimensaje.setText(body);
        javaMailSender.send(mimensaje);
    }


}
