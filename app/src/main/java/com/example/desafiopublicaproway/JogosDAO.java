package com.example.desafiopublicaproway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

//Classe responsável pelas inserções e modificações feitas no banco de dados.
public class JogosDAO {

    private SQLiteDatabase banco;
    private Conexao conexao;

//Esse método se conecta ao banco através de um parâmetro contexto.
    public JogosDAO(Context context){
        this.conexao = new Conexao(context);
        this.banco = conexao.getWritableDatabase();
    }

//Método que exclui os dados do banco de dados.
    public void Excluir(Jogo jogo){
        String[] id = new String[]{Integer.toString(jogo.getId())};
        banco.delete("jogos","id=?", id);
    }

//Método responável por inserir todos os dados no própro banco de dados, isso acontece através do parâmetro jogo que armazena os dados em um objeto no banco.
    public long Inserir(Jogo jogo){
        ContentValues values = new ContentValues();
        values.put("pontuacao",jogo.getPontuacao());
        values.put("data",jogo.getData());
        values.put("nome",jogo.getNome());
        return banco.insert("jogos",null, values);
    }

//Método que retorna um ArrayList contendo todos os jogos cadastrados no banco de dados. Essa retorna é feito através da leitura do objeto Cursor.
    public ArrayList Obter_Jogos(){
        ArrayList<Jogo> jogos = new ArrayList<>();
        Cursor cursor = banco.query("jogos", new String[]{"id","pontuacao","data","nome"}, (String)null, (String[])null, (String)null, (String)null, (String)null);

        while (cursor.moveToNext()){
            Jogo jogo = new Jogo();
            jogo.setId(cursor.getInt(0));
            jogo.setPontuacao(cursor.getInt(1));
            jogo.setData((cursor.getString(2)));
            jogo.setNome((cursor.getString(3)));
            jogos.add(jogo);
        }

        return jogos;
    }

}
