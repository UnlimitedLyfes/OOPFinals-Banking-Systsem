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
             fw.write(name + '\n');
             fw.write(Integer.toString(age) + '\n');
             fw.write(address + '\n');
             fw.write(balance + "\n\n");
             fw.close();
        } catch(Exception e){System.out.println(e);}
       
    }
    
    public void updateEntry(int offset)
    {
        try{
            RandomAccessFile file = new RandomAccessFile("BasicAccounts.txt", "rw");
            file.seek(offset);
            file.write((Integer.toString(accountId) + '\n').getBytes());
            file.write((name + '\n').getBytes());
            file.write((Integer.toString(age) + '\n').getBytes());
            file.write((address + '\n').getBytes());
            file.write((balance + "").getBytes());
            file.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
