package ru.shopper.service;

import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;

@Service
public class AppService {
    public String[] getMonth() {
        return new DateFormatSymbols().getMonths();
    }
}
