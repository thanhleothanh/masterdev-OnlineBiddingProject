package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.dtos.DataMailDto;
import javax.mail.MessagingException;

public interface MailService {
    void sendMail(DataMailDto dataMail, String templateName) throws MessagingException;
}
