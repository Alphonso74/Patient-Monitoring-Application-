package psu.ajm6684.patientmonitoringsystem.createAccount;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import psu.ajm6684.patientmonitoringsystem.R;
import psu.ajm6684.patientmonitoringsystem.ui.login.LoginActivity;
//import psu.ajm6684.patientmonitoringsystem.ui.login.User;

public class createAccount extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private Button submit;
    private Spinner hopsitalSpinner;
    private Spinner positionSpinner;
    private Spinner departmentSpinner;

    String Uid;

    FirebaseFirestore firestore;



    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);



        firstName = (EditText) findViewById(R.id.editText);
        lastName = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        submit = (Button) findViewById(R.id.button);

        firestore = FirebaseFirestore.getInstance();

        hopsitalSpinner = (Spinner) findViewById(R.id.spinner);
        positionSpinner = (Spinner) findViewById(R.id.spinner2);
        departmentSpinner = (Spinner) findViewById(R.id.spinner3);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);


        if(firebaseAuth.getCurrentUser() != null){

            Toast.makeText(getApplicationContext(), "Account already created", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(createAccount.this, LoginActivity.class);
            startActivity(intent);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);


                if(password.length() < 6){

                    password.setError("Password must be longer than 6 characters!");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }


                if(firstName.getText().toString().isEmpty()
                        || lastName.getText().toString().isEmpty()
                        || email.getText().toString().isEmpty()
                        || password.getText().toString().isEmpty()) {


                    Toast.makeText(getApplicationContext(), "Empty Credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                else
                {


                    String passWord = password.getText().toString().trim();
                    String lname = lastName.getText().toString().trim();
                    String fname = firstName.getText().toString().trim();
                    final String fullName = fname + " " + lname;
                    final String hopsitall = hopsitalSpinner.getSelectedItem().toString().trim();
                    final String emailAdd = email.getText().toString().trim();
                    final String pos = positionSpinner.getSelectedItem().toString().trim();
                    final String dep = departmentSpinner.getSelectedItem().toString().trim();


                    firebaseAuth.createUserWithEmailAndPassword(emailAdd,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG).show();
                                Uid = firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firestore.collection("Users").document(Uid);

                                Map<String,Object> userMap = new HashMap<>();
                                userMap.put("fullName",fullName);
                                userMap.put("email",emailAdd);
                                userMap.put("hospital",hopsitall);
                                userMap.put("position",pos);
                                userMap.put("department",dep);

                                documentReference.set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        System.out.println("Success" + Uid);
                                    }
                                });

                                Intent intent = new Intent(createAccount.this, LoginActivity.class);
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);

                            }
                        }
                    });





                }
            }
        });


    }


    public void back(View view) {

        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

    }
}
