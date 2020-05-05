package com.example.test;
public class Card {

    // RANKS
    public final static int ACE = 1;
    public final static int TWO = 2;
    public final static int THREE= 3;
    public final static int FOUR = 4;
    public final static int FIVE = 5;
    public final static int SIX = 6;
    public final static int SEVEN = 7;
    public final static int EIGHT = 8;
    public final static int NINE = 9;
    public final static int TEN = 10;
    public final static int JACK = 11;
    public final static int QUEEN = 12;
    public final static int KING = 13;

    // SUITS
    public final static int DIAMONDS = 1;
    public final static int CLUBS = 2;
    public final static int HEARTS = 3;
    public final static int SPADES = 4;

    // Card properties
    private int rank;
    private int suit;
    private String nameOfCard;

    public Card() {

        int tempRank = (int) Math.floor(Math.random() * 13) + 1;
        this.suit = (int) Math.floor(Math.random() * 4) + 1;

        switch(tempRank) {
            case ACE:
                this.nameOfCard = "aceof";
                this.rank = 11;
                break;
            case TWO:
                this.nameOfCard = "twoof";
                this.rank = 2;
                break;
            case THREE:
                this.nameOfCard = "threeof";
                this.rank = 3;
                break;
            case FOUR:
                this.nameOfCard = "fourof";
                this.rank = 4;
                break;
            case FIVE:
                this.nameOfCard = "fiveof";
                this.rank = 5;
                break;
            case SIX:
                this.nameOfCard = "sixof";
                this.rank = 6;
                break;
            case SEVEN:
                this.nameOfCard = "sevenof";
                this.rank = 7;
                break;
            case EIGHT:
                this.nameOfCard = "eightof";
                this.rank = 8;
                break;
            case NINE:
                this.nameOfCard = "nineof";
                this.rank = 9;
                break;
            case TEN:
                this.nameOfCard = "tenof";
                this.rank = 10;
                break;
            case JACK:
                this.nameOfCard = "jackof";
                this.rank = 10;
                break;
            case QUEEN:
                this.nameOfCard = "queenof";
                this.rank = 10;
                break;
            case KING:
                this.nameOfCard = "kingof";
                this.rank = 10;
                break;
            default:
                break;
        }

        switch(this.suit) {
            case DIAMONDS:
                this.nameOfCard = this.nameOfCard.concat("diamonds");
                break;
            case CLUBS:
                this.nameOfCard = this.nameOfCard.concat("clubs");
                break;
            case HEARTS:
                this.nameOfCard = this.nameOfCard.concat("hearts");
                break;
            case SPADES:
                this.nameOfCard = this.nameOfCard.concat("spades");
                break;
            default:
                break;
        }
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return nameOfCard;
    }
}
