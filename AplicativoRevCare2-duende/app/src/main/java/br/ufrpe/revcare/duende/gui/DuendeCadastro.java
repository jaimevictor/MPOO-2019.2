package br.ufrpe.revcare.duende.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.duende.dominio.Duende;
import br.ufrpe.revcare.duende.negocio.DuendeServices;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.infra.gui.Validacao;

public class DuendeCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_duende_cadastro);
        Button btnFinalizarCadastro = findViewById(R.id.botaoFinalizarCadastro);
        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (cadastrar()) {
                        finish();
                        startActivity(new Intent(DuendeCadastro.this, RecyclerViewDuende.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Não foi possível cadastrar.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean cadastrar() throws Exception {
        if (validarCampos() && confirmaEmail()) {
            Duende duende = criarDuende();
            DuendeServices services = new DuendeServices(getBaseContext());
            services.cadastrar(duende);
            Toast.makeText(getApplicationContext(), "Duende cadastrado com sucesso.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DuendeCadastro.this, MainActivity.class));
            return true;
        }
    return false;
    }

    private boolean validarCampos() {
        EditText nNome = findViewById(R.id.nomeTextField);
        EditText nCpf = findViewById(R.id.cpfTextField);
        EditText nEmail = findViewById(R.id.emailTextField);
        EditText nAltura = findViewById(R.id.AlturaTextField);
        EditText nSenha = findViewById(R.id.senhaTextField);
        EditText nConfirmarSenha = findViewById(R.id.confirmarSenhaTextField);
        Validacao valido = new Validacao();
        boolean emailValido =
                valido.validarEmail(nEmail);
        boolean camposValidos =
                valido.isValido(nNome, nEmail,nAltura, nSenha, nConfirmarSenha);
        boolean senhasValidas =
                valido.confirmarSenha(getApplicationContext(),nSenha.getText().toString(),nConfirmarSenha.getText().toString());
        boolean cpfValido=
                valido.isCPF(nCpf) && confirmaCpf();
        boolean senhaCorreta=
                valido.senhaCorreta(nSenha);
        return emailValido && camposValidos && senhasValidas && cpfValido && senhaCorreta;
    }

    private  boolean confirmaEmail(){
        Duende result = null;
        DuendeServices services = new DuendeServices(this);
        EditText nEmail = findViewById(R.id.emailTextField);
        String email = nEmail.getText().toString().trim();
        result = services.consultarEmail(email);
        if (result != null){
            nEmail.requestFocus();
            nEmail.setError("Preencha novamente o campo.");
            Toast.makeText(getApplicationContext(), "Não foi possível realizar o cadastro.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private  boolean confirmaCpf(){
        Duende result = null;
        DuendeServices services = new DuendeServices(this);
        EditText nCpf = findViewById(R.id.cpfTextField);
        String cpf = nCpf.getText().toString().trim();
        result = services.consultarCpf(cpf);
        if (result != null){
            nCpf.requestFocus();
            nCpf.setError("Preencha novamente o campo.");
            Toast.makeText(getApplicationContext(), "Não foi possível realizar o cadastro.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Duende criarDuende() {
        EditText nNome = findViewById(R.id.nomeTextField);
        EditText nCpf = findViewById(R.id.cpfTextField);
        EditText nAltura = findViewById(R.id.AlturaTextField);
        EditText nEmail = findViewById(R.id.emailTextField);
        EditText nSenha = findViewById(R.id.senhaTextField);

        Duende result = new Duende();
        result.setNome(nNome.getText().toString().trim());
        result.setCpf(nCpf.getText().toString().trim());
        result.setAltura(nAltura.getText().toString().trim());
        result.setEmail(nEmail.getText().toString().trim());
        result.setSenha(nSenha.getText().toString().trim());
        return result;
    }
}
