package com.company;



public class BankRekening{

    private String rekeningnummer;
    private double saldo;


    public BankRekening(String rekeningnummer) {
        this.rekeningnummer = rekeningnummer;
        saldo = 0;
    }


    public String getRekeningnummer() {

        return rekeningnummer;
    }


    public double getSaldo() {

        return saldo;
    }


    public void storten(double bedrag){

        saldo += bedrag;
    }

    public void afhalen(double bedrag){

        saldo -= bedrag;
    }
}
