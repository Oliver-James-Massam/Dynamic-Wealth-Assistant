package za.ac.uj.eve.dynamicwealthassistant;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //Database
    private AppDatabase db;
    //List View
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;
    //Other Variables
    private char myCurr = 'R';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Check for reoccuring
        SharedPreferences preferences = getSharedPreferences("BUDGET", MODE_PRIVATE);
        int budget = preferences.getInt("BudgetData", -1);
        //Is budget defined
        if (budget > -1)
        {
            preferences = getSharedPreferences("LASTMONTH", MODE_PRIVATE);
            int lastMonth = preferences.getInt("LastMonthData", -1);
            //get Date
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            //Is a new month
            if(month != lastMonth)
            {
                preferences = getSharedPreferences("BALANCE", MODE_PRIVATE);
                int balance = budget;

                SharedPreferences.Editor editor = preferences.edit();
                // Commit to shared preferences
                editor.putInt("BalanceData",balance);
                editor.commit();

                preferences = getSharedPreferences("LASTMONTH", MODE_PRIVATE);
                editor = preferences.edit();
                // Commit to shared preferences
                editor.putInt("LastMonthData",month);
                editor.commit();
            }
        }

        ImageButton btnAddIncome = (ImageButton) findViewById(R.id.btnAddIncome);
        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddIncomeValue.class));
            }
        });

        ImageButton btnAddExpense = (ImageButton) findViewById(R.id.btnAddExpense);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddExpenseValue.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Initialise the Database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "finance")
                .allowMainThreadQueries()
                .build();

        //List View
        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHashMap);
        listView.setAdapter(listAdapter);
    }

    //Initialise List View Data
    private void initData()
    {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();
        //List View Headers  in order of how they are displayed
        listDataHeader.add("Bills");
        listDataHeader.add("Transport");
        listDataHeader.add("Car");
        listDataHeader.add("House");
        listDataHeader.add("Food");
        listDataHeader.add("Eating Out");
        listDataHeader.add("Medical");
        listDataHeader.add("Entertainment");
        listDataHeader.add("Other");
        //List View Item Array in order of how they are displayed
        List<String> bills = new ArrayList<>();
        List<String> transport = new ArrayList<>();
        List<String> car = new ArrayList<>();
        List<String> house = new ArrayList<>();
        List<String> food = new ArrayList<>();
        List<String> eatingOut = new ArrayList<>();
        List<String> medical = new ArrayList<>();
        List<String> entertainment = new ArrayList<>();
        List<String> other = new ArrayList<>();

        List<Value> values = db.dao_database().getValuesAll();
        for (Value value: values)
        {
            //Date display format
            String strDateFormat = "dd-MMM-yyyy";// hh:mm:ss a
            SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
            //Display value in item
            String strOut;
            if(value.getValueType().equals("Expense"))
            {
                strOut = "(" + objSDF.format(value.getTransactionDate())+ ")\t\t\t\t" + "-  " + myCurr + value.getAmount();
            }
            else
            {
                strOut = "(" + objSDF.format(value.getTransactionDate())+ ")\t\t\t\t" + "+ " + myCurr + value.getAmount();
            }

            Category category = Category.valueOf(value.getCategoryName());
            switch (category)
            {
                case Bills:
                    bills.add(strOut);
                    break;
                case Car:
                    car.add(strOut);
                    break;
                case Transport:
                    transport.add(strOut);
                    break;
                case House:
                    house.add(strOut);
                    break;
                case EatingOut:
                    eatingOut.add(strOut);
                    break;
                case Medical:
                    medical.add(strOut);
                    break;
                case Entertainment:
                    entertainment.add(strOut);
                    break;
                case Food:
                    food.add(strOut);
                    break;
                case Other:
                    other.add(strOut);
                    break;
            }
        }
        //Place each list into List Grouping
        listHashMap.put(listDataHeader.get(0),bills);
        listHashMap.put(listDataHeader.get(1),transport);
        listHashMap.put(listDataHeader.get(2),car);
        listHashMap.put(listDataHeader.get(3),house);
        listHashMap.put(listDataHeader.get(4),food);
        listHashMap.put(listDataHeader.get(5),eatingOut);
        listHashMap.put(listDataHeader.get(6),medical);
        listHashMap.put(listDataHeader.get(7),entertainment);
        listHashMap.put(listDataHeader.get(8),other);

        //Setting Balance from Shared Preferences
        SharedPreferences preferences = getSharedPreferences("BALANCE", MODE_PRIVATE);
        int balance = preferences.getInt("BalanceData", 0);

        Button btnBalance = (Button) findViewById(R.id.balance_display);
        btnBalance.setText(myCurr + " " + balance);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
//                super.onBackPressed();
//                return;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finishAffinity();
                startActivity(intent);
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            mHandler.postDelayed(mRunnable, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage_balance) {
            startActivity(new Intent(MainActivity.this, ManageBalanceActivity.class));
        }  else if (id == R.id.nav_change_pin) {
            startActivity(new Intent(MainActivity.this, ChangePinActivity.class));
        } else if (id == R.id.nav_reset_data) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
            mBuilder.setTitle(R.string.dialog_title);
            mBuilder.setMessage(R.string.dialog_message);
            mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Delete all values
                    db.dao_database().deleteAll();
                    // Delete Shared Preferences
                    SharedPreferences preferences = getSharedPreferences("MYLOGIN", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    // Commit to shared preferences
                    editor.putString("LoginData","-1");
                    editor.commit();

                    // Delete Shared Preferences
                    preferences = getSharedPreferences("BALANCE", MODE_PRIVATE);
                    editor = preferences.edit();
                    // Commit to shared preferences
                    editor.putInt("BalanceData",0);
                    editor.commit();
                    // Delete Shared Preferences
                    preferences = getSharedPreferences("BUDGET", MODE_PRIVATE);
                    editor = preferences.edit();
                    // Commit to shared preferences
                    editor.putInt("BudgetData",-1);
                    editor.commit();
                    // Go back to Login
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    dialog.dismiss();
                }
            });
            mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            //Build and show the alert
            AlertDialog alertDialog = mBuilder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }
}
