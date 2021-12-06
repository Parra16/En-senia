package com.example.en_senia.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.en_senia.R;
import com.example.en_senia.databinding.FragmentHomeBinding;
import com.example.en_senia.ui.Lista_temas.ListaTemas;
import com.example.en_senia.ui.diccionario.Diccionario;
import com.example.en_senia.ui.mainPractica.Practicas;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ImageView ivLecciones,ivPracticas,ivDiccionario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        /*
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */

        asigComponentes(root);
        ivLecciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListaTemas.class);
                startActivity(intent);
            }
        });
        ivDiccionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Diccionario.class);
                startActivity(intent);
            }
        });

        ivPracticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Practicas.class);
                startActivity(intent);
            }
        });




        return root;
    }

    private void asigComponentes(View root){
        ivLecciones = root.findViewById(R.id.MAINivLecciones);
        ivDiccionario = root.findViewById(R.id.MAINivDiccionario);
        ivPracticas = root.findViewById(R.id.MAINivPracticas);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void cambiarFragmentLecciones(){
        // Crear fragmento de tu clase
        //Fragment fragment = new ListaTemasFragment();
// Obtener el administrador de fragmentos a través de la actividad
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        //getActivity().getSupportFragmentManager();
// Definir una transacción
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
// Remplazar el contenido principal por el fragmento
        //  fragmentTransaction.replace(R.id.nav_host_fragment_content_main, fragment);
        fragmentTransaction.addToBackStack(null);
// Cambiar
        fragmentTransaction.commit();
    }
}