package za.ac.uj.eve.dynamicwealthassistant;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class CreateAddValue extends AppCompatActivity {
    private EditText value;
    private Button addValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_value);

        value = findViewById(R.id.newValueAmount);
        addValue = findViewById(R.id.btnAddValue);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "finance")
                .allowMainThreadQueries()
                .build();

        addValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                db.dao_database().insertValue(new Value("Income", Integer.parseInt(value.getText().toString()), "Other", date,1));
                startActivity(new Intent(CreateAddValue.this, MainActivity.class));
            }
        });
    }

}
