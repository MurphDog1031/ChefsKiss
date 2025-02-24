package com.example.chefskiss2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AccountInfoPage extends AppCompatActivity {
    private Account loggedInAcct;

    private static final String TAG = "AccountInfoPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info_page);
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d(TAG, "onCreate: Starting.");

        loggedInAcct = (Account) getIntent().getSerializableExtra("account");

        EditText emailEditText = (EditText) findViewById(R.id.userEmail2);
        EditText passwordEditText = (EditText) findViewById(R.id.password2);
        EditText usernameEditText = (EditText) findViewById(R.id.username2);

        emailEditText.setText(loggedInAcct.getEmail());
        emailEditText.setText(loggedInAcct.getEmail());
        usernameEditText.setText(loggedInAcct.getUsername());
        passwordEditText.setText(loggedInAcct.getPassword());

        Button backhomebtn = (Button) findViewById(R.id.buttontohomepage);

        backhomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked backtohomebtn.");

                Intent intent = new Intent(AccountInfoPage.this, Homepage.class);
                intent.putExtra("account", loggedInAcct);
                startActivity(intent);
                finishAffinity();
            }
        });

        Button editaccountbtn = (Button) findViewById(R.id.buttontoedit);

        editaccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked editaccountbtn.");

                String emailString = emailEditText.getText().toString();
                String passwordString = passwordEditText.getText().toString();
                String usernameString = usernameEditText.getText().toString();

                Account newAcc = new Account(usernameString, emailString, passwordString);

                db.updateOne(loggedInAcct, newAcc);

                Intent intent = new Intent(AccountInfoPage.this, AccountInfoPage.class);
                intent.putExtra("account", newAcc);
                startActivity(intent);
                AccountInfoPage.this.finish();

            }
        });
    }
}