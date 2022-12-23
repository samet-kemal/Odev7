package com.samet.odev7.di;

import android.content.Context;

import androidx.room.Room;

import com.samet.odev7.data.repo.TodosDaoRepository;
import com.samet.odev7.room.TodosDao;
import com.samet.odev7.room.Veritabani;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public TodosDaoRepository provideTodosDaoRepository(TodosDao tDao){
        return new TodosDaoRepository(tDao);
    }

    @Provides
    @Singleton
    public TodosDao provideKisilerDao(@ApplicationContext Context context){

        Veritabani vt= Room.databaseBuilder(context,Veritabani.class,"toDos.sqlite").createFromAsset("toDos.sqlite").build();

        return vt.getTodosDao();

    }

}
