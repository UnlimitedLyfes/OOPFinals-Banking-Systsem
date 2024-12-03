package com.mycompany._oopfinals;
/**
 *
 * @author kurt
 */
public abstract class BankAccount implements AccountInterface {
    protected int accountId;
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String address;
    protected double balance;
    
    public int getId() { return accountId; }
    public void setId(int accountId) { this.accountId = accountId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public double getBalance() { return balance; }
    public void setBalance(double age) { this.balance = balance; }
    
    public abstract void writeAccount();
    
    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
