package com.example.desafiopublicaproway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//Essa classe é a tela principal do aplicativo.
//Tendo apenas em seu xml um TextView como título e dois Buttons que quando executados iniciam as activity_cadstro e a activity_estatistica.
public class MainActivity extends AppCompatActivity {

//Esse método é responsável pela inicialização da activity_main.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//Esses dois métodos são responsáveis por iniciar  duas novas Activity.
//É possível iniciar uma nova instância de uma Activity passando um Intent para startActivity() com o nome da Activity desejada.
//A função do intent é de  iniciar e carrega todos os dados necessários de uma outra Activity.
    public void AbrirEstatisticas(View view){
        Intent intent = new Intent(MainActivity.this, Estatistica.class);
        startActivity(intent);
    }

    public void AbrirCadastro(View view){
        Intent intent = new Intent(MainActivity.this, Cadastro.class);
        startActivity(intent);
    }
}