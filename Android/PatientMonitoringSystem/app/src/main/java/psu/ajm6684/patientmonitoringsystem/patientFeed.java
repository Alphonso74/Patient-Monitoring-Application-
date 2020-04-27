package psu.ajm6684.patientmonitoringsystem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import psu.ajm6684.patientmonitoringsystem.ui.login.LoginActivity;

public class patientFeed extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference patients = db.collection("patients3");
    //private CollectionReference bluePatients = db.collection("patients").whereEqualTo("triageTag","Blue");

    private PatientAdapter patientAdapter;
    RecyclerView recyclerView;
    DocumentReference patientItem;
    Spinner spinner;
    String nurseName;
    DatabaseReference reference;

    ArrayList<String> arrayList;

    EditText e1;
    ImageButton l1;
    ArrayAdapter<String> adapter;
    String name;
    EditText ee;
    int chatmenuindexclicked = -1;
    boolean isEditMode = false;
    Button profileButton;
    Long heartRate;
    Long bodyTemp;
    String department;
    TextView feedTitle;
    String feedType = "regular";
    @Override
    protected void onStart() {
        super.onStart();

        patientAdapter.startListening();


        patients.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                    DocumentSnapshot documentSnapshot = dc.getDocument();


                    String patientName1 = documentSnapshot.get("patientName").toString();
                    String id = documentSnapshot.getId();
                    int oldIndex = dc.getOldIndex();
                    int newIndex = dc.getNewIndex();
                    heartRate = (Long) documentSnapshot.get("rHeartRate");
                    bodyTemp = (Long) documentSnapshot.get("bodyTempature");
                    department = (String) documentSnapshot.get("department");







                    switch (dc.getType()) {
                        case ADDED:
                            //Toast.makeText(patientFeed.this, patientName1, Toast.LENGTH_SHORT).show();


                            break;
                        case MODIFIED:

                            //Toast.makeText(dataSimFeed.this,"Patient: " + patientName1 + ", New Body Temperature - " + documentSnapshot.get("rHeartRate") + ", New Heart Rate - " + documentSnapshot.get("bodyTempature"), Toast.LENGTH_SHORT).show();

                            if(department.equals("General Care")) {
                                if (heartRate < 50 || bodyTemp < 90 || heartRate > 100 || bodyTemp > 110) {

                                    AlertDialog.Builder menuDialog = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                                    menuDialog.setTitle("PATIENT IN CRITICAL CONDITION");
                                    menuDialog.setMessage(patientName1 + " Is in critical condition!!!!");
                                    menuDialog.setCancelable(true);

                                    menuDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

//                                    menuDialog.setNegativeButton("Notify", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                        }
//                                    });


                                    menuDialog.show();


                                }
                            }



                            break;
                        case REMOVED:
                            //Toast.makeText(dataSimFeed.this, "Removed - " + patientName1, Toast.LENGTH_SHORT).show();

                            break;
                    }
                }
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_patient);
        e1 = (EditText) findViewById(R.id.editText);
        l1 = (ImageButton) findViewById(R.id.button_message);
        arrayList = new ArrayList<>();

        feedTitle = (TextView) findViewById(R.id.textView32);
        final Intent intent = getIntent();
        feedType = intent.getStringExtra("Type");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        feedTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                TextView title = new TextView(patientFeed.this);
                title.setText("Filter This Feed");
                title.setPadding(10, 10, 10, 10);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(Color.rgb(0, 153, 204));
                title.setTextSize(23);
                builder.setMessage("Choose the filter type");
                builder.setCustomTitle(title);


                builder.setPositiveButton("Tag", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder tagDialog = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                        TextView title = new TextView(patientFeed.this);
                        title.setText("Choose Tag Color");
                        title.setPadding(10, 10, 10, 10);
                        title.setGravity(Gravity.CENTER);
                        title.setTextColor(Color.rgb(0, 153, 204));
                        title.setTextSize(23);
                        tagDialog.setMessage("Make Selection:");
                        tagDialog.setCustomTitle(title);

                        tagDialog.setPositiveButton("Black", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(patientFeed.this,patientFeed.class);
                                intent.putExtra("Type","Black");
                                startActivity(intent);


                            }
                        });

                        tagDialog.setNeutralButton("Red", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(patientFeed.this,patientFeed.class);
                                intent.putExtra("Type","Red");
                                startActivity(intent);


                            }
                        });

                        tagDialog.setNegativeButton("Yellow", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(patientFeed.this,patientFeed.class);
                                intent.putExtra("Type","Yellow");
                                startActivity(intent);


                            }
                        });


                        AlertDialog dialogTag =  tagDialog.show();


                        TextView messageView = (TextView) dialogTag.findViewById(android.R.id.message);
                        messageView.setGravity(Gravity.CENTER);

                    }
                });

                builder.setNeutralButton("Heart Rate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(patientFeed.this,patientFeed.class);
                        intent.putExtra("Type","Heart Rate");
                        startActivity(intent);


                    }
                });

                builder.setNegativeButton("Critical Status", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(patientFeed.this,patientFeed.class);
                        intent.putExtra("Type","Critical Status");
                        startActivity(intent);

                    }
                });

                AlertDialog dialog =  builder.show();


                TextView messageView = (TextView) dialog.findViewById(android.R.id.message);
                messageView.setGravity(Gravity.CENTER);
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        //  l1.setAdapter(adapter);
        reference = FirebaseDatabase.getInstance().getReference().getRoot();
        //request_username();

        profileButton = (Button) findViewById(R.id.profileButton);


        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(patientFeed.this,UProfile.class);
                startActivity(intent);
            }
        });


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();


                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()) {
                    set.add(((DataSnapshot) i.next()).getKey());

                }

                arrayList.clear();
                arrayList.addAll(set);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(patientFeed.this, "No network connectivity", Toast.LENGTH_SHORT).show();
            }
        });




        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {

                Intent intent = new Intent(patientFeed.this, Chatroom.class);
                startActivity(intent);
                Toast.makeText(patientFeed.this, "Welcome to the Hospital Chatroom! The Staff can communicate with and keep track of the patients and any emergencies.  ", Toast.LENGTH_LONG).show();



            }
        });


        if(feedType == null) {
            setUpView();
        }
        else if(feedType.equals("Critical Status")){

            filterStatusView();

        }
        else if(feedType.equals("Heart Rate")){

            heartRateView();
        }
        else if(feedType.equals("Black")){

            blackTagView();
        } else if(feedType.equals("Red")){

            redTagView();
        } else if(feedType.equals("Yellow")){

            yellowTagView();
        }



        Button menuButton = (Button) findViewById(R.id.button_menu);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder menuDialog = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                menuDialog.setTitle("User Options");
                menuDialog.setMessage("What would you like to do?");
                menuDialog.setCancelable(true);


                menuDialog.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(patientFeed.this, " Goodbye", Toast.LENGTH_SHORT).show();


                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();


                    }


                });


                menuDialog.setPositiveButton("More Options", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AlertDialog.Builder menuDialog23 = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                                menuDialog23.setTitle("More Options");
                                menuDialog23.setMessage("What would you like to do?");
                                menuDialog23.setCancelable(true);

                                menuDialog23.setPositiveButton("Add Patient", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(patientFeed.this, addPatient.class));

                                    }
                                });

                                menuDialog23.setNegativeButton("Post-Op feed", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        startActivity(new Intent(patientFeed.this, postOpPatientFeed.class));

                                    }
                                });

                                menuDialog23.setNeutralButton("Neonatal feed", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        startActivity(new Intent(patientFeed.this, neonatalPatientFeed.class));

                                    }
                                });







                                menuDialog23.show();
                            }

                    });




                menuDialog.setNeutralButton("Data Feed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(patientFeed.this, dataSimFeed.class);
                        startActivity(intent);


                    }

                });


                menuDialog.show();

            }


        });
    }

    public void request_username() {
        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
        builder.setTitle("Enter your name?");
        ee = new EditText(this);
        builder.setView(ee);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                name = ee.getText().toString();


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                request_username();


            }
        });
        builder.show();

    }


    public void insert_data(View v) {

        Map<String, Object> map = new HashMap<>();
        map.put(e1.getText().toString(), "");
        reference.updateChildren(map);
    }


        @RequiresApi(api = Build.VERSION_CODES.N)
    private void setUpView() {
//            Query query = patients.whereEqualTo("triageTag","Blue");
        Query query = patients.whereEqualTo("department","General Care");







        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
        options.getSnapshots().sort(Note.By_Ascending);

        patientAdapter = new PatientAdapter(options);



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(patientAdapter);

            ImageView face = (ImageView) findViewById(R.id.facey);





            patientAdapter.setOnItemClickListener(new PatientAdapter.onItemClickListener() {

            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                alertDlg.setTitle("Patient Options");
                alertDlg.setMessage("What would you like to do?");
                alertDlg.setCancelable(true);
                patientItem = db.collection("patients3").document(documentSnapshot.getId());

                alertDlg.setPositiveButton("Delete This Patient", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        AlertDialog.Builder alertDlg2 = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
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

                alertDlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

                Intent intent = new Intent(patientFeed.this,patientProfile.class);
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
                intent.putExtra("activeNurse",activeNurse);

                //intent.putExtra("DocSnap", (Serializable) documentSnapshot);
                //intent.putExtra("Firebse Reference", (Serializable) ref);

                startActivity(intent);






            }
        });




    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterStatusView() {
//            Query query = patients.whereEqualTo("triageTag","Blue");
        Query query = patients.whereLessThan("rHeartRate",50).whereEqualTo("department","General Care");







        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
        options.getSnapshots().sort(Note.By_Ascending);

        patientAdapter = new PatientAdapter(options);



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(patientAdapter);





        patientAdapter.setOnItemClickListener(new PatientAdapter.onItemClickListener() {

            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                alertDlg.setTitle("Patient Options");
                alertDlg.setMessage("What would you like to do?");
                alertDlg.setCancelable(true);
                patientItem = db.collection("patients3").document(documentSnapshot.getId());

                alertDlg.setPositiveButton("Delete This Patient", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        AlertDialog.Builder alertDlg2 = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
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

                alertDlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

                Intent intent = new Intent(patientFeed.this,patientProfile.class);
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
                intent.putExtra("activeNurse",activeNurse);

                //intent.putExtra("DocSnap", (Serializable) documentSnapshot);
                //intent.putExtra("Firebse Reference", (Serializable) ref);

                startActivity(intent);






            }
        });




    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void heartRateView() {
//            Query query = patients.whereEqualTo("triageTag","Blue");
        Query query = patients.whereEqualTo("department","General Care").orderBy("rHeartRate", Query.Direction.ASCENDING);







        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
        options.getSnapshots().sort(Note.By_Ascending);

        patientAdapter = new PatientAdapter(options);



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(patientAdapter);





        patientAdapter.setOnItemClickListener(new PatientAdapter.onItemClickListener() {

            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                alertDlg.setTitle("Patient Options");
                alertDlg.setMessage("What would you like to do?");
                alertDlg.setCancelable(true);
                patientItem = db.collection("patients3").document(documentSnapshot.getId());

                alertDlg.setPositiveButton("Delete This Patient", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        AlertDialog.Builder alertDlg2 = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
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

                alertDlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

                Intent intent = new Intent(patientFeed.this,patientProfile.class);
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
                intent.putExtra("activeNurse",activeNurse);

                //intent.putExtra("DocSnap", (Serializable) documentSnapshot);
                //intent.putExtra("Firebse Reference", (Serializable) ref);

                startActivity(intent);






            }
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void blackTagView() {
//            Query query = patients.whereEqualTo("triageTag","Blue");
        Query query = patients.whereEqualTo("department","General Care").whereEqualTo("triageTag","Black");







        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
        options.getSnapshots().sort(Note.By_Ascending);

        patientAdapter = new PatientAdapter(options);



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(patientAdapter);





        patientAdapter.setOnItemClickListener(new PatientAdapter.onItemClickListener() {

            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                alertDlg.setTitle("Patient Options");
                alertDlg.setMessage("What would you like to do?");
                alertDlg.setCancelable(true);
                patientItem = db.collection("patients3").document(documentSnapshot.getId());

                alertDlg.setPositiveButton("Delete This Patient", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        AlertDialog.Builder alertDlg2 = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
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

                alertDlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

                Intent intent = new Intent(patientFeed.this,patientProfile.class);
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
                intent.putExtra("activeNurse",activeNurse);

                //intent.putExtra("DocSnap", (Serializable) documentSnapshot);
                //intent.putExtra("Firebse Reference", (Serializable) ref);

                startActivity(intent);






            }
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void redTagView() {
//            Query query = patients.whereEqualTo("triageTag","Blue");
        Query query = patients.whereEqualTo("department","General Care").whereEqualTo("triageTag","Red");







        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
        options.getSnapshots().sort(Note.By_Ascending);

        patientAdapter = new PatientAdapter(options);



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(patientAdapter);





        patientAdapter.setOnItemClickListener(new PatientAdapter.onItemClickListener() {

            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                alertDlg.setTitle("Patient Options");
                alertDlg.setMessage("What would you like to do?");
                alertDlg.setCancelable(true);
                patientItem = db.collection("patients3").document(documentSnapshot.getId());

                alertDlg.setPositiveButton("Delete This Patient", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        AlertDialog.Builder alertDlg2 = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
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

                alertDlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

                Intent intent = new Intent(patientFeed.this,patientProfile.class);
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
                intent.putExtra("activeNurse",activeNurse);

                //intent.putExtra("DocSnap", (Serializable) documentSnapshot);
                //intent.putExtra("Firebse Reference", (Serializable) ref);

                startActivity(intent);






            }
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void yellowTagView() {
//            Query query = patients.whereEqualTo("triageTag","Blue");
        Query query = patients.whereEqualTo("department","General Care").whereEqualTo("triageTag","Yellow");







        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
        options.getSnapshots().sort(Note.By_Ascending);

        patientAdapter = new PatientAdapter(options);



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(patientAdapter);





        patientAdapter.setOnItemClickListener(new PatientAdapter.onItemClickListener() {

            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                alertDlg.setTitle("Patient Options");
                alertDlg.setMessage("What would you like to do?");
                alertDlg.setCancelable(true);
                patientItem = db.collection("patients3").document(documentSnapshot.getId());

                alertDlg.setPositiveButton("Delete This Patient", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        AlertDialog.Builder alertDlg2 = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
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

                alertDlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

                Intent intent = new Intent(patientFeed.this,patientProfile.class);
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
                intent.putExtra("activeNurse",activeNurse);

                //intent.putExtra("DocSnap", (Serializable) documentSnapshot);
                //intent.putExtra("Firebse Reference", (Serializable) ref);

                startActivity(intent);






            }
        });




    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        chatmenuindexclicked = info.position;

        switch(item.getItemId())
        {
            case R.id.chat_update:
                updateMessage();
                break;
            case R.id.chat_delete:
                deleteMessage();
                break;
        }
        return true;
    }

    private void deleteMessage() {
    }

    private void updateMessage() {
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
