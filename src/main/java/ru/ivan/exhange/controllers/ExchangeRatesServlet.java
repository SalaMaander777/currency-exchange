package ru.ivan.exhange.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.ivan.exhange.dto.ExchangeRateDto;
import ru.ivan.exhange.dto.ExchangeRateResponse;
import ru.ivan.exhange.entities.CurrencyEntity;
import ru.ivan.exhange.service.ExchangeRateService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {
    private ExchangeRateService exchangeRateService;
    private ObjectMapper objectMapper;

    public ExchangeRatesServlet() {
        exchangeRateService = new ExchangeRateService();
        objectMapper = new ObjectMapper();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        String baseCurrencyCode  =  request.getParameter("baseCurrencyCode");
        String targetCurrencyCode =   request.getParameter("targetCurrencyCode");
        String rate  =  request.getParameter("rate");
        ExchangeRateResponse fromDb = exchangeRateService.save(baseCurrencyCode, targetCurrencyCode, new BigDecimal(rate));

        objectMapper.writeValue(response.getWriter(), fromDb);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<ExchangeRateDto> entities = exchangeRateService.getAll();
        objectMapper.writeValue(resp.getWriter(), entities);
    }
}
