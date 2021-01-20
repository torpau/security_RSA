package com.kyh.security.assignment_RSA;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Menu {
    private final Main main;
    private int choiceOfMenu = 101;
    private ArrayList<String> listOfKeys;
    private KeyPair publicKey = null;
    private KeyPair privateKey = null;

    Menu(Main main){
        this.main = main;
    }

    public int getChoiceOfMenu() { return choiceOfMenu; }
    public void setChoiceOfMenu(int choiceOfMenu) { this.choiceOfMenu = choiceOfMenu; }

    public void setPublicKey(KeyPair publicKey) { this.publicKey = publicKey; }
    public void setPrivateKey(KeyPair privateKey) { this.privateKey = privateKey; }


    void whereToNavigate(){
        while(true) {
            switch (choiceOfMenu) {
                case 101 -> menu101();
                case 201 -> menu201();
                case 202 -> menu202();
                case 301 -> menu301();
                case 302 -> menu302();
                case 401 -> menu401();
                case 402 -> menu402();
                case 901 -> menu901();

            }
        }
    }

    void menu101() {
        System.out.println("Menu 101");
        boolean loop = true;

        KeyFiles keyFiles = new KeyFiles(main);
        listOfKeys = keyFiles.useSavedKeys();

        if (!listOfKeys.isEmpty()) {
            System.out.println("#   You have the following saved keys:");
            System.out.println("#");
            listOfKeys
                    .forEach(e -> System.out.println("#   " + (char) 45 + " " + e));

            System.out.println("#");
            System.out.println("#   Would you like to use any of these?");
            System.out.println("#");
            System.out.println("#   1. YES");
            System.out.println("#   2. NO");
            Scanner userInput = new Scanner(System.in);
            while (loop) {
                switch (userInput.nextInt()) {
                    case 1 -> {
                        choiceOfMenu = 201;
                        loop = false;
                    }
                    case 2 -> {
                        choiceOfMenu = 202;
                        loop = false;
                    }
                    default -> System.out.println("Bad input");
                }
            }
        } else {
            System.out.println("#   ");
            System.out.println("#   You have no saved keys!");
            choiceOfMenu = 202;
        }
    }

    void menu201() {
        System.out.println("Menu 201");

        if (!listOfKeys.isEmpty()) {
            System.out.println("#");
            System.out.println("#");
            System.out.println("#");
            System.out.println("#   Which of the following saved keys:");
            System.out.println("#");
            listOfKeys
                    .forEach(e -> System.out.println("#   " + (char)45 + " "  + e));
        }
        System.out.println("#");
        System.out.println("#   ...would you like to use?");

        Scanner userInput = new Scanner(System.in);

        boolean usableKeyName = false;
        String keyName = "";
        while(userInput.hasNextLine()) {
            String key = userInput.nextLine();
            if (!key.isEmpty()) {
                key = key.toLowerCase();
            }
            if (listOfKeys.contains(key)) {
                usableKeyName = true;
                System.out.println("all good");

                KeyFiles keyFiles = new KeyFiles(main);
                String test = main.getFolder() + key + "_pub.key";
                publicKey = keyFiles.readKey(main.getFolder() + key + "_pub.key");
                privateKey = keyFiles.readKey(main.getFolder() + key + "_pri.key");
                choiceOfMenu = 301;
                break;
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu202() {
        System.out.println("Menu 202");
        boolean loop = true;

        System.out.println("#");
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   You need to generate a new");
        System.out.println("#   key to proceed");
        System.out.println("#");
        System.out.println("#   1. OKAY");
        System.out.println("#   2. NO, I don't");
        Scanner userInput = new Scanner(System.in);
        while (loop) {
            switch (userInput.nextInt()) {
                case 1 -> {
                    choiceOfMenu = 302;
                    loop = false;
                }
                case 2 -> {
                    choiceOfMenu = 901;
                    loop = false;
                }
                default -> System.out.println("Bad input");
            }
        }
    }

    void menu301(){
        System.out.println("Menu 301");
        boolean loop = true;
        System.out.println("#");
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   What do you want to do now?");
        System.out.println("#");
        System.out.println("#   1. Encrypt a typed message?");
        System.out.println("#   2. Decrypt a typed message?");
        System.out.println("#   7. Change key!");

        System.out.println("#   9. Quit");
        Scanner userInput = new Scanner(System.in);
        while(loop){
            switch (userInput.nextInt()) {
                case 1 -> {
                    choiceOfMenu = 401;
                    loop = false;
                }
                case 2 -> {
                    choiceOfMenu = 402;
                    loop = false;
                }
                case 7 -> {
                    choiceOfMenu = 101;
                    loop = false;
                }
                case 9 -> {
                    choiceOfMenu = 901;
                    loop = false;
                }
                default -> System.out.println("Bad input");
            }
        }
    }

    void menu302() {
        System.out.println("Menu 401");

        System.out.println("#");
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   Please, name you key:");
        System.out.println("#");

        Scanner userInput = new Scanner(System.in);

        String keyName = "";
        while(userInput.hasNextLine()) {
            String key = userInput.nextLine();
            if (!key.isEmpty()) {
                key = key.toLowerCase();

                Pattern pattern = Pattern.compile("[a-z0-9]*");
                Matcher matcher = pattern.matcher(key);
                if (matcher.matches()) {
                    //generate
                    KeyGenerator keyGenerator = new KeyGenerator(main);
                    keyGenerator.generateKeys(main.getFolder() + key, main.getBitLength(), this);
                    choiceOfMenu = 301;
                    break;
                } else {
                    System.out.println("Bad input");
                }
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu401() {
        System.out.println("Menu 401");
        System.out.println("#");
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   Please enter the message you want to encrypt:");
        System.out.println("#");

        Scanner userIn = new Scanner(System.in);
        while(userIn.hasNextLine()) {
            String message = userIn.nextLine();
            if (!message.isEmpty()) {
                Crypto crypto = new Crypto(main);

                String encrypted = crypto.encrypt(message, privateKey);
                System.out.println("Here you go! Your encrypted message:");
                System.out.println(encrypted);


                choiceOfMenu = 301;
                break;
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu402() {
        System.out.println("Menu 402");
        System.out.println("#");
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   Please enter the message you want to decrypt:");
        System.out.println("#");

        Scanner userIn = new Scanner(System.in);
        while(userIn.hasNextLine()) {
            String message = userIn.nextLine();
            if (!message.isEmpty()) {
                Crypto crypto = new Crypto(main);

                String decrypted = crypto.decrypt(message, publicKey);
                System.out.println("Here you go! Your decrypted message:");
                System.out.println(decrypted);


                choiceOfMenu = 301;
                break;
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu901() {
        System.out.println("Menu 901");
        System.out.println("#");
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   Okay... bye bye!");
        System.exit(0);
    }

}
