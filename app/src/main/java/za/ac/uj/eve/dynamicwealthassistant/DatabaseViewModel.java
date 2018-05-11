package za.ac.uj.eve.dynamicwealthassistant;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import Database.Account;
import Database.Category;
import Database.History;
import Database.Recurring;
import Database.Repository_Database;
import Database.User;
import Database.Value;

public class DatabaseViewModel extends AndroidViewModel {

    private Repository_Database repository_database;

    private LiveData<List<User>> allUsers;
    private LiveData<List<Account>> allAccounts;
    private LiveData<List<Category>> allCategorys;
    private LiveData<List<History>> allHistorys;
    private LiveData<List<Value>> allValues;
    private LiveData<List<Recurring>> allRecurrings;

    public DatabaseViewModel(Application application) {
        super(application);
        setRepository_database(new Repository_Database(application));
        setAllUsers(getRepository_database().getAllUsers());
        setAllAccounts(getRepository_database().getAllAccounts());
        setAllCategorys(getRepository_database().getAllCategorys());
        setAllHistorys(getRepository_database().getAllHistorys());
        setAllValues(getRepository_database().getAllValues());
        setAllRecurrings(getRepository_database().getAllRecurrings());
    }


    public Repository_Database getRepository_database() {
        return repository_database;
    }

    public void setRepository_database(Repository_Database repository_database) {
        this.repository_database = repository_database;
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(LiveData<List<User>> allUsers) {
        this.allUsers = allUsers;
    }

    public LiveData<List<Account>> getAllAccounts() {
        return allAccounts;
    }

    public void setAllAccounts(LiveData<List<Account>> allAccounts) {
        this.allAccounts = allAccounts;
    }

    public LiveData<List<Category>> getAllCategorys() {
        return allCategorys;
    }

    public void setAllCategorys(LiveData<List<Category>> allCategorys) {
        this.allCategorys = allCategorys;
    }

    public LiveData<List<History>> getAllHistorys() {
        return allHistorys;
    }

    public void setAllHistorys(LiveData<List<History>> allHistorys) {
        this.allHistorys = allHistorys;
    }

    public LiveData<List<Value>> getAllValues() {
        return allValues;
    }

    public void setAllValues(LiveData<List<Value>> allValues) {
        this.allValues = allValues;
    }

    public LiveData<List<Recurring>> getAllRecurrings() {
        return allRecurrings;
    }

    public void setAllRecurrings(LiveData<List<Recurring>> allRecurrings) {
        this.allRecurrings = allRecurrings;
    }
    //----------------- Insertion -----------------------------------------------------------------
    public void insertUser(User... user)
    {
        repository_database.insertUser(user);
    }

    public void insertAccount(Account... account)
    {
        repository_database.insertAccount(account);
    }

    public void insertCategory(Category... category)
    {
        repository_database.insertCategory(category);
    }

    public void insertHistory(History... history)
    {
        repository_database.insertHistory(history);
    }

    public void insertValue(Value... value)
    {
        repository_database.insertValue(value);
    }

    public void insertRecurring(Recurring... recurring)
    {
        repository_database.insertRecurring(recurring);
    }
}
