package sg.edu.rp.c346.id20020509.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvName;
    EditText etName;
    TextView tvTelephone;
    EditText etTelephone;
    TextView tvSize;
    EditText etSize;
    CheckBox cbSmoking;
    DatePicker dp;
    TimePicker tp;
    Button btReserve;
    Button btReset;
    TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        etName = findViewById(R.id.etName);
        tvTelephone = findViewById(R.id.tvTelephone);
        etTelephone = findViewById(R.id.etTelephone);
        tvSize = findViewById(R.id.tvSize);
        etSize = findViewById(R.id.etSize);
        cbSmoking = findViewById(R.id.cbSmoking);
        dp = findViewById(R.id.dp);
        tp = findViewById(R.id.tp);
        btReserve = findViewById(R.id.btReserve);
        btReset = findViewById(R.id.btReset);
        tvSummary = findViewById(R.id.tvSummary);

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etTelephone.setText("");
                etSize.setText("");
                cbSmoking.setSelected(false);
                dp.updateDate(2021,6,1);
                tp.setCurrentHour(7);
                tp.setCurrentMinute(30);
            }
        });

        btReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String summarySmoking = "";
                int day = dp.getDayOfMonth();
                int month = dp.getMonth() + 1;
                int year = dp.getYear();
                String summaryDP = "Date: " + day + "/" + month + "/" + year;
                int hour = tp.getCurrentHour();
                int minute = tp.getCurrentMinute();
                String summaryTP = "Time: " + hour + ":" + minute;

                String summary = "";

                if (cbSmoking.isChecked()) {
                    summarySmoking = "smoking area";
                }
                else {
                    summarySmoking = "non-smoking area";
                }

                if (checkAllET(etName) && checkAllET(etTelephone) && checkAllET(etSize)) {
                    summary += String.format("Name: %s\n", etName.getText().toString());
                    summary += String.format("Telephone: %s\n", etTelephone.getText().toString());
                    summary += String.format("Size: %s\n", etSize.getText().toString());
                    summary += String.format("%s\n", summarySmoking);
                    summary += String.format("%s\n", summaryDP);
                    summary += String.format("%s\n", summaryTP);
                }
                else {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
                }

                tvSummary.setText(summary);

            }
        });

    }

    private boolean checkAllET (EditText etInput) {
        if (etInput.getText().toString().trim().length() != 0) {
            return true;
        }
       else {
           return false;
        }
    }



}