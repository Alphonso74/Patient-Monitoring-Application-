package psu.ajm6684.patientmonitoringsystem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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







                    switch (dc.getType()) {
                        case ADDED:
                            //Toast.makeText(patientFeed.this, patientName1, Toast.LENGTH_SHORT).show();


                            break;
                        case MODIFIED:

                            //Toast.makeText(dataSimFeed.this,"Patient: " + patientName1 + ", New Body Temperature - " + documentSnapshot.get("rHeartRate") + ", New Heart Rate - " + documentSnapshot.get("bodyTempature"), Toast.LENGTH_SHORT).show();

                            if(heartRate < 20 || bodyTemp < 20 || heartRate >  100 || bodyTemp > 140){

                                AlertDialog.Builder menuDialog = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                                menuDialog.setTitle("PATIENT IN CRITICAL CONDITION");
                                menuDialog.setMessage(patientName1 + " Is in critical condition!!!!");
                                menuDialog.setCancelable(true);

                                menuDialog.setPositiveButton("Button1", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                menuDialog.setNegativeButton("Button2", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });


                                menuDialog.show();


                            }


                            break;
                        case REMOVED:
                            //Toast.makeText(dataSimFeed.this, "Removed - " + patientName1, Toast.LENGTH_SHORT).show();

                            break;
                    }
                }
            }
        });

//        patients.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
//                if (e != null) {
//                    return;
//                }
//
//                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
//                    DocumentSnapshot documentSnapshot = dc.getDocument();
//                    String patientName1 = documentSnapshot.get("patientName").toString();
//                    String id = documentSnapshot.getId();
//                    int oldIndex = dc.getOldIndex();
//                    int newIndex = dc.getNewIndex();
//
//                    switch (dc.getType()) {
//                        case ADDED:
//                            //Toast.makeText(patientFeed.this, patientName1, Toast.LENGTH_SHORT).show();
//
//
//                            break;
//                        case MODIFIED:
//
//                            Toast.makeText(patientFeed.this,"Patient: " + patientName1 + ", New Body Temperature - " + documentSnapshot.get("rHeartRate") + ", New Heart Rate - " + documentSnapshot.get("bodyTempature"), Toast.LENGTH_SHORT).show();
//
//
//
//                            break;
//                        case REMOVED:
//                            Toast.makeText(patientFeed.this, "Removed - " + patientName1, Toast.LENGTH_SHORT).show();
//
//                            break;
//                    }
//                }
//            }
//        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_patient);
        e1 = (EditText) findViewById(R.id.editText);
        l1 = (ImageButton) findViewById(R.id.button_message);
        arrayList = new ArrayList<>();

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
                Toast.makeText(patientFeed.this, "No network connectivity", Toast.LENGTH_SHORT).show();
            }
        });


        //ImageButton imageButton = (ImageButton) findViewById(R.id.button_message);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                builder.setTitle("Enter your name:");
                ee = new EditText(patientFeed.this);
                builder.setView(ee);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        name = ee.getText().toString();
                        Intent intent = new Intent(patientFeed.this, Chatroom.class);
                       // intent.putExtra("room_name", ((TextView) view).getText().toString());
                        intent.putExtra("room_name","Admin");
                        intent.putExtra("user_name", name);

                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();



                    }
                });
                builder.show();



            }
        });

                    setUpView();

//         addPatient = (Button) findViewById(R.id.addPatient);


//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        Query query = patients;
//        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();

//        patientAdapter = new PatientAdapter(options);

//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(patientAdapter);

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




//                 menuDialog.setPositiveButton("Add Patient", new DialogInterface.OnClickListener() {
//                     @Override
//                     public void onClick(DialogInterface dialog, int which) {


