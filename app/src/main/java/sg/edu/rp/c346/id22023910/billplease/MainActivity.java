package sg.edu.rp.c346.id22023910.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // step 1: Declare the field variables
    EditText Amount;
    EditText PaxNo;
    ToggleButton ServiceCharge;
    ToggleButton GST;
    EditText Discount;
    RadioButton Cash;
    RadioButton PayNow;
    Button Split;
    Button Reset;
    TextView TotalBill;
    TextView EachCost;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // step 2: Link the field variables to UI components in layout
        Amount = findViewById(R.id.Amount);
        PaxNo = findViewById(R.id.PaxNo);
        ServiceCharge = findViewById(R.id.ServiceCharge);
        GST = findViewById(R.id.GST);
        Discount = findViewById(R.id.Discount);
        Cash = findViewById(R.id.Cash);
        PayNow = findViewById(R.id.PayNow);
        Split = findViewById(R.id.Split);
        Reset = findViewById(R.id.Reset);
        TotalBill = findViewById(R.id.TotalBill);
        EachCost = findViewById(R.id.EachCost);
        radioGroup =findViewById(R.id.RadioGroup);


        Split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for the action
                if (Amount.getText().toString().trim().length() != 0 && PaxNo.getText().toString().trim().length() != 0) {
                    double newAmount = 0.0;
                    if (!ServiceCharge.isChecked() && !GST.isChecked()) {
                        newAmount = Double.parseDouble(Amount.getText().toString());
                    } else if (!ServiceCharge.isChecked() && GST.isChecked()) {
                        newAmount = Double.parseDouble(Amount.getText().toString()) * 1.07;
                    } else if (ServiceCharge.isChecked() && !GST.isChecked()) {
                        newAmount = Double.parseDouble(Amount.getText().toString()) * 1.1;
                    } else if (ServiceCharge.isChecked() && GST.isChecked()) {
                        newAmount = Double.parseDouble(Amount.getText().toString()) * 1.10 * 1.07;
                    }

                    // Discount
                    if (Discount.getText().toString().trim().length() != 0) {
                        newAmount *= 1 - Double.parseDouble(Discount.getText().toString())/100;
                    }

                int numPerson = Integer.parseInt(PaxNo.getText().toString());
                int checkedRadioID = radioGroup.getCheckedRadioButtonId();


                if (numPerson != 1 && checkedRadioID == R.id.PayNow) {
                    TotalBill.setText("Total Bill: " + String.format("%.2f", newAmount));
                    EachCost.setText("Each Pays: $" + String.format("%.2f", newAmount/numPerson) + "via PayNow to 91234567");
                }

                else if (numPerson == 1 && checkedRadioID == R.id.PayNow) {
                    TotalBill.setText("Total Bill: " + String.format("%.2f", newAmount));
                    EachCost.setText("Each Pays: $" + String.format("%.2f", newAmount) + "via PayNow to 91234567");
                }

                else if (numPerson != 1 && checkedRadioID == R.id.Cash) {
                    TotalBill.setText("Total Bill: " + String.format("%.2f", newAmount));
                    EachCost.setText("Each Pays: $" + String.format("%.2f", newAmount/numPerson) + " in Cash");
                }

                else {
                    TotalBill.setText("Total Bill: " + String.format("%.2f", newAmount));
                    EachCost.setText("Each Pays: $" + String.format("%.2f", newAmount) + " in Cash");
                }

                }
                else if (Amount.getText().toString().trim().length() == 0 && PaxNo.getText().toString().trim().length() == 0) {
                    Amount.setError("Amount and no of pax required");
                }
                else if (Amount.getText().toString().trim().length() != 0 && PaxNo.getText().toString().trim().length() == 0) {
                    Amount.setError("No of pax required");
                }
                else if (Amount.getText().toString().trim().length() == 0 && PaxNo.getText().toString().trim().length() != 0) {
                    Amount.setError("Amount required");
                }

        }
            


    });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code for the action
                Amount.setText("");
                PaxNo.setText("");
                ServiceCharge.setChecked(false);
                GST.setChecked(false);
                Discount.setText("");

            }

        });

    }
}