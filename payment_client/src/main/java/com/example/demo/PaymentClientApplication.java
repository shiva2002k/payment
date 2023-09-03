package com.example.demo;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
@EnableEurekaClient
public class PaymentClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentClientApplication.class, args);
	}

	 // Configure JavaMailSender bean
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Set your SMTP server host
        mailSender.setPort(587); // Set your SMTP server port
        mailSender.setUsername("merlinm.19it@kongu.edu"); // Set your email address
        mailSender.setPassword("dkppfgjbpcsaksuq"); // Set your email password

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com"); // Set your SMTP server here

        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }
}
