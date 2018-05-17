package za.ac.uj.eve.dynamicwealthassistant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Date;

public class ManageBalanceActivity extends AppCompatActivity {
    private EditText value;
    private Switch recur;
    private Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_balance);

        //Set Icon in Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_action_wallet_filled_money);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Declare Value
        value = findViewById(R.id.txtSetBalance);
        //Declare Button
        btnChange = findViewById(R.id.btnChange);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChange();
            }
        });
    }

    public void setChange()
    {
        if(!value.getText().toString().isEmpty() && !value.getText().toString().equals("0"))
        {
            recur = (Switch) findViewById(R.id.swRecur);
            final int val = Integer.parseInt(value.getText().toString());
            SharedPreferences preferences;
            SharedPreferences.Editor editor;

            if(recur.isChecked())
            {
                // Shared Preferences for balance
                preferences = getSharedPreferences("BUDGET", MODE_PRIVATE);
                editor = preferences.edit();
                // Commit to shared preferences
                editor.putInt("BudgetData",val);
                editor.commit();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
                mBuilder.setTitle("Set Balance Now");
                mBuilder.setMessage("Would you like to set balance now?");
                mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Shared Preferences for balance
                        SharedPreferences preferences = getSharedPreferences("Balance", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        // Commit to shared preferences
                        editor.putInt("BalanceData",val);
                        editor.commit();
                        startActivity(new Intent(ManageBalanceActivity.this, MainActivity.class));
                        dialog.dismiss();
                    }
                });
                mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ManageBalanceActivity.this, MainActivity.class));
                        dialog.dismiss();
                    }
                });
                //Build and show the alert
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();
            }
            else
            {
                preferences = getSharedPreferences("Balance", MODE_PRIVATE);
                editor = preferences.edit();
                // Commit to shared preferences
                editor.putInt("BalanceData",val);
                editor.commit();
                startActivity(new Intent(ManageBalanceActivity.this, MainActivity.class));
            }

        }
        else
        {
            Toast.makeText(this, "Please enter a number greater than 0", Toast.LENGTH_SHORT).show();
        }
    }
}
