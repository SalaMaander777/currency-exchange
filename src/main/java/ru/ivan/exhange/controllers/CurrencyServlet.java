package ru.ivan.exhange.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.ivan.exhange.dto.CurrencyDto;
import ru.ivan.exhange.dto.ErrorDto;
import ru.ivan.exhange.entities.CurrencyEntity;
import ru.ivan.exhange.repository.currency.CurrencyRepository;
import ru.ivan.exhange.service.CurrencyService;

import java.io.IOException;

@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {
    private final CurrencyService currencyService;
    private final ObjectMapper objectMapper ;

    public CurrencyServlet(

    ) {
        this.currencyService = new CurrencyService();
        this.objectMapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        response.setContentType("application/json");
        if (code == null){
            ErrorDto error = new ErrorDto("Параметр code обязателен в запросе", 400);

            response.setStatus(error.code());
            objectMapper.writeValue(response.getWriter(), error);
        }
        CurrencyDto currency = currencyService.findByCode(code);
        if (currency == null){
            ErrorDto error = new ErrorDto("Unknown currency with code = " + code, 404);

            response.setStatus(error.code());
            objectMapper.writeValue(response.getWriter(), error);
        }
        response.setStatus(200);
        objectMapper.writeValue(response.getWriter(), currency);



    }


}
