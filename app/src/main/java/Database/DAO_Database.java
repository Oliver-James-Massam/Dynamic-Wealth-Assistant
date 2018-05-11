package Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DAO_Database {
    // --------- User -----------------------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... user);

    @Update
    void updateUser(User... user);

    @Delete
    void deleteUser(User... user);

    @Query("SELECT * FROM User")
    LiveData<List<User>> getUsers();

    // --------- Account --------------------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccount(Account... account);

    @Update
    void updateAccount(Account... account);

    @Delete
    void deleteAccount(Account... account);

    @Query("SELECT * FROM Account")
    LiveData<List<Account>> getAccounts();

    @Query("SELECT * FROM Account WHERE userID = :userID")
    double getAccount(int userID);

    @Query("SELECT * FROM Account WHERE accID = :accID")
    double getAccountBalance(int accID);

    // --------- Category -------------------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category... category);

    @Update
    void updateCategory(Category... category);

    @Delete
    void deleteCategory(Category... category);

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getCategorys();

    // --------- History --------------------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHistory(History... history);

    @Update
    void updateHistory(History... history);

    @Delete
    void deleteHistory(History... history);

    @Query("SELECT * FROM History")
    LiveData<List<History>> getHistorys();

    // --------- Value ----------------------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertValue(Value... value);

    @Update
    void updateValue(Value... value);

    @Delete
    void deleteValue(Value... value);

    @Query("SELECT * FROM Value")
    LiveData<List<Value>> getValuesAll();

    // --------- Recurring ------------------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecurring(Recurring... recurring);

    @Update
    void updateRecurring(Recurring... recurring);

    @Delete
    void deleteRecurring(Recurring... recurring);

    @Query("SELECT * FROM Recurring")
    LiveData<List<Recurring>> getRecurrings();
}
