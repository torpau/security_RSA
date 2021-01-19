package com.kyh.security.assignment_RSA;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static int bitLength = 4096;
    static String folder = "./src/com/kyh/security/assignment_RSA/keyFiles/";

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

            boolean usableKeyName = false;
            while(!usableKeyName) {
                String keyName = keyFiles.getKeyNameToUse();
                if (listOfKeys.contains(keyName)) {
                    usableKeyName = true;
                    System.out.println("all good");

                    KeyPair publicKey = keyFiles.readKey(folder + keyName + "_pub.key");
                    KeyPair privateKey = keyFiles.readKey(folder + keyName + "_pri.key");


                } else {
                    System.out.println("Bad input");
                }
            }
        } else{
            System.out.println("#   You need to generate a new");
            System.out.println("#   key to proceed");
            System.out.println("#");
            System.out.println("#   1. YES                    ");
            System.out.println("#   2. NO                     ");

            Scanner sc = new Scanner(System.in);
            String newKey = "";
            while (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("#   Please name your key!");
                    newKey = keyFiles.getKeyNameToUse();
                    break;
                } else if (choice == 2) {
                    System.out.println("okay... bye bye");
                    System.exit(0);
                } else{
                    System.out.println("Bad input");
                }
            }
            System.out.println(newKey+" : time to generate these bad boys");

            KeyGenerator keyGenerator = new KeyGenerator();
            keyGenerator.generateKeys(folder + newKey, bitLength);
        }
    }
}
