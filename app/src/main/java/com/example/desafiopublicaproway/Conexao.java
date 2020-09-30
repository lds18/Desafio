package com.example.desafiopublicaproway;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class Conexao extends SQLiteOpenHelper {

    private static final String nomeDoBanco = "Banco";
    private static final int versaoBanco = 1;

    public Conexao(Context context) {
        super(context, "Banco", (SQLiteDatabase.CursorFactory)null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table jogos(id integer primary key, nome varchar(50), data varchar(20),pontuacao integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}