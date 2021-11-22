package com.example.en_senia.ui.temas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.en_senia.R;

public class temasintermedioFragment extends Fragment {

    private TemasintermedioViewModel mViewModel;

    public static temasintermedioFragment newInstance() {
        return new temasintermedioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temasintermedio, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TemasintermedioViewModel.class);
        // TODO: Use the ViewModel
    }

}