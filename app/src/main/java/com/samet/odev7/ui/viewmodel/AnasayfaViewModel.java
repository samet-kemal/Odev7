package com.samet.odev7.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.samet.odev7.data.entity.Todos;
import com.samet.odev7.data.repo.TodosDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {
    private TodosDaoRepository trepo;
    public MutableLiveData<List<Todos>> todoListesi = new MutableLiveData<>();

    @Inject
    public  AnasayfaViewModel(TodosDaoRepository trepo){
        this.trepo=trepo;
        todolariYukle();
        todoListesi=trepo.getTodoListesi();

    }
    public  void  ara(String aramaKelimesi){
        trepo.ara(aramaKelimesi);
    }

    public void sil(int todo_id){
        trepo.sil(todo_id);
    }

    public void todolariYukle(){
        trepo.Todolariukle();
    }


}
