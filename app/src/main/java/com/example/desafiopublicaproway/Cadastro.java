package com.example.desafiopublicaproway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Essa classe é responsável por cadastar novos jogos no banco de dados, e simultâneamente sendo exibidos na acitivity_estatisca.
public class Cadastro extends AppCompatActivity {

    EditText editT_Nome, editT_Data, editT_Pontuacao;

//Esse método é responsável por exibir e carregar todos os dados da activity_cadastro.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//Busca uma View pelo seu id respectivo.
        editT_Nome = findViewById(R.id.editT_Nome);
        editT_Data = findViewById(R.id.editT_Data);
        editT_Pontuacao = findViewById(R.id.editT_Pontuacao);
    }

//Método responsável por coletar os dados nas EditText fornecidos pelo usuário, criando um novo objeto e inseridos no banco de dados.
    public void Salvar(View view){
        if((editT_Nome.getText().toString().length()>0) && (editT_Data.getText().toString().length()>0) && (editT_Pontuacao.getText().toString().length()>0)){
            JogosDAO jogosDAO= new JogosDAO(Cadastro.this);
            Jogo jogo= new Jogo();
            jogo.setNome(editT_Nome.getText().toString());
            jogo.setData(editT_Data.getText().toString());
            jogo.setPontuacao(Integer.parseInt(editT_Pontuacao.getText().toString()));
            jogosDAO.Inserir(jogo);
            Verificação_de_Pont(jogo.getPontuacao());
            finish();
        }
        else{
            Toast.makeText(this, "Você deve preencher todos os campos !!", Toast.LENGTH_SHORT).show();
        }
    }

//Esse método faz uma comparação para ver se é o primeiro jogo do usuário, ou se quebrou algum parâmetro.
    public void Verificação_de_Pont(int pontuacao){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.desafiopublicaproway", Context.MODE_PRIVATE);
        int mt= sharedPreferences.getInt("mt",0);
        int mat= sharedPreferences.getInt("mat",0);
        int qrmin= sharedPreferences.getInt("qrmin",0);
        int qrma= sharedPreferences.getInt("qrma",0);
        boolean pj = sharedPreferences.getBoolean("pj",true);

        if(pj){
            if(pontuacao>0){
                mat=pontuacao;
                mt=pontuacao;
                sharedPreferences.edit().putInt("mat",mat).apply();
                sharedPreferences.edit().putInt("mt",mt).apply();
            }
            sharedPreferences.edit().putBoolean("pj",false).apply();
        }
        else{
            if((pontuacao>mt)&&(pontuacao>mat)){
                mat=pontuacao;
                qrma++;
                sharedPreferences.edit().putInt("mat",mat).apply();
                sharedPreferences.edit().putInt("qrma",qrma).apply();
            }
            else if((pontuacao<mat)&&(pontuacao<mt)){
                mt=pontuacao;
                qrmin++;
                sharedPreferences.edit().putInt("mt",mt).apply();
                sharedPreferences.edit().putInt("qrmin",qrmin).apply();
            }
        }
    }

}