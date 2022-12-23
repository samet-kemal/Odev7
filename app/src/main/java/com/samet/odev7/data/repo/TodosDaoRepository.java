package com.samet.odev7.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.samet.odev7.data.entity.Todos;
import com.samet.odev7.room.TodosDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TodosDaoRepository {
    private MutableLiveData<List<Todos>> todoListesi;
    private TodosDao tDao;


    public TodosDaoRepository(TodosDao tDao) {
        this.tDao=tDao;
        todoListesi =new MutableLiveData();
    }

    public MutableLiveData<List<Todos>> getTodoListesi() {
        return todoListesi;
    }

    public void Todolariukle(){
        tDao.kisileriYukle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Todos>>()  {

                               @Override
                               public void onSubscribe(Disposable d) {

                               }

                               @Override
                               public void onSuccess(List<Todos> todos) {

                                   todoListesi.setValue(todos);
                               }

                               @Override
                               public void onError(Throwable e) {

                               }
                           });


    }


    public void kaydet(String todo_ad){
        Todos yeniKisi = new Todos(0, todo_ad);
        tDao.kaydet(yeniKisi)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });

        Log.e("Kişi Kayıt",todo_ad);

    }

    public void guncelle(int todo_id,String todo_ad){

        Todos guncellenenTodo = new Todos(todo_id, todo_ad);
        tDao.guncelle(guncellenenTodo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });    }

    public  void  ara(String aramaKelimesi){

        tDao.ara(aramaKelimesi)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Todos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<Todos> kisilers) {
                        todoListesi.setValue(kisilers);

                    }
                    @Override
                    public void onError(Throwable e) {}
                });     }


    public void sil(int todo_id){

        Todos silinenTodo=new Todos(todo_id,"");
        tDao.sil(silinenTodo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        Todolariukle();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}
