package com.reservationapp.util;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
@Service

public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    public void sendEmailWithAttachement(String to,String subject,String text,byte[] attachment ,String attachmentName){
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper =new MimeMessageHelper(message ,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            ByteArrayResource dataSource = new ByteArrayResource(attachment);
            helper.addAttachment(attachmentName, dataSource);
            emailSender.send(message);

        }catch(Exception e){

            e.printStackTrace();
        }
    }
}
