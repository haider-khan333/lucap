package com.fyp.lucapp.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Main;
import com.fyp.lucapp.Models.Address;
import com.fyp.lucapp.Models.Person;
import com.fyp.lucapp.R;

public class LoginActivity extends AppCompatActivity {


    TextView txtEmail, txtPassword, txtRegister;

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        txtRegister = findViewById(R.id.registerLink);
        btnLogin = findViewById(R.id.login);

        btnLogin.setOnClickListener(v -> {
            //get the user from the shared preferences
            SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
            String id = sharedPreferences.getString("id", "");
            String name = sharedPreferences.getString("name", "");
            String email = sharedPreferences.getString("email", "");
            String password = sharedPreferences.getString("password", "");
            String contactNumber = sharedPreferences.getString("contactNumber", "");
            Person person = new Person(id, name, email, password, contactNumber
                    , new Address("", "", "", "",
                    ""));

            //validate the email field
            if (txtEmail.getText().toString().isEmpty()) {
                txtEmail.setError("Email is required");
            } else if (txtPassword.getText().toString().isEmpty()) {
                txtPassword.setError("Password is required");
            } else if (isRegistered(person)) {
                //if the user is registered, then check if the password is correct
                if (person.getPersonPassword().equals(txtPassword.getText().toString())
                        && person.getPersonEmail().equals(txtEmail.getText().toString())) {
                    //if the password is correct, then navigate to the home page
                    Intent intent = new Intent(LoginActivity.this, Main.class);
                    startActivity(intent);
                    this.finish();


                } else {
                    //if the password is incorrect, then show a toast message
                    Toast.makeText(LoginActivity.this,
                            "Incorrect credentials", Toast.LENGTH_SHORT).show();
                }
            } else {
                //if the user is not registered, then show a toast message
                Toast.makeText(LoginActivity.this,
                        "User not registered", Toast.LENGTH_SHORT).show();
            }
        });

        txtRegister.setOnClickListener(v -> {
            //if the user clicks on the register link, then go to the registration activity
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

        });


    }

    /**
     * This method checks if the user is registered
     *
     * @param person
     * @return
     */
    private boolean isRegistered(Person person) {
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        String contactNumber = sharedPreferences.getString("contactNumber", "");

        Person person_obj = new Person(id, name, email, password, contactNumber
                , new Address("", "", "", "", ""));
        System.out.println("email from shared prefernces: " + person_obj.getPersonEmail() +"\n" +
                "email from the text field: " + txtEmail.getText().toString());

        return person_obj.getPersonEmail().equals(person.getPersonEmail());
    }
}