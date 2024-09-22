package org.example.entities;

public class Individual extends Employee {
    private final String firstName, lastName;
    private final boolean hasChildren;
    private final int age;

    public Individual(long id,
                      String email, String phone, String address,
                      BankAccount bankAccount,
                      String firstName, String lastName,
                      boolean hasChildren, int age) {
        super(id, email, phone, address, bankAccount);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasChildren = hasChildren;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String getEmployeeType() {
        return "Individual";
    }
}
