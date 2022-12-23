package com.samet.odev7.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.samet.odev7.R;
import com.samet.odev7.databinding.FragmentAnasayfaBinding;
import com.samet.odev7.ui.adapter.TodosAdapter;
import com.samet.odev7.ui.viewmodel.AnasayfaViewModel;


public class AnasayfaFragment extends Fragment {

    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false);
        binding.setAnasayfaToolbarBaslik("Todos");
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbarAnasayfa);

        // binding.rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));


        viewModel.todoListesi.observe(getViewLifecycleOwner(), todosList -> {
            TodosAdapter adapter = new TodosAdapter(requireContext(), todosList, viewModel);
            binding.setTodosAdapter(adapter);
        });
        binding.setAnasayfaFragment(this);


        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu,menu);

                MenuItem item = menu.findItem(R.id.action_ara);
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) AnasayfaFragment.this);

            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        },getViewLifecycleOwner(), Lifecycle.State.RESUMED);


        return binding.getRoot() ;

    }






    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(this).get(AnasayfaViewModel.class);
    }

    public void fabTikla(View view){
        Navigation.findNavController(view).navigate(R.id.action_anasayfaFragment_to_todoKayitFragment);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.ara(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        viewModel.ara(newText);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.todolariYukle();
    }



}