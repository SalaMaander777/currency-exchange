package ru.ivan.exhange.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.ivan.exhange.entities.CurrencyEntity;
import ru.ivan.exhange.repository.currency.CurrencyRepository;
import ru.ivan.exhange.service.CurrencyService;

import java.io.IOException;

@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {
    private  CurrencyService currencyService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if(code == null){

        }
        CurrencyEntity currency = currencyService.findByCode(code);



    }


}
