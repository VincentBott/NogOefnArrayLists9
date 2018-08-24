package com.company;


import java.util.ArrayList;


public class Bank {


    private ArrayList <BankRekening> rekeningen = new ArrayList<>();



    public void addRekening(BankRekening rek)  {


/*         Eigen versie hieronder. Werkt ook.

        for (BankRekening rekfor: rekeningen) {

            if (rekfor.getRekeningnummer().equals(rek.getRekeningnummer())) {

                String foutmelding = "Rekening " + rek.getRekeningnummer() + " is niet uniek.";

                throw new IllegalArgumentException(foutmelding);
            }

         }

        rekeningen.add(rek);

*/

        if (bestaatRekeningnummer(rek.getRekeningnummer())) {

            String foutmelding = "Rekening " + rek.getRekeningnummer() + " is niet uniek.";

            throw new IllegalArgumentException(foutmelding);   //  Na een throw stopt de functie en wordt de code die volgt, niet meer uitgevoerd.



            //       } else          // Deze else is eigenlijk niet nodig omwille van de throw.

        }
            rekeningen.add(rek);

    }


//   De onderstaande functie is voor de code leesbaarder te maken.


    private boolean bestaatRekeningnummer(String rekeningnummer) {

        if (getRekening(rekeningnummer) != null) {
            return true;
        }
        else
            return false;
    }


    public ArrayList<String> getRekeningnummers() {

        ArrayList <String> rekeningnummers = new ArrayList<>();

        for(BankRekening rek: rekeningen) {

            String rekeningnummer = rek.getRekeningnummer();

            rekeningnummers.add(rekeningnummer);

        }

        return rekeningnummers;
    }


    public BankRekening getRekening(String rekeningnummer) {


        for(BankRekening rek: rekeningen) {

            if (rekeningnummer.equals(rek.getRekeningnummer()))
                return rek;
        }

        return null;
    }

    public void stortGeld(String rekeningnummer, int bedrag) {

        if (bestaatRekeningnummer(rekeningnummer)) {
            BankRekening rekening = getRekening(rekeningnummer);
            rekening.storten(bedrag);

        } else
            {

            /* De onderstaande manier kan ook met Strings.

            String foutmelding = "Rekening " + rekeningnummer + " bestaat niet.";

            throw new IllegalArgumentException(foutmelding);
            */

            throw new IllegalArgumentException(String.format("Rekening %s bestaat niet.", rekeningnummer));
        }
    }


    public void schrijfGeldOver(String rekeningnummer1, String rekeningnummer2, int bedrag) {

        BankRekening BankRekening1 = getRekening(rekeningnummer1);

        BankRekening BankRekening2 = getRekening(rekeningnummer2);


        if (bedrag > BankRekening1.getSaldo())
            throw new IllegalArgumentException("Saldo kan niet negatief worden.");


        BankRekening1.afhalen(bedrag);

        BankRekening2.storten(bedrag);
    }

}
