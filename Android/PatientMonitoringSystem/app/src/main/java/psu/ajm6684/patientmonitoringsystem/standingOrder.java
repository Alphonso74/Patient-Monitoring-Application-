package psu.ajm6684.patientmonitoringsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class standingOrder extends Activity {


    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
//    final private CollectionReference charts = db.collection("patients").document();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createstandingorder);
        Intent intent = getIntent();
        final String pName = intent.getStringExtra("Patient Name");
        final String pID = intent.getStringExtra("Patient ID");

        TextView patientName = (TextView) findViewById(R.id.PatientNameChart1);

        final DocumentReference patientItem = firebaseFirestore.collection("patients3").document(pID);


        patientName.setText(pName);

        final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        final String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());

        TextView dateView = (TextView) findViewById(R.id.chartDate1);


        dateView.setText(currentDate + " @" + currentTime);
        Button cancel = (Button) findViewById(R.id.cancelButton1);
        Button save = (Button) findViewById(R.id.saveButton1);
        final EditText text = (EditText) findViewById(R.id.TextFieldMain1);



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userText = text.getText().toString();

                if(userText.isEmpty()){

                    Toast toast = Toast.makeText(getApplicationContext(),"Cannot Submit Empty Standing Order",Toast.LENGTH_SHORT);
                    toast.show();


                }
                else {

                    //charts.add(new chartNote(userText, pName, currentDate, currentTime));

                    patientItem.update("standingOrder",userText);

                    Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
                    toast.show();

                    finish();
                }
            }
        });
    }
}