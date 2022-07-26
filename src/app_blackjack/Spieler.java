package app_blackjack;

public class Spieler {
    String nameSpieler;
    double  einsatz;
    double bank;
    int spielerScore;
    int handScore;
    int cardFace1;
    int cardFace2;
    int[] hand;

    public Spieler(String nameSpieler, double bank, double einsatz, int spielerScore){
        this.nameSpieler = nameSpieler;
        this.bank = bank;
        this.einsatz = einsatz;
        this.spielerScore = spielerScore;

        this.hand = new int[52];
    }

    public String getNameSpieler(){
        return nameSpieler;
    }

    public void setBank(double bank) {
        this.bank = bank;
    }

    public double getBank() {
        return bank;
    }

    public void setEinsatz(double einsatz) {
        this.einsatz = einsatz;
    }

    public double getEinsatz() {
        return einsatz;
    }

    public void setSpielerScore(int spielerScore) {
        this.spielerScore = spielerScore;
    }
    public int getSpielerScore() {
        return spielerScore;
    }

    public void setHandScore(int handScore) {
        this.handScore = handScore;
    }

    public int getHandScore() {
        return handScore;
    }

    public void setFace1(int cardFace1) {
        this.cardFace1 = cardFace1;
    }

    public void setFace2(int cardFace2) {
        this.cardFace2 = cardFace2;
    }

    public void setHand(int z, int cardValue) {
        this.hand[z] = cardValue;
    }

    public int[] getHand() {
        return hand;
    }

}
