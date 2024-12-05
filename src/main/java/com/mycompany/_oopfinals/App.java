package com.mycompany._oopfinals;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Bank bank = new Bank();
        char repeat;
        Scanner scn = new Scanner(System.in);
        while(true)
        {
            bank.displayMainMenu();
            switch(bank.getChoice())
            {
                case 1:
                    bank.addAccount();
                    break;
                case 2:
                    bank.searchUser();
                    break;
                case 3:    
                    return;

            }
            System.out.println("\n\n");
            
        }
    }
}
