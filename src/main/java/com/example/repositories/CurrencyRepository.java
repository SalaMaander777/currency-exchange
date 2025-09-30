package com.example.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.models.Currency;

import com.example.util.DbUtil;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class CurrencyRepository {

    private final Connection connection;



    public Optional<List<Currency>> getCurrencies() {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM currency")) {
            ResultSet resultSet = statement.executeQuery();
            List<Currency> currencies = new ArrayList<>();
            while(resultSet.next()){
                long id =  resultSet.getLong(0);
                String code = resultSet.getString(1);
                String fullName = resultSet.getString(2);
                String sign = resultSet.getString(3);
                Currency currency = new Currency(id, code, fullName, sign);
                currencies.add(currency);
            }
            return Optional.of(currencies);
            
        }
                       
        catch (Exception e) {
            return Optional.empty();
          
        }
    }

    public Optional<Currency> getCurrencyByCode(String code) throws Exception{

     try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM currency WHERE code = ?")) {
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            
           
            if (resultSet.next()){
                long id =  resultSet.getLong(0);
                String code_ = resultSet.getString(1);
                String fullName = resultSet.getString(2);
                String sign = resultSet.getString(3);
                Currency currency = new Currency(id, code_, fullName, sign);
                return Optional.of(currency);
            }
        }
                       
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
     return null;
    }
}
