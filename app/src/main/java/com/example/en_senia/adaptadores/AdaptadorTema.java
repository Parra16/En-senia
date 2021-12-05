package com.example.en_senia.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.en_senia.R;
import com.example.en_senia.objetos.Tema;

import java.util.ArrayList;

public class AdaptadorTema extends BaseAdapter {

    ArrayList<Tema> listitems;
    Context context;
    public AdaptadorTema() {
    }

    public AdaptadorTema(ArrayList<Tema> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }



    @Override
    public int getCount() {
        return listitems.size();
    }

    @Override
    public Object getItem(int i) {
        return listitems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("tema_entro", "recibio"+String.valueOf(getCount()));
        Tema tema = (Tema) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.item_temas,null);
        //Toast.makeText(view.getContext(), this.getCount(), Toast.LENGTH_SHORT).show();
        ImageView imgFoto = view.findViewById(R.id.ITMivTemas);
        TextView titulo = view.findViewById(R.id.ITMtvTitulo);
        TextView descripcion = view.findViewById(R.id.ITMtvDescripcion);

        //imgFoto.setImageURI();
        titulo.setText(tema.getTitulo());
        descripcion.setText(tema.getDescripcion());

        return view;
    }
}
