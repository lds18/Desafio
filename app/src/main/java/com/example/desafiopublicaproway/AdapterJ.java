package com.example.desafiopublicaproway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

//Essa classe coordena as informação que serão exibidas no listview na activity_estatistica.
public class AdapterJ extends ArrayAdapter {

    Context context;
    ArrayList<Jogo> jogos;

//Esse é o contrutor que recebe como parâmetro um contexto e um ArrayList com os objetos jogo.
    public AdapterJ(Context context, ArrayList<Jogo> jogos) {
        super(context, R.layout.layout_estatistica_jogo,jogos);
        this.context=context;
        this.jogos = new ArrayList<Jogo>(jogos);
    }

//Método responsável por informar a posição de todos os dados que serão exibidas.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_estatistica_jogo,null);

        TextView txtNome = view.findViewById(R.id.txtNomeJogo);
        TextView txtData = view.findViewById(R.id.txtDataJogo);
        TextView txtPontuacao = view.findViewById(R.id.txtPontosDoJogo);

        txtNome.setText(jogos.get(position).getNome());
        txtData.setText(jogos.get(position).getData());
        txtPontuacao.setText("Pontuação: "+jogos.get(position).getPontuacao());

        return view;
    }
}
