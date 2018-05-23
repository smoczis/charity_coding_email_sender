package com.codecool.charity.senders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

@Service
@ConfigurationProperties
@EnableConfigurationProperties
public class SenderServiceImpl implements SenderService {

    private SenderRepository repository;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    public SenderServiceImpl(SenderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Sender> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Sender findOne(Long id) {

        Optional<Sender> sender = repository.findById(id);

        return sender.get();
    }

    @Override
    public void save(Sender sender) {

        if (this.repository.findSenderByHostName(sender.getHostName()) == null) {
            this.repository.save(sender);
        } else {
            throw new IllegalArgumentException();
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
