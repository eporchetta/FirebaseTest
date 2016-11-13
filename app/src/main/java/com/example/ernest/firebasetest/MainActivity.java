package com.example.ernest.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    // getting data from the root.

    private DatabaseReference myRef;
    private TextView tvQuest;
    private TextView tvAsk;
    private RadioButton radioA;
    private RadioButton radioB;
    private RadioButton radioC;
    private RadioButton radioD;
    private Button   btnNext;
    private Button   btnPrev;
    private Button   btnSubmit;
    private HashMap hashData = new HashMap();
    private int questionIndex=1;
    private int numOfQuestions=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRef = FirebaseDatabase.getInstance().getReference("Questions");


        tvQuest = (TextView) findViewById(R.id.tvQuest);
        tvAsk = (TextView) findViewById(R.id.tvAsk);
        radioA = (RadioButton) findViewById(R.id.radioA);
        radioB = (RadioButton) findViewById(R.id.radioB);
        radioC = (RadioButton) findViewById(R.id.radioC);
        radioD = (RadioButton) findViewById(R.id.radioD);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        // Read from the database

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int count=1;
                String mapKey="";

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    mapKey = "Question " + count;
                    hashData.put(mapKey      ,
                            new OnlineExamQuestions(
                                    mapKey ,
                                    data.child("ask").getValue().toString(),
                                    data.child("choiceA").getValue().toString(),
                                    data.child("choiceB").getValue().toString(),
                                    data.child("choiceC").getValue().toString(),
                                    data.child("choiceD").getValue().toString(),
                                    data.child("answer").getValue().toString()
                            )
                    );
                    count++;
                }

                // Initialy display the first question
                if (count>1) {
                    mapKey = "Question "+ questionIndex;
                    OnlineExamQuestions qData = (OnlineExamQuestions) hashData.get(mapKey);

                    tvQuest.setText(mapKey);
                    tvAsk.setText(qData.getAsk());
                    radioA.setText(qData.getChoiceA());
                    radioB.setText(qData.getChoiceB());
                    radioC.setText(qData.getChoiceC());
                    radioD.setText(qData.getChoiceD());
                    numOfQuestions = count-1;
                }
                else
                    System.out.println("HashMap is empty");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }


        });

        //Get the next question and create loop effect.
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                questionIndex++;
                if(questionIndex > numOfQuestions)
                    questionIndex=1;

                OnlineExamQuestions qData = (OnlineExamQuestions) hashData.get("Question "+ questionIndex);

                tvQuest.setText("Question "+ questionIndex);
                tvAsk.setText(qData.getAsk());
                radioA.setText(qData.getChoiceA());
                radioB.setText(qData.getChoiceB());
                radioC.setText(qData.getChoiceC());
                radioD.setText(qData.getChoiceD());

            }
        });

        //Get the next question and create loop effect.
        btnPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                questionIndex--;
                if(questionIndex < 1)
                    questionIndex = numOfQuestions;

                OnlineExamQuestions qData = (OnlineExamQuestions) hashData.get("Question "+ questionIndex);

                tvQuest.setText("Question "+ questionIndex);
                tvAsk.setText(qData.getAsk());
                radioA.setText(qData.getChoiceA());
                radioB.setText(qData.getChoiceB());
                radioC.setText(qData.getChoiceC());
                radioD.setText(qData.getChoiceD());

            }
        });


        //Get the next question and create loop effect.
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
            }
        });




    }
}
