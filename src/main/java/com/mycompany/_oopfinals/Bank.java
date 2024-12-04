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
        System.out.println("\nSearch for Account using Id or Name:");
        String searchTerm = getStringInput();
        int fileOffset = getUserFileOffset(searchTerm);
        if(fileOffset == -1)
        {
            System.out.println("\nUser not found.");
        }
        else
        {
            BankAccount searchedAccount = getAccountFromOffset(fileOffset);
            searchedAccount.displayInfo();
            editMenu(searchedAccount, fileOffset);
        }
    }
    
    private void editMenu(BankAccount acc, int offset)
    {
        System.out.println("\n[1] Deposit"
                + "\n[2] Withdraw"
                + "\n[3] Edit Information"
                + "\n[4] Done");
        switch(getChoice())
        {
            case 1:
                System.out.print("Insert amount to deposit: ");
                acc.deposit(Double.parseDouble(getStringInput()));
                acc.updateEntry(offset);
                break;
            case 2:
                System.out.print("Insert amount to deposit: ");
                acc.withdraw(Double.parseDouble(getStringInput()));
                acc.updateEntry(offset);
                break;
            case 3:
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
                if((lineCount % 6 == 1||lineCount % 6 == 2)&& str.contains(searchTerm))
                    return chOffsetBeginning;
                chOffset += str.length() + 1; // need to +1 for newline, +2 if not working
                
            }
        }catch(Exception e){ System.out.println(e); }  
        return -1;
    }
    
    private void updateUserId() // For The Counter
    {
        try
        {
            RandomAccessFile file = new RandomAccessFile("BankData.txt", "rws"); 
            file.seek(24);
            file.write((newId + "").getBytes());
            file.close();
        } catch(Exception e){ System.out.println(e); }
    }
    

    private BankAccount getAccountFromOffset(int offset)
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
            return account;
        }catch(Exception e){ System.out.println(e); }
        return new BasicAccount();
    }
}
