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
import ru.ivan.exhange.exceptions.DatabaseConnectionException;
import ru.ivan.exhange.exceptions.EntityIsPresentException;
import ru.ivan.exhange.service.CurrencyService;

import java.io.IOException;
import java.util.List;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {

    private final CurrencyService currencyService;
    private final ObjectMapper objectMapper;

    public CurrenciesServlet() {
        this.currencyService = new CurrencyService();
        this.objectMapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<CurrencyDto> currencies = null;
        try {
            currencies = currencyService.findAll();
        }
        catch (DatabaseConnectionException e){
            ErrorDto errorDto = new ErrorDto(e.getMessage(), 500);
            response.setStatus(errorDto.code());
            objectMapper.writeValue(response.getOutputStream(), errorDto);
        }
        response.setStatus(200);
        objectMapper.writeValue(response.getWriter(), currencies);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String sign = request.getParameter("sign");
            if (name == null || name.isEmpty() || code == null || code.isEmpty() || sign == null || sign.isEmpty()) {
                ErrorDto errorDto = new ErrorDto("Отсутствует нужное поле формы", 400);
                response.setStatus(errorDto.code());
                objectMapper.writeValue(response.getWriter(), errorDto);
            }
            CurrencyDto currencyToAdd = new CurrencyDto(name, code, sign);
            CurrencyDto returned = currencyService.save(currencyToAdd);
            if (returned == null) {
                ErrorDto errorDto = new ErrorDto("Currencies could not be saved", 400);
                response.setStatus(errorDto.code());
                objectMapper.writeValue(response.getWriter(), errorDto);
            }
            response.setStatus(200);
            objectMapper.writeValue(response.getWriter(), returned);
        }
        catch (EntityIsPresentException e) {
            ErrorDto errorDto = new ErrorDto(e.getMessage(), 409);
            response.setStatus(errorDto.code());
            objectMapper.writeValue(response.getWriter(), errorDto);
        }

    }
}
