package com.kyh.security.assignment_RSA;

import java.io.*;
import java.util.ArrayList;


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
            //System.out.println("saved key as: " + fileName);

        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    KeyPair readKey(String fileName) throws IOException, ClassNotFoundException{
        KeyPair key = null;
        //try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            key = (KeyPair) in.readObject();
            in.close();
            //System.out.println("read key from: " + fileName);
        //} catch (IOException | ClassNotFoundException i){
         //   i.printStackTrace();
        //}
        return key;
    }

    public ArrayList useSavedKeys() {
        File folder = new File(main.getFolder() + "keyCrypto/");
        ArrayList<String> uniqueFiles = new ArrayList<>();
        
        try {
            if (folder.isDirectory()) {
                File[] listOfFiles = folder.listFiles();
                if (listOfFiles != null && listOfFiles.length > 0) {
                    for (File elements : listOfFiles) {
                        if (elements.isFile() && elements.canRead()) {
                            if (elements.getName().substring(elements.getName().length() - 4).equals(".key")) {
                                String fName = elements.getName().substring(0, elements.getName().length() - 8);
                                if (!uniqueFiles.contains(fName)) {
                                    uniqueFiles.add(fName);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueFiles;
    }

}
