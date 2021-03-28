package com.five5.hellohomeo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.five5.hellohomeo.models.Crew;
import com.five5.hellohomeo.models.CrewEntity;

import java.util.List;

@Dao
public interface CrewDao{
    @Query("SELECT * FROM crew")
    List<Crew> getAll();

    @Insert
    void insert(Crew crew);

    @Delete
    void delete(Crew crew);

    @Query("DELETE FROM crew")
    void deleteAll();


}
