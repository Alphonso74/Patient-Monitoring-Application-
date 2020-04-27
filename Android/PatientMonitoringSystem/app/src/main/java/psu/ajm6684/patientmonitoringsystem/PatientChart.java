package psu.ajm6684.patientmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.*;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class PatientChart extends AppCompatActivity {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference charts = db.collection("charts");

    private chartAdapter ChartAdapter;

    String patientName;
    String patientDescription;
    String patientHeight;
    String patientRestingHeartRate;
    String patientID;
    String bodyTemp;

    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chartfeed);
        Intent intent = getIntent();
       patientName = intent.getStringExtra("Patient Name");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setUpView(patientName);

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


        backButton = (Button) findViewById(R.id.button_menu);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void setUpView(String name) {
//            Query query = patients.whereEqualTo("triageTag","Blue");
        Query query = charts.whereEqualTo("patientName",name);






        FirestoreRecyclerOptions<chartNote> options = new FirestoreRecyclerOptions.Builder<chartNote>().setQuery(query,chartNote.class).build();

        ChartAdapter = new chartAdapter(options);



        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ChartAdapter);



                ChartAdapter.setOnItemClickListener(new chartAdapter.onItemClickListener() {

                    @Override
                    public void onItemClick(final DocumentSnapshot documentSnapshot, final int position) {
                        final AlertDialog.Builder chartDialog = new AlertDialog.Builder(new ContextThemeWrapper(PatientChart.this, android.R.style.Theme_Holo_Light));
                        chartDialog.setTitle("Patient Chart Options");
                        chartDialog.setMessage("What would you like to do?");
                        chartDialog.setCancelable(true);

                       final String userText = documentSnapshot.get("userText").toString();
//                        Toast.makeText(PatientChart.this, userText, Toast.LENGTH_SHORT).show();


                        chartDialog.setPositiveButton("Edit this Chart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String id = documentSnapshot.getId();
//                                Toast.makeText(PatientChart.this, "Edit Chart Clicked!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(PatientChart.this,editChart.class);
                                intent.putExtra("Patient Name",patientName);
                                intent.putExtra("userText",userText);
                                intent.putExtra("ID",id);
                                startActivity(intent);


                            }
                        });

//                        chartDialog.setNeutralButton("View this Chart", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
////                                Toast.makeText(PatientChart.this, "View Chart Clicked!", Toast.LENGTH_SHORT).show();
//
//
//                            }
//                        });

                        chartDialog.setNegativeButton("Delete this Chart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                ChartAdapter.deleteChart(position);


                                Toast.makeText(PatientChart.this, "Chart Deleted!", Toast.LENGTH_SHORT).show();

                            }
                        });

                        chartDialog.show();


                    }

                    @Override
                    public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
//                        Toast.makeText(PatientChart.this, "Chart Long Clicked!", Toast.LENGTH_SHORT).show();
                    }


                });

//        patientAdapter.setOnItemClickListener(new PatientAdapter.onItemClickListener() {
//
//            @Override
//            public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
//                final AlertDialog.Builder alertDlg = new AlertDialog.Builder(patientFeed.this);
//                alertDlg.setTitle("Patient Options");
//                alertDlg.setMessage("What would you like to do?");
//                alertDlg.setCancelable(true);
//
//                alertDlg.setPositiveButton("Delete This Patient", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//
//                        final AlertDialog.Builder alertDlg2 = new AlertDialog.Builder(patientFeed.this);
//                        alertDlg2.setTitle("Delete this patient");
//                        alertDlg2.setMessage("Are you sure?");
//                        alertDlg2.setCancelable(true);
//
//                        alertDlg2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//
//
//                                patientAdapter.deletePatient(position);
//
//                            }
//
//
//                        });
//
//                        alertDlg2.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//
//                            }
//                        });
//
//                        alertDlg2.show();
//                    }
//
//
//                });
//
//                alertDlg.setNegativeButton("Add Nurse", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        final Dialog dialog1 = new Dialog(patientFeed.this);
//                        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        dialog1.setCancelable(false);
//                        dialog1.setContentView(R.layout.addnursedialog);
//
//                        final EditText et = dialog1.findViewById(R.id.et);
//
//                        //String id = patientAdapter.getItemId(position);
//
//                        Button btnok = (Button) dialog1.findViewById(R.id.btnok);
//                        btnok.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//
//                                String nurse = et.getText().toString();
//
//
//
//                                //Note patient = documentSnapshot.toObject(Note.class);
//
//
//                                final DocumentReference patientItem = db.collection("patients3").document(documentSnapshot.getId());
//
//                                //update
//
//                                if(nurse.isEmpty()){
//
//                                    Toast toast = Toast.makeText(getApplicationContext(),"Please Enter the name of the Nurse",Toast.LENGTH_SHORT);
//                                    toast.show();
//                                }
//                                else {
//
//                                    patientItem.update("activeNurse", nurse);
//
//                                    Toast.makeText(patientFeed.this, " Nurse Added", Toast.LENGTH_SHORT).show();
//
//
//
//                                    dialog1.dismiss();
//                                }
//                            }
//                        });
//
//                        Button btncn = (Button) dialog1.findViewById(R.id.btncn);
//                        btncn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                dialog1.dismiss();
//                            }
//                        });
//
//                        dialog1.show();
//
//                    }
//                });
//
//                alertDlg.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//
//                    }
//                });
//
//
//
//
//
//                alertDlg.show();
//
//
//
//
//            }
//
//
//            @Override
//            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//
//                Note note = documentSnapshot.toObject(Note.class);
//                assert note != null;
//                String pname = note.getPatientName();
//                String pdescription = note.getDescription();
//                String pheight = note.getHeight();
//                String pweight = String.valueOf(note.getWeight());
//                String prhr = String.valueOf(note.getrHeartRate());
//                String id = documentSnapshot.getId();
//                Integer position1 = position;
//                String bodyTemp = String.valueOf(note.getBodyTempature());
//                String activeNurse = note.getActiveNurse();
//                String medications = note.getMedications();
//                String surgicalHistory = note.getSurgicaHistory();
//                String standingO = note.getStandingOrder();
//
//
//                DocumentSnapshot DocSnap = documentSnapshot;
//
//
//                DocumentReference ref = documentSnapshot.getReference();
//
//                Intent intent = new Intent(patientFeed.this,patientProfile.class);
//                intent.putExtra("Patient Name",pname);
//                intent.putExtra("Patient Description",pdescription);
//                intent.putExtra("Patient Height",pheight);
//                intent.putExtra("Patient Weight",pweight);
//                intent.putExtra("Patient Resting Heart Rate",prhr);
//                intent.putExtra("Patient ID",id);
//                intent.putExtra("position",position1);
//                intent.putExtra("bodyTemp",bodyTemp);
//                intent.putExtra("nurse",activeNurse);
//                intent.putExtra("medications",medications);
//                intent.putExtra("surgicalH",surgicalHistory);
//                intent.putExtra("standingO",standingO);
//                //intent.putExtra("DocSnap", (Serializable) documentSnapshot);
//                //intent.putExtra("Firebse Reference", (Serializable) ref);
//
//                startActivity(intent);
//
//
//
//
//
//
//            }
        }

    @Override
    protected void onStart(){
        super.onStart();
        ChartAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        ChartAdapter.stopListening();
    }
}
