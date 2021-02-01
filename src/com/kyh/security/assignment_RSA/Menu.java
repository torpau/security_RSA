package com.kyh.security.assignment_RSA;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Menu {
    private final Main main;
    private int choiceOfMenu = 101;
    private ArrayList<String> listOfKeys;
    private String presentKey = "";
    private String presentKeyType = "";
    private KeyPair presentKeyKey = null;
    private KeyPair publicKey = null;
    private KeyPair privateKey = null;

    Menu(Main main){
        this.main = main;
    }

    public int getChoiceOfMenu() { return choiceOfMenu; }
    public void setChoiceOfMenu(int choiceOfMenu) { this.choiceOfMenu = choiceOfMenu; }

    public void setPublicKey(KeyPair publicKey) { this.publicKey = publicKey; }
    public void setPrivateKey(KeyPair privateKey) { this.privateKey = privateKey; }

    public String getPresentKey() { return presentKey; }
    public void setPresentKey(String presentKey) { this.presentKey = presentKey; }

    public String getPresentKeyType() { return presentKeyType; }
    public void setPresentKeyType(String presentKeyType) { this.presentKeyType = presentKeyType; }


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
                case 403 -> menu403();
                case 404 -> menu404();
                case 405 -> menu405();
                case 406 -> menu406();
                case 501 -> menu501();
                case 901 -> menu901();

            }
        }
    }

    void menu101() {
        boolean loop = true;
        border();
        KeyFiles keyFiles = new KeyFiles(main);
        listOfKeys = keyFiles.useSavedKeys();

        if (!listOfKeys.isEmpty()) {
            System.out.println("#");
            System.out.println("#");
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
            try {
                Thread.sleep(2400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            choiceOfMenu = 202;
        }
    }

    void menu201() {
        border();
        if (!listOfKeys.isEmpty()) {
            System.out.println("#");
            if(!presentKey.equals("")) {
                System.out.println("#   >> Loaded key: " + presentKey);
            }
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
        while(userInput.hasNextLine()) {
            String key = userInput.nextLine();
            if (!key.isEmpty()) {
                key = key.toLowerCase();
            }
            if (listOfKeys.contains(key)) {
                usableKeyName = true;

                KeyFiles keyFiles = new KeyFiles(main);

                try {
                    publicKey = keyFiles.readKey(main.getFolder() + "keyCrypto/" + key + "_pub.key");
                    System.out.println("#   Loaded public  key: "  + key + "_pub.key" );
                    presentKey = key;
                    presentKeyType = "_pub.key";
                    presentKeyKey = publicKey;
                } catch (Exception e) {
                    publicKey = null;
                    System.out.println("#   Loaded public  key: <NO SUCH FILE>");
                }

                try {
                    privateKey = keyFiles.readKey(main.getFolder() + "keyCrypto/" + key + "_pri.key");
                    System.out.println("#   Loaded private key: "  + key + "_pri.key" );
                    presentKey = key;
                    presentKeyType = "_pri.key";
                    presentKeyKey = privateKey;
                } catch (Exception e) {
                    privateKey = null;
                    System.out.println("#   Loaded private key: <NO SUCH FILE>");
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                choiceOfMenu = 301;
                break;
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu202() {
        border();
        boolean loop = true;

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
        border();
        boolean loop = true;
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   What do you want to do now?");
        System.out.println("#");
        System.out.println("#   1. Encrypt a typed message!");
        System.out.println("#   2. Decrypt a typed message!");
        System.out.println("#   3. Encrypt a message to a txt file!");
        System.out.println("#   4. Decrypt a message from a txt file!");
        System.out.println("#   5. Image en-/decrypt with copy of image ( NOT WORKING )");
        System.out.println("#   7. Change key!");
        System.out.println("#   9. Exit");
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
                case 3 -> {
                    choiceOfMenu = 403;
                    loop = false;
                }
                case 4 -> {
                    choiceOfMenu = 406;
                    loop = false;
                }
                case 5 -> {
                    choiceOfMenu = 501;
                    loop = false;
                }
                case 7 -> {
                    choiceOfMenu = 201;
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
        border();
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
                    KeyGenerator keyGenerator = new KeyGenerator(main);
                    keyGenerator.generateKeys(main.getFolder() + "keyCrypto/" + key, main.getBitLength(), this);
                    if(publicKey != null && privateKey != null){
                        System.out.println("#   Loaded public  key: "  + key + "_pub.key" );
                        System.out.println("#   Loaded private key: "  + key + "_pri.key" );
                        presentKey = key;
                        presentKeyType = "_pri.key";
                        presentKeyKey = privateKey;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
        switch (menu601()){
            case 1 -> {
                presentKeyType = "_pri.key";
                presentKeyKey = privateKey;
            }
            case 2 -> {
                presentKeyType = "_pub.key";
                presentKeyKey = publicKey;
            }
        }
        border();
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   >> Loaded key: " + presentKey + presentKeyType);
        System.out.println("#");
        System.out.println("#   Please enter the message you want to encrypt:");
        System.out.println("#");

        Scanner userIn = new Scanner(System.in);
        while(userIn.hasNextLine()) {
            String message = userIn.nextLine();
            if (!message.isEmpty()) {
                Crypto crypto = new Crypto(main);
                String encrypted = crypto.encrypt(message, presentKeyKey);
                System.out.println("#   Here you go! Your encrypted message:");
                System.out.println(encrypted);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                choiceOfMenu = 301;
                break;
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu402() {
        switch (menu601()){
            case 1 -> {
                presentKeyType = "_pri.key";
                presentKeyKey = privateKey;
            }
            case 2 -> {
                presentKeyType = "_pub.key";
                presentKeyKey = publicKey;
            }
        }
        border();
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   >> Loaded key: " + presentKey + presentKeyType);
        System.out.println("#");
        System.out.println("#   Please enter the message you want to decrypt:");
        System.out.println("#");

        Scanner userIn = new Scanner(System.in);
        while(userIn.hasNextLine()) {
            String message = userIn.nextLine();
            if (!message.isEmpty()) {
                Crypto crypto = new Crypto(main);
                String decrypted = crypto.decrypt(message, presentKeyKey);
                System.out.println("#   Here you go! Your decrypted message:");
                System.out.println(decrypted);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                choiceOfMenu = 301;
                break;
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu403() {
        border();
        boolean loop = true;
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   What do you want to encrypt from?");
        System.out.println("#");
        System.out.println("#   1. A typed message");
        System.out.println("#   2. A txt file (*.txt)");
        Scanner userInput = new Scanner(System.in);
        while (loop) {
            switch (userInput.nextInt()) {
                case 1 -> {
                    choiceOfMenu = 404;
                    loop = false;
                }
                case 2 -> {
                    choiceOfMenu = 405;
                    loop = false;
                }
                default -> System.out.println("Bad input");
            }
        }
    }

    void menu404() {
        switch (menu601()){
            case 1 -> {
                presentKeyType = "_pri.key";
                presentKeyKey = privateKey;
            }
            case 2 -> {
                presentKeyType = "_pub.key";
                presentKeyKey = publicKey;
            }
        }
        border();
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   >> Loaded key: " + presentKey + presentKeyType);
        System.out.println("#");
        System.out.println("#   Please enter the message you want to encrypt:");
        System.out.println("#");

        Scanner userIn = new Scanner(System.in);
        while(userIn.hasNextLine()) {
            String message = userIn.nextLine();
            if (!message.isEmpty()) {
                Crypto crypto = new Crypto(main);

                String encrypted = crypto.encrypt(message, presentKeyKey);

                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                String strTime = dateFormat.format(date);

                FileOutputStream fileOut = null;
                try {
                    FileWriter fw = new FileWriter(main.getFolder() + "postCrypto/" + strTime + ".txt", StandardCharsets.UTF_8);
                    fw.write(encrypted);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("#   Saved your encryption to file: " + strTime + ".txt");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                choiceOfMenu = 301;
                break;
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu405() {
        switch (menu601()){
            case 1 -> {
                presentKeyType = "_pri.key";
                presentKeyKey = privateKey;
            }
            case 2 -> {
                presentKeyType = "_pub.key";
                presentKeyKey = publicKey;
            }
        }
        border();
        System.out.println("#");
        System.out.println("#   >> Loaded key: " + presentKey + presentKeyType);
        System.out.println("#");
        System.out.println("#   Here is a list of un-encrypted txt files:");
        System.out.println("#");

        File folder = new File(main.getFolder() + "preCrypto/");
        ArrayList<String> uniqueFiles = new ArrayList<>();

        try {
            if (folder.isDirectory()) {
                File[] listOfFiles = folder.listFiles();
                if (listOfFiles != null && listOfFiles.length > 0) {
                    for (File elements : listOfFiles) {
                        if (elements.isFile() && elements.canRead()) {
                            if (elements.getName().endsWith(".txt") && !elements.getName().equals("readMe.txt")) {
                                uniqueFiles.add(elements.getName());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        uniqueFiles
                .forEach(e -> System.out.println("#   " + (char) 45 + " " + e));

        System.out.println("#");
        System.out.println("#   What file do you want to encrypt?");

        Scanner userInput = new Scanner(System.in);

        boolean usableKeyName = false;
        String keyName = "";
        while(userInput.hasNextLine()) {
            String key = userInput.nextLine();
            if (!key.isEmpty()) {
                key = key.toLowerCase();
                if (!key.endsWith(".txt")){
                    key += ".txt";
                }
            }
            if (uniqueFiles.contains(key)) {
                usableKeyName = true;


                String message ="";
                String ff = main.getFolder() + "preCrypto/" + key;
                //läsa från txt-filen.
                try {
                    Scanner sc = new Scanner(new File(ff));
                    while (sc.hasNextLine()){
                        message += sc.nextLine() + "\n";
                    }
                } catch (IOException i){
                        i.printStackTrace();
                }

                //kryptera och läsa till ny fil
                Crypto crypto = new Crypto(main);
                String encrypted = crypto.encrypt(message, presentKeyKey);

                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                String strTime = dateFormat.format(date);

                FileOutputStream fileOut = null;
                try {
                    FileWriter fw = new FileWriter(main.getFolder() + "postCrypto/" + strTime + ".txt", StandardCharsets.UTF_8);
                    fw.write(encrypted);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("#   Saved encrypted file as: " + strTime + ".txt");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                choiceOfMenu = 301;
                break;
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu406() {
        switch (menu601()){
            case 1 -> {
                presentKeyType = "_pri.key";
                presentKeyKey = privateKey;
            }
            case 2 -> {
                presentKeyType = "_pub.key";
                presentKeyKey = publicKey;
            }
        }
        border();
        System.out.println("#");
        System.out.println("#   >> Loaded key: " + presentKey + presentKeyType);
        System.out.println("#");
        System.out.println("#   Here is a list of encrypted txt files:");
        System.out.println("#");

        File folder = new File(main.getFolder() + "postCrypto/");
        ArrayList<String> uniqueFiles = new ArrayList<>();

        try {
            if (folder.isDirectory()) {
                File[] listOfFiles = folder.listFiles();
                if (listOfFiles != null && listOfFiles.length > 0) {
                    for (File elements : listOfFiles) {
                        if (elements.isFile() && elements.canRead() && !elements.getName().equals("readMe.txt")) {
                            uniqueFiles.add(elements.getName());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        uniqueFiles
                .forEach(e -> System.out.println("#   " + (char) 45 + " " + e));

        System.out.println("#");
        System.out.println("#   What file do you want to try to decrypt?");

        Scanner userInput = new Scanner(System.in);

        boolean usableKeyName = false;
        String keyName = "";
        while(userInput.hasNextLine()) {
            String key = userInput.nextLine();
            if (!key.isEmpty()) {
                key = key.toLowerCase();
                if (key.length() > 4 && !key.substring(key.length() - 4).equals(".txt")) {
                    key += ".txt";
                }
            }
            if (uniqueFiles.contains(key)) {
                usableKeyName = true;

                String ff = main.getFolder() + "postCrypto/" + key;

                //läsa från txt-filen.
                try {
                    FileInputStream infil = new FileInputStream(ff);
                    byte[] contentBytes  = infil.readAllBytes();
                    String content = new String(contentBytes, StandardCharsets.UTF_8);
                    infil.close();

                    Crypto crypto = new Crypto(main);
                    String decrypted = crypto.decrypt(content, presentKeyKey);
                    System.out.println("#   Here you go! Your decrypted message:");
                    System.out.println(decrypted);

                } catch (IOException i){
                    i.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                choiceOfMenu = 301;
                break;
            } else {
                System.out.println("Bad input");
            }
        }
    }

    void menu501(){

        try {

            FileInputStream inputFile = new FileInputStream("./src/com/kyh/security/assignment_RSA/keyFiles/ring.jpg");
            byte[] imageContentAsBytes  = inputFile.readAllBytes();

            byte[] imageContentAsBytes2 = new byte[imageContentAsBytes.length-1];
            for(int i = 0; i < imageContentAsBytes.length-1; i++) {
                imageContentAsBytes2[i] = imageContentAsBytes[i + 1];
            }
            String imageContentAsString = Arrays.toString(imageContentAsBytes2);
            inputFile.close();




            // --- for testing ---
            for(int i = 0; i < 10; i++) {
                System.out.print(imageContentAsBytes[i] + ", ");
            }
            System.out.print(". . . ");
            for(int i = imageContentAsBytes.length-11; i < imageContentAsBytes.length-2; i++) {
                System.out.print(imageContentAsBytes[i] + ", ");
            }
            System.out.println("");
//
            BigInteger test = new BigInteger(imageContentAsBytes2);
            byte[] test2 = test.toByteArray();
//

            for(int i = 0; i < 10; i++) {
                System.out.print(test2[i] + ", ");
            }
            System.out.print(". . . ");
            for(int i = test2.length-11; i < test2.length-2; i++) {
                System.out.print(test2[i] + ", ");
            }
            System.out.println("");
            System.out.println("Ja, här ser man att början på byteArrayen inte är likadana... men slutet är ?!");
            // --- --- --- --- ---



            Crypto crypto = new Crypto(main);
            String imageContentAsStringEncrypted = crypto.encrypt( imageContentAsString, privateKey);

            //PrintStream out = new PrintStream(new FileOutputStream("./src/com/kyh/security/assignment_RSA/keyFiles/testfile.txt"));
            //out.print(imageContentAsStringEncrypted);

        // --------------------------------------
/*
            File file = new File("./src/com/kyh/security/assignment_RSA/keyFiles/testfile.txt");
            Scanner sc = new Scanner(file);
            String contentFromTextFile = "";
            while (sc.hasNextLine()) {
                contentFromTextFile += sc.nextLine();
            }
 */
            String imageContentAsStringDecrypted = crypto.decrypt(imageContentAsStringEncrypted, publicKey);
            byte[] imageContentAsBytesDecrypted = imageContentAsStringDecrypted.getBytes();



            //BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageContentAsBytesDecrypted) );
            //ImageIO.write(image, "JPG", new File("./src/com/kyh/security/assignmenst_RSA/keyFiles/ring_copy.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        choiceOfMenu = 301;
    }



    int menu601() {
        boolean loop = true;
        int choice = 0;
        if (publicKey == null || privateKey == null){
            return choice;
        }
        border();
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   Loaded key is: " + presentKey + presentKeyType);
        System.out.println("#");
        System.out.println("#   Which key do you want to use?");
        System.out.println("#");
        System.out.println("#   1. PRIVATE");
        System.out.println("#   2. PUBLIC");
        Scanner userInput = new Scanner(System.in);
        while (loop) {
            switch (userInput.nextInt()) {
                case 1 -> {
                    choice = 1;
                    loop = false;
                }
                case 2 -> {
                    choice = 2;
                    loop = false;
                }
                default -> System.out.println("Bad input");
            }
        }
        return choice;
    }

    void menu901() {
        border();
        System.out.println("#");
        System.out.println("#");
        System.out.println("#   Okay... bye bye!");
        System.exit(0);
    }

    void border(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("        ..:: RSA assignment ::..");
        System.out.println("########################################");
    }

}
