package psu.ajm6684.patientmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class addPatient extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private EditText height;
    private EditText weight;
    private EditText rHeartRate;
    private EditText bodyTemp;
    private EditText medications;
    private EditText surgicalHistory;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    final private CollectionReference users = db.collection("Users");
    //DatabaseReference refP = db;

    final private CollectionReference nurses = db.collection("Nurse");

RadioGroup triage;

    String triageTagBoy;
    Spinner spinner;
    Spinner departmentSpinner;
    String TAG = "DocSnippets";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        //refP = FirebaseDatabase.getInstance().getReference("Users");

        //  Query query = users.


        triage = (RadioGroup) findViewById(R.id.triageGroup);

        String[] departments = new String[] {
                "Neonatal",
                "General Care",
                "Post-Operation",
        };

        triage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Toast.makeText(addPatient.this, "BUtton ClickeedeWrWR", Toast.LENGTH_LONG).show();

                if(checkedId == R.id.red) {
                    triageTagBoy = "Red";
                } else if(checkedId == R.id.green) {
                    triageTagBoy = "Green";
                } else if(checkedId == R.id.yellow) {
                    triageTagBoy = "Yellow";
                }else {
                    triageTagBoy ="Black";
                }
            }
        });

        departmentSpinner = (Spinner) findViewById(R.id.spinnerDepartment);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(addPatient.this, android.R.layout.simple_dropdown_item_1line, departments);

        adapter1.notifyDataSetChanged();
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        departmentSpinner.setAdapter(adapter1);
        departmentSpinner.setEnabled(true);

        departmentSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int postion, long arg3) {
                // TODO Auto-generated method stub
//                Object nurseName = parent.getItemAtPosition(postion).toString();
//                String  spinnerValue = parent.getItemAtPosition(postion).toString();

                ((TextView) v).setTextColor(Color.BLACK);


                departmentSpinner.setSelection(postion);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        spinner = (Spinner) findViewById(R.id.spinnerPatient);


        db.collection("Users")
                .whereEqualTo("position", "Nurse")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        final  List<String>  spinnerArray =  new ArrayList<String>();
                        QuerySnapshot queryDocumentSnapshots = task.getResult();
                        if (task.isSuccessful()) {


                            for (DocumentSnapshot document :  queryDocumentSnapshots.getDocuments()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());

                                String nurseName = Objects.requireNonNull(document.get("fullName")).toString();

                                spinnerArray.add(nurseName);

                                //Toast.makeText(addPatient.this,nurseName, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(addPatient.this, android.R.layout.simple_dropdown_item_1line, spinnerArray);


                        adapter.notifyDataSetChanged();
                        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

                        spinner.setAdapter(adapter);
                        spinner.setEnabled(true);

                    }
                });



        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int postion, long arg3) {
                // TODO Auto-generated method stub
                Object nurseName = parent.getItemAtPosition(postion).toString();
                String  spinnerValue = parent.getItemAtPosition(postion).toString();

                ((TextView) v).setTextColor(Color.BLACK);


                spinner.setSelection(postion);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

//        spinner.setSelection(1);


//        nurses.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        String data = "";
//
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            Note note = documentSnapshot.toObject(Note.class);
//                            note.setDocumentId(documentSnapshot.getId());
//
//                            String documentId = note.getDocumentId();
//                            String title = note.getTitle();
//                            String description = note.getDescription();
//
//                            data += "ID: " + documentId
//                                    + "\nTitle: " + title + "\nDescription: " + description + "\n\n";
//                        }
//
//                        textViewData.setText(data);
//                    }
//                });

//        List<String> subjects = new ArrayList<>();
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, subjects);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        nurses.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        String user = document.getString("fullName");
//                        nurses.add(user);
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });

        //System.out.println(query. + "****************************************************************************");

        name = (EditText) findViewById(R.id.edit_text_title);
        description = (EditText) findViewById(R.id.edit_text_description);
        height = (EditText) findViewById(R.id.edit_text_height);
        weight = (EditText) findViewById(R.id.edit_text_weight);
//        triageTag = (EditText) findViewById(R.id.edit_text_triageTag);
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


                saveNote(triageTagBoy);

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
        menuInflater.inflate(R.menu.new_patient_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_patient:
                saveNote(triageTagBoy);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void updateNurse() {

        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("patients2");

        //collectionReference.document().


    }


    private void saveNote(String triageTa) {

        String nurseName = spinner.getSelectedItem().toString();
        String patientName = name.getText().toString();
        String patientDescrition = description.getText().toString();
        String patientHeight = height.getText().toString();
        Integer patientWeight = Integer.valueOf(weight.getText().toString());
        Integer patientRHeartRate = Integer.valueOf(rHeartRate.getText().toString());
        Integer temp = Integer.valueOf(bodyTemp.getText().toString());
        String meds = medications.getText().toString();
        String sugeries = surgicalHistory.getText().toString();

        String department = departmentSpinner.getSelectedItem().toString();
        if (patientName.trim().isEmpty() || patientDescrition.trim().isEmpty() ||
                patientHeight.trim().isEmpty()) {

            Toast.makeText(this, "Please fill in Fields", Toast.LENGTH_LONG).show();

        } else {

            CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("patients3");

            collectionReference.add(new Note(patientName, patientDescrition, patientHeight, patientWeight, patientRHeartRate, triageTa, nurseName, temp, meds, sugeries, "", department ));

//                CollectionReference collectionReference1 = FirebaseFirestore.getInstance().collection("Nurse");
//
//                collectionReference1.add(new Note(patientName, patientDescrition, patientHeight, patientWeight, patientRHeartRate, triage, "",temp,meds,sugeries,""));
//    //collectionReference.document("")
            Toast.makeText(this, "Patient Added", Toast.LENGTH_LONG).show();

            finish();
        }


    }

    @SuppressWarnings({"unused", "Convert2Lambda"})
    public class DocSnippets {

        private static final String TAG = "DocSnippets";

        private final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        private final FirebaseFirestore db;

        DocSnippets(FirebaseFirestore db) {
            this.db = db;
        }
    }
}
