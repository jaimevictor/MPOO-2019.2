package br.ufrpe.revcare.duende.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufrpe.revcare.R;

public class RecyclerViewAdapterDuende extends RecyclerView.Adapter<RecyclerViewAdapterDuende.ViewHolder>  {
    private ArrayList<String> mNome = new ArrayList<>();
    private ArrayList<String> mAltura = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterDuende(Context context, ArrayList<String> nome, ArrayList<String> email, ArrayList<String> altura) {
        this.mContext = context;
        this.mNome = nome;
        this.mEmail = email;
        this.mAltura = altura;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_duende, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.nome.setText(mNome.get(position));
        holder.email.setText(mEmail.get(position));
        holder.altura.setText(mAltura.get((position)));

    }

    @Override
    public int getItemCount() {
        return mNome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        RelativeLayout parentLayout;
        TextView altura;
        TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            nome = itemView.findViewById(R.id.nome);
            altura = itemView.findViewById(R.id.altura);
            email = itemView.findViewById(R.id.email);
        }
    }
}

