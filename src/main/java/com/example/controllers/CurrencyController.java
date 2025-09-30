package com.example.controllers;

import java.io.IOException;
import java.util.List;

import com.example.DAO.CurrencyDAO;
import com.example.DTO.CurrencyDTO;
import com.example.services.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/currency")
public class CurrencyController extends HttpServlet{
    private CurrencyService currencyService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CurrencyDTO> currencies = currencyService.getAllCurrencies();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), currencies);
    }

}
