package com.halfbyte.waiter.Base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {LocalBD.class},version = 1)
public abstract class BaseDatos extends RoomDatabase {
    public abstract LocalDao localDao();
}
