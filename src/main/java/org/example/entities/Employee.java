package org.example.entities;

public abstract class Employee {
    private final long id;
    private final String email, phone, address;
    private final BankAccount bankAccount;

    public Employee(long id,
                    String email, String phone, String address,
                    BankAccount bankAccount) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bankAccount = bankAccount;
    }

    public long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }


    public String getAddress() {
        return address;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public abstract String getEmployeeType();
}
