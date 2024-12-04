package com.mycompany._oopfinals;
/**
 *
 * @author kurt
 */
public abstract class BankAccount implements AccountInterface {
    protected int accountId;
    protected String name;
    protected int age;
    protected String address;
    protected double balance;
    
    public int getId() { return accountId; }
    public void setId(int accountId) { this.accountId = accountId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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

    public void displayInfo()
    {
        System.out.println("Account Id: " + accountId 
                + "\nName: " + name 
                + "\nAge: " + age 
                + "\nAddress: " + address 
                + "\nBalance: " + balance);
    }
}
