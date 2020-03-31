package psu.ajm6684.patientmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.io.Serializable;
import java.lang.ref.Reference;

import psu.ajm6684.patientmonitoringsystem.ui.login.LoginActivity;
import android.os.Bundle;

public class postOpPatientFeed extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference patients = db.collection("patients3");
    //private CollectionReference bluePatients = db.collection("patients").whereEqualTo("triageTag","Blue");

    private PatientAdapter patientAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_op_patient_feed);

        setUpView();
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        Query query = patients;
//        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();

//        patientAdapter = new PatientAdapter(options);

//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(patientAdapter);


        Button memuButton = (Button) findViewById(R.id.button_menu);




        memuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder menuDialog = new AlertDialog.Builder(postOpPatientFeed.this);
                menuDialog.setTitle("User Options");
                menuDialog.setMessage("What would you like to do?");
                menuDialog.setCancelable(true);

                menuDialog.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(postOpPatientFeed.this, " Goodbye", Toast.LENGTH_SHORT).show();


                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();


                    }


                });

                menuDialog.setPositiveButton("More Options", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final AlertDialog.Builder menuDialog23 = new AlertDialog.Builder(postOpPatientFeed.this);
                        menuDialog23.setTitle("More Options");
                        menuDialog23.setMessage("What would you like to do?");
                        menuDialog23.setCancelable(true);

                        menuDialog23.setPositiveButton("Add Patient", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(postOpPatientFeed.this, addPatient.class));

                            }
                        });

                        menuDialog23.setNegativeButton("Switch to General Care feed", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                startActivity(new Intent(postOpPatientFeed.this, patientFeed.class));

                            }
                        });

                        menuDialog23.setNeutralButton("Switch to Neonatal feed", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                startActivity(new Intent(postOpPatientFeed.this, neonatalPatientFeed.class));

                            }
                        });





                        menuDialog23.show();
                    }

                });

                menuDialog.setNeutralButton("Filter Patient Feed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final AlertDialog.Builder menuDialog1 = new AlertDialog.Builder(postOpPatientFeed.this);
                        menuDialog1.setTitle("How would you like to filter the feed?");
                        menuDialog1.setMessage("Options: ");
                        menuDialog1.setCancelable(true);

                        menuDialog1.setPositiveButton("Filter By Tag", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //setUpViewByTag();

//                                Query patients1 = patients.whereEqualTo("triageTag","Blue");
//
//                                setUpView(patients1);
                            }
                        });



                        menuDialog1.setNegativeButton("Filter By Heart Rate", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //setUpViewByHeartRate();


                            }
                        });

                        menuDialog1.setNeutralButton("Default List", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                setUpView();
                            }
                        });



                        menuDialog1.show();
                    }

                });


                menuDialog.show();

            }


        });



    }

    private void setUpView() {
//            Query query = patients.whereEqualTo("triageTag","Blue");
        Query query = patients.limit(2);




        // Query nurses = patients.document().;


        ImageView face = (ImageView) findViewById(R.id.facey) ;

        // face.setVisibility(View.VISIBLE);



        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();

        patientAdapter = new PatientAdapter(options);



        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(patientAdapter);





        patientAdapter.setOnItemClickListener(new PatientAdapter.onItemClickListener() {

            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
                final AlertDialog.Builder alertDlg = new AlertDialog.Builder(postOpPatientFeed.this);
                alertDlg.setTitle("Patient Options");
                alertDlg.setMessage("What would you like to do?");
                alertDlg.setCancelable(true);

                alertDlg.setPositiveButton("Delete This Patient", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        final AlertDialog.Builder alertDlg2 = new AlertDialog.Builder(postOpPatientFeed.this);
                        alertDlg2.setTitle("Delete this patient");
                        alertDlg2.setMessage("Are you sure?");
                        alertDlg2.setCancelable(true);

                        alertDlg2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {



                                patientAdapter.deletePatient(position);

                            }


                        });

                        alertDlg2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });

                        alertDlg2.show();
                    }


                });

                alertDlg.setNegativeButton("Add Nurse", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final Dialog dialog1 = new Dialog(postOpPatientFeed.this);
                        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog1.setCancelable(false);
                        dialog1.setContentView(R.layout.addnursedialog);

                        final EditText et = dialog1.findViewById(R.id.et);

                        //String id = patientAdapter.getItemId(position);

                        Button btnok = (Button) dialog1.findViewById(R.id.btnok);
                        btnok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                String nurse = et.getText().toString();



                                //Note patient = documentSnapshot.toObject(Note.class);


                                final DocumentReference patientItem = db.collection("patients3").document(documentSnapshot.getId());

                                //update

                                if(nurse.isEmpty()){

                                    Toast toast = Toast.makeText(getApplicationContext(),"Please Enter the name of the Nurse",Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                else {

                                    patientItem.update("activeNurse", nurse);

                                    Toast.makeText(postOpPatientFeed.this, " Nurse Added", Toast.LENGTH_SHORT).show();



                                    dialog1.dismiss();
                                }
                            }
                        });

                        Button btncn = (Button) dialog1.findViewById(R.id.btncn);
                        btncn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });

                        dialog1.show();

                    }
                });

                alertDlg.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                });





                alertDlg.show();




            }


            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

                Note note = documentSnapshot.toObject(Note.class);
                assert note != null;
                String pname = note.getPatientName();
                String pdescription = note.getDescription();
                String pheight = note.getHeight();
                String pweight = String.valueOf(note.getWeight());
                String prhr = String.valueOf(note.getrHeartRate());
                String id = documentSnapshot.getId();
                Integer position1 = position;
                String bodyTemp = String.valueOf(note.getBodyTempature());
                String activeNurse = note.getActiveNurse();
                String medications = note.getMedications();
                String surgicalHistory = note.getSurgicaHistory();
                String standingO = note.getStandingOrder();


                DocumentSnapshot DocSnap = documentSnapshot;


                DocumentReference ref = documentSnapshot.getReference();

                Intent intent = new Intent(postOpPatientFeed.this,patientProfile.class);
                intent.putExtra("Patient Name",pname);
                intent.putExtra("Patient Description",pdescription);
                intent.putExtra("Patient Height",pheight);
                intent.putExtra("Patient Weight",pweight);
                intent.putExtra("Patient Resting Heart Rate",prhr);
                intent.putExtra("Patient ID",id);
                intent.putExtra("position",position1);
                intent.putExtra("bodyTemp",bodyTemp);
                intent.putExtra("nurse",activeNurse);
                intent.putExtra("medications",medications);
                intent.putExtra("surgicalH",surgicalHistory);
                intent.putExtra("standingO",standingO);
                //intent.putExtra("DocSnap", (Serializable) documentSnapshot);
                //intent.putExtra("Firebse Reference", (Serializable) ref);

                startActivity(intent);






            }
        });




    }

//    private void setUpViewByTag() {
//            Query query = patients.whereEqualTo("triageTag","Blue");
//        //Query query = patients.orderBy("triageTag");
//
//
//
//
//        // Query nurses = patients.document().;
//
//
//        ImageView face = (ImageView) findViewById(R.id.facey) ;
//
//        // face.setVisibility(View.VISIBLE);
//
//
//
//        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
//
//        patientAdapter = new PatientAdapter(options);
//
//
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(patientAdapter);
//
//
//
//
//
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
//        });
//
//
//
//
//    }

//    private void setUpViewByHeartRate() {
////            Query query = patients.whereEqualTo("triageTag","Blue");
//        Query query = patients.orderBy("rHeartRate");
//
//
//
//
//        // Query nurses = patients.document().;
//
//
//        ImageView face = (ImageView) findViewById(R.id.facey) ;
//
//        // face.setVisibility(View.VISIBLE);
//
//
//
//        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
//
//        patientAdapter = new PatientAdapter(options);
//
//
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(patientAdapter);
//
//
//
//
//
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
//        });



//
//    }



    @Override
    protected void onStart(){
        super.onStart();
        patientAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        patientAdapter.stopListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_patient_menu, menu);
        return true;
    }



    public void logout(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
