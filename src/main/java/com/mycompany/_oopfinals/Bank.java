package com.mycompany._oopfinals;
import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
public class Bank {
    private static int newId = 1; // Fix
    private static RandomAccessFile file;
    
    public Bank()
    {
        try{
            file = new RandomAccessFile("BankData.txt", "r");
            file.seek(22);
            String read = file.readLine();
            newId = Integer.parseInt(read.trim());
            file.close();
        } catch(Exception e){ System.out.println(e); }
    }
    
    public void displayMainMenu()
    {
        System.out.println("KF Banking System");
        System.out.print("[1] Add Account\n"
                + "[2] Modify Account\n"
                + "[3] Exit\n"
                + "> ");
    }
    
    public int getChoice()
    {
        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();
        scn.nextLine();
        return choice;
    }
    
    public void addAccount()
    {
        Scanner scn = new Scanner(System.in);
        BankAccount user = new BasicAccount();
        try{
            System.out.println("\nAdding Account:\n");
            System.out.print("Name: ");
            user.setName(scn.nextLine().toUpperCase());
            System.out.print("Age: ");
            user.setAge(scn.nextInt());
            scn.nextLine(); 
            System.out.print("Address: ");
            user.setAddress(scn.nextLine());
            user.setId(newId);
            newId++;
            updateUserId();
            user.writeAccount();
        }
        catch(InputMismatchException e)
        {
            System.out.println("Invalid Entry");
            return;
        }
        catch(Exception e){System.out.println(e);}
        
        System.out.println("\nAccount Added. ID #" + (newId - 1));   
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
    
    public void searchUser()
    {
        System.out.println("\nSearch for Account using Id or Name:");
        String searchTerm = getStringInput().toUpperCase();
        if(searchTerm == "0")
        {
            System.out.println("User not found.");
            return;
        }
        int fileOffset = getUserFileOffset(searchTerm);
        if(fileOffset == -1)
        {
            System.out.println("\nUser not found.");
        }
        else
        {
            BankAccount searchedAccount = getAccountFromOffset(fileOffset);
            System.out.println("\n");
            searchedAccount.displayInfo();
            editMenu(searchedAccount, fileOffset);
        }
    }
    
    private void editMenu(BankAccount acc, int offset)
    {
        while(true)
        {
            System.out.print("\n[1] Deposit"
                    + "\n[2] Withdraw"
                    + "\n[3] Edit Information"
                    + "\n[4] Delete Account"
                    + "\n[5] Done"
                    + "\n> ");
            switch(getChoice())
            {
                case 1:
                    System.out.print("Insert amount to deposit: ");
                    acc.deposit(Double.parseDouble(getStringInput()));
                    acc.updateEntry(offset);
                    System.out.println("Deposit Successful. Balance: " + acc.getBalance());
                    break;
                case 2:
                    System.out.print("Insert amount to withdraw: ");
                    if(acc.withdraw(Double.parseDouble(getStringInput())))
                        acc.updateEntry(offset);
                    else 
                        System.out.println("\nNot enough balance.");
                    break;
                case 3:
                    editInformationMenu(acc, offset);
                    break;
                case 4:
                    acc.setId(0);
                    acc.setName("[-]");
                    acc.updateEntry(offset);
                    break;
                case 6:
                    return;
            }
        }
    }
    
    private void editInformationMenu(BankAccount acc, int offset)
    {
        System.out.print("\nEditing Account Information: \n"
                + "[1] Change Name\n"
                + "[2] Chenge Age\n"
                + "[3] Change Address\n"
                + "[4] Exit"
                + "> ");
        switch(getChoice())
        {
            case 1:
                System.out.print("Change Name to: ");
                acc.setName(getStringInput().toUpperCase());
                break;
            case 2:
                System.out.print("Change Age to: ");
                acc.setAge(Integer.parseInt(getStringInput()));
                break;
            case 3:
                System.out.print("Change Address to: ");
                acc.setAddress(getStringInput());
                break;
            case 4: 
                return;
            default:
                System.out.println("Invalid.");
                return;
        }
        acc.updateEntry(offset);
    }
    
    private String getStringInput()
    {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine();
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
                if((lineCount % 6 == 1||lineCount % 6 == 2) && str.contains(searchTerm))
                    return chOffsetBeginning;
                chOffset += str.length() + 1; // need to +1 for newline, +2 if not working
                
            }
        }catch(Exception e){ System.out.println(e + "getUserFileOffset"); }  
        return -1;
    }
    

    private BankAccount getAccountFromOffset(int offset)
    {
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
        }catch(Exception e){ System.out.println(e + "getAccountFromOffset"); }
        return new BasicAccount();
    }
}
