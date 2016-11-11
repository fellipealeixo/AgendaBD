package ifrn.tads.ddm.agendabd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private static ContatoDAO instancia;
    private BDHelper helper;

    private final String LIST_CONTATOS = "SELECT * FROM contato";

    private ContatoDAO (Context context) {
        helper = new BDHelper(context);
    }

    public static ContatoDAO getInstancia(Context context) {
        if (instancia == null)
            instancia = new ContatoDAO(context);
        return instancia;
    }

    public List<Contato> getContatos () {
        Cursor cursor = helper.getReadableDatabase().rawQuery(LIST_CONTATOS, null);
        List<Contato> lista = new ArrayList<Contato>();
        while (cursor.moveToNext()) {
            Contato contato = new Contato();
            contato.setId(cursor.getInt(0));
            contato.setNome(cursor.getString(1));
            contato.setEmail(cursor.getString(2));
            contato.setTelefone(cursor.getString(3));
            lista.add(contato);
        }
        cursor.close();
        return lista;
    }

    public int insereContato (String nome, String email, String fone) {
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("email", email);
        cv.put("telefone", fone);
        long id = helper.getWritableDatabase().insert("contato", null, cv);
        return (int) id;
    }
}
