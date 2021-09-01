package com.halfbyte.waiter.Base;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface LocalDao {
    @Query("SELECT * FROM LocalBD")
    LocalBD getLocal();

    @Insert
    void inserLocal(LocalBD... local);

    @Delete
    void deletLocal(LocalBD local);
}
