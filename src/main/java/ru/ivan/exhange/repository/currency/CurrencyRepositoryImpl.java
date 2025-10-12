package ru.ivan.exhange.repository.currency;

import ru.ivan.exhange.entities.CurrencyEntity;
import ru.ivan.exhange.repository.orm.AbstractJDBCRepository;
import ru.ivan.exhange.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyRepositoryImpl extends AbstractJDBCRepository<CurrencyEntity>
        implements CurrencyRepository {
    @Override
    protected String getTableName() {
        return "currency";
    }

    @Override
    protected CurrencyEntity mapResultSet(ResultSet rs) throws SQLException {
        return new CurrencyEntity(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
        );
    }

    @Override
    public CurrencyEntity save(CurrencyEntity entity) {
       String SQLQuery = """
               insert into currency(name, code, sign) values(?,?,?)""";
       try(Connection connection = DbUtil.connect(); PreparedStatement statement = connection.prepareStatement(SQLQuery)){
           statement.setString(1,entity.getName());
           statement.setString(2,entity.getCode());
           statement.setString(3,entity.getSign());
           statement.executeUpdate();

           ResultSet rs = statement.getGeneratedKeys();
           if(rs.next()){
               Long id = rs.getLong(1);

               return new CurrencyEntity(id, entity.getName(), entity.getCode(), entity.getSign());
           }
       }catch (SQLException e){
           throw new RuntimeException(e);
       }return null;
    }



    @Override
    public CurrencyEntity update(CurrencyEntity entity) {
        return null;
    }


    @Override
    public CurrencyEntity getByCode(String code) {
        String SQLQuery = """
               select * from currency where code = ?;""";
        try(Connection connection = DbUtil.connect(); PreparedStatement statement = connection.prepareStatement(SQLQuery)){
            statement.setString(1,code);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return mapResultSet(rs);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }return null;

    }
}
