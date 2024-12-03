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
            System.out.print("First Name: ");
            user.setFirstName(scn.nextLine());
            System.out.print("Last Name: ");
            user.setLastName(scn.nextLine());
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
    
    private void updateUserId()
    {
        try
        {
            RandomAccessFile file = new RandomAccessFile("BankData.txt", "rws"); 
            file.seek(24);
            file.write((newId+"").getBytes());
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
                                                                     System.out.println(str);
                        getAccountFromFile(chOffsetBeginning);
                    } 
                }
                chOffset += str.length() + 1; // need to +1 for newline
                
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
                                                                     System.out.println(str);
                        getAccountFromFile(chOffsetBeginning);
                    } 
                }
                chOffset += str.length() + 1; // need to +1 for newline
                
            }
        }catch(Exception e){ System.out.println(e); }
    }
    
    public BankAccount getAccountFromFile(int offset)
    {
        System.out.println("Reached");
        try{
            RandomAccessFile file = new RandomAccessFile("BasicAccounts.txt", "r"); 
                                                      System.out.println(offset);
            file.seek(offset);
            System.out.println(file.readLine());
        }catch(Exception e){ System.out.println(e); }
        return new BasicAccount();
    }
}
