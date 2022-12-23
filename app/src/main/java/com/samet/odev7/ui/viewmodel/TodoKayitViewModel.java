package com.samet.odev7.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.samet.odev7.data.repo.TodosDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TodoKayitViewModel extends ViewModel {

   private TodosDaoRepository trepo;

   @Inject
   public TodoKayitViewModel(TodosDaoRepository trepo) {
      this.trepo=trepo;
   }

   public void kaydet(String todo_ad){

      trepo.kaydet(todo_ad);

   }
}
