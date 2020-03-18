package psu.ajm6684.patientmonitoringsystem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class chart extends Activity {


    private Button save;
    private Button cancel;
    private EditText text;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    final private CollectionReference users = db.collection("Charts");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createchart);
        Intent intent = getIntent();
        final String pName = intent.getStringExtra("Patient Name");
        TextView patientName = (TextView) findViewById(R.id.PatientNameChart);

        patientName.setText(pName);

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        TextView dateView = (TextView) findViewById(R.id.chartDate);

        dateView.setText(currentDate + " @" + currentTime);
        Button cancel = (Button) findViewById(R.id.cancelButton);
        Button save = (Button) findViewById(R.id.saveButton);
        EditText text = (EditText) findViewById(R.id.text);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(getApplicationContext(), "Chart Saved", Toast.LENGTH_SHORT);
                toast.show();

                finish();
                saveNote();
            }
        });

    }


        private void saveNote() {
            String name = text.getText().toString();

            if (name.trim().isEmpty())
            {

                Toast.makeText(this, "Please fill in the fields", Toast.LENGTH_LONG).show();

            } else {

                CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Charts");

                collectionReference.add(new Note(text));

                Toast.makeText(this, "Chart is saved", Toast.LENGTH_LONG).show();

                finish();
                saveNote();
            }

        }
}
