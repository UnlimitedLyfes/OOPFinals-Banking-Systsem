/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._oopfinals;

/**
 *
 * @author kurt
 */
public interface AccountInterface {
    public void deposit(double amount);
    public boolean withdraw(double amount);
    
    public int getId();
    public void setId(int accountId);
    
    public String getName();
    public void setName(String name);

    public int getAge();
    public void setAge(int age);

    public String getAddress();
    public void setAddress(String address);
    
    public double getBalance();
    public void setBalance(double age);
    
    public abstract void writeAccount();
    public abstract void updateEntry(int offset);
    
    public void displayInfo();
}
