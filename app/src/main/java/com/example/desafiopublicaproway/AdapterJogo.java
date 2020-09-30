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

public class AdapterJogo extends ArrayAdapter {

    Context context;
    ArrayList<Jogo> jogos;

    public AdapterJogo(Context context,ArrayList<Jogo> jogos) {
        super(context, R.layout.layout_estatistica_jogo,jogos);
        this.context=context;
        this.jogos = new ArrayList<Jogo>(jogos);
    }

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
