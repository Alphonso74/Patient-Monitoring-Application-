package psu.ajm6684.patientmonitoringsystem;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class chart extends Activity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    final private CollectionReference charts = db.collection("charts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createchart);
        Intent intent = getIntent();
        //final String pName = intent.getStringExtra("Patient Name");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final String pName = intent.getStringExtra("Patient Name");
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

        TextView patientName = (TextView) findViewById(R.id.PatientNameChart);


        patientName.setText(pName);

        final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        final String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());

        TextView dateView = (TextView) findViewById(R.id.chartDate);


        dateView.setText(currentDate + " @" + currentTime);
        Button cancel = (Button) findViewById(R.id.cancelButton);
        Button save = (Button) findViewById(R.id.saveButton);
        final EditText text = (EditText) findViewById(R.id.TextFieldMain);



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

                    Toast toast = Toast.makeText(getApplicationContext(),"Cannot Submit Empty Chart",Toast.LENGTH_SHORT);
                    toast.show();


                }
                else {

                    charts.add(new chartNote(userText, pName, currentDate, currentTime));



                    Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
                    toast.show();

                    finish();
                }
            }
        });
    }
}
