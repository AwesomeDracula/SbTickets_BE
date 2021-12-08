package com.example.sbtickets.service;

import com.example.sbtickets.entity.Customer;
import com.example.sbtickets.entity.TripBus;
import com.example.sbtickets.service.impl.SendToEmailImplement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SendToEmailService implements SendToEmailImplement {

    private static final Logger logger = Logger.getLogger(SendToEmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendToEmail(int roleCar, TripBus tripBus, Customer customer) {
        try {
            String from = "sbtickets2021@gmail.com";
            String to = customer.getEmail();

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper setMess = new MimeMessageHelper(message);

            setMess.setSubject("Booked in Sbtickets");
            setMess.setFrom(from);
            setMess.setTo(to);

            setMess.setText("<b>Dear " + customer.getFullName() +  "</b>,<br><i>Thanks you. You have successfully booked your ticket</i><br>" + "Seats: " +
                    roleCar + "<br> TripCode: " + tripBus.getId() + "<br> TimeTrip: " +  tripBus.getTimeTrip() + "<br> CarNumber: " + tripBus.getBus().getCarNumber() + " - Color: " + tripBus.getBus().getColor() + "<br> Please arrive 30 minutes in advance. Thanks" +
                    "", true);

            javaMailSender.send(message);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }
}
