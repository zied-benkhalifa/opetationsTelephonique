package com.example.operateur;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    int orange = Color.rgb(255, 165, 0);
    private EditText ed1;
    private EditText ed2;
    private TextView t3;
    private TextView t4,t1;
    private TextView t6;
    private TextView t8;
    private Button bt1;
    private Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t6 = findViewById(R.id.t6);
        t8 = findViewById(R.id.t8);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        t1=findViewById(R.id.t1);
        t1.setText(getIntent().getStringExtra("login"));

        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Ne fait rien avant le changement de texte.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Ne fait rien pendant le changement de texte.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Appelé après que le texte a été modifié.
                String phoneNumber = s.toString();
                if (!phoneNumber.isEmpty()) {
                    char firstDigit = phoneNumber.charAt(0);
                    handleFirstDigit(firstDigit);
                }
            }

        });
        ed2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Ne fait rien avant le changement de texte.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Ne fait rien pendant le changement de texte.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Appelé après que le texte a été modifié.
                String phoneNumber = s.toString();
                if (ed1.getText().toString().startsWith("9")) {
                    if (phoneNumber.length() > 13) {
                        // Limiter la longueur de la saisie à 13 chiffres
                        ed2.setText(phoneNumber.substring(0, 13));
                        ed2.setSelection(13); // Place le curseur à la fin
                    }
                } else if (ed1.getText().toString().startsWith("5") || ed1.getText().toString().startsWith("2")) {
                    if (phoneNumber.length() > 14) {
                        // Limiter la longueur de la saisie à 14 chiffres
                        ed2.setText(phoneNumber.substring(0, 14));
                        ed2.setSelection(14); // Place le curseur à la fin
                    }
                }
                updateTextView6("123 " + phoneNumber + "#");
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = t6.getText().toString();
                if (verif()) {
                    dialPhoneNumber(phoneNumber);
                } else {
                    // Code de recharge incorrect, afficher un message Toast
                    showToast("Code de recharge incorrect !");
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = t8.getText().toString();
                dialPhoneNumber(phoneNumber);
            }
        });
    }

    private void handleFirstDigit(char firstDigit) {
        switch (firstDigit) {
            case '9':
                t3.setText("Votre Ligne est Tunisie Telecom");
                t3.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                t4.setText("Entrez votre code de recharge (13 chiffres)");
                t6.setText("123 " + ed2.getText().toString());
                t6.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                t8.setText("*122#");
                t8.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                break;
            case '2':
                t3.setText("Votre Ligne est Ooredoo");
                t3.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                t4.setText("Entrez votre code de recharge (14 chiffres)");
                t6.setText("101 " + ed2.getText().toString());
                t6.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                t8.setText("*100#");
                t8.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                break;
            case '5':
                t3.setText("Votre Ligne est Orange");
                t3.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
                t4.setText("Entrez votre code de recharge (14 chiffres)");
                t6.setText("100 " + ed2.getText().toString());
                t6.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                t8.setText("*111#");
                t8.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                break;
            default:
                // Mettez ici le comportement par défaut si le premier chiffre ne correspond à aucun cas.
                break;
        }
    }

    private void updateTextView6(String phoneNumber) {
        t6.setText(phoneNumber);
    }
    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
    private boolean verif() {
        // Mettez ici votre logique pour vérifier si le code de recharge est valide
        // Par exemple, vérifiez la longueur du code ou d'autres critères
        String codeRecharge = ed2.getText().toString();
        return codeRecharge.length() == 13; // Exemple : code valide s'il a 13 chiffres
    }

    private void showToast(String message) {
        Toast.makeText(this, "Code de recharge incorrect !", Toast.LENGTH_SHORT).show();
    }


}