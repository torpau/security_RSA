package com.kyh.security.assignment_RSA;

import java.math.BigInteger;
import java.security.SecureRandom;

public class KeyGenerator {

    void generateKeys(String fileName, int bitLength){
        SecureRandom rand = new SecureRandom();
        BigInteger p = new BigInteger(bitLength/2, 100, rand);
        BigInteger q = new BigInteger(bitLength/2, 100, rand);
        BigInteger n = p.multiply(q);
        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = new BigInteger("3");
        while(phiN.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        BigInteger d = e.modInverse(phiN);

        KeyPair publicKey = new KeyPair(e, n);
        KeyPair privateKey = new KeyPair(d, n);
        KeyFiles keyFiles = new KeyFiles();
        keyFiles.saveKey(fileName + "_pub.key", publicKey);
        keyFiles.saveKey(fileName + "_pri.key", privateKey);
    }
}
