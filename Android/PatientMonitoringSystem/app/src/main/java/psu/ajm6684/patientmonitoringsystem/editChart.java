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

public class editChart extends Activity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    final private CollectionReference charts = db.collection("charts");

    EditText text;
    String userText;
    TextView patientName;
    String pName;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createchart);
        Intent intent = getIntent();
        //final String pName = intent.getStringExtra("Patient Name");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

       pName = intent.getStringExtra("Patient Name");
       userText = intent.getStringExtra("userText");
       id = intent.getStringExtra("ID");


         patientName = (TextView) findViewById(R.id.PatientNameChart);

//         Toast.makeText(editChart.this, pName, Toast.LENGTH_SHORT).show();

        patientName.setText(pName);

        final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        final String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());

        TextView dateView = (TextView) findViewById(R.id.chartDate);


        dateView.setText(currentDate + " @" + currentTime);
        Button cancel = (Button) findViewById(R.id.cancelButton);
        Button save = (Button) findViewById(R.id.saveButton);
         text = (EditText) findViewById(R.id.TextFieldMain);

        text.setText(userText);



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

//                    charts.add(new chartNote(userText, pName, currentDate, currentTime));

                    charts.document(id).update("userText",userText, "date",currentDate,"time",currentTime);


                    Toast toast = Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT);
                    toast.show();

                    finish();
                }
            }
        });
    }
}
