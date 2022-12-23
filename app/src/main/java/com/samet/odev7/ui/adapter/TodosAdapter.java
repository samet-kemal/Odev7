package com.samet.odev7.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.samet.odev7.R;
import com.samet.odev7.data.entity.Todos;
import com.samet.odev7.databinding.CardTasarimBinding;
import com.samet.odev7.ui.viewmodel.AnasayfaViewModel;

import java.util.List;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Todos> todoListesi;
    private AnasayfaViewModel viewModel;

    public TodosAdapter(Context mContext, List<Todos> todoListesi, AnasayfaViewModel viewModel) {
        this.mContext = mContext;
        this.todoListesi = todoListesi;
        this.viewModel = viewModel;
    }


    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private CardTasarimBinding binding;

        public CardTasarimTutucu(CardTasarimBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    @NonNull
    @Override
    public TodosAdapter.CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        CardTasarimBinding binding= DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.card_tasarim,parent,false);


        return new CardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodosAdapter.CardTasarimTutucu holder, int position) {
        Todos todo= todoListesi.get(position);
        CardTasarimBinding t = holder.binding;
        t.setTodoNesnesi(todo);

        t.satirCard.setOnClickListener(view->{
            AnasayfaFragmentDirections.ActionAnasayfaFragmentToTodoDetayFragment gecis=
                    AnasayfaFragmentDirections.actionAnasayfaFragmentToTodoDetayFragment(todo);
            Navigation.findNavController(view).navigate(String.valueOf(gecis));

        });

        t.imageViewSil.setOnClickListener(view->{
            Snackbar.make(view,todo.getName()+"Silinsin mi?",Snackbar.LENGTH_LONG)
                    .setAction("EVET",view1->{
                        viewModel.sil(todo.getId());
                    }).show();
        });
    }

    @Override
    public int getItemCount() {
        return todoListesi.size();
    }
}

