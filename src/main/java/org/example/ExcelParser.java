package org.example;

import org.example.entities.BankAccount;
import org.example.entities.Company;
import org.example.entities.Employee;
import org.example.entities.Individual;
import org.example.entities.CompanyType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {
    private final String filePath;

    public ExcelParser(String filePath) {
        this.filePath = filePath;
    }

    // Метод для парсинга Excel файла
    public List<Employee> parseExcelFile() throws IOException {
        List<Employee> employees = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath));

        Sheet sheet = workbook.getSheetAt(0);

        // Проходим по строкам и колонкам для поиска данных
        for (int rowIndex = 2; rowIndex < sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            if (row != null && row.getCell(0).getCellType() != CellType.BLANK) {
                long id = (long) Double.parseDouble(getCellValue(row.getCell(0)).trim());
                String email = getCellValue(row.getCell(1));
                String phone = getCellValue(row.getCell(2));
                String address = getCellValue(row.getCell(3));

                // Получаем данные для банковского аккаунта
                String iban = getCellValue(row.getCell(13));
                String bic = getCellValue(row.getCell(14));
                String accountHolder = getCellValue(row.getCell(15));
                BankAccount bankAccount = new BankAccount(iban, bic, accountHolder);

                // Проверяем данные во второй таблице Individual
                Cell firstNameCell = row.getCell(5);
                if (firstNameCell != null && firstNameCell.getCellType() != CellType.BLANK) {
                    // Если есть данные для физического лица, создаем объект Individual
                    String firstName = firstNameCell.getStringCellValue();
                    String lastName = row.getCell(6).getStringCellValue();
                    boolean hasChildren = Boolean.parseBoolean(getCellValue(row.getCell(7)));
                    int age = (int) row.getCell(8).getNumericCellValue();

                    Individual individual = new Individual(id, email, phone, address, bankAccount, firstName, lastName, hasChildren, age);
                    employees.add(individual);
                } else {
                    // Если нет данных для физического лица, значит это компания
                    String companyName = row.getCell(10).getStringCellValue();
                    String typeString = row.getCell(11).getStringCellValue();
                    CompanyType type = CompanyType.valueOf(typeString);

                    Company company = new Company(id, email, phone, address, bankAccount, companyName, type);
                    employees.add(company);
                }
            }
        }

        workbook.close();
        return employees;
    }

    static private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
