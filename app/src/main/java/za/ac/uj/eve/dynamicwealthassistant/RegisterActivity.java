package za.ac.uj.eve.dynamicwealthassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    // UI references.
    private EditText mPasswordView, mPasswordViewConfirmed;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPasswordView = (EditText) findViewById(R.id.pinPassword1);
        mPasswordViewConfirmed = (EditText) findViewById(R.id.pinPasswordConfirmed);

//        mPasswordViewConfirmed.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
//                    attemptRegister(textView);
//                    return true;
//                }
//                return false;
//            }
//        });

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
        String pass = mPasswordView.getText().toString();
        String passCon = mPasswordViewConfirmed.getText().toString();
//        Log.d(TAG, "2 password:        " + mPasswordView.getText().toString() + "      passwordConfirmed:     " + mPasswordViewConfirmed.getText().toString());
//        Log.d(TAG, "3 password:        " + pass + "      passwordConfirmed:     " + passCon);
        if(pass.equals(passCon))
        {
            // Shared Preferences
            SharedPreferences preferences = getSharedPreferences("MYLOGIN", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            // Commit to shared preferences
            editor.putString("LoginData",pass);
            editor.commit();
            // Go back to login
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
        else
        {
            Toast.makeText(this, "PINs do not match", Toast.LENGTH_SHORT).show();
        }
    }
}
