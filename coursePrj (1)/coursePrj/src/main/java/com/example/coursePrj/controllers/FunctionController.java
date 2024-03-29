package com.example.coursePrj.controllers;

import com.example.coursePrj.properties.DBProperties;
import com.example.coursePrj.services.FunctionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FunctionController {
    @GetMapping("/functions")
    public String functions(Model model) {
        model.addAttribute("title", "Функции");
        return "functions";
    }

    @GetMapping("/functions/alterFunction")
    public String alterTable(Model model) throws SQLException {
        model.addAttribute("title", "Функция 1");

        DBProperties properties = DBProperties.getProperties();
        try(Connection connection = DriverManager.getConnection(
                properties.getUrl(),
                properties.getUsername(),
                properties.getPassword()
        )) {
            FunctionsService service = new FunctionsService();
            service.divideColumn(connection);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "alterTableFunction";
    }

    @GetMapping("/functions/getAvgAmountFunction")
    public String getAvgAmount(Model model, @ModelAttribute("List")ArrayList<String> list) throws SQLException {
        model.addAttribute("title", "Функция 2");

        List<Integer> list1 = new ArrayList<>();

        DBProperties properties = DBProperties.getProperties();
        try(Connection connection = DriverManager.getConnection(
                properties.getUrl(),
                properties.getUsername(),
                properties.getPassword()
        )) {
            FunctionsService service = new FunctionsService();
            list1 = (ArrayList<Integer>) service.getAvgAmount(connection);
            for (int item : list1) {
                list.add(Integer.toString(item));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "getAvgAmountFunction";
    }

    @PostMapping("/backToFunctions")
    public String backToFunctions(Model model) {
        return "redirect:/functions";
    }
}
