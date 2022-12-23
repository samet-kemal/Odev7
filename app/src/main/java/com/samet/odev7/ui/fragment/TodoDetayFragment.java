package com.samet.odev7.ui.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samet.odev7.R;
import com.samet.odev7.data.entity.Todos;
import com.samet.odev7.databinding.FragmentTodoDetayBinding;


public class TodoDetayFragment extends Fragment {

    private FragmentTodoDetayBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_todo_detay,container,false);



        binding.setTodoDetayToolbarBaslik("Todo Detay");

        TodoDetayFragmentArgs bundle = TodoDetayFragmentArgs.fromBundle(getArguments());
        Todos gelenTodo=bundle.getTodo();

        binding.setTodoNesnesi(gelenTodo);

        binding.editTextKisiAd.setText(gelenTodo.getName());

        binding.setTodoDetayragment(this);

        return binding.getRoot();
    }
}