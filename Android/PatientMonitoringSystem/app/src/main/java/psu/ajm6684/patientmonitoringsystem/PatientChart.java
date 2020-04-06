package psu.ajm6684.patientmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.*;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chartfeed);
        Intent intent = getIntent();
        String patientName = intent.getStringExtra("Patient Name");
        Toast.makeText(PatientChart.this, patientName, Toast.LENGTH_SHORT).show();

        setUpView(patientName);

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
