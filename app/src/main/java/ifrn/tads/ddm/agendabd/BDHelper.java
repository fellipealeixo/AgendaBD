package ifrn.tads.ddm.agendabd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BDHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "agendaBD";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TB_CONTATO = "CREATE TABLE contato ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "nome TEXT, email TEXT, telefone TEXT);";

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        banco.execSQL(CREATE_TB_CONTATO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int versaoAntiga, int novaVersao) {
        banco.execSQL("DROP TABLE IF EXISTS contato");
        onCreate(banco);
    }
}
