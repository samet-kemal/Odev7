package com.samet.odev7.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.samet.odev7.data.entity.Todos;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface TodosDao {

    @Query("SELECT * FROM todos")
    Single<List<Todos>> kisileriYukle();

    @Query("SELECT * FROM todos WHERE name like '%'||:aramaKelimesi||'%'")
    Single<List<Todos>> ara(String aramaKelimesi);


    @Delete
    Completable sil(Todos todo);

    @Insert
    Completable kaydet(Todos todo);

    @Update
    Completable guncelle(Todos todo);



}
