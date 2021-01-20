package com.kyh.security.assignment_RSA;

public class Main {
    private final int bitLength = 4096;
    private final String folder = "./src/com/kyh/security/menu/keyFiles/";

    public int getBitLength() { return bitLength; }
    public String getFolder() { return folder; }



    Main(){
        System.out.println("\n\n");
        System.out.println("    ..:: RSA assignment ::..    ");
        System.out.println("########################################");

        Menu menu = new Menu(this);
        menu.whereToNavigate();
    }

    public static void main(String[] args) { new Main(); }

}