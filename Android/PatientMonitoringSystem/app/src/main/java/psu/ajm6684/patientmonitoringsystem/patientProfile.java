package psu.ajm6684.patientmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class patientProfile extends AppCompatActivity {
    PatientAdapter patientAdapter;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    Spinner spinner;
    String nurseName ;
CollectionReference userCharts = db.collection("charts");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientprofilelinear);



        Button addNurse = (Button) findViewById(R.id.button5);
        final Button createChart = (Button) findViewById(R.id.button7);
        Intent intent = getIntent();

        Button backButton = (Button) findViewById(R.id.back2feed);
        final Button viewCharts = (Button) findViewById(R.id.viewCharts);
        final String patientName = intent.getStringExtra("Patient Name");
        final int count = 0;

        Query query = userCharts.whereEqualTo("patientName",patientName);

        db.collection("charts").whereEqualTo("patientName",patientName).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
        @Override
        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
            // [START_EXCLUDE]
            int count = 0;
            for (QueryDocumentSnapshot snap : queryDocumentSnapshots) {


                if(snap!= null ){

                    count++;
                }


            }
            Log.d("Data ------", "Should be zero" + count);

            if(count == 0){


                viewCharts.setAlpha(.5f);
                viewCharts.setClickable(false);
            }
            // [END_EXCLUDE]
        }
    });

        Button standingOrderButton = (Button) findViewById(R.id.button6);

        FirebaseFirestore  firebaseFirestore = FirebaseFirestore.getInstance();




        final String patientDescription = intent.getStringExtra("Patient Description");
        final String patientHeight = intent.getStringExtra("Patient Height");
        final String patientWeight = intent.getStringExtra("Patient Weight");
        final String patientRestingHeartRate = intent.getStringExtra("Patient Resting Heart Rate");
        final String patientID = intent.getStringExtra("Patient ID");
        final Integer position = intent.getIntExtra("position",0);
        final String bodyTemp = intent.getStringExtra("bodyTemp");
        final String activeNurse = intent.getStringExtra("nurse");
        final String medications = intent.getStringExtra("medications");
        final String sHistory = intent.getStringExtra("surgicalH");
        final String standingO = intent.getStringExtra("standingO");


        final DocumentReference patientItem = firebaseFirestore.collection("patients3").document(patientID);


        final TextView pnameView = (TextView) findViewById(R.id.textView10);
        TextView pdescriptionView = (TextView) findViewById(R.id.textView13);
        TextView pRestRate = (TextView) findViewById(R.id.textView22);
        TextView pHeight  = (TextView) findViewById(R.id.textView23);
        TextView pWeight = (TextView) findViewById(R.id.textView24);
        TextView bodyTemp1 = (TextView) findViewById(R.id.textView29);
        TextView meds = (TextView) findViewById(R.id.textView30);
        TextView sHistory1 = (TextView) findViewById(R.id.textView31);
        TextView aNurse = (TextView) findViewById(R.id.textView25);
        TextView standingOrder = (TextView) findViewById(R.id.textView17);



        bodyTemp1.setText(bodyTemp);
        meds.setText(medications);
        sHistory1.setText(sHistory);
        aNurse.setText(activeNurse);
        pnameView.setText(patientName);
        pRestRate.setText(patientRestingHeartRate);
        pHeight.setText(patientHeight);
        pWeight.setText(patientWeight);
        pdescriptionView.setText(patientDescription);
        standingOrder.setText(standingO);

        viewCharts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(patientProfile.this,PatientChart.class);
                intent.putExtra("Patient Name",patientName);
                intent.putExtra("Patient Description",patientDescription);
                intent.putExtra("Patient Height",patientHeight);
                intent.putExtra("Patient Weight",patientWeight);
                intent.putExtra("Patient Resting Heart Rate",patientRestingHeartRate);
                intent.putExtra("Patient ID",patientID);
                intent.putExtra("position",position);
                intent.putExtra("bodyTemp",bodyTemp);
                intent.putExtra("medications",medications);
                intent.putExtra("surgicalH",sHistory);
                intent.putExtra("standingO",standingO);
                intent.putExtra("activeNurse",activeNurse);



                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(patientProfile.this,patientFeed.class);
//                startActivity(intent);
                finish();



            }
        });


        addNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(patientProfile.this, android.R.style.Theme_Holo_Light));
                TextView title = new TextView(patientProfile.this);
                title.setText("Choose a Nurse:");
                title.setPadding(10, 10, 10, 10);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(Color.rgb(0, 153, 204));
                title.setTextSize(23);
                builder.setCustomTitle(title);

                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout_spinners = inflater.inflate(R.layout.addnursedialog,null);
                spinner = (Spinner) layout_spinners.findViewById(R.id.spinner_titulo_carpetas);

                builder.setPositiveButton("Add Nurse", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        nurseName = spinner.getSelectedItem().toString();

                        patientItem.update("activeNurse", nurseName);


                        Intent intent = new Intent(patientProfile.this,patientProfile.class);
                            intent.putExtra("Patient Name",patientName);
                            intent.putExtra("Patient Description",patientDescription);
                            intent.putExtra("Patient Height",patientHeight);
                            intent.putExtra("Patient Weight",patientWeight);
                            intent.putExtra("Patient Resting Heart Rate",patientRestingHeartRate);
                            intent.putExtra("Patient ID",patientID);
                            intent.putExtra("position",position);
                            intent.putExtra("bodyTemp",bodyTemp);
                            intent.putExtra("nurse",nurseName);
                            intent.putExtra("medications",medications);
                            intent.putExtra("surgicalH",sHistory);
                            intent.putExtra("standingO",standingO);


                            startActivity(intent);


                    }
                });


                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                builder.setView(layout_spinners);
                builder.setCancelable(true);
                builder.show();


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

                                       // Toast.makeText(patientProfile.this,nurseName, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
