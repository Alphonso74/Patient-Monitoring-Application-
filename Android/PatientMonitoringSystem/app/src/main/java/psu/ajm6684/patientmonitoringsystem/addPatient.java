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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class addPatient extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private EditText height;
    private EditText weight;
    private EditText triageTag;
    private EditText rHeartRate;
    private EditText bodyTemp;
    private EditText medications;
    private EditText surgicalHistory;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    final private CollectionReference users = db.collection("Users");
    //DatabaseReference refP = db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        //refP = FirebaseDatabase.getInstance().getReference("Users");

      //  Query query = users.







        //System.out.println(query. + "****************************************************************************");

        name = (EditText) findViewById(R.id.edit_text_title);
        description = (EditText) findViewById(R.id.edit_text_description);
        height = (EditText) findViewById(R.id.edit_text_height);
        weight = (EditText) findViewById(R.id.edit_text_weight);
        triageTag = (EditText) findViewById(R.id.edit_text_triageTag);
        rHeartRate = (EditText) findViewById(R.id.edit_text_heartRate);
        bodyTemp = (EditText) findViewById(R.id.bodyTemp);
        medications = (EditText) findViewById(R.id.medications);
        surgicalHistory = (EditText) findViewById(R.id.surgicalHistory);


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

    private void updateNurse(){

        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("patients2");

        //collectionReference.document().



    }




    private void saveNote() {



            String patientName = name.getText().toString();
            String patientDescrition = description.getText().toString();
            String triage = triageTag.getText().toString();
            String patientHeight = height.getText().toString();
            Integer patientWeight = Integer.valueOf(weight.getText().toString());
            Integer patientRHeartRate = Integer.valueOf(rHeartRate.getText().toString());
            Integer temp = Integer.valueOf(bodyTemp.getText().toString());
            String meds = medications.getText().toString();
            String sugeries = surgicalHistory.getText().toString();

            if (patientName.trim().isEmpty() || patientDescrition.trim().isEmpty() || triage.trim().isEmpty() ||
                    patientHeight.trim().isEmpty()) {

                Toast.makeText(this, "Please fill in Fields", Toast.LENGTH_LONG).show();

            } else {

                CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("patients3");

                collectionReference.add(new Note(patientName, patientDescrition, patientHeight, patientWeight, patientRHeartRate, triage, "",temp,meds,sugeries,""));

    //collectionReference.document("")
                Toast.makeText(this, "Patient Added", Toast.LENGTH_LONG).show();

                finish();
            }


    }
}