//                         startActivity(new Intent(patientFeed.this, addPatient.class));


                                menuDialog23.show();
                            }

                    });




                menuDialog.setNeutralButton("Data Feed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(patientFeed.this, dataSimFeed.class);
                        startActivity(intent);

//                        AlertDialog.Builder menuDialog1 = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
//                        menuDialog1.setTitle("How would you like to filter the feed?");
//                        menuDialog1.setMessage("Options: ");
//                        menuDialog1.setCancelable(true);

//                        menuDialog1.setPositiveButton("Filter By Tag", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                //setUpViewByTag();
//
////                                Query patients1 = patients.whereEqualTo("triageTag","Blue");
////
////                                setUpView(patients1);
//                            }
//                        });


//                        menuDialog1.setNegativeButton("Filter By Heart Rate", new DialogInterface.OnClickListener() {
//                            @RequiresApi(api = Build.VERSION_CODES.N)
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                setUpViewByHeartRate();
//
//
//                            }
//                        });

//                        menuDialog1.setNeutralButton("Default List", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                setUpView();
//                            }
//                        });


                       // menuDialog1.show();
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




           // Query nurses = patients.document().;


//            ImageView face = (ImageView) findViewById(R.id.facey) ;

           // face.setVisibility(View.VISIBLE);



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

//                    alertDlg.setNegativeButton("Add Nurse", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
//                                    TextView title = new TextView(patientFeed.this);
//                                    title.setText("Choose a Nurse:");
//                                    title.setPadding(10, 10, 10, 10);
//                                    title.setGravity(Gravity.CENTER);
//                                    title.setTextColor(Color.rgb(0, 153, 204));
//                                    title.setTextSize(23);
//                                    builder.setCustomTitle(title);
//
//                                    LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                                    View layout_spinners = inflater.inflate(R.layout.addnursedialog,null);
//                                    spinner = (Spinner) layout_spinners.findViewById(R.id.spinner_titulo_carpetas);
//
//                                    builder.setPositiveButton("Add Nurse", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                            nurseName = spinner.getSelectedItem().toString();
//
//                                            documentSnapshot.
//                                            .update("activeNurse", nurseName);
//
//
//                                            Intent intent = new Intent(patientProfile.this,patientProfile.class);
//                                            intent.putExtra("Patient Name",patientName);
//                                            intent.putExtra("Patient Description",patientDescription);
//                                            intent.putExtra("Patient Height",patientHeight);
//                                            intent.putExtra("Patient Weight",patientWeight);
//                                            intent.putExtra("Patient Resting Heart Rate",patientRestingHeartRate);
//                                            intent.putExtra("Patient ID",patientID);
//                                            intent.putExtra("position",position);
//                                            intent.putExtra("bodyTemp",bodyTemp);
//                                            intent.putExtra("nurse",nurseName);
//                                            intent.putExtra("medications",medications);
//                                            intent.putExtra("surgicalH",sHistory);
//                                            intent.putExtra("standingO",standingO);
//
//
//                                            startActivity(intent);
//
//
//                                        }
//                                    });
//
//
//                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//
//                                }
//                            });
//
//                            builder.setView(layout_spinners);
//                            builder.setCancelable(true);
//                            builder.show();
//
//
//                            db.collection("Users")
//                                    .whereEqualTo("position", "Nurse")
//                                    .get()
//                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                            final List<String> spinnerArray =  new ArrayList<String>();
//                                            QuerySnapshot queryDocumentSnapshots = task.getResult();
//                                            if (task.isSuccessful()) {
//
//
//                                                for (DocumentSnapshot document :  queryDocumentSnapshots.getDocuments()) {
//                                                    //Log.d(TAG, document.getId() + " => " + document.getData());
//
//                                                    String nurseName = Objects.requireNonNull(document.get("fullName")).toString();
//
//                                                    spinnerArray.add(nurseName);
//
//                                                    // Toast.makeText(patientProfile.this,nurseName, Toast.LENGTH_SHORT).show();
//                                                }
//                                            } else {
////                                    Log.d(TAG, "Error getting documents: ", task.getException());
//                                            }
//                                            ArrayAdapter<String> adapter = new ArrayAdapter<>(patientProfile.this, android.R.layout.simple_dropdown_item_1line, spinnerArray);
//
//
//                                            adapter.notifyDataSetChanged();
//                                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//
//                                            spinner.setAdapter(adapter);
//                                            spinner.setEnabled(true);
//
//                                        }
//                                    });
//
//
//
//                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//
//                                @Override
//                                public void onItemSelected(AdapterView<?> parent, View v,
//                                                           int postion, long arg3) {
//                                    // TODO Auto-generated method stub
//                                    Object nurseName = parent.getItemAtPosition(postion).toString();
//                                    String  spinnerValue = parent.getItemAtPosition(postion).toString();
//
//
//
//                                    nurseName = parent.getItemAtPosition(position).toString();
//                                    ((TextView) v).setTextColor(Color.BLACK);
//
//
//
//
//                                }
//
//                                @Override
//                                public void onNothingSelected(AdapterView<?> arg0) {
//                                    // TODO Auto-generated method stub
//
//                                }
//                            });
//
//                            dialog1.show();
//
//                        }
//                    });
//
//                    alertDlg.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//
//
//                        }
//                    });
//
//
//
//
//
//                    alertDlg.show();




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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setUpViewByHeartRate() {
//            Query query = patients.whereEqualTo("triageTag","Blue");

        Query query = patients.orderBy("rHeartRate", Query.Direction.DESCENDING);

//        Toast.makeText(getApplicationContext(),"Tester",Toast.LENGTH_SHORT).show();


        // Query nurses = patients.document().;



        // face.setVisibility(View.VISIBLE);

        patientAdapter.notifyDataSetChanged();


        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
        options.getSnapshots().sort(Note.By_Descending);
        patientAdapter = new PatientAdapter(options);



         recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(patientAdapter);




        patientAdapter.setOnItemClickListener(new PatientAdapter.onItemClickListener() {

            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, final int position) {
                final AlertDialog.Builder alertDlg = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
                alertDlg.setTitle("Patient Options");
                alertDlg.setMessage("What would you like to do?");
                alertDlg.setCancelable(true);

                alertDlg.setPositiveButton("Delete This Patient", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        final AlertDialog.Builder alertDlg2 = new AlertDialog.Builder(new ContextThemeWrapper(patientFeed.this, android.R.style.Theme_Holo_Light));
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

                        final Dialog dialog1 = new Dialog(patientFeed.this);
                        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog1.setCancelable(false);
                        dialog1.setContentView(R.layout.addnursedialog);

//                        final EditText et = dialog1.findViewById(R.id.et);

                        //String id = patientAdapter.getItemId(position);

                       // Button btnok = (Button) dialog1.findViewById(R.id.btnok);
//                        btnok.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//
//                              //  String nurse = et.getText().toString();
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
//                                String nurse = null;
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

//                        Button btncn = (Button) dialog1.findViewById(R.id.btncn);
//                        btncn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                dialog1.dismiss();
//                            }
//                        });

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

                String patientFeed = "PatientFeed";
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
                //intent.putExtra("DocSnap", (Serializable) documentSnapshot);
                //intent.putExtra("Firebse Reference", (Serializable) ref);
                intent.putExtra("FeedType", patientFeed);

                startActivity(intent);






            }
        });




    }



//    @Override
//    protected void onStart(){
//        super.onStart();
//        patientAdapter.startListening();
//    }

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
