package com.kyh.security.assignment_RSA;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    private final int bitLength = 4096;
    private final String folder = "./src/com/kyh/security/assignment_RSA/keyFiles/";

    public int getBitLength() { return bitLength; }
    public String getFolder() { return folder; }


    Main(){
/*
        byte[] byteString = "hello world".getBytes(StandardCharsets.UTF_8);


        try
        {
            //skriva till
            FileOutputStream utfil = new FileOutputStream("bytefil.dat");
            utfil.write(byteString);
            utfil.close();





            //läsa in
            FileInputStream infil = new FileInputStream("bytefil.dat");
            byte[] test  = infil.readAllBytes();
            String myStr = new String(test, StandardCharsets.UTF_8);
            infil.close();
            System.out.println(myStr);
        }
        catch(Exception ex) { System.out.println("Fel! " + ex); }




 */


        Menu menu = new Menu(this);
        menu.whereToNavigate();
    }

    public static void main(String[] args) { new Main(); }


}