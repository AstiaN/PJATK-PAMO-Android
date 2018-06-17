package com.example.astian.pjatk_pamo_project.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.example.astian.pjatk_pamo_project.model.Clients;

@Dao
public interface ClientDao {

    @Query("SELECT * FROM Clients")
    List<Clients> getAll();

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(Clients clients);

    @Update
    void update(Clients clients);

    @Delete
    void delete(Clients clients);
}
