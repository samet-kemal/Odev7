package com.samet.odev7.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samet.odev7.R;
import com.samet.odev7.databinding.FragmentTodoDetayBinding;
import com.samet.odev7.ui.viewmodel.TodoKayitViewModel;


public class TodoKayitFragment extends Fragment {

    private FragmentTodoDetayBinding binding;
    private TodoKayitViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_todo_kayit,container,false);

        binding.setTodoDetayToolbarBaslik("Todo Kayıt");
        binding.setTodoKayitFregment(this);


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(this).get(TodoKayitViewModel.class);
    }

    public void kaydet(String todo_ad){

        viewModel.kaydet(todo_ad);
    }


}