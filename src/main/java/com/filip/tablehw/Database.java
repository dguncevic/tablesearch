package com.filip.tablehw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {

    static String url = "jdbc:postgresql://localhost:5432/Programeri";
    static Properties props = new Properties();

    static {

        props.setProperty("user", "postgres");
        props.setProperty("password", "programeri");
        props.setProperty("ssl", "false");
    }

    public static ObservableList<Employee> getEmployees() {

        ObservableList<Employee> employees = FXCollections.observableArrayList();

        try {
            Connection con = DriverManager.getConnection(url, props);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from zaposleni");
            while (rs.next()) {

                Employee temp = new Employee();
                temp.setId(rs.getInt("id"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setEmail(rs.getString("email"));
                temp.setDateEntered(rs.getTimestamp("dateEntered"));

                City tcity = new City();
                tcity.setId(rs.getInt("city"));
                tcity.setName(rs.getString("cityname"));
                tcity.setZip(rs.getString("zip"));
                tcity.setCityState(rs.getString("cityState"));

                temp.setCity(tcity);

                Status tstatus = new Status();
                tstatus.setId(rs.getInt("statusid"));
                tstatus.setStatus(rs.getString("status"));

                temp.setStatus(tstatus);

                Role trole = new Role();
                trole.setId(rs.getInt("roleid"));
                trole.setLevel(rs.getInt("level"));
                trole.setRoleName(rs.getString("rolename"));

                temp.setRole(trole);
                System.out.println(temp);
                employees.add(temp);
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {

        }

        return employees;

    }

    public static ObservableList<City> getCities() {
        ObservableList<City> cities = FXCollections.observableArrayList();

        try {
            Connection con = DriverManager.getConnection(url, props);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cities");
            while (rs.next()) {
                City temp = new City();
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("cityName"));
                temp.setCityState(rs.getString("cityState"));
                temp.setZip(rs.getString("zip"));
                cities.add(temp);

            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {

        }

        return cities;
    }

    public static boolean executeQuery(String query) {
        boolean result = true;

        try {
            Connection con = DriverManager.getConnection(url, props);
            Statement st = con.createStatement();
            result = st.execute(query);
        } catch (Exception e) {

        }
        return !result;
    }
}
