package com.example.coursePrj.controllers;

import com.example.coursePrj.services.CsvParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Controller
public class CsvParserController {
    @Autowired
    CsvParserService parserService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @Transactional
    @PostMapping("/upload")
    public String uploadCsvFile(@RequestParam("file") MultipartFile[] files, Model model) throws IOException {
        model.addAttribute("title", "Статус загрузки");

        for (MultipartFile file : files) {
            if (file.isEmpty()){
                model.addAttribute("message", "Файл не был выбран");
                model.addAttribute("status", false);
            }

            else {
                try {
                    parserService.upload(file);
                }

                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return "redirect:/functions";
    }

    @PostMapping("/functions")
    public String showFunctions(Model model) {
        model.addAttribute("title", "Функции");
        return "functions";
    }

    @PostMapping("/backToHome")
    public String backToHome(Model model) {
        return "redirect:/";
    }
}
