package za.ac.uj.eve.dynamicwealthassistant;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    // UI references.
    private EditText mPasswordView, mPasswordViewConfirmed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPasswordView = (EditText) findViewById(R.id.pinPassword1);
        mPasswordViewConfirmed = (EditText) findViewById(R.id.pinPasswordConfirmed);

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister(view);
            }
        });
    }

    private void attemptRegister(View view)
    {
        final String pass = mPasswordView.getText().toString();
        String passCon = mPasswordViewConfirmed.getText().toString();
        //Check if PINS are equal
        if(pass.equals(passCon))
        {
            // Shared Preferences
            SharedPreferences preferences = getSharedPreferences("MYLOGIN", MODE_PRIVATE);
            String storedPass = preferences.getString("LoginData", "-1");

            if(!storedPass.equals("-1"))
            {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
                mBuilder.setTitle(R.string.dialog_register_title);
                mBuilder.setMessage(R.string.dialog_register_message);
                mBuilder.setPositiveButton("Overwrite", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Delete all values
                        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "finance")
                                .allowMainThreadQueries()
                                .build();
                        db.dao_database().deleteAll();

                        // Update Login Shared Preferences
                        SharedPreferences preferences = getSharedPreferences("MYLOGIN", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        // Commit to shared preferences
                        editor.putString("LoginData",pass);
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
                        editor.putInt("BudgetData",0);
                        editor.commit();

                        dialog.dismiss();
                        // Go back to login
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
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
            else
            {
                // Update Login Shared Preferences
                SharedPreferences.Editor editor = preferences.edit();
                // Commit to shared preferences
                editor.putString("LoginData",pass);
                editor.commit();
                // Go back to login
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        }
        else
        {
            Toast.makeText(this, "PINs do not match", Toast.LENGTH_SHORT).show();
        }
    }
}
