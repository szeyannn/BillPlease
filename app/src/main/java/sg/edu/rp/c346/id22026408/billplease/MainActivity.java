package sg.edu.rp.c346.id22026408.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import sg.edu.rp.c346.id22026408.billplease.R;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText pax;
    ToggleButton SVS;
    ToggleButton GST;
    EditText discount;
    RadioGroup paymentType;
    RadioButton cash;
    RadioButton paynow;
    Button split;
    Button reset;
    TextView total;
    TextView splitAmnt;






    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount =findViewById(R.id.tvAmount);
        pax = findViewById(R.id.tvNumOfPeople);
        SVS = findViewById(R.id.SVS_btn);
        GST = findViewById(R.id.GST_btn);
        discount = findViewById(R.id.discount_Input);
        paymentType = findViewById(R.id.type);
        cash = findViewById(R.id.cash_radio);
        paynow = findViewById(R.id.paynow_radio);
        split = findViewById(R.id.split_btn);
        reset = findViewById(R.id.reset_btn);
        total = findViewById(R.id.displayBill);
        splitAmnt = findViewById(R.id.tvSplitAmount);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                pax.setText("");
                SVS.setChecked(false);
                GST.setChecked(false);
                discount.setText("");
            }
        });




        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double updatedAmount = 0.0;
                if (!SVS.isChecked() && !GST.isChecked()) {
                    updatedAmount = Double.parseDouble(amount.getText().toString());
                } else if (SVS.isChecked() && !GST.isChecked()){
                    updatedAmount = Double.parseDouble(amount.getText().toString()) * 1.1;
                } else if (!SVS.isChecked() && GST.isChecked()) {
                    updatedAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                } else {
                    updatedAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                }

                if (discount.getText().toString().length() != 0) {
                    updatedAmount = (1 - (Double.parseDouble(discount.getText().toString())) / 100) * updatedAmount;
                }

                total.setText("Total Bill: $" + String.format("%.2f", updatedAmount));
                int numPax = Integer.parseInt(pax.getText().toString());
                int checkedRadioId = paymentType.getCheckedRadioButtonId();
                if (checkedRadioId == R.id.cash_radio){
                    if (numPax > 1) {
                        splitAmnt.setText("Each Pays: $" + String.format("%.2f", updatedAmount / numPax) + " in cash.");
                    } else {
                        splitAmnt.setText("Each Pays: $" + updatedAmount + " in cash.");
                    }


                } else {
                    if (numPax > 1) {
                        splitAmnt.setText("Each Pays: $" + String.format("%.2f", updatedAmount / numPax) + "via Paynow");
                    } else {
                        splitAmnt.setText("Each Pays: $" + updatedAmount + "via Paynow");
                    }

                }
            }



        });










    }
}
