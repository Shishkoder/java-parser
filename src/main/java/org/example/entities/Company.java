package org.example.entities;


public class Company extends Employee {
    private final String companyName;
    private final CompanyType type;

    public Company(long id,
                   String email, String phone, String address,
                   BankAccount bankAccount,
                   String companyName, CompanyType type) {
        super(id, email, phone, address, bankAccount);
        this.companyName = companyName;
        this.type = type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CompanyType getType() {
        return type;
    }

    @Override
    public String getEmployeeType() {
        return "Company";
    }
}
