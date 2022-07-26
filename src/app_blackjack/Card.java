package app_blackjack;

public class Card {
    //Symbol Karten
    public static int pik = 0;
    public static int herz = 1;
    public static int karo = 2;
    public static int kreuz = 3;

    //Face Karten
    public static int ass = 1;
    public static int zwei = 2;
    public static int drei = 3;
    public static int vier = 4;
    public static int fuenf = 5;
    public static int sechs = 6;
    public static int sieben = 7;
    public static int acht = 8;
    public static int neun = 9;
    public static int zehn = 10;
    public static int bube = 11;
    public static int dame = 12;
    public static int koenig = 13;

    int symbol;
    int face;
    private boolean faceUp;
    private Spieler spieler;

    public Card(int symbol, int face){
        this.symbol = symbol;
        this.face = face;
        this.faceUp = false;
    }

    public int getSymbol() {
        return symbol;
    }

    public int getFace() {
        return face;
    }

    //Zehn, Bube, Dame, Koenig sind immer 10 Punkte wert
    public int getValue(){
        if(getFace() > 9){
            return 10;
        }
        return getFace();
    }

    //Festlegung des Ass Wertes
    public int getAssValue(int ace){
        if(getFace() == 1 && getValue() + spieler.getSpielerScore() > 21){
            return 1;
        }else{
            return 11;
        }
    }

    //Entscheidet ob Karte aufgedeckt ist
    public boolean istFaceUp()
    {
        return this.faceUp;
    }

    //Entscheidet ob Karte nicht aufgedeckt ist
    public boolean istFaceDown()
    {
        return !(this.faceUp);
    }

    public void turnFaceUp()
    {
        this.faceUp = true;
    }

    public void turnFaceDown()
    {
        this.faceUp = false;
    }


    //Namenbezeichnung der Karten
    public String bezeichnungDerKarten(){
        String vollerKartenName = "";
        String symbolName = "";
        String faceName = "";

        //Symbol Karten
        if(symbol == 0){
            symbolName = "Pik";
        }
        if(symbol == 1){
            symbolName = "Herz";
        }
        if(symbol == 2){
            symbolName = "Karo";
        }
        if(symbol == 3){
            symbolName = "Kreuz";
        }

        //Face Karten
        if(face == 1 || face > 10){
            if(face == 1){
                faceName = "Ass";
            }
            if(face == 11){
                faceName = "Bube";
            }
            if(face == 12){
                faceName = "Dame";
            }
            if(face == 13){
                faceName = "Koenig";
            }
            vollerKartenName = symbolName + " " + faceName;
        } else if (face != 1 && face <=10) {
            vollerKartenName = symbolName + " " +  face;
        }

        return vollerKartenName;
    }
}