//                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(patientProfile.this, android.R.layout.simple_dropdown_item_1line, spinnerArray);


                                adapter.notifyDataSetChanged();
                                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

                                spinner.setAdapter(adapter);
                                spinner.setEnabled(true);

                            }
                        });



                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                    @Override
                    public void onItemSelected(AdapterView<?> parent, View v,
                                               int postion, long arg3) {
                        // TODO Auto-generated method stub
                        Object nurseName = parent.getItemAtPosition(postion).toString();
                        String  spinnerValue = parent.getItemAtPosition(postion).toString();



                        nurseName = parent.getItemAtPosition(position).toString();
                        ((TextView) v).setTextColor(Color.BLACK);




                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }
                });

//
//

            }
            //  @Override
            //public void onClick(View v) {



//                final Dialog dialog = new Dialog(patientProfile.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setCancelable(true);
//                dialog.setContentView(R.layout.addnursedialog);
//
//                //final EditText et = dialog.findViewById(R.id.et);
//
//               final Spinner nurseSpinner = (Spinner) findViewById(R.id.spinnerAddnurse);
//
//
//                db.collection("Users")
//                        .whereEqualTo("position", "Nurse")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                final List<String> spinnerArray =  new ArrayList<String>();
//                                QuerySnapshot queryDocumentSnapshots = task.getResult();
//                                if (task.isSuccessful()) {
//
//
//                                    for (DocumentSnapshot document :  queryDocumentSnapshots.getDocuments()) {
//                                        //Log.d(TAG, document.getId() + " => " + document.getData());
//
//                                        String nurseName = Objects.requireNonNull(document.get("fullName")).toString();
//
//                                        spinnerArray.add(nurseName);
//
//                                        //Toast.makeText(addPatient.this,nurseName, Toast.LENGTH_SHORT).show();
//                                    }
//                                } else {
////                                    Log.d(TAG, "Error getting documents: ", task.getException());
//                                }
//                                ArrayAdapter<String> adapter = new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_dropdown_item_1line, spinnerArray);
//
//
//                                adapter.notifyDataSetChanged();
//                                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//
//                                nurseSpinner.setAdapter(adapter);
//                                nurseSpinner.setEnabled(true);
//
//                                nurseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//
//                                    @Override
//                                    public void onItemSelected(AdapterView<?> parent, View v,
//                                                               int postion, long arg3) {
//                                        // TODO Auto-generated method stub
//                                        Object nurseName = parent.getItemAtPosition(postion).toString();
//                                        String  spinnerValue = parent.getItemAtPosition(postion).toString();
//
//                                        ((TextView) v).setTextColor(Color.BLACK);
//
//
//                                        nurseSpinner.setSelection(postion);
//
//
//                                    }
//
//                                    @Override
//                                    public void onNothingSelected(AdapterView<?> arg0) {
//                                        // TODO Auto-generated method stub
//
//                                    }
//                                });
//
//
//                            }
//                        });
//
//
//



//                Button btnok = (Button) dialog.findViewById(R.id.btnok);
//                btnok.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//                        String nurse = nurseSpinner.getSelectedItem().toString();
//
//
//                        if(nurse.isEmpty()){
//
//                            Toast toast = Toast.makeText(getApplicationContext(),"Please Select the name of the Nurse",Toast.LENGTH_SHORT);
//                            toast.show();
//                        }
//                        else {
//                            patientItem.update("activeNurse", nurse);
//
//                            Toast.makeText(patientProfile.this, " Nurse Added", Toast.LENGTH_SHORT).show();
//
//
//                            Intent intent = new Intent(patientProfile.this,patientProfile.class);
//                            intent.putExtra("Patient Name",patientName);
//                            intent.putExtra("Patient Description",patientDescription);
//                            intent.putExtra("Patient Height",patientHeight);
//                            intent.putExtra("Patient Weight",patientWeight);
//                            intent.putExtra("Patient Resting Heart Rate",patientRestingHeartRate);
//                            intent.putExtra("Patient ID",patientID);
//                            intent.putExtra("position",position);
//                            intent.putExtra("bodyTemp",bodyTemp);
//                            intent.putExtra("nurse",nurse);
//                            intent.putExtra("medications",medications);
//                            intent.putExtra("surgicalH",sHistory);
//                            intent.putExtra("standingO",standingO);
//
//
//                            startActivity(intent);
//
//
//
//                            //dialog.dismiss();
//                        }
//                    }
//                });

//                Button btncn = (Button) dialog.findViewById(R.id.btncn);
//                btncn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//
//                dialog.show();
//


           // }
        });

        standingOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(patientProfile.this,standingOrder.class);
                intent.putExtra("Patient ID",patientID); //Changeeeeeeeeeeeee***
                intent.putExtra("Patient Name", patientName);
                startActivity(intent);
            }
        });

        createChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(patientProfile.this,chart.class);
                intent.putExtra("Patient Name",patientName);
                intent.putExtra("Patient Description",patientDescription);
                intent.putExtra("Patient Height",patientHeight);
                intent.putExtra("Patient Weight",patientWeight);
                intent.putExtra("Patient Resting Heart Rate",patientRestingHeartRate);
                intent.putExtra("Patient ID",patientID);
                intent.putExtra("position",position);
               intent.putExtra("bodyTemp",bodyTemp);
                intent.putExtra("nurse",activeNurse);
                intent.putExtra("medications",medications);
                intent.putExtra("surgicalH",sHistory);
                intent.putExtra("standingO",standingO);
                startActivity(intent);


            }
        });


    }



    private void updateNurse(View v, String Name){

//        patientAdapter.updateNurse(position,name);

    }
}
