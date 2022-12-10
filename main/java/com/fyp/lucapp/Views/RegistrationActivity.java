package com.fyp.lucapp.Views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Models.Address;
import com.fyp.lucapp.Models.Person;
import com.fyp.lucapp.R;

public class RegistrationActivity extends AppCompatActivity {

    TextView txtFirstName, txtLastName, txtEmail, txtPassword, txtConfirmPassword, txtContactNumber;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Initialize
        txtFirstName = findViewById(R.id.firstName);
        txtLastName = findViewById(R.id.lastName);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        txtConfirmPassword = findViewById(R.id.confirmPassword);
        txtContactNumber = findViewById(R.id.contactNumber);
        btnRegister = findViewById(R.id.nextButton);


        //check if the password and confirm password are the same
        btnRegister.setOnClickListener(v -> {
            //validate the text fields
            if (txtFirstName.getText().toString().isEmpty()) {
                txtFirstName.setError("First name is required");
            } else if (txtLastName.getText().toString().isEmpty()) {
                txtLastName.setError("Last name is required");
            } else if (txtEmail.getText().toString().isEmpty()) {
                txtEmail.setError("Email is required");
            } else if (txtPassword.getText().toString().isEmpty()) {
                txtPassword.setError("Password is required");
            } else if (txtConfirmPassword.getText().toString().isEmpty()) {
                txtConfirmPassword.setError("Confirm password is required");
            } else if (txtContactNumber.getText().toString().isEmpty()) {
                txtContactNumber.setError("Contact number is required");
            } else if (txtPassword.getText().toString().
                    equals(txtConfirmPassword.getText().toString())) {
                //if the password and confirm password are the same, then register the user
                Person person = new Person(generateID(), txtFirstName.getText().toString()
                        + " " + txtLastName.getText().toString(),
                        txtEmail.getText().toString(),
                        txtPassword.getText().toString(),
                        txtContactNumber.getText().toString(),
                        new Address("", "", "", "", ""));

                //if the user is already registered, then show a toast message
                if (isRegistered(person)) {
                    Toast.makeText(RegistrationActivity.this,
                            "User already registered", Toast.LENGTH_SHORT).show();
                } else {
                    //if the user is not registered, then register the user
                    registerUser(person);
                    Toast.makeText(RegistrationActivity.this, "User registered",
                            Toast.LENGTH_SHORT).show();
                }

            } else {
                //if the password and confirm password are not the same, then show an error message
                Toast.makeText(RegistrationActivity.this,
                        "Password and Confirm Password are not the same",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * Checks if the user is already registered
     *
     * @param person
     * @return
     */
    private boolean isRegistered(Person person) {
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        return email.equals(person.getPersonEmail()) &&
                password.equals(person.getPersonPassword());
    }

    /**
     * This method will generate a unique ID for the user
     */

    private String generateID() {
        return "ID" + System.currentTimeMillis();
    }

    /**
     * This method will store the user information in shared preferences
     */
    private void registerUser(Person person) {
        //TODO: store the user information in shared preferences

        //store the data in shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", person.getPersonId());
        editor.putString("name", person.getPersonName());
        editor.putString("email", person.getPersonEmail());
        editor.putString("password", person.getPersonPassword());
        editor.putString("contactNumber", person.getPersonContact());
        editor.apply();

    }
}
