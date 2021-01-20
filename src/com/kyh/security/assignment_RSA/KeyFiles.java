package com.kyh.security.assignment_RSA;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class KeyFiles {
    Main main;
    KeyFiles(Main main){
        this.main = main;
    }

    void saveKey(String fileName, KeyPair key){
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(key);
            out.close();
            System.out.println("saved key as: " + fileName);

        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    KeyPair readKey(String fileName){
        KeyPair key = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            key = (KeyPair) in.readObject();
            in.close();
            System.out.println("read key from: " + fileName);
        } catch (IOException | ClassNotFoundException i){
            i.printStackTrace();
        }
        return key;
    }

    public ArrayList useSavedKeys() {

        File folder = new File(main.getFolder());
        ArrayList<String> uniqueFiles = new ArrayList<>();
        
        try {
            if (folder.isDirectory()) {
                File[] listOfFiles = folder.listFiles();
                if (listOfFiles != null && listOfFiles.length > 0) {
                    for (File elements : listOfFiles) {
                        if (elements.isFile() && elements.canRead()) {
                            String fName = elements.getName().substring(0, elements.getName().length() - 8);
                            if (!uniqueFiles.contains(fName)) {
                                uniqueFiles.add(fName);
                            }
                        }
                    }
                }
            }

                /*



                    //System.out.println("#   You have the following keys saved:");

                    for (File fileName : listOfFiles) {
                        if (fileName.isFile() && fileName.canRead()) {
                            System.out.println("#   " + fileName.getName());
                        }
                    }

                    System.out.println("#");
                    System.out.println("#   Would you like to use any of these?");
                    System.out.println("#   1. YES");
                    System.out.println("#   2. NO");
                    Scanner sc = new Scanner(System.in);
                    while (sc.hasNextInt()) {
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                return uniqueFiles;
                            case 2:
                                uniqueFiles.clear();
                                return uniqueFiles;
                            default:
                                System.out.println("Bad input");
                        }
                    }
                }
            }

                 */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueFiles;
    }


    String getKeyNameToUse(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String nextLine = sc.nextLine();
            if (!nextLine.isEmpty()) {
                return nextLine.toLowerCase();
            }
        }
        return "";
    }


}
