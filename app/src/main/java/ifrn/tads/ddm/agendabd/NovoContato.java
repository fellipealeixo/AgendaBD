
package ifrn.tads.ddm.agendabd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NovoContato extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextFone;
    private ContatoDAO contatoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contato);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextFone = (EditText) findViewById(R.id.editTextFone);

        contatoDAO = ContatoDAO.getInstancia(this);
    }

    public void insereContato(View view) {
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String fone = editTextFone.getText().toString();
        if (!nome.isEmpty() && !email.isEmpty() && !fone.isEmpty()) {
            contatoDAO.insereContato(nome, email, fone);
            Intent data = new Intent();
            data.putExtra("nome", nome);
            data.putExtra("email", email);
            data.putExtra("fone", fone);
            setResult(RESULT_OK, data);
            finish();
        } else {
            Toast.makeText(this, "Preencha todos os campos do form!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void voltar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
