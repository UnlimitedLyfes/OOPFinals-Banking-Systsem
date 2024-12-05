package com.mycompany._oopfinals;

import java.io.FileWriter;
import java.io.RandomAccessFile;

public class GoldAccount extends BankAccount {
    
    @Override
    public void writeAccount()
    {
        try
        {
             FileWriter fw = new FileWriter("GoldAccounts.txt", true);
             fw.write(accountId + "\n");
             fw.write(name + "\n");
             fw.write(age + "\n");
             fw.write(address + "\n");
             fw.write(balance + "\n\n");
             fw.close();
        } catch(Exception e){System.out.println(e);}
       
    }
    
    public void updateEntry(int offset)
    {
        try{
            RandomAccessFile file = new RandomAccessFile("GoldAccounts.txt", "rwd");
            file.seek(offset);
            file.write((accountId + "\n").getBytes());
            file.write((name + "\n").getBytes());
            file.write((age + "\n").getBytes());
            file.write((address + "\n").getBytes());
            file.write((balance + "\n\n").getBytes());
            file.close();
        }catch(Exception e){ System.out.println(e);}
    }
}

