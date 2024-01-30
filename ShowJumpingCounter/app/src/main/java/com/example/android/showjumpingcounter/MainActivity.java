package com.example.android.showjumpingcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.os.SystemClock;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int penaltyRiderA;
    int penaltyRiderB;
    int refusalRiderA;
    int refusalRiderB;

    Button startRiderA, stopRiderA, startRiderB, stopRiderB, restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Chronometer section
        //Rider A
        Chronometer chronometerRiderA = (Chronometer) findViewById(R.id.timeRiderA);
        startRiderA = (Button) findViewById(R.id.start_time_RiderA);
        stopRiderA = (Button) findViewById(R.id.stop_time_RiderA);
        //Rider B
        Chronometer chronometerRiderB = (Chronometer) findViewById(R.id.timeRiderB);
        startRiderB = (Button) findViewById(R.id.start_time_RiderB);
        stopRiderB = (Button) findViewById(R.id.stop_time_RiderB);
        //reset penalties and time for both riders
        restart = (Button) findViewById(R.id.reset);

        //Rider A chronometer actions
        // perform click  event on start button to start a chronometer
        startRiderA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometerRiderA.setBase(SystemClock.elapsedRealtime());
                chronometerRiderA.start();
            }
        });
        // perform click  event on stop button to stop the chronometer
        stopRiderA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometerRiderA.stop();
            }
        });

        //Rider B chronometer actions
        // perform click  event on start button to start a chronometer
        startRiderB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometerRiderB.setBase(SystemClock.elapsedRealtime());
                chronometerRiderB.start();
            }
        });
        // perform click  event on stop button to stop the chronometer
        stopRiderB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometerRiderB.stop();
            }
        });

        // perform click  event on restart button to set the base time on chronometer. Also, resets penalties
        // for both Rider A and B
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penaltyRiderA = 0;
                penaltyRiderB = 0;
                refusalRiderA = 0;
                refusalRiderB = 0;
                displayForRiderA(penaltyRiderA);
                displayForRiderB(penaltyRiderB);
                chronometerRiderA.setBase(SystemClock.elapsedRealtime());
                chronometerRiderA.stop();
                chronometerRiderB.setBase(SystemClock.elapsedRealtime());
                chronometerRiderB.stop();
            }
        });


    }

    /**
     * Rider A section.
     * <p>
     * Displays the given penalties for the rider.
     */
    public void displayForRiderA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.rider_a_penalty);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the elimination for the rider.
     */
    public void displayEliminForA(String elim) {
        TextView scoreView = (TextView) findViewById(R.id.rider_a_penalty);
        scoreView.setText(elim);
    }

    /**
     * If the rider is eliminated, no penalties are added. Otherwise 4 penalties are added for each barrel hit
     */
    public void knockdownForA(View view) {
        if (refusalRiderA >= 2) {
        } else {
            penaltyRiderA += 4;
            displayForRiderA(penaltyRiderA);
        }
    }

    /**
     * The first refusal counts 4 penalties, the second implies elimination
     */
    public void refusalForA(View view) {
        if (refusalRiderA == 0) {
            penaltyRiderA += 4;
            refusalRiderA++;
            displayForRiderA(penaltyRiderA);
        } else {
            refusalRiderA++;
            displayEliminForA("Elim");
        }
    }

    /**
     * Reset penalties.
     */
    public void retiredForA(View view) {
        refusalRiderA = 2;
        displayEliminForA("Elim");
    }

    /**
     * Rider B section. Methods are similar to Rider A's.
     */

    public void displayForRiderB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.rider_b_penalty);
        scoreView.setText(String.valueOf(score));
    }

    public void displayEliminForB(String elim) {
        TextView scoreView = (TextView) findViewById(R.id.rider_b_penalty);
        scoreView.setText(elim);
    }

    public void knockdownForB(View view) {
        if (refusalRiderB >= 2) {
        } else {
            penaltyRiderB += 4;
            displayForRiderB(penaltyRiderB);
        }
    }

    public void refusalForB(View view) {
        if (refusalRiderB == 0) {
            penaltyRiderB += 4;
            refusalRiderB++;
            displayForRiderB(penaltyRiderB);
        } else {
            refusalRiderB++;
            displayEliminForB("Elim");
        }
    }

    public void retiredForB(View view) {
        refusalRiderB = 2;
        displayEliminForB("Elim");
    }

}