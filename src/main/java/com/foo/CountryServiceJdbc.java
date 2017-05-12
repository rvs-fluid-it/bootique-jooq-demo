package com.foo;

import com.google.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountryServiceJdbc implements CountryService {
    private final DataSource dataSource;

    @Inject
    public CountryServiceJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Country get(String code) {
        Country result = null;
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT CODE, NAME FROM COUNTRY");
            if (rs.next()) {
                result = new Country(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs !=  null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
