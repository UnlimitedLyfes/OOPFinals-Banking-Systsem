package com.mycompany._oopfinals;
import java.util.Scanner;
import java.io.*;
public class Bank {
    private static int newId = 1; // Fix
    private static RandomAccessFile file;
    
    public Bank()
    {
        try{
            file = new RandomAccessFile("BankData.txt", "r");
            file.seek(22);
            String read = file.readLine();
            System.out.println(read);
            newId = Integer.parseInt(read.trim());
            file.close();
        } catch(Exception e){ System.out.println(e); }
    }
    
    public void displayMainMenu()
    {
        System.out.println("KF Banking System");
        System.out.println("[1] Display/Update Account\n"
                + "[2] Display All Accounts\n"
                + "[3] Add Account\n"
                + "[4] Delete Account");
    }
    
    public int getChoice()
    {
        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();
        scn.nextLine();
        return choice;
    }
    
    public void searchUser()
    {
        System.out.println("Search for Account:"
                + "\n\n[1] Search by ID"
                + "\n[2] Search by Name");
        
        int choice = getChoice();
        
        System.out.println("Search for: ");
        
        String searchTerm = getStringInput();
        
        switch(choice)
        {
            case 1:
                getAccountById(searchTerm);
                break;
            case 2:
                getAccountByName(searchTerm);
                break;
        }
    }
    
    private String getStringInput()
    {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine();
    }
    
    public void addAccount()
    {
        Scanner scn = new Scanner(System.in);
        BankAccount user = new BasicAccount();
        try{
            System.out.println("Adding Account:\n");
            user.setId(newId);
            newId++;
            updateUserId();
            System.out.print("Name: ");
            user.setName(scn.nextLine());
            System.out.print("Age: ");
            user.setAge(scn.nextInt());
            scn.nextLine(); 
            System.out.print("Address: ");
            user.setAddress(scn.nextLine());
            user.writeAccount();
        }
        catch(Exception e){System.out.println(e);}
        
        System.out.println("Account Added.");
    }
    
    private int getUserFileOffset(String searchTerm)
    {
        try{
            Scanner scn = new Scanner(new File("BasicAccounts.txt"));
            int lineCount = 0;
            int chOffset = 0;
            int chOffsetBeginning = 0; //Beginning of entry
            while(scn.hasNextLine())
            {
                lineCount++;
                String str = scn.nextLine(); 
                if(lineCount % 6 == 1)
                    chOffsetBeginning = chOffset;
                if(str.contains(searchTerm))
                    return chOffsetBeginning;
                chOffset += str.length() + 2; // need to +1 for newline, +2 if not working
                
            }
        }catch(Exception e){ System.out.println(e); }  
        return -1;
    }
    
    private void updateUserId()
    {
        try
        {
            RandomAccessFile file = new RandomAccessFile("BankData.txt", "rws"); 
            file.seek(24);
            file.write((newId + "").getBytes());
            file.close();
        } catch(Exception e){ System.out.println(e); }
    }
    
    public void getAccountById(String id)
    {
        try{
            Scanner scn = new Scanner(new File("BasicAccounts.txt"));
            int lineCount = 0;
            int chOffset = 0;
            int chOffsetBeginning = 0; //Beginning of entry
            while(scn.hasNextLine())
            {
                lineCount++;
                String str = scn.nextLine(); 
                if(lineCount % 6 == 1)
                {
                    chOffsetBeginning = chOffset;
                    
                    if(str.contains(id))
                    {
                        getAccountFromFile(chOffsetBeginning);
                    } 
                }
                chOffset += str.length() + 2; // need to +1 for newline, +2 if not working
                
            }
        }catch(Exception e){ System.out.println(e); }  
    }
    
    public void getAccountByName(String name)
    {
        try{
            Scanner scn = new Scanner(new File("BasicAccounts.txt"));
            int lineCount = 0;
            int chOffset = 0;
            int chOffsetBeginning = 0; //Beginning of entry
            while(scn.hasNextLine())
            {
                lineCount++;
                String str = scn.nextLine(); 
                
                if(lineCount % 6 == 1)
                    chOffsetBeginning = chOffset;
                
                if(lineCount % 6 == 2)
                {
                    if(str.contains(name))
                    {
                        getAccountFromFile(chOffsetBeginning);
                    } 
                }
                chOffset += str.length() + 2; // need to +1 for newline, +2 if not working
                
            }
        }catch(Exception e){ System.out.println(e); }
    }
    
    private BankAccount getAccountFromFile(int offset)
    {
        System.out.println("Reached");
        try{
            RandomAccessFile file = new RandomAccessFile("BasicAccounts.txt", "r"); 
            file.seek(offset);
            BankAccount account = new BasicAccount();
            account.setId(Integer.parseInt(file.readLine()));
            account.setName(file.readLine());
            account.setAge(Integer.parseInt(file.readLine()));
            account.setAddress(file.readLine());
            account.setBalance(Double.parseDouble(file.readLine()));
            account.displayInfo();
            return account;
        }catch(Exception e){ System.out.println(e); }
        return new BasicAccount();
    }
}
