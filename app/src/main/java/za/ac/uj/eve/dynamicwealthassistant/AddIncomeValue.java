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
    private Button addValue;
    private Context context;

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

        value = findViewById(R.id.newIncomeValueAmount);
        addValue = findViewById(R.id.btnAddIncomeValue);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "finance")
                .allowMainThreadQueries()
                .build();

        addValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check to make sure there is a valid number before using it
                if(!value.getText().toString().isEmpty() && !value.getText().toString().equals("0"))
                {
                    Date date = new Date();
                    int val = Integer.parseInt(value.getText().toString());

                    db.dao_database().insertValue(new Value("Income", val, "Other", date,1));
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
        });
    }
//Log.d(TAG, "Preference:        " + storedPass + "       password:       " + mPasswordView.getText().toString());
}
