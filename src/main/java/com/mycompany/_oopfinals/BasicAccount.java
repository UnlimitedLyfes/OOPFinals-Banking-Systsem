package com.mycompany._oopfinals;
import java.io.*;
public class BasicAccount extends BankAccount {
    
    @Override
    public void writeAccount()
    {
        try
        {
             FileWriter fw = new FileWriter("BasicAccounts.txt", true);
             fw.write(Integer.toString(accountId) + '\n');
             fw.write(firstName + ' ');
             fw.write(lastName + '\n');
             fw.write(Integer.toString(age) + '\n');
             fw.write(address + '\n');
             fw.write(balance + "\n\n");
             fw.close();
        } catch(Exception e){System.out.println(e);}
       
    }
}
