package app_blackjack;

import java.util.Objects;
import java.util.Scanner;


public class Blackjack  {

    static Scanner scannerBlackjack;

    public static void main(String[] args) {
        //Willkommensfunktion wird aufgerufen
        Welcome.welcome();
    }

    /*
    ###############################################################################################################################################################
    Teilnehmeranzahl bestimmen
    ###############################################################################################################################################################
    */

    public static void spiel() {
        System.out.println("Wie viele Spieler sollen teilnehmen? (1-5)");
        scannerBlackjack = new Scanner(System.in);
        int numPlayers = getUserInput();

        //Array Anzahl der Spieler, die am Spiel teilnehmen
        Spieler[] spieler = new Spieler[numPlayers];

        //Gesamtscore der Spieler und des Dealers
        int spielerScore = 0;
        int dealerScore = 0;

        numPlayers = numPlayers + 1;

        //Eingabe der Spielerdaten
        for(int i = 1; i < numPlayers; i++) {
            System.out.println("Gib den Vornamen von Spieler " + i + " ein:");
            scannerBlackjack = new Scanner(System.in);
            String nameSpieler = scannerBlackjack.next();
            System.out.print("Spieler " + i + " (" + nameSpieler + ")" + "s Startguthaben: ");
            double bank = 250;
            System.out.println(bank + " Euro");
            System.out.println("Spieler " + i + " (" + nameSpieler + ")" + ", setze dein Einsatz: ");
            scannerBlackjack = new Scanner(System.in);
            double einsatz = Double.parseDouble(scannerBlackjack.next());

            while (einsatz > bank || einsatz < 1) {
                if (einsatz > bank) {
                    System.out.println("Du kannst nicht mehr setzen als du besitzt.");

                }
                if (einsatz < 1) {
                    System.out.println("Du musst mindestens 1 Euro setzen.");

                }
                System.out.println("Spieler " + i + " (" + nameSpieler + ")" + ", setze dein Einsatz: ");
                scannerBlackjack = new Scanner(System.in);
                einsatz = Double.parseDouble(scannerBlackjack.next());
            }

            //Eingaben werden als Objekt Spieler im Array gespeichert
            spieler[i-1] = new Spieler(nameSpieler, bank, einsatz, spielerScore);

        }

        //Dealer Informationen
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("=============================================");
        System.out.println("Der heutige Dealer ist...");
        String dealerName = "OX-Dealer";
        System.out.println("Name: " + dealerName);
        Dealer dealer = new Dealer(dealerName, dealerScore);
        System.out.println();
        System.out.println("Moege das Spiel beginnen!!!");;
        System.out.println("=============================================");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");

        /*
        ###############################################################################################################################################################
        Deck vorbereiten
        ###############################################################################################################################################################
         */

        //Deck wird gemischt
        Deck deck = new Deck();
        deck.shuffle();

        //Wert von Karte 1 und 2
        int cardFace1 = 0;
        int cardFace2 = 0;

        /*
        ###############################################################################################################################################################
        Zuteilung 2 Karten an Spieler
        ###############################################################################################################################################################
         */

        for(int i = 0; i < spieler.length; i++){
            //Handscore wird auf 0 gesetzt
            int handScore = 0;
            spieler[i].setHandScore(handScore);
            String name = spieler[i].getNameSpieler();

            System.out.println("Spieler " + (i+1) + " (" + name + "):");
            System.out.println();

            //Spieler bekommt 2 Karten zum Start
            for(int j = 0; j < 2; j++){
                int handScore2 = spieler[i].getHandScore();
                Card card = deck.deal();

                //Erste Karte
                if(j == 0){
                    cardFace1 = card.getFace();
                    spieler[i].setFace1(cardFace1);
                }

                //Zweite Karte
                if(j == 1){
                    cardFace2 = card.getFace();
                    spieler[i].setFace2(cardFace2);
                }

                //Wert vom Ass wird definiert
                int cardValue = card.getValue();
                if(cardValue == 1){
                    if(handScore2 + (cardValue + 10) <= 21){
                        cardValue = 11;
                    }else {
                        cardValue = 1;
                    }
                }

                //Setzt Karten in die Hand des Spielers
                spieler[i].setHand(j, cardValue);

                //Voller Kartenname wird geprintet und der Handscore wird gesetzt
                String fullCardSpieler = card.bezeichnungDerKarten();
                System.out.println("      " + fullCardSpieler);
                handScore = cardValue + handScore2;
                spieler[i].setHandScore(handScore);
            }

            System.out.println();
            System.out.println("Score = " + handScore);
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
        }

        /*
        ###############################################################################################################################################################
        Zuteilung 2 Karten an Dealer
        ###############################################################################################################################################################
         */

        //Dealer werden 2 Karten zugeteilt
        //Karte 1
        System.out.println(dealerName + ":");
        System.out.println();
        Card dealerCard1 = deck.deal();
        int cardValue1 = dealerCard1.getValue();

        //Ass Wert wird bestimmt
        if(cardValue1 == 1){
            if(dealerScore + (cardValue1 + 10) <= 21){
                cardValue1 = 11;
            }else {
                cardValue1 = 1;
            }
        }

        //Hand für die erste Karte wird gesetzt
        dealer.setHand(0, cardValue1);
        dealerScore = cardValue1;
        String fullCardDealer1 = dealerCard1.bezeichnungDerKarten();
        System.out.println("      " + fullCardDealer1);

        //Karte 2
        Card dealerCard2 = deck.deal();
        int cardValue2 = dealerCard2.getValue();

        if(cardValue2 == 1){
            if(cardValue1 + (cardValue2 + 10) <= 21){
                cardValue2 = 11;
            }else {
                cardValue2 = 1;
            }
        }

        dealer.setHand(1, cardValue2);
        String dealerFullCard2 = dealerCard2.bezeichnungDerKarten();
        if(dealerCard2.istFaceDown() == true){
            System.out.println("      " + "Eine verdeckte Karte");
        }

        dealerScore = cardValue1 + cardValue2;
        dealer.setDealerScore(dealerScore);
        System.out.println();

        if(cardValue1 == 1 | cardValue1 == 11){
            System.out.println("Score: " + "1 or 11" + " + (Card 2)");
        }else {
            System.out.println("Score: " + cardValue1 + " + (Card 2)");
        }

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");

        /*
        ###############################################################################################################################################################
        Weiterspielen Spieler
        ###############################################################################################################################################################
         */

        for(int i = 0; i < spieler.length; i++){
            while(spieler[i].getHandScore() < 21){

                System.out.println("Spieler " + (i + 1) + " (" + spieler[i].getNameSpieler() + ") | " + "Aktueller Score: " + spieler[i].handScore);
                System.out.println("Noch eine Karte ziehen? y | n ");
                scannerBlackjack = new Scanner(System.in);
                String eingabeHit = scannerBlackjack.next();

                if(Objects.equals(eingabeHit, "y")) {
                    int spielerScoreTotal;
                    int arrayCount = 2;

                    // Spieler zieht eine Karte
                    System.out.println("      " + "Spieler " + (i + 1) + " (" + spieler[i].getNameSpieler() + ") zieht eine Karte.");
                    int score = spieler[i].getHandScore();
                    Card card = deck.deal();
                    int cardValue = card.getValue();
                    String fullCard = card.bezeichnungDerKarten();
                    System.out.println("      " + "Karte gezogen: " + fullCard);

                    // Ass Wert wird berrechnet
                    if (cardValue == 1) {
                        if (score + (cardValue + 10) <= 21) {
                            cardValue = 11;
                        } else {
                            cardValue = 1;
                        }
                    }

                    // Kartenwert wird zum Score hinzugefügt
                    spielerScoreTotal = (cardValue + score);

                    // Wenn Ass in der Hand vorhanden, wird Ass gegebenenfalls geändert
                    if (score + cardValue > 21) {
                        for (int a = 0; a < spieler[i].getHand().length; a++) {
                            if (spieler[i].getHand()[a] == 11) {
                                spieler[i].getHand()[a] = 1;

                                spielerScoreTotal = ((score + cardValue) - 10);
                                spieler[i].setHandScore(spielerScoreTotal);
                            }
                        }
                    }

                    // Karten werden der Hand hinzugefügt
                    spieler[i].setHand(arrayCount, cardValue);
                    arrayCount++;

                    // Spieler überkauft -> Score auf 0
                    if (spielerScoreTotal > 21) {
                        int scoreUeberkauft = 0;
                        spieler[i].setHandScore(scoreUeberkauft);
                        System.out.println("      " + "Spieler " + (i + 1) + " hat sich ueberkauft." + " [[Score: " + spielerScoreTotal + "]]");
                        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                        break;
                    }

                    // Spieler bekommt Blackjack
                    if (spielerScoreTotal == 21) {
                        spieler[i].setHandScore(spielerScoreTotal);
                        System.out.println("      " + "Spieler " + (i + 1) + " hat ein Blackjack." + " [[Score: " + spielerScoreTotal + "]]");
                        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                        break;
                    }

                    // Handscore wird überschrieben
                    spieler[i].setHandScore(spielerScoreTotal);

                    // Score von Spieler wird geprintet wenn scoreTotal < 21 ist
                    System.out.println("      " + spieler[i].getNameSpieler() + ", dein aktueller Score ist: " + spielerScoreTotal);
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");

                }
                else if(Objects.equals(eingabeHit, "n")) { // Wenn Spieler bleibt
                    System.out.println("Es wird keine Karte mehr gezogen.");
                    System.out.println("Der Score bleibt bei: " + spieler[i].getHandScore());
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    break;
                }
                else{
                    System.out.println("Eingabe nicht gueltig. Versuche erneut.");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                }

            }

        }

        /*
        ###############################################################################################################################################################
        Weiterspielen Dealer
        ###############################################################################################################################################################
         */

        //Dealers Zug
        int dealerScoreTotal = 0;
        boolean dealerEntscheidung;

        while(dealerScoreTotal < 21) {
            System.out.println("Dealer: " + dealerName + " | " + "Aktueller Score: " + dealer.dealerScore);
            System.out.println("Noch eine Karte ziehen? y | n ");
            System.out.println();

            if(dealer.dealerScore <= 17 || dealerScoreTotal <= 17){
                dealerEntscheidung = true;
            }else{
                dealerEntscheidung = false;
            }

            // true = Er zieht eine
            if (dealerEntscheidung) {
                int arrayCount = 2;

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("      " + "Dealer (" + dealerName + ") zieht eine Karte.");
                Card dealerCard3 = deck.deal();
                int cardValue3 = dealerCard3.getValue();
                String fullCard = dealerCard3.bezeichnungDerKarten();
                System.out.println("      " + "Karte gezogen: " + fullCard);

                //Ass Wert wird bestimmt
                if (cardValue3 == 1) {
                    if (dealerScore + (cardValue3 + 10) <= 21) {
                        cardValue3 = 11;
                    } else {
                        cardValue3 = 1;
                    }
                }

                dealerScoreTotal = dealerScore + cardValue3;

                if (dealerScoreTotal > 21) {
                    for (int a = 0; a < dealer.hand.length; a++) {
                        if (dealer.hand[a] == 11) {
                            dealer.hand[a] = 1;
                            dealerScoreTotal = ((dealerScore + cardValue3) - 10);
                            dealer.setDealerScore(dealerScoreTotal);
                        }
                    }
                }

                //Hand für die erste Karte wird gesetzt
                dealer.setHand(arrayCount, cardValue3);
                arrayCount++;

                //Dealer überkauft sich
                if (dealerScoreTotal > 21) {
                    int scoreUeberkauft = 0;
                    dealer.setDealerScore(scoreUeberkauft);
                    System.out.println("      " + "Dealer (" + dealerName + ") hat sich ueberkauft." + " [[Score: " + dealerScoreTotal + "]]");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    break;
                }

                // Dealer bekommt Blackjack
                if (dealerScoreTotal == 21) {
                    dealer.setDealerScore(dealerScoreTotal);
                    System.out.println("      " + "Dealer (" + dealerName+ ") hat ein Blackjack." + " [[Score: " + dealerScoreTotal + "]]");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    break;
                }

                dealer.setDealerScore(dealerScoreTotal);

                System.out.println("      " + dealerName + ", dein aktueller Score ist: " + dealerScoreTotal);
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");

            }
            //false = er zieht keine
            if (!dealerEntscheidung) {

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Es wird keine Karte mehr gezogen.");
                System.out.println("Der Score bleibt bei: " + dealerScoreTotal);
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                break;
            }

        }

        /*
        ###############################################################################################################################################################
        Resultat des Spiels
        ###############################################################################################################################################################
         */

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=============================================");
        System.out.println("Resultat des Spiels: ");
        System.out.println("=============================================");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");


        for(int i = 0; i < spieler.length; i++){

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("      " + "Spieler " + (i + 1) + " (" + spieler[i].getNameSpieler() + ") | " + "Endscore: " + spieler[i].getHandScore());
            System.out.println("      " + "Dealer (" + dealerName + ") | " + "Endscore: " + dealer.getDealerScore());

            if (spieler[i].getHandScore() > dealer.getDealerScore()) {
                System.out.println("      " + "Spieler " + (i + 1) + " (" + spieler[i].getNameSpieler() + "), du hast gewonnen!" );

                double einsatzSpieler = spieler[i].getEinsatz();
                double bankSpieler = spieler[i].getBank();
                spieler[i].setBank(bankSpieler + einsatzSpieler);

                System.out.println("      " + "Dein Guthaben wurde aktualisiert (+" + einsatzSpieler + " Euro) | Stand: " + spieler[i].getBank());
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");

            }
            if (spieler[i].getHandScore() < dealer.getDealerScore()) {
                System.out.println("      " + "Spieler " + (i + 1) + " (" + spieler[i].getNameSpieler() + "), du hast verloren!" );

                double einsatzSpieler = spieler[i].getEinsatz();
                double bankSpieler = spieler[i].getBank();
                spieler[i].setBank(bankSpieler - einsatzSpieler);

                System.out.println("      " + "Dein Guthaben wurde aktualisiert (-" + einsatzSpieler + " Euro) | Stand: " + spieler[i].getBank());
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");

            }
            if (spieler[i].getHandScore() == dealer.getDealerScore()) {
                System.out.println("      " + "Spieler " + (i + 1) + " (" + spieler[i].getNameSpieler() + "), das ist ein Unentschieden!" );
                System.out.println("      " + "Dein Guthaben wurde aktualisiert (+/- 0 Euro) | Stand: " + spieler[i].getBank());
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");

            }

        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Das Spiel ist vorbei. Was moechtest du machen?");
        System.out.println("y | Weiterspielen");
        System.out.println("n | Spiel beenden");

        scannerBlackjack = new Scanner(System.in);
        String eingabeSchluss = scannerBlackjack.next();

        switch(eingabeSchluss){
            case "y":
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                break;
            case "n":
                spiel();
                break;
            default:
                System.out.println("Eingabe nicht gueltig. Versuche erneut");

        }

    }

    //Überpürufung der Teilnehmeranzahl Eingabe (Integer erforderlich)
    public static int getUserInput() {
        boolean validNumber = false;
        int userNumber = -1;
        while (!validNumber) {
            try {
                userNumber = scannerBlackjack.nextInt();
                if(userNumber > 0 && userNumber < 6){
                    validNumber = true;
                }else{
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Die Eingabe ist nicht gueltig.");
                System.out.println("Wie viele Spieler sollen teilnehmen? (1-5)");
            } finally {
                scannerBlackjack.nextLine();
            }
        }
        return userNumber;
    }

}