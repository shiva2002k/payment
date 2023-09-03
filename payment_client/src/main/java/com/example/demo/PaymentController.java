package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/makePayment")
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.makePayment(payment);

        // Send a simple email receipt
        sendEmailReceipt(savedPayment);

        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    private void sendEmailReceipt(Payment payment) {
        // Create a simple email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("recipient@example.com"); // Replace with the recipient's email address
        message.setSubject("Payment Receipt");
        message.setText("Order ID: " + payment.getOrderId() +
                "\nAmount: $" + payment.getAmount() +
                "\nPayment Status: " + payment.getPaymentStatus() +
                "\nThank you for your payment!");

        // Send the email
        javaMailSender.send(message);
    }
}
