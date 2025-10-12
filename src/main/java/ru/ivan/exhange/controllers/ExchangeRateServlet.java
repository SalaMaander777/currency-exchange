package ru.ivan.exhange.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ru.ivan.exhange.dto.ExchangeRateDto;
import ru.ivan.exhange.service.ExchangeRateService;

import java.io.IOException;
@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {
    private final ExchangeRateService exchangeRateService;
    private final ObjectMapper objectMapper;

    public ExchangeRateServlet(){
        this.exchangeRateService = new ExchangeRateService();
        this.objectMapper = new ObjectMapper();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String pair = request.getPathInfo().substring(1);
            String code1 = pair.substring(0, 3);
            String code2 = pair.substring(3);



    }
}
