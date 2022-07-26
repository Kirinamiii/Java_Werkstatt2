package app_blackjack;

public class Dealer{
    String dealerName;
    int dealerScore;
    int[] hand;

    public Dealer(String dealerName, int dealerScore){
        this.dealerName = dealerName;
        this.dealerScore = dealerScore;
        this.hand = new int[52];
    }

    public void setHand(int z, int cardValue) {
        this.hand[z] = cardValue;
    }

    public void setDealerScore(int dealerScore){
        this.dealerScore = dealerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }
}
