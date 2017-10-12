package com.example.idrisadrien.tp7b2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {
    private Button mBoutonVrai;
    private Button mBoutonFaux;
    private Button mBoutonSuivant;
    private TextView mQuestionTextView;

    private VraiFaux[] mTabQuestions = new VraiFaux[]{
            new VraiFaux(R.string.q_1, true),
            new VraiFaux(R.string.q_2, false),
            new VraiFaux(R.string.q_3, false),
            new VraiFaux(R.string.q_4, true),
            new VraiFaux(R.string.q_5, true),
            new VraiFaux(R.string.q_6, false),
            new VraiFaux(R.string.q_7, true),
            new VraiFaux(R.string.q_8, false),
            new VraiFaux(R.string.q_9, true),
            new VraiFaux(R.string.q_10, false),
    };

    private int indexActuel=0;
    private int point = 0;
//    private String resultat = "result";


    private void majQuestion(){
        int question = mTabQuestions[indexActuel].getQuestion();
        mQuestionTextView.setText(getString(question));
    }

    private void verifyReponse(boolean userVrai){
        boolean reponseVraie = mTabQuestions[indexActuel].isQuestionVraie();

        int messReponseId = 0;

        if(userVrai == reponseVraie){
            messReponseId = R.string.toast_correct;
            point++;
        }else{
            messReponseId = R.string.toast_false;
        }


        Toast.makeText(MainActivity.this, messReponseId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Texte de la question
        mQuestionTextView = (TextView)findViewById(R.id.textView);
        int question = mTabQuestions[indexActuel].getQuestion();
        mQuestionTextView.setText(getString(question));


        mBoutonVrai = (Button)findViewById(R.id.true_button);
        mBoutonVrai.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                verifyReponse(true);
            }
        });


        mBoutonFaux = (Button)findViewById(R.id.false_button);
        mBoutonFaux.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                verifyReponse(false);
            }
        });

        mBoutonSuivant = (Button)findViewById(R.id.next);
        mBoutonSuivant.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                indexActuel = (indexActuel + 1);//% mTabQuestions.length;

                if(indexActuel==mTabQuestions.length){
                    Intent intent = new Intent(MainActivity.this, Activity2.class);

//                    intent.putExtra(resultat, mBoutonSuivant.getText().toString());
                    intent.putExtra("point",point);

                    startActivity(intent);
                }else{
                    int question = mTabQuestions[indexActuel].getQuestion();
                    mQuestionTextView.setText(getString(question));
                }


            }

        });

    }
}