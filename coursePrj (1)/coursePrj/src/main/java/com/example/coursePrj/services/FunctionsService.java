package com.example.coursePrj.services;

import com.example.coursePrj.models.Transactions;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionsService {
    public void divideColumn(@NonNull Connection connection) throws SQLException {
        String query = "alter table transactions add tr_date varchar(255); update transactions as tr1 set tr_date = (select split_part(tr_datetime, ' ', 1) from transactions as tr2 where tr1.customer_id = tr2.customer_id);";

        try(Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String filename = "C:\\Users\\marpo\\OneDrive\\Рабочий стол\\transactions_updated.csv";
        File csvFile = new File(filename);

        String _query = "select * from transactions;";
        try(Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(_query);

            try (PrintWriter printWriter = new PrintWriter(csvFile)) {
                printWriter.println("customer_id;amount;mcc_code;term_id;tr_datetime;tr_type;tr_date");

                while (result.next()) {
                    printWriter.println(
                            result.getLong("customer_id") + ";" +
                            result.getInt("amount") + ";" +
                            result.getInt("mcc_code") + ";" +
                            result.getInt("term_id") + ";" +
                            result.getString("tr_datetime") + ";" +
                            result.getInt("tr_type") + ";" +
                            result.getString("tr_date")
                    );
                }

                printWriter.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Integer> getAvgAmount(@NonNull Connection connection) {
        String query = "select * from (select avg(amount) as am from transactions group by mcc_code having count(mcc_code) > 1) as subq;";
        List<Integer> list = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                list.add(result.getInt("am"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String filename = "C:\\Users\\marpo\\OneDrive\\Рабочий стол\\avg_amount.csv";
        File csvFile = new File(filename);

        try (PrintWriter printWriter = new PrintWriter(csvFile)) {
            printWriter.println("avg_amount");

            for (int item: list) {
                printWriter.println(item);
            }

            printWriter.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
}
