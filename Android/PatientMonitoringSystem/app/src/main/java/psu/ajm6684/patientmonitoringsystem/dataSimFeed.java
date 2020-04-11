package psu.ajm6684.patientmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class dataSimFeed extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference patients = db.collection("patients3");
    TextView dataFeed ;

    Integer[] dataChanges = new Integer[2];
    Long heartRate;
    Long bodyTemp;
    Button clearButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sim_feed);




    }

    @Override
    protected void onStart() {
        super.onStart();


        backButton = (Button) findViewById(R.id.backButton);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dataFeed = (TextView) findViewById(R.id.dataFeed);

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

//                            if(heartRate < 20 || bodyTemp < 20 || heartRate >  100 || bodyTemp > 140){
//
//                                AlertDialog.Builder menuDialog = new AlertDialog.Builder(new ContextThemeWrapper(dataSimFeed.this, android.R.style.Theme_Holo_Light));
//                                menuDialog.setTitle("PATIENT IN CRITICAL CONDITION");
//                                menuDialog.setMessage(patientName1 + " Is in critical condition!!!!");
//                                menuDialog.setCancelable(true);
//
//                                menuDialog.setPositiveButton("Button1", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//
//                                    }
//                                });
//
//                                menuDialog.setNegativeButton("Button2", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//
//                                    }
//                                });
//
//
//                                menuDialog.show();
//
//
//                            }

                            dataFeed.append("\nPatient: " + patientName1 + ", New Body Temperature - " + documentSnapshot.get("rHeartRate") + ", New Heart Rate - " + documentSnapshot.get("bodyTempature") + ", Active Nurse - " + documentSnapshot.get("activeNurse"));

                            break;
                        case REMOVED:
                            //Toast.makeText(dataSimFeed.this, "Removed - " + patientName1, Toast.LENGTH_SHORT).show();

                            break;
                    }
                }
            }
        });

        clearButton = (Button) findViewById(R.id.clearButton);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataFeed.setText("");
            }
        });

    }
}
