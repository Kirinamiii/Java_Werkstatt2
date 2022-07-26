package app_blackjack;

import java.util.Scanner;

public class Welcome {

    static Scanner scannerWelcome;

    public static void welcome(){
        System.out.println("=============================================");
        System.out.println("Willkommen bei Blackjack! Was moechtest du machen?");
        System.out.println("=============================================");
        System.out.println("y | Spiel starten");
        System.out.println("r | Regeln oeffnen");
        System.out.println("n | Spiel beenden");

        scannerWelcome = new Scanner(System.in);
        String eingabeWelcome = scannerWelcome.next();

        switch (eingabeWelcome){
            case "y":
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                Blackjack.spiel();
                break;
            case "r":
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                regeln();
                break;
            case "n":
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                spielBeenden();
                break;
            default:
                System.out.println("Die Eingabe ist nicht gueltig.");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                welcome();
        }

    }

    public static void spielBeenden(){
        System.out.println("Das Spiel wird auf Wunsch beendet.");
        System.exit(0);

    }

    public static void regeln(){
        System.out.println("Hier sind die Regeln fuer Blackjack:");
        System.out.println("- Alle Spieler treten gegen den Dealer an. Zu Beginn werden an alle zwei Karten ausgeteilt.");
        System.out.println("- Danach kann jeder entscheiden, ob er eine zusaetzliche Karte erhalten moechte oder nicht.");
        System.out.println("- Um zu gewinnen muessen die Spieler eine hoehere Gesamtsumme als die Dealer haben, ohne die 21 zu ueberschreiten.");
        System.out.println("- Wenn die 21 ueberschritten wird, hat man sich ueberkauft. Andere Optionen waeren genau 21 oder weniger zu haben, solange der Dealer keine hoehere Hand hat.");
        System.out.println("- Ein Blackjack sind genau 21, die aus einer Kombination aus Karten besteht.");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
        welcome();

    }
}
