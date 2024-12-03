package com.mycompany._oopfinals;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.displayMainMenu();
        switch(bank.getChoice())
        {
            case 1:
                bank.searchUser();
                break;
            case 2:
                break;
            case 3:        
                bank.addAccount();
                break;
            case 4:
                break;
        }
    }
}
