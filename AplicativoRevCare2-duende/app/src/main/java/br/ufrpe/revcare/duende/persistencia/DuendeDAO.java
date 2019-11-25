package br.ufrpe.revcare.duende.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.duende.dominio.Duende;
import br.ufrpe.revcare.infra.persistencia.DBHelper;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_CPF;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_EMAIL;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_DUENDE;

public class DuendeDAO {

    private static DBHelper dbHelper;

    public DuendeDAO(Context context) {

        dbHelper = new DBHelper(context);
    }
    public long cadastrar(Duende duende){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_NOME, duende.getNome());
        values.put(DBHelper.COL_CPF, duende.getCpf());
        values.put(DBHelper.COL_EMAIL, duende.getEmail());
        values.put(DBHelper.COL_ALTURA, duende.getEmail());
        values.put(DBHelper.COL_SENHA, duende.getSenha());

        long id = db.insert(TABELA_DUENDE, null, values);
        db.close();
        return id;

    }
    public List<Duende> getAllDuendes() {
        List<Duende> duendeArrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = " SELECT * " +
                " FROM " + TABELA_DUENDE;
        String[] args = {};
        Cursor cursor = db.rawQuery(query, args);
        Duende duende = null;
        if (cursor.moveToFirst()) {
            do {
                duende = criarDuende(cursor);
                duendeArrayList.add(duende);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
            return duendeArrayList;
        }
        cursor.close();
        db.close();
        return duendeArrayList;
    }
    private Duende criarDuende(Cursor cursor) {
        Duende result = new Duende();
        result.setId(cursor.getInt(0));
        result.setNome(cursor.getString(1));
        result.setCpf(cursor.getString(2));
        result.setEmail(cursor.getString(3));
        result.setAltura(cursor.getString(4));
        result.setSenha(cursor.getString(5));


        return result;
    }
    public Duende consultarEmail(String email) {
        Duende result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_DUENDE +
                        " WHERE " + COL_EMAIL + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if (cursor.moveToFirst()) {
            result = criarDuende(cursor);
        }
        cursor.close();
        db.close();

        return result;
    }
    public Duende consultarCpf(String cpf) {
        Duende result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_DUENDE +
                        " WHERE " + COL_CPF + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{cpf});
        if (cursor.moveToFirst()) {
            result = criarDuende(cursor);
        }
        cursor.close();
        db.close();
        return result;
    }
}
