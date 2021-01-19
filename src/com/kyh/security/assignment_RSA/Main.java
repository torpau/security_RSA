package com.kyh.security.assignment_RSA;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n\n");
        System.out.println("    ..:: RSA assignment ::..    ");
        System.out.println("########################################");

        KeyFiles keyFiles = new KeyFiles();
        ArrayList<String> listOfKeys = keyFiles.useSavedKeys();

        if (!listOfKeys.isEmpty()){
            System.out.println("#   Declare which key you want to use!");
            listOfKeys
                    .forEach(e -> System.out.println("#   " + e));
            Scanner scKey = new Scanner(System.in);
            String keyName = scKey.nextLine();

            while(scKey.hasNextLine()) {
                if (listOfKeys.contains(keyName)) {
                    System.out.println("yes");
                } else {
                    System.out.println("Bad input");
                }
            }

        } else{
            System.out.println("#   There are no keys to load!");
            System.out.println("#   Would you like to generate");
            System.out.println("#   these?                    ");
            System.out.println("#");
            System.out.println("#   1. YES                    ");
            System.out.println("#   2. NO                     ");
            System.out.print("#   ");

            Scanner sc = new Scanner(System.in);
            String newKey = "";
            while (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("#   Please name your key!");
                    newKey = keyFiles.getNewKeyName();
                    break;
                } else if (choice == 2) {
                    System.exit(0);
                } else{
                    System.out.println("Bad input");
                }
            }
            System.out.println(newKey);
        }
    }
}
