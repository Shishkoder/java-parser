package org.example;

import org.example.entities.Employee;
import org.example.entities.Individual;
import org.example.ExcelParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\eldar\\Desktop\\parser\\src\\java-test.xlsx";  // Укажите путь к вашему файлу

        ExcelParser parser = new ExcelParser(filePath);

        try {
            List<Employee> employees = parser.parseExcelFile();

            // Подсчёт количества физических лиц и компаний
            long individualCount = employees.stream().filter(e -> e.getEmployeeType().equals("Individual")).count();
            long companyCount = employees.stream().filter(e -> e.getEmployeeType().equals("Company")).count();

            System.out.println("Количество физических лиц: " + individualCount);
            System.out.println("Количество компаний: " + companyCount);

            // Поиск сотрудников моложе 20 лет
            employees.stream()
                    .filter(e -> e instanceof Individual)
                    .map(e -> (Individual) e)
                    .filter(ind -> ind.getAge() < 20)
                    .forEach(ind -> System.out.println("Младше 20 лет: " + ind.getFirstName() + " " + ind.getLastName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}