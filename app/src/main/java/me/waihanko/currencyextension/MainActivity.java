package me.waihanko.currencyextension;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                String result = GetMCExtension(input.getText().toString(), 6);
                amount.setText(result);
            }

        });
    }

    // 6, 7, 8 -> "သိန်း", "သန်း", "ကုဋေ"
    private String GetMCExtension(String originalInput, int format) {
        if (originalInput.length() > 15) {
            return "Number Count Must Less Than 16";
        }
        if (originalInput.length() > 10) {
            format = 7;
        }
        if (originalInput.length() > 12) {
            format = 8;
        }
        String[] mcFormat = SplitAmount(originalInput, format, true);
        String prefix = "";
        String output = "";
        for (int i = 0; i < mcFormat.length; i++) {
            if (i == 0 && originalInput.length() > format) output += Cal(mcFormat[i] + "0");
            else if (originalInput.length() > format) {
                prefix = getPrefix(format);
                output += Cal(mcFormat[i]);
            } else {
                output += Cal(mcFormat[i]);
            }
        }
        return prefix + output;
    }

    private String getPrefix(int prefixIndex) {
        String[] prefixExtension = {"သိန်း", "သန်း", "ကုဋေ"};
        return prefixExtension[prefixIndex - 6];
    }

    private String Cal(String originalInput) {

        String output = "";
        String[] extension = {"ကျပ်", "ဆယ်", "ရာ", "ထောင်", "သောင်း", "သိန်း", "သန်း", "ကုဋေ"};
        String[] deepExtension = {"ကျပ်", "ဆယ့်", "ရာ့", "ထောင့်", "သောင်း", "သိန်း", "သန်း", "ကုဋေ"};

        char[] originalInputChar = originalInput.toCharArray();
        for (int i = 0; i < originalInputChar.length; i++) {
            output += GetMMLongAmount2(originalInputChar[i]);
            if (originalInputChar[i] != '0') {
                if (i + 1 < originalInputChar.length && originalInputChar[i + 1] != '0') {
                    output += deepExtension[originalInputChar.length - i - 1];
                } else {
                    output += extension[originalInputChar.length - i - 1];
                }
            }
        }
        return output;
    }

    public static String[] SplitAmount(String amount, int subLen, boolean backwards) {
        assert amount != null;
        int groups = amount.length() % subLen == 0 ? amount.length() / subLen : amount.length() / subLen + 1;
        String[] strs = new String[groups];
        if (backwards) {
            for (int i = 0; i < groups; i++) {
                int beginIndex = amount.length() - subLen * (i + 1);
                int endIndex = beginIndex + subLen;
                if (beginIndex < 0)
                    beginIndex = 0;
                strs[groups - i - 1] = amount.substring(beginIndex, endIndex);
            }
        } else {
            for (int i = 0; i < groups; i++) {
                int beginIndex = subLen * i;
                int endIndex = beginIndex + subLen;
                if (endIndex > amount.length())
                    endIndex = amount.length();
                strs[i] = amount.substring(beginIndex, endIndex);
            }
        }
        return strs;
    }

    private String GetMMLongAmount2(char input) {
        String output = "";
        switch (input) {
            case '0':
            case ',':
                output = "";
                break;
            case '1':
                output = "တစ်";
                break;
            case '2':
                output = "နှစ်";
                break;
            case '3':
                output = "သုံး";
                break;
            case '4':
                output = "လေး";
                break;
            case '5':
                output = "ငါး";
                break;
            case '6':
                output = "ခြောက်";
                break;
            case '7':
                output = "ခုနှစ်";
                break;
            case '8':
                output = "ရှစ်";
                break;
            case '9':
                output = "ကိုး";
                break;
        }
        return output;
    }

}
