package me.waihanko.currencyextension;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import me.waihanko.mcextension.MCExtension;

public class MainActivity extends AppCompatActivity {

    Button change;
    EditText input;
    TextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        change = findViewById(R.id.btn_Change);
        input = findViewById(R.id.edt_input);
        amount = findViewById(R.id.txt_Amount);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = MCExtension.GetMCExtension(Integer.parseInt(input.getText().toString()));
                amount.setText(result);
            }
        });
    }


}
