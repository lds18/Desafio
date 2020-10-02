package com.example.desafiopublicaproway;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Essa classe é responsavel por fazer a conexão como o próprio nome diz, entre a aplicação e o banco nativo (SQLite).
public class Conexao extends SQLiteOpenHelper {

    private static final String nomeDoBanco = "Banco";
    private static final int versaoBanco = 1;

//O Construtor dessa classe é responsável por criar um banco de dados, que necessita de um contexo como parâmetro.
    public Conexao(Context context) {
        super(context, "Banco", (SQLiteDatabase.CursorFactory)null, 1);
    }

//Esse metodo é responsável pela criação da tabela. Isso acontecerá após a primeira execução do aplicativo.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table jogos(id integer primary key, nome varchar(50), data varchar(20),pontuacao integer)");
    }

//Esse método é executado cada fez que o banco de dados é modificado, sendo através de comandos SQL.
//Esse método também é responsável pelas alterações do usuário que já tenha aberto o aplicativo.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}