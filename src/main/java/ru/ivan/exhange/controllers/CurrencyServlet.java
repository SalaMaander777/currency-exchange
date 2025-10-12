package ru.ivan.exhange.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.ivan.exhange.dto.CurrencyDto;
import ru.ivan.exhange.dto.ErrorDto;

import ru.ivan.exhange.exceptions.CurrencyNotFoundException;

import ru.ivan.exhange.service.CurrencyService;

import java.io.IOException;


@WebServlet("/currency/*")
public class CurrencyServlet extends HttpServlet {
    private final CurrencyService currencyService;
    private final ObjectMapper objectMapper ;

    public CurrencyServlet(

    ) {
        this.currencyService = new CurrencyService();
        this.objectMapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getPathInfo().substring(1);
        System.out.println(code);
        response.setContentType("application/json");
        if (code == null || code.isEmpty()) {
            ErrorDto error = new ErrorDto("Параметр code обязателен в запросе", 400);
            response.setStatus(error.code());
            objectMapper.writeValue(response.getWriter(), error);
        }
        try {
            CurrencyDto currency = currencyService.findByCode(code);
            response.setStatus(200);
            objectMapper.writeValue(response.getWriter(), currency);
        }catch (CurrencyNotFoundException e){
            ErrorDto error = new ErrorDto(e.getMessage(), 404);
            response.setStatus(error.code());
            objectMapper.writeValue(response.getWriter(),error);
        }


    }


}
