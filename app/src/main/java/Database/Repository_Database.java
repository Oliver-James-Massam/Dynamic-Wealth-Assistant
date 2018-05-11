package Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class Repository_Database {
    private DAO_Database dao_database;

    private LiveData<List<User>> allUsers;
    private LiveData<List<Account>> allAccounts;
    private LiveData<List<Category>> allCategorys;
    private LiveData<List<History>> allHistorys;
    private LiveData<List<Value>> allValues;
    private LiveData<List<Recurring>> allRecurrings;

    public Repository_Database(Application application)
    {
        Room_Database db = Room_Database.getDatabase(application);
        dao_database = db.dao_database();

        allUsers = dao_database.getUsers();
        allAccounts = dao_database.getAccounts();
        allCategorys = dao_database.getCategorys();
        allHistorys = dao_database.getHistorys();
        allValues = dao_database.getValuesAll();
        allRecurrings = dao_database.getRecurrings();
    }

    public LiveData<List<User>> getAllUsers()
    {
        return allUsers;
    }

    public LiveData<List<Account>> getAllAccounts()
    {
        return allAccounts;
    }

    public LiveData<List<Category>> getAllCategorys()
    {
        return allCategorys;
    }

    public LiveData<List<History>> getAllHistorys()
    {
        return allHistorys;
    }

    public LiveData<List<Value>> getAllValues()
    {
        return allValues;
    }

    public LiveData<List<Recurring>> getAllRecurrings()
    {
        return allRecurrings;
    }

    //---------- User -----------------------------------------------------------------------------
    public void insertUser (User... user) {
        new insertUserAsyncTask(dao_database).execute(user);
    }

    //---------- Account --------------------------------------------------------------------------
    public void insertAccount (Account... account) {
        new insertAccountAsyncTask(dao_database).execute(account);
    }
    //---------- Category -------------------------------------------------------------------------
    public void insertCategory (Category... category) {
        new insertCategoryAsyncTask(dao_database).execute(category);
    }
    //---------- History --------------------------------------------------------------------------
    public void insertHistory (History... history) {
        new insertHistoryAsyncTask(dao_database).execute(history);
    }
    //---------- Value ----------------------------------------------------------------------------
    public void insertValue (Value... value) {
        new insertValueAsyncTask(dao_database).execute(value);
    }
    //---------- Recurring ------------------------------------------------------------------------
    public void insertRecurring (Recurring... recurring) {
        new insertRecurringAsyncTask(dao_database).execute(recurring);
    }

    //---------- Async Classes --------------------------------------------------------------------
    private static class insertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private DAO_Database mAsyncTaskDao;

        insertUserAsyncTask(DAO_Database dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insertUser(params[0]);
            return null;
        }
    }

    private static class insertAccountAsyncTask extends AsyncTask<Account, Void, Void> {

        private DAO_Database mAsyncTaskDao;

        insertAccountAsyncTask(DAO_Database dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Account... params) {
            mAsyncTaskDao.insertAccount(params[0]);
            return null;
        }
    }

    private static class insertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private DAO_Database mAsyncTaskDao;

        insertCategoryAsyncTask(DAO_Database dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            mAsyncTaskDao.insertCategory(params[0]);
            return null;
        }
    }

    private static class insertHistoryAsyncTask extends AsyncTask<History, Void, Void> {

        private DAO_Database mAsyncTaskDao;

        insertHistoryAsyncTask(DAO_Database dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final History... params) {
            mAsyncTaskDao.insertHistory(params[0]);
            return null;
        }
    }

    private static class insertValueAsyncTask extends AsyncTask<Value, Void, Void> {

        private DAO_Database mAsyncTaskDao;

        insertValueAsyncTask(DAO_Database dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Value... params) {
            mAsyncTaskDao.insertValue(params[0]);
            return null;
        }
    }

    private static class insertRecurringAsyncTask extends AsyncTask<Recurring, Void, Void> {

        private DAO_Database mAsyncTaskDao;

        insertRecurringAsyncTask(DAO_Database dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Recurring... params) {
            mAsyncTaskDao.insertRecurring(params[0]);
            return null;
        }
    }
}
