package com.example.desafiopublicaproway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    EditText editT_Nome, editT_Data, editT_Pontuacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editT_Nome = findViewById(R.id.editT_Nome);
        editT_Data = findViewById(R.id.editT_Data);
        editT_Pontuacao = findViewById(R.id.editT_Pontuacao);
    }

    public void SalvarJogo(View view){
        if((editT_Nome.getText().toString().length()>0) && (editT_Data.getText().toString().length()>0) && (editT_Pontuacao.getText().toString().length()>0)){
            JogosDAO jogosDAO= new JogosDAO(Cadastro.this);
            Jogo jogo= new Jogo();
            jogo.setNome(editT_Nome.getText().toString());
            jogo.setData(editT_Data.getText().toString());
            jogo.setPontuacao(Integer.parseInt(editT_Pontuacao.getText().toString()));
            jogosDAO.inserirJogo(jogo);
            VerificaPontuacao(jogo.getPontuacao());
            finish();
        }
        else{
            Toast.makeText(this, "Você deve preencher todos os campos !!", Toast.LENGTH_SHORT).show();
        }
    }

    public void VerificaPontuacao(int pontuacao){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.desafiopublicaproway", Context.MODE_PRIVATE);
        int minimo_temporada= sharedPreferences.getInt("minimo_temporada",0);
        int maximo_temporada= sharedPreferences.getInt("maximo_temporada",0);
        int quebra_recorde_minimo= sharedPreferences.getInt("quebra_recorde_minimo",0);
        int quebra_recorde_maximo= sharedPreferences.getInt("quebra_recorde_maximo",0);
        boolean primeiro_jogo = sharedPreferences.getBoolean("primeiro_jogo",true);

        if(primeiro_jogo){
            if(pontuacao>0){
                maximo_temporada=pontuacao;
                minimo_temporada=pontuacao;
                sharedPreferences.edit().putInt("maximo_temporada",maximo_temporada).apply();
                sharedPreferences.edit().putInt("minimo_temporada",minimo_temporada).apply();
            }
            sharedPreferences.edit().putBoolean("primeiro_jogo",false).apply();
        }
        else{
            if((pontuacao>minimo_temporada)&&(pontuacao>maximo_temporada)){
                maximo_temporada=pontuacao;
                quebra_recorde_maximo++;
                sharedPreferences.edit().putInt("maximo_temporada",maximo_temporada).apply();
                sharedPreferences.edit().putInt("quebra_recorde_maximo",quebra_recorde_maximo).apply();
            }
            else if((pontuacao<maximo_temporada)&&(pontuacao<minimo_temporada)){
                minimo_temporada=pontuacao;
                quebra_recorde_minimo++;
                sharedPreferences.edit().putInt("minimo_temporada",minimo_temporada).apply();
                sharedPreferences.edit().putInt("quebra_recorde_minimo",quebra_recorde_minimo).apply();
            }
        }
    }

}