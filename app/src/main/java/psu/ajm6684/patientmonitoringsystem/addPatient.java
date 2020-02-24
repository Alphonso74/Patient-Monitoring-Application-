package psu.ajm6684.patientmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class addPatient extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private EditText height;
    private EditText weight;
    private EditText triageTag;
    private EditText rHeartRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        name = (EditText) findViewById(R.id.edit_text_title);
        description = (EditText) findViewById(R.id.edit_text_description);
        height = (EditText) findViewById(R.id.edit_text_height);
        weight = (EditText) findViewById(R.id.edit_text_weight);
        triageTag = (EditText) findViewById(R.id.edit_text_triageTag);
        rHeartRate = (EditText) findViewById(R.id.edit_text_heartRate);

//        final String patientName = name.getText().toString();
//        final String patientDescrition = description.getText().toString();
//        final String triage = triageTag.getText().toString();
//        final String patientHeight = height.getText().toString();
//        final Integer patientWeight = Integer.valueOf(weight.getText().toString());
//        final Integer patientRHeartRate = Integer.valueOf(rHeartRate.getText().toString());

        Button discard = (Button) findViewById(R.id.button4);



        Button addPatient = (Button) findViewById(R.id.button3);
        addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(patientName.trim().isEmpty() || patientDescrition.trim().isEmpty() || triage.trim().isEmpty() ||
//                        patientHeight.trim().isEmpty()){
//
//                    Toast.makeText(psu.ajm6684.patientmonitoringsystem.addPatient.this,"Please fill in Fields",Toast.LENGTH_LONG).show();
//
//                }else {



                    saveNote();

                //}
            }
        });

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), patientFeed.class));
                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_patient_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_patient:
                saveNote();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }
    }

    private void saveNote() {



            String patientName = name.getText().toString();
            String patientDescrition = description.getText().toString();
            String triage = triageTag.getText().toString();
            String patientHeight = height.getText().toString();
            Integer patientWeight = Integer.valueOf(weight.getText().toString());
            Integer patientRHeartRate = Integer.valueOf(rHeartRate.getText().toString());

            if (patientName.trim().isEmpty() || patientDescrition.trim().isEmpty() || triage.trim().isEmpty() ||
                    patientHeight.trim().isEmpty()) {

                Toast.makeText(this, "Please fill in Fields", Toast.LENGTH_LONG).show();

            } else {

                CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("patients");

                collectionReference.add(new Note(patientName, patientDescrition, patientHeight, patientWeight, patientRHeartRate, triage));

                Toast.makeText(this, "Patient Added", Toast.LENGTH_LONG).show();

                finish();
            }


    }
}
