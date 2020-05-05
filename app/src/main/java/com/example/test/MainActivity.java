package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // log of player and dealer's hand
        List<Card> pHand = new ArrayList<Card>();
        List<Card> dHand = new ArrayList<Card>();

        // initialize the parts of the app for manipulation
        Button deal = findViewById(R.id.deal);
        Button stand = findViewById(R.id.stand);
        Button hit = findViewById(R.id.hit);
        Button ok = findViewById(R.id.ok);

        ImageView card1 = findViewById(R.id.card1);
        ImageView card2 = findViewById(R.id.card2);
        ImageView card3 = findViewById(R.id.card3);

        ImageView dCard1 = findViewById(R.id.dCard1);
        ImageView dCard2 = findViewById(R.id.dCard2);
        ImageView dCard3 = findViewById(R.id.dCard3);

        TextView dialog = findViewById(R.id.dialog);

        // initial conditions
        deal.setVisibility(View.VISIBLE);
        stand.setVisibility(View.GONE);
        hit.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);
        card1.setVisibility(View.GONE);
        card2.setVisibility(View.GONE);
        card3.setVisibility(View.GONE);
        dCard1.setVisibility(View.GONE);
        dCard2.setVisibility(View.GONE);
        dCard3.setVisibility(View.GONE);
        dialog.setVisibility(View.VISIBLE);

         deal.setOnClickListener(v -> {
             // modifies screen
             deal.setVisibility(View.GONE);
             stand.setVisibility(View.VISIBLE);
             hit.setVisibility(View.VISIBLE);

             // player's cards
             Card first = new Card();
             Card second = new Card();

             pHand.add(first);
             pHand.add(second);

             // sets text and images
             card1.setImageResource(getResources().getIdentifier(first.getName(), "drawable", getPackageName()));
             card2.setImageResource(getResources().getIdentifier(second.getName(), "drawable", getPackageName()));

             card1.setVisibility(View.VISIBLE);
             card2.setVisibility(View.VISIBLE);

             // dealer's cards
             Card dFirst = new Card();
             Card dSecond = new Card();

             dHand.add(dFirst);
             dHand.add(dSecond);

             dCard1.setImageResource(getResources().getIdentifier(dFirst.getName(), "drawable", getPackageName()));
             dCard2.setImageResource(getResources().getIdentifier("cardback", "drawable", getPackageName()));

             dCard1.setVisibility(View.VISIBLE);
             dCard2.setVisibility(View.VISIBLE);
         });

         stand.setOnClickListener(v -> {
             // hides buttons so dealer can do stuff
             stand.setVisibility(View.GONE);
             hit.setVisibility(View.GONE);

             // reveal hidden card
             dCard2.setImageResource(getResources().getIdentifier(dHand.get(1).getName(), "drawable", getPackageName()));

             // decides if they want to add
             boolean canHit = true;

             while (canHit) {
                 Card toAdd = new Card();
                 dHand.add(toAdd);

                 dCard3.setImageResource(getResources().getIdentifier(dHand.get(dHand.size() - 1).getName(), "drawable", getPackageName()));
                 dCard2.setImageResource(getResources().getIdentifier(dHand.get(dHand.size() - 2).getName(), "drawable", getPackageName()));
                 dCard1.setImageResource(getResources().getIdentifier(dHand.get(dHand.size() - 3).getName(), "drawable", getPackageName()));

                 dCard3.setVisibility(View.VISIBLE);
                 int sum = 0;
                 for (int i = 0; i < dHand.size(); i++) {
                     sum += dHand.get(i).getRank();
                     if (dHand.get(i).getRank() == 11) {
                     }
                 }

                 if (sum > 16) {
                     canHit = false;
                 }
             }

             // tallies hands
             int pSum = 0;
             int pAces = 0;
             for (int i = 0; i < pHand.size(); i++) {
                 pSum += pHand.get(i).getRank();
                 if (pHand.get(i).getRank() == 11) {
                     pAces++;
                 }
             }
             if (pSum > 21 && pAces > 0) {
                 pSum -= pAces * 10;
             }

             int dSum = 0;
             int dAces = 0;
             for (int i = 0; i < dHand.size(); i++) {
                 dSum += dHand.get(i).getRank();
                 if (dHand.get(i).getRank() == 11) {
                     dAces++;
                 }
             }
             if (dSum > 21 && dAces > 0) {
                 dSum -= pAces * 10;
             }

             // Compares hands
             if (dSum > 21) {
                 dialog.setText("Dealer busted. Player wins!");
             } else if (pSum > dSum) {
                 dialog.setText("Player wins!");
             } else {
                 dialog.setText("Dealer wins!");
             }

             ok.setVisibility(View.VISIBLE);
         });

        hit.setOnClickListener(v -> {
            // adds new card
            Card added = new Card();
            pHand.add(added);

            // updates to top 3 most recent cards
            card3.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 1).getName(), "drawable", getPackageName()));
            card2.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 2).getName(), "drawable", getPackageName()));
            card1.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 3).getName(), "drawable", getPackageName()));

            // displays third card
            card3.setVisibility(View.VISIBLE);

            // sum of cards in hand
            int sum = 0;
            int aceCtr = 0;
            for (int i = 0; i < pHand.size(); i++) {
                sum += pHand.get(i).getRank();
                if (pHand.get(i).getRank() == 11) {
                    aceCtr++;
                }
            }

            // checks if the player busts
            if (sum > 21 && aceCtr > 0) {
                sum -= aceCtr * 10;
            }

            // checks for bust
            if (sum > 21) {
                // sets dialog to "BUST"
                dialog.setText("Player busted. Dealer Wins!");

                // reveals ok button
                ok.setVisibility(View.VISIBLE);
                stand.setVisibility(View.GONE);
                hit.setVisibility(View.GONE);
            }
        });

        ok.setOnClickListener(v -> {
            // resets to initial game state
            dialog.setText("Blackjack");
            pHand.clear();
            dHand.clear();

            // resets visibility
            deal.setVisibility(View.VISIBLE);
            ok.setVisibility(View.GONE);
            card1.setVisibility(View.GONE);
            card2.setVisibility(View.GONE);
            card3.setVisibility(View.GONE);
            dCard1.setVisibility(View.GONE);
            dCard2.setVisibility(View.GONE);
            dCard3.setVisibility(View.GONE);
        });
    }
}
