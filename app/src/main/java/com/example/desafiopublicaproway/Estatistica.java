package com.example.desafiopublicaproway;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Estatistica extends AppCompatActivity {

    TextView txtMinimoTemp, txtMaximoTemp, txtQuebraMinima, txtQuebraMaxima;
    ListView listview_jogos;
    ArrayAdapter adapterjogo;
    ArrayList<Jogo> jogos=null;
    JogosDAO jogosDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatistica);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtMinimoTemp =findViewById(R.id.txtMinimoTemp);
        txtMaximoTemp =findViewById(R.id.txtMaximoTemp);
        txtQuebraMinima =findViewById(R.id.txtQuebraMinima);
        txtQuebraMaxima =findViewById(R.id.txtQuebraMaxima);

        listview_jogos = findViewById(R.id.listview_jogos);
        listview_jogos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder adb= new AlertDialog.Builder(Estatistica.this);
                adb.setTitle("Excluir jogo");
                adb.setMessage("Deseja realmente excluir");
                adb.setNegativeButton("NÂo",null);
                adb.setPositiveButton("SIM",new AlertDialog.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JogosDAO jogosDAO = new JogosDAO(Estatistica.this);
                        jogosDAO.excluirJogo(jogos.get(position));
                        CarregarJogos();
                        verificarJogos();
                        Toast.makeText(Estatistica.this, "Jogo excluído.", Toast.LENGTH_SHORT).show();
                    }
                });
                adb.show();

                return true;
            }
        });
        jogosDAO = new JogosDAO(this);

        CarregarJogos();
    }

    public void CarregarJogos(){
        jogos = new ArrayList<Jogo>(jogosDAO.obterJogos());
        adapterjogo = new AdapterJogo(Estatistica.this, jogos);
        listview_jogos.setAdapter(adapterjogo);
    }


    private void verificarJogos() {
        if(jogos.size()<=0){
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.desafiopublicaproway",Context.MODE_PRIVATE);
            sharedPreferences.edit().putInt("maximo_temporada",0).apply();
            sharedPreferences.edit().putInt("minimo_temporada",0).apply();
            sharedPreferences.edit().putInt("quebra_recorde_minimo",0).apply();
            sharedPreferences.edit().putInt("quebra_recorde_maximo",0).apply();
            sharedPreferences.edit().putBoolean("primeiro_jogo",true).apply();
        }
    }


    public void CarregarEstatisticas(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.desafiopublicaproway", Context.MODE_PRIVATE);
        int minimo_temporada= sharedPreferences.getInt("minimo_temporada",0);
        int maximo_temporada= sharedPreferences.getInt("maximo_temporada",0);
        int quebra_recorde_minimo= sharedPreferences.getInt("quebra_recorde_minimo",0);
        int quebra_recorde_maximo= sharedPreferences.getInt("quebra_recorde_maximo",0);

        txtMinimoTemp.setText(Integer.toString(minimo_temporada));
        txtMaximoTemp.setText(Integer.toString(maximo_temporada));
        txtQuebraMinima.setText(Integer.toString(quebra_recorde_minimo));
        txtQuebraMaxima.setText(Integer.toString(quebra_recorde_maximo));
    }

    @Override
    protected void onResume() {
        CarregarJogos();
        CarregarEstatisticas();
        verificarJogos();
        super.onResume();
    }
}