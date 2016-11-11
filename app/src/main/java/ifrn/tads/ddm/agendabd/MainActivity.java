package ifrn.tads.ddm.agendabd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContatoDAO contatoDAO;
    private TextView textViewContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewContatos = (TextView) findViewById(R.id.textViewContatos);

        contatoDAO = ContatoDAO.getInstancia(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Contato> contatoList = contatoDAO.getContatos();
        String result = "";
        for (Contato c : contatoList) {
            result += c;
        }
        textViewContatos.setText(result);
    }

    public void novoContato(View view) {
        Intent formContato = new Intent(this, NovoContato.class);
        startActivityForResult(formContato, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String nome = data.getStringExtra("nome");
                String email = data.getStringExtra("email");
                String telefone = data.getStringExtra("telefone");
                String atual = textViewContatos.getText().toString();
                atual += nome+" : "+email+" : "+telefone+"\n";
                textViewContatos.setText(atual);
            }
        }
    }

    public void fechar(View view) {
        finish();
    }
}
