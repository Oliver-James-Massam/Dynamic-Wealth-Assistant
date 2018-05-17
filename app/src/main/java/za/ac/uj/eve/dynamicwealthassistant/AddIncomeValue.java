package za.ac.uj.eve.dynamicwealthassistant;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class AddIncomeValue extends AppCompatActivity {
    private EditText value;
    private Context context;
    private AppDatabase db;
    //Category Buttons
    private Button btnIncomeBills;
    private Button btnIncomeCar;
    private Button btnIncomeHouse;
    private Button btnIncomeFood;
    private Button btnIncomeMedical;
    private Button btnIncomeEatingOut;
    private Button btnIncomeEntertainment;
    private Button btnIncomeTransport;
    private Button btnIncomeOther;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_income_value);
        context = this;

        //Set Icon in Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_action_wallet_filled_money);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Declare Value
        value = findViewById(R.id.newIncomeValueAmount);
        //Declare Buttons
        btnIncomeBills = findViewById(R.id.btnIncomeBills);
        btnIncomeCar = findViewById(R.id.btnIncomeCar);
        btnIncomeEatingOut = findViewById(R.id.btnIncomeEatingOut);
        btnIncomeEntertainment = findViewById(R.id.btnIncomeEntertainment);
        btnIncomeFood = findViewById(R.id.btnIncomeFood);
        btnIncomeHouse = findViewById(R.id.btnIncomeHouse);
        btnIncomeMedical = findViewById(R.id.btnIncomeMedical);
        btnIncomeTransport = findViewById(R.id.btnIncomeTransport);
        btnIncomeOther = findViewById(R.id.btnIncomeOther);
        //Open DB
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "finance")
                .allowMainThreadQueries()
                .build();

        btnIncomeBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Bills");
            }
        });

        btnIncomeTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Transport");
            }
        });

        btnIncomeHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("House");
            }
        });

        btnIncomeCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Car");
            }
        });

        btnIncomeFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Food");
            }
        });

        btnIncomeMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Medical");
            }
        });

        btnIncomeEatingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("EatingOut");
            }
        });

        btnIncomeEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Entertainment");
            }
        });

        btnIncomeOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Other");
            }
        });
    }

    public void saveData(String category)
    {
        //Check to make sure there is a valid number before using it
        if(!value.getText().toString().isEmpty() && !value.getText().toString().equals("0"))
        {
            Date date = new Date();
            int val = Integer.parseInt(value.getText().toString());
            db.dao_database().insertValue(new Value("Income", val, category, date,1));
            // Shared Preferences for balance
            SharedPreferences preferences = getSharedPreferences("BALANCE", MODE_PRIVATE);
            int balance = preferences.getInt("BalanceData", 0);
            balance = balance + val;

            SharedPreferences.Editor editor = preferences.edit();
            // Commit to shared preferences
            editor.putInt("BalanceData",balance);
            editor.commit();
            startActivity(new Intent(AddIncomeValue.this, MainActivity.class));
        }
        else
        {
            Toast.makeText(context, "Please enter a number greater than 0", Toast.LENGTH_SHORT).show();
        }
    }
}
