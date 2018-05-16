package za.ac.uj.eve.dynamicwealthassistant;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DAO_Database {

    // --------- Value ----------------------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertValue(Value... value);

    @Update
    void updateValue(Value... value);

    @Delete
    void deleteValue(Value... value);

    @Query("SELECT * FROM Value")
    List<Value> getValuesAll();
}
