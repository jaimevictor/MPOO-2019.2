package br.ufrpe.revcare.duende.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.duende.dominio.Duende;
import br.ufrpe.revcare.duende.persistencia.DuendeDAO;

public class RecyclerViewDuende extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<String> mAltura = new ArrayList<>();
    private ArrayList<String> mCpf = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private List<Duende> duendes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_duende);
        getSupportActionBar().hide();
        FloatingActionButton btnCadastro = findViewById(R.id.BotaoCadastroDuende);
        initDuendes();

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(RecyclerViewDuende.this, DuendeCadastro.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void initDuendes() {

        DuendeDAO dao = new DuendeDAO(getApplicationContext());
        duendes = dao.getAllDuendes();
        adicionaNoArray(duendes);
        initRecyclerView();
    }

    private void adicionaNoArray(List<Duende> duendes) {
        for (int i = 0; i < duendes.size(); i++) {
            mNomes.add(duendes.get(i).getNome());
            mEmail.add(duendes.get(i).getEmail());
            mAltura.add(duendes.get(i).getAltura());
            mCpf.add(duendes.get(i).getCpf());

        }
    }

    private void zerarArrays() {
        mNomes = new ArrayList<>();
        mAltura = new ArrayList<>();
        duendes = new ArrayList<>();
        mEmail = new ArrayList<>();
        mCpf = new ArrayList<>();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.duenderecylcer);
        RecyclerViewAdapterDuende adapter = new RecyclerViewAdapterDuende(this, mNomes, mEmail, mAltura);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}