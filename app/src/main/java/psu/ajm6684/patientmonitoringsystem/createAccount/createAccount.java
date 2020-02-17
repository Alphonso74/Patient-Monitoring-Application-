package psu.ajm6684.patientmonitoringsystem.createAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




import java.util.HashMap;
import java.util.Map;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import psu.ajm6684.patientmonitoringsystem.R;
import psu.ajm6684.patientmonitoringsystem.ui.login.User;

public class createAccount extends AppCompatActivity {

    private static final String KEY_FNAME = "First Name";
    private static final String KEY_LNAME = "Last Name";
    private static final String KEY_UNAME = "User Name";
    private static final String KEY_EID = "Employee ID";

    private FirebaseAuth auth;
    private EditText firstName;
    private EditText lastName;
    private EditText userName;
    private EditText employeeID;
    private Button submit;
    private DatabaseReference mDatabase;
    private Firebase mRef;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Firebase.setAndroidContext(this);

        mRef = new Firebase("https://patient-monitoring-syste-39706.firebaseio.com/");

        firstName = (EditText) findViewById(R.id.editText);
        lastName = (EditText) findViewById(R.id.editText2);
        userName = (EditText) findViewById(R.id.editText3);
        employeeID = (EditText) findViewById(R.id.editText4);
        submit = (Button) findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase mydatabase = openOrCreateDatabase("Patient Monitoring System",MODE_PRIVATE,null);

//                Map<String,Object> myMap = new HashMap<String,Object>();
//
//                String fname = firstName.getText().toString();
//                String lname = lastName.getText().toString();
//                String uName = userName.getText().toString();
//                String eID = employeeID.getText().toString();
//
//                myMap.put(KEY_FNAME,fname);
//                myMap.put(KEY_LNAME,lname);
//                myMap.put(KEY_UNAME,uName);
//                myMap.put(KEY_EID,eID);
//
////                db.collection("demoProviders").document("First Provider");
//
//                db.collection("Doctors").document().set(myMap);

            }
        });


    }






}
