package com.example.yanis.speechrecogniztion_vistaar;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    // Il s'agit de la variable sur laquelle pointe le Layout TextViex dans res/layout/activity_main.xml
    private TextView txvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvResult = (TextView) findViewById(R.id.txvResult);
    }


    public void getSpeechInput(View view)
    {
        // Nous créeons une instance de la classe fournie par la bibliothèque de reconnaissance vocale

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Nous paramétrons l'instance en getDefault() car nous utilisons plus d'un langage
        // Ainsi la technologie Google se chargera de définir le langage source
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        // Nous testons la présence du PackageManager sur l'appareil utilisateur
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, 10);
        }
        else
        {
            Toast.makeText(this, "Votre appareil ne support pas la reconnaissance vocale", Toast.LENGTH_SHORT).show();
        }
    }
    //TEst git
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            // Le paramètre 10 doit être égal au RequestCode ligne 40
            case 10:
                if (resultCode == RESULT_OK && data != null)
                {
                    // Nous affectons à la variable txvResult le retour de l'API
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(result.get(0));
                }
                break;
        }
    }
}
