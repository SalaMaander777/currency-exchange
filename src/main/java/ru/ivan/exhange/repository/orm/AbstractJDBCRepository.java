package ru.ivan.exhange.repository.orm;

import ru.ivan.exhange.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class AbstractJDBCRepository<T> implements CrudRepository<T, Long> {

    abstract protected String getTableName();

    abstract protected T mapResultSet(ResultSet rs) throws SQLException;


    public Optional<T> findById(Long id){
        String SQLQuery = "select * from " + getTableName() + " where id = ?";
        try(Connection connection = DbUtil.connect(); PreparedStatement statement = connection.prepareStatement(SQLQuery)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return Optional.of(mapResultSet(resultSet));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
       return Optional.empty();
    }

    public List<T> findAll(){
        String SQLQuery = "select * from " + getTableName();
        try (Connection connection = DbUtil.connect(); PreparedStatement statement = connection.prepareStatement(SQLQuery)){
            ResultSet resultSet = statement.executeQuery();
            List<T> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(mapResultSet(resultSet));
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public T delete(Long id){
        String SQLQuery = "delete from " + getTableName() + " where id = ?";
        try (Connection connection = DbUtil.connect(); PreparedStatement statement = connection.prepareStatement(SQLQuery)){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return mapResultSet(resultSet);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }





}
