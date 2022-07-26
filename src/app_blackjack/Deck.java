package app_blackjack;

public class Deck {
    private Card[] deck;

    //Deck mit 52 Karten wird erstellt
    public Deck(){
        int position = 0;
        deck = new Card[52];

        for (int i = 0; i < 4; i++){
            for (int j = 1; j < 14; j++){
                Card temp = new Card(i, j);
                deck[position] = temp;
                position++;
            }
        }
    }

    //nimmt die erste Karte vom Deck
    public Card deal()
    {
        Card dealCard = null;

        for(int i = 0; i < deck.length; i++){
            if(deck[i] != null){
                dealCard = deck[i];
                deck[i] = null;
                break;
            }
        }

        return dealCard;
    }

    //Berrechnung der Deck Größe
    public int getDeckGroesse()
    {
        int deckGroesse = 0;

        for(int i = 0; i < deck.length; i++){
            if(deck[i] != null){
                deckGroesse++;
            }
        }

        return deckGroesse;
    }

    //Überprüfung ob Deck leer ist
    public boolean istLeer()
    {
        if(getDeckGroesse() == 0){
            return true;
        }

        return false;

    }

    //Deck wird gemischt
    public void shuffle()
    {
        int index = 0;
        Card temp = null;

        for (int i = 0; i < deck.length; i++){
            index = (int)(Math.random()*(deck.length));
            temp = deck[index];
            deck[index] = deck[i];
            deck[i] = temp;
        }
    }
}

