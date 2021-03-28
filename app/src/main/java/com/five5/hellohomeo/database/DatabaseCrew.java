package com.five5.hellohomeo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.five5.hellohomeo.dao.CrewDao;
import com.five5.hellohomeo.models.Crew;
import com.five5.hellohomeo.models.CrewEntity;

@Database(entities = {Crew.class}, version = 1,exportSchema = true)
public abstract class DatabaseCrew extends RoomDatabase {
        public abstract CrewDao crewDao();

        public DatabaseCrew() {
        }
}
