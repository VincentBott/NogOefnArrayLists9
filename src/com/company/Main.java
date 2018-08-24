package com.company;

import java.io.*;


public class Main {

    public static void main(String[] args) {


        Bank bank = new Bank();

        try {

            bank.addRekening(new BankRekening("000-0000011-11"));
            bank.addRekening(new BankRekening("000-0000022-22"));
            bank.addRekening(new BankRekening("000-0000033-33"));
            bank.addRekening(new BankRekening("000-0000011-11"));
        } catch (IllegalArgumentException ex){

            System.out.println(ex.getMessage());
        }

        toonRekeningen(bank);

        System.out.println("We storten 1000 EUR op rekening 000-0000011-11");
        bank.stortGeld("000-0000011-11", 1000);
        toonRekeningen(bank);

        try {

            System.out.println("We proberen 1000 EUR te storten op rekening 000-0000044-44");
            bank.stortGeld("000-0000044-44", 1000);
        } catch (IllegalArgumentException ex){

            System.out.println(ex.getMessage());
        }

        toonRekeningen(bank);

        System.out.println("We schrijven 500 EUR over van 000-0000011-11 naar 000-0000022-22");
        bank.schrijfGeldOver("000-0000011-11", "000-0000022-22", 500);
        toonRekeningen(bank);

        System.out.println("We proberen 1000 EUR over te schrijven van 000-0000011-11 naar 000-0000022-22");

        try {

            bank.schrijfGeldOver("000-0000011-11", "000-0000022-22", 1000);

        } catch (IllegalArgumentException ex){

            System.out.println(ex.getMessage());
        }

        toonRekeningen(bank);

        schrijfbestand("text.html", bank);
    }


    private static void toonRekeningen(Bank bank) {

        for(String rekeningnummer:bank.getRekeningnummers()){

            BankRekening br = bank.getRekening(rekeningnummer);
            System.out.printf("Op rekening %s staat %.2f EURO%n", br.getRekeningnummer(), br.getSaldo());
        }
    }

    private static void schrijfbestand(String bestandsnaam, Bank bank)  {


        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(bestandsnaam))))

        {

            pw.print("<html><body><table>\n"
                    + "<thead>\n"
                    + "<tr><th>Rekeningnummer</th><th>Saldo</th></tr>\n"
                    + "</thead>\n"
                    + "<tbody>\n");


            for ( String rekeningnummer : bank.getRekeningnummers()){

                BankRekening br = bank.getRekening(rekeningnummer);

                pw.printf("<tr><td>%s</td><td>%.2f</td></tr>\n", br.getRekeningnummer(), br.getSaldo());
            }

            pw.print("</tbody>\n" +
                    "</table>\n" +
                    "</body></html>");

        } catch (IOException e) {

            System.out.println("IOException: " +  e.getMessage());
        }

    }
}

/*
<html><body><table>
<thead>
<tr><th>Rekeningnummer</th><th>saldo</th></tr>
</thead>
<tbody>
<tr><td>000-0000011-11</td><td>500.0</td></tr>
<tr><td>000-0000022-22</td><td>500.0</td></tr>
<tr><td>000-0000033-33</td><td>0.0</td></tr>
</tbody>
</table>
</body></html>
 */