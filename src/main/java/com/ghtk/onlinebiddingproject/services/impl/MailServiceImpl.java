package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.models.dtos.DataMailDto;
import com.ghtk.onlinebiddingproject.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
   @Autowired
   JavaMailSender mailSender;
   @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void sendMail(DataMailDto dataMail, String templateName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");

        Context context = new Context();
        context.setVariables(dataMail.getProps());

        String html = templateEngine.process(templateName,context);

        helper.setTo(dataMail.getTo());
        helper.setSubject(dataMail.getSubject());
        helper.setText(html , true);

        mailSender.send(message);
    }
}
