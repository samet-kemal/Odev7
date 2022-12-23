package com.samet.odev7.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.samet.odev7.data.entity.Todos;

@Database(entities = {Todos.class},version=1)
public abstract class Veritabani extends RoomDatabase {
    public abstract TodosDao getTodosDao();

}
