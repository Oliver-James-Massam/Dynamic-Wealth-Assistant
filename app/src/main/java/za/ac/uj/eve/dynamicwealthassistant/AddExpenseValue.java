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

public class AddExpenseValue extends AppCompatActivity {
    private EditText value;
    private Context context;
    private AppDatabase db;
    //Category Buttons
    private Button btnExpenceBills;
    private Button btnExpenceCar;
    private Button btnExpenceHouse;
    private Button btnExpenceFood;
    private Button btnExpenceMedical;
    private Button btnExpenceEatingOut;
    private Button btnExpenceEntertainment;
    private Button btnExpenceTransport;
    private Button btnExpenceOther;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense_value);
        context = this;

        //Set Icon in Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_action_wallet_filled_money);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //Declare Value
        value = findViewById(R.id.newExpenseValueAmount);
        //Declare Buttons
        btnExpenceBills = findViewById(R.id.btnExpenceBills);
        btnExpenceCar = findViewById(R.id.btnExpenceCar);
        btnExpenceEatingOut = findViewById(R.id.btnExpenceEatingOut);
        btnExpenceEntertainment = findViewById(R.id.btnExpenceEntertainment);
        btnExpenceFood = findViewById(R.id.btnExpenceFood);
        btnExpenceHouse = findViewById(R.id.btnExpenceHouse);
        btnExpenceMedical = findViewById(R.id.btnExpenceMedical);
        btnExpenceTransport = findViewById(R.id.btnExpenceTransport);
        btnExpenceOther = findViewById(R.id.btnExpenceOther);
        //Open DB
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "finance")
                .allowMainThreadQueries()
                .build();

        btnExpenceBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Bills");
            }
        });

        btnExpenceTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Transport");
            }
        });

        btnExpenceHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("House");
            }
        });

        btnExpenceCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Car");
            }
        });

        btnExpenceFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Food");
            }
        });

        btnExpenceMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Medical");
            }
        });

        btnExpenceEatingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("EatingOut");
            }
        });

        btnExpenceEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("Entertainment");
            }
        });

        btnExpenceOther.setOnClickListener(new View.OnClickListener() {
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

            db.dao_database().insertValue(new Value("Expense", val, category, date,1));
            // Shared Preferences for balance
            SharedPreferences preferences = getSharedPreferences("BALANCE", MODE_PRIVATE);
            int balance = preferences.getInt("BalanceData", 0);
            balance = balance - val;

            SharedPreferences.Editor editor = preferences.edit();
            // Commit to shared preferences
            editor.putInt("BalanceData",balance);
            editor.commit();
            startActivity(new Intent(AddExpenseValue.this, MainActivity.class));
        }
        else
        {
            Toast.makeText(context, "Please enter a number greater than 0", Toast.LENGTH_SHORT).show();
        }
    }
}
